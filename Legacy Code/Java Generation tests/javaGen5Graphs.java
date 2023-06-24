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
        // int[][] test =new int[2][2];
        // for(int i=0;i<2;i++){
        //     for(int j=0;j<2;j++){
        //         test[i][j]=1;
        //     }
        // }
       //int[] startarr = convertTo1D(test); // Will be used as start of generate

        //Stores all possible graphs
        ArrayList <int[][]> possibleGraphs= new ArrayList<int[][]>();
        int[] test = {1,1,1,1};
        
        generate(possibleGraphs, test);

        printPossibleGraphs( possibleGraphs);
    }

    //conerts from 1d array to 2d array for storing in list
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

     //conerts from 2d to 1d for starting the generate method
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

    
    // Prints the edges in a readable format (2D array)
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

    // Prints all graphs in possibleGraphs (arraylist of the 2D arrays)
    public static void printPossibleGraphs(ArrayList <int[][]> possibleGraphs){
       for(int i=0;i<possibleGraphs.size();i++){
            printEdges(possibleGraphs.get(i));
            System.out.print("\n");
       } // Moved to bottom incase too many were made
       System.out.println("Graphs generated: "+ possibleGraphs.size());
    }

    // Creates a copy of a 2D array to get around the pointer issue I was having
    public static int[][] copyArr(int[][] srcArr){
        int[][] newArr = new int[srcArr.length][srcArr[0].length];
        for(int i=0;i<srcArr.length;i++){
            for(int j=0;j<2;j++){
                newArr[i][j]=srcArr[i][j];
            }
        }
        return newArr;
    }

    // Generates all of the permutations the graph, brute force style
    public static void generate(ArrayList <int[][]> combos,int[] test){
        int index=0; //Keeps track of current position, only goes up
        int adding=0; //Keeps track of where it will be added
        // Goes until all elements are 5's
        while(!checkAllFives(test, test.length-1)){ // fence post problem, maybe fix later
            // adds array to list
            combos.add(copyArr(convertTo2D(test)));

            // 3 cases: 1st checks if all are 5's upto index. If it is,
            // Then increases index (and it's position), and clears everything before it to 1's
            if(checkAllFives(test, index)){
                index++;
                test[index]++;
                adding=0;
                //Clears all before it
                for(int i=0;i<index;i++){
                    test[i]=1;
                }

            // Second case, if the position adding is at is less than one, incremenet
            }else if(test[adding]<5){
                test[adding]++;

            // Last case, all 5's at position 1, go up and see if it's 5. If it isnt, incremenet 
            // And reset all before it to 1. If the next is 5, keep going until either the end
            // Or a non-5 number is found. Incemrenet that and reset all before it
            }else{
                // Checks for next non-5
                while(test[adding+1]==5){
                    adding++;
                }
                // makes sure it's not the same as a previous case
               if(adding<index){
                test[adding+1]++;
                for(int i=0;i<adding+1;i++){
                   test[i]=1;
               }
               adding=0;
               }   
            }
            checkForLoops(test);
        }
    }

    // If there is a loop, increment the element in the first position
    public static void checkForLoops(int[]arr){
        for(int i=0;i<arr.length;i+=2){
            if(arr[i]==arr[i+1]){
                arr[i]++;
            }
        }
    }
    // Checks if all entries are 5 upto a certain index in an arr. If it is, return true
    // else return false, for generate
    public static boolean checkAllFives(int[]arr, int end){
        for(int i=0;i<=end;i++){
            if(!(arr[i]>=5)) return false;
        }
        return true;
    }

    // copies 1D array into a new array, not needed, keeping just incase, as copying a 1D array could be needed
    public static int[] copyArr(int[] srcArr){
        int[] newArr = new int[srcArr.length];
        for(int i=0;i<srcArr.length;i++){
            newArr[i]=srcArr[i];
        }
        return newArr;
    }



    // Used for debugging, to check 1D arrays
    public static void print1DArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+",");
        }   
    }

 }