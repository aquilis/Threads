package com.sirma.itt.javacourse.threads.producerConsumer;

/**
 * Gets products from the warehouse.
 */
public class Consumer extends Thread {
	private final Warehouse warehouse;
	private final int timeInterval;

	/**
	 * Constrcuts the producer.
	 * 
	 * @param timeInterval
	 *            is the time interval in seconds between each production
	 * @param warehouse
	 *            are the warehouses that the producer will fill
	 */
	public Consumer(int timeInterval, Warehouse warehouse) {
		this.warehouse = warehouse;
		this.timeInterval = timeInterval;
		start();
	}

	@Override
	public synchronized void run() {
		while (true) {
			// TODO debug line
			System.out.println("Consumer has retrieved " + warehouse.get());
			try {
				wait(timeInterval * 1000);
			} catch (InterruptedException e) {
			}
		}
	}
}
