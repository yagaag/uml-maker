package View;

import Model.ConnectionType;

public interface DrawingPanel {
    public void drawRectangle(int x, int y, String content);
    public void drawConnection(int from, int to, ConnectionType type);
    public void redraw();
    public void clearAll();
}
