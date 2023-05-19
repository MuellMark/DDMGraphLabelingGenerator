import sys

# Reads in from a file and stores all the directinoal edges in a list of lists
def readFromFile(edges):
    commandFile = sys.argv[1]
    # Will need to change based on machine
    file = open('/Users/markymarkscomputer/Desktop/Untitled/known5Labelings/'+commandFile, 'r')
    content = file.readlines()

    for line in content:
        tempNode = [0,0]  #list that takes in a temp pairing
        index =0
        for i in line:
            # Based on the file structure, each line only has 2 ints
            if(i.isdigit()):
                tempNode[index]=int(i)
                index+=1
        edges.append(tempNode) #Appends to the list of edges

    file.close()

# Based on the list of edges, find the values of nodes going in and out from the nodes
def getSums(edges,sumsPerEdge):
    #Init the sums list to have 5 empty "tuples"
    i=0
    while(i<5):
        tempPair =[0,0]
        sumsPerEdge.append(tempPair)
        i+=1

    # For each edge, add the value of the node going in and coming out
    for i in edges:
        indexOut = i[0]-1
        indexIn = i[1]-1
        sumsPerEdge[indexOut][1]+=i[1]  #Going out
        sumsPerEdge[indexIn][0]+=i[0]   #Coming in
        # Debugging Statements
        # print(i)
        # print(sumsPerEdge)
    

#Verifies that the values of nodes going in is the same as the nodes going out
def verifier(sumsPerEdge):
    valid = True
    for i in sumsPerEdge:
        if(i[0] != i[1]):
            valid= False
            break
    return valid

# "Main Method" calls all the other funcitons

edges=[] #Stores all the edges
readFromFile(edges)

sumsPerEdge=[]  #Stores all the in/out values
getSums(edges,sumsPerEdge)
print()
print(verifier(sumsPerEdge)) #prints if it is a valid labeling