import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.texture;

/**
 * 实体工厂
 * @author lhDream
 */
public class DemoEntityFactory implements EntityFactory {

    /**
     * 创建玩家实体
     * @return 玩家实体
     */
    @Spawns("Player")
    public Entity newPlayer(SpawnData data){
        //引入Player图片资源
        var texture = texture("player.gif");
        return FXGL.entityBuilder(data)
                // 声明设备类型
                .type(EntityType.PLAYER)
                // 将实体标记为可碰撞，并根据UI大小自动生成碰撞体积
                .collidable()
                // 宽 10px 高 10px 颜色黑色
                .viewWithBBox(texture)
                .build();
    }

    /**
     * 创建Gold实体
     * @return Gold实体
     */
    @Spawns("Gold")
    public Entity newGold(SpawnData data){
        return FXGL.entityBuilder(data)
                //声明设备类型
                .type(EntityType.GOLD)
                // 根据UI大小自动生成碰撞体积
                .collidable()
                // 宽 50px 高 50px 颜色红色
                .viewWithBBox(new Rectangle(50,50, Color.RED))
                .build();
    }
    /**
     * 创建Enemy实体
     * @return Enemy实体
     */
    @Spawns("Enemy")
    public Entity newEnemy(SpawnData data){
        // 移动速度, 200 - 400 内随机
        var speed = FXGLMath.random(200,400);
        return FXGL.entityBuilder(data)
                //声明设备类型
                .type(EntityType.ENEMY)
                // 根据UI大小自动生成碰撞体积
                .collidable()
                .with(new ProjectileComponent(new Point2D(0, 1), speed))
                // 宽 50px 高 50px 颜色蓝色
                .viewWithBBox(new Rectangle(50,50, Color.BLUE))
                // 超出边界移除实体
                .with(new OffscreenCleanComponent())
                .build();
    }

}
