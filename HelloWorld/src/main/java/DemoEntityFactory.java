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
                // 宽 10px 高 10px 颜色黑色
                .viewWithBBox(new Rectangle(10,10, Color.BLACK))
                .build();
    }

}
