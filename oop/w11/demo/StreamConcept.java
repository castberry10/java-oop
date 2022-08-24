package w11.demo;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamConcept {

	public static void main(String[] args) {
		
		List<String> season = List.of("하지", "동지", "춘분", "추분", "입동");

		int sum = season.stream() // stream creation
				.filter(w -> w.charAt(w.length() - 1) == '지') // filter
				.mapToInt(w -> w.length()) // map
				.sum(); // reduce
		System.out.println(sum);

		Random r = new Random();
		IntStream is = r.ints(10, 20);	// 10 이상 20 미만 난수 스트림 생성
		is.limit(10).forEach(n -> System.out.print(n + " "));
		System.out.println();

		String str = null;
		try (BufferedReader br = Files.newBufferedReader(Paths.get("동백꽃.txt"))) {
			Stream<String> lines = br.lines();
			str = lines.limit(4).collect(Collectors.joining(" "));
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		System.out.println(str);

		Pattern pattern = Pattern.compile("\\P{L}+");
	    Stream<String> ss = pattern.splitAsStream(str);
		ss.forEach(s -> System.out.print(s + " "));
		


	}

}