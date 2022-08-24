package w11.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Stateful {

	static void statefullLambda() {
		Set<Integer> s = new HashSet<>(
				Arrays.asList(1, 2, 3, 4, 5, 6));
		List<Integer> list = new ArrayList<>();
		int sum = s.parallelStream().mapToInt(e -> {
			if (list.size() <= 3) {
				list.add(e);
				return e;
			}
			else
				return 0;
		}).sum();
		System.out.println(sum);
	}
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++)
			statefullLambda();
	}
}
