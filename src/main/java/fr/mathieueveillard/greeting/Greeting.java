package fr.mathieueveillard.greeting;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Greeting {

    public static String ESCAPE_CHAR = "\"";

    public static String greet(String[] names) {
        final String[] defaultedNames = Arrays.stream(names).map(Greeting::defaultName).toArray(s -> new String[s]);
        final String[] splitNames = Arrays.stream(defaultedNames).map(Greeting::splitName).flatMap(ss -> Arrays.stream(ss)).toArray(s -> new String[s]);
        final String[] normalNames = Arrays.stream(splitNames).filter(Greeting::isNotAllUppercase).toArray(s -> new String[s]);
        final String[] shoutNames = Arrays.stream(splitNames).filter(Greeting::isAllUppercase).toArray(s -> new String[s]);
        return greetEverybody(normalNames, shoutNames);
    }

    private static String greetEverybody(String[] normalNames, String[] shoutNames) {
        String result = "";
        if (normalNames.length > 0) {
            result += greetManyPersonsNormally(normalNames);
        }
        if (result.length() > 0 && shoutNames.length > 0) {
            result += " AND ";
        }
        if (shoutNames.length > 0) {
            result += shoutOnePersonsName(shoutNames[0]);
        }
        return result;
    }

    private static String[] splitName(String name) {
        if (name.startsWith(ESCAPE_CHAR)) {
            final String nameWithoutEscapeChars = name.replace(ESCAPE_CHAR, "");
            return new String[]{nameWithoutEscapeChars};
        }
        return name.split(", ");
    }

    private static String defaultName(String name) {
        if (name == null) {
            return "my friend";
        }
        return name;
    }

    private static boolean isAllUppercase(String s) {
        return s.toUpperCase().equals(s);
    }

    private static boolean isNotAllUppercase(String s) {
        return !isAllUppercase(s);
    }

    private static String greetManyPersonsNormally(String[] names) {
        final int length = names.length;
        if (length == 1) {
            return greetOnePersonNormally(names[0]);
        }
        if (length == 2) {
            return greetTwoPersonsNormally(names);
        }
        return greetMoreThanTwoPersonsNormally(names);
    }

    private static String greetOnePersonNormally(String name) {
        return "Hello, " + name + ".";
    }

    private static String greetTwoPersonsNormally(String[] names) {
        return "Hello, " + names[0] + " and " + names[1] + ".";
    }

    private static String greetMoreThanTwoPersonsNormally(String[] names) {
        final String allNamesExceptTheLastOne = Arrays.stream(names).limit(names.length - 1).collect(Collectors.joining(", "));
        final String lastName = names[names.length - 1];
        return "Hello, " + allNamesExceptTheLastOne + ", and " + lastName + ".";
    }

    private static String shoutOnePersonsName(String name) {
        return "HELLO " + name + "!";
    }
}
