package w8.generics;

import java.util.LinkedList;
import java.util.List;

public class ListReverserTest {

	public static void main(String[] args) {
		
		List<Number> list = new LinkedList<>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(-1);
		list.add(3.3);
		list.add(10.5f);
		
		System.out.println(list);
		
		ListReverser.reverse(list);
		System.out.println(list);
		System.out.println();
		
//		List<Double> list = new LinkedList<>();
//		
//		list.add(1.0);
//		list.add(2.0);
//		list.add(3.0);
//		list.add(-1.0);
//		list.add(3.3);
//		list.add(10.5);
//		
//		System.out.println(list);
//		
//		ListReverser.reverse(list);
//		System.out.println(list);
//		System.out.println();
	}

}
