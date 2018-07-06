package main.java.lambdaBasics;
/**
 * 
Why Lambda - 
1- Enables funtional programming - functions can be passed around as entities.
2- Readable and concise code
3- Enables us to write APIs and Libraries that are easier to use - collection api is easier to use now
4-Enables suport for parallel processing

Lambda = Assigning a block of code to a variable. 
We do not need public/private as method is used in isolation, lly method name doesnt make sense as we are accessing that block of code ith the name of variable, then compiler can figure out what is the return type depending upon the returing value; So we are left with 
aBlockOfCode = () { System.out.print("cvxvxv");}

Lambda's syntax is just addition of ->
aBlockOfCode = () -> { System.out.print("cvxvxv");}

Steps to use lambda 
1- Take a method
2- Remove the modifiers- public/private etc
3- Remove the Method name
4- Remove the return Type
5- Put -> between argument bracket and method's code
6- If method's body is just One line, remove curly braces
	aBlockOfCode = () -> System.out.print("cvxvxv");

Lets take example of Behavoural pattern.

Lets say we have a greet method and we want to print certain message on certain criteria or behaviour. One way to do it in Java 7 is to put all if else code in greet metod and let argument parameter decide which piece to execute, This is not a very good design. So to iprove this code, lets take out the behavior to some other class and lets one of its implementation execute


pubic class Greeter{
	public class greet(Greeting greeting){
		greeting.perform();	
	}

	public class greetOld(int criteria){
		if(criteria = 0)
			System.out.printn("Hello"));
		else
			System.out.printn("Bye"));
	}

	...static main(){
		//Way 1
		Greeter greeter = new Greeter();
		greeter.greetOld("Hello");	

		//Way2 - Passign behavior inside a class; Greet method now doesnt contain any behaviour
		HelloGreeting helloGreeting = new HelloGreeting();
		greeter.greet(helloGreeting);
		
		
	}
}

public interface Greeting{
	public void perform();
}

public class HelloGreeting implements Greeting{
	public void perform(){
		System.out.printn("Better Design");
	}
}

Now lets think about it, we are passign a thing that has te behavior, not direclty behaviur,. As in OOP we cannot have code outside a class. So lambda is passing just an action directly rather than a class that has that action. Lambda are just functions that exists in isolation, they are not part of class.

greetingFunction = () -> System.out.println("Hello World");

Advantages of using this is - greetingFunction can be passed around as paramter, send it to method as argument;;
greet(greetingFunction). We have encapsulated a function in a variable that can be used any where..

public void greet(...action..){
	..performs action...
}

Further we can directly use lambda exp in line
greet(() -> System.out.println("Hello World"););

Now go back to beginning, where we were passing criteria to greet method, that was passign numbers inline. Now we are passing inline lambda expression too

Varieties of lambda

1- greetingFunction = () -> System.out.println("Hello World");
2- 
doubleNuberFUnction = public int abc(int a){return a*2.8} ;
to
doubleNuberFUnction = (int a) -> {return a*2.8} ;
to
doubleNuberFUnction = (int a) -> return a*2.8 ;
to (Invaid to write return stmt when we have one liner lambda exp without {}
doubleNuberFUnction = (int a) -> a*2.8;

3- addFunction = (int a, int b) -> a+b;

4- safeDivideFuncion = (int a, int b) -> {
	if(b==0) return 0;
	else return a/b;
};

# Type of Lambda functions
write an interface with the method having exacly same signature


 * @author Neetika
 *
 */
public class Greeter {
	
	public static void main(String[] args) {
		//1. Old Way
		Greeter greeter = new Greeter();
		greeter.greet();
		
		//2. Using design pattern- - keeping behaviour out of greet method
		greeter.greet(new HelloGreeting());
		greeter.greet(new NamasteGreeting());
		
		//3. Using Lambda
		greeter.greet(() -> System.out.println("\nHello Greetings!!"));
		greeter.greet(() -> System.out.println("Namaste Greetings!!"));
		
		//4. Using Lambda Type
		//Both stmts are equivalent because both have one method with same signature
		MyLambda myLambda = () -> System.out.println("\nMy Lambda Greetings!!");
		Greeting helloLambda = () -> System.out.println("\nHello Greetings!!");
		Greeting namLambda = () -> System.out.println("Namaste Greetings!!");
		helloLambda.perform();
		namLambda.perform();
		
		//Tips
		System.out.println("\n---");
		/**
		 * One is instance of a class that implemented Greeting interface
		 * where as two is Lambda Expression of type Greeting interface.
		 * Three is just an annomous inner class which is equivalent to one
		 * 
		 * two is syntactical sugar or just an another way to write three or
		 * just a fancy new way in java8. Lambda is not the shortcut for innerclass
		 */
		Greeting one = new HelloGreeting();
		Greeting two = () -> System.out.println("Two - Hello Greetings!!");
		Greeting three = new Greeting(){//Anonmous InnerClass
			public void perform(){
				System.out.println("Three - Hello Greetings!!");
			}
		};
		greeter.greet(one);
		greeter.greet(two);
		greeter.greet(three);
		
	}
	
	public void greet(){
		System.out.println("Hello There");
	}
	
	public void greet(Greeting greeting){
		greeting.perform();
	}
	
	
	//This inteface is to make Lambda type
	interface MyLambda{
		//Method signature must match
		void foo();
	}

}


