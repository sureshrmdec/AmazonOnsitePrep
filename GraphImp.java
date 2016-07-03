package leetcode;

import java.util.ArrayList;

/*
 * Well to form it in a proper object oriented way,
 *  i would make a class of edge which would contain the nodes it connects and its weight, 
 *  another class of node which contains a list of edges(going in or coming out from that node). 
 *  Lastly i would make a class of graph which would contain list of nodes it connects.
 */

/*
 * Serialization of objects with cyclic reference:
 * Java keeps track of the objects that have been written to the stream, 
 * and subsequent instances are written as an ID, not an actual serialized object.
 * So, for your example, if you write instance "a" to the stream, the stream gives that object a unique ID (let's say "1"). 
 * As part of the serialization of "a", you have to serialize "b", and the stream gives it another id ("2"). 
 * If you then write "b" to the stream, the only thing that is written is the ID, not the actual object.
 * 
 * The input stream does the same thing in reverse: for each object that it reads from the stream, 
 * it assigns an ID number using the same algorithm as the output stream, 
 * and that ID number references the object instance in a map. 
 * When it sees an object that was serialized using an ID, it retrieves the original instance from the map.
 * 
 * This is how the API docs describe it:
 * Multiple references to a single object are encoded using a reference sharing mechanism 
 * so that graphs of objects can be restored to the same shape as when the original was written.
 * This behavior can cause problems: because the stream holds a hard reference to each object 
 * (so that it knows when to substitute the ID), you can run out of memory 
 * if you write a lot of transient objects to the stream. 
 * You solve that by calling reset().
 */
public class GraphImp {
	Node list[];
	int vertices;

	GraphImp(int v) {// v is the number of vertices in this graph I think...
		vertices = v;
		list = new Node[v];
		for (int i = 0; i < v; i++)
			list[i] = new Node();
	}

	public void addEdge(int s, int d, int w) {
		Edge e1 = new Edge(s, d, w);
		list[s].AdjacencyList.add(e1);// If it were an undirected graph add the
										// edge to source as well as destination
										// node
	}

	public void bfs() {
		bfs(0);
	}

	private void bfs(int s) {
		boolean visited[] = new boolean[vertices];
		visited[s] = true;
		ArrayList<Integer> q = new ArrayList<Integer>();
		while (!q.isEmpty()) {
			int next = q.remove(0);
			System.out.println("Visited " + next + "node");
			for (int i = 0; i < list[next].AdjacencyList.size(); i++) {
				Edge e1 = list[next].AdjacencyList.get(i);
				if (!visited[e1.destination]) {
					visited[e1.destination] = true;
					q.add(e1.destination);
				}
			}
		}
	}
	
	class Edge {
		int source, destination, weight;

		Edge(int s, int d, int w) {
			source = s;
			destination = d;
			weight = w;
		}
	}

	class Node {
		ArrayList<Edge> AdjacencyList = new ArrayList<Edge>();
	}
}
