package w12;

/**
 * 스레드에 인터럽트를 거는 예.
 */

public class TwoThreads2 {
	
	public static void main(String[] args) throws InterruptedException {
		
		Runnable r = new Timing();
		Thread t = new Thread(r);
		t.start();

		// 아래 문장은 InterruptedException을 던지는 수가 있음.
		// InterrutedException은 확인예외이므로 반드시 예외처리를 해야 함.
		// 이 프로그램에서는 메소드 헤더에 throws 절을 적어 주고,
		// 예외가 발행하면 이 메소드를 호출하는 놈(이 프로그램의 경우 시스템)에게  예외를 전달하여 
		// 그 놈에게 예외처리 책임을 넘기는 방식으로 예외를 처리를 함.
		Thread.sleep(5000);	// 5초 동안 잠듬.
		
		t.interrupt();	// t에게 인터럽트를 걸어준다.
	}
}

