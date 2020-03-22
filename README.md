# A-On-Terrain-Maps
A* platform for plotting a course home, minimizing the total cost of the path and the amount of time spent searching

## The world 
[**TerrainMap**](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/TerrainMap.java):
TerrainMap is a class encapsulating a two-dimensional world layered on top of a rectangular grid. Each point in the world has a height, represented by an integer value between 0 and 255. Depending on the selected movement type, you can either move to any of the eight squares adjacent to your own location (e.g. the four cardinal directions and the diagonals) or just the cardinal directions. As you would expect, the cost to traverse between tiles is dependent on the differences in height between the tiles.
The TerrainMap class also keeps track of which tiles your algorithm visits as it looks for an optimal path home. The cost of searching will be determined by how many tiles your algorithm visisted and the amount of time of searching 

## Implementation Details 
All java code, except for [AStarDiv.java](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/AStarDiv.java), [AStarExp.java](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/AStarExp.java) and [MtStHelensExp.java](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/MtStHelensExp.java), were given by the professor. All files present an implementation of the A star algorithm, but they differ on the Heuristics choice. This is rather redundant, but is for the sake of assignment constraints.Speaking of assignment constraints, we had to implement all the auxiliary classes as nested classes in both files.

## Usage 
Compiles source： **javac  <src_path>/*.java*

Runs randomly generated map： **java Main StupidAI**

Runs with a specifiic seed: **java Main StupidAI -seed 1** 

To try out our AI implementation, with the division heuristics, go to the src folder and:  
**java Main PerfectAI_Div**

To try out our AI implementation, with the exponential heuristics, go to the src folder and:  
**java Main PerfectAI_Exp**

To try out our AI implementation in Mount Saint Helens map , go to the src folder and:   
**java Main PerfectAI**

For more API usage, please refer [How to build and run](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/HowToBuildandRun.txt)

## Sources 
[Project link](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/html/index.html)
