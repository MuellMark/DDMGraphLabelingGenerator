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
            // Need input and output for each possible vertex
        }
    }
}