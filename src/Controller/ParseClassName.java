package Controller;

import Model.UserClass;

public class ParseClassName implements ParseUserClass {
    @Override
    public String parse(UserClass userClass) {
        return "class " + userClass.getTitle() + " {\n}";
    }
}
