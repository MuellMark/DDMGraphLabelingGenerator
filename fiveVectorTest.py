
def readFromFile(edges):
    # Will need to change for machine
    file = open('/Users/markymarkscomputer/Desktop/Untitled/fiveVec1.txt', 'r')
    content = file.readlines()

    for line in content:
        tempNode = [0,0]  #list that takes in a temp pairing
        index =0
        for i in line:
            if(i.isdigit()):
                tempNode[index]=int(i)
                index+=1
        edges.append(tempNode) #Appends to the list of edges

    file.close()

def getSums(edges,sumsPerEdge):
    #Init the sums list to have 5 empty "tuples"
    i=0
    while(i<5):
        tempPair =[0,0]
        sumsPerEdge.append(tempPair)
        i+=1

    for i in edges:
        indexOut = i[0]-1
        indexIn = i[1]-1
        sumsPerEdge[indexOut][1]+=i[1]
        sumsPerEdge[indexIn][0]+=i[0]
        # Debugging Statements
        # print(i)
        # print(sumsPerEdge)
    

#Will verify once the other list is filled in
def verifier(sumsPerEdge):
    valid = True
    for i in sumsPerEdge:
        if(i[0] != i[1]):
            valid= False
            break
    return valid


edges=[] #Stores all the edges
readFromFile(edges)
#print(edges) 

sumsPerEdge=[]
getSums(edges,sumsPerEdge)
print(sumsPerEdge) 
print(verifier(sumsPerEdge))