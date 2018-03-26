package ru.marushkai.infosearch.parser.interfaces;

import java.util.List;
import java.util.Map;

public interface Util {
    Map<Integer, List<Integer>> convertToOptimal(int[][] adjacency);
    void writeOptimalToFile(Map<Integer, List<Integer>> optimalMatrix, String fileName);
    Map<Integer, List<Integer>> readOptimalFromFile(String fileName);
}
