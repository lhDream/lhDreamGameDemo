import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;

/**
 * @author lhDream
 */
public class HelloWorldApp extends GameApplication {

    /**
     * 玩家实体
     */
    private Entity player;

    /**
     * 初始化设置
     * @param settings 配置对象
     */
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Hello world");
        settings.setWidth(600);
        settings.setHeight(600);

        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(true);
    }

    /**
     * 初始化游戏内容
     */
    @Override
    protected void initGame() {
        // 注册实体工厂
        FXGL.getGameWorld().addEntityFactory(new DemoEntityFactory());

        // 在界面正中间生成玩家实体
        player = FXGL.spawn("Player",FXGL.getAppWidth()/2,FXGL.getAppHeight()/2);

        // 在界面200,200位置生成一个Gold实体
        spawn("Gold", 200,200);

        // 每秒生成一个敌方实体
        run(()->{
            // 生成坐标
            var x = FXGLMath.random(0,getAppWidth()-50);
            var y = FXGLMath.random(-50,0);

            spawn("Enemy",x,y);
        },Duration.seconds(1));
    }

    /**
     * 初始化输入
     */
    @Override
    protected void initInput() {
        onKey(KeyCode.W,"上",()->{
            player.translateY(-5);
        });
        onKey(KeyCode.S,"下",()->{
            player.translateY(+5);
        });
        onKey(KeyCode.A,"左",()->{
            player.translateX(-5);
        });
        onKey(KeyCode.D,"右",()->{
            player.translateX(+5);
        });
    }

    /**
     * 初始化物理引擎（碰撞效果）
     */
    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER,EntityType.GOLD){
            @Override
            protected void onCollisionBegin(Entity player, Entity gold) {
                //将gold对象从游戏中移除
                gold.removeFromWorld();
                // 设置参数+1
                inc("integral",+1);
                // 在界面内随机位置生成一个 Gold对象
                spawn("Gold", FXGLMath.random(0,getAppWidth()-50),FXGLMath.random(0,getAppHeight()-50));
            }
        });

        onCollisionBegin(EntityType.PLAYER, EntityType.ENEMY,(player, enemy)->{
            inc("integral",-1);
        });
    }

    /**
     * 初始化参数
     * @param vars
     */
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("integral",0);
    }

    /**
     * 初始化UI
     */
    @Override
    protected void initUI() {
        // 初始化 Text 字体大小18 颜色 粉色
        var scoreText = getUIFactoryService().newText("", Color.PINK, 18);
        scoreText.textProperty().bind(getip("integral").asString("分数: %d"));
        addUINode(scoreText, 10, 30);
    }


    /**
     * main 程序的开始
     * @param args 参数
     */
    public static void main(String[] args) {
        launch(args);
    }
}
