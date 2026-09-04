package GUI;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.List;

public class Settings {

    Map<String, List<Resolution>> resolutions = Map.of(
        "16:9", List.of(
            new Resolution(3840, 2160),
            new Resolution(2560, 1440),
            new Resolution(1920, 1080), 
            new Resolution(1600, 900),
            new Resolution(1280, 720)
        ),
        "16:10", List.of(
            new Resolution(2880, 1800),
            new Resolution(2560, 1600),
            new Resolution(1920, 1200),
            new Resolution(1600, 1000),
            new Resolution(1280, 800)
        )
    );

    public Scene getScene() {
        StackPane root = new StackPane();

        Label title = new Label("Settings");

        VBox vbox = new VBox(10);
        Label resolution_lbl = new Label("Resolution:");
        ComboBox<Resolution> resolutionDropdown; 

        return new Scene(root);
    }

    public List<Resolution> get_resolutions(Screen screen) {
        Rectangle2D bounds = screen.getVisualBounds();

        double maxWidth = bounds.getWidth();
        double maxHeight = bounds.getHeight();

        double targetAspectRatio = 16.0 / 10.0;
        double tolerance = 0.05;

        List<Resolution> resolutions;

        if (isAspectRatio(maxWidth / maxHeight, targetAspectRatio, tolerance)) {
            resolutions = this.resolutions.get("16:10");
            }
        else {
            resolutions = this.resolutions.get("16:9");
        }

        List<Resolution> availableResolutions = new ArrayList<Resolution>();

        for (Resolution resolution: resolutions) {
            if (resolution.getWidth() <= maxWidth && resolution.getHeight() <= maxHeight) {
                availableResolutions.add(resolution);
            }
        }

        return availableResolutions;
    }

    public boolean isAspectRatio(double value, double target, double tolerance) {
        return Math.abs(value - target) < tolerance;
    }
    
}
