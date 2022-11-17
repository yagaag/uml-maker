package Model;

public class Connection {

    UserClass toClass;
    ConnectionType type;

    public Connection(UserClass to, ConnectionType connectionType) {
        toClass = to;
        type = connectionType;
    }

    public ConnectionType getType() {
        return type;
    }

    public UserClass getToClass() {
        return toClass;
    }
}
