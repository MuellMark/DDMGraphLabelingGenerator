import networkx as nx
import matplotlib.pyplot as plt

# Change number of vertices here
numVertices = 7

#File that all the graphs are stored in, change based on where storing the files
graphsToGenerate= open("/Users/markymarkscomputer/Desktop/Untitled/Python Code/outputForCirVis.txt",'r')

#File path and name for where all graphs will be changed. Keep /graph at end
fileName = "/Users/markymarkscomputer/Desktop/Untitled/GraphVisualizations/Circulant Graphsgraph"

contents = graphsToGenerate.readlines()

graphnum=1 #Tracks graph number for file

#Goes through each graph in the file
for line in contents:
    index =0
    tempList=[]
    G = nx.DiGraph() #Makes graph
    while index<len(line):
        #Gets all edges and puts them in a list
        if line[index].isdigit():
            ifrom=int(line[index])
            index+=2
            ito=int(line[index])
            tempTuple = (ifrom,ito)
            tempList.append(tempTuple)
        index+=1

    #TODO: could make this an algorithm
    #Locks vertices in place, one case for each number of graph
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
    elif(numVertices==7):
        G.add_node(1,pos=(2,1))
        G.add_node(2,pos=(4,1))
        G.add_node(3,pos=(5,2))
        G.add_node(4,pos=(5,3))
        G.add_node(5,pos=(3,4))
        G.add_node(6,pos=(1,3))
        G.add_node(7,pos=(1,2))
    elif(numVertices==8):
        G.add_node(1,pos=(2,1))
        G.add_node(2,pos=(3,1))
        G.add_node(3,pos=(4,2))
        G.add_node(4,pos=(4,3))
        G.add_node(5,pos=(3,4))
        G.add_node(6,pos=(2,4))
        G.add_node(7,pos=(1,3))
        G.add_node(8,pos=(1,2))
    pos=nx.get_node_attributes(G,'pos')

    # Used to keep track of the graphs it is visualizing
    print(tempList)

    G.add_edges_from(tempList) #Adds edges to the graph object
    plt.title(str(numVertices)+" Vertex Graph #"+str(graphnum)) # adds title
    nx.draw(G,pos,node_color='white',with_labels=True) #Draws the graph based on edge list and positions

    # Saves the figure to a specified path, change based on where you want them saved
    plt.savefig(fileName+str(graphnum)+".jpeg")
    plt.clf() # clears matplotlib
    G.clear() # clears graph object
    graphnum+=1 