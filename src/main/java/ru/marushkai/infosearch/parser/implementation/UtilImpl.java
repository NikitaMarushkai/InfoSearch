package ru.marushkai.infosearch.parser.implementation;

import ru.marushkai.infosearch.parser.interfaces.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilImpl implements Util {
    @Override
    public Map<Integer, List<Integer>> convertToOptimal(int[][] adjacency) {
        Map<Integer, List<Integer>> optimalMatrix = new HashMap<>();
        for (int row = 0; row < adjacency.length; row++){
            List<Integer> cols = new ArrayList<>();
            for (int col = 0; col < adjacency[row].length; col++){
                if (adjacency[row][col] == 1){
                    cols.add(col);
                }
            }
            optimalMatrix.put(row, cols);
        }
        return optimalMatrix;
    }

    @Override
    public void writeOptimalToFile(Map<Integer, List<Integer>> optimalMatrix, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(optimalMatrix);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Integer, List<Integer>> readOptimalFromFile(String fileName) {
        Map<Integer, List<Integer>> mapFromFile = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            mapFromFile = (Map<Integer, List<Integer>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
        return mapFromFile;
    }
}
