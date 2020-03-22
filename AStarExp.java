/**

 */

import java.awt.Point;
import java.util.*;

class PerfectAI_Exp implements AIModule {
    static class Cell {
        double heuristicCost = 0; //Heuristic cost
        double finalCost = 0; //G+H
        Point point;
        Cell parent;

        Cell(Point p) {
            this.point = p;
        }
    }

    static Cell[][] grid;
    static PriorityQueue<Cell> open;
    static boolean closed[][];
    static int startI, startJ;
    static int endI, endJ;

    public static void setStartCell(int i, int j) {
        startI = i;
        startJ = j;
    }

    public static void setEndCell(int i, int j) {
        endI = i;
        endJ = j;
    }

    public static void setGrid(int width, int height) {
        grid = new Cell[width][height];
    }

    static void checkAndUpdateCost(Cell current, Cell t, double cost, double heuristicCost) {
        // child cell is explored and cell in closed set always has lowest cost
        if (t == null) return;
        if (closed[t.point.x][t.point.y] && cost > t.finalCost) return;

        // check if frontier contains cell
        boolean inOpen = open.contains(t);
        // if cell not in frontier -> add into frontier or cell in frontier but has lower cost -> update cost
        if (!inOpen || cost < t.finalCost) {
            t.finalCost = cost;
            t.heuristicCost = heuristicCost;
            t.parent = current;
            if (!inOpen) open.add(t);
        }
    }

    public List<Point> createPath(final TerrainMap map) {
        // Set up Grid
        setGrid(map.getWidth(), map.getHeight());
        // for (int i = 0; i < map.getWidth(); ++i) {
        //     for (int j = 0; j < map.getHeight(); ++j) {
        //         grid[i][j] = new Cell(new Point(i, j));
        //         // leave heuristicCose here :
        //         grid[i][j].heuristicCost = 0;
        //     }
        // }

        closed = new boolean[map.getWidth()][map.getHeight()];
        final ArrayList<Point> path = new ArrayList<Point>();

        open = new PriorityQueue<>((Object o1, Object o2) -> {
            Cell c1 = (Cell) o1;
            Cell c2 = (Cell) o2;

            return c1.finalCost < c2.finalCost ? -1 :
                    c1.finalCost > c2.finalCost ? 1 : 0;
        });

        //Set start position
        Point start = map.getStartPoint();
        setStartCell(start.x, start.y);  //Setting to 0,0 by default. Will be useful for the UI part
        //Set End Location
        Point end = map.getEndPoint();
        setEndCell(end.x, end.y);

        //add the start location to open list.
        grid[startI][startJ] = new Cell(new Point(startI, startJ));
        grid[startI][startJ].finalCost = 0;
        grid[startI][startJ].heuristicCost = 0;
        open.add(grid[startI][startJ]);

        Cell current;
        while (true) {
            current = open.poll();
            if (current == null) break;
            closed[current.point.x][current.point.y] = true;
            if (current.equals(grid[endI][endJ])) {
                break;
            }
            Cell t;
            Double cost;
            Point[] Neighbors = map.getNeighbors(current.point);
            for (Point neighbor : Neighbors) {
              if (grid[neighbor.x][neighbor.y] == null){
                grid[neighbor.x][neighbor.y] = new Cell(new Point(neighbor.x, neighbor.y));
              }
                t = grid[neighbor.x][neighbor.y];

                // if child.point is not in frontier or explored
                cost = current.finalCost - current.heuristicCost
                		+ map.getCost(current.point, t.point)
                		+ getHeuristic(map, t.point, end);
                checkAndUpdateCost(current, t, cost, getHeuristic(map, t.point, end));
            }
        }

        if (closed[endI][endJ]) {
            //Trace back the path
            current = grid[endI][endJ];
            while (current.parent != null) {
                path.add(current.point);
                current = current.parent;
            }
            // add startNode;
            path.add(current.point);
        }
        Collections.reverse(path);
        return path;
    }


	private double getHeuristic(final TerrainMap map, final Point pt1, final Point pt2){
	//pt1 as the current point, pt2 as the end point
		/*
		double DifferenceHeight = map.getTile(pt2) - map.getTile(pt1);
		if (DifferenceHeight >= 0){
			return 1.8*DifferenceHeight;
		}else if(DifferenceHeight < 0){
			double DistanceX = Math.abs(pt2.x - pt1.x);
			double DistanceY = Math.abs(pt2.y - pt1.y);
			double LeastDistance = Math.max(DistanceX,DistanceY);
			return 0.8 * LeastDistance * Math.pow(2,DifferenceHeight/LeastDistance);
		}
		*/
		double DifferenceHeight = map.getTile(pt2) - map.getTile(pt1);
		double DistanceX = Math.abs(pt2.x - pt1.x);
		double DistanceY = Math.abs(pt2.y - pt1.y);
		double LeastDistance = Math.max(DistanceX,DistanceY)+20;
		return 0.8 * LeastDistance * Math.pow(2,DifferenceHeight/LeastDistance);


	}
}
