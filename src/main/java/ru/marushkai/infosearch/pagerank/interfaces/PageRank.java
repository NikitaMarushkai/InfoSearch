package ru.marushkai.infosearch.pagerank.interfaces;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface PageRank {

    List<List<? extends Number>> readMatrix(String path) throws FileNotFoundException;

    Double[] calculatePageRank(Map<Integer, Map<Integer, Double>> matrix, int numberOfIterations);

    Map<Integer, Map<Integer, Double>> prepareMatrix(Map<Integer, List<Integer>> matrix);
}
