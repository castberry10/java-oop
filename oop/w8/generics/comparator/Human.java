package w8.generics.comparator;

public class Human {

    String name;
    int age;

    public Human(String name, int age) {
       this.name = name;
       this.age = age;
    }

    @Override
    public String toString() {
        return name + ":" + age;
    }
    
    public static void main(String[] args) {
    	System.out.println(new Human("Hong Gildong", 18));
    }

}
