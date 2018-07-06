package main.java.collectionIteration;

import java.util.Arrays;
import java.util.List;

import main.java.lambdaBasics.Person;


/**
 * In both 1 & 2 way to iterae , we are controlling the iteration externally, we are tellign compiler that 
 * we are iterating one by one over the elements externally.
 * Both of them are called external Iterators that is we are writing code to perform iteration; we are controlling iteration
 * 
 * 
 * In Java 8, Internal Iterators have been introduced. We do not control iteration here; just telling the intent to iterate.
 * And how the iteration happens is up to the runtime.
 * So ow every collectio has one new method "foreach" where we do not instruct the order in which this loop should execute
 * 
 * In 1&2 , the list is iterated in sequence, however with foreach it is not necessarily sequential
 * 
 * @author Neetika
 *
 */
public class CollectionIterationExample {

	public static void main(String[] args) {
		List<Person> persons = Arrays.asList(new Person(25, "Jack", "Carroll"), new Person(40, "Arnold", "Barroll"),
				new Person(35, "Donald", "Caryle"), new Person(56, "Pat", "Kings"), new Person(65, "Lindt", "Chocs"),
				new Person(15, "Mathew", "Bronte"));
		
		//Before Java 8, teher were 2 ways to Iterate
		
		//1-Standard way
		System.out.println("Using for loop");
		for(int i = 0; i < persons.size(); i++){
			System.out.println(persons.get(i));
		}
		
		//2-Relatively new
		System.out.println("\nUsing for-in loop");
		for(Person person : persons){
			System.out.println(person);
		}
		
		//3-Java8- Foreach method takes 1 argument and returns nothing :: COnsumer Function Interface
		//the method body content is called for every elemnt in the list by runtime
		System.out.println("\nUsing for-each loop");
		persons.forEach(p -> System.out.println(p));//Consumer = (p -> System.out.println(p)) 
		
		//4- Methd Reference
		System.out.println("\nUsing Methd Ref");
		persons.forEach(System.out::println);//Consumer = (p -> System.out.println(p)) 
	}

}
