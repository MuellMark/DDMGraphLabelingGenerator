import java.util.ArrayList;
class generateCirculantGraphs{
    public static void main(String[]args){
        // Specifies size of graph
        int numVertices =7;

        // Used to generate given circulant graph
        int a=2;
        int b=3;

        //Stores all ddm labelings of a given Circualnt graph
        ArrayList<edgeStorageArrays> allCombos = new ArrayList<>();

        // Stores original starter Circulant graph
        edgeStorageArrays startCirGraph = new edgeStorageArrays(numVertices);
        createCirculantGraphs(numVertices, a, b, startCirGraph);
        startCirGraph.printAdjMatrix();
    }

    // Generates the starting circulant graph based on the number of vertices, a and b
    public static void createCirculantGraphs(int numVertices,int a, int b, edgeStorageArrays startCirGraph){
        if(a>=b) System.out.println("Invalid Condidtions, a must be less than b");
        if(numVertices<5) System.out.println("Invalid Condidtions, numVertices must be equal to 5 or more");
        if(b>=numVertices+1/2) System.out.println("Invalid Condidtions, a+b<=numVertices/2");
        else{
            System.out.println("Conditions met, generating the graph");
            for(int i=0;i<numVertices;i++){
                //Makes cycle, added
                startCirGraph.addPair(i+1, ((i+a)%numVertices)+1);
                startCirGraph.addPair(((i+a)%numVertices)+1, i+1);
                // Makes cycle, subtracted, 
                startCirGraph.addPair(i+1, ((i+numVertices-a)%numVertices)+1);
                startCirGraph.addPair(((i+numVertices-a)%numVertices)+1, i+1);

            }
        }
    }
}