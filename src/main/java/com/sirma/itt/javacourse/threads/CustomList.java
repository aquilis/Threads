package com.sirma.itt.javacourse.threads;


/**
 * Stores an array with a certain size and has methods for retrieving the lastly
 * added element, adding a new element and printing all elements on the console.
 * Starts a new thread each time when the user wants to put or retrieve an
 * element. These methods are synchronized for the current monitor lock.
 * 
 * @author v.tsonev
 * @since 24/06/2013
 */
public class CustomList {

	private final int size;
	private final Object[] array;
	// the index of the topmost, recently added element
	private int top;

	/**
	 * Constructs the list with an initial size that can not be changed later.
	 * Sets the top element to -1 (no elements).
	 * 
	 * @param size
	 *            is the initial size of the list
	 */
	public CustomList(final int size) {
		this.size = size;
		this.array = new Object[size];
		this.top = -1;
	}

	/**
	 * Externally visible method for adding an element into the list. Starts a
	 * new thread to perform the operation.
	 * 
	 * @param obj
	 *            is the object to add.
	 */
	public synchronized void add(final Object obj) {
		Thread adder = new Thread() {
			@Override
			public void run() {
				addElement(obj);
			}
		};
		adder.start();
	}

	/**
	 * The internal method adds the speciied object to the end of the array. If
	 * it is full, waits until another thread removes an element and then adds
	 * the current one.
	 * 
	 * @param obj
	 *            is the object to be insterted into the list
	 */
	private synchronized void addElement(Object obj) {
		while (top + 1 >= size) {
			try {
				wait();
			} catch (InterruptedException e) {
				break;
			}
		}
		array[++top] = obj;
		notifyAll();
	}

	/**
	 * Removes the lastly added element. Starts a new thread to perform the
	 * operation.
	 */
	public void remove() {
		Thread remover = new Thread(){
			@Override
			public void run() {
				removeElement();
			}
		};
		remover.start();
	}

	/**
	 * The internal method removes the lastly added element from the array. If
	 * it is empty, waits until another thread add an element.
	 */
	private synchronized void removeElement() {
		while (top == -1) {
			try {
				wait();
			} catch (InterruptedException e) {
				break;
			}
		}
		// set the topmost element to null and decrease the top variable by one
		array[top--] = null;
		notifyAll();
	}

	/**
	 * Prints all list elements on the console.
	 */
	public synchronized void printAllElements() {
		System.out.print("[");
		for (int i = 0; i <= top; i++) {
			System.out.print(array[i]);
			if (i < top) {
				System.out.print(", ");
			}
		}
		System.out.print("]");
	}

	/**
	 * Self-testing of the class.
	 * 
	 * @param args
	 *            are the cmd args
	 * @throws InterruptedException
	 *             asdasd.
	 */
	public static void main(String[] args) throws InterruptedException {
		CustomList list = new CustomList(5);
		list.add(1);
		Thread.sleep(10);
		list.add(2);
		Thread.sleep(10);
		list.add(3);
		Thread.sleep(10);
		list.add(4);
		Thread.sleep(10);
		list.add(5);
		Thread.sleep(10);
		list.add(6);
		Thread.sleep(10);
		list.remove();
		Thread.sleep(10);
		list.printAllElements();
	}
}