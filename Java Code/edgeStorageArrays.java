import java.util.ArrayList;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;

// Class used to store a graph. Effectivly an array of arrays of arrays
// The first array is of size n+1, where n is the number of vertices in a given graph
// The 0th index is not used to make it easier, vertex 1 is index 1, and so on
// For each of those arrays there is an array of size 2, 0 is for edges oing out, 1 is for edges coming in
// Then, for both 0 and 1, there is an array to store all the edges where the 0th index is the index at which 
// to add the next edge
class edgeStorageArrays{
   int[][][] edges;

    // Constructor to specify size
    public edgeStorageArrays(int size){
        size++;
        edges = new int[size][2][size];
        for(int i=1;i<size;i++){
            for (int j = 0; j < 2; j++) {
                edges[i][j][0]=1;
            }
        }
    }
    //Default Constructor, size of 6 for 5 vertices
    public edgeStorageArrays(){
        edges = new int[6][2][6];
        for(int i=1;i<6;i++){
            for (int j = 0; j < 2; j++) {
                edges[i][j][0]=1;
            }
        }
    }

    //prints edges in a easily readable way
    public void print(){
        for(int i=1;i<edges.length;i++){
            System.out.print("Vertex "+i+":");
            System.out.print(" [i:");
            for(int j=1;j<edges[i][1].length;j++){
                if(edges[i][1][j]>0) System.out.print(" "+edges[i][1][j]);
            }
            System.out.print("]\n          [o:");
            for(int j=1;j<edges[i][0].length;j++){
                if(edges[i][0][j]>0) System.out.print(" "+edges[i][0][j]);
            }
            System.out.println("]");
        }
    }

    // Getter for a vertex's inputs, makes a copy, returns arrayList
    public int[] getIns(int vertex){
        int[] ins = new int[edges[vertex][1].length];
        for(int i=0;i<edges[vertex][1].length;i++){
            ins[i]=edges[vertex][1][i];
        } 
        return ins;
    }

    // Getter for a vertex's outputs, makes a copy, returns arrayList
    public int[] getOuts(int vertex){
        int[] outs = new int[edges[vertex][0].length];
        for(int i=0;i<edges[vertex][0].length;i++){
            outs[i]=edges[vertex][0][i];
        } 
        return outs;
    }

    // Adds element to inputs at vertex
    public void addIn(int vertex, int element){
        int tempIndex = edges[vertex][1][0];
        edges[vertex][1][tempIndex]=element;
        edges[vertex][1][0]++;
    }
    // Adds element to outputs at vertex
    public void addOut(int vertex, int element){
        int tempIndex = edges[vertex][0][0];
        edges[vertex][0][tempIndex]=element;
        edges[vertex][0][0]++;
    }

    // Takes in an arraylist of ins, and adds them to then ins one by one 
    public void addInList(int vertex, int[] ins){
        for(int i=0;i<ins.length;i++) edges[vertex][1][i]=(ins[i]);
    }
    // Takes in an arraylist of outss, and adds them to then outss one by one 
    public void addOutList(int vertex, int[] outs){
        for(int i=0;i<outs.length;i++) edges[vertex][0][i]=(outs[i]);
    }

    // Adds a pair of elements from -> to
    public void addPair(int from,int to){
        addOut(from,to);
        addIn(to,from);
    }

    // Gets the sum of the ins at a vertex
    public int getSumIns(int vertex){
        int sum=0;
        for(int i=1;i<edges[vertex][1].length;i++) sum+=edges[vertex][1][i];
        return sum;
    }
    // Gets the sum of the outs at a vertex
    public int getSumOuts(int vertex){
        int sum=0;
        for(int i=1;i<edges[vertex][0].length;i++) sum+=edges[vertex][0][i];
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
            if(getIns(i)[0]+getOuts(i)[0]<5) isDDM =false; // Will need to change later for 0's
        }
        return isDDM;
    }

    // Checks if it is a DDM labeling, not accounting for zeroes
    public boolean isDDMLabelingIncludeZeroes(){
        boolean isDDM = true;
        for(int i=1;i<size()+1;i++){
            if(getSumIns(i)!=getSumOuts(i)) isDDM =false;
        }
        return isDDM;

    }

    //TODO
    // Checks if it is a circulant labeling
    public boolean isCirculantLabeling(){
        boolean isCir = true;

        if(!allEdgesDegree4()) isCir=false;
        if(isCir){
            ArrayList<int[]> cycles = getCycles();
        }
        //Needs method to get the cycle
        //Needs method to check said cycle
        return isCir;
    }

    // Checks if all vertices have degree 4
    private boolean allEdgesDegree4(){
        boolean all4 = true;
        for(int i=1;i<size();i++){
            int tempSum=0;
            tempSum+=edges[i][0][0]-1;
            tempSum+=edges[i][1][0]-1;
            if(tempSum!=4) all4=false;
        }
        return all4;
    }

    // Returns all possible cycles in a given graph, kick off method
    // Not sure if should be arraylist
    public ArrayList<int[]> getCycles(){
        ArrayList<int[]> cycles = new ArrayList<>();
        //Start point always 1, don't need multiple start points
        //Will keep track of possibleNums to avoid repeating
        ArrayList<Integer> possibleNums = new ArrayList<>();
        for(int i=1;i<size();i++) possibleNums.add(i);
        int[] cycle = new int[size()-1];
        cycle[0]=1;

        getCyclesRecur(cycle,1,possibleNums,cycles);

        return cycles;
    }

    private void getCyclesRecur(int[] cycle,int currentEdge, ArrayList<Integer> possibleNums, 
    ArrayList<int []> cycles){
        if(possibleNums.size()==0){
            if(contains(edges[currentEdge][0],1) ||contains(edges[currentEdge][1],1)){
                cycles.add(cycle);
            }
        }else{
            for(int i=1;i<edges[currentEdge][0][0];i++){
                //TODo need copy method for arrayInts and arraylist of ints
                //getCyclesRecur(cycle,)
            }
        }
        //Base case, when possiblenums is empty
        //Recur case, check for all possible next moves, remove it from possiblenum,
        //Move the current to that edge and call the method again.

        //Meed to check is there are duplicates
    }

    // Returns a copy of the current edgeStorage in question
    public edgeStorageArrays copy(){
        edgeStorageArrays newArr = new edgeStorageArrays(this.size());
        for(int i=1;i<=newArr.size();i++){
            int[] tempIn = this.getIns(i);
            newArr.addInList(i, tempIn);

            int[] tempOut = this.getOuts(i);
            newArr.addOutList(i, tempOut);
        }
        return newArr;
    }

    //Checks if the two edgeStorages are the same
    public boolean equals(edgeStorageArrays other){
        boolean equals = true;
        if(size()!=other.size()) equals = false;
        else{
            for(int i=1;i<size()+1;i++){
                
                if(!Arrays.equals(getIns(i),other.getIns(i))){
                    equals=false;
                    //System.out.println(getIns(i)+" =/="+getIns(i));
                }
                if(!Arrays.equals(getOuts(i),other.getOuts(i))){
                    equals=false;
                    //System.out.println(getOuts(i)+" =/="+getOuts(i));
                }
            }
        }
        return equals;
    }

    //Checks if the two edgeStorages are the exact same
    public boolean isInverse(edgeStorageArrays other){
        boolean equals = true;
        if(size()!=other.size()) equals = false;
        else{
            for(int i=1;i<size()+1;i++){
                if(!Arrays.equals(getIns(i),other.getOuts(i))) equals=false;
                if(!Arrays.equals(getOuts(i),other.getIns(i))) equals=false;
            }
        }
        return equals;
    }

    //writes edges in an easily readable way to a file
    public void writeToFile(FileWriter myWriter){
        try {
            for(int i=1;i<edges.length;i++){
                myWriter.write("Vertex "+i+":");
                myWriter.write(" [i:");
                for(int j=0;j<edges[i][1].length;j++){
                    if(edges[i][0][j]>0) myWriter.write(" "+edges[i][0][j]);
                }
                myWriter.write("]\n          [o:");
                for(int j=0;j<edges[i][0].length;j++){
                    if(edges[i][1][j]>0) myWriter.write(" "+edges[i][0][j]);
                }
                myWriter.write("]\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }   
    }

    // Prints as adjacency matrix for easy manual checking
    public void printAdjMatrix(){
        for(int i=1;i<=size();i++){
            String tempStr = "";
            for(int j=1;j<=size();j++){
                int found = 0;
                for(int k =0;k<edges[i][1].length;k++){
                    if(edges[i][1][k]==j) found=1;
                }
                tempStr+= " "+found+",";
            }
            tempStr=tempStr.substring(0,tempStr.length()-1);
            System.out.println(tempStr);
        }
    }

    //prints edges in adjacency matrix to file
    public void writeToFileAdjMat(FileWriter myWriter){
        try {
            for(int i=1;i<=size();i++){
                String tempStr = "";
                for(int j=1;j<=size();j++){
                    int found = 0;
                    for(int k =1;k<edges[i][1].length;k++){
                        if(edges[i][1][k]==j) found=1;
                    }
                    tempStr+= " "+found+",";
                }
                tempStr=tempStr.substring(0,tempStr.length()-1);
                myWriter.write(tempStr+"\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }   
    }
         
    //prints edges in a way that can be read in by visualizeGraphs.py
    public void writeToFileForVisualization(FileWriter myWriter){
        try {
            for(int i=1;i<edges.length;i++){
                for(int j=1;j<edges[i][1].length;j++){
                    if(edges[i][1][j]>0) myWriter.write(i+" "+edges[i][1][j]+",");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }    
    }

    private boolean contains(int[] arr,int num){
        boolean contain = false;
        for(int i=1;i<=arr[0];i++){
            if(arr[i]==num) contain=true;
        }
        return contain;
    }
}
