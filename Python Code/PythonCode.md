# PythonCode
The python code is responsible for running the code for visualizing the graphs. To do this you'll need an file to plug into to python code, make sure to include your computer's file structure

## VisualizeGraphs
This is the only python program in here at the moment. To visualize the graphs you'll need an output file from running the correct java code, but you can use the file here as a tester. In the python code, you can change the number of vertices for your graphs if you choose to visualize a different size of graphs by changing numVertices. Again, only 5-8 will work. Make sure this corresponds to the number of vertices the code had when you generated them in the java code, otherwise the code will not work right. Then, you'll have to change your file location in the next line of code down. And finally, before you run the code you'll have to change the save location located near the bottom of the file called in plt.savefig. Once these are in place, you can run the code and it will generate all of the visualizations, which should all be the same as the one already stored in the github.

A couple of other things you could do is change the style by altering the colors of the vertices or edges in nx.draw, or you can change where the vertices are compared to one another in the G.add_node section.


