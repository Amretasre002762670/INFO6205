package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Assignment3_Benchmark {

    public static Integer[] arrayGenerator(String arrayType, int arrayLength) {
        Random rand = new Random();
        Integer[] result = new Integer[arrayLength];
        switch (arrayType) {
            case "random":
                for (int i = 0; i < arrayLength; i++) {
                    result[i] = rand.nextInt(arrayLength);
                }
                break;
            case "orderd":
                for (int i = 0; i < arrayLength; i++) {
                    result[i] = i;
                }
                break;
            case "partially-ordered":
                for (int j = 0; j < arrayLength / 2; j++) {
                    result[j] = j;
                }
                for (int i = arrayLength / 2; i < arrayLength; i++) {
                    result[i] = rand.nextInt(arrayLength);
                }
                break;
            case "reverese-ordered":
                int[] result_sample = IntStream.range(0, arrayLength).toArray();
                int j = arrayLength;
                for (int i = 0; i < arrayLength; i++) {
                    result[j-1] = result_sample[i];
                    j = j-1;
                }

                break;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("For random array");
        for (int i = 200; i <= 4000; i= i*2) {
            Integer[] array = arrayGenerator("random", i);
            InsertionSort<Integer> sorter = new InsertionSort<Integer>();
            Benchmark<Boolean> bm = new Benchmark_Timer<>("random array sort",  b -> sorter.sort(array));
            double x = bm.run(true, 1000);
            System.out.println(x + " ns for " + i + " array length");
        }

        System.out.println("\n");
        System.out.println("For partially sorted array");
        for (int i = 200; i <= 4000; i= i*2) {
            Integer[] array = arrayGenerator("partially-ordered", i);
            InsertionSort<Integer> sorter = new InsertionSort<Integer>();
            Benchmark<Boolean> bm = new Benchmark_Timer<>("random array sort",  b -> sorter.sort(array));
            double x = bm.run(true, 1000);
            System.out.println(x + " ns for " + i + " array length");
        }
        System.out.println("\n");
        System.out.println("For reversly sorted array");
        for (int i = 200; i <= 4000; i= i*2) {
            Integer[] array = arrayGenerator("reverese-ordered", i);
            InsertionSort<Integer> sorter = new InsertionSort<Integer>();
            Benchmark<Boolean> bm = new Benchmark_Timer<>("random array sort",  b -> sorter.sort(array));
            double x = bm.run(true, 1000);
            System.out.println(x + " ns for " + i + " array length");
        }
        System.out.println("\n");
        System.out.println("For sorted array");
        for (int i = 200; i<= 4000; i= i*2) {
            Integer[] array = arrayGenerator("orderd", i);
            InsertionSort<Integer> sorter = new InsertionSort<Integer>();
            Benchmark<Boolean> bm = new Benchmark_Timer<>("random array sort",  b -> sorter.sort(array));
            double x = bm.run(true, 1000);
            System.out.println(x + " ns for " + i + " array length");
        }
    }

}
