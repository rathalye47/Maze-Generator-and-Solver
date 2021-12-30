package athalyeshao.cs146.project3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {
	
	private Cell[][] mazeGrid; // 2D array of Cells of maze.
	private ArrayList<Cell> neighborWalls; // ArrayList containing all neighbors of cell.
	private Cell currentCell; // Cell that references currentCell.
	private Cell nextCell; // Cell that references nextCell of currentCell.
	private Random random; // Random generator
	
	public Maze(int mazeSize)
	{
		mazeGrid = new Cell[mazeSize][mazeSize];
		neighborWalls = null;
		currentCell = null;
		nextCell = null;
		random = new Random(4);
	}
	
	// Randomly generates a simple maze using DFS.
	public Cell[][] createMaze()
	{
		// Calls on helper method to create the mazeGrid.
		createMazeGrid();
		// Calls on helper method to update neighbors of all cells in maze given their location.
		updateCellsInGrid();
		// Calls on helper method to generate simple maze using DFS algorithm.
		generateMaze();
		// Returns 2D array of cells of maze.
		return mazeGrid;
	}
	
	// Updates cells in maze to create simple maze.
	private void generateMaze() 
	{
		// Starts time to start maze generation.
		double time = System.nanoTime();
		
		// cellStack holds list of cell locations.
		Stack<Cell> cellStack = new Stack<Cell>();
		// totalCells is number of cells in the 2D array.
		int totalCells = mazeGrid.length * mazeGrid[0].length;
		// Initialize visitedCells to 1.
		int visitedCells = 1;
		// currentCell always begins as location 0, 0.
		currentCell = mazeGrid[0][0];
		
		// Iterates until every cell in the array is visited.
		while (visitedCells < totalCells)
		{
			// Initialize neighborWalls as a new arraylist.
			neighborWalls = new ArrayList<>();
			// Finds all neighbors of currentCell with walls intact 
			// and adds them to neighborWalls.
			findWalledNeighbors();
			
			// If there is more than one neighbor with walls intact, randomly choose one.
			if (neighborWalls.size() > 0)
			{
				// Set nextCell as randomly selected neighbor with walls intact of currentCell.
				nextCell = neighborWalls.get(random.nextInt(neighborWalls.size()));
				
				// Calls helper method to open path between currentCell and nextCell 
				// depending on their locations.
				this.openPath();
				
				// Make nextCell currentCell.
				currentCell = nextCell;
				// Push currentCell onto cellStack.
				cellStack.push(currentCell);
				// Add 1 to visitedCells.
				visitedCells = visitedCells + 1;
			}
			
			// If there are no neighbors of currentCell, pop off the most recent entry
			// off the cellStack and make it currentCell.
			else
			{
				currentCell = cellStack.pop();
			}
		}
		// Prints the total time it takes to generate maze.
		System.out.println("Maze generation time: " + (System.nanoTime() - time));
	}
	
	// Helper method that creates the 2D array of Cells of the maze.
	private void createMazeGrid()
	{
		for (int column = 0; column < mazeGrid.length; column++)
		{
			for (int row = 0; row < mazeGrid[0].length; row++)
			{
				// Declare new gridCell to be added to mazeGrid.
				Cell gridCell = new Cell(row, column);
				
				// Special case: if cell is 0,0 north wall does not exist.
				if(row == 0 && column == 0) 
				{
					gridCell.setHasNorthWall(false);
				}
				
				// Special case: if cell is at bottom right, south wall does not exist.
				if(row == mazeGrid[0].length - 1 && column == mazeGrid.length - 1) 
				{
					gridCell.setHasSouthWall(false);
				}
				// Fill location of mazeGrid at row, column with gridCell.
				mazeGrid[row][column] = gridCell;
			}
		}
	}
	
	// Helper method that ensures all the cells in the 2D grid have proper neighbors given their location.
	private void updateCellsInGrid() 
	{
		// Iterates through every column of the mazeGrid.
		for (int column = 0; column < mazeGrid.length; column++)
		{
			// Iterates through every row in the column of the mazeGrid.
			for (int row = 0; row < mazeGrid[0].length; row++)
			{
				// Accesses Cell with given location.
				Cell cell = mazeGrid[row][column];
				
				// If cell is not in the last row of the maze, 
				// then the cell directly under it is a neighbor.
				if (row < mazeGrid.length - 1)
				{
					cell.addCellNeighbor(mazeGrid[row + 1][column]);
				}
				
				// If cell is not in the first row of the maze, 
				// then the cell directly above it is a neighbor.
				if (row >= 1)
				{
					cell.addCellNeighbor(mazeGrid[row - 1][column]);
				}
				
				// If cell is not in the last column of the maze, 
				// then the cell directly east of it is a neighbor.
				if (column < mazeGrid[0].length - 1)
				{
					cell.addCellNeighbor(mazeGrid[row][column + 1]);
				}
				
				// If cell is not in the first column of the maze, 
				// then the cell directly west of it is a neighbor.
				if (column >= 1)
				{
					cell.addCellNeighbor(mazeGrid[row][column - 1]);
				}
			}
		}
	}
	
	// Finds all the neighbors of currentCell with walls intact and adds to neighborWalls.
	private void findWalledNeighbors()
	{
		// Access neighbors of currentCell.
		ArrayList<Cell> neighbors = currentCell.getCellNeighbors();
		
		// Iterates through each cell in the list of neighbors of currentCell.
		for (Cell neighborCell : neighbors)
		{
			// If the neighbor of the currentCell has all walls intact, 
			// add to neighborWalls.
			if (neighborCell.getIsBlockedCell() == true)
			{
				neighborWalls.add(neighborCell);
			}
		}
	}
	
	// Helper method that opens path between currentCell and nextCell depending on their locations.
	private void openPath() 
	{
		// If currentCell is south of nextCell, open up the north path of currentCell.
		if (currentCell.getRow() > nextCell.getRow())
		{
			this.openNorthPath();
		}
		// If currentCell is north of nextCell, open up the south path of currentCell.
		else if (currentCell.getRow() < nextCell.getRow())
		{
			this.openSouthPath();
		}
		// If currentCell is east of nextCell, open up the west path of currentCell.
		else if (currentCell.getColumn() > nextCell.getColumn())
		{
			this.openWestPath();
		}
		// If currentCell is west of nextCell, open up the east path of currentCell.
		else
		{
			this.openEastPath();
		}
	}
	
	// Remove the north wall of currentCell and remove the south wall of nextCell.
	// currentCell and nextCell are not blocked anymore.
	private void openNorthPath() 
	{
		currentCell.removeNorthWall();
		currentCell.setIsBlockedCell(false);
		nextCell.removeSouthWall();
		nextCell.setIsBlockedCell(false);
	}
	
	// Remove the south wall of currentCell and remove the north wall of nextCell.
	// currentCell and nextCell are not blocked anymore.
	private void openSouthPath() 
	{					
		currentCell.removeSouthWall();
		currentCell.setIsBlockedCell(false);
		nextCell.removeNorthWall();
		nextCell.setIsBlockedCell(false);
	}
	
	// Remove the east wall of currentCell and remove the west wall of nextCell.
	// currentCell and nextCell are not blocked anymore.
	private void openEastPath() 
	{
		currentCell.removeEastWall();
		currentCell.setIsBlockedCell(false);
		nextCell.removeWestWall();
		nextCell.setIsBlockedCell(false);
	}
	
	// Remove the west wall of currentCell and remove the east wall of nextCell.
	// currentCell and nextCell are not blocked anymore.
	private void openWestPath() 
	{
		currentCell.removeWestWall();
		currentCell.setIsBlockedCell(false);
		nextCell.removeEastWall();
		nextCell.setIsBlockedCell(false);
	}
}
