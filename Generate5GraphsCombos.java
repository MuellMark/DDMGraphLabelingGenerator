import java.util.ArrayList;
import java.util.*;

class generate5GraphsCombos{
    public static void main (String []args){
        // Keeps track of numbers that are not tied to an vertex
        ArrayList<Integer> freeNums = new ArrayList<Integer>();
        for(int i=5;i>0;i--) freeNums.add(i);

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

        generate(edges, 4);
    }   

    public static void generate(ArrayList<Integer>[][] edges,int vertex){
        ArrayList<Integer> unusable = new ArrayList<>();
        unusable.add(vertex);
        for(int i=0;i<edges[vertex][0].size();i++){
            unusable.add(edges[vertex][0].get(i));
        }for(int i=0;i<edges[vertex][1].size();i++){
            unusable.add(edges[vertex][1].get(i));
        }
        //System.out.println(unusable);
        // Need to find all combos and assign them accordingly
        // Gets set of all values that are usable
        ArrayList<Integer> set = new ArrayList<>();
        for(int i=1;i<6;i++){
            boolean inUnusable = false;
            for(int j=0;j<unusable.size();j++){
                if(i==unusable.get(j)) inUnusable=true;
            }
            if(!inUnusable) set.add(i);
        }
        ArrayList<Integer> in = new ArrayList<>();
        Collections.copy(in, edges[4][1]);
        ArrayList<Integer> out = edges[4][0];
        Collections.copy(out, edges[4][0]);
        // Collections.copy(list,zoo); // copying the ArrayList zoo to the ArrayList list
        ArrayList<Integer>[] currentIO = new ArrayList[2];
        currentIO[0]=out;
        currentIO[1]=in;
        
        edges[4][0].add(12);
        System.out.println(currentIO[0]);
        // ArrayList<Integer>[] currentIO = new ArrayList[2]; // Array of 2 array lists to be added
        //     for (int j = 0; j < 2; j++) {
        //         currentIO[j] = new ArrayList<Integer>();
        //     }

        ArrayList<ArrayList<Integer>[]> allPosList = new ArrayList<>();
        allPosList = checkForSums(allPosList, currentIO, set, 0);
        //System.out.println(currentIO[1]);
        // checkForSums(edges, set);
    }

    public static ArrayList<ArrayList<Integer>[]> checkForSums(ArrayList<ArrayList<Integer>[]> allPosList,
    ArrayList<Integer>[] currentIO,  ArrayList<Integer> set, int index){
        int maxVal = sum(set);
        int sumOut = sum(currentIO[0]);
        int sumIn = sum(currentIO[1]);
        if(sumIn==sumOut && sumIn>0){
            allPosList.add(currentIO);
            System.out.println("AAAAAA!!!!!!");
        }else if(index<set.size() && sumIn<=maxVal && sumOut<=maxVal){
            //REcursive cases
            
            ArrayList<Integer>[] tempIncIn = currentIO;
            tempIncIn[1].add(set.get(index));
            ArrayList<Integer>[] tempIncOut = currentIO;
            tempIncOut[0].add(set.get(index));
            index++;

            allPosList =  checkForSums(allPosList, tempIncIn, set, index);

            allPosList =  checkForSums(allPosList, tempIncOut, set, index);

            allPosList =  checkForSums(allPosList, currentIO, set, index);
            
            
            //System.out.println("made it here ig");
        }
        return allPosList;
    }
    
    // public static ArrayList<Integer>[] checkForSums(ArrayList<Integer>[][] edges, ArrayList<Integer> set){
    //     // Array list that stores sets of ins and outs to be added
    //     ArrayList<ArrayList<Integer>[]> test = new ArrayList<>();
    //     ArrayList<Integer>[] possibleAdditions = new ArrayList[2]; // Array of 2 array lists to be added
    //         for (int j = 0; j < 2; j++) {
    //             possibleAdditions[j] = new ArrayList<Integer>();
    //         }
    //     ArrayList<Integer>[] possibleAddition = new ArrayList[2]; // Array of 2 array lists to be added
    //         for (int j = 0; j < 2; j++) {
    //             possibleAddition[j] = new ArrayList<Integer>();
    //         }
        
    //     System.out.println(possibleAddition.length);
    //     int maxVal = sum(set);
    //     // Somehow set needs to be added around to find all possible combos
    //     return possibleAddition;
    // }

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