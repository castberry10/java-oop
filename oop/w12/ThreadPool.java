package w12;

import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// 아무것도 리턴하지 않는 Runnable.
		// Runnable의 run 메소드는 예외를 던지지 않기 때문에
		// 람다식 내에서 발생하는 확인예외는 람다식 내에서 처리해야 한다.
		Runnable runnableTask = () -> {
			try {
				System.out.println(Thread.currentThread().getName() + ": Run");
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		// String을 리턴하는 Callable.
		// Callable의 call 메소드는 예외를 던지는 놈이므로
		// 람다식 안에서 발생하는 확인예외를 그 내부에서 처리하지 않고
		// 바깥으로 내보낼 수 있다. -> main 메소드 헤더에 throws 절이 붙어있다.
		// 람다식 안에서 발생하는 InterrupedException을 main 바같으로 내보낸다.
		Callable<String> callableTask = () -> {
			TimeUnit.MILLISECONDS.sleep(300);
			return Thread.currentThread().getName() + ": Callable";
		};

		ExecutorService es = Executors.newSingleThreadExecutor();
		es.execute(runnableTask); // execute 메소드는 즉시 리턴한다. runnable 태스크는 es의 쓰레드에서 실행된다.
		System.out.println(Thread.currentThread().getName() + ": execute 호출 후");

		Future<?> f = es.submit(runnableTask); // submit 메소드는 즉시 리턴한다. 
			// 만약 es의 쓰레드가 아직 예전 태스크를 실행하고 있다면 이 태스크는 대기열로 들어간다.
		assertNull(f.get()); // 아무 것도 반환하지 않는 Runnable을 submit하면 null이 들어있는 Future이 반환된다.
		System.out.println();
		
		List<Callable<String>> callableTasks = new ArrayList<>();
		callableTasks.add(callableTask);
		callableTasks.add(callableTask);
		callableTasks.add(callableTask);

		List<Future<String>> futures = es.invokeAll(callableTasks); 
			// invokeAll은 모든 Callable task들이 끝날 때까지 리턴하지 않는다.
		for (Future<String> future : futures)
			System.out.println(future.get()); // 각 Future에는 각 Callable의 반환값이 들어 있다.
		System.out.println(Thread.currentThread().getName() + ": invokeAll 호출 후");
		System.out.println();

		Future<String> future = es.submit(callableTask); // submit은 즉시 리턴한다.
		String result = null;
		try {
			result = future.get(); // task가 끝날 때까지 기다린다. (리턴하지 않는다.)
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println(result);
		
		boolean canceled = future.cancel(true); // task가 이미 끝났으므로 cancel 결과는 false.
		System.out.println(canceled);
		es.shutdown();  // shutdown을 호출하지 않으면 task가 없어도 스레드풀이 한 동안 살아있다.
		// --> 애플리케이션이 종료되지 않는다. (콘솔창에 빨간 불).
	}

}