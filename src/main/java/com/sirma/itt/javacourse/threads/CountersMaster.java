package com.sirma.itt.javacourse.threads;

/**
 * Controls and coordinates the counter threads.
 */
public class CountersMaster {
	// when set to true, the master will interrupt and stop all child threads
	private boolean stopAll = false;

	/**
	 * Sets the stopAll boolean variable that interrupts all active child
	 * threads.
	 * 
	 * @param stopAll
	 *            is the value to be set to the variable
	 */
	public void setStopAll(boolean stopAll) {
		this.stopAll = stopAll;
	}

	/**
	 * Used by the child threads to count.
	 * 
	 * @param invoker
	 *            is the thread that invokes the synchronized method
	 * @param threadName
	 *            is the thread na me
	 * @param counter
	 *            is the current count number
	 */
	public synchronized void count(Thread invoker, String threadName,
			int counter) {
		if (stopAll) {
			invoker.interrupt();
			return;
		}
		System.out.println(threadName + ": " + counter);
		notify();
	}
}
