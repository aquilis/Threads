package com.sirma.itt.javacourse.threads;

/**
 * Starts to sleeping threads to test them.
 */
public final class SleepingThreadsTester {
	/**
	 * A private constructor.
	 */
	private SleepingThreadsTester() {

	}

	/**
	 * entnry point for testing.
	 * 
	 * @param args
	 *            are the cmd args
	 */
	public static void main(String[] args) {
		SleepingThread t1 = new SleepingThread(0, 10, null, "thread1");
		SleepingThread t2 = new SleepingThread(0, 5, null, "thread2");
		t1.setOther(t2);
		t2.setOther(t1);
		new Thread(t1);
		new Thread(t2);
	}
}
