import java.util.ArrayList;
import java.util.Arrays;
class iterComboGeneratorTest{
    public static void main (String []args){
        int[] test = {1,1,1,1};
        ArrayList <int[]> combos= new ArrayList<int[]>();
        generate(combos, test);
        printCombos(combos);
        System.out.println(combos.size());
        }

    // Generates all of the permutations for 4
    public static void generate(ArrayList <int[]> combos,int[] test){
        int index=0; //Keeps track of current position, only goes up
        int adding=0; //Keeps track of where it will be added
        // Goes until all are 5's
        while(!checkAllFives(test, test.length-1)){ 
            // Prints arrays and number added, for debugging
            // for(int i=0;i<test.length;i++){
            //     System.out.print(test[i]+",");
            // }
            // System.out.println();
            combos.add(copyArr(test)); // Adds to array list
            // System.out.println(combos.size());

            // 3 cases: 1st checks if all are 5's upto index. If it is,
            // Then increases index (and it's position), and clears everything before it to 1's
            if(checkAllFives(test, index)){
                //System.out.print("happens"); //debugging
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
        }
    }

    // Checks if all entries are 5 upto a certain index in an arr. If it is, return true
    // else return false
    public static boolean checkAllFives(int[]arr, int end){
        for(int i=0;i<=end;i++){
            if(!(arr[i]==5)) return false;
        }
        return true;
    }

    // copies array into a new array for the list
    public static int[] copyArr(int[] srcArr){
        int[] newArr = new int[srcArr.length];
        for(int i=0;i<srcArr.length;i++){
            newArr[i]=srcArr[i];
        }
        return newArr;
    }

    // Prints all combos in the array list
    public static void printCombos(ArrayList <int[]> combos){
        System.out.println("Combos generated: "+ combos.size());
       for(int i=0;i<combos.size();i++){
            for(int j=0;j<combos.get(i).length;j++){
                System.out.print(combos.get(i)[j]);
            }
            System.out.println();
       } 
    }
}