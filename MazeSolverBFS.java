package athalyeshao.cs146.project3;
import java.util.*;

public class MazeSolverBFS 
{
	private Queue<Cell> queue; // Contains all cells to be visited next (FIFO).
	private ArrayList<Cell> cellsVisited; // Contains all cells that have already been visited.
	private Cell startingCell; // Cell to start traversal at.
	private Cell[][] mazeGrid; // 2D array of Cells of maze.
	private int numCellsVisited; // Number of Cells traversed so far.
	
	public MazeSolverBFS(Cell[][] mazeGrid) 
	{
		queue = new LinkedList<>();
		cellsVisited = new ArrayList<>();
		startingCell = mazeGrid[0][0];
		this.mazeGrid = mazeGrid;
		this.numCellsVisited = 0;
	}
	
	// Solves maze using Breadth First Search.
	public Cell[][] solveBFS()
	{
		// Starts time to start solving maze using BFS.
		double time = System.nanoTime();
		
		// Add startingCell to queue.
		queue.add(startingCell);
		// Add startingCell to cellsVisited.
		cellsVisited.add(startingCell);
		// Set visiting order of startingCell to 0.
		startingCell.setVisitingOrder(0);
		// Set numCellsVisited to 1.
		numCellsVisited = 1;
		
		// Runs until all cells are searched.
		while (queue.size() != 0)
		{
			// startingCell is a Cell from queue. 
			startingCell = queue.remove();
			
			// If location of startingCell is the last cell in the 2D array, we have reached the end. Break.
			if (startingCell.equals(mazeGrid[mazeGrid.length - 1][mazeGrid.length - 1]))
			{
				break;
			}
			
			// Iterate through all neighbors of startingCell.
			for (Cell adjacentCell : startingCell.getCellNeighbors())
			{
				// Only continue if adjacentCell has not been visited yet.
				if (!cellsVisited.contains(adjacentCell))
				{
					// If location of startingCell is not 0,0 and adjacentCell is north of cell and there is no wall between them,
					// then update accordingly.
					if ((!startingCell.equals(mazeGrid[0][0])) && (startingCell.findNeighborDirection(adjacentCell).equals("North")) && (startingCell.getHasNorthWall() == false))
					{
						updateCellValue(adjacentCell, startingCell);
					}
					
					// If adjacentCell is south of startingCell and there is no wall between them,
					// then update accordingly.
					else if ((startingCell.findNeighborDirection(adjacentCell).equals("South")) && (startingCell.getHasSouthWall() == false))
					{
						updateCellValue(adjacentCell, startingCell);
					}
					
					// If adjacentCell is west of startingCell and there is no wall between them,
					// then update accordingly.
					else if ((startingCell.findNeighborDirection(adjacentCell).equals("West")) && (startingCell.getHasWestWall() == false))
					{
						updateCellValue(adjacentCell, startingCell);
					}
					
					// If adjacentCell is east of startingCell and there is no wall between them,
					// then update accordingly.
					else if ((startingCell.findNeighborDirection(adjacentCell).equals("East")) && (startingCell.getHasEastWall() == false))
					{
						updateCellValue(adjacentCell, startingCell);
					}
				}
			}
		}
		
		// Prints the total time it takes to generate maze.
		System.out.println("Time to solve using BFS: " + (System.nanoTime() - time));
		// Return mazeGrid.
		return mazeGrid;
	}
	
	// Returns cellsVisited ArrayList.
	public ArrayList<Cell> returnCellsVisited() 
	{
		return cellsVisited;
	}
	
	// Helper method that updates queue and cellsVisited list.
	private void updateQueueAndList(Cell cell)
	{
		cellsVisited.add(cell);
		queue.add(cell);
	}

	// Helper method that finds all cells that are in path to end of maze.
	public ArrayList<Cell> returnPath() 
	{
		// Declare a new ArrayList to hold all cells that are in maze path.
		ArrayList<Cell> path = new ArrayList<>();
		// Start finding all cells in path with cell at end of maze.
		Cell pathCell = mazeGrid[mazeGrid.length - 1][mazeGrid.length - 1];
		// Add pathCell to path.
		path.add(pathCell);

		// Iterates through cells until the cell at the beginning of the maze is reached.
		//while(pathCell.getRow() != 0 && pathCell.getColumn() != 0) {
		while (pathCell != mazeGrid[0][0]) 
		{
			Cell parentCell = pathCell.getCellParent();
			path.add(parentCell);
			pathCell = parentCell;
		}
		return path;
	}

	// Helper method that updates the queue, nodesVisited list, and visitingOrder of adjacentCell.
	private void updateCellValue(Cell adjacentCell, Cell cell) 
	{
		// Update queue and cellsVisited list.
		updateQueueAndList(adjacentCell);
		// Set visitingOrder of adjacentCell to numCellsVisited value under 10.
		adjacentCell.setVisitingOrder(numCellsVisited % 10);
		// Set parent of adjacentCell to cell.
		adjacentCell.setCellParent(cell);
		// Increment numNodesVisited.
		numCellsVisited = numCellsVisited + 1;
	}
}
