import java.util.ArrayList;

class javaGen5Graphs{
    public static void main (String []args){
        //Initializes an array of edges at the start
        int[][] edges =new int[8][2];
        for(int i=0;i<8;i++){
            for(int j=0;j<2;j++){
                edges[i][j]=1;
            }
        }

        // Test for generate, will have 6 total edges to add instead of 8
        int[][] test =new int[3][2];
        for(int i=0;i<3;i++){
            for(int j=0;j<2;j++){
                test[i][j]=1;
            }
        }

        //Stores all possible graphs
        ArrayList <int[][]> possibleGraphs= new ArrayList<int[][]>();
        possibleGraphs.add(edges);

        //printEdges(possibleGraphs.get(0));

        possibleGraphs=generate(possibleGraphs, test);
        printPossibleGraphs( possibleGraphs);

    }

    // Prints the edges in a readable format
    public static void printEdges(int[][] edges){
        for(int i=0;i<8;i++){
            System.out.print("[");
            for(int j=0;j<2;j++){
                System.out.print(edges[i][j]);
                if(j==0) System.out.print(",");
            }
            System.out.print("] ");
        }
    }

    public static void printPossibleGraphs(ArrayList <int[][]> possibleGraphs){
       for(int i=0;i<possibleGraphs.size();i++){
        printEdges(possibleGraphs.get(i));
       } 
    }

    //TODO: This method will be for generating the graphs
    // inputs: a start arr of edges, and the arraylist
    // output: arraylist, to be used to test
    public static ArrayList <int[][]> generate(ArrayList <int[][]> possibleGraphs,int[][] startEdges){
        //First, I need a starting point for the array of edges
        // I'm going to try an iterative approach that first goes through all possible permutations
        // Checking each one, then seeing if I can scale it down

        System.out.print("TODO");
        return possibleGraphs;
    }
}