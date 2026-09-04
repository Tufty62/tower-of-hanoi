package GUI;
import java.util.Map;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;

public class Settings {

    private final Stage stage;
    private final Scene scene;
    private final ComboBox<Resolution> resolutionDropdown = new ComboBox<>();
    private final ComboBox<Monitor> monitorDropdown = new ComboBox<>();

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

    public Settings(Stage stage) {
        this.stage = stage;
        
        //create elements
        Label title = new Label("Settings");

        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-background-color: black;");

        Label resolutionLbl = new Label("Resolution:");

        CheckBox fullscreenCB = new CheckBox("Fullscreen");
        fullscreenCB.setIndeterminate(false);

        Label monitorLbl = new Label("Monitor");

        //populate dropdowns
        List<Resolution> availableSizes = get_resolutions();
        List<Monitor> monitors = getMonitors();

        this.resolutionDropdown.getItems().addAll(availableSizes);
        this.monitorDropdown.getItems().addAll(monitors);

        this.resolutionDropdown.setValue(availableSizes.getLast());

        for (Monitor monitor: monitors) {
            if (monitor.getScreen().equals(getCurrentScreen())) {
                this.monitorDropdown.setValue(monitor);
                break;
            }
        } 

        //handle inputs
        resolutionDropdown.setOnAction(event -> {
            handleResolutionChange();
        });

        monitorDropdown.setOnAction(event -> {
            handleMonitorChange();
        });


        //add to root
        vbox.getChildren().add(title);
        vbox.getChildren().add(resolutionLbl);
        vbox.getChildren().add(resolutionDropdown);
        vbox.getChildren().add(fullscreenCB);
        vbox.getChildren().add(monitorLbl);
        vbox.getChildren().add(monitorDropdown);

        this.scene = new Scene(vbox, stage.getWidth(), stage.getHeight());
    }

    public Scene getScene() {
        return this.scene;
    }

    private List<Resolution> get_resolutions() {
        return get_resolutions(getCurrentScreen());
    }

    private List<Resolution> get_resolutions(Screen screen) {
        Rectangle2D bounds = screen.getBounds();

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

    private List<Monitor> getMonitors() {
        List<Screen> screens = Screen.getScreens();
        List<Monitor> monitors = new ArrayList<>();
        
        for (int i = 0; i < screens.size(); i++) {
            Screen s = screens.get(i);
            monitors.add(new Monitor(s, "Display " + (i+1) + ": " + s.getBounds().getWidth() + "x" + s.getBounds().getHeight()));    
        }

        return monitors;
    }

    private void handleResolutionChange () {
        Resolution selectedResolution = this.resolutionDropdown.getValue();

        if (selectedResolution == null) {
            return;
        }

        Screen screen = getCurrentScreen();
        
        applyResolution(selectedResolution, screen);

    }

    private void handleMonitorChange() {
        Monitor selectedMonitor = monitorDropdown.getValue();

        if (selectedMonitor == null) {
            return;
        }
        Screen screen = selectedMonitor.getScreen();

        if (stage.isFullScreen()) {
            stage.setFullScreenExitHint("");
            stage.setFullScreen(true);
            return;
        }

        List<Resolution> availableSizes = get_resolutions(screen);
        Resolution smallestResolution = availableSizes.getLast();

        this.resolutionDropdown.getItems().setAll(availableSizes);
        this.resolutionDropdown.setValue(smallestResolution);
        applyResolution(smallestResolution, screen);
    }

    private void applyResolution(Resolution resolution, Screen screen) {
        stage.setWidth(resolution.getWidth());
        stage.setHeight(resolution.getHeight());

        Rectangle2D bounds = screen.getVisualBounds();

        double x = bounds.getMinX() + (bounds.getWidth() - stage.getWidth()) / 2;

        double y = bounds.getMinY() + (bounds.getHeight() - stage.getHeight()) / 2;

        stage.setX(x);
        stage.setY(y);
    }
    
    private Screen getCurrentScreen() {
        double centerX = this.stage.getX() + this.stage.getWidth() / 2;
        double centerY = this.stage.getY() + this.stage.getHeight() / 2;

        List<Screen> screens = Screen.getScreensForRectangle(
            centerX,
            centerY,
            1,
            1
        );

        return screens.get(0);
    }
}
