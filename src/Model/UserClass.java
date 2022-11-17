package Model;

import java.util.ArrayList;

public class UserClass {

    Point center;
    String title;
    ArrayList<Connection> connections = new ArrayList<>();

    public UserClass(int x, int y) {
        this.center = new Point(x,y);
    }

    public void addConnection() {

    }

    public int xCoord() {
        return center.xCoord();
    }

    public int yCoord() {
        return center.yCoord();
    }

}
