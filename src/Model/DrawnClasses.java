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

//    public boolean loadFromFile(String fileName) {
//        FileProcessor processor = new FileProcessor();
//        processor.load
//    }

    public int getLength() {
        return classes.size();
    }

    public UserClass getClassByID(int id) {
        return classes.get(id);
    }

    public void addUserClass(int x, int y, String name) {
        classes.add(new UserClass(x, y, name));
        this.setChanged();
        this.notifyObservers();
    }

    public void changeClassPosition(int id, int x, int y) {
        classes.get(id).setCenter(x, y);
    }

    public void addConnection(int from, int to, ConnectionType type) {
        Connection c = new Connection(classes.get(to), to, type);
        classes.get(from).addConnection(c);
        this.setChanged();
        this.notifyObservers();
    }

    public void reset() {
        classes.clear();
        this.setChanged();
        this.notifyObservers();
    }
}
