import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


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
        return FXGL.entityBuilder(data)
                // 声明设备类型
                .type(EntityType.PLAYER)
                // 将实体标记为可碰撞，并根据UI大小自动生成碰撞体积
                .collidable()
                // 宽 10px 高 10px 颜色黑色
                .viewWithBBox(new Rectangle(50,50, Color.BLACK))
                .build();
    }

    /**
     * 创建Gold实体
     * @return 玩家实体
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

}
