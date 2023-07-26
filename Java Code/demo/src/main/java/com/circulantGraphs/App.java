package com.circulantGraphs;
import org.paukov.combinatorics3.Generator;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
        System.out.println( "Hello World!" );
        List<Integer> dom = Arrays.asList(1,2,3,4,5);
        Generator.combination(dom)
            .simple(3)
            .stream()
            .forEach(System.out::println);
    }
}
