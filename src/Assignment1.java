import java.util.Scanner;

class Something { }

public class Assignment1 {
	
	private static volatile Something something;
	
	public static void Go(Scanner scanner) {
		System.out.println("Running...");
		GetSomething();
		System.out.println("Done.");
	}
		
	public static Something GetSomething() {
		
        if (something == null) {
            synchronized (Something.class) {
                if (something == null) {
                	something = new Something();
                }
            }
        }
        
        return something;
	}
}
