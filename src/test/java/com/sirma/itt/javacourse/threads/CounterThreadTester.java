package com.sirma.itt.javacourse.threads;

import java.util.Scanner;

/**
 * The runner class.
 */
public final class CounterThreadTester {
	/**
	 * 
	 */
	private CounterThreadTester() {
	}

	/**
	 * Starts a counter thread and waits for the user to input a character. If a
	 * character (or a string)is entered the main therad stops the counter
	 * thread and prints the time it has counted to.
	 * 
	 * @param args
	 *            are the cmd args
	 */
	public static void main(String[] args) {
		CounterThread counter = new CounterThread(100000000000L);
		Scanner scn = new Scanner(System.in);
		String line = "";
		while (line.length() == 0) {
			System.out.println("Input a character to stop the counter:");
			line = scn.nextLine();
		}
		scn.close();
		counter.interrupt();
		System.out.println("Counter was interrupted at: " + counter.getCount());
	}
}
