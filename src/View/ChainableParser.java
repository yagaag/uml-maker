package View;

import Model.UserClass;

public abstract class ChainableParser implements Parser {
    Parser nextParser;
    public void setNextParser(Parser parser) {
        this.nextParser = parser;
    }

    public Parser getNextParser() { return nextParser; }
    public abstract void parse(UserClass userClass, CodeViewPanel panel);
}

