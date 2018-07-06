package main.java.methodReference;

/**
 * Method Refrence is an alternative way to write LAMBDA when we are calling a method on rhs of lambda expression
 * 
 * Eligibility for method Reference-
 * No Input arguments and RUnning a method with no Parameters
 * or 
 * Perfect match in input arguments and same arguemnts being passed to the method
 * 
 * @author Neetika
 *
 */
public class MethodReferenceExample {

	public static void main(String[] args) {
		//Lambda Expression
		Thread thread = new Thread(() -> sayRun());
		thread.start();
		
		//Method Refrence
		Thread thread1 = new Thread(MethodReferenceExample::sayRun);
		thread1.start();
	}
	
	public static void sayRun(){
		System.out.println("I am Running");
	}

}
