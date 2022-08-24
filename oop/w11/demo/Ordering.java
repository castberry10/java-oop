package w11.demo;

import java.util.Arrays;
import java.util.List;

public class Ordering {

	public static void main(String[] args) {
		
		// ForEach는 스트림 원소의 순서를 존중하지 않는다.
		// ForEachOrdered는 스트림 원소의 순서를 존중한다.
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		numbers.stream().forEach(System.out::print);
		System.out.println();
		numbers.parallelStream().forEach(System.out::print);
		System.out.println();
		numbers.parallelStream().forEachOrdered(System.out::print);
		

	}

}