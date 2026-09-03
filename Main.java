import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class Main extends Application{
    @Override 
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 200, 150);
        scene.setFill(Color.AQUA);
        stage.setTitle("Tower of Hanoi");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
