package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeLadder {
	/*
	 * The idea is to consider the given snake and ladder board as a directed graph 
	 * with number of vertices equal to the number of cells in the board. 
	 * The problem reduces to finding the shortest path in a graph. 
	 * Every vertex of the graph has an edge to next six vertices
	 *  if next 6 vertices do not have a snake or ladder. 
	 *  If any of the next six vertices has a snake or ladder, 
	 *  then the edge from current vertex goes to the top of the ladder or tail of the snake. 
	 *  Since all edges are of equal weight, we can efficiently find shortest path using Breadth First Search of the graph.
	 */

	private class queueEntry{
		int v;// Vertex number
		int dist;// Distance of this vertex from source
		public queueEntry(int v, int dist){
			this.v = v;
			this.dist = dist;
		}
		public queueEntry(int dist){
			this.dist = dist;
		}
	}
	// This function returns minimum number of dice throws required to
	// Reach last cell from 0'th cell in a snake and ladder game.
	// move[] is an array of size N where N is no. of cells on board
	// If there is no snake or ladder from cell i, then move[i] is -1
	// Otherwise move[i] contains cell to which snake or ladder at i
	// takes to.
	private static int getMinDiceThrows(int[] move, int N){
		// The graph has N vertices. Mark all the vertices as not visited
		boolean[] visited = new boolean[N];
		// queue for BFS
		Queue<queueEntry> q = new LinkedList<queueEntry>();
		// Mark the node 0 as visited and enqueue it.
		visited[0] = true;
		queueEntry qentry = new SnakeLadder().new queueEntry(0, 0);
		q.add(qentry);
		// Do a BFS starting from vertex at index 0
		queueEntry ret = null;
		while(!q.isEmpty()){
			ret = q.peek();
			int v = ret.v;
			// If front vertex is the destination vertex, we are done
			if(v == N - 1){
				break;
			}
			 // Otherwise dequeue the front vertex and enqueue
	        // its adjacent vertices (or cell numbers reachable
	        // through a dice throw)
			q.remove();
			for(int j=v+1;j<=(v+6) && j<N; j++){
				if(!visited[j]){
					queueEntry aEntry = new SnakeLadder().new queueEntry(ret.dist + 1);
					visited[j] = true;
					
					// Check if there a snake or ladder at 'j'
	                // then tail of snake or top of ladder
	                // become the adjacent of 'i'
					if(move[j] != -1){
						aEntry.v = move[j];
					}else{
						aEntry.v = j;
					}
					q.add(aEntry);
				}
			}
		}
		// We reach here when 'qe' has last vertex
	    // return the distance of vertex in 'qe'
		return ret.dist;
	}
	
	
	public static void main(String[] args){
		// Let us construct the board given in above diagram
	    int N = 30;
	    int[] moves = new int[N];
	    for (int i = 0; i<N; i++){
	        moves[i] = -1;
	    }
	 
	    // Ladders
	    moves[2] = 21;
	    moves[4] = 7;
	    moves[10] = 25;
	    moves[19] = 28;
	 
	    // Snakes
	    moves[26] = 0;
	    moves[20] = 8;
	    moves[16] = 3;
	    moves[18] = 6;
	 
	    System.out.println("Min Dice throws required is " + getMinDiceThrows(moves, N));
	}
	
}
