package GUI;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.List;
import javafx.geometry.Rectangle2D;
import javafx.scene.text.Font;


public class Main extends Application{
    @Override 
    public void start(Stage stage) {
        Screen targetScreen = Screen.getPrimary();
        Rectangle2D bounds = targetScreen.getVisualBounds();
        Text text = new Text(10, 90, "Displaying on Monitor: " + targetScreen.toString());
        text.setFill(Color.WHITE);
        Font font = new Font(20);
        text.setFont(font);
        Group root = new Group();
        root.getChildren().add(text);
        Scene scene = new Scene(root, bounds.getWidth() * 0.7, bounds.getHeight() * 0.7);
        scene.setFill(Color.BLACK);
        stage.setTitle("Tower of Hanoi");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
