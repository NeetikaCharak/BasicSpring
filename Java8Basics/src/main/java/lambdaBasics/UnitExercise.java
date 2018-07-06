package main.java.lambdaBasics;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * In this class we are trying code wih java7, then lambda(java8). Further we will learn about functional interfaces
 * 
 * So, in this class we dont acually care what interface (Condition) is called as, we can call it Foo and method as anything
 * it really doesnt matter, All that interface is doing is providing us is "Lambda Type".
 * We just need an Interface with one absttract method which is provided by java.util.function.
 * 
 * 
 * Java provided us out of box interfaces like predicate; we must use it .Predicate interface has an abstract method ith same sgnature as Condiitonal interface.
 * Its test methoed is taking one input argument and returning boolean
 * 
 * @author Neetika
 *
 */
public class UnitExercise {
	public static void main(String[] args) {
		List<Person> persons = Arrays.asList(
				new Person(25, "Jack", "Carroll"),
				new Person(40, "Arnold", "Barroll"),
				new Person(35, "Donald", "Caryle"),
				new Person(56, "Pat", "Kings"),
				new Person(65, "Lindt", "Chocs"),
				new Person(15, "Mathew", "Bronte"));
		
		//Sort the list by Names
		Collections.sort(persons, new Comparator<Person>(){
			@Override
			public int compare(Person a, Person b){
				return a.getFirstName().compareTo(b.getFirstName());
			}
		});
		System.out.println("Sorted List (traditionally)- "+persons.toString());
		Collections.sort(persons, (Person a, Person b) -> a.getFirstName().compareTo(b.getFirstName()));
		//Also correct--- Collections.sort(persons, (a, b) -> a.getFirstName().compareTo(b.getFirstName()));
		System.out.println("Sorted List        (Lambda)- "+persons.toString());
		
		// Print all the elements in list
		System.out.println("\nList of all elements:");
		for (Person person : persons) {
			System.out.println(person);
		}
		
		//Print all the elements that have last name beginning with letter C
		System.out.println("\nList of elements wth last name with C:");
		/**
		 * Not very good method as if we need list with last names having D as starting letter, this method will not work.
		 */
		printLastNameC(persons);
		/**
		 * So lets pass the behaviour as parameter; now all that changes is condiiton and printLastNameConditonally() remains same
		 */
/*		Commenting as we are using Predicate in method below; This code is also fine
 * 		System.out.println("\nList of elements Condiitonally (last name starts with C)");
		printLastNameConditonally(persons, new Condition(){
			@Override
			public boolean test(Person p){
				return p.getLastName().startsWith("C");
			}
		});*/
		/**
		 * using Lambda
		 */
		System.out.println("\nList of elements Condiitonally with LAMBDA (last name starts with B)");
		printLastNameConditonally(persons, (Person p) -> p.getLastName().startsWith("B"), p -> System.out.println(p.getFirstName()));
		System.out.println("\nList of elements Condiitonally with LAMBDA (last name starts with K)");
		printLastNameConditonally(persons, p -> p.getLastName().startsWith("K"), p -> System.out.println(p.getLastName()));
		System.out.println("\nList of elements Condiitonally with LAMBDA (All Names)");
		printLastNameConditonally(persons, p -> true, p -> System.out.println(p.getAge()));
				
	}
	
	interface Condition{
		boolean test(Person p);
	}


	private static void printLastNameC(List<Person> persons) {
		for (Person person : persons) {
			if(person.getLastName().startsWith("C"))
			System.out.println(person);
		}
	}
/*	Commenting as we are using Predicate in method below; This method is also fine
	private static void printLastNameConditonally(List<Person> persons, Condition condition) {
		for (Person person : persons) {
			if(condition.test(person))
			System.out.println(person);
		}
	}*/
	
	private static void printLastNameConditonally(List<Person> persons, Predicate<Person> predicate, Consumer<Person> consumer) {
		for (Person person : persons) {
			if(predicate.test(person))
				consumer.accept(person);
		}
	}
}
