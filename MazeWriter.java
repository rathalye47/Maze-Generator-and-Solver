package athalyeshao.cs146.project3;

import java.io.*;
import java.util.ArrayList;

public class MazeWriter {
	private BufferedWriter bw; // Buffered Writer.
	private String blank; // Used when printing the results of the maze.
	private ArrayList<Cell> path; // Holds an ArrayList of cells in the shortest solution path.
	
	public MazeWriter() 
	{
		blank = " ";
		path = new ArrayList<>();
		
		try 
		{
			// Creates a new text file titled maze.txt.
			File file = new File("maze.txt");
			
			// If file exists, append to file.
			if(file.exists()) 
			{
				bw = new BufferedWriter(new FileWriter(file, true));
			} 
			
			else 
			{
			// Else, initializes BufferedWriter bw and create a new file.
				bw = new BufferedWriter(new FileWriter(file));
			}
			
		} catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	// Writes all elements to a file.
	public void writeToFile(Cell[][] emptyMaze, Cell[][] solvedBFSMaze, Cell[][] solvedDFSMaze, 
			ArrayList<Cell> bfsNodesVisited, ArrayList<Cell> dfsNodesVisited) 
	{
		try
		{
			// Start by stating graph size.
			bw.write("Graph Size: "  + emptyMaze.length);
			bw.newLine();
			
			// Print empty maze.
			bw.write("MAZE: ");
			bw.newLine();
			writeMaze(emptyMaze, "empty");
			
			// Print maze solved with BFS.
			bw.write("BFS:");
			bw.newLine();
			writeMaze(solvedBFSMaze, "order");
			writeMaze(solvedBFSMaze, "path");
			writePathAndVisitedCells(bfsNodesVisited);
			
			// Print maze solved with DFS.
			bw.write("DFS:");
			bw.newLine();
			writeMaze(solvedDFSMaze, "order");
			writeMaze(solvedDFSMaze, "path");
			writePathAndVisitedCells(dfsNodesVisited);
			
			// Finish by stating program completed.
			bw.write("===================");
			bw.newLine();
			bw.write(" PROGRAM COMPLETED");
			bw.newLine();
			bw.write("===================");
			bw.newLine();
			bw.newLine();
			
		}
		
		catch(IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	// Writes maze in ASCII format to the maze.txt file.
	private void writeMaze(Cell[][] maze, String format) 
	{
		try 
		{
			// Iterates through each row of maze
			for(int i = 0; i < maze.length; i++) 
			{
				Cell[] row = maze[i];
				// In each iteration of a row, 
				// write top and middle of row.
				writeTop(row);
				// Write blanks with " " if format is empty.
				if(format.equalsIgnoreCase("empty")) 
				{
					writeEmptyMiddle(row);
				}
				// Write blanks with visitorOrder of cell if format is order.
				else if(format.equalsIgnoreCase("order")) 
				{
					writeOrder(row);
				} 
				// Write blanks with "#" if format is path.
				else if(format.equalsIgnoreCase("path"))
				{
					findPath(maze);
					writePathOrder(row);
				}
				// If last row is reached, also write bottom.
				if(i == maze.length - 1) 
				{
					writeBottom(row);
				}
			}
			
			// Move to next line in maze.txt.
			bw.newLine();
			
		} catch(IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	// Helper method that writes the top of a given row.
	private void writeTop(Cell[] row) 
	{
		try 
		{
			// Iterates through each cell in the row.
			for(Cell cell : row) 
			{
				// If there is a wall between cell and cell above it, write "+-".
				if(cell.getHasNorthWall() == true) 
				{
					bw.write("+-");
				}
				// If there is no wall between cell and cell above it, write "+ ".
				else 
				{
					bw.write("+ ");
				}
			}
			// At the end of the row, write an extra "+" for the corner 
			// and go to next line.
			bw.write("+");
			bw.newLine();
			
		} catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	// Helper method that writes the middle of a given row of an empty maze.
	private void writeEmptyMiddle(Cell[] row) 
	{
		try 
		{
			// Iterates through each cell in the row.
			for(Cell cell : row) 
			{
				// Resets blank to " ".
				blank = " ";
				// If there is a wall between cell and cell to the left of it, write "| ".
				if(cell.getHasWestWall() == true) 
				{
					bw.write("|" + blank);
				} 
				// If there is no wall between cell and cell to the left, write "  ".
				else 
				{
					bw.write(" " + blank);
				}
			}
			// At the end of the row, write an extra "|" to close off the row
			// and go to next line.
			bw.write("|");
			bw.newLine();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}	
	
	// Helper method that writes the order of a given row of a solved maze.
	private void writeOrder(Cell[] row) 
	{
		try 
		{
			// Iterates through each cell in the row.
			for(Cell cell : row) 
			{
				// Resets blank to " ".
				blank = " ";
				
				// If cell has been visited, then blank should be the String value of its visiting order;
				// otherwise is still " ".
				if (cell.getVisitingOrder() > -1 )
				{
					blank = String.valueOf(cell.getVisitingOrder());
				}
				
				// If there is a wall between cell and cell to the left of it, write "| ".
				if (cell.getHasWestWall() == true) 
				{
					bw.write("|" + blank);
				} 
				
				// If there is no wall between cell and cell to the left, write "  ".
				else 
				{
					bw.write(" " + blank);
				}
			}
			// At the end of the row, write an extra "|" to close off the row
			// and go to next line.
			bw.write("|");
			bw.newLine();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Helper method that writes the path of an empty maze using hashes.
	private void writePathOrder(Cell[] row) 
	{
		try 
		{
			// Iterates through each cell in the row. 
			for(Cell cell : row) 
			{
				// Resets blank to " ".
				blank = " ";
				
				// If cell is in the path through the maze, then blank is #;
				// otherwise is still " ".
				if(path.contains(cell)) 
				{
					blank = "#";
				}
					
				// If there is a wall between cell and cell to the left of it, write "| ".
				if(cell.getHasWestWall() == true) 
				{
					bw.write("|" + blank);
				} 
				// If there is no wall between cell and cell to the left, write "  ".
				else 
				{
					bw.write(" " + blank);
				}
			}
			// At the end of the row, write an extra "|" to close off the row
			// and go to next line.
			bw.write("|");
			bw.newLine();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Helper method that writes the bottom of the maze (only used once).
	private void writeBottom(Cell[] row) 
	{
		try {
			// Iterates through all cells in the last row.
			for(Cell cell : row) 
			{
				// If there is a wall between cell and cell under it, write "+-".
				if(cell.getHasSouthWall() == true) 
				{
					bw.write("+-");
				} 
				// If there is no wall between cell and cell under it, write "+ ".
				else 
				{
					bw.write("+ ");
				}
			}
			// At the end of the row, write an extra "+" for the corner
			// and go to the next line.
			bw.write("+");
			bw.newLine();
			
		} catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	// Helper method that finds all cells that are in path to end of maze.
	private void findPath(Cell[][] maze) 
	{
		// Declare a new ArrayList to hold all cells that are in maze path.
		path = new ArrayList<>();
		// Start finding all cells in path with cell at end of maze.
		Cell pathCell = maze[maze.length - 1][maze.length - 1];
		// Add Cell end to path.
		path.add(pathCell);
		
		// Iterates through cells until the cell at the beginning of the maze is reached.
		//while(pathCell.getRow() != 0 && pathCell.getColumn() != 0) {
		while (pathCell != maze[0][0]) 
		{
			Cell parentCell = pathCell.getCellParent();
			path.add(parentCell);
			pathCell = parentCell;
		}
	}
	
	// Writes the path and the number of visited cells.
	private void writePathAndVisitedCells(ArrayList<Cell> cellsVisited) {
		try
		{
			bw.write("Path: ");
			
			for(int i = path.size() - 1; i >= 0; i--) 
			{
				bw.write("(" + path.get(i).getRow() + "," + path.get(i).getColumn() + ") ");
			}
			
			bw.newLine();
			bw.write("Length of path: " + path.size());
			bw.newLine();
			bw.write("Visited cells: " + cellsVisited.size());
			bw.newLine();
			bw.newLine();
		}
		
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Closes the BufferedWriter when done writing to file.
	// MUST CALL AFTER FILE IS DONE!
	public void cleanUp() {
		try 
		{
			bw.close();
		} 
		
		catch(IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}