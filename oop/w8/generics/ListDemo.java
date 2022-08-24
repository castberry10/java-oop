package w8.generics;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {

	public static void main(String[] args) {
		List<Number> list = new ArrayList<>();
		list.add(1);
		list.add(1.1f);
		System.out.println(list);
		
		list.add(new Integer(1));
		list.add(new Float(1.1f));
		System.out.println(list);
		
		list.add(Integer.valueOf(1));
		list.add(Float.valueOf(1.1f));
		System.out.println(list);
		

	}

}
