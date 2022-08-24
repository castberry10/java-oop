package w7.stutterlist;

/**
 * 정수만을 저장할 수 있는 ArrayList, 두 번째 버전.
 * Version 2.
 * 첫 버전에 아래 기능이 추가됨.
 * - 리스트 탐색을 위한 메소드 - indexOf와 contains 메소드.
 * - capacity 파라미터를 추가로 갖는 두 번째 구성자.
 * - precondition을 체크하고 이를 만족하지 않는 경우 exception을 던짐.
 */

import java.util.Arrays;  

public class ArrayIntList {

	protected static final int DEFAULT_CAPACITY = 10;

	// fields
	protected int size;
	protected int[] element;

	/**
	 * DEFAULT_CAPACITY 크기의 배열을 구성하고
	 * size를 0으로 설정한다.
	 */
	public ArrayIntList() {
		this(DEFAULT_CAPACITY);   // call the (int) constructor
	}

	/**
	 * 주어진 크기의 크기의 배열을 구성하고
	 * size를 0으로 설정한다.
	 * @param capacity 초기 용량 (0 이상이어야 함).
	 */
	public ArrayIntList(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("capacity cannot be negative: " + capacity);
		}
		size = 0;
		element = new int[capacity];
	}

	/**
	 * 주어진 값을 리스트의 마지막에 추가한다.
	 * 만약 배열이 이미 꽉 찬 상태이면 배열의 크기를 늘인다.
	 * @param value 추가할 값.
	 */
	public void add(int value) {
		add(size, value);
	}

	/**
	 * 주어진 값을 리스트의 주어진 인덱스 방에 삽입한다.
	 * 만약 배열이 이미 꽉 찬 상태이면 배열의 크기를 늘인다.
	 * 일단, index는 0 이상, size 이하라고 가정한다.
	 * @param index 인덱스.
	 * @param value 삽입할 값.
	 */
	public void add(int index, int value) {

		checkIndex(index, 0, size);
		// 위 체크를 통과했으면 인덱스에 관한 전제조건을 만족한 것임.

		checkResize();

		// slide elements to the right to make room
		for (int i = size; i > index; i--) {
			element[i] = element[i - 1];
		}

		// insert the value in the hole we just made
		element[index] = value;
		size++;

	}

	/**
	 * 주어진 인덱스의 값을 읽는다.
	 * @param index 인덱스 (0 이상 size 미만).
	 * @return 읽은 값.
	 */
	public int get(int index) {

		checkIndex(index, 0, size - 1);
		// 위 체크를 통과했으면 인덱스에 관한 전제조건을 만족한 것임.

		return element[index];
	}

	/**
	 * 주어진 인덱스의 값을 설정한다.
	 * @param index 인덱스 (0 이상 size 미만).
	 * @param value 값.
	 */
	public void set(int index, int value) {

		checkIndex(index, 0, size - 1);
		// 위 체크를 통과했으면 인덱스에 관한 전제조건을 만족한 것임.

		element[index] = value;
	}

	/**
	 * 원소의 수가 얼마인지 알아낸다.
	 * @return 원소 수.
	 */
	public int size() {
		return size;
	}

	/**
	 * 빈 리스트인가?
	 * @return 빈 리스트인경우 true, 그렇지 않으면 false.
	 */
	public boolean isEmpty() {
		return size == 0;  
	}

	/**
	 * 주어진 인덱스의 값을 제거하고 그 이후에 있는 값들을
	 * 앞으로 한 칸씩 이동한다.
	 * @param index 인덱스 (0 이상 size 미만).
	 */
	public void remove(int index) {

		checkIndex(index, 0, size - 1);
		// 위 체크를 통과했으면 인덱스에 관한 전제조건을 만족한 것임.

		for (int i = index; i < size - 1; i++) {
			element[i] = element[i + 1];
		}
		size--;
	}

	/**
	 * 주어진 값이 리스트에서 몇 번 방에 있는지 알아본다.
	 * 값이 여러 개 있으면 그 중 첫 번째 것의 방 번호를 반환한다.
	 * @param value 찾을 값.
	 * @return 방 번호, 만약 값이 없다면 -1.
	 */
	public int indexOf(int value) {
		for (int i = 0; i < size; i++) {
			if (element[i] == value) {
				return i;
			}
		}
		return -1;   // not found
	}

	/**
	 * 주어진 값이 리스트에 들어 있나?
	 * @param value 값.
	 * @return 값이 리스트에 들어 있으면 true, 그렇지 않으면 false.
	 */
	public boolean contains(int value) {
		return indexOf(value) >= 0;
	}

	/**
	 * 리스트를 아래 형식의 문자열로 반환한다.
	 * "[12, 34, -102, 1]"
	 * 만약 빈 문자열이면 "[]"를 반환한다.
	 */
	public String toString() {
		if (size > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (int i = 0; i < size - 1; i++)
				sb.append(element[i] + ", ");
			sb.append(element[size - 1] + "");
			sb.append("]");
			return sb.toString();
		}
		else
			return "[]";
	}

	/**
	 * private helper method.
	 * 배열이 꽉 찼는지 검사하고
	 * 꽉 찬 경우 배열의 크기를 두배로 늘여준다.
	 */
	private void checkResize() {

		if (size == element.length) {
			// resize the array
			element = Arrays.copyOf(element, 2 * size);
		}
	}

	/**
	 * private helper method. 
	 * 인덱스가 주어진 범위 내에 있는지 검사한다.
	 * min 이상이고 max 이하이면 유효하다.
	 * @param index 인덱스.
	 * @param min 최소값.
	 * @param max 최대값.
	 */
	private void checkIndex(int index, int min, int max) {
		if (index < min || index > max) {
			throw new ArrayIndexOutOfBoundsException("Wrong index: " + index);
		}
	}
}