package w11;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 간단한 stream pipelines 사용 연습입니다.
 * 중간연산(intermediate operations)과 기초적인 collector를 다룹니다.
 *
 * 일부 문제에서 "reader"라는 이름의 BufferedReader 변수를 사용하는데
 * 이 변수를 선언하고 값을 설정하는 코드가 이 프로그램의 맨 끝 부분에 있습니다.
 * 이 코드는 JUnit 시스템에 의해 자동으로 실행됩니다.
 */

@SuppressWarnings("all") // <-- 이 줄은 삭제하세요.
public class Streams {
    /**
     * Given a list of words, create an output list that contains
     * only the odd-length words, converted to upper case.
     */
    @Test 
    public void d1_upcaseOddLengthWords() {
        List<String> input = List.of(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot");

        List<String> result =  input.stream().filter(w -> w.length()%2 == 1).map(w -> w.toUpperCase()).collect(Collectors.toList()); //

        assertEquals(List.of("BRAVO", "CHARLIE", "DELTA", "FOXTROT"), result);
    }
    // Hint 1:
    // Use filter() and map().
    // Hint 2:
    // To create the result list, use collect() with one of the
    // predefined collectors on the Collectors class.


    /**
     * Take the third through fifth words of the list, extract the
     * second letter from each, and join them, separated by commas,
     * into a single string. 
     * Watch for off-by-one errors. (한칸씩 어긋나는 에러를 조심하시오.)
     */
    @Test 
    public void d2_joinStreamRange() {
        List<String> input = List.of(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot");

        String result = input.stream().skip(2).limit(3).map(w -> Character.toString(w.charAt(1))).collect(Collectors.joining(","));
        assertEquals("h,e,c", result);
    }
    // Hint 1:
    // Use Stream.skip() and Stream.limit().
    // Hint 2:
    // Use Collectors.joining().


    /**
     * Count the number of lines in the text file. 
     * 
     * (Remember to use the BufferedReader named "reader" 
     * that has already been opened for you.
     * "reader"라는 이름의 BufferedReader가 이미 열려 있으니
     * 이것을 사용하면 됩니다.)
     * 
     * 이 파일의 맨 끝에 @Before 아노테이션이 붙어 있는
     * 메소드와 @After 아노테이션이 붙어 있는 메소드가 있습니다.
     * 각 @Test 메소드가 실행되기 전에 @Before 메소드가 먼저 실행되고
     * @Test 메소드 실행 후에 @After 메소드가 실행됩니다.)
     * 
     * @throws IOException
     */
    @Test 
    public void d3_countLinesInFile() throws IOException {
        
    	long count = reader.lines().count(); 

        assertEquals(14, count);
    }
    // Hint 1:
    // Use BufferedReader.lines() to get a stream of lines.
    // Hint 2:
    // Use Stream.count().


    /**
     * Find the length of the longest line in the text file.
     *
     * @throws IOException
     */
    @Test 
    public void d4_findLengthOfLongestLine() throws IOException {
        
    	int longestLength = reader.lines().mapToInt(String::length).max().orElse(-1); 

        assertEquals(53, longestLength);
    }
    // Hint 1:
    // Use Stream.mapToInt() to convert a stream of objects to an IntStream.
    // IntSream에서 최대값을 찾는 메소드는 max입니다.
    // max는 OptionalInt를 반환합니다.
    // Hint 2:
    // Look at java.util.OptionalInt to get the result.
    // Hint 3:
    // Think about the case where the OptionalInt might be empty
    // (that is, where it has no value). 이런 경우에는 -1을 반환하게 하세요.


    /**
     * Find the longest line in the text file.
     *
     * @throws IOException
     */
    @Test 
    public void d5_findLongestLine() throws IOException {
    	
        String longest = reader.lines().max((p1, p2) -> p1.length()- p2.length()).orElse(""); 

        assertEquals("Feed'st thy light's flame with self-substantial fuel,", longest);
    }
    // Hint 1:
    // Use Stream.max() with a Comparator.
	// Stream 클래스에도 max 메소드가 있습니다. 
	// Stream.max는 Comparator를 파라미터로 갖습니다.
	// api 문서에서 Stream.max를 찾아보세요.


    /**
     * Select the longest words from the input list. 
     * 가장 긴 단어들이 들어 있는 리스트를 구하세요.
     */
    @Test 
    public void d6_selectLongestWords() {
        List<String> input = List.of(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel");

        
        List<String> result = input.stream().filter(w -> w.length() == input.stream().mapToInt(a -> a.length()).max().orElse(-1)).collect(Collectors.toList());
//        List<String> result = input.stream().

        assertEquals(List.of("charlie", "foxtrot"), result);
    }
    // Hint:
    // Consider making two passes over the input stream.
    // 두 문장으로 작성하는 편이 좋습니다.
    // 첫 문장에서는 가장 긴 단어를 길이를 알아냅니다.
    // 두 번째 문장에서는 그 길이를 갖는 단어 리스트를 구합니다.

    /**
     * Select the list of words from the input list whose length is greater than
     * the word's position in the list (starting from zero) .
     */
    @Test 
    public void d7_selectByLengthAndPosition() {
        List<String> input = List.of(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel");
       
        List<String> result = input.stream().filter(w ->w.length() > input.indexOf(w)).collect(Collectors.toList()); // TODO

        assertEquals(List.of("alfa", "bravo", "charlie", "delta", "foxtrot"), result);
    }
    // Hint:
    // Instead of a stream of words (Strings), run an IntStream of indexes of
    // the input list. Use index values to get elements from the input list.
    // 0번째 단어 길이가 0보다 큰가? 1번 단어 길이가 1보다 큰가? 2번 단어 길이가 2보다 큰가? 이런 식으로 풀면 됩니다.
    // IntStream을 가지고 작업합니다. 해당되는 번호의 단어만을 input에서 골라 리스트에 모으면 됩니다.


// ========================================================
// END OF EXERCISES
// TEST INFRASTRUCTURE IS BELOW
// ========================================================


    private BufferedReader reader;

    @Before
    public void z_setUpBufferedReader() throws IOException {
        reader = Files.newBufferedReader(
                Paths.get("sonnet.txt"), StandardCharsets.UTF_8);
    }

    @After
    public void z_closeBufferedReader() throws IOException {
        reader.close();
    }

}