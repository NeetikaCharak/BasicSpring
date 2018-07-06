package main.java;

public class NestedClass {
	public int i = 4;
	public int instanceVariable = 4;
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	static class StaticNested{
		int i = 9;
		
		public void test1(){
			NestedClass bb = new NestedClass();
					
			System.out.printf("this is STATIC int value %s%n", i);
			System.out.printf("this is Intance Variable %s%n", bb.getI());
		}
	}
	
	class InnerClass{
		int i = 7;
		
		
		
		public void test1(){
			System.out.printf("this is NOn STATIC int value %s;", i);
			System.out.printf("this is Intance Variable %s%n", instanceVariable);
		}
		
	}
	
	public static void main(String... args){
		NestedClass.StaticNested s = new  NestedClass.StaticNested();
		s.test1();
		
		NestedClass outer = new NestedClass();
		NestedClass.InnerClass s1 = outer.new  InnerClass();
		s1.test1();
	}

}
