package com.sirma.itt.javacourse.threads;

/**
 * A thread counter class that can be coordinated with others using the
 * CountersMaster class, so when counting, each one waits for the others to
 * count and when the first one finishes, it interrupts and terminates all
 * others.
 */
public class SyncThread extends Thread {
	private final int start;
	private final int end;
	private final String name;
	private final CountersMaster master;

	/**
	 * Constructs the synced thread.
	 * 
	 * @param master
	 *            is the master object that coordinates the threads work
	 * @param start
	 *            is the start time
	 * @param end
	 *            is the end time
	 * @param name
	 *            is the name of the current timer thread
	 */
	public SyncThread(CountersMaster master, int start, int end, String name) {
		this.master = master;
		this.start = start;
		this.end = end;
		this.name = name;
		start();
	}

	@Override
	public synchronized void run() {
		for (int i = start; i <= end; i++) {
			master.count(this, name, i);
			try {
				this.wait(1000);
			} catch (InterruptedException e) {
				System.out.println(name + " was interrupted and terminated.");
				return;
			}
		}
		master.setStopAll(true);
		System.out.println(name + " successfully counted to " + end);
	}
}
