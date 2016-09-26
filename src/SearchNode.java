/**
* @author Caroline Danzi
* @version 28 September 2016
*/

public class SearchNode {
	private int row;
	private int col;
	private int cost;
	private boolean isFinish;
	private char action;
	private SearchNode parent;
	
	public SearchNode(int row, int col, int cost, boolean isFinish,
			char action, SearchNode parent) {
		this.row = row;
		this.col = col;
		this.cost = cost;
		this.isFinish = isFinish;
		this.action = action;
		this.parent = parent;
	}

	public SearchNode(int row, int col, char action, SearchNode parent) {
		super();
		this.row = row;
		this.col = col;
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

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean isFinish() {
		return isFinish;
	}

	public void setFinish(boolean isFinish) {
		this.isFinish = isFinish;
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
	
}