package w12.bankaccount_synch;

public class SynchronizedBankAccount {

	public static final double MAX_BALANCE = 100000;

	private double balance = 0.0;

	public synchronized void deposit(double amount) {
		balance += amount;
		System.out.println("입금 후 잔액: " + balance);
	}

	public synchronized void withdraw(double amount) {
		balance -= amount;
		System.out.println("출금 후 잔액: " + balance);
	}

	public double getBalance() {
		return balance;
	}

}