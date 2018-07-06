package main.java.lambdaBasics;

import java.util.function.Consumer;
import java.util.function.Function;

public class Lambda {

	public static void main(String... args){
		Lambda lm = new Lambda();

//		Printer printer = new Printer(){
//			@Override
//			public void print(String message) {
//				// TODO Auto-generated method stub
//				System.out.println(message);
//			}
//		};
		
		lm.printSomething("neetika", message -> {return "Hi "+message;});
		
		lm.printSomethingUsingFunctions("mihika", message -> {return "Hi "+message;});
		
		lm.printSomethingUsingFunctions("atharv", message -> {return "Hi "+message;}, abc -> System.out.println(abc));
	}
	
	public void printSomething(String message, Printer printer){
		System.out.println(printer.print(message));
	}
	
	public void printSomethingUsingFunctions(String message, Function<String, ?> function){
		System.out.println(function.apply(message));
	}
	
	public void printSomethingUsingFunctions(String message, Function function, Consumer consumer){
		String message1 = (String) function.apply(message);
		consumer.accept(message1);
	}
	
	
	interface Printer{
		String print(String message);
	}
	
}
