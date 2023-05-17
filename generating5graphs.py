
# Function to initialize the lists as all 0's
def initListOfLists(list,size):
    i=0
    while(i<size):
        tempPair =[0,0]
        list.append(tempPair)
        i+=1

def generatePossibleGraph(edges,sumofedges,possibleGraphs):
    for i in edges:
        if(i[0]==0 or i[1]==0):
            i[0]=1
            i[1]=2

edges=[] #Stores all the edges
initListOfLists(edges,8) #For now, assume min and max edges is 8
sumofedges=[]
initListOfLists(sumofedges,5)

possibleGraphs=[] #stores all possible graphs that were made
generatePossibleGraph(edges,sumofedges,possibleGraphs)
print(edges)