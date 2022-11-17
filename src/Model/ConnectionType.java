package Model;

public enum ConnectionType {
    ASSOCIATION("Association"), INHERITANCE("Inheritance"), DEPENDENCY("Dependency");
    public String name;
    ConnectionType(String s) {
        this.name = s;
    }
}
