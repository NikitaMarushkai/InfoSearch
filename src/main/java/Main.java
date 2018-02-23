import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Parser parser = new ParserImpl();
        try {
            parser.parse(2, "https://www.drive.ru/", WhatToParse.ALL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}













































