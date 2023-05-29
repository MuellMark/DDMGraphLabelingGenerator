import java.util.ArrayList;
import java.util.Arrays;
class iterComboGeneratorTest{
    public static void main (String []args){
        int[] test = {1,1,1,1};
        ArrayList <int[]> combos= new ArrayList<int[]>();
        generate(combos, test);
        printCombos(combos);
    }

    public static void generate(ArrayList <int[]> combos,int[] test){
        int index=0;
        int adding=0;
        while(test[3]<5){ //Will need to change
            combos.add(copyArr(test));

            if(adding==index &&test[adding]==5){
                
                index++;
                test[index]++;
                adding=0;
                for(int i=0;i<index;i++){
                    test[i]=1;
                }

            }

            if(test[adding]<5){
                test[adding]++;
            }else{ adding++;}

            //System.out.println(test[0]);
            
        }
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