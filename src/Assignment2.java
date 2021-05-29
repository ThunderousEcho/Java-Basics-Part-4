import java.util.Scanner;

class a {}
class b {}

public class Assignment2 {

	public static void Go(Scanner scanner) {
		
		System.out.println("Deadlock started.");
		
		new Thread(
			new Runnable() {
				@Override
				public void run() {
					synchronized (a.class) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						synchronized (b.class) {
							System.out.println("This message will not be printed.");
						}
					}
				}
			}
		).start();
		
		synchronized (b.class) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (a.class) {
				System.out.println("This message will not be printed.");
			}
		}
		
		System.out.println("This message will not be printed.");
	}
}
