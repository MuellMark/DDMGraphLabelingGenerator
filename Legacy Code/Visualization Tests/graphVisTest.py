# First networkx library is imported
# along with matplotlib
import networkx as nx
import matplotlib.pyplot as plt

graphNum=1

testGraphs = open("/Users/markymarkscomputer/Desktop/Untitled/graphVisTestGraph.txt",'r')
contents = testGraphs.readlines()
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

    G.add_node(1,pos=(2,1))
    G.add_node(2,pos=(4,1))
    G.add_node(3,pos=(1,2))
    G.add_node(4,pos=(5,2))
    G.add_node(5,pos=(3,3))
    pos=nx.get_node_attributes(G,'pos')

    print(tempList)
    G.add_edges_from(tempList)
    plt.title("5 Vertex Graph #[number]")
    nx.draw(G,pos,node_color='white',with_labels=True,)
    plt.savefig("/Users/markymarkscomputer/Desktop/Untitled/graphVisTest.png")
    plt.clf()
    G.clear()
    #From here, add it to a list, to the be added to G

#G = nx.DiGraph()
# G.add_edges_from([(1, 2), (1, 3),(2,5),(3,5),(4,2),(4,3),(5,1),(5,4)])

# #G = nx.complete_graph(5)
# plt.title("5 Vertex Graph #[number]")
# nx.draw(G,node_color='white',with_labels=True)
# plt.savefig("/Users/markymarkscomputer/Desktop/Untitled/graphVisTest.png")
# plt.clf()
# G.clear()
# G.add_edges_from(list)
# nx.draw(G,node_color='white',with_labels=True)
# plt.savefig("/Users/markymarkscomputer/Desktop/Untitled/graphVisTest2.png")