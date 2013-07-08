package com.sirma.itt.javacourse.threads;


/**
 * A counter thread. Counts to the given number (long value) and then returns
 * execution. Can be interrupted by anotehr thread.
 */
public class CounterThread extends Thread {
	private final long max;
	private long count;

	/**
	 * constrcuts the thread and starts it.
	 * 
	 * @param max
	 *            is the max value that the thread will count to.
	 */
	public CounterThread(long max) {
		super("Counter");
		this.max = max;
		this.count = 0;
		start();
	}

	@Override
	public void run() {
		for (long i = 0; i < max; i++) {
			if (Thread.interrupted()) {
				return;
			}
			count++;
		}
	}

	/**
	 * A getter method for the count.
	 * 
	 * @return the counter value
	 */
	public long getCount() {
		return count;
	}
}