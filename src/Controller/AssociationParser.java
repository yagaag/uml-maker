package Controller;

import Model.Connection;
import Model.ConnectionType;
import Model.UserClass;
import Model.ViewConstants;
import View.CodeViewPanel;

import java.awt.*;
import java.util.ArrayList;

public class AssociationParser implements Parser {

    @Override
    public void parse(UserClass userClass, CodeViewPanel panel) {

        boolean method_added = false;
        ArrayList<Connection> connections = userClass.getConnections();
        for (int i=0; i<connections.size(); i++) {
            if (connections.get(i).getType() == ConnectionType.ASSOCIATION) {
                if (method_added) {
                    panel.appendToPanel("    " + connections.get(i).getToClass().getTitle() + "\n", ViewConstants.baseSyntaxColor);
                }
                else {
                    method_added = true;
                    panel.appendToPanel("  method() {\n", Color.red);
                    panel.appendToPanel("    " + connections.get(i).getToClass().getTitle() + "\n", ViewConstants.baseSyntaxColor);
                }
            }
        }
        if (method_added) {
            panel.appendToPanel("  }\n", ViewConstants.methodSyntaxColor);
        }
        panel.appendToPanel("}", ViewConstants.baseSyntaxColor);
    }
}
