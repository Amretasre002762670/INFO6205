package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Stopwatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Implementation of ThreeSum which follows the brute-force approach of
 * testing every candidate in the solution-space.
 * The array provided in the constructor may be randomly ordered.
 * <p>
 * This algorithm runs in O(N^3) time.
 */
class ThreeSumCubic implements ThreeSum {
    /**
     * Construct a ThreeSumCubic on a.
     * @param a an array.
     */
    public ThreeSumCubic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++)
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    if (a[i] + a[j] + a[k] == 0)
                        triples.add(new Triple(a[i], a[j], a[k]));
                }
            }
        Collections.sort(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    public static void main(String[] args) {
        Supplier<int[]> intsSupplier = new Source(500, 1000).intsSupplier(10);
        int[] ints = intsSupplier.get();
//        System.out.println("ints: " + Arrays.toString(ints));
        System.out.println("ints length: " + ints.length);

        // For Cubic
        ThreeSum target2 = new ThreeSumCubic(ints);
        Stopwatch start2 = new Stopwatch();
        Triple[] triplesCubic = target2.getTriples();
        long lap2 = start2.lap();
        System.out.println(lap2 + " lap2 in ms");
        start2.close();
        System.out.println(triplesCubic.length);
    }


    private final int[] a;
    private final int length;
}
