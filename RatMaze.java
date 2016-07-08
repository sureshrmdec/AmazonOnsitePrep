package leetcode;

public class RatMaze {
	/*
	 * If destination is reached print the solution matrix Else a) Mark current
	 * cell in solution matrix as 1. b) Move forward in horizontal direction and
	 * recursively check if this move leads to a solution. c) If the move chosen
	 * in the above step doesn't lead to a solution then move down and check if
	 * this move leads to a solution. d) If none of the above solutions work
	 * then unmark this cell as 0 (BACKTRACK) and return false.
	 */
	final int N = 4;

	/*
	 * A utility function to print solution matrix sol[N][N]
	 */
	void printSolution(int sol[][]) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(" " + sol[i][j] + " ");
			System.out.println();
		}
	}

	/*
	 * A utility function to check if x,y is valid index for N*N maze
	 */
	boolean isSafe(int maze[][], int x, int y) {
		// if (x,y outside maze) return false
		return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1);
	}

	/*
	 * This function solves the Maze problem using Backtracking. It mainly uses
	 * solveMazeUtil() to solve the problem. It returns false if no path is
	 * possible, otherwise return true and prints the path in the form of 1s.
	 * Please note that there may be more than one solutions, this function
	 * prints one of the feasible solutions.
	 */
	boolean solveMaze(int maze[][]) {
		int sol[][] = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

		if (solveMazeUtil(maze, 0, 0, sol) == false) {
			System.out.print("Solution doesn't exist");
			return false;
		}

		printSolution(sol);
		return true;
	}

	/*
	 * A recursive utility function to solve Maze problem
	 */
	boolean solveMazeUtil(int maze[][], int x, int y, int sol[][]) {
		// if (x,y is goal) return true
		if (x == N - 1 && y == N - 1) {
			sol[x][y] = 1;
			return true;
		}

		// Check if maze[x][y] is valid
		if (isSafe(maze, x, y) == true) {
			// mark x,y as part of solution path
			sol[x][y] = 1;

			/* Move forward in x direction */
			if (solveMazeUtil(maze, x + 1, y, sol))
				return true;

			/*
			 * If moving in x direction doesn't give solution then Move down in
			 * y direction
			 */
			if (solveMazeUtil(maze, x, y + 1, sol))
				return true;

			/*
			 * If none of the above movements work then BACKTRACK: unmark x,y as
			 * part of solution path
			 */
			sol[x][y] = 0;
			return false;
		}

		return false;
	}

	public int maxSegment(int[] nums) {
	    if(nums == null || nums.length == 0) {
	        return 0;
	    }
	    int  max =1;
	    int  len = nums.length;
	    int index = len -2;
	    int current = nums[len - 1] ;
	    int count = 1;
	    while( index >= 0) {
	        if(current >= nums[index]) {
	            count++;
	        } else {
	            max = Math.max(max,count);
	            count = 1;
	            current = nums[index];
	        }
	       index--; 
	    }
	    return max;
	}
}
