package w7.stutterlist;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StutterIntList extends ArrayIntList{
	
	private int stretch; //반복도 
	
	/**
	 * 생성자
	 * 주어진 stretch factor를 갖는 기본 용량의 리스트를 구성한다. 
	 * @param stretch 반복도. {@literal stretch >= 0}
	 * @param capacity {@literal capacity >= 0}
	 */
	public StutterIntList(int stretch, int capacity) {
		super(capacity);
		if(stretch < 0) {
			throw new IllegalArgumentException("Strectch fector cannot be negatice: "+ stretch);
		}
		this.stretch = stretch;
	}
	
	/**
	 * 생성자
	 * 주어진 stretch factor를 갖는 기본 용량의 리스트를 구성한다. 
	 * @param stretch 반복도. {@literal stretch >= 0}
	 */
	public StutterIntList(int stretch) {
		this(stretch, DEFAULT_CAPACITY);
	}
	
	/**
	 * 주어진 값을 주어진 위치에 추가하되 stretch factor만큼 추가한다.
	 */
	public void add(int index, int value) {
		for(int i = 1; i <= stretch; i++) {
			super.add(index, value);
		}
		
	}
	/**
	 * 이 StutterIntList의 stretch factor를 반환한다. 
	 * @return stretch factor
	 */
	public int getStretch() {
		return stretch;
	}
	
	public int count(int value) {
		int number = 0;
		for(int i = 0; i <size; i++) {
			if(element[i] == value) {
				number++;
			}
		}
		return number;
		
	}
	
	/**
	 * 이 리스트에 들어있는 각 값이 각각 몇 개씩이나 들어있는지 알려준다
	 * @param value 주어진 값
	 * @return 리스트에 value가 몇개나 들어있나? 
	 */
	public Map<Integer, Integer> counts () {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Set<Integer> set = new HashSet<Integer>();

		
		for(int i = 0; i < size; i++) {
			set.add(element[i]);
		}
		
		for(Integer i : set) {
			map.put(i, count(i));
		}
			
		return map;
		
	}
	
	

}
