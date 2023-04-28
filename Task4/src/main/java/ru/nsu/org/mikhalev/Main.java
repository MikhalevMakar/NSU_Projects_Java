package ru.nsu.org.mikhalev;

import org.apache.commons.cli.ParseException;
import ru.nsu.org.mikhalev.factory.Factory;
import ru.nsu.org.mikhalev.proces_input.ParseFileJSON;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        ParseFileJSON parseFileJSON = new ParseFileJSON(args);
        Factory factory = new Factory(parseFileJSON);
        factory.start();
    }
}
