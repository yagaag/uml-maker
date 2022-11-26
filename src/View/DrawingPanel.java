package View;

public interface DrawingPanel {
    public void drawRectangle(int x, int y, String content);
    public void drawConnection(int from, int to);
    public void clearAll();
}
