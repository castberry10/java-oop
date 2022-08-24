package w12;
import java.util.concurrent.CompletableFuture;

/**
 * 비동기식으로 일을 처리하는 방법을 설명한다.
 * 비동기식이란 main 스레도에서 하는 일과 
 * 별도의 쓰레드에서 하는 일이 
 * 시간적으로 서로 연관 없이 진행된다는 말이다.
 * 
 * 비동기식 연산을 위해서는 CompetableFuture을 사용한다.
 * 
 * @author jck
 *
 */
public class SimpleDemo {

	public static void main(String[] args) throws Exception {

		// main 쓰레도
		System.out.println("시작 " + Thread.currentThread().getName());

		// 리턴타입이 없는 경우, runAsynch를 호출하면서 일거리(태스크)를 넘겨준다.
		CompletableFuture.runAsync(() -> {
			System.out.println("Run" + Thread.currentThread().getName());
		}); // 람다식(태스크)은 시스템이 알아서 준비하는 ForkJoinPool 속에 있는 스레드에 의해 실행된다.
  		    // ForkJoinPool은 ExecutorService이다.
		    // ExecutorService는 내부에 쓰레드풀을 갖고 있다.

		// 리턴타입이 있는 경우, supplyAsynch를 호출하면서 일거리(태스크)를 넘겨준다.
		CompletableFuture<String> returnFuture = CompletableFuture.supplyAsync(() -> {
			System.out.println("Supply" + Thread.currentThread().getName());
			return "Hi";
		});  // supplyAsynch 호출은 CompletableFuture를 즉시 리턴한다.
		     // 태스크 실행에 시간이 많이 걸리는 경우, 리턴된 CompletableFuture에는 아직 계산 결과가 들어 있지 않다.
		     // 태스크가 끝나면 그 때 비로소 CompletableFuture에 값이 적힌다.
		
		System.out.println(returnFuture.get()); // 태스크가 끝날 때가지 get이 리런하지 않는다.
		System.out.println();
		
		// 위 작업은 댜른 쓰레드가 일을 하는 동안 
		// main 쓰레드가 그 일이 끝나기를 기다리지 않고, 나름대로의 일을 할 수 있다는 점에서는 비동기적이다.
		// 그러나 다른 쓰레드가 수행한 일의 결과를 받아 그 결과를 이용하려면 그 일이 끝나기를 기다려야 하므로
		// 완전한 비동기식 작업이라고 말할 수 없는 측면이 있다.
		
		// ------------------------------------------------------------------------------

		// CompletableFuture.suppyAsynch는 CompletableFuture을 반환하므로 아래와 같이 연결할 수 있다.
		
		CompletableFuture.supplyAsync(() -> {for(int i=0;i<1_000_000_000;i++); return "aa";})	
		// CompletableFuture<String>이 반환된다.
		.thenApply(x -> {
			System.out.println("0 " + Thread.currentThread().getName());
			return x.toUpperCase();
		})											// CompletableFuture<String>이 반환된다.
		.thenAccept(x -> {
			System.out.println("1 " + Thread.currentThread().getName());
			System.out.println(x); 
		})											// CompletableFuture<Void>가 반환된다.
		.thenRun(() -> {
			System.out.println("2 " + Thread.currentThread().getName());
			System.out.println(); 
		})											// CompletableFuture<Void>가 반환된다.
		.thenRun(() -> {
			System.out.println("3 " + Thread.currentThread().getName());
			System.out.println("End"); 
		});											// CompletableFuture<Void>가 반환된다.

		System.out.println("main은 여기에서 하고 싶은 다른 일을 할 수 있다. " + Thread.currentThread().getName()
				
				);
		
 		// for 루프가 도는 데 시간이 많이 걸리므로, 
		// "aa"가 리턴되기 전에, main 쓰레드는 "자기 일"을 다 끝내고 "끝"을 출력하고 종료한다.
		// main의 "자기 일"이란 chain을 엮는 일이다.
		// Chain을 엮는다는 말은 CompletableFuture에게 thenApply 메소드를 호출하여 새로운 복합 CompletableFuture를 만들고,
		// 그 CompletableFuture에게 theAccept를 호출하여 새 복합 CompletableFuture을 만들고,
		// 그 CompletableFuture에게 theRun을 호출하여 새 복합 CompletableFuture을 만드는 작업을 말한다.
		// 이렇게 만들어진 복합 CompletableFuture 속에 들어 있는 작업들(람다식들)은 시스템이 알아서 준비하는 
		// ForkJoinPool 속에 있는 스레드에 의해 실행된다.
		
		// CompletableFuture을 이용하면 어떤 태스크를 다른 쓰레드가 처리하게 할 수 있으며
		// 태스크가 끝나면, 다른 쓰레드가 그 결과를 이용하여 다른 작업을 하도록 설정할 수 있다. (Callback은 필요 없다.) 
		// 여기서 다른 쓰레드란 main이 아닌 쓰레드를 말한다.
		// main 쓰레드는 다른 쓰레드가 어떤 일을 하도록, 그리고 그 결과를 이용하는 다른 일도 다른 쓰레드가 하도록 설정해 놓고
		// 자기는 자기 할 일을 계속할 수 있다.
		
		// 위 작업은 댜른 쓰레드가 일을 하는 동안 
		// main 쓰레드가 그 일이 끝나기를 기다리지 않고, 나름대로의 일을 할 수 있을 뿐 아니라,
		// 다른 쓰레드가 수행한 일의 결과를 받아 그 결과를 이용하는 작업 자체도 
		// 다른 쓰레드에게 맡겨 놓고 자기는 자기 일만 하면 되므로
		// 완전한 비동기식 작업이라고 말할 수 있다.


	}

}