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

    if(numVertices==5):
        G.add_node(1,pos=(2,1))
        G.add_node(2,pos=(4,1))
        G.add_node(3,pos=(1,2))
        G.add_node(4,pos=(5,2))
        G.add_node(5,pos=(3,3))
    elif(numVertices==6):
        G.add_node(1,pos=(1,2))
        G.add_node(2,pos=(2,1))
        G.add_node(3,pos=(3,2))
        G.add_node(4,pos=(3,3))
        G.add_node(5,pos=(2,4))
        G.add_node(6,pos=(1,3))
    pos=nx.get_node_attributes(G,'pos')

    print(tempList)
    G.add_edges_from(tempList)
    plt.title(str(numVertices)+" Vertex Graph #"+str(graphnum))
    nx.draw(G,pos,node_color='white',with_labels=True)

    # Need to change save location based on what is generated
    plt.savefig("/Users/markymarkscomputer/Desktop/Untitled/GraphVisualizations/5VertexNoInverse/graph"+str(graphnum)+".jpeg")
    plt.clf()
    G.clear()
    graphnum+=1

#plt.savefig("/Users/markymarkscomputer/Desktop/Untitled/GraphVisualizations/5VertexNoInverse/graph"+str(graphnum)+".jpeg")
