package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.InstrumentedHelper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.sort.linearithmic.MergeSort;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;

public class HeapSort<X extends Comparable<X>> extends SortWithHelper<X> {

    public HeapSort(Helper<X> helper) {
        super(helper);
    }

    @Override
    public void sort(X[] array, int from, int to) {
        if (array == null || array.length <= 1) return;

        // XXX construction phase
        buildMaxHeap(array);

        // XXX sort-down phase
        Helper<X> helper = getHelper();
        for (int i = array.length - 1; i >= 1; i--) {
            helper.swap(array, 0, i);
            maxHeap(array, i, 0);
        }
    }

    private void buildMaxHeap(X[] array) {
        int half = array.length / 2;
        for (int i = half; i >= 0; i--) maxHeap(array, array.length, i);
    }

    private void maxHeap(X[] array, int heapSize, int index) {
        Helper<X> helper = getHelper();
        final int left = index * 2 + 1;
        final int right = index * 2 + 2;
        int largest = index;
        if (left < heapSize && helper.compare(array, largest, left) < 0) largest = left;
        if (right < heapSize && helper.compare(array, largest, right) < 0) largest = right;
        if (index != largest) {
            helper.swap(array, index, largest);
            maxHeap(array, heapSize, largest);
        }
    }

    public static void main(String[] args) {
        int N = 320000;
        InstrumentedHelper<Integer> helper = new InstrumentedHelper<>("HeapSort", Config.setupConfig("true", "0", "1", "", ""));
        HeapSort<Integer> s = new HeapSort<>(helper);
        s.init(N);
        Integer[] xs = helper.random(Integer.class, r -> r.nextInt(320000));
        Benchmark<Boolean> bm = new Benchmark_Timer<>("random array sort", b -> s.sort(xs, 0, N));
        double x = bm.run(true, 20);
        s.sort(xs, 0, N);
//        System.out.println("-----");
//        for(int i = 0; i<xs.length; i++) {
//            System.out.println(xs[i]);
//        }
        long compares = helper.getCompares();
        int swaps = helper.getSwaps();
        int hits = helper.getHits();
        System.out.println(compares + " compares");
        System.out.println(swaps + " swap");
        System.out.println(hits + " hits");
        System.out.println(x + " ns");
//        System.out.println(compares/20 + " compares/20");
//        System.out.println(swaps/20 + " swap/20");
//        System.out.println(hits/20 + " hits/20");
//        System.out.println(x/20 + " ns/20");
    }

}
