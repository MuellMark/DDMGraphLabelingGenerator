import networkx as nx
import matplotlib.pyplot as plt

# Change here depending on what needs to be generated
numVertices = 5
inverses = False

graphnum=1 #Tracks graph number for file

graphsToGenerate= open("/Users/markymarkscomputer/Desktop/Untitled/outputForVis.txt",'r')
contents = graphsToGenerate.readlines()
for line in contents:
    index =0
    tempList=[]
    G = nx.DiGraph()
    while index<len(line):
        #Need functionality for naming maybe
        if line[index].isdigit():
            ifrom=int(line[index])
            index+=2
            ito=int(line[index])
            tempTuple = (ifrom,ito)
            tempList.append(tempTuple)
        index+=1

    print(tempList)
    G.add_edges_from(tempList)
    plt.title(str(numVertices)+" Vertex Graph #"+str(graphnum))
    nx.draw(G,node_color='white',with_labels=True)
    plt.savefig("/Users/markymarkscomputer/Desktop/Untitled/GraphVisualizations/5VertexNoInverse/graph"+str(graphnum)+".jpeg")
    plt.clf()
    G.clear()
    graphnum+=1

#plt.savefig("/Users/markymarkscomputer/Desktop/Untitled/GraphVisualizations/5VertexNoInverse/graph"+str(graphnum)+".jpeg")
