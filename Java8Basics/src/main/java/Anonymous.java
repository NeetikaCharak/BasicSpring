package main.java;

public class Anonymous {

	interface HelloWorld {
		public void greet();

		public void greetSomeone(String someone);
	}

	public void sayHello() {

		class EnglishGreeting implements HelloWorld {
			String name = "world";

			public void greet() {
				greetSomeone("world");
			}

			public void greetSomeone(String someone) {
				name = someone;
				System.out.println("Hello " + name);
			}
		}

		HelloWorld englishGreeting = new EnglishGreeting();

		HelloWorld frenchGreeting = new HelloWorld() {
			String name = "tout le monde";

			public void greet() {
				greetSomeone("tout le monde");
			}

			public void greetSomeone(String someone) {
				name = someone;
				System.out.println("Salut " + name);
			}
		};

		HelloWorld spanishGreeting = new HelloWorld() {
			String name = "mundo";

			public void greet() {
				greetSomeone("mundo");
			}

			public void greetSomeone(String someone) {
				name = someone;
				System.out.println("Hola, " + name);
			}
		};
		
		HelloWorld india = new HelloWorld() {
			public void greet(){
				System.out.println("Namaste ji");
			}

			public void greetSomeone(String someone){
				System.out.println("Namaste " + someone);
			}
		};
		
		englishGreeting.greet();
		frenchGreeting.greetSomeone("Fred");
		spanishGreeting.greet();
		india.greet();
	}

	public static void main(String... args) {
		Anonymous myApp = new Anonymous();
		myApp.sayHello();
	}
}
