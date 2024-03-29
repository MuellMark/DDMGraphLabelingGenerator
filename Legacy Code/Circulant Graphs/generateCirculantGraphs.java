import java.util.ArrayList;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;
import generateGraphs;

class generateCirculantGraphs{
    public static void main(String[]args){
        // Specifies size of graph
        int numVertices =7;

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
        printGraphForVis(allCombos,a,b);
        System.out.println(Arrays.toString(startCirGraph.getCirculentCycle(a, b)));

        getUsableSets(5);
        // Can use emthods from generateGraphs to avoid rewriting
        //generateGraphs.printAllCombos(allCombos);

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
    ArrayList<edgeStorageArrays> allCombos){
        //need to make a usuable set
        //then start checking recursively
    }

    //Returns the set of vertices it can use
    public static void getUsableSets(int size){
        Set<Integer> baseSet = new HashSet<>();
        for(int i=1;i<size;i++) baseSet.add(i); //all usuable elements

        ArrayList<Set<Integer>> allSets = new ArrayList<>();
        for(int i=0;i<size*2;i++){
            Set<Integer> set = new HashSet<>();
            set.add(i);
            allSets.add(set);
            // Must have size 4, in and out must be the same, 1 in and out minimum
            // need way of checking for duplicates
            // Don;t want it to be recursive if not needed
        }
        System.out.println(allSets);

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
}