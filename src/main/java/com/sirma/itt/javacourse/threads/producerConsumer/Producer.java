package com.sirma.itt.javacourse.threads.producerConsumer;

/**
 * Fills the specified warehouse with products during certain time.
 */
public class Producer extends Thread {
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
	public Producer(int timeInterval, Warehouse warehouse) {
		this.warehouse = warehouse;
		this.timeInterval = timeInterval;
		start();
	}

	@Override
	public synchronized void run() {
		int count = 1;
		while (true) {
			// TODO debug line
			System.out.println("Producer has put into the warehouse product "
					+ count);
			warehouse.put(new Product("sample " + count));
			count++;
			try {
				wait(timeInterval * 1000);
			} catch (InterruptedException e) {
			}
		}
	}
}
