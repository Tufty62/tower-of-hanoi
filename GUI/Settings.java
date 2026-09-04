package GUI;
import java.util.Map;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class Settings {

    private final Screen screen;
    private final Scene scene;

    private final Map<String, List<Resolution>> resolutions = Map.of(
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

    public Settings(Screen screen) {
        this.screen = screen;
        
        Label title = new Label("Settings");

        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-background-color: black;");

        Label resolutionLbl = new Label("Resolution:");
        List<Resolution> availableSizes = get_resolutions();
        ComboBox<Resolution> resolutionDropdown = new ComboBox<>();
        resolutionDropdown.getItems().addAll(availableSizes);

        CheckBox fullscreenCB = new CheckBox("Fullscreen");
        fullscreenCB.setIndeterminate(false);

        Label monitorLbl = new Label("Monitor");
        ComboBox<Monitor> monitorDropdown = new ComboBox<>();
        monitorDropdown.getItems().addAll(getMonitors());

        vbox.getChildren().add(title);
        vbox.getChildren().add(resolutionLbl);
        vbox.getChildren().add(resolutionDropdown);
        vbox.getChildren().add(fullscreenCB);
        vbox.getChildren().add(monitorLbl);
        vbox.getChildren().add(monitorDropdown);

        this.scene = new Scene(vbox);
    }

    public Scene getScene() {
        return this.scene;
    }

    private final List<Resolution> get_resolutions() {
        Rectangle2D bounds = this.screen.getVisualBounds();

        double maxWidth = bounds.getWidth();
        double maxHeight = bounds.getHeight();

        double targetAspectRatio = 16.0 / 10.0;
        double tolerance = 0.05;

        List<Resolution> availableSizes;

        if (isAspectRatio(maxWidth / maxHeight, targetAspectRatio, tolerance)) {
            availableSizes = this.resolutions.get("16:10");
            }
        else {
            availableSizes = this.resolutions.get("16:9");
        }

        List<Resolution> availableResolutions = new ArrayList<>();

        for (Resolution resolution: availableSizes) {
            if (resolution.getWidth() <= maxWidth && resolution.getHeight() <= maxHeight) {
                availableResolutions.add(resolution);
            }
        }

        return availableResolutions;
    }

    private boolean isAspectRatio(double value, double target, double tolerance) {
        return Math.abs(value - target) < tolerance;
    }

    public final List<Monitor> getMonitors() {
        List<Screen> screens = Screen.getScreens();
        List<Monitor> monitors = new ArrayList<>();
        
        for (int i = 0; i < screens.size(); i++) {
            monitors.add(new Monitor(screens.get(i), "Display " + (i+1)));    
        }

        return monitors;
    }

}
