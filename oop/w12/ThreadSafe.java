package w12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/** Thread-safe한 객체가 무엇인지 학습한다.
 * 
 * @author jck
 *
 */
public class ThreadSafe {
	
	// 다른 클래스 안에 선언된 static 클래스는 일반적인 클래스와 별반 다르지 않다.
	// Thread-safe하지 않은 클래스
	static class Counter {
		int count = 0;
		void up() {
			count++;
		}
		int getCount() {
			return count;
		}
	}
	
	// Thread-safe한 클래스
	// 필드를 업데이트하는 up 메소드를 synchronized로 선언했다.
	// 한 스레드가 up 메소드를 실행하고 있는 중에는 스레드 스위칭이 일어나지 않게 된다. 
	static class SafeCounter {
		int count = 0;
		synchronized void up() {
			count++;
		}
		int getCount() {
			return count;
		}
	}

	public static void main(String[] args) throws InterruptedException {

		// (1) Parallel Stream은 여러 스레드에 의해 실행된다.
		
		// 여러 스레드들이 경쟁적으로 업데이트하는 Counter는 바른 값을 유지하지 못한다.
		Counter c = new Counter();
		IntStream.range(0, 100).parallel().forEach(i -> c.up());
		System.out.printf("일반 Counter: %d\n", c.getCount());

		// 여러 스레드가 경쟁적으로 업데이트해도 바른 값을 유지하는 (Thread-safe) SafeCounter. 
		SafeCounter sc = new SafeCounter();
		IntStream.range(0, 100).parallel().forEach(i -> sc.up());
		System.out.printf("Safe Counter: %d\n", sc.getCount());

		// AtomicInteger는 표준 라이브러리에 들어 있는 thread-safe한 클래스이다.
		// 여러 스레드가 AtomicInteger의 메소드를 경쟁적으로 호출해도 데이터가 깨지지 않는다. 
		AtomicInteger a = new AtomicInteger();
		IntStream.range(0, 100).parallel().forEach(i -> a.incrementAndGet());
		System.out.printf("AtomicInteger: %d\n", a.get());
		System.out.println();

		// (2) Thread Pool을 이용하여 여러 스레드를 실행한다.
		
		// 두 개의 스레드에서 하나의 일반 Counter를 경쟁적으로 update한다.
		Counter c2 = new Counter();
		ExecutorService es = Executors.newCachedThreadPool();
		es.submit(() -> {
			for (int i = 0; i < 100_000; i++)
				c2.up();
		});
		es.execute(() -> {
			for (int i = 0; i < 100_000; i++)
				c2.up();
		});
		
		// shutdown: 스레드풀을 끈다.
		// task를 실행 중인 스레드는 하던 일을 마칠 때까지 끝나지 않는다.
		// shutdown 메소드 자체는 즉시 리턴한다.
		es.shutdown();	
		
		// awaitTermination: 모든 스레드가 끝날 때까지 기다린다. (최대 지정된 시간까지)
		// 그 때까지는 awaitTermination 메소드가 리턴하지 않으므로 
		// 이 코드를 실행하는 main 스레드는 계산이 끝날 때까지 이곳에서 잠시 block된다.
		es.awaitTermination(10, TimeUnit.SECONDS);  // 최대 10초 기다린다.
		System.out.printf("일반 Counter: %d\n", c2.getCount());

		// AtomicInteger
		AtomicInteger a2 = new AtomicInteger();
		ExecutorService es2 = Executors.newCachedThreadPool();
		es2.execute(() -> {
			for (int i = 0; i < 100_000; i++)
				a2.incrementAndGet();
		});
		es2.execute(() -> {
			for (int i = 0; i < 100_000; i++)
				a2.incrementAndGet();
		});
		es2.shutdown();
		es2.awaitTermination(10, TimeUnit.SECONDS);
		System.out.printf("AtomicInteger: %d\n", a2.get());
	}

}