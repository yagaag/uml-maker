package Model;

import java.util.ArrayList;

public class UserClass {

    int x;
    int y;
    String title;
    ArrayList<Connection> connections = new ArrayList<>();

    public UserClass(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addConnection() {

    }

}
