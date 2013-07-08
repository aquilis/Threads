package com.sirma.itt.javacourse.threads;

/**
 * Tests the functionality of the timeout hashtable.
 */
public final class TimeoutMapTester {
	/**
	 * A private constructor.
	 */
	private TimeoutMapTester() {

	}

	/**
	 * main test method.
	 * 
	 * @param args
	 *            are the cmd args.
	 * @throws InterruptedException
	 *             when thread interrupted.
	 */
	public static void main(String[] args) throws InterruptedException {
		// set a hashable with a timeout of 2 sec for the test
		TimeoutHashtable map = new TimeoutHashtable(2000L);
		map.put("Ivan", 1);
		map.put("Dragan", 2);
		map.put("Pesho", 3);
		map.remove("Ivan");
		Thread.sleep(1000);
		// Displays just Dragan and Pesho
		System.out.println("The map after waiting for 1 sec:");
		map.printMe();
		// the time for Pesho gets reset by the request and it stays in the
		// hashtable 1 sec more
		map.get("Pesho");
		Thread.sleep(1200);
		// should display just Pesho
		System.out.println("The map after waiting for 2.2 sec:");
		map.printMe();
		Thread.sleep(1000);
		// now it should be completely empty
		System.out.println("The map after waiting for 3.0 sec:");
		map.printMe();
		Thread.sleep(2000);
		System.out.println("The map after waiting for 5.0 sec:");
		map.printMe();
	}
}
