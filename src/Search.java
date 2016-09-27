import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Executes DFS, BFS, and UCS on a maze to print the path and cost of the maze solution
 * @author Caroline Danzi
 * @version 2016-09-28
 * I verify this is my own work
 *
 */
public class Search {
	public static void main(String[] args) {
		
		// Get the maze file name from the arguments
		String mazeFile = args[0];
		// Create the maze
		Maze maze = new Maze(mazeFile);
		for(int i = 0; i < maze.getMatrix().length; i++) {
			System.out.println(maze.getMatrix()[i]);
		}
		ArrayList<SearchNode> nodes = maze.getNeighbors(maze.findStart());
		for(SearchNode n : nodes) {
			System.out.println(n.toString());
		}
		
		ArrayList<SearchNode> test = new ArrayList<SearchNode>();
		test.add(maze.findStart());
		System.out.println(test.contains(maze.findStart()));
		System.out.println(UCS(maze));
	}
	
	public static String BFS(Maze problem) {
//		SearchNode node = problem.findStart();
//		Queue<SearchNode> frontier = new Queue<SearchNode>();
//		HashSet<SearchNode> explored = new HashSet<SearchNode>();
//		frontier.add(node);
//		
//		while(!frontier.isEmpty()) {
//			node = frontier.
//		}
		return "";
	}
	
	public static String DFS(Maze problem) {
		SearchNode node = problem.findStart();
		Stack<SearchNode> frontier = new Stack<SearchNode>();
		HashSet<SearchNode> explored = new HashSet<SearchNode>();
		frontier.push(node);
		
		while(!frontier.isEmpty()) {
		//for(int i = 0; i < 4; i++) {
			node = frontier.pop();
			System.out.println("Current node: " + node.toString());
			if(node.getVal() == 'F') {
				return "DFS: " + getPath(node) + " cost = " + node.getCost();
			}
			explored.add(node);
			ArrayList<SearchNode> neighbors = problem.getNeighbors(node);
			for(SearchNode n : neighbors) {
				if(!frontier.contains(n) && !explored.contains(n)) {
					n.setCost(problem.getCost(n));
					frontier.push(n);
				}
			}
			System.out.println("Added neighbors to frontier");
			for(SearchNode n : frontier) {
				System.out.println("Frontier: " + n.toString());
			}
			for(SearchNode n : explored) {
				System.out.println("Explored: " + n.toString());
			}
			System.out.println();
		}
		return "DFS failed to find finish";
	}
	
	// TODO: If neighbor is in the explored list, then do not add again to frontier.
	// But if it is in the frontier, add again because it may have a different cost
	public static String UCS(Maze problem) {
		// Initialize problem with the start space in the frontier 
		// and an empty explored set
		SearchNode node = problem.findStart();
		PriorityQueue<SearchNode> frontier = new PriorityQueue<SearchNode>();
		HashSet<SearchNode> explored = new HashSet<SearchNode>();
		frontier.add(node);
		
		System.out.println("Initial set up for UCS complete");
		
		// As long as the frontier is not empty, keep searching
		while(!frontier.isEmpty()) {
			// Get the first node - the one with the lowest cost
			node = frontier.poll();
			System.out.println("Current node: " + node.toString());
			// If this node is the finish, we have found our solution so
			// we should print out the path we took and the total cost
			if(node.getVal() == 'F') {
				return "UCS: " + getPath(node) + " cost = " + node.getCost();
			}
			// Otherwise, add this node to the explored set
			explored.add(node);
			// For each neighboring space, if it is not already in the frontier
			// or explored sets then calculate its cost and add it to the frontier
			ArrayList<SearchNode> neighbors = problem.getNeighbors(node);
			for(SearchNode n : neighbors) {
				System.out.println("Explored contains node? " + explored.contains(n));
				if(!frontier.contains(n) && !explored.contains(n)) {
					// The cost of this node is the parent node's cost plus
					// the cost of this node, which comes from the original problem
					n.setCost(node.getCost() + problem.getCost(n));
					frontier.add(n);
				}
			}
			System.out.println("Added neighbors to frontier");
			for(SearchNode n : frontier) {
				System.out.println("Frontier: " + n.toString());
			}
			for(SearchNode n : explored) {
				System.out.println("Explored: " + n.toString());
			}
		}
		// If we make it down here, we did not find a solution
		return "UCS: Failed to find solution";
	}
	
	private static String getPath(SearchNode node) {
		String path = "";
		while(node.getParent() != null) {
			path = node.getAction() + path;
			node = node.getParent();
		}
		return path;
	}
}
