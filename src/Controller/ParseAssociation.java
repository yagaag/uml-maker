package Controller;

import Model.Connection;
import Model.ConnectionType;
import Model.UserClass;

import java.util.ArrayList;

public class ParseAssociation implements ParseUserClass {

    ParseUserClass parser;

    public void setNextParser(ParseUserClass parser) {
        this.parser = parser;
    }

    @Override
    public String parse(UserClass userClass) {
        ArrayList<Connection> connections = userClass.getConnections();
        String s = parser.parse(userClass);
        for (int i=0; i<connections.size(); i++) {
            if (connections.get(i).getType() == ConnectionType.ASSOCIATION) {
                if (s.contains("() {\n")) {
                    int idx = s.indexOf("() {\n");
                    idx += 5;
                    s = s.substring(0,idx)
                            + "    "
                            + connections.get(i).getToClass().getTitle()
                            + "\n"
                            + s.substring(idx);
                } else {
                    int idx = s.indexOf("}");
//                    idx -= 1;
                    s = s.substring(0,idx)
                            + "  method() {\n"
                            + "    "
                            + connections.get(i).getToClass().getTitle()
                            + "\n  }\n"
                            + s.substring(idx);
                }
            }
        }
        return s;
    }
}
