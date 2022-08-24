package w8.generics;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReplaceAllDemo {

	public static void main(String[] args) {
		
		List<Integer> li = Arrays.asList(1, 2, 3, -1, 3);
		System.out.println("List<Integer>: " + li);
		System.out.println("Replace all 3's with 30.");
		Collections.replaceAll(li, 3, 30);
		
		System.out.println(li);
		System.out.println();
		
		List<Double> li1 = Arrays.asList(1.0 , 2.0, 3.0, -1.0, 3.0);
		System.out.println("List<Float>: " + li1);
		System.out.println("Replace all 3.0's with 30.0.");
		Collections.replaceAll(li1, 3.0, 30.0);
		System.out.println(li1);
		System.out.println();
		
		
		List<String> li2 = Arrays.asList("Sping", "Summer", "Fall", "Winter", "Spring", "Spring");
		System.out.println("List<String>: " + li2);
		System.out.println("Replace all \"Spring\"s with \"Summer\".");
		Collections.replaceAll(li2, "Spring", "Summer");
		System.out.println(li2);
		System.out.println();
		
		

	}

}
