package Model;

public enum ConnectionType {
    ASSOCIATION("Association"), INHERITANCE("Inheritance"), AGGREGATION("Aggregation");
    public String name;
    ConnectionType(String s) {
        this.name = s;
    }
}
