public class Main {

    public static void main(String[] args) {
        Parser parser = new ParserImpl();
        try {
            parser.parse(200, "http://parts-on-line.be/", WhatToParse.ALL,
                    "C:\\Programming\\infosearch\\matrix_marushkai.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}













































