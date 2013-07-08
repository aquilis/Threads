package com.sirma.itt.javacourse.threads;

/**
 * Tests the coordination of the both threads.
 */
public final class SynchronizedThreadsTester {
	/**
	 * A private constrcutor.
	 */
	private SynchronizedThreadsTester() {
	}

	/**
	 * entry point.
	 * 
	 * @param args
	 *            are the cmd args
	 * @throws InterruptedException
	 *             if a thread gets interrupted
	 */
	public static void main(String[] args) throws InterruptedException {
		// the coordinator object
		CountersMaster master = new CountersMaster();
		new SyncThread(master, 1, 8, "timer 1");
		Thread.sleep(100);
		new SyncThread(master, 1, 12, "timer 2");
		Thread.sleep(100);
		new SyncThread(master, 1, 16, "timer 3");
	}
}
