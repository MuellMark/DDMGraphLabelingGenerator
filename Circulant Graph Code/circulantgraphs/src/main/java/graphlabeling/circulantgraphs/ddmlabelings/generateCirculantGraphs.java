package graphlabeling.circulantgraphs.ddmlabelings;

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
        int numVertices =5;

        // Used to generate given circulant graph
        int a=1;
        int b=2;

        //Stores all ddm labelings of a given Circualnt graph
        ArrayList<edgeStorageArrays> allCombos = new ArrayList<>(); //For recursive
        ArrayList<edgeStorageArrays> ddmLabelings = new ArrayList<>(); //For ddmlabelings

        // Stores original starter Circulant graph
        edgeStorageArrays startCirGraph = new edgeStorageArrays(numVertices);
        
        // Used to create a ddm graph of any size
        //createCirculantGraphs(numVertices, a, b, startCirGraph);

        // Generates circulant graphs
        allCombos.add(startCirGraph);
        findDDMLabelings(ddmLabelings,allCombos,numVertices,a,b);

        //Different print statements, more avaiable, same as in generateGraphs
        generateGraphs.printAllAdjMatrix(ddmLabelings);
        generateGraphs.writeAllCombosToFileVisualization(ddmLabelings);

    }

    // Generates a graph of a given size with valid a and b parameters
    // Originally to be used for generation, now stand alone function
    public static void createCirculantGraphs(int numVertices,int a, int b, edgeStorageArrays startCirGraph){
        if(a>=b) System.out.println("Invalid Condidtions, a must be less than b");
        if(numVertices<5) System.out.println("Invalid Condidtions, numVertices must be equal to 5 or more");
        if(b>=numVertices+1/2) System.out.println("Invalid Condidtions, a+b<=numVertices/2");
        else{
            System.out.println("Conditions met, generating the graph");
            for(int i=0;i<numVertices;i++){
                //Makes cycle, added
                startCirGraph.addPair(i+1, ((i+a)%numVertices)+1);
                startCirGraph.addPair(((i+a)%numVertices)+1, i+1);
                // Makes cycle, subtracted, 
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
        for(int i=size;i>0;i--){
            
            int tempLeng = allCombos.size();
            //Should add all start graphs to allCombos, then a seperate for loop will continue from there
            for(List<Integer> set:sets){
                //set.add(0,6);
                System.out.println(set);
                System.out.println(allCombos.size());
                
                for(int j=0;j<tempLeng;j++){
                    findDDMRecur(ddmLabelings,allCombos,size,set,allCombos.get(j),a,b,i);
                }
                
            }
            sets.clear();
            ArrayList<Integer> temp = new ArrayList<>();
            for(int j=1;j<=size;j++){
                if(j!=i) temp.add(j);
            }
            sets.add(temp);

            if(i==size){
                allCombos.remove(0);
            }
        }
    }

    private static void findDDMRecur(ArrayList<edgeStorageArrays> ddmLabelings,
    ArrayList<edgeStorageArrays> allCombos, int size, List<Integer> set, 
    edgeStorageArrays graph, int a, int b, int curr){
        //Base case, if a graph is a circulant graph, may need to change
        int sumIns = graph.getSumIns(curr);
        int sumOuts = graph.getSumOuts(curr); // ALso temporary
        if(graph.isCirculantLabeling(a, b) && graph.isDDMLabeling()){
            // add to all combos
            boolean eq = false;
            for(int i=0;i<ddmLabelings.size();i++){
                if(ddmLabelings.get(i).equals(graph) || ddmLabelings.get(i).isInverse(graph)){
                    eq=true;
                    i+=ddmLabelings.size();
                }
            }
            if(!eq) ddmLabelings.add(graph);
        }else if(set.size()==0 && sumIns==sumOuts&& graph.getCountEdges(curr)==4){ //Temporary!!!!!
            boolean eq = false;
            for(int i=0;i<allCombos.size();i++){
                if(allCombos.get(i).equals(graph) || allCombos.get(i).isInverse(graph)){
                    eq=true;
                    i+=allCombos.size();
                }
            }
            if(!eq) allCombos.add(graph);
        }else if(graph.stillCirculant()){
            for(int i=0;i<set.size();i++){
                // Makes 3 new graphs, adding two different ways, and not adding at all

                edgeStorageArrays newGraph1 = graph.copy();
                newGraph1.addPair(set.get(i),curr);

                edgeStorageArrays newGraph2 = graph.copy();
                newGraph2.addPair(curr,set.get(i));

                edgeStorageArrays newGraph3 = graph.copy();

                List<Integer> set1 = copySet(set);
                List<Integer> set2 = copySet(set);

                set1.remove(i);
                set2.remove(i);

                findDDMRecur(ddmLabelings,allCombos,size,set1,newGraph1,a,b,curr);
                findDDMRecur(ddmLabelings,allCombos,size,set2,newGraph2,a,b,curr);
                findDDMRecur(ddmLabelings,allCombos,size,set2,newGraph3,a,b,curr);
            }
        }
    }

    //Returns the set of vertices it can use
    public static void getUsableSets(int size,List<List<Integer>> test){
        List<Integer> dom = new ArrayList<Integer>();
        for(int i=1;i<size;i++) dom.add(i);

        Generator.combination(dom)
            .simple(4)
            .stream()
            .forEach(combination -> test.add(combination));

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
    public static List<Integer> copySet(List<Integer> set){
        List<Integer> newSet = new ArrayList<>();

        for(int i=0;i<set.size();i++){
            newSet.add(set.get(i));
        }
        return newSet;
    }
}
