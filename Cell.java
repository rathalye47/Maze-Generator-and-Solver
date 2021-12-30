package athalyeshao.cs146.project3;

import java.util.ArrayList;

public class Cell {

	private Cell cellParent; // The parent of the cell.
	private ArrayList<Cell> cellNeighbors; // An ArrayList of the cell's neighbors.
	
	private boolean hasNorthWall; // Checks if the cell has a north wall.
	private boolean hasSouthWall; // Checks if the cell has a south wall.
	private boolean hasWestWall; // Checks if the cell has a west wall.
	private boolean hasEastWall; // Checks if the cell has a east wall.
	
	private int row; // The row of the cell.
	private int column; // The column of the cell.
	
	private boolean isBlockedCell; // Checks if the cell has no openings, meaning it is blocked.
	
	private int visitingOrder; // Keeps track of the visiting order for the BFS and DFS algorithms.
	
	public Cell(int row, int column)
	{
		cellParent = null;
		cellNeighbors = new ArrayList<>();
		
		hasNorthWall = true;
		hasSouthWall = true;
		hasWestWall = true;
		hasEastWall = true;
		
		this.row = row;
		this.column = column;
		
		isBlockedCell = true;
		
		visitingOrder = -1;
	}
	
	// Returns an ArrayList of the cell's neighbors.
	public ArrayList<Cell> getCellNeighbors()
	{
		return this.cellNeighbors;
	}
	
	// Adds a cell neighbor to the cellNeighbors ArrayList.
	public void addCellNeighbor(Cell cellNeighbor)
	{
		this.cellNeighbors.add(cellNeighbor);
	}
	
	// Returns the parent of the cell.
	public Cell getCellParent()
	{
		return this.cellParent;
	}
	
	// Updates the parent of the cell.
	public void setCellParent(Cell newCellParent)
	{
		this.cellParent = newCellParent;
	}
	
	// Returns the row of the cell.
	public int getRow()
	{
		return this.row;
	}
	
	// Returns the column of the cell.
	public int getColumn()
	{
		return this.column;
	}
	
	// Returns true or false depending on whether or not the cell has a north wall.
	public boolean getHasNorthWall()
	{
		return this.hasNorthWall;
	}
	
	// Updates the status of whether or not the cell has a north wall.
	public void setHasNorthWall(boolean newHasNorthWall)
	{
		this.hasNorthWall = newHasNorthWall;
	}
	
	// Returns true or false depending on whether or not the cell has a south wall.
	public boolean getHasSouthWall()
	{
		return this.hasSouthWall;
	}
	
	// Updates the status of whether or not the cell has a south wall.
	public void setHasSouthWall(boolean newHasSouthWall)
	{
		this.hasSouthWall = newHasSouthWall;
	}
	
	// Returns true or false depending on whether or not the cell has an east wall.
	public boolean getHasEastWall()
	{
		return this.hasEastWall;
	}
	
	// Updates the status of whether or not the cell has an east wall.
	public void setHasEastWall(boolean newHasEastWall)
	{
		this.hasEastWall = newHasEastWall;
	}
	
	// Returns true or false depending on whether or not the cell has a west wall.
	public boolean getHasWestWall()
	{
		return this.hasWestWall;
	}
	
	// Updates the status of whether or not the cell has a west wall.
	public void setHasWestWall(boolean newHasWestWall)
	{
		this.hasWestWall = newHasWestWall;
	}
	
	// Checks if two cells are equal to each other.
	public boolean equals(Object other)
	{
		Cell otherCell = (Cell) other;
		return (this.row == otherCell.row) && (this.column == otherCell.column);
	}
	
	// Knocks down the cell's north wall.
	public void removeNorthWall()
	{
		this.hasNorthWall = false;
	}
	
	// Knocks down the cell's south wall.
	public void removeSouthWall()
	{
		this.hasSouthWall = false;
	}
	
	// Knocks down the cell's east wall.
	public void removeEastWall()
	{
		this.hasEastWall = false;
	}
	
	// Knocks down the cell's west wall.
	public void removeWestWall()
	{
		this.hasWestWall = false;
	}
	
	// Returns true or false depending on whether or not the cell is blocked.
	public boolean getIsBlockedCell()
	{
		return this.isBlockedCell;
	}
	
	// Updates the status regarding whether or not the cell is blocked.
	public void setIsBlockedCell(boolean blockedCellStatus)
	{
		this.isBlockedCell = blockedCellStatus;
	}
	
	// Returns the direction of the neighboring cell.
	public String findNeighborDirection(Cell cell)
	{
		if (cell.getRow() < this.getRow()) // If the neighboring cell's row is less than the current cell's
										   // row, return "North."
		{
			return "North";
		}
		
		else if (cell.getRow() > this.getRow()) // If the neighboring cell's row is greater than the current cell's
			   								    // row, return "South."
		{
			return "South";
		}
		
		else if (cell.getColumn() > this.getColumn()) // If the neighboring cell's column is greater than the current cell's
			   									      // column, return "East."
		{
			return "East";
		}
		
		else if (cell.getColumn() < this.getColumn()) // If the neighboring cell's column is less than the current cell's
			   									      // column, return "West."
		{
			return "West";
		}
		
		else
		{
			return "None"; // Otherwise, return "None."
		}
	}

	// Returns the visiting order for the BFS and DFS algorithms accordingly.
	public int getVisitingOrder() 
	{
		return visitingOrder;
	}

	// Updates the visiting order for the BFS and DFS algorithms accordingly.
	public void setVisitingOrder(int visitingOrder) 
	{
		this.visitingOrder = visitingOrder;
	}
}