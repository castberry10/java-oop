package w8.generics;

import java.util.ArrayList;
import java.util.List;

public class UpperBoundDemo {

	public static void main(String[] args) {
		List<Number> ln = new ArrayList<>();
		
		ln.add(1);
		ln.add(5);
		ln.add(1.1f);
		System.out.println(ln);
		
		List<Number> ln2 = new ArrayList<>();
		ln2.add(10);
		ln2.add(100);
		ln.addAll(ln2);
		System.out.println(ln);
		
		List<Integer> li = new ArrayList<>();
		li.add(-1);
		li.add(-100);
		ln.addAll(li);
		System.out.println(ln);

	}

}
