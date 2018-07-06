package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortCollection implements Comparable<Object> {
	String name;
	String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public SortCollection(String name) {
		this.name = name;
	}


	public static void main(String... args){
		List<SortCollection> list = new ArrayList<SortCollection>();
		list.add(new SortCollection("Neetika"));
		list.add(new SortCollection("Yash"));
		list.add(new SortCollection("Mihika"));
		list.add(new SortCollection("Atharv"));
		list.add(new SortCollection("Candy"));
		Collections.sort(list);
		System.out.println(list.toString());
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + " ";
	}
	
	@Override
	public boolean equals(Object obj) {
		SortCollection sortObj = (SortCollection) obj;

		return this.name.equals(sortObj.getName());
	}

	@Override
	public int compareTo(Object o) {
		SortCollection sortObj = (SortCollection) o;
		return this.getName().compareTo(sortObj.getName());
	}
}
