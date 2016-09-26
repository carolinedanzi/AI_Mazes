import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Caroline Danzi
 * @version 28 September 2016
 *
 */
public class Maze {
	private int rows;
	private int cols;
	private SearchNode start;
	private String[] mazeMatrix;
	
	public static void main(String[] args) {
		// Get the maze file name from the arguments
		String mazeFile = args[0];
		// Create the maze
		Maze maze = new Maze(mazeFile);
		for(int i = 0; i < maze.mazeMatrix.length; i++) {
			System.out.println(maze.mazeMatrix[i]);
		}
	}
	
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
			String rowColLine = br.readLine();
			this.rows = Integer.parseInt(rowColLine.substring(0, rowColLine.indexOf(' ')));
			this.cols = Integer.parseInt(rowColLine.substring(rowColLine.indexOf(' ') + 1));
			
			// There are actually rows in between the maze rows, as
			// well as the top and bottom rows indicating the outer walls.
			// The total rows in our matrix are 2*mazeRows plus 1
			mazeMatrix = new String[rows * 2 + 1];
			
			// Add the maze rows to the String maze
			for(int i = 0; i < mazeMatrix.length; i++) {
				mazeMatrix[i] = br.readLine();
			}
			br.close();
		} catch(Exception e) {
			System.err.println("Error: " + e);
		}
		
	}
	
	// TODO: check for walls
	public ArrayList<SearchNode> getNeighbors(SearchNode node) {
		ArrayList<SearchNode> neighbors = new ArrayList<SearchNode>();
		int row = node.getRow(); // row in the actual maze
		int col = node.getCol(); // column in the actual maze
		int matrixRow = row * 2 + 1; // row in the String[] matrix
		int matrixCol = col * 2 + 1; // column in the String[] matrix
		// Check up - If this space is not in the first row, add the space above it
		if(row > 0 && mazeMatrix[matrixRow - 1].charAt(matrixCol) != '-') {
			neighbors.add(new SearchNode(row - 1, col, 'N', node));
		}
		// Check down - If this space is not in the last row, add space below it
		if(row < rows - 1) {
			neighbors.add(new SearchNode(row + 1, col, 'S', node));
		}
		// Check left - If this space is not at left edge, add space to left
		if(col > 0) {
			neighbors.add(new SearchNode(row, col - 1, 'W', node));
		}
		// Check right - If this space is not at right edge, add space to right
		if(col < cols - 1) {
			neighbors.add(new SearchNode(row, col + 1, 'E', node));
		}
		return neighbors;
	}
	
	/**
	 * Gets the cost of traveling to a particular space in the maze
	 * The cost is 11 if the node is on the edge, or 1 otherwise
	 * @param node The space in the maze
	 * @return 11 if the space is on the outer edge of the maze and 1 otherwise
	 */
	private int getCost(SearchNode node) {
		int row = node.getRow();
		int col = node.getCol();
		// If we are at an edge, the cost is 11
		if(row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
			return 11;
		} else { // otherwise cost is 1
			return 1;
		}
	}
	
	private int calcMatrix(int mazeRow) {
		return mazeRow * 2 + 1;
	}
}
