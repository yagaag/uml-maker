package Controller;

import Model.UserClass;

public abstract class ChainableParser implements Parser {
    Parser parser;
    public void setNextParser(Parser parser) {
        this.parser = parser;
    }

    public String parse(UserClass userClass) {
        if (parser == null) {
            parser = new ClassNameParser();
        }
        return parser.parse(userClass);
    }
}
