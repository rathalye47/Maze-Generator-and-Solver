package athalyeshao.cs146.project3;
import java.util.*;

public class MazeSolverDFS {
	private Stack<Cell> cellStack; // Contains all cells to be visited next (LIFO).
	private Cell[][] mazeGrid; // 2D array of Cells of maze.
	private int numCellsVisited; // Stores the number of cells visited.
	private Cell cell; // Cell to start traversal at.
	private ArrayList<Cell> cellsVisited; // Contains all cells that have already been visited.
	
	public MazeSolverDFS(Cell[][] mazeGrid) 
	{
		cellStack = new Stack<>();
		this.mazeGrid = mazeGrid;
		cell = mazeGrid[0][0];
		numCellsVisited = 0;
		cellsVisited = new ArrayList<>();
	}
	
	// Solves maze using Depth First Search.
	public Cell[][] solveDFS() 
	{
		// Starts time to start solving maze using BFS.
		double time = System.nanoTime();
		
		// Add startingCell to stack.
		cellStack.add(cell);
		// Add startingCell to nodesVisited.
		cellsVisited.add(cell);
		// Set visiting order of startingCell to 0.
		cell.setVisitingOrder(0);
		// Set numNodesVisited to 1.
		numCellsVisited = 1;
		
		// Iterates until all cells are searched.
		while(!cellStack.isEmpty()) 
		{
			// cell is now a popped cell from cellStack.
			cell = cellStack.pop();
			
			// If location of currentCell is the last cell in the 2D array, we have reached the end. Break.
			if (cell.equals(mazeGrid[mazeGrid.length - 1][mazeGrid.length - 1]))
			{
				break;
			}

			// Iterate through all neighbors of cell.
			for (Cell adjacentCell : cell.getCellNeighbors())
			{
				// Only continue if adjacentCell has not been visited yet.
				if (!cellsVisited.contains(adjacentCell))
				{
					// If location of cell is not 0,0 and adjacentCell is north of cell and there is no wall between them,
					// then update accordingly.
					if ((cell.getRow() != 0 && cell.getColumn() != 0) && (cell.findNeighborDirection(adjacentCell).equals("North")) && (cell.getHasNorthWall() == false))
					{
						updateCellValue(adjacentCell);
					}

					// If adjacentCell is south of cell and there is no wall between them,
					// then update accordingly.
					else if ((cell.findNeighborDirection(adjacentCell).equals("South")) && (cell.getHasSouthWall() == false))
					{
						updateCellValue(adjacentCell);
					}

					// If adjacentCell is west of cell and there is no wall between them,
					// then update accordingly.
					else if ((cell.findNeighborDirection(adjacentCell).equals("West")) && (cell.getHasWestWall() == false))
					{
						updateCellValue(adjacentCell);
					}

					// If adjacentCell is east of cell and there is no wall between them,
					// then update accordingly.
					else if ((cell.findNeighborDirection(adjacentCell).equals("East")) && (cell.getHasEastWall() == false))
					{
						updateCellValue(adjacentCell);
					}
				}
			}
		}
		
		System.out.println("Time to solve using DFS: " + (System.nanoTime() - time));
		return mazeGrid;
	}

	// Returns cellsVisited ArrayList.
	public ArrayList<Cell> returnCellsVisited() 
	{
		return cellsVisited;
	}

	// Helper method that finds all cells that are in path to end of maze.
	public ArrayList<Cell> returnPath() 
	{
		// Declare a new arraylist to hold all cells that are in maze path.
		ArrayList<Cell> path = new ArrayList<>();
		// Start finding all cells in path with cell at end of maze.
		Cell pathCell = mazeGrid[mazeGrid.length - 1][mazeGrid.length - 1];
		// Add Cell end to path.
		path.add(pathCell);

		// Iterates through cells until the cell at the beginning of the maze is reached.
		while (pathCell != mazeGrid[0][0]) 
		{
			Cell parentCell = pathCell.getCellParent();
			path.add(parentCell);
			pathCell = parentCell;
		}
		return path;
	}

	// Helper method that that updates the stack and nodesVisited list to add updateCell.
	private void updateStackAndList(Cell updateCell) 
	{
		cellsVisited.add(updateCell);
		cellStack.push(updateCell);
	}

	// Helper method that updates the stack, nodesVisited list, and visitingOrder of updateCell.
	private void updateCellValue(Cell updateCell) 
	{
		// Update stack and nodesVisited list.
		updateStackAndList(updateCell);
		// Set visitingOrder of updateCell to numNodesVisited value under 10.
		int visitingOrder = numCellsVisited % 10;
		updateCell.setVisitingOrder(visitingOrder);
		// Increment numNodesVisited.
		numCellsVisited = numCellsVisited + 1;
		// Set parent of updateCell to cell.
		updateCell.setCellParent(cell);
	}
}