import java.util.ArrayList;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;

// This was the first successful file that generated all the graphs, however, it is the slowest and 
//least optimized version of the code. Both other versions are better
class generateGraphsUnOptimized{
    public static void main(String[]args){
        int numVertices =5; // Change for # of Vertices

        // Stores all possible combinations for all recurssive calls
        ArrayList<edgeStorage> allCombos = new ArrayList<>();

        //Starting vertex is all 0's
        edgeStorage startGraph = new edgeStorage(numVertices);
        allCombos.add(startGraph);

        generate(allCombos, numVertices); // Starts generating graphs
        System.out.println("Finished! "+allCombos.size()+" Graphs generated, need to sort throught them...");
        sortAllCombos(allCombos); // Sort values for easier comparing
        ArrayList<edgeStorage> filtered = filterResults(allCombos); //Removes repeats & invalid graphs
        System.out.println("Finished first filter!! "+filtered.size()+" Graphs are in allCombos");
        // Removes inverses, comment out if inverses wanted
        ArrayList<edgeStorage> inverseFiltered = filterInverseResults(filtered);

        // Prints all graphs, need to change if inverses wanted
        printAllAdjMatrix(inverseFiltered);
        //writeAllCombosToFileVisualization(inverseFiltered);
    }

    // Loops through all recursive calls from checkforSums
    public static void generate(ArrayList<edgeStorage> allCombos, int vertex){
        // Loops through all vertices
        while(vertex>0){
            
            ArrayList<Integer> set = getUsuableSet(vertex);
            int currentSize=allCombos.size(); // Makes sure not an infinite loop, only checks current graphs
            System.out.println(currentSize);
            for(int i=0;i<currentSize;i++){
                edgeStorage temp = allCombos.get(i).copy(); // Creates copy to avoid pointer issues
                checkForSums(allCombos, temp, set, vertex, 0);
            }
            vertex--;
        }
    }

    // Recursive method, finds all possible combinations of edges
    public static void checkForSums(ArrayList<edgeStorage> allCombos, edgeStorage current,
    ArrayList<Integer> set, int vertex, int index){
        //int maxVal = 10; // Place holder, will need a way to calculate it, may be able to remove it
        int sumIns = current.getSumIns(vertex);
        int sumOuts = current.getSumOuts(vertex);

        if(sumIns==sumOuts && sumIns>0 ){
            allCombos.add(current); 
        }else if(index<set.size()){// Recurssive, may need something with the max
            // Creates copy and adds index to ins
            edgeStorage addToIns = current.copy();
            addToIns.addPair(set.get(index),vertex);

            // Creates copy and adds index to outs
            edgeStorage addToOuts = current.copy();
            addToOuts.addPair(vertex,set.get(index));
            
            // Creates copy, does not add index
            edgeStorage copyCurrent = current.copy(); 
            index++;

            //Recursive calls
            checkForSums(allCombos,copyCurrent,set,vertex,index);

            checkForSums(allCombos,addToIns,set,vertex,index);

            checkForSums(allCombos,addToOuts,set,vertex,index);

        }
    }
    //Gets usable set for a vertex
    public static ArrayList<Integer> getUsuableSet(int vertex){
        ArrayList<Integer> set = new ArrayList<>();
        for(int i=1;i<vertex;i++){
            set.add(i);
        }
        return set;
    }

    // Prints arraylist of edgeStorage
    public static void printAllCombos(ArrayList<edgeStorage> AllCombos){
        for(int i=0;i<AllCombos.size();i++){
            System.out.println("Graph #"+(i+1)+":");
            AllCombos.get(i).print();
        }
    }

        // Prints arraylist of edgeStorage
        public static void printAllAdjMatrix(ArrayList<edgeStorage> AllCombos){
            for(int i=0;i<AllCombos.size();i++){
                System.out.println("Graph #"+(i+1)+":");
                AllCombos.get(i).printAdjMatrix();
            }
        }

    // Filters results, stores all non-repeated DDM labelings in a new Arraylist, returns that
    public static ArrayList<edgeStorage> filterResults(ArrayList<edgeStorage> allCombos){
        ArrayList<edgeStorage> filtered = new ArrayList<>(); // Stores the filtered results
        for(int i=0;i<allCombos.size();i++){
            if(allCombos.get(i).isDDMLabeling()){ //Checks if it is a DDM
                boolean isRepeat= false; // Checks if repeat
                for(int j=0;j<filtered.size();j++){
                    if(filtered.get(j).equals(allCombos.get(i))) isRepeat=true;
                }
                if(!isRepeat) filtered.add(allCombos.get(i));
            }
        }
        return filtered;
    }

    // If a graph is just the inverse of another graph, not kept
    public static ArrayList<edgeStorage> filterInverseResults(ArrayList<edgeStorage> allCombos){
        ArrayList<edgeStorage> filtered = new ArrayList<>(); // Stores the filtered results
        for(int i=0;i<allCombos.size();i++){
            boolean isRepeat= false; // Checks if inverse
            for(int j=0;j<filtered.size();j++){
                if(filtered.get(j).isInverse(allCombos.get(i))) isRepeat=true;
            }
            if(!isRepeat) filtered.add(allCombos.get(i));
        }
        return filtered;
    }

    // calls sort on every graph in allCombos
    public static void sortAllCombos(ArrayList<edgeStorage> allCombos){
        for(int i=0;i<allCombos.size();i++){
            allCombos.get(i).sort();
        }
    }

    // Prints arraylist of edgeStorage
    public static void writeAllCombosToFile(ArrayList<edgeStorage> AllCombos){
        try {
            FileWriter myWriter = new FileWriter("/Users/markymarkscomputer/Desktop/Untitled/output.txt");
            for(int i=0;i<AllCombos.size();i++){
            
                myWriter.write("\nGraph #"+(i+1)+":\n");
                
                AllCombos.get(i).writeToFile(myWriter);
            }
            myWriter.close();
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
        
    }

    // Prints arraylist of edgeStorage
    public static void writeAllCombosToFileAdjMatrix(ArrayList<edgeStorage> AllCombos){
        try {
            FileWriter myWriter = new FileWriter("/Users/markymarkscomputer/Desktop/Untitled/output.txt");
            for(int i=0;i<AllCombos.size();i++){
            
                myWriter.write("\nGraph #"+(i+1)+":\n");
                
                AllCombos.get(i).writeToFileAdjMat(myWriter);
            }
            myWriter.close();
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
        
    }

    public static void writeAllCombosToFileVisualization(ArrayList<edgeStorage> AllCombos){
        try {
            FileWriter myWriter = new FileWriter("/Users/markymarkscomputer/Desktop/Untitled/outputForVis.txt");
            for(int i=0;i<AllCombos.size();i++){
            
                
                
                AllCombos.get(i).writeToFileForVisualization(myWriter);
                myWriter.write("\n");
            }
            myWriter.close();
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
        
    }
}