package com.sirma.itt.javacourse.threads;

/**
 * Counts to a specific number, with a count interval of 1 second and when done,
 * stops the other thread specified.
 */
public class WaitingThread extends Thread {
	private final int start;
	private final int end;
	private SleepingThread other;
	private final String name;

	/**
	 * Set the other timer instance for this one.
	 * 
	 * @param other
	 *            is the timer instance to set
	 */
	public void setOther(SleepingThread other) {
		this.other = other;
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
	public WaitingThread(int start, int end, SleepingThread other, String name) {
		this.start = start;
		this.end = end;
		this.other = other;
		this.name = name;
		this.start();
	}

	@Override
	public synchronized void run() {
		for (int i = start; i <= end; i++) {
			try {
				System.out.println(this.name + ": " + i);
				wait();
			} catch (InterruptedException e) {
				System.out.println(this.name + " was interrupted");
				return;
			}
		}
		System.out.println(name + " successfully counted to " + end);
		if (other != null) {
			other.interrupt();
		}
	}
}
