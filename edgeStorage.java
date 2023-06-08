import java.util.ArrayList;
import java.util.*;

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
    public void sort(){
        //System.out.println("ASFDG");
        for(int i=1;i<size()+1;i++){
            Collections.sort(edges[i][0]);
            Collections.sort(edges[i][1]);
        }
    }

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

        //Checks if the two edgeStorages are the exact same
        public boolean isInverse(edgeStorage other){
            boolean equals = true;
            if(size()!=other.size()) equals = false;
            else{
                for(int i=1;i<size()+1;i++){
                    if(!getIns(i).equals(other.getOuts(i))) equals=false;
                    if(!getOuts(i).equals(other.getIns(i))) equals=false;
                }
            }
            
            return equals;
        }
}
