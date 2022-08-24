package w8.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericReverser {

	/**
	 * 리스트의 원소 순서를 뒤집어준다. 
	 * @param list 주어진 리스트 
	 */
	public static <E> void reverse(List<E> list) {
		
		
		
		for(int i = 0; i < list.size() / 2 ; i++) {
			E temp = list.get(i);
			list.set(i, list.get(list.size() - 1 - i));
			list.set(list.size() - 1 - i, temp);
		}
		
	}
}
