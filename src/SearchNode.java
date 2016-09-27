/**
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
	
	public SearchNode(int row, int col, char val, int cost,
			char action, SearchNode parent) {
		this.row = row;
		this.col = col;
		this.val = val;
		this.cost = cost;
		this.action = action;
		this.parent = parent;
	}

	public SearchNode(int row, int col, char val, char action, SearchNode parent) {
		this.row = row;
		this.col = col;
		this.val = val;
		this.action = action;
		this.parent = parent;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public char getVal() {
		return val;
	}

	public void setVal(char val) {
		this.val = val;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public char getAction() {
		return action;
	}

	public void setAction(char action) {
		this.action = action;
	}

	public SearchNode getParent() {
		return parent;
	}

	public void setParent(SearchNode parent) {
		this.parent = parent;
	}
	
	@Override
	public int compareTo(SearchNode other) {
		return other.getCost() - this.cost;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		result = prime * result + val;
//		result = prime * result + cost;
		return result;
	}
	
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
//		if (cost != other.cost)
//			return false;
		if (row != other.row)
			return false;
		if (val != other.val)
			return false;
		return true;
	}

	public String toString() {
		return "row " + row + "; col " + col + "; val " + val + "; cost " + cost + "; action " + action;
	}
}