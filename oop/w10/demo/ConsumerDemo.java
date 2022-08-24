package w10.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {

	public static void main(String[] args) {

		// Fuctional interface인 Consumer<T>는 accept(T) 메소드를 갖는 놈이다.
		// Consumer.accept(T)는 T를 받아 무언가 일을 하고 아무 것도 반환하지 않는다.
		// Consumer 인터페이스의 accept 메소드에는 무슨일을 하는지 정해져 있지 않다. (메소드가 구현되어 있지 않다.)

		// (1) Consumer 타입 랍다식에게 직접 accept를 호출하는 예.

		// Consumer 타입 람다식 선언
		// 람다식 선언에서 무슨일을 할지 정해준다.
		Consumer<String> consumer = s -> System.out.println(s);
		Consumer<String> consumerString = s -> System.out.println(s.toUpperCase());
		Consumer<Integer> intConsumer = i -> System.out.println(5 * i);

		// Consumer에게 accept(T) 호출 - T를 인자로 넘겨주면서 accept를 호출한다.
		consumer.accept("하이"); 
		consumerString.accept("High priority"); 
		intConsumer.accept(4);
		System.out.println("---------------");

		// (2) Consumer 타입 파라미터를 갖는 메소드를 호출하는 예.

		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(5);
		numbers.add(9);
		numbers.add(8);
		Consumer<Integer> printer = n -> System.out.print(n + " ");
		
		// List.forEach는 Consumer를 파라미터로 갖는 메소드이다.
		numbers.forEach(printer); // numbers의 각 원소를 printer.accpet에게 인자로 넘겨준다.
		System.out.println();
		numbers.forEach(n -> System.out.print(n + " ")); // 람다식을 직접 적을 수도 있다.
		System.out.println();
		System.out.println("---------------");

		// (3) Consumer의 default 메소드인 andThen을 사용하여 복합 (Composite) Consumer를 만들고 이용하는 예.

		Consumer<Integer> c1 = i -> System.out.print(5 * i + " ");
		Consumer<Integer> c2 = i -> System.out.print(5 + i + " ");

		c1.andThen(c2).accept(4); // c1을 한 후 c2를 하는 복합 Consumer에게 4를 인자로 넘겨준다.
		System.out.println();
		c2.andThen(c1).accept(5); 
		System.out.println();
		c1.andThen(i -> System.out.println(3 * i)).accept(4); 
	}

}