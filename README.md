# A-On-Terrain-Maps
A* platform for plotting a course home, minimizing the total cost of the path and the amount of time spent searching

## The world 
[**TerrainMap**](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/TerrainMap.java):
TerrainMap is a class encapsulating a two-dimensional world layered on top of a rectangular grid. Each point in the world has a height, represented by an integer value between 0 and 255. Depending on the selected movement type, you can either move to any of the eight squares adjacent to your own location (e.g. the four cardinal directions and the diagonals) or just the cardinal directions. As you would expect, the cost to traverse between tiles is dependent on the differences in height between the tiles.
The TerrainMap class also keeps track of which tiles your algorithm visits as it looks for an optimal path home. The cost of searching will be determined by how many tiles your algorithm visisted and the amount of time of searching 

## Usage 
Compiles source： javac  Src/*.java

Runs randomly generated map： java Main StupidAI

Runs with map generated from DEM2XYZ : 
1. java -Xmx1024M Main -load MTAFT.XYZ DijkstraAI
2. time java -Xmx1024M Main -load MTAFT.XYZ DijkstraAI

Runs multiple modules: time java -Xmx1024M Main -load MTAFT.XYZ DijkstraAI DirectAI

Runs with a specifiic seed: java Main StupidAI -seed 1   ***note no equal sign***

## Sources 
[Project link](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/html/index.html)
