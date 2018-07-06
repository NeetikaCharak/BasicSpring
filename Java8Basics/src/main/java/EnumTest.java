package main.java;

public class EnumTest {

	public enum DAY {
		MON, TUE, WED, THU, FRI, SAT, SUN
	}
	
	public DAY myDay;
	
	public EnumTest(DAY day){
		this.myDay = day;
	}
	
	public void whichDayItIs(){
		switch (myDay){
			case MON: System.out.println("Monday it is...");break;
			default: System.out.println("Is not Monday"); break;
		}
	}
	public static void main(String... args){
		EnumTest test = new EnumTest(DAY.MON);
		test.whichDayItIs();
		
		System.out.println(DAY.values()[2]);
	}
}
