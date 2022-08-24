package w10;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * This set of exercises is about lambdas and method references.
 * You will write a lambda or method reference corresponding to
 * each of several different functional interfaces. Each exercise
 * is named after the functional interface intended to be used
 * as the solution.
 * 
 * lambda와 method reference에 대한 문제입니다. 여러 가지 functional interface 각각에 해당하는
 * lambda나 method reference를 적어야 합니다. 각 문제의 이름은 그 문제를 푸는데 필요한 functional
 * interface에 맞춰 지었습니다.
 */
public class Lambdas {

	/**
	 * Write a lambda expression that is a predicate
	 * that tests whether a string is longer than four characters.
	 * 
	 * predicate인 lambda식을 작성하시오. 
	 * 이 lambda식은 string의 길이가 4보다 큰지 테스트하게 하시오.
	 */
	@Test
	public void a_predicate1() {
		Predicate<String> pred = n -> n.length()>4; // 

		assertTrue(pred.test("abcde"));
		assertFalse(pred.test("abcd"));
	}
	// Hint:
	// Predicate는 파라미터를 한 개 갖고 boolean을 반환하는 놈입니다.
	// 타입 파라미터가 String이므로 이 Predicate에게 String울 한 개 주면
	// true 혹은 false를 반환해야 합니다.
	// 즉, 람다식은 파라미터를 한 개 가져야 학고, 
	// 람다식 코드의 계산 결과는 boolean이어야 합니다.
	// Predicate에게 일을 시킬 때 호출하는 메소드 이름은 test입니다.


	/**
	 * Write a lambda expression that is a predicate
	 * that tests whether a string is empty.
	 * 
	 * predicate인 lambda식을 작성하시오. 
	 * 이 lambda식은 string이 empty인지 테스트하게 하시오.
	 */
	@Test
	public void a_predicate2() {

		Predicate<String> pred = n -> n.isEmpty();

		assertTrue(pred.test(""));
		assertFalse(pred.test("a"));
	}
	// Hint:
	// String의 메소드 isEmpty를 이용하세요.


	/**
	 * Write an unbound method reference that is a predicate
	 * that tests whether a string is empty. An unbound method
	 * reference has a class name on the left-hand side of the ::
	 * operator:
	 *
	 *     classname::methodname
	 *     
	 * Predicate인 unbound method reference를 적으시오. 
	 * 이 method는 string이 empty인지 여부를 테스트하는 메소드이어야 합니다. 
	 * Unbound method reference는 :: 연산자 왼쪽에 클래스 이름을 갖습니다.
	 * 
	 *     classname::methodname
	 */
	@Test
	public void a_predicate3() {
		Predicate<String> pred = String::isEmpty; // TODO

		assertTrue(pred.test(""));
		assertFalse(pred.test("a"));
	}

	/**
	 * Create a predicate that returns true if both predicates
	 * startsWithJ and lengthIs7 hold.
	 * 
	 * startsWithJ predicate와 lengthIs7 predicate가 모두 성립하면 true를 반환하는 predicate를
	 * 작성하시오.
	 */
	@Test
	public void a_predicate4() {
		Predicate<String> startsWithJ = s -> s.startsWith("J");
		Predicate<String> lengthIs7 = s -> s.length() == 7;

		Predicate<String> startsWithJAndLengthIs7 = n -> startsWithJ.and(lengthIs7).test(n);

		assertFalse(startsWithJAndLengthIs7.test("Hello"));
		assertFalse(startsWithJAndLengthIs7.test("HelloJ1"));
		assertFalse(startsWithJAndLengthIs7.test("Java1"));
		assertTrue(startsWithJAndLengthIs7.test("JavaOne"));
	}
	// Hint:
	// 힌트: java.util.function.Predicate.and()를 찾아보시오.
	// 아래와 같이 되어 있습니다.
	//
	// default Predicate<T>	and​(Predicate<? super T> other)	
	// Returns a composed predicate that represents a short-circuiting logical AND of this predicate and another.
	// 이 Predicate와 주어진 다른 Predicate의 AND에 해당하는 복합 Predicate를 반환한다.
	//
	// Predicate.and 메소드는 인터페이스에 이미 구현되어 있는 default 메소드이고
	// 개별 Predicate 객체에게 호출하는 인스턴스 메소드입니다.
	// 그러니까 어떤 Predicate에게 and를 호출하면서 다른 Predicate를 인자로 주면
	// 두 Predicate의 AND 연산을 하는 새 Predicate가 만들어집니다.

	/**
	 * Create a predicate that is true if the length of the provided string
	 * is 9 or the provided string equals ERROR.
	 * 
	 * 주어진 string의 길이가 9이거나 주어진 string이 ERROR와 같으면 true를 반환하는 predicate를 작성하시오. 
	 */
	@Test
	public void a_predicate5() {
		Predicate<String> lengthIs9 = s -> s.length() == 9;
		Predicate<String> equalsError = "ERROR"::equals;
		// Note: this could also be: Predicate.isEqual("ERROR")

		Predicate<String> lengthIs9orError = n -> lengthIs9.or(equalsError).test(n); // TODO

		assertFalse(lengthIs9orError.test("Hello"));
		assertTrue(lengthIs9orError.test("Hello J1!"));
		assertTrue(lengthIs9orError.test("ERROR"));
		assertFalse(lengthIs9orError.test("Error"));
	}
	// Hint:
	// java.util.function.Predicate.or()를 찾아보시오.

	/**
	 * Write a lambda expression that wraps the given
	 * string in parentheses.
	 * 
	 * 주어진 string을 괄호로 감싸는 lambda식을 작성하시오. 
	 */
	@Test
	public void b_function1() {
		Function<String, String> func = n -> "(" + n + ")";

		assertEquals("(abc)", func.apply("abc"));
	}
	// Hint:
	// Function<T, U>는 T를 주면 U를 반환하는 놈입니다.
	// 그러니까 Funtion인 람다식은 파라미터를 하나 갖고 뭔가 하나를 반환해야 합니다.
	// func는 Function<String, String> 타입이라고 선언되어 있으므로
	// func는 String 타입 파라미터를 하나 갖고 String을 반환하는 람다식이어야 합니다.
	// Function에게 일을 시킬 때 호출하는 메소드 이름은 apply입니다.

	
	/**
	 * Write a lambda expression that converts the
	 * given string to upper case.
	 * 
	 * 주어진 string을 대문자로 바꿔주는 lambda식을 작성하시오. 
	 */
	@Test
	public void b_function2() {
		Function<String, String> func = n -> n.toUpperCase(); 

		assertEquals("ABC", func.apply("abc"));
	}
	// Hint:
	// String.toUpperCase 메소드를 사용합니다.

	
	/**
	 * Write an unbound method reference that converts the
	 * given string to upper case.
	 * 
	 * 주어진 string을 대문자로 바꿔주는 unbound method reference를 적으시오. 
	 * unbound method reference라는 말은 
	 * method reference가 특정 객체와 연결되어 있지 않다는 말입니다.
	 * */
	@Test
	public void b_function3() {
		Function<String, String> func =  String::toUpperCase; 

		assertEquals("ABC", func.apply("abc"));
	}
	// Hint:
	// String::toUpperCase는 인스턴스 메소드입니다.
	// 따라서 주어진 파라미터 중 첫 번째 것이 메소드 호출의 receiver가 됩니다.
	// Function에게 일을 시킬 때는 인자를 한 개 넘겨줍니다.
	// 그 한 개의 인자가 메소드의 receiver가 됩니다.
	// 그러니까 String::toUpperCase는 x -> x.toUpperCase()와 같습니다.


	/**
	 * Given two Functions, one that converts a null reference to an
	 * empty string, and another that gets the length of a string,
	 * create a single function converts nulls and then gets the
	 * string's length.
	 * 
	 * null reference를 empty string으로 바꿔주는 function과 string의 길이를 반환하는 function이 있을
	 * 때, null을 empty string으로 바꾼 후 string의 길이를 반환하는 function을 작성하시오.
	 */
	@Test
	public void b_function4() {
		Function<String, String> unNullify = s -> s == null ? "" : s;
		Function<String, Integer> length = String::length;

		Function<String, Integer> lengthBis = unNullify.andThen(length);

		assertEquals((Integer)14, lengthBis.apply("Hello JavaOne!"));
		assertEquals((Integer)0, lengthBis.apply(""));
		assertEquals((Integer)0, lengthBis.apply(null));
	}
	// Hint:
	// java.util.Function.andThen()을 찾아보시오.


	/**
	 * Write a lambda expression that appends the
	 * string "abc" to the given StringBuilder.
	 * 
	 * “abc”를 주어진 StringBuilder에 덧붙이는 lambda식을 작성하시오. 
	 */
	@Test
	public void c_consumer1() {
		Consumer<StringBuilder> cons = n -> n.append("abc"); // TODO

		StringBuilder sb = new StringBuilder("xyz");
		cons.accept(sb);
		assertEquals("xyzabc", sb.toString());
	}
	// Hint:
	// Consumer에게 일을 시킬 때는 인자를 하나 주어야 합니다.
	// Consumer는 아무 것도 반환하지 않습니다. (아무 일도 하지 않는다는 말은 아닙니다.)
	// Consumer에게 일을 시킬 때 호출하는 메소드 이름은 accept입니다.
	// Consumer<StringBuilder>인 람다식을 적어야 하므로
	// 람다식의 파라미터는 한 개이고 파라미터의 타입은 StringBuilder이어야 합니다.
	// 파라미터의 타입은 명시적으로 선언하지 않아도 됩니다.


	/**
	 * Write a lambda expression that clears the given list.
	 * 
	 * 주어진 list를 비우는(clear하는) lambda식을 적으시오. 
	 */
	@Test
	public void c_consumer2() {
		Consumer<List<String>> cons = n -> n.clear(); // TODO

		List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
		cons.accept(list);
		assertTrue(list.isEmpty());
	}
	// Hint:
	// 람다식이 Consumer<List<String>> 타입이므로
	// List<String> 타입인 파라미터 한 개를 갖습니다.


	/**
	 * Write an unbound method reference that clears the given list.
	 * 
	 * 주어진 list를 비우는 unbound method reference를 적으시오.
	 */
	@Test
	public void c_consumer3() {
		Consumer<List<String>> cons = List::clear; // TODO

		List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
		cons.accept(list);
		assertTrue(list.isEmpty());
	}
	// Hint:
	// List.clear는 인스턴스 메소드입니다.


	/**
	 * Given two consumers, create a consumer that passes the list of String to the
	 * first consumer, then to the second.
	 * 
	 * 두 개의 consumer들이 주어졌을 때, 
	 * String의 list를 첫 consumer로 보내고 
	 * 그 다음으로 두 번째 consumer로 보내는 consumer를 작성하시오.
	 */
	@Test
	public void c_consumer4() {
		Consumer<List<String>> c1 = list -> list.add("first");
		Consumer<List<String>> c2 = list -> list.add("second");

		Consumer<List<String>> consumer = n -> c1.andThen(c2).accept(n);

		List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
		consumer.accept(list);
		assertEquals(Arrays.asList("a", "b", "c", "first", "second"), list);
	}
	// Hint:
	// java.util.function.Consumer.andThen()츨 찾아보시오.


	/**
	 * Write a lambda expression that returns a new StringBuilder
	 * containing the string "abc".
	 * 
	 * “abc”를 갖는 새 StringBuilder를 구성하여 반환하는 lambda식을 적으시오. 
	 */
	@Test
	public void d_supplier1() {
		Supplier<StringBuilder> sup = () -> new StringBuilder("abc"); 

		assertEquals("abc", sup.get().toString());
	}
	// Hint:
	// Supplier에게 일을 시킬 때는 아무 것도 주지 않습니다.
	// Supplier에게 일을 시키면 뭔가 하나를 반환합니다.
	// Supplier에게 일을 시킬 때 호출하는 메소드 이름은 get입니다.
	// sup은 Supplier<StringBuilder>이므로 이 람다식은
	// StringBuilder를 반환합니다.


	/**
	 * Write a lambda expression that returns a new, empty StringBuilder.
	 * 
	 * 빈 StringBuilder를 새로 만들어 반환하는 람다식을 적으시오.
	 */
	@Test
	public void d_supplier2() {
		Supplier<StringBuilder> sup = () -> new StringBuilder(); 
		assertEquals("", sup.get().toString());
	}

	/**
	 * Write a constructor reference that returns a new, empty StringBuilder.
	 * 
	 * 빈 StringBuilder를 새로 만들어 반환하는 constructor reference를 적으시오.
	 */
	@Test
	public void d_supplier3() {
		Supplier<StringBuilder> sup = StringBuilder::new; 

		assertEquals("", sup.get().toString());
	}

	/**
	 * Write a lambda expression that, given two strings, returns the result
	 * of concatenating the first with the second, followed by the
	 * first again.
	 * 
	 * 두 개의 string이 주어졌을 때, 첫 번째 것에 두 번째 것을 연결하고 
	 * 다시 첫 번째 것을 그 뒤에 한 번 더 연결한 string을 반환하는 lambda식을 적으시오. 
	 */
	@Test
	public void e_bifunction1() {
		BiFunction<String, String, String> bifunc = (a, b) -> a+b+a; 

		assertEquals("FirstSecondFirst", bifunc.apply("First", "Second"));
	}
	// Hint:
	// BiFunction<T, U, R>에게 두 개의 값 T와 U를 주면 R 한 개를 반환합니다.
	// bifunc는 BiFunctin<String, String, String>이므로
	// 이 놈에게 String 두 개를 주면 String 한 개를 반환하는 람다식입니다.


	/**
	 * Write a lambda expression that returns the index of
	 * the first occurrence of the second string within the first string,
	 * or -1 if the second string doesn't occur within the first string.
	 * 아래와 같이 작동하는 lambda식을 적으시오.
	 * 
	 * 두 개의 string이 주어졌을 때, 
	 * 두 번째 string이 첫 번째 string의 어느 위치에 들어 있는지 찾아 그 index를 반환한다.
	 * 여러 번 나타나는 경우에는 첫 번째 나타나는 위치를 반환한다. 
	 * 두 번째 string이 첫 번째 string에 나타나지 않는 경우에는 -1을 반환한다.
	 */
	@Test
	public void e_bifunction2() {
		BiFunction<String, String, Integer> bifunc = (a, b) -> a.indexOf(b); 

		assertEquals(3, bifunc.apply("abcdefghi", "def").intValue());
		assertEquals(-1, bifunc.apply("abcdefghi", "xyz").intValue());
	}
	/*
	 * 힌트: String 메소드
	 *     public int indexOf(String)
	 * 를 이용한다.
	 * 
	 * BiFunction은 파라미터를 두 개 갖는 함수를 말한다. 가령, z = f(x, y).
	 * java.util.function.BiFunction<T, U, R> 
	 *   R	apply(T t, U u)
	 *   
	 *   T, U: 함수에 주어지는 첫 번째, 두 번째 인자.
	 *   R: 함수의 반환값.
	 */

	/**
	 * Write an unbound method reference that returns the index of
	 * the first occurrence of the second string within the first string,
	 * or -1 if the second string doesn't occur within the first string.
	 * 
	 * 아래와 같이 작동하는 unbound method reference를 적으시오.
	 * 두 개의 string이 주어졌을 때, 
	 * 두 번째 string이 첫 번째 string의 어느 위치에 들어 있는지 찾아 그 index를 반환한다.
	 * 여러 번 나타나는 경우에는 첫 번째 나타나는 위치를 반환한다. 
	 * 두 번째 string이 첫 번째 string에 나타나지 않는 경우에는 -1을 반환한다.
	 */
	@Test
	public void e_bifunction3() {
		BiFunction<String, String, Integer> bifunc = String::indexOf; // TODO

		assertEquals(3, bifunc.apply("abcdefghij", "def").intValue());
		assertEquals(-1, bifunc.apply("abcdefghij", "xyz").intValue());
	}

	/**
	 * Write a lambda expression that appends the 'suffix'
	 * variable (a String) to the sb variable (a StringBuilder).
	 * 
	 * suffix를 sb에 append하는 람다식을 적으시오.
	 */
	@Test
	public void f_runnable1() {
		StringBuilder sb = new StringBuilder("abc");
		String suffix = "xyz";

		Runnable r = () -> sb.append(suffix); 

		r.run();
		r.run();
		r.run();
		assertEquals("abcxyzxyzxyz", sb.toString());
	}
	// Hint:
	// Runnable에게 일을 시킬 때는 아무 것도 주지 않습니다.
	// Runable은 아무 것도 반환하지 않습니다.
	// Runnable에 일을 시킬 때, 그 놈이 무슨 일을 할지를 람다식 코드로 적어줍니다.


	/**
	 * Write a lambda expression that takes a string argument
	 * and returns the index of that argument into the string
	 * "abcdefghij", or that returns -1 if the string argument
	 * doesn't occur.
	 * 
	 * String 인자를 하나 받고, 그 인자가 string "abcdefghij"의 몇 번째에 
	 * 나타나는지(index)를 반환하는 람다식을 적으시오. 
	 * 만약 인자로 주어진 string이 들어 있지 않다면 -1을 반환해야 합니다. 
	 */

	@Test
	public void g_boundMethodRef1() {
		Function<String, Integer> func = a -> "abcdefghij".indexOf(a) ;

		assertEquals(2, func.apply("cde").intValue());
		assertEquals(4, func.apply("efg").intValue());
		assertEquals(-1, func.apply("xyz").intValue());
	}
	// Hint:
	// String울 주면 Integer를 반환하는 람다식을 적으세요.
	// 주어진 문자열에 indexOf() 메소드를 호출하면 됩니다.

	/**
	 * Write a bound method reference that takes a string argument
	 * and returns the index of that argument into the string
	 * "abcdefghij", or that returns -1 if the string argument
	 * doesn't occur. A bound method reference has an instance,
	 * or an expression that evaluates to an instance, on the left-hand
	 * side of the :: operator:
	 *
	 *     myObject::methodname
	 *
	 * This is in contrast to an unbound method reference, which has
	 * a classname on the left-hand side of the :: operator.
	 * 
	 * String 인자를 하나 받고, 그 인자가 string "abcdefghij"의 몇 번째에 
	 * 나타나는지(index)를 반환하도록 하는 bound method reference를 적으시오. 
	 * 만약 인자로 주어진 string이 들어 있지 않다면  -1을 반환해야 합니다.
	 * 
	 * Bound method reference는 :: 연산자의 왼쪽에 객체가 옵니다:
	 * 
	 *  myObject::methodname
	 *  
	 *  Unbound method reference의 경우 :: 연산자의 왼쪽에 클래스 이름이 왔었습니다.
	 * 
	 */
	@Test
	public void g_boundMethodRef2() {
		Function<String, Integer> func = "abcdefghij"::indexOf; // TODO

		assertEquals(2, func.apply("cde").intValue());
		assertEquals(4, func.apply("efg").intValue());
		assertEquals(-1, func.apply("xyz").intValue());
	}
	// 힌트
	// :: 연산자의 왼쪽에 주어진 문자열을 적으면 됩니다.
}