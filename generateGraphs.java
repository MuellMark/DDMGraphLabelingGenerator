import java.util.ArrayList;
import java.util.*;

class testEdges6Vertex{
    public static void main(String[]args){
        // Stores all possible combinations for all recurssive calls
        ArrayList<edgeStorage> allCombos = new ArrayList<>();

        // edge6Storage sortTest = new edge6Storage(6);
        // sortTest.addPair(2, 1);
        // sortTest.sort();

        //Starts generating
        //gen5KickOff(allCombos);
        generate(allCombos, 6);
        sortAllCombos(allCombos);
        // Filters results and prints all valid graphs
        ArrayList<edgeStorage> filtered = filterResults(allCombos);
        ArrayList<edgeStorage> inverseFiltered = filterInverseResults(filtered);
        printAllCombos(inverseFiltered);
    }

    // Loops through all recursive calls from checkforSums
    public static void generate(ArrayList<edgeStorage> allCombos, int vertex){
        // Loops through all vertices
        while(vertex>0){
            
            ArrayList<Integer> set = getUsuableSet(vertex);
            int currentSize=allCombos.size(); // Filter comes at end, may want to add one here 
            
            for(int i=0;i<currentSize;i++){
                edgeStorage temp = allCombos.get(i).copy(); // Creates copy to avoid pointer issues
                checkForSums(allCombos, temp, set, vertex, 0);
                //System.out.println(currentSize);
            }
            vertex--;
        }
    }

    // Recursive method, finds all possible combinations of edges
    public static void checkForSums(ArrayList<edgeStorage> allCombos, edgeStorage current,
    ArrayList<Integer> set, int vertex, int index){
        int maxVal = 10; // Place holder, will need a way to calculate it
        int sumIns = current.getSumIns(vertex);
        int sumOuts = current.getSumOuts(vertex);

        if(sumIns==sumOuts && sumIns>0 && sumIns<=maxVal){
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

    public static ArrayList<edgeStorage> filterInverseResults(ArrayList<edgeStorage> allCombos){
        ArrayList<edgeStorage> filtered = new ArrayList<>(); // Stores the filtered results
        for(int i=0;i<allCombos.size();i++){
            boolean isRepeat= false; // Checks if repeat
            for(int j=0;j<filtered.size();j++){
                if(filtered.get(j).isInverse(allCombos.get(i))) isRepeat=true;
            }
            if(!isRepeat) filtered.add(allCombos.get(i));
        }
        return filtered;
    }

    public static void sortAllCombos(ArrayList<edgeStorage> allCombos){
        for(int i=0;i<allCombos.size();i++){
            allCombos.get(i).sort();
        }
    }
}