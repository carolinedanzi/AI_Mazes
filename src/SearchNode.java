/**
 * Nodes for this maze search problem need to keep track of the
 * row and column of the space, the value (character) at that space,
 * the cost of moving to the space, the action taken to get to that
 * space from the parent, and a reference to the parent node. 
* @author Caroline Danzi
* @version 28 September 2016
*/

public class SearchNode implements Comparable<SearchNode>{
	private int row;
	private int col;
	private char val;
	private int cost;
	private char action;
	private SearchNode parent;
	
	/**
	 * Constructor
	 * @param row the row of this space
	 * @param col the column of this space
	 * @param val the value (character) at this space
	 * @param cost the cost to get to this space
	 * @param action the action taken from parent to get to this space
	 * @param parent the parent space (the previous space along the path to get to this space)
	 */
	public SearchNode(int row, int col, char val, int cost,
			char action, SearchNode parent) {
		this.row = row;
		this.col = col;
		this.val = val;
		this.cost = cost;
		this.action = action;
		this.parent = parent;
	}

	/**
	 * Constructor - If the cost is not known
	 * @param row the row of this space
	 * @param col the column of this space
	 * @param val the value (character) at this space
	 * @param action the action taken from parent to get to this space
	 * @param parent the parent space (the previous space along the path to get to this space)
	 */
	public SearchNode(int row, int col, char val, char action, SearchNode parent) {
		this.row = row;
		this.col = col;
		this.val = val;
		this.action = action;
		this.parent = parent;
	}
	
	/**
	 * To compare spaces, we need to know which space is cheaper 
	 * to get to
	 * @param other the SearchNode to compare
	 * @return a negativie integer if this space has a cheaper cost than the other space
	 */
	@Override
	public int compareTo(SearchNode other) {
		return this.cost - other.getCost();
	}
	
	/**
	 * Allow SearchNodes to be hashable. The three fields that uniquely identify
	 * a node are its row, column, and value
	 * @return a unique integer for this node
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		result = prime * result + val;
		return result;
	}
	
	/**
	 * Test if two SearchNodes are equal. The three fields that uniquely identify
	 * a node are its row, column, and value. Two nodes can represent the same
	 * space but have a different cost, action, and parent.
	 * @param obj the other SearchNode for checking equality with this node
	 * @return true if this SearchNode references the same space in the maze as
	 *         the other SearchNode
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchNode other = (SearchNode) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		if (val != other.val)
			return false;
		return true;
	}

	/**
	 * Return a string representation of this SearchNode
	 * @return a String representing the values of this SearchNode
	 */
	public String toString() {
		return "row " + row + "; col " + col + "; val " + val + "; cost " + cost + "; action " + action;
	}

	/**
	 * Getter for row
	 * @return the row of this space
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Getter for column
	 * @return the column
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Getter for the character value of this space
	 * @return the character at this space (either '.', 'S', or 'F')
	 */
	public char getVal() {
		return val;
	}

	/**
	 * Getter for cost of this space
	 * @return the cost of this space
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Setter for the cost of this space
	 * @param cost the cost of this space
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * Getter for the action taken to enter this space. N = north, S = south,
	 * E = east, and W = west
	 * @return the character representing the action taken to enter this space
	 *         (either 'N', 'S', 'E', 'W')
	 */
	public char getAction() {
		return action;
	}

	/**
	 * Getter for the parent node of this space
	 * @return the parent node of this space
	 */
	public SearchNode getParent() {
		return parent;
	}
}