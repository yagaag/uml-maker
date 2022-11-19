package Controller;

import Model.Connection;
import Model.ConnectionType;
import Model.UserClass;

import java.util.ArrayList;

public class InheritanceParser extends ChainableParser {

    @Override
    public String parse(UserClass userClass) {

        String s = super.parse(userClass);

        ArrayList<Connection> connections = userClass.getConnections();
        for (int i=0; i<connections.size(); i++) {
            if (connections.get(i).getType() == ConnectionType.INHERITANCE) {
                if (s.contains("extends")) {
                    int idx = s.indexOf("extends");
                    idx += 7;
                    s = s.substring(0,idx)
                            + " "
                            + connections.get(i).getToClass().getTitle()
                            + ","
                            + s.substring(idx);
                } else {
                    int idx = s.indexOf(userClass.getTitle());
                    idx += userClass.getTitle().length();
                    s = s.substring(0,idx)
                            + " extends "
                            + connections.get(i).getToClass().getTitle()
                            + s.substring(idx);
                }
            }
        }
        return s;
    }
}
