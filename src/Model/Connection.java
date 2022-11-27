package Model;

public class Connection {

    UserClass toClass;
    int toID;
    ConnectionType type;

    public Connection(UserClass to, int toID, ConnectionType connectionType) {
        toClass = to;
        this.toID = toID;
        type = connectionType;
    }

    public ConnectionType getType() {
        return type;
    }

    public UserClass getToClass() {
        return toClass;
    }

    public int getToID() {
        return toID;
    }
}
