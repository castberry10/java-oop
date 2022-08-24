package w11.demo;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class OptionalIntDemo {

	public static void main(String[] args) {
		
//		List<String> list = List.of("a", "ab", "abc");	// (A)
//		List<String> list = List.of("");				// (B)
		List<String> list = List.of();					// (C)
		IntStream is = list.stream().mapToInt(String::length);
		OptionalInt opt = is.max();
		int maxLength = opt.orElse(-1);
		System.out.println(opt.isEmpty());
		System.out.println(maxLength);

	}

}