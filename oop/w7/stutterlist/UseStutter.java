package w7.stutterlist;

public class UseStutter {
	public static void main(String[] args) {
		
		// 원소를 add하면 그 원소가 세 개 add되는 StutterIntList를 생성한다.
		// 반복도가 3인 StutterIntList!
		StutterIntList list = new StutterIntList(3);

		list.add(7);     
		System.out.println(list); // [7, 7, 7]
		
		list.add(-1);    // 맨 뒤에 추가한다.
		System.out.println(list);	// [7, 7, 7, -1, -1, -1]
		
		list.add(2, 5);  // 2번 방에 5를 삽입하되 세 개 삽입한다.
		System.out.println(list);	// [7, 7, 5, 5, 5, 7, -1, -1, -1]

		list.add(3);  
		System.out.println(list);	// [7, 7, 5, 5, 5, 7, -1, -1, -1, 3, 3, 3]

		list.remove(4);  // remove할 때는 한 개만 remove한다. (4번 방에 있는 것!)
		System.out.println(list);	// [7, 7, 5, 5, 7, -1, -1, -1, 3, 3, 3]

		// 이 StutterIntList의 반복도 (stretch factor) --> 3
		System.out.println("Stretch factor = " + list.getStretch());   
		System.out.println();

		// 이 StutterIntList에 5가 몇 개나 들어 있나?
		System.out.println(list.count(5) + " 5's");   
		System.out.println();
		
		list.add(5);  
		System.out.println(list.count(5) + " 5's");   
		
		// 값들이 각각 몇 개 씩이나 들어 있나?
		System.out.println(list.counts());

		list = new StutterIntList(0);	// 반복도가 0이므로 
		list.add(7);     				// 7이 0번 add된다!
		System.out.println(list);	// []
		System.out.println();
		
		// 음수 반복도는 허용되지 않는다.
		list = new StutterIntList(-1);	// Exception 발생!
		
	}
}
