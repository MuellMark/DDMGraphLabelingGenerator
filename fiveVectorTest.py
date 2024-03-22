
# Will need to change for machine
file = open('/Users/markymarkscomputer/Desktop/Untitled/fiveVec1.txt', 'r')
content = file.readlines()


for line in content:
    tempNode = [0,0]
    index =0
    for i in line:
        if(i.isdigit()):
            tempNode[index]=i
            index+=1
    print(tempNode)
        

         
    
 
file.close()