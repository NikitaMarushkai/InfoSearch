package ru.marushkai.infosearch;

import ru.marushkai.infosearch.pagerank.implementation.PageRankImpl;
import ru.marushkai.infosearch.pagerank.interfaces.PageRank;
import ru.marushkai.infosearch.parser.implementation.UtilImpl;
import ru.marushkai.infosearch.parser.interfaces.Parser;
import ru.marushkai.infosearch.parser.implementation.ParserImpl;
import ru.marushkai.infosearch.parser.enums.WhatToParse;
import ru.marushkai.infosearch.parser.interfaces.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Parser parser = new ParserImpl();
        PageRank pageRank = new PageRankImpl();
        String fileName = "C:\\Programming\\infosearch\\matrix_marushkai.optimal";
        Util matrixUtils = new UtilImpl();
        try {
            Map<Integer, Map<Integer, Double>> readyMatrix = pageRank.prepareMatrix(matrixUtils.readOptimalFromFile(fileName));
            Arrays.stream(pageRank.calculatePageRank(readyMatrix, 500))
                    .forEach(v -> System.out.print(v + ", "));
            System.out.println();
        } catch (IOException | ClassNotFoundException e) {
            try {
                parser.parse(200, "http://parts-on-line.be/", WhatToParse.ALL,
                        fileName);
                Map<Integer, Map<Integer, Double>> readyMatrix = pageRank.prepareMatrix(matrixUtils.readOptimalFromFile(fileName));

                Arrays.stream(pageRank.calculatePageRank(readyMatrix, 50))
                        .forEach(v -> System.out.print(v + ", "));
                System.out.println();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}













































