package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KClosestPoints {

	// heap
	public List<Point> findKClosest(Point[] p, int k) {
		PriorityQueue<Point> pq = new PriorityQueue<>(k, new Comparator<Point>() {
			@Override
			public int compare(Point a, Point b) {
				return (b.x * b.x + b.y * b.y) - (a.x * a.x + a.y * a.y);
			}
		});

		for (int i = 0; i < p.length; i++) {
			if (i < k)
				pq.offer(p[i]);
			else {
				Point temp = pq.peek();
				if ((p[i].x * p[i].x + p[i].y * p[i].y) - (temp.x * temp.x + temp.y * temp.y) < 0) {
					pq.poll();
					pq.offer(p[i]);
				}
			}
		}

		List<Point> x = new ArrayList<>();
		while (!pq.isEmpty())
			x.add(pq.poll());

		return x;
	}

	
	// May not necessary =============================================================================================================================
	// quick select
	public static double kthSmallest(final double[] A, final int p, final int r, final int k) {
		if (p < r) {
			final int q = RandomizedPartition(A, p, r);

			final int n = q - p + 1;
			if (k == n) {
				return A[q];
			} else if (k < n) {
				return kthSmallest(A, p, q - 1, k);
			} else {
				return kthSmallest(A, q + 1, r, k - n);
			}
		} else {
			return Double.MIN_VALUE;
		}
	}

	public static Point[] closestkWithOrderStatistics(final Point points[], final int k) {
		final int n = points.length;
		final double[] dist = new double[n];
		for (int i = 0; i < n; i++) {
			dist[i] = Math.sqrt(points[i].x * points[i].x + points[i].y * points[i].y);
		}
		final double kthMin = kthSmallest(dist, 0, n - 1, k);

		final Point[] result = new Point[k];
		for (int i = 0, j = 0; i < n && j < k; i++) {
			final double d = Math.sqrt(points[i].x * points[i].x + points[i].y * points[i].y);
			if (d <= kthMin) {
				result[j++] = points[i];
			}
		}

		return result;
	}

	private static void swap(final double input[], final int i, final int j) {
		final double temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

	private static int partition(final double[] A, final int p, final int r) {
		final double pivot = A[r];
		int i = p - 1;
		int j = p;

		for (j = p; j < r; j++) {
			if (A[j] <= pivot) {
				swap(A, ++i, j);
			}
		}

		swap(A, i + 1, r);
		return i + 1;
	}

	private static int RandomizedPartition(final double[] A, final int p, final int r) {
		final int i = (int) Math.round(p + Math.random() * (r - p));
		swap(A, i, r);
		return partition(A, p, r);
	}

	public class Point {
		public int x;
		public int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
