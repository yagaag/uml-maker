package Model;

import java.util.Observable;

public class GlobalStatus extends Observable {
    static GlobalStatus instance;
    ConnectionType connectionType = ConnectionType.ASSOCIATION;
    String drawStatus = "No class selected";
    private GlobalStatus() {}
    public static GlobalStatus getInstance() {
        if (instance == null) {
            instance = new GlobalStatus();
        }
        return instance;
    }
    public void setConnectionType(ConnectionType type) {
        connectionType = type;
        this.setChanged();
        this.notifyObservers();
    }
    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setDrawStatus(String status) {
        drawStatus = status;
        this.setChanged();
        this.notifyObservers();
    }
    public String getDrawStatus() {
        return drawStatus;
    }
}
