import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Represents a maze as a String array. Reads in a text file containing a 
 * description of the maze. This supports finding the neighbors of a space,
 * as well as getting the cost of entering that space (an edge space costs
 * 11 and any other space costs 1)
 * 
 * @author Caroline Danzi
 * @version 28 September 2016
 * I verify this is my own work. I did talk to Gianna Sheffield about different
 * ways to represent the maze, but we both had independently decided to use a
 * String array before even speaking with each other. 
 */
public class Maze {
	private int rows;
	private int cols;
	private String[] mazeMatrix;
	
	/**
	 * Constructor - creates a maze from a text file description of that maze
	 * @param file the name of the file containing this maze
	 */
	public Maze(String file) {
		parseMazeFromFile(file);
	}
	
	/**
	 * Parses a file containing a maze into a String array that contains each row
	 * of the maze. The first line of the file contains the number of rows and
	 * columns in the maze. 
	 * @param file The name of the file containing the maze 
	 */
	private void parseMazeFromFile(String file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			// Get the number of rows and columns, which are on the first line
			// Multiply by 2 and add 1 to calculate the actual number of rows 
			// and columns in the text representation of the maze
			String rowColLine = br.readLine();
			this.rows = Integer.parseInt(rowColLine.substring(0, rowColLine.indexOf(' '))) * 2 + 1;
			this.cols = Integer.parseInt(rowColLine.substring(rowColLine.indexOf(' ') + 1)) * 2 + 1;
			
			// There are actually rows in between the maze rows, as
			// well as the top and bottom rows indicating the outer walls.
			// The total rows in our matrix are 2*mazeRows plus 1
			mazeMatrix = new String[rows];
			
			// Add the maze rows to the String maze
			for(int i = 0; i < rows; i++) {
				mazeMatrix[i] = br.readLine();
			}
			br.close();
		} catch(Exception e) {
			System.err.println("Error: " + e);
		}
		
	}
	
	/**
	 * Finds the S in the maze, which indicates the start, and creates a
	 * SearchNode for that starting space. The node contains the row and column
	 * within the matrix, as well as the value of 'S', a cost of 0, and no parent.
	 * @return the SearchNode for the start of the maze
	 */
	public SearchNode findStart() {
		for(int row = 1; row < rows; row+=2) {
			for(int col = 1; col < cols - 1; col+=2) {
				if(mazeMatrix[row].charAt(col) == 'S') {
					SearchNode start = new SearchNode(row, col, 'S', 0, ' ', null);
					return start;
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns the matrix representation of this maze
	 * @return a String array representing the maze as a matrix
	 */
	public String[] getMatrix() {
		return this.mazeMatrix;
	}
	
	/**
	 * Gets the legal neighboring spaces for one space in the maze. It checks the surrounding
	 * directions (up, down, left, right) for walls. If there is no wall, create a new SearchNode
	 * for that space with the row, column, and node as the parent. The action is the direction
	 * you would take from the current node (up = N, down = S, left = W, right = E). The cost
	 * is set to 0, since some algorithms keep track of just the path cost and others the 
	 * total cost from start. 
	 * @param node the node whose neighbors we wish to find
	 * @return an ArrayList of SearchNode objects that represent the legal spaces accessible
	 *         from this node
	 */
	public ArrayList<SearchNode> getNeighbors(SearchNode node) {
		ArrayList<SearchNode> neighbors = new ArrayList<SearchNode>();
		int row = node.getRow(); // row in the actual maze
		int col = node.getCol(); // column in the actual maze
		
		// Check up - If this space is not in the row 1, add the space above it
		if(row > 1 && mazeMatrix[row - 1].charAt(col) != '-') {
			// Use row - 2 to get the row of the actual space, not the row with walls in between
			neighbors.add(new SearchNode(row - 2, col, mazeMatrix[row-2].charAt(col), 'N', node));
		}
		// Check down - If this space is not in the last row, add space below it
		if(row < rows - 2 && mazeMatrix[row + 1].charAt(col) != '-') {
			neighbors.add(new SearchNode(row + 2, col, mazeMatrix[row+2].charAt(col), 'S', node));
		}
		// Check left - If this space is not at left edge, add space to left
		if(col > 1 && mazeMatrix[row].charAt(col - 1) != '|') {
			neighbors.add(new SearchNode(row, col - 2, mazeMatrix[row].charAt(col-2), 'W', node));
		}
		// Check right - If this space is not at right edge, add space to right
		if(col < cols - 2 && mazeMatrix[row].charAt(col + 1) != '|') {
			neighbors.add(new SearchNode(row, col + 2, mazeMatrix[row].charAt(col+2), 'E', node));
		}
		return neighbors;
	}
	
	/**
	 * Gets the cost of traveling to a particular space in the maze
	 * The cost is 11 if the node is on the edge, or 1 otherwise
	 * @param node The space in the maze
	 * @return 11 if the space is on the outer edge of the maze and 1 otherwise
	 */
	public int getCost(SearchNode node) {
		int row = node.getRow();
		int col = node.getCol();
		// If we are at an edge, the cost is 11
		if(row <= 1 || row >= rows - 2 || col <= 1 || col >= cols - 2) {
			return 11;
		} else { // otherwise cost is 1
			return 1;
		}
	}
}
