package w7.dog2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 문제로 제시되는 초기 코드

public class DogTest {
	public static void main(String[] args) {
		
		Set<Jindo> dogs = new HashSet<>();
		dogs.add(new Jindo("Jindori", "black"));
		dogs.add(new Jindo("Baku", "white"));
		dogs.add(new Jindo("Smart", "white"));
		dogs.add(new Jindo("Smart", "white"));
		System.out.println(dogs.size());
	}
}