package main.java;

interface Horse {
    public String identifyMyself() ;
    
    public void identifyMyself1();
}

public class TestInheritance extends Horse  {
    public static void main(String... args) {
    	TestInheritance myApp = new TestInheritance();
        System.out.println();
    }
    
    public String identifyMyself1(){
    	
    }
}