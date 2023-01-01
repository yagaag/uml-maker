package Model;

public enum ConnectionType {
    Association("Association"), Inheritance("Inheritance"), Composition("Composition");
    public String name;
    ConnectionType(String s) {
        this.name = s;
    }
}