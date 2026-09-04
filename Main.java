
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import GUI.Settings;
import javafx.geometry.Rectangle2D;
import javafx.scene.text.Font;


public class Main extends Application{
    @Override 
    public void start(Stage stage) {
        Settings settings = new Settings(Screen.getPrimary());
        Scene settingsScene = settings.getScene();
        Screen targetScreen = Screen.getPrimary();
        System.out.println(targetScreen.getVisualBounds());
        Rectangle2D bounds = targetScreen.getVisualBounds();
        Text text = new Text(10, 90, "Settings");
        text.setFill(Color.WHITE);
        Font font = new Font(20);
        text.setFont(font);
        text.setOnMouseClicked(event -> {stage.setScene(settingsScene);});
        Group root = new Group();
        root.getChildren().add(text);
        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        scene.setFill(Color.BLACK);
        stage.setTitle("Tower of Hanoi");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
