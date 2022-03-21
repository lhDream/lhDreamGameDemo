import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

/**
 * @author lhDream
 */
public class HelloWorldApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Hello world");
        settings.setWidth(600);
        settings.setHeight(600);
    }

    /**
     * main 程序的开始
     * @param args 参数
     */
    public static void main(String[] args) {
        launch(args);
    }
}
