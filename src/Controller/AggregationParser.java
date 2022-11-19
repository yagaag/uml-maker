package Controller;

import Model.Connection;
import Model.ConnectionType;
import Model.UserClass;

import java.util.ArrayList;

public class AggregationParser extends ChainableParser {

    @Override
    public String parse(UserClass userClass) {

        String s = super.parse(userClass);

        ArrayList<Connection> connections = userClass.getConnections();
        for (int i=0; i<connections.size(); i++) {
            if (connections.get(i).getType() == ConnectionType.AGGREGATION) {
                int idx = s.indexOf("{\n");
                idx += 2;
                s = s.substring(0,idx)
                        + "  "
                        + connections.get(i).getToClass().getTitle()
                        + "\n"
                        + s.substring(idx);
            }
        }
        return s;
    }
}
