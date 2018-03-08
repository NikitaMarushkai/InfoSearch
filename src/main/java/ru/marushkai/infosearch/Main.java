package ru.marushkai.infosearch;

import ru.marushkai.infosearch.pagerank.implementation.PageRankImpl;
import ru.marushkai.infosearch.pagerank.interfaces.PageRank;
import ru.marushkai.infosearch.parser.interfaces.Parser;
import ru.marushkai.infosearch.parser.implementation.ParserImpl;
import ru.marushkai.infosearch.parser.enums.WhatToParse;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Parser parser = new ParserImpl();
        PageRank pageRank = new PageRankImpl();
        String fileName = "C:\\Programming\\infosearch\\matrix_marushkai.csv";
        try {
            List<List<? extends Number>> readyMatrix = pageRank.prepareMatrix(pageRank.readMatrix(fileName));
            for (Double m : pageRank.calculatePageRank(readyMatrix, 25)){
                System.out.print(m + ", ");
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            try {
                parser.parse(200, "http://parts-on-line.be/", WhatToParse.ALL,
                        fileName);
                List<List<? extends Number>> readyMatrix = pageRank.prepareMatrix(pageRank.readMatrix(fileName));

                for (Double m : pageRank.calculatePageRank(readyMatrix, 10)){
                    System.out.print(m + ", ");
                }
                System.out.println();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}













































