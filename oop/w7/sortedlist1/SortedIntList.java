package w7.sortedlist1;

import java.util.Arrays;
import java.util.NoSuchElementException;

import w7.stutterlist.ArrayIntList;


public class SortedIntList extends ArrayIntList{
	
	boolean uniqueness;
	
	/**
	 * 생성자 
	 * 원소중복금지 (uniqueness)가 false로 설정된 기본 용량을 갖는 리스트를 구성한다.
	 */
	public SortedIntList() {
		this(false, DEFAULT_CAPACITY);
	}
	
	/**
	 * 생성자 
	 * 원소중복금지 (uniqueness)가 false로 설정된 주어 용량을 갖는 리스트를 구성한다.
	 */
	public SortedIntList(int capacity) {
		this(false, capacity);
	}
	
	/**
	 * 생성자 
	 * 주어진 값에 따라 원소중복금지 (uniqueness) 여부가 정해지는 기본 용량을 갖는 리스트를 구성한다.
	 */
	public SortedIntList(boolean unique){
		this(unique, DEFAULT_CAPACITY);
	}
	
	/**
	 * 생성자 
	 * 주어진 값에 따라 원소중복금지 (uniqueness) 여부가 정해지고 주어진 용량을 갖는 리스트를 구성한다.
	 */
	public SortedIntList(boolean unique, int capacity){
		super(capacity);
		
		if(capacity < 0) {
			throw new IllegalArgumentException("capacity fector cannot be negatice: "+ capacity);
		}
		
		this.uniqueness = unique;
	}
	
	/**
	 * 오름차순이 유지되도록 value를 배열에 끼워넣는다
	 * @param value
	 */
	public void add(int value) {
//		if(uniqueness) {
//			
//		}else {
//			
//		}
		int index = indexOf(value);
		
		if(index < 0) {
			index = (index + 1)* -1; 
		}
		
		super.add(index, value);
		
		
	}
	
	public void add(int index, int value) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 주어진 값이 리스트에서 몇번 방에 있는지 알아본다.
	 */
	public int indexOf(int value) {
		int index = Arrays.binarySearch(element, 0, size, value);
		return index;
	}
	
	
	/**
	 * 이 리스트에서 가장 큰 원소를 반환한다. 
	 * @return 가장 큰 원소
	 */
	public int max() {
		if (size== 0) {
			throw new NoSuchElementException();
			
		}
		else {
			return element[size-1];	
		}
		
	}
	
	
	/**
	 * 이 리스트에서 가장 작은 원소를 반환한다.
	 * @return 가장 작은 원소 
	 */
	public int min() {
		if (size== 0) {
			throw new NoSuchElementException();
		}
		else {
			return element[0];	
		}
		
	}
	/**
	 * 원소중복금지 상태이면 true를, 그렇지 않으면 false를 반환
	 * @return 원소중복금지 상태 
	 */
	public boolean getUnique() {
		return uniqueness;
	}
	
	public void setUnique(boolean value) {
		this.uniqueness = value;
		
		//미완성
	}
	
	/**
	 * 리스트를 아래 형식의 문자열로 반환한다.
	 * "S:[-3, 7, 20]U " or "S:[-3, 7, 20]U"
	 */
	public String toString() {
		if (size > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("S:[");
			for (int i = 0; i < size - 1; i++)
				sb.append(element[i] + ", ");
			sb.append(element[size - 1] + "");
			sb.append("]");
			if(uniqueness) {
				sb.append("U");
			}
			return sb.toString();
		}
		else {
			if(uniqueness) {
				return "S:[]U";
			}else {
				return "S:[]";
			}
			
		}
	}
}
