import java.util.ArrayList;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;

class generateCirculantGraphs{
    public static void main(String[]args){
        // Specifies size of graph
        int numVertices =7;

        // Used to generate given circulant graph
        int a=1;
        int b=3;

        //Stores all ddm labelings of a given Circualnt graph
        ArrayList<edgeStorageArrays> allCombos = new ArrayList<>();

        // Stores original starter Circulant graph
        edgeStorageArrays startCirGraph = new edgeStorageArrays(numVertices);
        createCirculantGraphs(numVertices, a, b, startCirGraph);
        //startCirGraph.printAdjMatrix();

        allCombos.add(startCirGraph);
        printGraphForVis(allCombos);
        System.out.println(Arrays.toString(startCirGraph.getCirculentCycle(a, b)));
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

    // similar to how it was done in generateGraphs, but need extra line for the cycle
    public static void printGraphForVis(ArrayList<edgeStorageArrays> AllCombos){
        try {
            // Opens files, change file name here
            FileWriter myWriter = new FileWriter("/Users/markymarkscomputer/Desktop/Untitled/Python Code/outputForCirVis.txt");
            for(int i=0;i<AllCombos.size();i++){
                // myWriter.write("\nGraph #"+(i+1)+":\n");
                //Grapcycle goes here
                AllCombos.get(i).writeToFileForVisualization(myWriter);
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}