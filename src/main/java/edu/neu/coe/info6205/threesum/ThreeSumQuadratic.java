package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Stopwatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * NOTE: The array provided in the constructor MUST be ordered.
 */
public class ThreeSumQuadratic implements ThreeSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     * @param a a sorted array.
     */
    public ThreeSumQuadratic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++) triples.addAll(getTriples(i));
        Collections.sort(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a list of Triples such that the middle index is the given value j.
     *
     * @param i the index of the middle value.
     * @return a Triple such that
     */
    public List<Triple> getTriples(int i) {
        List<Triple> triples = new ArrayList<>();
        // FIXME : for each candidate, test if a[i] + a[j] + a[k] = 0.
        int j = i+1;
        int k = length-1;


        if (length > 3) {
            while(j<k) {
                if (a[i] + a[j] + a[k] == 0) {
                    Triple newTriple = new Triple(a[i], a[j], a[k]);
                    triples.add(newTriple);
                    ++j;
                    while (j > i+1 && a[j] == a[j-1]) {
                        ++j;
                        continue;
                    }
                } else if (a[i] + a[j] + a[k] < 0){
                    ++j;
                } else if (a[i] + a[j] + a[k] > 0){
                    --k;
                    if (j == k && i >= 0) {
                        --i;
                        if (i < 0) {
                            break;
                        }
                        j = i+1;
                        k = length - 1;

                    } else if (i < 0) {
                        break;
                    }

                }
            }
        } else if (length == 3) {
            if (i%2 == 1) {
                j = i-1;
                k = length-1;

                if (a[i] + a[j] + a[k] == 0) {
                    Triple tripleArray = new Triple(a[i], a[j], a[k]);
                    triples.add(tripleArray);
                }
            }
        }
        // FIXME : for each candidate, test if a[i] + a[j] + a[k] = 0.
        return triples;
    }

    public static void main(String[] args) {
        Supplier<int[]> intsSupplier = new Source(1300, 1000).intsSupplier(10);
        int[] ints = intsSupplier.get();
//        System.out.println("ints: " + Arrays.toString(ints));
        System.out.println("ints length: " + ints.length);

        // For Quadratic
        ThreeSum target = new ThreeSumQuadratic(ints);
        Stopwatch start = new Stopwatch();
        Triple[] triplesQuadratic = target.getTriples();
        long lap = start.lap();
        System.out.println(lap + " lap in ms");
        start.close();
        System.out.println(triplesQuadratic.length+ " Length of Triple Quardratic");
        System.out.println("\n");
        // For Cubic
        ThreeSum target2 = new ThreeSumCubic(ints);
        Stopwatch start2 = new Stopwatch();
        Triple[] triplesCubic = target2.getTriples();
        long lap2 = start2.lap();
        System.out.println(lap2 + " lap2 in ms");
        start2.close();
        System.out.println(triplesCubic.length+" Length of Triple Cubic");
    }

    private final int[] a;
    private final int length;
}
