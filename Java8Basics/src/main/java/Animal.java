package main.java;

public class Animal {
	Animal(){
		System.out.println("Construcr in superclass");
	}
    public static void testClassMethod() {
        System.out.println("The static method in Animal");
    }
    public void testInstanceMethod() {
        System.out.println("The instance method in Animal");
    }
}


class Cat extends Animal {
	Cat(){
		//super();
		System.out.println("Construcr in subclass");
	}

    public static void testClassMethod() {
        System.out.println("The static method in Cat");
    }
    public void testInstanceMethod() {
    	super.testInstanceMethod();
        System.out.println("The instance method in Cat" );
    }

    public static void main(String[] args) {
    	//Animal.testClassMethod();
    	
        //Animal myAnimal = new Cat();
    	Cat myAnimal  = new Cat();
        myAnimal.testInstanceMethod();
//        Animal.testClassMethod();
//       Cat.testClassMethod();
    }
}