package w11.demo;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ObtainingStream {

	public static void main(String[] args) throws IOException {

		String[] sa = new String[] { "A", "B", "C" };
//		Stream<String> words = Arrays.stream(sa);
		Stream<String> words = Arrays.stream(sa, 1, 3);
//		Stream<String> words = Stream.of(sa);
		words.forEach(s -> System.out.print(s + " "));
		System.out.println();
		
		IntStream ints = IntStream.range(10, 20);
		ints.forEach(s -> System.out.print(s + " "));
		System.out.println();

		Stream<String> seasons = Stream.of("하지", "동지", "춘분", "추분", "입동");
		seasons.forEach(s -> System.out.print(s + " "));
		System.out.println();
		
		// 빈 스트림
		Stream<String> silence = Stream.empty();	
		silence.forEach(s -> System.out.print(s + " "));
		System.out.println();
		
		// 필요할 때마다 "야옹"을 만든다. 무한히 긴 스트림이다.
		Stream<String> yaongs = Stream.generate(() -> "야옹");
		yaongs.limit(10).forEach(s -> System.out.print(s + " "));
		System.out.println();

		// 필요할 때마다 double 난수를 만든다. 무한히 긴 스트림이다.
		Stream<Double> randoms = Stream.generate(Math::random);
		randoms.limit(4).forEach(s -> System.out.print(s + " "));
		System.out.println();
		
 		//java.util.stream.Stream 인터페이스
		// static <T> Stream<T> iterate​(T seed, UnaryOperator<T> f)
		// 무한 스트림: seed, f(seed), f(f(seed)), ...  
		// 0, 0+1, (0+1)+1, ((0+1)+1)+1, ...
		Stream<Integer> integers = Stream.iterate(0, n -> n + 1);	 
		integers.limit(4).forEach(s -> System.out.print(s + " "));
		System.out.println();

		// 무한 스트림
		Stream<BigInteger> bIntegers = Stream.iterate(
				BigInteger.ZERO, 
				n -> n.add(BigInteger.ONE));
		bIntegers.limit(4).forEach(s -> System.out.print(s + " "));
		System.out.println();

		// 0부터 10000000 미만 유한 스트림
		BigInteger limit = new BigInteger("10000000");
		bIntegers = Stream.iterate(
				BigInteger.ZERO, 
				n -> n.compareTo(limit) < 0,
				n -> n.add(BigInteger.ONE));
		
		
	}
}