public class Main {

    public static void main(String[] args) {
        Parser parser = new ParserImpl();
        try {
            parser.parse(100, "https://www.drive.ru/", WhatToParse.INNER,
                    "C:\\Programming\\infosearch\\matrix_test.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}













































