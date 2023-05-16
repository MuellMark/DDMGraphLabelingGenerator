
# Will need to change for machine
file = open('/Users/markymarkscomputer/Desktop/Untitled/fiveVec1.txt', 'r')
content = file.readlines()

edges=[]

for line in content:
    tempNode = [0,0]
    index =0
    for i in line:
        if(i.isdigit()):
            tempNode[index]=int(i)
            index+=1
    #print(tempNode)
    edges.append(tempNode)
print(edges)   

         
    
 
file.close()