# A-On-Terrain-Maps
A* platform for plotting a course home, minimizing the total cost of the path and the amount of time spent searching

## The world 
[**TerrainMap**](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/TerrainMap.java):
TerrainMap is a class encapsulating a two-dimensional world layered on top of a rectangular grid. Each point in the world has a height, represented by an integer value between 0 and 255. Depending on the selected movement type, you can either move to any of the eight squares adjacent to your own location (e.g. the four cardinal directions and the diagonals) or just the cardinal directions. As you would expect, the cost to traverse between tiles is dependent on the differences in height between the tiles.
The TerrainMap class also keeps track of which tiles your algorithm visits as it looks for an optimal path home. The cost of searching will be determined by how many tiles your algorithm visisted and the amount of time of searching 

## Implementation Details 
All java code, except for [AStarDiv.java](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/AStarDiv.java), [AStarExp.java](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/AStarExp.java) and [MtStHelensExp.java](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/MtStHelensExp.java), were given by the professor. All files present an implementation of the A star algorithm, but they differ on the Heuristics choice. This is rather redundant, but is for the sake of assignment constraints.Speaking of assignment constraints, we had to implement all the auxiliary classes as nested classes in both files.

## Cost Function & Heuristics Design 
1. "Power World" 
  - **Cost Function** 
  ```java 
    public double getCost(final Point p1, final Point p2)
      {
          return Math.pow(2.0, (getTile(p2) - getTile(p1)));
      }
   ```
  - **Heuristics**  
  ```java
  	private double getHeuristic(final TerrainMap map, final Point pt1, final Point pt2){
		double DifferenceHeight = map.getTile(pt2) - map.getTile(pt1);
		double DistanceX = Math.abs(pt2.x - pt1.x);
		double DistanceY = Math.abs(pt2.y - pt1.y);
		double LeastDistance = Math.max(DistanceX,DistanceY)+20;
		return 0.8 * LeastDistance * Math.pow(2,DifferenceHeight/LeastDistance);
	}
  ```
2. "Bizzaro World"
  - **Cost Function**
  ```java 
    public double getCost(final Point p1, final Point p2)
      {
          return 1.0*(getTile(p1) / (getTile(p2) + 1));
      }
  ```
  - **Heuristics**
  ```java
    private double getHeuristic(final TerrainMap map, final Point pt1, final Point pt2){
      //pt1 as the current point, pt2 as the end point
        double DifferenceHeight = map.getTile(pt2) - map.getTile(pt1);
        double DistanceX = Math.abs(pt2.x - pt1.x);
        double DistanceY = Math.abs(pt2.y - pt1.y);
        double LeastDistance = Math.max(DistanceX,DistanceY);
        if (DifferenceHeight >= 0){
          return (map.getTile(pt1)/255)*LeastDistance;
        }else{
          return 0.5 * LeastDistance;
        }
      }
  ```
For more consistency provement, please refer [Heuristics Design](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/Part1.pdf)

## Usage 
Compiles source： **javac  <src_path>/*.java**

Runs randomly generated map： **java Main StupidAI**

Runs with a specifiic seed: **java Main StupidAI -seed 1** 

To try out our AI implementation, with the division heuristics, go to the src folder and:  
1. Make sure that in the getCost() method in TerrainMap.java is "Bizzaro World"
2. Run : **java Main PerfectAI_Div**

To try out our AI implementation, with the exponential heuristics, go to the src folder and:  
1. Make sure that in the getCost() method in TerrainMap.java is "Power World"
2. Run : **java Main PerfectAI_Exp**

To try out our AI implementation in Mount Saint Helens map , go to the src folder and:   
**java Main PerfectAI -load MTAFT.XYZ**

For more API usage, please refer [How to build and run](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/HowToBuildandRun.txt)

## Sources 
[Project link](https://github.com/waiyulam/A-On-Terrain-Maps/blob/master/html/index.html)
