package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule {
	/*
	 * There are a total of n courses you have to take, labeled from 0 to n - 1.
	 * 
	 * Some courses may have prerequisites, for example to take course 0 you
	 * have to first take course 1, which is expressed as a pair: [0,1]
	 * 
	 * Given the total number of courses and a list of prerequisite pairs, is it
	 * possible for you to finish all courses?
	 * 
	 * For example:
	 * 
	 * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you
	 * should have finished course 0. So it is possible.
	 * 
	 * 2, [[1,0],[0,1]] There are a total of 2 courses to take. To take course 1
	 * you should have finished course 0, and to take course 0 you should also
	 * have finished course 1. So it is impossible.
	 * 
	 * Note: The input prerequisites is a graph represented by a list of edges,
	 * not adjacency matrices.
	 */

	// The basic idea is to use Topological algorithm:
	// put NODE with 0 indgree into the queue,
	// then make indegree of NODE's successor dereasing 1.
	// Keep the above steps with BFS.
	// Finally, if each node' indgree equals 0,
	// then it is validated DAG (Directed acyclic graph),
	// which means the course schedule can be finished.
	public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
		if (numCourses == 0 || prerequisites.length == 0) {
			return true;
		}

		// Convert graph presentation from edges to indegree of adjacent list.
		int indegree[] = new int[numCourses];
		// Indegree - how many prerequisites are needed.
		for (int i = 0; i < prerequisites.length; i++) {
			indegree[prerequisites[i][0]]++;
		}

		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < numCourses; i++)
			if (indegree[i] == 0) {
				queue.add(i);
			}

		// How many courses don't need prerequisites.
		int canFinishCount = queue.size();
		while (!queue.isEmpty()) {
			// Already finished this prerequisite course.
			int prerequisite = queue.remove();
			for (int i = 0; i < prerequisites.length; i++) {
				if (prerequisites[i][1] == prerequisite) {
					indegree[prerequisites[i][0]]--;
					if (indegree[prerequisites[i][0]] == 0) {
						canFinishCount++;
						queue.add(prerequisites[i][0]);
					}
				}
			}
		}

		return (canFinishCount == numCourses);
	}

	// DFS
	// The basic idea is using DFS to check if the current node was already
	// included in the traveling path
	public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
		ArrayList[] graph = new ArrayList[numCourses];
		for (int i = 0; i < numCourses; i++)
			graph[i] = new ArrayList();

		boolean[] visited = new boolean[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			graph[prerequisites[i][1]].add(prerequisites[i][0]);
		}

		for (int i = 0; i < numCourses; i++) {
			if (!dfs(graph, visited, i))
				return false;
		}
		return true;
	}

	private boolean dfs(ArrayList[] graph, boolean[] visited, int course) {
		if (visited[course])
			return false;
		else
			visited[course] = true;
		;

		for (int i = 0; i < graph[course].size(); i++) {
			if (!dfs(graph, visited, (int) graph[course].get(i)))
				return false;
		}
		visited[course] = false;
		return true;
	}
}
