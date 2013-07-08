package com.sirma.itt.javacourse.threads.producerConsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Contains the products and coordinates the work of the consnumers and
 * producers.
 */
public class Warehouse {
	private final int capacity;
	private volatile Queue<Product> production = new LinkedList<Product>();

	/**
	 * Constructs the warehouse class.
	 * 
	 * @param capacity
	 *            is the maximum capacity of the warehouse
	 */
	public Warehouse(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Stores a product in the warehouse. If the warehouse is already full,
	 * waits until a consumer thread retrieves a product from it.
	 * 
	 * @param product
	 *            is the product to store
	 */
	public synchronized void put(Product product) {
		while (production.size() == capacity) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		production.offer(product);
		notifyAll();
	}

	/**
	 * Gets a product from the warehouse. If the warehouse is empty, waits until
	 * a producer thread fills it.
	 * 
	 * @return a product from the warehouse
	 */
	public synchronized Product get() {
		while (production.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		notifyAll();
		return production.poll();
	}
}
