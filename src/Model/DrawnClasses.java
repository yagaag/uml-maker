package Model;

import java.util.ArrayList;
import java.util.Observable;

public class DrawnClasses extends Observable {

    private static DrawnClasses instance;
    ArrayList<UserClass> classes = new ArrayList<>();

    private DrawnClasses() {}

    public static DrawnClasses getInstance() {
        if (instance == null) {
            instance = new DrawnClasses();
        }
        return instance;
    }

    public int getLength() {
        return classes.size();
    }

    public String getText() {
        return "Hellow";
    }

    public UserClass getClassByID(int id) {
        return classes.get(id);
    }

    public void addUserClass(int x, int y) {
        classes.add(new UserClass(x,y));
        this.setChanged();
        this.notifyObservers();
    }

    public void addConnection(int from, int to) {

    }

    public void reset() {
        classes.clear();
    }
}
