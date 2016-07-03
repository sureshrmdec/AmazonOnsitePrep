package leetcode;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.Deque;
import java.util.HashMap;

public class LogFile {

	Deque<LogTest> deque;// used to store logs
	HashMap<Long, LogTest> map;

	public LogFile() {
		deque = new ArrayDeque<LogTest>();
		map = new HashMap<Long, LogFile.LogTest>();
	}

	public boolean timeOut(String then) {
		String now = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return getMinDiff(now, then) > 60;
	}

	private int getMinDiff(String now, String then) {
		// TODO Auto-generated method stub
		return 0;
	}

	// return false when a log with same ID is already in the map.
	// remove outdated logs as well. O(n)
	// or using binary search to make it O(log(n)).
	public boolean insert(LogTest log) {
		if (!map.containsKey(log.id)) {
			// all time out
			if (timeOut(deque.peekLast().getDate())) {
				deque.clear();
				map.clear();
			} else {
				while (timeOut(deque.peek().getDate())) {
					map.remove(deque.peek().getID());
					deque.pollFirst();
				}
			}
			deque.offerFirst(log);
			map.put(log.getID(), log);
			return true;
		} else {
			return false;
		}
	}

	// O(1)
	public LogTest lookup(long id) {
		if (map.containsKey(id)) {
			return map.get(id);
		} else {
			// return new LogTest(Long.MIN_VALUE);// Need to check the id
			return null;
		}
	}

	public class LogTest {
		long id;
		String date;
		String content;
		String level;// This could be a Enum class (eg, WARN, ERROR, INFO,
						// DEBUG, TRACE)

		public LogTest(Long id, String date, String content, String level) {
			this.id = id;
			this.date = date;
			this.content = content;
			this.level = level;
		}

		public LogTest(Long id) {
			this.id = id;
		}

		public String getDate() {
			return date;
		}

		public long getID() {
			return id;
		}

		// getter and setter
	}
}
