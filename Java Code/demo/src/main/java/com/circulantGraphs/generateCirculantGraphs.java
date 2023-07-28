package com.circulantGraphs;

import java.util.ArrayList;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;
import org.paukov.combinatorics3.Generator;
//import generateGraphs;

class generateCirculantGraphs{
    public static void main(String[]args){
        // Specifies size of graph
        int numVertices =6;

        // Used to generate given circulant graph
        int a=1;
        int b=3;

        //Stores all ddm labelings of a given Circualnt graph
        ArrayList<edgeStorageArrays> allCombos = new ArrayList<>(); //For recursive
        ArrayList<edgeStorageArrays> ddmLabelings = new ArrayList<>(); //For ddmlabelings

        // Stores original starter Circulant graph
        edgeStorageArrays startCirGraph = new edgeStorageArrays(numVertices);
        //createCirculantGraphs(numVertices, a, b, startCirGraph);
       
        //startCirGraph.print();
        //startCirGraph.printAdjMatrix();

        allCombos.add(startCirGraph);
        printGraphForVis( allCombos,a,b);
        System.out.println(Arrays.toString(startCirGraph.getCirculentCycle(a, b)));

        findDDMLabelings(ddmLabelings,allCombos,numVertices,a,b);
        // Can use emthods from generateGraphs to avoid rewriting
        // generateGraphs.printAllCombos(allCombos);

        // ArrayList<int[]> arrTest = startCirGraph.getCycles();
        // for(int[] arr:arrTest){
        //     System.out.println(Arrays.toString(arr));
        // }
    }

    // Generates the starting circulant graph based on the number of vertices, a and b
    public static void createCirculantGraphs(int numVertices,int a, int b, edgeStorageArrays startCirGraph){
        if(a>=b) System.out.println("Invalid Condidtions, a must be less than b");
        if(numVertices<5) System.out.println("Invalid Condidtions, numVertices must be equal to 5 or more");
        if(b>=numVertices+1/2) System.out.println("Invalid Condidtions, a+b<=numVertices/2");
        else{
            System.out.println("Conditions met, generating the graph");
            for(int i=0;i<numVertices;i++){
                //Makes cycle, added
                //System.out.println(i);
                startCirGraph.addPair(i+1, ((i+a)%numVertices)+1);
                startCirGraph.addPair(((i+a)%numVertices)+1, i+1);
                // Makes cycle, subtracted, 
                //System.out.println(((i+numVertices-a)%numVertices)+1);
                startCirGraph.addPair(i+1, ((i+numVertices-a)%numVertices)+1);
                startCirGraph.addPair(((i+numVertices-a)%numVertices)+1, i+1);

                // Other edges, by adding
                startCirGraph.addPair(i+1, ((i+b)%numVertices)+1);
                startCirGraph.addPair(((i+b)%numVertices)+1, i+1);
                // Other edges, by subtracting
                startCirGraph.addPair(i+1, ((i+numVertices-b)%numVertices)+1);
                startCirGraph.addPair(((i+numVertices-b)%numVertices)+1, i+1);

            }
        }
    }

    // Kick off method for recursive method
    public static void findDDMLabelings(ArrayList<edgeStorageArrays> ddmLabelings,
    ArrayList<edgeStorageArrays> allCombos, int size, int a, int b){
        List<List<Integer>> sets = new ArrayList<>();
        getUsableSets(size,sets);

        //Should add all start graphs to allCombos, then a seperate for loop will continue from there
        for(List<Integer> set:sets){
            System.out.println(set);
            edgeStorageArrays startCirGraph = new edgeStorageArrays(size);
            findDDMRecur(ddmLabelings,allCombos,size,set,startCirGraph,a,b);
        }
        //then start checking recursively
    }

    private static void findDDMRecur(ArrayList<edgeStorageArrays> ddmLabelings,
    ArrayList<edgeStorageArrays> allCombos, int size, List<Integer> set, 
    edgeStorageArrays graph, int a, int b){
        //Base case, if a graph is a circulant graph, may need to change
        if(graph.isCirculantLabeling(a, b)){
            // add to all combos
        }else if(graph.stillCirculant()){
            System.out.println("test");
            for(int i=0;i<set.size();i++){
                for(int j=i+1;j<set.size();i++){
                    edgeStorageArrays newGraph1 = graph.copy();
                    newGraph1.addPair(set.get(i),set.get(j));

                    edgeStorageArrays newGraph2 = graph.copy();
                    newGraph2.addPair(set.get(j),set.get(i));

                    // No case where edge isn't added, since all edges must be used in these cases

                    //need to make new copy method for sets
                    findDDMRecur(ddmLabelings,allCombos,size,set,newGraph1,a,b);
                    findDDMRecur(ddmLabelings,allCombos,size,set,newGraph2,a,b);
                }
            }
        }
        //recur only if still fits as a circulant graph

    }

    //Returns the set of vertices it can use
    public static void getUsableSets(int size,List<List<Integer>> test){
        List<Integer> dom = new ArrayList<Integer>();
        for(int i=1;i<size;i++) dom.add(i);

        Generator.combination(dom)
            .simple(4)
            .stream()
            .forEach(combination -> test.add(combination));
        //System.out.println(test);

    }

    // similar to how it was done in generateGraphs, but need extra line for the cycle
    public static void printGraphForVis(ArrayList<edgeStorageArrays> AllCombos, int a, int b){
        try {
            // Opens files, change file name here
            FileWriter myWriter = new FileWriter("/Users/markymarkscomputer/Desktop/Untitled/Python Code/outputForCirVis.txt");
            for(int i=0;i<AllCombos.size();i++){
                myWriter.write(Arrays.toString(AllCombos.get(i).getCirculentCycle(a,b))+"\n");
                AllCombos.get(i).writeToFileForVisualization(myWriter);
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Used to make copies of arraylists, used for copies of sets
    public static ArrayList<Integer> copySet(ArrayList<Integer> set){
        ArrayList<Integer> newSet = new ArrayList<>();

        for(int i=0;i<set.size();i++){
            newSet.add(set.get(i));
        }
        return newSet;
    }


}






 
