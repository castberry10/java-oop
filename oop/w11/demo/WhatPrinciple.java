package w11.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WhatPrinciple {

	public static void main(String[] args) throws IOException {

		// 파일을 읽어 String으로 저장.
		String contents = new String(Files.readAllBytes(Paths.get("동백꽃.txt")), StandardCharsets.UTF_8); 
		// String contents를 단어로 분해한다.
		// 영문, 한글 알파벳(Letter)이 아닌 모든 글자들이 구분자(delimiter)로 사용된다.
		List<String> words = List.of(contents.split("\\P{L}+"));
		
		// 한 단어 한 단어 차례로 처리하는 절차를 차례차례 지정한 코드
		// Imperative programming(명령형 프로그래밍)이라고 부르며 우리가 지금까지 공부한 방식이다.
		int count1 = 0;
		for (String w : words)
			if (w.length() > 6)
				count1++;

		// Declarative programming (선언형 프로그래밍)
		// 어떻게 할지가 아니라 무엇을 할지를 정해준다.
		// 어떻게 할지는 컴퓨터가 알아서 결정한다.
		long count2 = words.stream().filter(w -> w.length() > 6).count();
		long count3 = words.parallelStream().filter(w -> w.length() > 6).count();

		System.out.format("%d, %d, %d\n", count1, count2, count3);

		String longWords = words.stream().filter(w -> w.length() > 6).collect(Collectors.joining(", "));
		System.out.println(longWords);
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get("동백꽃.txt"))) {
			Stream<String> lines = br.lines();
			lines.filter(line -> line.contains("감자")).findFirst().ifPresent(System.out::println);
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

	}
}