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
    public Double[] calculatePageRank(List<List<? extends Number>> matrix, int numberOfIterations) {
        Double[] initialVector = new Double[matrix.size()];
        Arrays.fill(initialVector, 1.);
        for (int i = 0; i < numberOfIterations; i++) {
            initialVector = pageRankIterate(matrix, initialVector);
        }
        return initialVector;
    }

    @Override
    public List<List<? extends Number>> prepareMatrix(List<List<? extends Number>> matrix) {
        // Transpose matrix
        List<List<? extends Number>> transposedMatrix = new ArrayList<>();

        for (int i = 0; i < matrix.get(0).size(); i++) {
            List<Double> newLine = new ArrayList<>();
            for (List<? extends Number> row : matrix) {
                newLine.add(row.get(i).doubleValue());
            }
            if (Collections.frequency(newLine, 0.) == newLine.size()){
                newLine.replaceAll(e -> 1. / newLine.size());
            } else {
                int numberOfOnes = Collections.frequency(newLine, 1.);
                newLine.replaceAll(e -> e / numberOfOnes);
            }
            transposedMatrix.add(newLine);
        }
        return transposedMatrix;
    }

    private Double[] pageRankIterate(List<List<? extends Number>> matrix, Double[] vector) {

        Double[] newVector = IntStream.range(0, matrix.size())
                .mapToDouble(row -> IntStream.range(0, matrix.get(0).size())
                        .mapToDouble(col -> matrix.get(col).get(row).doubleValue() * vector[col])
                        .sum()).boxed().toArray(Double[]::new);


        newVector = Arrays.stream(newVector)
                .map(v -> (1. - 0.85) + v * 0.85)
                .toArray(Double[]::new);

        return newVector;
    }
}
