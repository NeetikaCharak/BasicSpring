package main.java;

public class Employee  {
	String name;
	int id;
	String address;

	public Employee(String name, int id, String address) {
		this.name = name;
		this.id = id;
		this.address = address;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Employee(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
