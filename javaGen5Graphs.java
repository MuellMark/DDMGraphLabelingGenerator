import java.util.ArrayList;
import java.util.Arrays;
//Need to make sure everything works for larger graphs
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

        //possibleGraphs=recursiveGenerate(possibleGraphs, test);
        printPossibleGraphs( possibleGraphs);

        //testing converts
        int[] tester= {1,2,3,4,6,12};
        int[][] test2D = convertTo2D(tester);
        printEdges(test2D);
        int[]  test2 =convertTo1D(test2D);
        print1DArray(test2);

    }

    //conerts from 1d to 2d
    public static int[][] convertTo2D(int[] arr){
        int[][] new2Darr = new int[arr.length/2][2];
        int i2D=0;
        int i=0;
        while(i<arr.length){
            new2Darr[i2D][0]=arr[i];
            i++;
            new2Darr[i2D][1]=arr[i];
            i++;
            i2D++;
        }
        return new2Darr;
    }
     //conerts from 2d to 1d
     public static int[] convertTo1D(int[][] arr2D){
        int[] newArr = new int[arr2D.length*2];
        int i2D=0;
        int i=0;
        while(i2D<arr2D.length){
            newArr[i]=arr2D[i2D][0];
            i++;
            newArr[i]=arr2D[i2D][1];
            i++;
            i2D++;
        }
        return newArr;
    }

    public static void print1DArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+",");
        }
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
        
        return possibleGraphs;
    }




//Failed attempts 

//     //REcursive attempt to generate, with helper
//     public static ArrayList <int[][]> recursiveGenerate(ArrayList <int[][]> possibleGraphs,int[][] startEdges){
//         int[] index = {0,0};
//         helper(possibleGraphs,startEdges,index);
//         return possibleGraphs;
//     }

//     private static void helper(ArrayList <int[][]> possibleGraphs,int[][] startEdges,int[] index){
//         if(index[0]>=startEdges.length){
//             System.out.println("aw yea");
//         }else if(!(startEdges[index[0]][index[1]]==5)){

//             possibleGraphs.add(copyArr(startEdges));
//             startEdges[index[0]][index[1]]++;
//             System.out.println(index[0]);
//             helper(possibleGraphs,startEdges,index);

//             if(index[1]==1){
//                 index[0]++;
//                 index[1]=0;
//             }else{index[1]++;}
//             System.out.println(index[0]);
//             helper(possibleGraphs,startEdges,index);
            
//         }
        
        
//     }

//     //TODO: This method will be for generating the graphs
//     // inputs: a start arr of edges, and the arraylist
//     // output: arraylist, to be used to test
//     public static ArrayList <int[][]> generate(ArrayList <int[][]> possibleGraphs,int[][] startEdges){
//         //First, I need a starting point for the array of edges
//         // I'm going to try an iterative approach that first goes through all possible permutations
//         // Checking each one, then seeing if I can scale it down

//         // Used to keep an element the same
//         int currentEdge=0;
//         int currentElement=0;

//         int[][] edges = startEdges;
//         for(int i=0;i<startEdges.length;i++){
//             for(int j=0;j<startEdges[i].length;j++){
                
//                 for(int k=1;k<=5;k++){
//                     if(!(i==currentEdge)||!(j==currentElement)){
//                         edges[i][j]=k;
//                         //System.out.println(startEdges[0][0]);
//                         possibleGraphs.add(copyArr(edges));
//                     }
                    
//                 }
//             }
            

//         }

//         return possibleGraphs;
//     }
 }