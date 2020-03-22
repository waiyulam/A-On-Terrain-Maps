# A-On-Terrain-Maps
A* platform for plotting a course home, minimizing the total cost of the path and the amount of time spent searching

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
