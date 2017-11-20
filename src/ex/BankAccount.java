package ex;

public class BankAccount {
	private int minreposit;
	private int reposit;
	public BankAccount(){
		minreposit = 0;
	}

	public BankAccount(int min){
		minreposit = min;
	}
	
	public void deposit(int money){
		reposit += money;
	}
	
	public int withdraw(int money){
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
