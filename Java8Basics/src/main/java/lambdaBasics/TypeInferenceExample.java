package main.java.lambdaBasics;


/**
 * With Lambda Java is doing type Inference
 * The binding between the Lambda expression and the interface is TypeInference. 
 * 
 * E.g.
 * In the following line, compiler tries to match argumet which is lambda exp with the Greeting interface; it infers that Greeting has one mthod with no paramter
 * 		greeter.greet(() -> System.out.println("\nHello Greetings!!"));
 * 		public void greet(Greeting greeting){..}
 * 
 * We are tryign to tell complier that we want to use lambda expression for some thig that is Greeting interface
 * 
 * @author Neetika
 *
 */
public class TypeInferenceExample {
	
	
	public static void main(String[] args) {
		//1. Option1
		StringLengthLambda stringLengthLambda1 = (String s) -> {return s.length();};
		System.out.println(stringLengthLambda1.getLength("neetika here"));
		
		//2. Option2
		/**
		 * Complier already knows that StringLengthLambda has method with parameter
		 * type as String, so we can skip writing parameter type. Further we 
		 * can get rid of parenthesis as well.
		 * 
		 * Remove  return statement and parenthesis too
		 * 
		 * Compiler will figure out 
		 * 
		 *
		 */
		StringLengthLambda stringLengthLambda2 = s -> s.length();
		System.out.println(stringLengthLambda2.getLength("mihika here"));
		
		//3. Option 3
		/**
		 *  Right side of following exp can go as method argument that takes StringLengthLambda 
		 *  "StringLengthLambda stringLengthLambda2 = s -> s.length();"
		 *  
		 *  This is type inference in action - compiler is inferring lot of things from the code - what is input type is, what the return type is= all depending upen the 
		 *  type of that interface
		 */
		printLen(s -> s.length());
		
	}
	
	public static void printLen(StringLengthLambda s){
		System.out.println(s.getLength("atharv here"));
	}
	
	
	
	interface StringLengthLambda{
		int getLength(String s); 
	}

}
