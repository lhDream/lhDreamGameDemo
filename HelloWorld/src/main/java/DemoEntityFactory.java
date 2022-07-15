import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static com.almasb.fxgl.core.math.FXGLMath.random;
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
        var emitter = new ParticleEmitter();
        emitter.setMaxEmissions(Integer.MAX_VALUE);
        // 粒子数
        emitter.setNumParticles(50);
        emitter.setEmissionRate(0.6);
        // 粒子大小（最小值，最大值），创建的大小将是最小值（包括）和最大值（不包括）之间的随机值。
        emitter.setSize(1, 5);
        // 定义粒子大小如何随时间变化。
        emitter.setScaleFunction(i -> FXGLMath.randomPoint2D().multiply(0.2));
        // 定义粒子的过期时间
        emitter.setExpireFunction(i -> Duration.seconds(random(0.25, 2.5)));
        // 例子偏移方向
        emitter.setAccelerationFunction(() -> new Point2D(1,1));
        // 例子初始速度
        emitter.setVelocityFunction(i -> FXGLMath.randomPoint2D().multiply(random(1, 10)));
        // 例子初始位置(实体的相对坐标,0,0即实体的 x,y位置/ 30,50即实体的 x+30,y+50位置)
        emitter.setSpawnPointFunction(i-> new Point2D(30,60));
//        emitter.setColor(Color.BLUE);
        emitter.setStartColor(Color.BLUE);
        emitter.setEndColor(Color.RED);
        return FXGL.entityBuilder(data)
                // 声明设备类型
                .type(EntityType.PLAYER)
                // 将实体标记为可碰撞，并根据UI大小自动生成碰撞体积
                .collidable()
                // 添加粒子效果
                .with(new ParticleComponent(emitter))
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
        var speed = random(200,400);
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
