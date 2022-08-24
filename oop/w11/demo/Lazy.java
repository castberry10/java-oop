package w11.demo;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lazy {
	
	private static long counter = 0;
	public static void main(String[] args) {
		IntStream is = IntStream.range(1,  10)
				.filter(i -> {
					counter++; 
					return i < 5;
				});	// 중간연산
		System.out.print(counter + " "); // 중간연산은 아직 실행되지 않았다.
		counter = 0;
		System.out.print(is.sum() + " ");	// 최종연산
		System.out.print(counter + " "); 


		List<String> list = List.of("Brown", "Orange", "Black");
		Optional<String> first = list.stream()
				.filter(el -> {
					System.out.print(el + " ");
					return el.contains("a");
				})
				.map(el -> {
					System.out.print(el + " ");
					return el.toUpperCase();
				})
				.findFirst();
		// 중간연산과 최종연산이 한꺼번에 차례로 실행된다.
		// 만족할 만한 결과를 얻으면 더 이상은 일을 하지 않는다.
		// 위 에에서 Brown을 처리하고, Orange를 처리하고 나면 최종 결과를 얻게 되므로
		// Black은 처리하지 않는다.
		// 그러나 항상 이렇게 하는 것은 아니다. 컴퓨터가 알아서 한다. 
		// 프로그래머는 무엇을 할 것인지만 지정하고 어떻게 할 것인가는 컴퓨터가 알아서 정한다.
		

		Stream<String> stream2 = 
				Stream.of("Eric", "Elena", "Java")
				.filter(name -> name.contains("a"));

		Optional<String> firstElement = stream2.findFirst();
		// stream2가 빈 스트림인 경우에는 빈 Optional이 반환된다!
		
		Optional<String> anyElement = stream2.findAny(); 
		// IllegalStateException: 스트림이 이미 사용되었으므로 스트림이 닫힌 상태이다.

	}

}