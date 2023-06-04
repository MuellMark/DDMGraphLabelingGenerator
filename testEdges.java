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
    //TODO: getter methods, probably a copy method
    // Need getters for arraylists of ins and outs
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

    // getter for a vertex's inputs, makes a copy
    public ArrayList<Integer> getIns(int vertex){
        ArrayList<Integer> ins = new ArrayList<Integer>();
        for(int i=0;i<edges[vertex][1].size();i++){
            ins.add(edges[vertex][1].get(i));
        } 
        return ins;
    }

    // getter for a vertex's outputs, makes a copy
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

    public void addInList(int vertex, ArrayList<Integer> ins){
        //for(int i=0;i<edges[vertex][i].size();i++) outs.add(edges[vertex][0].get(i));
        for(int i=0;i<ins.size();i++) edges[vertex][1].add(ins.get(i));
        //edges[vertex][1]=ins;
    }

    public void addOutList(int vertex, ArrayList<Integer> outs){
        for(int i=0;i<outs.size();i++) edges[vertex][0].add(outs.get(i));
    }

    // Adds a pair of elements from -> to
    public void addPair(int from,int to){
        addOut(from,to);
        addIn(to,from);
    }

    // gets how many vertices are in edges
    public int size(){
        return edges.length-1;
    }

    public edgeStorage copy(){
        edgeStorage newArr = new edgeStorage(this.size());
        for(int i=1;i<=newArr.size();i++){
            ArrayList<Integer> tempIn = this.getIns(i);
            //System.out.println(tempIn);
            newArr.addInList(i, tempIn);

            ArrayList<Integer> tempOut = this.getOuts(i);
            // System.out.println("OUT:"+tempOut);
            newArr.addOutList(i, tempOut);
        }
        return newArr;


    }
    // public ArrayList<Integer>[][] copy(ArrayList<Integer>[][] current){
    //     edgeStorage newArr = new edgeStorage(current.length-1);
    //     for(int i=0;i<current.length;i++);
    //     return newArr;
    // }

}
// Want to move all the code over here for testing
class testEdges{
    public static void main(String[]args){
        ArrayList<edgeStorage> AllCombos = new ArrayList<>(); //Will store all possible combos I'm working with
        edgeStorage test = new edgeStorage(5);
        test.addPair(5,2);
        //test.print();
        ArrayList<Integer> testList = test.getIns(2);
        //System.out.println("TEST"+testList);
        edgeStorage test2 = test.copy();
       
        test.print();
        test2.print();
        //System.out.println(test2.size());
    }

}