package att06;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 5000, 10000};

        System.out.println("--- Análise de Desempenho de Algoritmos de Ordenação em Java ---");

        // Cabeçalho da tabela de resultados
        System.out.println("\n| Tamanho  | Tipo de Lista  | Bubble Sort | Selection Sort | Insertion Sort | Merge Sort | Quick Sort |");
        System.out.println("|----------|----------------|-------------|----------------|----------------|------------|------------|");

        for (int size : sizes) {
            // Arrays de teste
            int[] randomArr = DataGenerator.generateRandomArray(size);
            int[] sortedArr = DataGenerator.generateSortedArray(size);
            int[] reverseArr = DataGenerator.generateReverseSortedArray(size);

            // Rodando e exibindo os resultados em uma linha da tabela
            runTests(size, "Aleatória", randomArr);
            runTests(size, "Ordenada", sortedArr);
            runTests(size, "Invertida", reverseArr);
        }
        System.out.println("\nAnálise de desempenho concluída!");
    }

    public static void runTests(int size, String listType, int[] originalArray) {
        long[] times = new long[5]; // Array para armazenar os tempos em nanossegundos

        // Bubble Sort
        int[] arrBubble = Arrays.copyOf(originalArray, originalArray.length);
        long startTime = System.nanoTime();
        SortingAlgorithms.bubbleSort(arrBubble);
        long endTime = System.nanoTime();
        times[0] = endTime - startTime;

        // Selection Sort
        int[] arrSelection = Arrays.copyOf(originalArray, originalArray.length);
        startTime = System.nanoTime();
        SortingAlgorithms.selectionSort(arrSelection);
        endTime = System.nanoTime();
        times[1] = endTime - startTime;

        // Insertion Sort
        int[] arrInsertion = Arrays.copyOf(originalArray, originalArray.length);
        startTime = System.nanoTime();
        SortingAlgorithms.insertionSort(arrInsertion);
        endTime = System.nanoTime();
        times[2] = endTime - startTime;

        // Merge Sort
        int[] arrMerge = Arrays.copyOf(originalArray, originalArray.length);
        startTime = System.nanoTime();
        SortingAlgorithms.mergeSort(arrMerge);
        endTime = System.nanoTime();
        times[3] = endTime - startTime;

        // Quick Sort
        int[] arrQuick = Arrays.copyOf(originalArray, originalArray.length);
        startTime = System.nanoTime();
        SortingAlgorithms.quickSort(arrQuick, 0, arrQuick.length - 1);
        endTime = System.nanoTime();
        times[4] = endTime - startTime;

        // Imprimindo a linha da tabela
        System.out.printf("| %-8d | %-14s | %-11.4f | %-14.4f | %-14.4f | %-10.4f | %-10.4f |\n",
                size, listType,
                times[0] / 1e6,
                times[1] / 1e6,
                times[2] / 1e6,
                times[3] / 1e6,
                times[4] / 1e6);
    }
}