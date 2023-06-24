# Legacy Code

The legacy code folder is used to document past files and ideas that, for some reason or another, didn't end up working. There are 3 main sections for the legacy code:
the initial python code, the java code and the visualization code

## Python Code
This was the start of the project, the files for generating the graphs were originally written in python, There are 2 main files here, 5VectorTest and generating5Graphs
5Vector test was solely used to check if a given labeling was a ddm labeling. The two other folders are there as test cases. From there, generating5graphs was made 
where I attempted to generate all of the 5 graphs via an optimized brute force approach. However, after some testing I knew python would be difficult to work with the 
lack of declared variables, so I shifted my focus over to generating code in Java

## Java Code
There are by far the most legacy files here. The first files I made after switching from python were the LEGACYiterComboGeneratorTest and the javaGen5Graphs. The iter combo file was first but they both effectively try to do the same thing: Find all of the combinations via brute force. The iter combo was the first test to see how the code would look for a case with only 2 edges and the Gen 5 Graphs had all of the code for the 8 edges, but it would have been way too costly to pursue a brute force approach, even with potential optimizations. Therefore, both of these files were scrapped.

The next stepping stone was LEGACYGenerate5GraphCombos. This was where it was thought about algorithmically, and where the current graph generation code came from. It was a test of the algorithm where I realized I’d need an array list to store all of the potential graphs, and that I’d need a class for storing a graph. This was where edgeStorage was created, a class of graphs using an array of arrays of arrayLists to store the contents of a single graph. Its predecessors, testEdges and test6Edges are here too, they are essentially the same thing but without some of the method. They were used for testing before they were consolidated into one class. This was first utilized by the generateGraphsUnOptimized code, where it worked. This was able to generate all of the graphs up to 8 vertices. I then hit a roadblock not being able to generate the 9 graphs. This made me try and optimize the code. The first optimization, called generateGraphsOptimizationTest, changed how graphs were tested for whether or not they were ddm. This made the code about twice as efficient. These were then all later scrapped due to the array based implementation, as it was faster. However, these are all still viable options that do the task at hand.

The final 2 files here, the .class and the .txt are relics from different runs. The .class was added to the code after I tried to run it from the command line, so it is useless now. The txt is used to store the output from various java code, if they are run again.


## Visualization Code
There isn't very much in this folder. The 3 folders are a java version of graph generation tools that I ended up scrapping called Graph Stream. Because I wrote all of my functional code in java, I thought it would be best to generate graphs in java too. However, many were incompatible with vscode, the IDE I enjoy coding in the best, and trying to transfer the code over to eclipse was causing a bunch of errors. The GraphStreamTest.java file was my experimentation with the 3 folders, but it didn't work at all. That's when I decided to transition over to python, since I knew it was better for this sort of thing. I used networkX to generate the graphs, and the 2 png's in this folder are from initial tests of the code to see how I could apply it to mine. The last txt file was used to get the formatting down for the java code's write to files.
