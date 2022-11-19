package Controller;

import Model.UserClass;

public class ClassNameParser implements Parser {
    @Override
    public String parse(UserClass userClass) {
        return "class " + userClass.getTitle() + " {\n}";
    }
}
