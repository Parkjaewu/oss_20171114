package ex;

public class Ex03 {

	public static void main(String[] args) throws InvalidWithdraw {
		// TODO Auto-generated method stub
		BankAccount ba = new BankAccount(-100000);
		ba.deposit(100000);
		ba.withdraw(500000);
	}

}
