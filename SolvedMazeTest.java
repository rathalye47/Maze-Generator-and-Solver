package athalyeshao.cs146.project3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class SolvedMazeTest {
	
	@Test
	public void testFile() 
	{
		Maze mazeCreator;
		MazeSolverBFS mazeSolveBFS;
		MazeSolverDFS mazeSolveDFS;
		ArrayList<Cell> bfsNodesVisited;
		ArrayList<Cell> dfsNodesVisited;
		Cell[][] maze;
		Cell[][] solvedBFS;
		Cell[][] solvedDFS;
		MazeWriter mw = new MazeWriter();
		
		for(int i = 4; i <= 10; i++) {
			System.out.println("Maze size: " + i + " x " + i);
			mazeCreator = new Maze(i);
			maze = mazeCreator.createMaze();
			mazeSolveBFS = new MazeSolverBFS(maze);
			solvedBFS = mazeSolveBFS.solveBFS();
			bfsNodesVisited = mazeSolveBFS.returnCellsVisited();
			
			mazeCreator = new Maze(i);
			maze = mazeCreator.createMaze();
			mazeSolveDFS = new MazeSolverDFS(maze);
			solvedDFS = mazeSolveDFS.solveDFS();
			dfsNodesVisited = mazeSolveDFS.returnCellsVisited();
			mw.writeToFile(maze, solvedBFS, solvedDFS, bfsNodesVisited, dfsNodesVisited);
		}
		
		mw.cleanUp();
		
		try 
		{		
			BufferedReader output = new BufferedReader (new FileReader ("maze.txt"));
			BufferedReader expected = new BufferedReader (new FileReader ("expected.txt"));
			String expectedLine;
			String actualLine;
			
			while ((expectedLine = expected.readLine()) != null) {
				actualLine = output.readLine();
				// Tests line by line if the data in file maze.txt
				// is equal to the file expected.txt.
				assertEquals((String) expectedLine, (String) actualLine);
			}
			
			output.close();
			expected.close();
		}
		
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Test case for Maze Size 4 x 4.
	@Test
	public void testCase4() 
	{
		// Maze size 4 x 4.
		System.out.println("Maze dimensions: 4 x 4");
		Maze mazeCreator = new Maze(4);
		Cell[][] maze = mazeCreator.createMaze();
		
		MazeSolverBFS mazeSolveBFS = new MazeSolverBFS(maze);
		mazeSolveBFS.solveBFS();
		ArrayList<Cell> bfsPath = mazeSolveBFS.returnPath();
		MazeSolverDFS mazeSolveDFS = new MazeSolverDFS(maze);
		mazeSolveDFS.solveDFS();
		ArrayList<Cell> dfsPath = mazeSolveDFS.returnPath();
		assertEquals(bfsPath.size(), dfsPath.size());
	}
	
	// Test case for Maze Size 5 x 5.
	@Test
	public void testCase5() {
		// Maze size 5 x 5.
		System.out.println("Maze dimensions: 5 x 5");
		Maze mazeCreator = new Maze(5);
		Cell[][] maze = mazeCreator.createMaze();

		MazeSolverBFS mazeSolveBFS = new MazeSolverBFS(maze);
		mazeSolveBFS.solveBFS();
		ArrayList<Cell> bfsPath = mazeSolveBFS.returnPath();
		MazeSolverDFS mazeSolveDFS = new MazeSolverDFS(maze);
		mazeSolveDFS.solveDFS();
		ArrayList<Cell> dfsPath = mazeSolveDFS.returnPath();
		assertEquals(bfsPath.size(), dfsPath.size());
	}

	// Test case for Maze Size 6 x 6.
	@Test
	public void testCase6() {
	    // Maze size 6 x 6.
		System.out.println("Maze dimensions: 6 x 6");
		Maze mazeCreator = new Maze(6);
		Cell[][] maze = mazeCreator.createMaze();
		
		MazeSolverBFS mazeSolveBFS = new MazeSolverBFS(maze);
		mazeSolveBFS.solveBFS();
		ArrayList<Cell> bfsPath = mazeSolveBFS.returnPath();
		MazeSolverDFS mazeSolveDFS = new MazeSolverDFS(maze);
		mazeSolveDFS.solveDFS();
		ArrayList<Cell> dfsPath = mazeSolveDFS.returnPath();
		assertEquals(bfsPath.size(), dfsPath.size());
	}

	// Test case for Maze Size 7 x 7.
	@Test
	public void testCase7() {
		// Maze size 7 x 7.
		System.out.println("Maze dimensions: 7 x 7");
		Maze mazeCreator = new Maze(7);
		Cell[][] maze = mazeCreator.createMaze();

		MazeSolverBFS mazeSolveBFS = new MazeSolverBFS(maze);
		mazeSolveBFS.solveBFS();
		ArrayList<Cell> bfsPath = mazeSolveBFS.returnPath();
		MazeSolverDFS mazeSolveDFS = new MazeSolverDFS(maze);
		mazeSolveDFS.solveDFS();
		ArrayList<Cell> dfsPath = mazeSolveDFS.returnPath();
		assertEquals(bfsPath.size(), dfsPath.size());
	}

	// Test case for Maze Size 8 x 8.
	@Test
	public void testCase8() {
		// Maze size 8 x 8.
		System.out.println("Maze dimensions: 8 x 8");
		Maze mazeCreator = new Maze(8);
		Cell[][] maze = mazeCreator.createMaze();

		MazeSolverBFS mazeSolveBFS = new MazeSolverBFS(maze);
		mazeSolveBFS.solveBFS();
		ArrayList<Cell> bfsPath = mazeSolveBFS.returnPath();
		MazeSolverDFS mazeSolveDFS = new MazeSolverDFS(maze);
		mazeSolveDFS.solveDFS();
		ArrayList<Cell> dfsPath = mazeSolveDFS.returnPath();
		assertEquals(bfsPath.size(), dfsPath.size());
	}

	// Test case for Maze Size 9 x 9.
	@Test
	public void testCase9() {
		// Maze size 9 x 9.
		System.out.println("Maze dimensions: 9 x 9");
		Maze mazeCreator = new Maze(9);
		Cell[][] maze = mazeCreator.createMaze();

		MazeSolverBFS mazeSolveBFS = new MazeSolverBFS(maze);
		mazeSolveBFS.solveBFS();
		ArrayList<Cell> bfsPath = mazeSolveBFS.returnPath();
		MazeSolverDFS mazeSolveDFS = new MazeSolverDFS(maze);
		mazeSolveDFS.solveDFS();
		ArrayList<Cell> dfsPath = mazeSolveDFS.returnPath();
		assertEquals(bfsPath.size(), dfsPath.size());
	}

	// Test case for Maze Size 10 x 10.
	@Test
	public void testCase10() {
		// Maze size 10 x 10.
		System.out.println("Maze dimensions: 10 x 10");
		Maze mazeCreator = new Maze(10);
		Cell[][] maze = mazeCreator.createMaze();

		MazeSolverBFS mazeSolveBFS = new MazeSolverBFS(maze);
		mazeSolveBFS.solveBFS();
		ArrayList<Cell> bfsPath = mazeSolveBFS.returnPath();
		MazeSolverDFS mazeSolveDFS = new MazeSolverDFS(maze);
		mazeSolveDFS.solveDFS();
		ArrayList<Cell> dfsPath = mazeSolveDFS.returnPath();
		assertEquals(bfsPath.size(), dfsPath.size());
	}
}
