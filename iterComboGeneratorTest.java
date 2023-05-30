import java.util.ArrayList;
import java.util.Arrays;
class iterComboGeneratorTest{
    public static void main (String []args){
        int[] test = {1,1,1,1};
        ArrayList <int[]> combos= new ArrayList<int[]>();
        generate(combos, test);
        //printCombos(combos);
    }

    public static void generate(ArrayList <int[]> combos,int[] test){
        int index=0;
        int adding=0;
        while(!checkAllFives(test, test.length-1)){ //Will need to change
            for(int i=0;i<test.length;i++){
                System.out.print(test[i]+",");
            }
            System.out.println();
            combos.add(copyArr(test));
            System.out.println(combos.size());
            if(checkAllFives(test, index)){
                System.out.print("happens");
                index++;
                test[index]++;
                adding=0;
                for(int i=0;i<index;i++){
                    test[i]=1;
                }

            }else if(test[adding]<5){
                
                test[adding]++;
            }else{
                // need a for loop
                while(test[adding+1]==5){
                    adding++;
                }
                
               if(adding<index){
                test[adding+1]++;
                for(int i=0;i<adding+1;i++){
                   test[i]=1;
               }
               adding=0;
               }
                 
            }
            
            //System.out.println(test[0]);
            
        }
    }

    public static boolean checkAllFives(int[]arr, int end){
        for(int i=0;i<=end;i++){
            if(!(arr[i]==5)) return false;
        }
        return true;
    }

    public static int[] copyArr(int[] srcArr){
        int[] newArr = new int[srcArr.length];
        for(int i=0;i<srcArr.length;i++){
            newArr[i]=srcArr[i];
        }
        return newArr;
    }

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