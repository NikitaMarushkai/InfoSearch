package ru.marushkai.infosearch.pagerank.implementation;

import ru.marushkai.infosearch.pagerank.interfaces.PageRank;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PageRankImpl implements PageRank {
    @Override
    public List<List<? extends Number>> readMatrix(String path) throws FileNotFoundException {
        File file = new File(path);
        List<List<? extends Number>> readMatrix = new ArrayList<>();

        try (Scanner inputStream = new Scanner(file)) {
            while (inputStream.hasNext()) {
                String line = inputStream.next();
                String[] values = line.split(",");
                readMatrix.add(Arrays.stream(values)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()));
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        return readMatrix;
    }

    @Override
    public Double[] calculatePageRank(Map<Integer, Map<Integer, Double>> matrix, int numberOfIterations) {
        Double[] initialVector = new Double[matrix.size()];
        Arrays.fill(initialVector, 1.);
        for (int i = 0; i < numberOfIterations; i++) {
            initialVector = pageRankIterate(matrix, initialVector);
        }
        return initialVector;
    }


    @Override
    public Map<Integer, Map<Integer, Double>> prepareMatrix(Map<Integer, List<Integer>> matrix) {
        Map<Integer, Map<Integer, Double>> newMatrix = new HashMap<>();
        matrix.forEach((key, value) -> {
            Map<Integer, Double> newMapInsteadValues = new HashMap<>();
            if (!value.isEmpty()) {
                value.forEach(e ->
                        newMapInsteadValues.put(e, 1. / (double) value.size())
                );
            } else {
                for (int i = 0; i < matrix.size(); i++) {
                    newMapInsteadValues.put(i, 1. / matrix.size());
                }
            }
            newMatrix.put(key, newMapInsteadValues);
        });
        return newMatrix;
    }

    private Double[] pageRankIterate(Map<Integer, Map<Integer, Double>> matrix, Double[] vector) {
        Double[] newVector = IntStream.range(0, matrix.size())
                .mapToDouble(row -> IntStream.range(0, matrix.size())
                        .mapToDouble(col -> (matrix.get(col).get(row) == null ? 0 : matrix.get(col).get(row)) * vector[col])
                        .sum()).boxed().toArray(Double[]::new);


        newVector = Arrays.stream(newVector)
                .map(v -> (1. - 0.85) + v * 0.85)
                .toArray(Double[]::new);

        return newVector;
    }
}
