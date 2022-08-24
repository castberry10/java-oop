package w7.dog;

//package w7.dog;

import java.util.ArrayList;
import java.util.List;

// 문제로 제시되는 초기 코드

public class DogTest {
	public static void main(String[] args) {
		
		Dog dog ;
//		System.out.println(dog);
//		System.out.println(dog.getName() + " " + dog.speak());
//		
		dog = new Jindo("Jindol", "black");
		System.out.println(dog);
		System.out.println(dog.getName() + " " + dog.speak());
		Jindo jin = (Jindo) dog;
		System.out.println("Average weight of Jindos = " + jin.getAverageWeight());
		

		dog = new Yorki("Yomi");
		System.out.println(dog);
		System.out.println(dog.getName() + " " + dog.speak());
		
		System.out.println("Average weight of Jindos = " + dog.getAverageWeight());
		
		System.out.println();
		
		List<Dog> dogs = new ArrayList<>();
		dogs.add(new Jindo("Jindol", "black"));
		dogs.add(new Jindo("Baku", "white"));
		dogs.add(new Yorki("Yomi"));
		dogs.add(new Yorki("Yepi"));
		
		for(Dog d : dogs) {
			System.out.println(d.toString() + " " + d.speak());
		}
	
		
	}
}
//public class DogTest {
//	public static void main(String[] args) {
//		
//		
//		
//		dog = new Jindo("Jindol", "black");
//		System.out.println(dog);
//		System.out.println(dog.getName() + " " + dog.speak());
//
//		dog = new Yorki("Yomi");
//		System.out.println(dog);
//		System.out.println(dog.getName() + " " + dog.speak());
//		
//	}
//}
