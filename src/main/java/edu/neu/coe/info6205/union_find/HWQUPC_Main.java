package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class HWQUPC_Main {

    public static int pairs = 0;

    public static int count(int n) {
        int connections = 0;
        UF_HWQUPC uf = new UF_HWQUPC(n);
        Random rand = new Random();
        while (uf.components() > 1) {

            int i = rand.nextInt(n);
            int j = rand.nextInt(n);
            pairs++;

            if (!uf.connected(i, j)) {
                uf.union(i, j);
                connections++;
            }

        }
        return connections;
    }

    public static void main(String[] args) {
        int result = 0;
        int meanPairs = 0;
        int total = 0;
        int n = Integer.parseInt(args[0]);
        int exp = 30;

        for (int i = 0; i < exp; i++) {
            result = count(n);
            System.out.println("Edges for connection : "+ n +" result is : "+result);
            System.out.println("Pairs for connection : "+ n +" pairs is : "+pairs);
            System.out.println("\n");

            total =+ pairs;
        }
        meanPairs = total / exp;
        System.out.println(meanPairs + " mean of pairs generated for 200");
    }
}
