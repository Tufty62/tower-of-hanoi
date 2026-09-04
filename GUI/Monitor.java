package GUI;
import javafx.stage.Screen;

public class Monitor {
    private final Screen screen;
    private final String name;

    public Monitor(Screen screen, String name) {
        this.screen = screen;
        this.name = name;
    }

    public Screen getScreen() {
        return this.screen;
    }

    public String getName() {
        return this.name;
    }

    @Override 
    public String toString() {
        return this.name;
    }

}
