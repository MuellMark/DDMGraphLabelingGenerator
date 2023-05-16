# Demonstrated Python Program
# to read file character by character
 
 
file = open('/Users/markymarkscomputer/Desktop/Untitled/fiveVec1.txt', 'r')
char = file.read(1)
while (char):
    if(char.isdigit()):
        print(char)
    # read by character
    char = file.read(1)         

         
    
 
file.close()