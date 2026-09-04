package GUI;

public class Resolution {
    private final int width;
    private final int height;

    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override 
    public String toString() {
        return Integer.toString(this.width) + "x" + Integer.toString(this.height);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
