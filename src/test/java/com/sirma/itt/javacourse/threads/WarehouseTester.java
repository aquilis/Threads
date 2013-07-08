package com.sirma.itt.javacourse.threads;

import com.sirma.itt.javacourse.threads.producerConsumer.Consumer;
import com.sirma.itt.javacourse.threads.producerConsumer.Producer;
import com.sirma.itt.javacourse.threads.producerConsumer.Warehouse;

/**
 * Tests the producer-consumer model of the whharehouse.
 */
public final class WarehouseTester {
	/**
	 * A private constructor.
	 */
	private WarehouseTester() {
	}

	/**
	 * main test method.
	 * 
	 * @param args
	 *            are the mcmd atrgs
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Warehouse warehouse = new Warehouse(5);
		Producer producer = new Producer(1, warehouse);
		Consumer consumer = new Consumer(2, warehouse);
	}
}
