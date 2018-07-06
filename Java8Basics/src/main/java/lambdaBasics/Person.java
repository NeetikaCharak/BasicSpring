package main.java.lambdaBasics;

public class Person {
	private int age;
	private String firstName;
	private String lastName;
	
	public Person(int age, String firstName, String lastName) {
		super();
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getAge() {
		return age;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Person [age=" + age + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
