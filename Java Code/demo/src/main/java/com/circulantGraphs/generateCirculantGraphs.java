package com.circulantGraphs;
import org.paukov.combinatorics3.Generator;
import java.util.*;


public class generateCirculantGraphs {
    public static void main( String[] args ){
        edgeStorageArrays temp = new edgeStorageArrays(5);
        temp.addPair(2, 1);
        temp.print();
        System.out.println( "Hello World!" );
        List<Integer> dom = Arrays.asList(1,2,3,4,5);
        List<List<Integer>> test = new ArrayList<>();
        Generator.combination(dom)
            .simple(4)
            .stream()
            .forEach(combination -> test.add(combination));
        System.out.println(test);
    }
}