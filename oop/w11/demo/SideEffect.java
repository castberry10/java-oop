package w11.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 부수효과는 가급적 피해야 한다.
// 부수효과란 파이프라인 연산이 파이프라인 바깥의 무엇인가를 변화시키는 일이다.
// 가령 프로그램 내의 변수 변경, 출력, 이메일 발송 등.
// peek, forEach 등은 원천적으로 부수효과를 내기 위해 만들어진 메소드이다.
// 이런 메소드를 단순히 디버깅을 위해 사용하는 것은 좋으나 그 외에는 사용하지 않아야 한다.
// 부수효과에 의존하는 파이프라인 연산은 일정하지 않거나 틀린 결과를 낼 위험이 있다.
//
// 파이프라인은 최종 결과를 얻기만 하면 된다.
// 연산 효율을 높이기 위해 중간 연산을 생략하거나 연산 순서를 바꿀 수 있다.
// 중간연산은 lazy하게 실행된다. 
// 최종연산이 시작될 때 중간연산들이 실행되는데 시스템이 알아서 효율적 계산을 추구한다.
// 소스에서 나오는 원소 순서와 최종적으로 나오는 결과의 순서가 같은 경우라 하더라도
// 중간단계에서 처리하는 원소의 순서가 바뀌거나
// 하나의 원소에 적용되는 연산의 순서가 바뀌거나
// 일부 연산은 생략되는 수가 있다.

public class SideEffect {

	public static void main(String[] args) {

		// 중간연산인 peek를 실행하지 않아도 올바른 결과를 낼 수 있으므로
		// peek 연산을 생략하는 최적화를 할 수 있다.
		List<String> l = Arrays.asList("A", "B", "C", "D");
		long count = l.stream().peek(System.out::println).count();
		System.out.println(count);
		System.out.println();

		// 입력과 출력의 순서가 일정한 경우라 하더라도
		// 중간연산을 적용하는 원소의 순서는 다를 수 있다.
		// 시스템은 효율을 높이기 위해 마음대로 처리한다.
		int[] a = (int[]) IntStream.range(0, 5)
				.parallel().map(x -> x * 2)
				.peek(System.out::println)
				.toArray();
		System.out.println(Arrays.toString(a));

		// 부수효과에 의존하는 프로그램은 위험하다!
		// 불필요하게 부수효과에 의존하지 말아야 한다.

		// 불필요하게 부수효과에 의존하는 예
		List<String> results = new ArrayList<>();
		List<String> src = Arrays.asList("a", "b", "c");
		src.stream().forEach(s -> results.add(s));

		// 부수효과에 의존하지 않는 예
		List<String> results2 = new ArrayList<>();
		results2 = src.stream().collect(Collectors.toList()); 

	} 

}