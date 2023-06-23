# Legacy Code

The legacy code folder is used to document past files and ideas that, for some reason or another, didn't end up working. There are 3 main sections for the legacy code:
the initial python code, the java code and the visualization code

## Python Code
This was the start of the project, the files for generating the graphs were originally written in python, There are 2 main files here, 5VectorTest and generating5Graphs
5Vector test was solely used to check if a given labeling was a ddm labeling. The two other folders are there as test cases. From there, generating5graphs was made 
where I attempted to generate all of the 5 graphs via an optimized brute force approach. However, after some testing I knew python would be dificult to work with the 
lack of declared variables, so I shifted my focus over to generating code in Java

## Java Code
TODO

## Visualization Code
There isn't very much in this folder. The 3 folders are a java version of graph generation tools that I ended up scrapping called Graph Stream. Because I wrote all of my functinoal code in java, I thought it would be best to generate graphs in java too. However, many were incompatible with vscode, the IDE I enjoy coding in the best, and trying to transfer the code over to eclipse was causing a bunch of errors. The GraphStreamTest.java file was my experimentation with the 3 folders, but it didn't work at all. That's when I decided to transition over to python, since I knew it was better for this sort of thing. I used networkX to generate the graphs, and the 2 png's in this folder are from initial tests of the code to see how I could apply it to mine. The last txt file was used to get the formatting down for the java code's write to files.
