package com.sirma.itt.javacourse.threads;

/**
 * Counts from the start time to the end time and when the counting is done,
 * interrupts the specified other thread to stop it.
 */
public class Counters implements Runnable {
	private final int start;
	private final int end;
	private Counters other;
	private final String name;
	private boolean mustStop;

	/**
	 * Set the other timer instance for this one.
	 * 
	 * @param other
	 *            is the timer instance to set
	 */
	public void setOther(Counters other) {
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
	public Counters(int start, int end, Counters other, String name) {
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