import java.util.ArrayList;
import java.util.*;

// Might make it's own file
class edgeStorage{
    ArrayList<Integer>[][] edges;

    // Constructor to specify size
    public edgeStorage(int size){
        size++;
        edges = new ArrayList[size][2];
        for(int i=1;i<size;i++){
            ArrayList<Integer>[] test = new ArrayList[2];
            for (int j = 0; j < 2; j++) {
                test[j] = new ArrayList<Integer>();
            }
            edges[i]=test;
        }
    }
    //Default Constructor
    public edgeStorage(){
        edges = new ArrayList[6][2];
        for(int i=1;i<6;i++){
            ArrayList<Integer>[] test = new ArrayList[2];
            for (int j = 0; j < 2; j++) {
                test[j] = new ArrayList<Integer>();
            }
            edges[i]=test;
        }
    }

    //prints edges in a easily readable way
    public void print(){
        for(int i=1;i<edges.length;i++){
            System.out.print("Vertex "+i+":");
            System.out.print(" [i:");
            for(int j=0;j<edges[i][1].size();j++){
                System.out.print(" "+edges[i][1].get(j));
            }
            System.out.print("]\n          [o:");
            for(int j=0;j<edges[i][0].size();j++){
                System.out.print(" "+edges[i][0].get(j));
            }
            System.out.println("]");
        }
    }

    // Getter for a vertex's inputs, makes a copy, returns arrayList
    public ArrayList<Integer> getIns(int vertex){
        ArrayList<Integer> ins = new ArrayList<Integer>();
        for(int i=0;i<edges[vertex][1].size();i++){
            ins.add(edges[vertex][1].get(i));
        } 
        return ins;
    }

    // Getter for a vertex's outputs, makes a copy, returns arrayList
    public ArrayList<Integer> getOuts(int vertex){
        //System.out.println("OUT:"+edges[vertex][0]);
        ArrayList<Integer> outs = new ArrayList<Integer>();
        for(int i=0;i<edges[vertex][0].size();i++){
            outs.add(edges[vertex][0].get(i));
        } 
        return outs;
    }

    // Adds element to inputs at vertex
    public void addIn(int vertex, int element){
        edges[vertex][1].add(element);
    }
    // Adds element to outputs at vertex
    public void addOut(int vertex, int element){
        edges[vertex][0].add(element);
    }

    // Takes in an arraylist of ins, and adds them to then ins one by one 
    public void addInList(int vertex, ArrayList<Integer> ins){
        //for(int i=0;i<edges[vertex][i].size();i++) outs.add(edges[vertex][0].get(i));
        for(int i=0;i<ins.size();i++) edges[vertex][1].add(ins.get(i));
        //edges[vertex][1]=ins;
    }

    // Takes in an arraylist of outss, and adds them to then outss one by one 
    public void addOutList(int vertex, ArrayList<Integer> outs){
        for(int i=0;i<outs.size();i++) edges[vertex][0].add(outs.get(i));
    }

    // Adds a pair of elements from -> to
    public void addPair(int from,int to){
        addOut(from,to);
        addIn(to,from);
    }

    // Gets the sum of the ins at a vertex
    public int getSumIns(int vertex){
        int sum=0;
        for(int i=0;i<edges[vertex][1].size();i++) sum+=edges[vertex][1].get(i);
        return sum;
    }

    // Gets the sum of the outs at a vertex
    public int getSumOuts(int vertex){
        int sum=0;
        for(int i=0;i<edges[vertex][0].size();i++) sum+=edges[vertex][0].get(i);
        return sum;
    }

    // gets how many vertices are in edges, not actual size of the array
    public int size(){
        return edges.length-1;
    }

    // Checks if it is a DDM labeling, not accounting for zeroes
    public boolean isDDMLabeling(){
        boolean isDDM = true;
        for(int i=1;i<size()+1;i++){
            if(getSumIns(i)!=getSumOuts(i)) isDDM =false;
            if(getIns(i).size()+getOuts(i).size()<3) isDDM =false; // Will need to change later for 0's
        }
        return isDDM;
    }
    // Returns a copy of the current edgeStorage in question
    public edgeStorage copy(){
        edgeStorage newArr = new edgeStorage(this.size());
        for(int i=1;i<=newArr.size();i++){
            ArrayList<Integer> tempIn = this.getIns(i);
            newArr.addInList(i, tempIn);

            ArrayList<Integer> tempOut = this.getOuts(i);
            newArr.addOutList(i, tempOut);
        }
        return newArr;

    }

    //Sorts the ins and outs of all ins and outs
    // public int sort(){
    //     Systen.out.println("ASFDG");
    //     // for(int i=1;i<size()+1;i++){
    //     //     Collections.sort(edges[0]);
    //     //     Collections.sort(edges[1]);
    //     // }
    //     return 2;
    // }

    //Checks if the two edgeStorages are the same
    public boolean equals(edgeStorage other){
        boolean equals = true;
        if(size()!=other.size()) equals = false;
        else{
            for(int i=1;i<size()+1;i++){
                if(!getIns(i).equals(other.getIns(i))) equals=false;
                if(!getIns(i).equals(other.getIns(i))) equals=false;
            }
        }
        
        return equals;
    }

    

}
class testEdges6Vertex{
    public static void main(String[]args){
        // Stores all possible combinations for all recurssive calls
        ArrayList<edgeStorage> allCombos = new ArrayList<>();

        // edgeStorage sortTest = new edgeStorage(6);
        // sortTest.addPair(2, 1);
        // sortTest.sort();
        //Starts generating
        gen5KickOff(allCombos);
        //sortAllCombos(allCombos);
        // Filters results and prints all valid graphs
        ArrayList<edgeStorage> filtered = filterResults(allCombos);
        printAllCombos(filtered);
    }

    // will need to be replaced, need a way to calculate this
    public static void gen5KickOff(ArrayList<edgeStorage> allCombos){
        edgeStorage gen5Case1 = new edgeStorage(6);
        gen5Case1.print();
        // gen5Case1.addPair(1, 5);
        // gen5Case1.addPair(4, 5);

        // gen5Case1.addPair(5, 2);
        // gen5Case1.addPair(5, 3);
        allCombos.add(gen5Case1);

        // edgeStorage gen5Case2 = new edgeStorage(5);
        // gen5Case2.addPair(4, 5);

        // gen5Case2.addPair(5, 1);
        // gen5Case2.addPair(5, 3);
        // allCombos.add(gen5Case2);

        // edgeStorage gen5Case3 = new edgeStorage(5);
        // gen5Case3.addPair(3, 5);

        // gen5Case3.addPair(5, 1);
        // gen5Case3.addPair(5, 2);
        // allCombos.add(gen5Case3);

        generate(allCombos, 6);
    }

    // public static void sortAllCombos(ArrayList<edgeStorage> allCombos){
    //     for(int i=0;i<allCombos.size();i++){
    //         allCombos.get(i).sort();
    //     }
    // }
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
}