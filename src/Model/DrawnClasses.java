package Model;

import java.util.ArrayList;

public class DrawnClasses {

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

    public UserClass getClassByID(int id) {
        return classes.get(id);
    }

    public void addUserClass(int x, int y) {
        classes.add(new UserClass(x,y));
    }

    public void reset() {
        classes.clear();
    }
}
