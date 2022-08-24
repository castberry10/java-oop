package w10.demo;

import java.util.function.Function;

public class FunctionDemo {
	public static void main(String args[]) {
		
		// Fuctional interface인 Function<T, R>은 apply(T) 메소드를 갖는 놈이다.
		// Function.apply(T)는 T를 받아 무언가 일을 하고 R을 반환한다.
		// Function 인터페이스의 apply 메소드에는 무슨일을 하는지 정해져 있지 않다. (메소드가 구현되어 있지 않다.)

		// Function 타입 랍다식에게 직접 apply를 호출하는 예.
		// Function 타입 람다식 선언
		// 람다식 선언에서 무슨일을 할지 정해준다.
        Function<Integer, Double> half = a -> a / 2.0;	// Integer를 받아 Double을 만들어 주는 놈.
        System.out.println(half.apply(3));	// Integer 3에 half를 적용하면 Double이 만들어진다.

		// Function의 default 메소드인 andThen을 사용하여 복합 (Composite) Consumer를 만들고 이용하는 예.
        Function<Integer, Double> threeHalves = half.andThen(a -> 3 * a);
        // 3에 half를 먼저 적용하여 결과를 얻고 그 결과에 "a -> 3 * a"을 적용하여 최종 결과를 낸다.
        // (Integer -> Double, Double -> Double) = Integer -> Double
		System.out.println(threeHalves.apply(3));  

		// Function의 default 메소드인 compose을 사용하여 복합 (Composite) Consumer를 만들고 이용하는 예.
		Function<Integer, Double> halfOfSquare = half.compose(x -> x*x);
        // 3에 "x -> x*x"를 적용하여 결과를 얻고 그 결과에 half를 적용하여 최종 결과를 낸다.
		System.out.println(halfOfSquare.apply(4));
		
//		Bounded wild card에 대한 보충설명
		
//		Interface Function<T,​R>
//
//		T - the type of the input to the function
//		R - the type of the result of the function
//
//		Function<T, R>의 메소드 ----------------------------------------------------		
//		default <V> Function<T,​V>	andThen​(Function<? super R,​? extends V> after) 
//   	--------------------------------------------------------------------------
//		
//		일단, 아래와 같이 wild card를 무시하고 생각해 보자.
//		<V> Function<T,​V>	andThen​(Function<R, V> after)	
//		
//		T를 받아 R를 만드는 Function f1이 있고,
//		R을 받아 V를 만드는 Function f2가 있을 때,
//		f1.andThen(f2)는 T를 받아 V를 만드는 복합 Function을 반환한다.
//
//		이제, bounded wild card를 포함해서 생각해 보자.
//		<V> Function<T,​V>	andThen​(Function<? super R,​? extends V> after)
//		
//		R의 슈퍼타입을 받을 수 있는 놈은 R도 받을 수 있다.
//		V의 서브클래스를 만드는 놈은 V를 만드는 놈이기도 하다.
//
//		T를 받아 R를 만드는 Function f1이 있고, 
//		R의 수퍼타입을 받아 V의 서브타입을 만드는 Function f2가 있을 때,
//		f1.andThen(f2)는 T를 받아 V를 만드는 Function을 반환한다.
//
// 		Function<Integer, Double> f1이 있고
//		f1.andThen(f2)를 실행하여 Function<Integer, Number> f3를 합성하고자 한다.
//		아래와 같이 여러 가지 방법이 모두 가능하다.

        Function<Integer, Double> f1 = a -> a / 2.0;	// Integer를 받아 Double을 만들어 주는 놈.
        Function<Integer, Number> f3;					// Integer를 방아 Number를 만들어 주는 놈.
        
        Function<Double, Number> f21 = a -> a.intValue(); // Double을 받아 Number를 만들어 주는 놈.
        // f1과 f2를 합성하면 Integer를 받아 Number를 만들어 주는 놈이 된다. 그러니까 그 놈을 f3에 저장할 수 있다.
        f3 = f1.andThen(f21); 
        System.out.println(f3.apply(5));

        Function<Double, Integer> f22 = a -> a.intValue(); // Double을 받아 Integer를 만들어 주는 놈. 
        // Integer를 만들어 주는 놈은 Number를 만들어 주는 놈이기도 하다. 
        // 그러니까 f1과 f2 합성에 의해 Integer를 받아 Number를 만들어 주는 놈이 만들어지는 셈이며 그 놈을 f3에 저장할 수 있다.
        f3 = f1.andThen(f22);  
        System.out.println(f3.apply(5));
        
        Function<Number, Number> f23 = a -> a; // Number을 받아 Number를 만들어 주는 놈. 
        // Number를 받는 놈은 Double도 받을 수 있다.
        // 그러니까 Double을 만들어내는 f1과 Number를 받는 f2를 합성할 수 있다.
        f3 = f1.andThen(f23); 
        System.out.println(f3.apply(5));
		
        Function<Number, Double> f24 = a -> a.doubleValue(); // Number를 받아 Double을 만들어 주는 놈.
        // Number을 받는 놈은 Double을 받을 수도 있으며, Double을 만들어 주는 놈은 Number를 만들어 주는 놈이기도 하다.
        f3 = f1.andThen(f24); 
        System.out.println(f3.apply(5));
        
        // 인터페이스 자체는 형식만 지정해 준다.
        // 인터페이스가 어떤 일을 하는지는 인터페이스를 어떻게 구현하느냐에 따라 달라진다.
        // 인터페이스를 구현한다는 말은 그 인터페이스를 구현하는 클래스를 작성하거나 람다식을 작성하는 것을 의미한다.
        // 위 예에서 f21, f22, f23, f24 람다식이 서로 다르면 f3의 작동도 달라진다!
		
	}
	
}