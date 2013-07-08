package com.sirma.itt.javacourse.threads;

/**
 * Tests the two counter threads.
 */
public final class EntangledCounterTester {
	/**
	 * A private constrcutor.
	 */
	private EntangledCounterTester() {
	}

	/**
	 * Creates and starts two counter threads with different count time so the
	 * one can interrupt the otehr.
	 * 
	 * @param args
	 *            are the cmd args
	 */
	public static void main(String[] args) {
		EntangledCounter testTimer1 = new EntangledCounter(0, 8000, null,
				"Timer1");
		EntangledCounter testTimer2 = new EntangledCounter(0, 8300, null,
				"Timer2");
		testTimer1.setOther(testTimer2);
		testTimer2.setOther(testTimer1);
		new Thread(testTimer1).start();
		new Thread(testTimer2).start();
	}
}
