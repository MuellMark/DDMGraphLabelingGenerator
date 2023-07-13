import java.util.ArrayList;
class generateCirculantGraphs{
    public static void main(String[]args){
        // Specifies size of graph
        int numVertices =6;

        // Used to generate given circulant graph
        int a=1;
        int b=2;

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
        if(a+b>numVertices/2) System.out.println("Invalid Condidtions, a+b<=numVertices/2");
        else{
            System.out.println("Conditions met, generating the graph");
            for(int i=0;i<numVertices;i++){
                //Makes cycle
                startCirGraph.addPair(i+1, ((i+1)%numVertices)+1);
                startCirGraph.addPair(((i+1)%numVertices)+1, i+1);
            }
        }
    }
}