
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

import GUI.Settings;
import javafx.geometry.Rectangle2D;


public class Main extends Application{
    @Override 
    public void start(Stage stage) {
        Screen targetScreen = Screen.getScreens().get(1);
        Rectangle2D bounds = targetScreen.getVisualBounds();
        Text text = new Text(10, 90, "Settings");
        text.setFill(Color.WHITE);
        Font font = new Font(20);
        text.setFont(font);
        Group root = new Group();
        root.getChildren().add(text);
        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.BLACK);
        stage.setTitle("Tower of Hanoi");
        stage.setOpacity(0);
        stage.setScene(scene);
        stage.show();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setOpacity(1);
        Settings settings = new Settings(stage);
        Scene settingsScene = settings.getScene();
        text.setOnMouseClicked(event -> {stage.setScene(settingsScene);});
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
