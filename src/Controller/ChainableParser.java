package Controller;

import Model.UserClass;
import View.CodeViewPanel;

public abstract class ChainableParser implements Parser {
    Parser nextParser;
    public void setNextParser(Parser parser) {
        this.nextParser = parser;
    }
    public void parse(UserClass userClass, CodeViewPanel panel) {
        if (nextParser == null) {
            nextParser = new AssociationParser();
        }
        nextParser.parse(userClass, panel);
    }
}

