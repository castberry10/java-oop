package w7.sortedlist1;

/**
 * SortedIntList에 정수들을 add하면 
 * 오름차순으로 정렬된 상태로 저장되는지 확인하는 아주 간단한 테스트 프로그램.
 * 테스트를 추가할 수 있다.
 * 
 *  이 프로그램은 JUnit 테스트 파일이 아니고
 *  단순히 main 메소드를 갖는 하나의 프로그램이다.
 */

public class SimpleTest {

	public static void main(String[] args) {

		SortedIntList list;

		// 네 개의 생성자 테스트
		System.out.println("Test 4 constructors ---------------");
		// 초기 용량이 음수인 SortedIntList 구성 시도
		try {
			list = new SortedIntList(-1);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		list = new SortedIntList(true);
		System.out.println(list);

		list = new SortedIntList(true, 5);
		System.out.println(list);

		// 초기 용량이 음수인 SortedIntList 구성 시도
		try {
			list = new SortedIntList(true, -1);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		list = new SortedIntList();
		System.out.println(list);

		list = new SortedIntList(5);
		System.out.println(list);

		System.out.println();

		System.out.println("SortedIntList with initial capacity 5");
		System.out.println("Test add(value) - check sorting and automatic capacity grow --------");
		System.out.println("Add 33, 12, -9, 1024, 1024, 1024");
		list.add(33);
		list.add(12);
		list.add(-9);
		list.add(1024);
		list.add(1024);
		list.add(1024);
		System.out.println(list);

		System.out.println("Test add(index, value) ---------------");
		try {
			list.add(3, 444);
		}
		catch (UnsupportedOperationException e) {
			System.out.println("UnsupportedOperationException is thrown.");
		}
		System.out.println();

		System.out.println("Test remove(index) --- ");
		list.remove(1);
		System.out.println(list);

		System.out.println("Test indexOf(value) --------");
		System.out.println("Index of 33 = " + list.indexOf(33));


		System.out.println("Test contains(value) --------");
		System.out.println("Contains 33? " + list.contains(33));
		System.out.println("Contains 333? " + list.contains(333));

		System.out.println("Test max() --------");
		System.out.println("Max value: " + list.max());
		System.out.println("Test min() --------");
		System.out.println("Min value: " + list.min());

		System.out.println();
	}
}