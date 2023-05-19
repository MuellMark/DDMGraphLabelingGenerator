
# Function to initialize the lists as all 0's
def initListOfLists(list,size):
    i=0
    while(i<size):
        tempPair =[0,0]
        list.append(tempPair)
        i+=1

def clearInVsOut(invsout):
    for i in invsout:
        i[0]=0
        i[1]=0

#Computes sums of vertices going in and out, same as in other file
def computeSums(edges,sumofedges):
    for i in edges:
        indexOut = i[0]-1
        indexIn = i[1]-1
        sumofedges[indexOut][1]+=i[1]  #Going out
        sumofedges[indexIn][0]+=i[0]   #Coming in

#computes the number of edges going in and out, same as sums, but without the edge weights
def computeInvsOut(edges,invsout):
    clearInVsOut(invsout)
    for i in edges:
        #print(edges)
        invsout[i[1]-1][0]+=1
        invsout[i[0]-1][1]+=1

#Checks if any edges have less than 3 coming in+going out, returns first one that is like that
#May need to change or add another method that makes sure 1 in each in and out
def checkInvsOut(invsout):
    valNeedsMore =0
    i=0
    while(i<5): #Constant, to be changed for more vertices
        if(invsout[i][0]+invsout[i][1]<4):
            valNeedsMore = i+1
            i+=5
        i+=1
    # print("insvsouts:")
    # print(invsout)
    # print(valNeedsMore)
    return valNeedsMore

#Finds all possible edges with 4 or more connections, returns list of all possible
#edges to change
def augmentEdges(invsout, edges):
    valToChange = []
    i=0
    while(i<5): 
        if(invsout[i][0]+invsout[i][1]>2):
            valToChange.append(i+1)
        i+=1
    return valToChange

def checkRepeatGraph(edges,invalidGraphs):
    newElement = True
    graph =0
    while(graph<len(invalidGraphs)):
        if(edges==invalidGraphs[graph]):
            newElement=False
            graph+=len(invalidGraphs)
    return newElement


       

#Focus on generating one graph first, then see how to generate more before stopping again
#After checking, check which one has too many, take one from there, replace the with the new
#one in the direction that has too many (so first, replace ones that start from 1, will need to
# be new method)
def generatePossibleGraph(edges,sumofedges,possibleGraphs, invsout, invalidGraphs):
    computeInvsOut(edges,invsout)
    val =checkInvsOut(invsout)
    if(val ==  0):
        print("Found: ")
        print(edges)
    else:
        verticesToChange = augmentEdges(invsout, edges)
        i=0
        j=0
        while(i<len(edges)):
            # print(val) #debugging
            while(j<len(verticesToChange)):
                if(edges[i][0]==verticesToChange[j]):
                    print("First "+str(edges))
                    edges[i][0]=val
                    print("after "+str(edges))
                    newGraph=checkRepeatGraph(edges,invalidGraphs)
                    if(newGraph):
                        print("added to invalid: "+ str(edges))
                        invalidGraphs.append(edges)
                        print("\n INVALID:"+str(invalidGraphs))
                        generatePossibleGraph(edges,sumofedges,possibleGraphs, invsout, invalidGraphs)
                elif(edges[i][1]==verticesToChange[j]):
                    print("First "+str(edges))
                    edges[i][1]=val
                    print("after "+str(edges))
                    newGraph=checkRepeatGraph(edges,invalidGraphs)
                    if(newGraph):
                        print("happens")
                        invalidGraphs.append(edges)
                        generatePossibleGraph(edges,sumofedges,possibleGraphs, invsout, invalidGraphs)
                j+=1
            i+=1

        # Will loop thru edges, first edge that it finds with more than 4 going in and out replace

edges=[[1,2],[1,3],[1,4],[1,5],[2,1],[2,3],[2,4],[2,5]] #Stores all the edges
sumofedges=[] #stores sum of edges
initListOfLists(sumofedges,5)
computeSums(edges,sumofedges)

invsout=[] #stores edges coming in vs out
initListOfLists(invsout,5)

possibleGraphs =[] #stores all possible graphs that were made
invalidGraphs = [] #Stores all invalid graphs for chekcing, may need to be a dict
generatePossibleGraph(edges,sumofedges,possibleGraphs,invsout,invalidGraphs)

#debugging
# print("edges: "+str(edges))
# print(sumofedges)
# print(invsout)
print("\n INVALID:"+str(invalidGraphs))
test = [3,4,5]
invalidGraphs.append(test)
test.append(7)
print(invalidGraphs)