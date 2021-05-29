import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

class BoundedBuffer {
	
	int[] contents;
	int start = 0; //index to write to to enqueue the next element
	int end = 0; //index to read from to dequeue the next element
	int count = 0;
	
	public BoundedBuffer(int capacity) {
		contents = new int[capacity];
	}

	public boolean Enqueue(int value) {
		
		if (count == contents.length) {
			return false;
		} else {
			contents[start] = value;
			start = (start + 1) % contents.length;
			count++;
			return true;
		}
	}
	
	public Optional<Integer> Dequeue() {
		
		if (count == 0) {
			return Optional.empty();
		} else {
			Optional<Integer> ret = Optional.of(contents[end]);
			end = (end + 1) % contents.length;
			count--;
			return ret;
		}
	}
}

public class Assignment3 {

	public static void Go(Scanner scanner) {
		
		BoundedBuffer buffer = new BoundedBuffer(10);
		
		//producer
		new Thread(
			new Runnable() {
				@Override
				public void run() {
					
					Random r = new Random();
					
					for (int i = 0; i < 25;) {
						synchronized (buffer) {
							
							boolean success = buffer.Enqueue(i);
							
							System.out.println("enqueueing #" + i + " " + (success ? " success" : " failed- buffer at capacity"));
							
							if (success) { i++; }
						}
						
						try {
							Thread.sleep(r.nextInt(1000));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		).start();
		
		Random rand = new Random();
		
		//consumer
		for (int i = 0; i < 25;) {
			synchronized (buffer) {
				
				var read = buffer.Dequeue();
				
				System.out.println("dequeueing #" + i + " " + (read.isPresent() ? " success" : " failed- buffer empty"));
				
				if (read.isPresent()) { 
					
					if (read.get() != i) {
						System.err.println("expected " + i + ", got " + read.get());
					}
					
					i++;
				}
			}
			
			try {
				Thread.sleep(rand.nextInt(10000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
