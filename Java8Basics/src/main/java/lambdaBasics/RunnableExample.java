package main.java.lambdaBasics;

/**
 * All that Thread class expects is Runnable interface
 * @author Neetika
 *
 */
public class RunnableExample {
	public static void main(String[] args) {
		//1. option 1 - Anonymous Innner class
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Called run method, thread will be created with start()");

			}
		});
		thread.run();
		
		//2. option 2 - removing the above boilerplate code - use Lambda
		Thread threadNew = new Thread(
				() -> System.out.println("Called run method, Removed Boilerplate code")
				);
		threadNew.run();
	}
}
