package w8.generics.comparator;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student>{

	//StudentComparator를 이용하여 학생을 사람으로서 먼저 비교한다. 
	//즉, 이름과 나이을 차례로 비교한다.
	//만약, 이름과 나이가 같으면 학번을 비교한다. 
	@Override
	public int compare(Student o1, Student o2) {
		int value = o1.name.compareTo(o2.name);
		if(value == 0) {
			if(o1.age == o2.age) {
				if(o1.studentId == o2.studentId) {
					return o1.studentId - o2.studentId;
				}else {
					return value;
				}
			}else {
				return o1.age - o2.age;
			}
		}else {
			return value;
		}
	}
	

	

}
