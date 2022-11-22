package Model;

public enum ConnectionType {
    ASSOCIATION("Association"), INHERITANCE("Inheritance"), COMPOSITION("Composition");
    public String name;
    ConnectionType(String s) {
        this.name = s;
    }
}
