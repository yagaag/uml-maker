package View;

import Model.ConnectionType;
import Model.UserClass;

public interface DrawingPanel {
    public void drawRectangle(int x, int y, String content);
    public void drawConnection(UserClass from, UserClass to, ConnectionType type);
    public void redraw();
    public void clearAll();
}
