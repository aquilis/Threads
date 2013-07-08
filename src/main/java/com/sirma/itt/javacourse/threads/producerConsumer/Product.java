package com.sirma.itt.javacourse.threads.producerConsumer;

/**
 * A sample product for the producer-consumer model.
 */
public class Product {
	private final String name;

	/**
	 * Construct the product with a name.
	 * 
	 * @param name
	 *            is the name of the product.
	 */
	public Product(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
