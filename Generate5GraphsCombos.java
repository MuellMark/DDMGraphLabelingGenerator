import java.util.ArrayList;
import java.util.*;

class generate5GraphsCombos{
    public static void main (String []args){
        // May actually need to be within another arraylist to track all possible outcomes
        //edges is the whole strucutre
        //edges[1-5] calls a given vertex
        //edges[1-5][0-1] calls ins vs outs, 0 for out and 1 for ins, this is an arraylist
        // NEW DECLARATION OF EDGES:
        ArrayList<Integer>[][] edges = new ArrayList[6][2];
        for(int i=1;i<6;i++){
            ArrayList<Integer>[] test = new ArrayList[2];
            for (int j = 0; j < 2; j++) {
                test[j] = new ArrayList<Integer>();
            }
            edges[i]=test;
        }
        //DEbugging
        // edges[2][1].add(53);
        // System.out.println(edges[2][1].get(0));
        // System.out.println(edges2[3][1].get(0));

        gen5KickOff(edges);

    }

    // Kick off for generating from 5
    public static void gen5KickOff(ArrayList<Integer>[][] edges){
        // Need the 3 cases, go to a different recurssive method

        //First case: 5: 1+4 and 2+3
        edges[5][1].add(1); // adding 1 and 4 as in
        edges[5][1].add(4);
        edges[1][0].add(5);
        edges[4][0].add(5);

        edges[5][0].add(2); // adding 2 and 3 as out
        edges[5][0].add(3);
        edges[2][1].add(5);
        edges[3][1].add(5);

        // Calls generate
        generate(edges, 4);
    }   

    public static void generate(ArrayList<Integer>[][] edges,int vertex){
        // Could make recursive, easier to understand if this was a while loop

        //Stores all unusable edges
        ArrayList<Integer> unusable = new ArrayList<>();
        unusable.add(vertex);
        for(int i=0;i<edges[vertex][0].size();i++){
            unusable.add(edges[vertex][0].get(i));
        }for(int i=0;i<edges[vertex][1].size();i++){
            unusable.add(edges[vertex][1].get(i));
        }
        // Need to find all combos and assign them accordingly
        // Gets set of all values that are usable from the unusable set
        ArrayList<Integer> set = new ArrayList<>();
        for(int i=1;i<6;i++){
            boolean inUnusable = false;
            for(int j=0;j<unusable.size();j++){
                if(i==unusable.get(j)) inUnusable=true;
            }
            if(!inUnusable) set.add(i);
        }
        //Testing for copy
        // System.out.println(edges[4][0]);
        // System.out.println(edges[4][1]);
        ArrayList<Integer>[] currentIO = copy(edges[vertex]); // calls my method and makes a copy
        
        //edges[4][0].add(12);
        //System.out.println(currentIO[0]);

        ArrayList<ArrayList<Integer>[]> allPosList = new ArrayList<>();
        allPosList = checkForSums(allPosList, currentIO, set, 0);
        for(int i=0;i<allPosList.get(0)[0].size();i++){
            //System.out.println(allPosList.get(0)[0].get(i)+" ");
            //System.out.println(edges[vertex][0].contains(5));
            if(!(edges[vertex][0].contains(allPosList.get(0)[0].get(i)))){
                edges[vertex][0].add(allPosList.get(0)[0].get(i));
                edges[allPosList.get(0)[0].get(i)][1].add(vertex);
            }
        }
        for(int i=0;i<allPosList.get(0)[1].size();i++){
            //System.out.println(allPosList.get(0)[1].get(i)+" ");
            if(!(edges[vertex][1].contains(allPosList.get(0)[1].get(i)))){
                edges[vertex][1].add(allPosList.get(0)[1].get(i));
                edges[allPosList.get(0)[1].get(i)][0].add(vertex);
            }
        }
        printEdges(edges);
        // Neeeds to be added to edges
    }

    // Recurssive method
    public static ArrayList<ArrayList<Integer>[]> checkForSums(ArrayList<ArrayList<Integer>[]> allPosList,
    ArrayList<Integer>[] currentIO,  ArrayList<Integer> set, int index){
        int maxVal = sum(set);  // Max value that can be made with the set may need to change
        int sumOut = sum(currentIO[0]);
        int sumIn = sum(currentIO[1]);  // Sums of the ins and outs
        if(sumIn==sumOut && sumIn>0){  // If ins and outs equal, possible match
            allPosList.add(currentIO);
            System.out.println("AAAAAA!!!!!!");
            // System.out.println(currentIO[1]);
        // Recursive case only if not at end of index, and the I/O aren't mroe than max
        }else if(index<set.size() && sumIn<=maxVal && sumOut<=maxVal){
            // System.out.println(currentIO[0]);
            // System.out.println(currentIO[1]);
            
            // Adding index to ins
            ArrayList<Integer>[] tempIncIn = copy(currentIO);
            tempIncIn[1].add(set.get(index));
            // Adding index to outs
            ArrayList<Integer>[] tempIncOut = copy(currentIO);
            tempIncOut[0].add(set.get(index));
            index++;

            // 3 cases, one for ins, one for outs, one for skipping the index
            allPosList =  checkForSums(allPosList, tempIncIn, set, index);

            allPosList =  checkForSums(allPosList, tempIncOut, set, index);

            allPosList =  checkForSums(allPosList, currentIO, set, index);
        }
        return allPosList;
    }

    public static void printEdges(ArrayList<Integer>[][] edges){
        //edges[vertex][1].add(allPosList.get(0)[1].get(i));
        for(int i=1;i<6;i++){
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

    // Copies array of arraylists
    public static ArrayList<Integer>[] copy(ArrayList<Integer>[] arr){
        ArrayList<Integer>[] newArr = new ArrayList[2];
        for (int j = 0; j < 2; j++) {
            newArr[j] = new ArrayList<Integer>();
        }

        for(int i=0;i<arr[0].size();i++){
            newArr[0].add(arr[0].get(i));
        }for(int i=0;i<arr[1].size();i++){
            newArr[1].add(arr[1].get(i));
        }
        return newArr;
    }

    //Gets sum of array list
    public static int sum(ArrayList<Integer> arr){
        int sum=0;
        for(int i =0;i<arr.size();i++){
            sum+=arr.get(i);
        }
        return sum;
    }
    //Need a method to decipher the graphs generated
}