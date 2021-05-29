import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		System.out.println("Choose an assignment (1, 2, 3, or 4)");
		
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			Assignment1.Go(scanner);
			break;
		case 2:
			Assignment2.Go(scanner);
			break;
		case 3:
			Assignment3.Go(scanner);
			break;
		case 4:
			Assignment4.Go(scanner);
			break;
		default:
			System.out.println(choice + " was not a valid answer");
			break;
		}
		
		scanner.close();
	}

}