import java.util.ArrayList;

class generate5GraphsCombos{
    public static void main (String []args){
        // Keeps track of numbers that are not tied to an vertex
        ArrayList<Integer> freeNums = new ArrayList<Integer>();
        for(int i=5;i>0;i--) freeNums.add(i);

        //DEBUggING
        // System.out.println(freeNums.get(0));
        // // Removed as soon as added to any vertex
        // freeNums.remove(0); //al.remove(Integer.valueOf(1));
        // System.out.println(freeNums.get(0));

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
        edges[2][1].add(53);
        System.out.println(edges[2][1].get(0));
        // System.out.println(edges2[3][1].get(0));


    }
}