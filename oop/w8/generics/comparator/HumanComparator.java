package w8.generics.comparator;

import java.util.Comparator;

public class HumanComparator implements Comparator<Human>{

	
	@Override
	public int compare(Human o1, Human o2) {
		int value = o1.name.compareTo(o2.name);
		if(value == 0) {
			return o1.age - o2.age;
		}else {
			return value;
		}
	}

	
}
