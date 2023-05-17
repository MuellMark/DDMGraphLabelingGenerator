
# Function to initialize the lists as all 0's
def initListOfLists(list,size):
    i=0
    while(i<size):
        tempPair =[0,0]
        list.append(tempPair)
        i+=1

def computeSums(edges,sumofedges):
    for i in edges:
        indexOut = i[0]-1
        indexIn = i[1]-1
        sumofedges[indexOut][1]+=i[1]  #Going out
        sumofedges[indexIn][0]+=i[0]   #Coming in

def computeInvsOut(edges,invsout):
    for i in edges:
        invsout[i[1]-1][0]+=1
        invsout[i[0]-1][1]+=1

def checkInvsOut(invsout):
    valNeedsMore =0
    i=0
    while(i<5):
        if(invsout[i][0]+invsout[i][1]<3):
            valNeedsMore = i+1
            i+=5
        i+=1
    return valNeedsMore


def generatePossibleGraph(edges,sumofedges,possibleGraphs, invsout):
    computeInvsOut(edges,invsout)
    print(checkInvsOut(invsout))

edges=[[1,2],[1,3],[1,4],[1,5],[2,1],[2,3],[2,4],[2,5]] #Stores all the edges
sumofedges=[]
initListOfLists(sumofedges,5)
computeSums(edges,sumofedges)

invsout=[]
initListOfLists(invsout,5)

possibleGraphs=[] #stores all possible graphs that were made
generatePossibleGraph(edges,sumofedges,possibleGraphs,invsout)
print(edges)
print(sumofedges)
print(invsout)