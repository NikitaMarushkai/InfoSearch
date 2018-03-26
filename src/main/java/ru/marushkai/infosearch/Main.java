package ru.marushkai.infosearch;

import ru.marushkai.infosearch.pagerank.implementation.PageRankImpl;
import ru.marushkai.infosearch.pagerank.interfaces.PageRank;
import ru.marushkai.infosearch.parser.implementation.UtilImpl;
import ru.marushkai.infosearch.parser.interfaces.Parser;
import ru.marushkai.infosearch.parser.implementation.ParserImpl;
import ru.marushkai.infosearch.parser.enums.WhatToParse;
import ru.marushkai.infosearch.parser.interfaces.Util;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Parser parser = new ParserImpl();
        PageRank pageRank = new PageRankImpl();
        String fileName = "C:\\Programming\\infosearch\\matrix_marushkai.csv";
        Util matrixUtils = new UtilImpl();
//        System.out.println(matrixUtils.readOptimalFromFile(fileName).toString());
        try {
            List<List<? extends Number>> readyMatrix = pageRank.prepareMatrix(pageRank.readMatrix(fileName));
            Arrays.stream(pageRank.calculatePageRank(readyMatrix, 50))
                    .forEach(v -> System.out.print(v + ", "));
            System.out.println();
        } catch (FileNotFoundException e) {
            try {
                parser.parse(200, "http://parts-on-line.be/", WhatToParse.ALL,
                        fileName);
                List<List<? extends Number>> readyMatrix = pageRank.prepareMatrix(pageRank.readMatrix(fileName));

                Arrays.stream(pageRank.calculatePageRank(readyMatrix, 50))
                        .forEach(v -> System.out.print(v + ", "));
                System.out.println();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}













































