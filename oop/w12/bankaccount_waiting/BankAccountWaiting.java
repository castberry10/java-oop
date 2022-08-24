package w12.bankaccount_waiting;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountWaiting {

	public static final double MAX_BALANCE = 100000;

	private double balance = 0.0;
	
	private Lock blanceLock = new ReentrantLock();

	
	public void deposit(double amount) {
		blanceLock.lock();
		try {
			balance += amount;
			System.out.println("입금 후 잔액: " + balance);
		}finally {
			blanceLock.unlock();
		}
	}

	public void withdraw(double amount) {
		blanceLock.lock();
		try {
			while(balance < amount) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
			}
			balance -= amount;
			System.out.println("출금 후 잔액: " + balance);
		}finally {
			blanceLock.unlock();
		}
		
	}

	public double getBalance() {
		return balance;
	}

}