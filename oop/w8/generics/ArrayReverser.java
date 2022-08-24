package w8.generics;

public class ArrayReverser {

	/**
	 * 주어진 배열의 원소 순서를 뒤집어준다. 
	 * @param array 주어진 배열.
	 */
	public static void reverse(Number[] array) {
		
		
		for (int i = 0; i < array.length / 2; i++) {
			Number temp = array[i];
			array[i] = array[(array.length - 1) - i];
			array[(array.length - 1) - i] = temp;
		}
	}
	
}
