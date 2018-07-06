package main.java.stream;

import java.util.Arrays;
import java.util.List;

import main.java.lambdaBasics.Person;

/**
 * Stream is like aseembly line or conveyer  belt and the there is list of operations to be performed on it.
 * Looping throuh the list for every operation is not a very good idea.
 * 
 * Its a new view of the collection
 * 
 * So the solution is to create a stream of the list , prepare oprations n run the assembly line or conveyer belt.
 * 
 * Lambda enables parallel processing; stream is just a way of taking control of iteraion out of our control.it is now internal iteraton.
 * Adv of having this is we can have different portions of collection to be handled by different processors. One portion of huge colelction will
 * go to one asembly line and other will go to another assemly line, way to do this to use parallelstream
 * @author Neetika
 *
 */
public class StreamExample1 {
	
	public static void main(String[] args) {
		List<Person> persons = Arrays.asList(
				new Person(25, "Jack", "Carroll"),
				new Person(40, "Arnold", "Barroll"),
				new Person(35, "Donald", "Caryle"),
				new Person(56, "Pat", "Kings"),
				new Person(65, "Lindt", "Chocs"),
				new Person(15, "Mathew", "Bronte"));
		//Example 1
		//Make assembly line, putting all persons object on converyer belt n run it
		//Two operations executed in one go - one iteration;result of first operation goes to the next
		//End result is summation of all individual operations
		persons.stream()//just this line doent make any iteration
		.filter(p -> p.getLastName().startsWith("K"))//1st mechanic working on conveyer belt. Filter out the non-required elements from conveyer belt
		.forEach(p -> System.out.println(p.getFirstName()));//2nd mechanic  working on conveyer belt
		
		
		//Example 2
		long count = persons.stream()//just this line doent make any iteration
		.filter(p -> p.getLastName().startsWith("C"))//Iteration happens here
		.count();//Terminal Operation
		System.out.println(count);
		
		persons.parallelStream();//This could ptenially split the collection into multiple streams if it feels that this could do the execution faster
	}

}
