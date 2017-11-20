package ex;
import java.util.Scanner;

public class book8_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 0, y = 0, z = 0;
		Scanner input = new Scanner(System.in);
		
		try {
			x = input.nextInt();
			y = input.nextInt();
			z = x * y;
			System.out.printf("%d * %d = %d %n", x, y, z);
		} catch(Exception e) {
			System.out.println("입력이 잘못되었습니다.");
		}
	}
}
