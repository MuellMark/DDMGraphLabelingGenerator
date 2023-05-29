class javaGen5Graphs{
    public static void main (String []args){
        int[][] edges =new int[8][2];
        for(int i=0;i<8;i++){
            for(int j=0;j<2;j++){
                edges[i][j]=1;
            }
        }
        // first have an array of size 2 that stores an edge 
        // Them have an array of size 8 storing the edges, 8 needs to be a var
        // Then have an array list to hold all graphs, then go from there
        //System.out.print("ASDFGSDA\n");
        printEdges(edges);
    }

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
}