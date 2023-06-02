import java.util.ArrayList;

class generate5GraphsCombos{
    public static void main (String []args){
        // Keeps track of numbers that are not tied to an vertex
        ArrayList<Integer> freeNums = new ArrayList<Integer>();
        for(int i=5;i>0;i--) freeNums.add(i);

        System.out.println(freeNums.get(0));
        // Removed as soon as added to any vertex
        freeNums.remove(0); //al.remove(Integer.valueOf(1));
        System.out.println(freeNums.get(0));

        ArrayList<int[][]> edges = new ArrayList<int[][]>();
        int[][] error =new int[1][1];
        error[0][0]=-1;
        edges.add(error);
        for(int i=1;i<6;i++){
            edges.add(new int[2][4]);
            // Need input and output for each possible vertex
        }

        // ArrayList<Integer>[] test = new ArrayList[2];
        // for (int i = 0; i < 2; i++) {
        //     test[i] = new ArrayList<Integer>();
        // }
        // System.out.println(test[0].size());
        // NEW DECLARATION OF EDGES:
        ArrayList<Integer>[][] edges2 = new ArrayList[6][2];
        for(int i=1;i<6;i++){
            ArrayList<Integer>[] test = new ArrayList[2];
            for (int j = 0; j < 2; j++) {
                test[j] = new ArrayList<Integer>();
            }
            edges2[i]=test;
        }
        edges2[2][1].add(53);
        System.out.println(edges2[2][1].get(0));
        System.out.println(edges2[3][1].get(0));


    }
}