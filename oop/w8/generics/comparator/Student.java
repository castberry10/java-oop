package w8.generics.comparator;

public class Student extends Human {

    int studentId;

    public Student(String name, int age, int id) {
       super(name, age);
       studentId = id;
    }

    @Override
    public String toString() {
        return name + ":" + age + ":" + studentId;
    }
    
    public static void main(String[] args) {
    	System.out.println(new Student("Hong Gildong", 18, 20180003));
    }

}

