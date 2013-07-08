package com.sirma.itt.javacourse.threads;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

/**
 * Contains key-value pairs for a specific amount of time and if they are not
 * used during that time, they get deleted by the appropriate timer thread.
 */
public class TimeoutHashtable {
	private final long timeout;
	private volatile Map<String, Object> map = new HashMap<String, Object>();
	private volatile Map<String, Long> mapTimes = new HashMap<String, Long>();

	/**
	 * Constructs the hashtable with an initial timeout amount.
	 * 
	 * @param timeout
	 *            is the amount of time in milliseconds that each key-value pair
	 *            will stay in the hashtable, if it is not retrieved.
	 */
	public TimeoutHashtable(long timeout) {
		this.timeout = timeout;
		new Timer();
	}

	/**
	 * Iterates over the times hashmap and removes the expired keys, if any.
	 */
	private void checkTimeouts() {
		// System.out.println("Checking timeouts...");
		// the expired keys are first stored in a Queue and then removed
		// in order to avoid ConcurrentModificationException while iterating
		// over the hahsmap
		Queue<String> keysToRemove = new LinkedList<String>();
		for (String key : mapTimes.keySet()) {
			if (mapTimes.get(key) < System.currentTimeMillis()) {
				keysToRemove.add(key);
			}
		}
		while (keysToRemove.size() > 0) {
			String temp = keysToRemove.poll();
			mapTimes.remove(temp);
			map.remove(temp);
		}
	}

	/**
	 * Puts the given key-value pair into the hashmap.
	 * 
	 * @param key
	 *            is a specific key for searching.
	 * @param value
	 *            is any object to be put as a value to the given key
	 */
	public void put(String key, Object value) {
		map.put(key, value);
		mapTimes.put(key, System.currentTimeMillis() + timeout);
	}

	/**
	 * Retrieves the value coresponding to the given key.
	 * 
	 * @param key
	 *            is the key to search fmor
	 * @return the value coresponding to the given key
	 */
	public Object get(String key) {
		if (mapTimes.get(key) < System.currentTimeMillis()) {
			return null;
		} else {
			mapTimes.put(key, System.currentTimeMillis() + timeout);
			return map.get(key);
		}
	}

	/**
	 * Removes the given key-value pair from the map.
	 * 
	 * @param key
	 *            is the key to be removed
	 */
	public void remove(String key) {
		mapTimes.remove(key);
		map.remove(key);
	}

	/**
	 * Prints the hashtable on the console.
	 */
	public void printMe() {
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			System.out.println(key + " --> " + value);
		}
	}

	/**
	 * An inner counter thread that will update the global timer incrementing it
	 * each second.
	 */
	private class Timer extends Thread {
		/**
		 * Starts the timer thread.
		 */
		public Timer() {
			start();
		}

		/**
		 * main thread logic here.
		 */
		@Override
		public void run() {
			while (true) {
				if (mapTimes.size() == 0) {
					return;
				}
				checkTimeouts();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
