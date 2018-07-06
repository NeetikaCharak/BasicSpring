package main.java.stream;

import java.util.ArrayList;
import java.util.List;

public class Stream {
	
	public static void main(String... args){
		List<String> list = new ArrayList<String>();
		list.add("Yash");
		list.add("Neetika");
		list.add("Mihika");
		list.add("Atharv");
		
		System.out.println("My Family - "+list.stream().filter(s -> s.startsWith("M")).count());
		System.out.println();
		
		
	}

}
