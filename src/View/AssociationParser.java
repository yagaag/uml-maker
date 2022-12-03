package View;

import Model.Connection;
import Model.ConnectionType;
import Model.UserClass;

import java.awt.*;
import java.util.ArrayList;

public class AssociationParser implements Parser {

    @Override
    public void parse(UserClass userClass, CodeViewPanel panel) {
        boolean isMethodAdded = false;
        ArrayList<Connection> connections = userClass.getConnections();
        for (int i=0; i<connections.size(); i++) {
            if (connections.get(i).getType() == ConnectionType.Association) {
                if (isMethodAdded) {
                    panel.appendToPanel("    " + connections.get(i).getToClass().getTitle() + "\n", ViewConstants.baseSyntaxColor);
                }
                else {
                    isMethodAdded = true;
                    panel.appendToPanel("  method() {\n", Color.red);
                    panel.appendToPanel("    " + connections.get(i).getToClass().getTitle() + "\n", ViewConstants.baseSyntaxColor);
                }
            }
        }
        if (isMethodAdded) {
            panel.appendToPanel("  }\n", ViewConstants.methodSyntaxColor);
        }
        panel.appendToPanel("}", ViewConstants.baseSyntaxColor);
    }
}
