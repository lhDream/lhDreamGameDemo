import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGL.*;

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
     * main 程序的开始
     * @param args 参数
     */
    public static void main(String[] args) {
        launch(args);
    }
}
