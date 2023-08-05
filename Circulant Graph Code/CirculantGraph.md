# Circulant Graph Code
There are lots of files here that work together to generate Circulant Graphs with ddm labelings. This part of the code uses Maven, so make sure you make the necessary arrangements in your code if you want to run it yourself. Troubleshooting links are on the ReadMe. Other than that, there are 2 main parts to the code:

## Java Code
As stated above, the code is run through Maven, which is why there is a circulantgraphs folder. To get to the actual code you'll go circulantgraphs->src->main->java->graphlabeling->circulantgraphs->ddmLabelings. Here, there are 3 files. edgeStorageArrays and generateGraphs are here to give structure and generateCirculantGraphs is used to generate the circulant graphs. Maven was used to add a package to generate subsets for the initial batch of generations. Basically, for any given start point, there are only so many options for the start of the generation because all edges must have degree 4. From there, the set changes to allow for all edges again. This code is currently very inefficient, as it checks thousands of graphs that it may not have to. Only graphs with 6 vertices were able to be run and it takes several minutes to finish. More optimizations and further research is being done to improve it.

Side note. Because the other code is here, it can be run in Maven, which makes it run more reliably if there are any issues with the Java Code

## Python Code
The Python code is currently not working and is still under development. If you want to visualize the graphs, you'll have to do it with the original visualize graphs code found in the Python Code folder. Eventually, this function will be supported, but there are still challenges being worked through to allow for uniformity among the generated graphs.



