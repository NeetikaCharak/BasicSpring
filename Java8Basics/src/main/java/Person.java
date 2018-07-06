package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Person {
	public enum SEX {
		male, female
	}

	public Person(String name, int age, Person.SEX gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	String name;
	int age;
	Person.SEX gender;

	public Person.SEX getGender() {
		return gender;
	}

	public void setGender(Person.SEX gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setNameAge(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

class SearchPerson{
	public static void getPersonOlderThan15(List<Person> listPerson, CheckService cp){
		for(Person p : listPerson){
			if(cp.check(p)){
				System.out.printf("%s is my search result %n", p.getName());
			}
		}
	}
	
	public static void getPersonOlderThan15_Standardfns(List<Person> listPerson, Predicate<Person> cp){
		for(Person p : listPerson){
			if(cp.test(p)){
				System.out.printf("%s is my search result %n", p.getName());
			}
		}
	}
	
	public static void main(String[] args){
		List<Person> pList = new ArrayList<Person>();
		pList.add(new Person("Misha", 30, Person.SEX.female));
		pList.add(new Person("Kishi", 20,  Person.SEX.male));
		//getPersonOlderThan15(pList, new CheckServiceImpl());
		
		//Anonymous class
		getPersonOlderThan15(pList, new CheckServiceImpl() {
			public boolean check(Person p) {
				if (p.getAge() > 15 & p.getName().startsWith("K") && Person.SEX.male == p.getGender())
					return true;
				return false;
			}
		});
		
		//lambda
		getPersonOlderThan15(pList, (Person p) -> p.getAge() > 15 & p.getName().startsWith("M") && Person.SEX.male == p.getGender());
		
		//Lambda - Using Predicate
		getPersonOlderThan15(pList, k -> k.getAge() > 15 & k.getName().startsWith("M") && Person.SEX.male == k.getGender());

	}
	
}


interface CheckService{
	boolean check(Person p);
}
class CheckServiceImpl implements  CheckService{
	public boolean check(Person p){
		if(p.getAge() > 15 & p.getName().startsWith("M") && Person.SEX.female == p.getGender()) return true;
		return false;
	}
}
