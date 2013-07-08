package com.sirma.itt.javacourse.threads;

/**
 * A counter thread which is entangled with another one. If the thread
 * successfully counts to its end time, it stops the other, or vice versa.
 */
public class EntangledCounter implements Runnable {
	private final int start;
	private final int end;
	private EntangledCounter other;
	private final String name;
	private boolean mustStop;

	/**
	 * Set the other timer instance for this one.
	 * 
	 * @param other
	 *            is the timer instance to set
	 */
	public void setOther(EntangledCounter other) {
		this.other = other;
	}

	/**
	 * Set the must stop trigger that tells the thread to stop execution.
	 * 
	 * @param mustStop
	 *            is the boolean variable to set
	 */
	public void setMustStop(boolean mustStop) {
		this.mustStop = mustStop;
	}

	/**
	 * Construct the timer.
	 * 
	 * @param start
	 *            is the start time
	 * @param end
	 *            is the end time
	 * @param other
	 *            is the other timer to interrupt
	 * @param name
	 *            is the name of the current timer
	 */
	public EntangledCounter(int start, int end, EntangledCounter other,
			String name) {
		this.start = start;
		this.end = end;
		this.other = other;
		this.name = name;
	}
	@Override
	public synchronized void run() {
		for (int i = start; i <= end; i++) {
			if (mustStop) {
				System.out.println(this.name + " was stopped at " + i);
				return;
			}
			System.out.println(this.name + ": " + i);
		}
		System.out.println(name + " successfully counted to " + end);
		if (other != null) {
			other.setMustStop(true);
		}
	}
}