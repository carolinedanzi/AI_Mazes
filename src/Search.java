import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Executes DFS, BFS, and UCS on a maze to print the path and cost of the maze solution
 * @author Caroline Danzi
 * @version 2016-09-28
 * I verify this is my own work, though I received help from Dr. Zmuda in office hours
 *
 */
public class Search {
	public static void main(String[] args) {		
		Maze maze;
		for(int i = 0; i < args.length; i++) {
			maze = new Maze(args[i]);
			System.out.println(args[i]);
			// Execute and print the output of the search algorithms
			System.out.println(BFS(maze));
			System.out.println(DFS(maze));
			System.out.println(UCS(maze));
		}
	}
	
	/**
	 * Breadth-First Search to solve a maze problem and find a path 
	 * and the cost of that path
	 * @param maze the maze problem to solve
	 * @return a String containing the path to take from start to finish
	 *         and the cost of that path
	 */
	public static String BFS(Maze maze) {
		SearchNode node = maze.findStart();
		// Note: I will use a LinkedList as a FIFO queue (only use add() and poll())
		LinkedList<SearchNode> frontier = new LinkedList<SearchNode>();
		HashSet<SearchNode> explored = new HashSet<SearchNode>();
		frontier.add(node);
		
		while(!frontier.isEmpty()) {
			// Take off the the first node in the queue and add it to the explored list
			node = frontier.poll();
			explored.add(node);
			ArrayList<SearchNode> neighbors = maze.getNeighbors(node);
			for(SearchNode n : neighbors) {
				// For each neighbor, get the cost and check if it is the finish
				// before adding it to the frontier if it has not been seen
				n.setCost(n.getParent().getCost() + maze.getCost(n));
				if(n.getVal() == 'F') {
					return "BFS: " + getPath(n) + " cost = " + n.getCost();
				}
				if(!frontier.contains(n) && !explored.contains(n)) {
					frontier.add(n);
				}
			}
		}
		return "BFS: Failed to find finish";
	}
	
	/**
	 * Depth-First Search of this maze to find a route from start to finish
	 * @param maze the maze problem to solve
	 * @return a String that contains the path from start to finish and the cost of that path
	 */
	public static String DFS(Maze maze) {
		// Set up frontier (represented by a Stack) and explored set
		SearchNode node = maze.findStart();
		Stack<SearchNode> frontier = new Stack<SearchNode>();
		HashSet<SearchNode> explored = new HashSet<SearchNode>();
		frontier.push(node);
		
		while(!frontier.isEmpty()) {
			node = frontier.pop();
			// If this node is the finish, we have found the end so return the 
			// path and cost
			if(node.getVal() == 'F') {
				return "DFS: " + getPath(node) + " cost = " + node.getCost();
			}
			// Otherwise, add the node to the explored list and get its neighbors
			explored.add(node);
			ArrayList<SearchNode> neighbors = maze.getNeighbors(node);
			for(SearchNode n : neighbors) {
				// Do not add the node to the frontier if it is either currently
				// in the frontier or in the explored set
				if(!frontier.contains(n) && !explored.contains(n)) {
					// Store the total cost from starting node to this node
					n.setCost(n.getParent().getCost() + maze.getCost(n));
					frontier.push(n);
				}
			}
		}
		return "DFS: Failed to find finish";
	}
	
	/**
	 * Implements Uniform Cost Search using a priority queue
	 * @param maze the maze problem to solve
	 * @return a String containing the path taken and the cost of that path
	 */
	public static String UCS(Maze maze) {
		// Initialize problem with the start space in the frontier 
		// and an empty explored set
		SearchNode node = maze.findStart();
		PriorityQueue<SearchNode> frontier = new PriorityQueue<SearchNode>();
		HashSet<SearchNode> explored = new HashSet<SearchNode>();
		frontier.add(node);
		
		// As long as the frontier is not empty, keep searching
		while(!frontier.isEmpty()) {
			// Get the first node - the one with the lowest cost
			node = frontier.poll();
			// If this node is the finish, we have found our solution so
			// we should print out the path we took and the total cost
			if(node.getVal() == 'F') {
				return "UCS: " + getPath(node) + " cost = " + node.getCost();
			}
			// Otherwise, add this node to the explored set
			explored.add(node);
			// For each neighboring space, if it is not already in the explored set
			// then calculate its cost and add it to the frontier. Check only the
			// explored set because even if the node appears in the frontier, the
			// cost could be different and thus worth investigating
			ArrayList<SearchNode> neighbors = maze.getNeighbors(node);
			for(SearchNode n : neighbors) {
				if(!explored.contains(n)) {
					// The cost of this node is the parent node's cost plus
					// the cost of this node, which comes from the original problem
					n.setCost(n.getParent().getCost() + maze.getCost(n));
					frontier.add(n);
				}
			}
		}
		// If we make it down here, we did not find a solution
		return "UCS: Failed to find finish";
	}
	
	/**
	 * Get the path from the start to this node, as a String
	 * @param node the node to trace back to start
	 * @return a String of actions representing the path to take from the starting
	 *         node to this node
	 */
	private static String getPath(SearchNode node) {
		String path = "";
		while(node.getParent() != null) {
			path = node.getAction() + path;
			node = node.getParent();
		}
		return path;
	}
}
