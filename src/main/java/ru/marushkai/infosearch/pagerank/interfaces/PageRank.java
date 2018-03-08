package ru.marushkai.infosearch.pagerank.interfaces;

import java.io.FileNotFoundException;
import java.util.List;

public interface PageRank {
    List<List<? extends Number>> readMatrix(String path) throws FileNotFoundException;
    Double[] calculatePageRank(List<List<? extends Number>> matrix, int numberOfIterations);
    List<List<? extends Number>> prepareMatrix(List<List<? extends Number>> matrix);
}
