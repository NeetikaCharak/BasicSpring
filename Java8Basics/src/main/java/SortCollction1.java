package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortCollction1<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		Employee e1 = (Employee) o1;
		Employee e2 = (Employee) o2;

		return e1.getName().compareTo(e2.getName());
	}

	public static void main(String[] args) {
		List<Employee> listE1 = new ArrayList<Employee>();
		listE1.add(new Employee("Neetika", 20, "Pune"));
		listE1.add(new Employee("Yash", 10, "INdia"));
		listE1.add(new Employee("Mihika", 40, "Edinburgh"));
		listE1.add(new Employee("Atharv", 30, "Dublin"));
		listE1.add(new Employee("Pundu", 60, "Jammu"));
		Collections.sort(listE1, new SortCollction1<>());
		
		Collections.sort(listE1, new Comparator<Employee>() {
			@Override 
			public int compare(Employee e1, Employee e2){
				 return e1.getAddress().compareTo(e2.getAddress());
			 }
			
		});
		
//		Collections.sort(listE1, );
//		for (int i = 0; i < listE1.size(); i++) {
//
//			Employee emp1 = (Employee) listE1.get(i);
//
//			System.out.println("'name: " + emp1.getName() + "||" + "id: " + emp1.getId() + "||" + "Address: " + emp1.getAddress());
//
//		}

	}

}
