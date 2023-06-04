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
    //TODO: getter methods, probably a copy method
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

    // Adds element to inputs at vertex
    public void addIn(int vertex, int element){
        edges[vertex][1].add(element);
    }
    // Adds element to outputs at vertex
    public void addOut(int vertex, int element){
        edges[vertex][0].add(element);
    }

    // Adds a pair of elements from -> to
    public void addPair(int from,int to){
        addOut(from,to);
        addIn(to,from);
    }

}
// Want to move all the code over here for testing
class testEdges{
    public static void main(String[]args){
        ArrayList<edgeStorage> AllCombos = new ArrayList<>(); //Will store all possible combos I'm working with
        edgeStorage test = new edgeStorage(5);
        test.addPair(5,2);
        test.print();
    }

}