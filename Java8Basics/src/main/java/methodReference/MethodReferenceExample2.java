package main.java.methodReference;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import main.java.lambdaBasics.Person;

/**
 * method refrence syntax
 * Instancename::methodname and In case of static class StaticClassName::methodname
 * 
 * Eligible for Method Refrence - p -> System.out.println(p)
 * @author Neetika
 *
 */
public class MethodReferenceExample2 {
	public static void main(String[] args) {
		List<Person> persons = Arrays.asList(new Person(25, "Jack", "Carroll"), new Person(40, "Arnold", "Barroll"),
				new Person(35, "Donald", "Caryle"), new Person(56, "Pat", "Kings"), new Person(65, "Lindt", "Chocs"),
				new Person(15, "Mathew", "Bronte"));

		System.out.println("List of elements (last name starts with C)");
		
		printLastNameConditonally(persons, p -> p.getLastName().startsWith("C"), p -> System.out.println(p));
		printLastNameConditonally(persons, p -> p.getLastName().startsWith("C"), System.out::println);//p->method(p)

	}

	/**
	 * 
	 * @param persons
	 * @param predicate - To define wHom (from persons list) should be acted upon; Filter
	 * @param consumer - Action over predicates; operation over predicates
	 */
	private static void printLastNameConditonally(List<Person> persons, Predicate<Person> predicate,
			Consumer<Person> consumer) {
		for (Person person : persons) {
			if (predicate.test(person))
				consumer.accept(person);
		}
	}
}
