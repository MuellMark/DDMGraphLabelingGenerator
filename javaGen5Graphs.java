import java.util.ArrayList;
import java.util.Arrays;

class javaGen5Graphs{
    public static void main (String []args){
        //Initializes an array of edges at the start
        int[][] edges =new int[8][2];
        for(int i=0;i<8;i++){
            for(int j=0;j<2;j++){
                edges[i][j]=1;
            }
        }

        // Test for generate, will have 4 total edges to add instead of 8
        int[][] test =new int[2][2];
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                test[i][j]=1;
            }
        }

        //Stores all possible graphs
        ArrayList <int[][]> possibleGraphs= new ArrayList<int[][]>();

        possibleGraphs=recursiveGenerate(possibleGraphs, test);
        printPossibleGraphs( possibleGraphs);

    }

    // Prints the edges in a readable format
    public static void printEdges(int[][] edges){
        for(int i=0;i<edges.length;i++){
            System.out.print("[");
            for(int j=0;j<2;j++){
                System.out.print(edges[i][j]);
                if(j==0) System.out.print(",");
            }
            System.out.print("] ");
        }
    }

    // Prints all graphs in possibleGraphs
    public static void printPossibleGraphs(ArrayList <int[][]> possibleGraphs){
        System.out.println("Graphs generated: "+ possibleGraphs.size());
       for(int i=0;i<possibleGraphs.size();i++){
            printEdges(possibleGraphs.get(i));
            System.out.print("\n");
       } 
    }

    // Creates a copy of an array to get around the pointer issue wI was having
    public static int[][] copyArr(int[][] srcArr){
        int[][] newArr = new int[srcArr.length][srcArr[0].length];
        for(int i=0;i<srcArr.length;i++){
            for(int j=0;j<2;j++){
                newArr[i][j]=srcArr[i][j];
            }
        }
        return newArr;
    }

//TODO: This method will be for generating the graphs, round 2 baby
    public static ArrayList <int[][]> iterGenerate(ArrayList <int[][]> possibleGraphs,int[][] edges){
        while(edges[edges.length-1][1]<5){
            
        }
        return possibleGraphs;
    }



    //REcursive attempt to generate, with helper
    public static ArrayList <int[][]> recursiveGenerate(ArrayList <int[][]> possibleGraphs,int[][] startEdges){
        int[] index = {0,0};
        helper(possibleGraphs,startEdges,index);
        return possibleGraphs;
    }

    private static void helper(ArrayList <int[][]> possibleGraphs,int[][] startEdges,int[] index){
        if(index[0]>=startEdges.length){
            System.out.println("aw yea");
        }else if(!(startEdges[index[0]][index[1]]==5)){

            possibleGraphs.add(copyArr(startEdges));
            startEdges[index[0]][index[1]]++;
            System.out.println(index[0]);
            helper(possibleGraphs,startEdges,index);

            if(index[1]==1){
                index[0]++;
                index[1]=0;
            }else{index[1]++;}
            System.out.println(index[0]);
            helper(possibleGraphs,startEdges,index);
            
        }
        
        
    }

    //TODO: This method will be for generating the graphs
    // inputs: a start arr of edges, and the arraylist
    // output: arraylist, to be used to test
    public static ArrayList <int[][]> generate(ArrayList <int[][]> possibleGraphs,int[][] startEdges){
        //First, I need a starting point for the array of edges
        // I'm going to try an iterative approach that first goes through all possible permutations
        // Checking each one, then seeing if I can scale it down

        // Used to keep an element the same
        int currentEdge=0;
        int currentElement=0;

        int[][] edges = startEdges;
        for(int i=0;i<startEdges.length;i++){
            for(int j=0;j<startEdges[i].length;j++){
                
                for(int k=1;k<=5;k++){
                    if(!(i==currentEdge)||!(j==currentElement)){
                        edges[i][j]=k;
                        //System.out.println(startEdges[0][0]);
                        possibleGraphs.add(copyArr(edges));
                    }
                    
                }
            }
            

        }

        return possibleGraphs;
    }
}