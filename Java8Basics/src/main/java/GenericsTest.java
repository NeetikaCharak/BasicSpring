package main.java;

public class GenericsTest {

	public static void main(String...strings){
		printArray(new Integer[]{2, 3, 4, 5, 6});
		printArray(new Double[]{2.4, 4.3, 7.4,6.5, 2.6});
		printArray(new Integer[]{2, 3, 4, 5, 6});
	}
	
	public static <E> void printArray(E[] obj){
		for(E e: obj){
			System.out.printf("%s ",e);
		}
		System.out.printf("%n");
	}
}
