package graphlabeling.circulantgraphs.ddmlabelings;
import java.util.ArrayList;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;

class generateGraphs{
    public static void main(String[]args){
        // Change for # of Vertices you'd like to generate
        int numVertices =6; 

        // Change number based on the desired method
        // 1 = isDDMLabeling()
        // 2 = isDDMLabelingIncludeZeroes()
        // 3 = isCirculantLabeling() REMOVE #, other file
        int methodSelect = 1;

        // These two variables are only for when methodSelect is set at 3, they select the
        // a and b values for checkign for circulant labelings.
        // Note: a=/=b, a<b<numVertices/2
        int a=1;
        int b=2;

        // For changing how the set works, TODO, may be removed
        int usableSetSelector = 1;

        // Stores all possible combinations for all recurssive calls
        ArrayList<edgeStorageArrays> allCombos = new ArrayList<>();
        // Stores all ddmLabelings as the recurssive method runs through
        ArrayList<edgeStorageArrays> ddmLabelings = new ArrayList<>();

        // StartGraph is all zeroes
        edgeStorageArrays startGraph = new edgeStorageArrays(numVertices);
        allCombos.add(startGraph);

        generate(allCombos, numVertices,ddmLabelings,methodSelect, usableSetSelector,a,b); // Starts generating graphs

        // Different print statements, comment out desired one
        //printAllCombos(ddmLabelings);
        printAllAdjMatrix(ddmLabelings);

        //Different Write to files, comment out the desired one
        //writeAllCombosToFile(ddmLabelings);
        //writeAllCombosToFileAdjMatrix(ddmLabelings);
        //writeAllCombosToFileVisualization(ddmLabelings);
    }

    // Loops through all recursive calls from checkforSums
    public static void generate(ArrayList<edgeStorageArrays> allCombos, int vertex,ArrayList<edgeStorageArrays> ddmLabelings,
    int methodSelect, int usableSetSelector,int a,int b){
        // Loops through all vertices in reverse order
        while(vertex>0){
            ArrayList<Integer> set = getUsableSet(usableSetSelector, vertex); // Gets usable edge set
            int currentSize=allCombos.size(); // Makes sure not an infinite loop, only checks current graphs
            System.out.println("Graphs generated so far: "+currentSize);
            // Does a recurrsive call to all graphs in allCombos
            for(int i=0;i<currentSize;i++){
                edgeStorageArrays temp = allCombos.get(i).copy(); // Creates copy to avoid pointer issues
                checkForSums(allCombos, temp, set, vertex, 0,ddmLabelings,methodSelect,a,b); // Recursive call
            }
            vertex--;
        }
    }

    // Recursive method, finds all possible combinations of edges 
    public static void checkForSums(ArrayList<edgeStorageArrays> allCombos, edgeStorageArrays current,
    ArrayList<Integer> set, int vertex, int index, ArrayList<edgeStorageArrays> ddmLabelings, int methodSelect,
    int a, int b){
        int sumIns = current.getSumIns(vertex);
        int sumOuts = current.getSumOuts(vertex);

        // base case, if the two are equal then a potential labeling could be found
        if(sumIns==sumOuts && sumIns>0){
            allCombos.add(current); // Adds to allCombos for future graphs to check from
            if(callSpecificMethod(methodSelect, current,a,b)){ // Change to include zeroes with current.isDDMLabelingIncludeZeroes()
                boolean isInvOrEq = false;
                //Checks if the graphs found is an inverse or repeat graph
                for(int i=0;i<ddmLabelings.size();i++){
                    if(ddmLabelings.get(i).isInverse(current) || ddmLabelings.get(i).equals(current)){
                        isInvOrEq=true;
                        i+=allCombos.size();
                    }
                }
                // If the graph is a valid labeling, is not a repeat or inverse, then it's added to ddmLabelings
                if(!isInvOrEq){
                    ddmLabelings.add(current);
                }
            }
        }
            
        // Otherwise, does a recurrsive case
        else if(index<set.size() ){
            // Creates copy and adds index to ins
            edgeStorageArrays addToIns = current.copy();
            addToIns.addPair(set.get(index),vertex);
 
            // Creates copy and adds index to outs
            edgeStorageArrays addToOuts = current.copy();
            addToOuts.addPair(vertex,set.get(index));
                
            // Creates copy, does not add index to either ins or outs
            edgeStorageArrays copyCurrent = current.copy(); 
            index++; // Incremenets index

            // Debugging statement, to track number of graphs generated when dealing with large cases
            if(allCombos.size()%10000==0) System.out.println("Graph added, "+allCombos.size()+" graphs found");
            //Recursive calls, one for each new graph created
            checkForSums(allCombos,copyCurrent,set,vertex,index,ddmLabelings,methodSelect,a,b);

            checkForSums(allCombos,addToOuts,set,vertex,index,ddmLabelings,methodSelect,a,b);

            checkForSums(allCombos,addToIns,set,vertex,index,ddmLabelings,methodSelect,a,b);

        }
    }

    public static ArrayList<Integer>  getUsableSet(int num, int vertex){
        ArrayList<Integer> set = new ArrayList<>();
        switch(num){
            case(1):
                set = getUsuableSetDecreasing(vertex);
                return set;
            // case(2):
            //     return current.isDDMLabelingIncludeZeroes();
            default:
                return set;
        }
    }
    //Gets usable set for a vertex, basically all vertices less than the input
    public static ArrayList<Integer> getUsuableSetDecreasing(int vertex){
        ArrayList<Integer> set = new ArrayList<>();
        for(int i=1;i<vertex;i++){
            set.add(i);
        }
        return set;
    }

    // Prints arraylist of edgeStorage in a readable fassion
    public static void printAllCombos(ArrayList<edgeStorageArrays> AllCombos){
        for(int i=0;i<AllCombos.size();i++){
            System.out.println("Graph #"+(i+1)+":");
            AllCombos.get(i).print();
        }
    }

        // Prints arraylist of edgeStorage in an adjacency matrix for easy checking
        public static void printAllAdjMatrix(ArrayList<edgeStorageArrays> AllCombos){
            for(int i=0;i<AllCombos.size();i++){
                System.out.println("Graph #"+(i+1)+":");
                AllCombos.get(i).printAdjMatrix();
            }
        }

    // Writes the arraylist of combinations to a file in a readbale way
    public static void writeAllCombosToFile(ArrayList<edgeStorageArrays> AllCombos){
        try {
            // Opens files, change file name here
            FileWriter myWriter = new FileWriter("/Users/markymarkscomputer/Desktop/Untitled/Java Code/output.txt");
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

    // Writes to a file in an adjacency matrix for manual checking
    public static void writeAllCombosToFileAdjMatrix(ArrayList<edgeStorageArrays> AllCombos){
        try {
            // Opens file, change file name here
            FileWriter myWriter = new FileWriter("/Users/markymarkscomputer/Desktop/Untitled/Java Code/output.txt");
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

    // Writes to file to be read in by visualizeGraphs.py
    public static void writeAllCombosToFileVisualization(ArrayList<edgeStorageArrays> AllCombos){
        try {
            // Opens File, change file name here
            FileWriter myWriter = new FileWriter("/Users/markymarkscomputer/Desktop/Untitled/Python Code/outputForVis.txt");
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

    // Selector method to call different methods based on the user selected number
    // If invalid number selected, no grpahs are generated
    public static boolean callSpecificMethod(int num, edgeStorageArrays current,int a,int b){
        switch(num){
            case(1):
                return current.isDDMLabeling();
            case(2):
                return current.isDDMLabelingIncludeZeroes();
            case(3):
                return current.isCirculantLabeling(a,b);
            default:
                return false;
        }
    }


        
    // LEGACY, keeping in case needed in future 
    // Filters results, stores all non-repeated DDM labelings in a new Arraylist, returns that
    public static ArrayList<edgeStorageArrays> filterResults(ArrayList<edgeStorageArrays> allCombos){
        ArrayList<edgeStorageArrays> filtered = new ArrayList<>(); // Stores the filtered results
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

    // LEGACY, keeping in case needed in future 
    // If a graph is just the inverse of another graph, not kept, to be used in addition to 
    // filterResults, not instead of it
    public static ArrayList<edgeStorageArrays> filterInverseResults(ArrayList<edgeStorageArrays> allCombos){
        ArrayList<edgeStorageArrays> filtered = new ArrayList<>(); // Stores the filtered results
        for(int i=0;i<allCombos.size();i++){
            boolean isRepeat= false; // Checks if inverse
            for(int j=0;j<filtered.size();j++){
                if(filtered.get(j).isInverse(allCombos.get(i))) isRepeat=true;
            }
            if(!isRepeat) filtered.add(allCombos.get(i));
        }
        return filtered;
    }
}

