import networkx as nx
import matplotlib.pyplot as plt

# Change here depending on what needs to be generated
numVertices = 5
inverses = False

graphnum=1 #Tracks graph number for file

graphsToGenerate= open("/Users/markymarkscomputer/Desktop/Untitled/graphVisTestGraph.txt",'r')
contents = graphsToGenerate.readlines()


#plt.savefig("/Users/markymarkscomputer/Desktop/Untitled/GraphVisualizations/5VertexNoInverse/graph"+str(graphnum)+".jpeg")
