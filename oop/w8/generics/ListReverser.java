package w8.generics;

import java.util.Collections;
import java.util.List;

public class ListReverser {

	/**
	 * 리스트의 원소 순서를 뒤집어준다. 
	 * @param list 주어진 리스트
	 */
	public static void reverse(List<Number> list) {
		for(int i = 0; i < list.size() / 2 ; i++) {
			Number temp = list.get(i);
			list.set(i, list.get(list.size() - 1 - i));
			list.set(list.size() - 1 - i, temp);
		}
	}

}
