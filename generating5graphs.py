
# Function to initialize the lists as all 0's
def initListOfLists(list,size):
    i=0
    while(i<size):
        tempPair =[0,0]
        list.append(tempPair)
        i+=1

#Computes sums of vertices going in and out, same as in other file
def computeSums(edges,sumofedges):
    for i in edges:
        indexOut = i[0]-1
        indexIn = i[1]-1
        sumofedges[indexOut][1]+=i[1]  #Going out
        sumofedges[indexIn][0]+=i[0]   #Coming in

#computes the number of edges going in and out, same as sums, but without the edge weights
def computeInvsOut(edges,invsout):
    for i in edges:
        invsout[i[1]-1][0]+=1
        invsout[i[0]-1][1]+=1

#Checks if any edges have less than 3 coming in+going out, returns first one that is like that
#May need to change or add another method that makes sure 1 in each in and out
def checkInvsOut(invsout):
    valNeedsMore =0
    i=0
    while(i<5):
        if(invsout[i][0]+invsout[i][1]<3):
            valNeedsMore = i+1
            i+=5
        i+=1
    return valNeedsMore

#Focus on generating one graph first, then see how to generate more before stopping again
#After checking, check which one has too many, take one from there, replace the with the new
#one in the direction that has too many (so first, replace ones that start from 1, will need to
# be new method)
def generatePossibleGraph(edges,sumofedges,possibleGraphs, invsout):
    computeInvsOut(edges,invsout)
    print(checkInvsOut(invsout))

edges=[[1,2],[1,3],[1,4],[1,5],[2,1],[2,3],[2,4],[2,5]] #Stores all the edges
sumofedges=[] #stores sum of edges
initListOfLists(sumofedges,5)
computeSums(edges,sumofedges)

invsout=[] #stores edges coming in vs out
initListOfLists(invsout,5)

possibleGraphs=[] #stores all possible graphs that were made
generatePossibleGraph(edges,sumofedges,possibleGraphs,invsout)

#debugging
print(edges)
print(sumofedges)
print(invsout)