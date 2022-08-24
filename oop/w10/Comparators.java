package w10;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.function.IntBinaryOperator;

import org.junit.jupiter.api.Test;

public class Comparators {

    final Person 승기 = new Person("승기", "이", 33);
    final Person 소미 = new Person("소미", "전", 20);
    final Person 재인 = new Person("재인", "문", 67);
    final Person 효리 = new Person("효리", "이", 41);
    final Person 혜교 = new Person("혜교", "송", 38);

    /**
     * Write a Comparator that compare instances of String using their length.
     * For instance FOUR (4 letters) is greater than TWO (three letters)
     * 
     * 길이를 기준으로 String을 비교하는 Comparator를 작성하시오.
     */
    @Test 
    public void comparator01() {
        Comparator<String> compareByLength = Comparator.comparing(String::length); 

        assertTrue(compareByLength.compare("FOUR", "TWO") > 0);
        assertTrue(compareByLength.compare("ONE", "SEVEN") < 0);
        assertTrue(compareByLength.compare("ONE", "TWO") == 0);
    }
    // Hint:
    // Comparator interface의 static 메소드인 comparing을 사용하면 쉽다.
    // comparing 메소드의 인자로는 String의 길이를 얻는 데 사용하는 인스턴스 메소드를 가리키는 
    // 메소드 레퍼런스를 사용한다. String::length

    /**
     * Write a Comparator that compare instances of String using their length.
     * If the lengths are the same, then use the alphabetical order.
     * 
     * 길이를 기준으로 String을 비교하는 Comparator를 작성하시오.
     * 길이가 같으면 사전 순서를 사용하게 하시오.
     */
    @Test 
    public void comparator02() {
        Comparator<String> compareByLengthThenAlphabetical = Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder());

        assertTrue(compareByLengthThenAlphabetical.compare("FOUR", "TWO") > 0);
        assertTrue(compareByLengthThenAlphabetical.compare("ONE", "SEVEN") < 0);
        assertTrue(compareByLengthThenAlphabetical.compare("ONE", "TWO") < 0);
        assertTrue(compareByLengthThenAlphabetical.compare("FOUR", "FIVE") > 0);
        assertTrue(compareByLengthThenAlphabetical.compare("EIGHT", "EIGHT") == 0);
    }
    // Hint:
    // comparing과 thenComparing을 연이어 적는다(chaining).
    // comparing의 인자로는 String의 길이를 얻는 메소드를 메소드 레퍼런스 형태로 준다. String::length
    // thenComparing의 인자로는 객체를 natural order에 따라 비교하는 Comparator를 만들어주는
    // Comparator의 static 메소드를 호출하는 문장을 넣어준다. Compator.naturalOrder()


    /**
     * Write a Comparator that compares instances of Person using their lastName.
     * 
     * Last name을 기준으로 Person을 비교하는 Comparator를 작성하시오. 
     */
    @Test 
    public void comparator03() {
        Comparator<Person> comparebyLastName = Comparator.comparing(Person::getLastName); 

        assertTrue(comparebyLastName.compare(혜교, 소미) < 0);
        assertTrue(comparebyLastName.compare(재인, 재인) == 0);
        assertTrue(comparebyLastName.compare(효리, 승기) == 0);
    }
    // Hint:
    // Comparator interface의 static 메소드인 comparing을 사용하면 쉽다.
    // comparing 메소드의 인자로는 Person의 lastName을 얻는 데 사용하는 인스턴스 메소드를 가리키는 
    // 메소드 레퍼런스를 사용한다. Person::getLastName


    /**
     * Write a Comparator that compares instances of Person using their
     * lastName, and if their last name is the same, uses their first name.
     * 
     * Last name을 기준으로 Person을 비교하는 Comparator를 작성하시오.
     * Last Name이 같은 경우에는 first name을 기준으로 삼도록 하시오.
     */
    @Test 
    public void comparator04() {
        Comparator<Person> comparebyLastNameThenFirstName =Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName); 


        assertTrue(comparebyLastNameThenFirstName.compare(혜교, 소미) < 0);
        assertTrue(comparebyLastNameThenFirstName.compare(재인, 재인) == 0);
        assertTrue(comparebyLastNameThenFirstName.compare(효리, 승기) > 0);
    }
    // Hint:
    // comparing과 thenComparing을 연이어 적는다(chaining).
    // comparing의 인자로는 Person의 lastName을 얻는 메소드를 메소드 레퍼런스 형태로 준다. Person::getLastName
    // thenComparing의 인자로는 Person의 firstName을 얻는 메소드를 메소드 레퍼런스 형태로 준다. Person::getFirstName


    /**
     * Write a Comparator that compares the people in the order reversed from
     * the one you wrote in the comparator04() exercise. That is, the person
     * with the greater last name should be ordered first. If two persons have
     * the same last name, the one with the greater first name should be
     * ordered first.
     * 
     * 위 comparator04() 문제와 반대의 순서로 비교하는 Comparator를 작성하시오. 
     */
    @Test 
    public void comparator05() {
        Comparator<Person> comparebyLastNameThenFirstNameReversed = Comparator.comparing(Person::getLastName).reversed();


        assertFalse(comparebyLastNameThenFirstNameReversed.compare(혜교, 소미) < 0);
        assertTrue(comparebyLastNameThenFirstNameReversed.compare(재인, 재인) == 0);
        assertFalse(comparebyLastNameThenFirstNameReversed.compare(효리, 승기) > 0);
    }
    // Hint:
    // comparing과 thenComparing, 그리고 
    // 주어진 Comparator의 비교방식을 뒤집어 주는 Comparator의 메소드를 연이어 적는다(chaining).
    // 주어진 Comparator의 비교방식을 뒤집어 주는 Comparator의 메소드는 reversed()이다. 
   	// reversed()는 thenComparing처럼 Comparator 인스턴스 하나를 콕 집어 호출하는 인스턴스 메소드이다.


    /**
     * Write a Comparator that compares the people in the same order than the
     * one you wrote in comparator04(), but that supports null values. The null
     * values should be considered greater than any non-null values.
     * 
     * 위 comparator04() 문제와 같은 순서로 비교하는 Comparator를 작성하시오.
     * 단, 이 Comparator는 null을 다루도록 하시오. null은 null이 아닌 것보다 크다고 판단하게 하시오.
     */
    @Test 
    public void comparator06() {
        Comparator<Person> comparebyLastNameThenFirstNameWithNull = Comparator.nullsLast(Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName));

        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(혜교, 소미) < 0);
        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(재인, 재인) == 0);
        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(효리, 승기) > 0);
        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(재인, null) < 0);
        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(null, 재인) > 0);
    }
    // Hint:
    // Comparator의 static 메소드인 nullsLast 메소드를 사용한다.
    // 이 메소드는 인자로 주어진 Comparator로부터 새 Comparator를 만들어 반환한다.
   	// 새 Comparator는 인자로 주어진 원래의 Comparator의 규칙을 유지하되 
    // 원소가 null이면 가장 큰 원소로 간주하도록 하는Comparator이다.


    /**
     * Write a Comparator that compares two people by age.
     * Try to write the comparator so as to avoid boxing of primitives.
     * 
     * 나이를 기준으로 Person을 비교하는 Comparator를 작성하시오.
     * 기본 타입 값에 대한 boxing(포장)이 일어나지 않도록 하시오.
     */
    @Test 
    public void comparator07() {
        Comparator<Person> comparebyAge = Comparator.comparingInt(Person::getAge);

        assertTrue(comparebyAge.compare(소미, 혜교) < 0);
        assertTrue(comparebyAge.compare(재인, 재인) == 0);
        assertTrue(comparebyAge.compare(효리, 승기) > 0);
    }
    // Hint:
    // 나이는 int 타입이므로 int 타입 값을 기준으로 객체를 비교하는 Comparator를 만든다.
    // int 타입 값을 기준으로 객체를 비교하는 Comparator를 만들어주는 
    // Comparator의 static 메소드는 comparingInt이다.
    // comparingInt의 인자로는 
    // Person으로부터 나이(int 타입)를 얻는 메소드 레퍼런스를 준다. Person::getAge


    /**
     * Write a lambda expression that compares two int values and returns an
     * int result that is less than, equal to, or greater than zero, like
     * a comparator. Watch out for overflow. The Comparator interface takes
     * two objects, but in this case we are comparing int primitives, so the
     * functional interface we use is IntBinaryOperator.
     * 
     * 두 개의 int 값을 비교하여 음수, 0, 혹은 양수를 int 타입으로 반환하는 람다식을 작성하시오.
     * 기본 타입을 비교하는 것이므로 BinarayOperator보다는 IntBinaryOperator를 사용하는 편이 좋습니다.
     */
    @Test 
    public void comparator08() {
        IntBinaryOperator intCompare = (a, b) -> Integer.compare(a, b); 

        assertTrue(intCompare.applyAsInt(0, 1) < 0);
        assertTrue(intCompare.applyAsInt(1, 1) == 0);
        assertTrue(intCompare.applyAsInt(2, 1) > 0);
        assertTrue(intCompare.applyAsInt(Integer.MIN_VALUE, Integer.MAX_VALUE) < 0);
        assertTrue(intCompare.applyAsInt(Integer.MAX_VALUE, Integer.MIN_VALUE) > 0);
    }
    // Hint:
    // 여러 줄로 이루어진 {코드 블럭}를 적어도 됩니다.
    // 삼항연산자를 사용하면 코드 블럭 대신 한 줄로 적을 수 있습니다.
    // 삼항연산자란 피연산자 세 개를 갖는 (boolean expression) ? b : c 형식의 연산자를 말합니다.
    // boolean expression이 true일 때는 b, false일 때는 c가 됩니다.
    // larger = a > b ? a : b
    // max = (n1 > n2) ? (n1 > n3 ? n1 : n3) : (n2 > n3 ? n2 : n3); 
    //
    // IntBinaryOperator 인터페이스의 메소드(apply)는 int 파라미터를 두 개 갖고 int 값을 반환합니다.
    // 그러니까 람다식도 int 파라미터를 두 개 가져야 합니다.
    // 변수 intCompare가 IntBinaryOperator로 선언되어 있으므로 람다식의 파라미터가 
    // int 타입이라고 명시적으로 선언하지 않아도 됩니다.


    /**
     * Write a method reference that compares two int values and returns an
     * int result that is less than, equal to, or greater than zero, like
     * a comparator.
     * 
     * 두 개의 int 값을 비교하여 음수, 0, 혹은 양수를 int 타입으로 반환하는 method reference를 적으시오.
     */
    @Test 
    public void comparator09() {
        IntBinaryOperator intCompare = Integer::compare; // TODO

        assertTrue(intCompare.applyAsInt(0, 1) < 0);
        assertTrue(intCompare.applyAsInt(1, 1) == 0);
        assertTrue(intCompare.applyAsInt(2, 1) > 0);
        assertTrue(intCompare.applyAsInt(Integer.MIN_VALUE, Integer.MAX_VALUE) < 0);
        assertTrue(intCompare.applyAsInt(Integer.MAX_VALUE, Integer.MIN_VALUE) > 0);
    }
    // Hint:
    // Integer는 static method인 compare(int a, int b) 메소드를 갖고 있습니다.
    // (API 문서에서 Integer를 찾아보면 알 수 있습니다! 맨 윗 부분에 적혀 있습니다. 찾아 보세요.)
    // 이 메소드를 가리키는 메소드 레퍼런스를 적으면 됩니다.
    //


    /**
     * Write a method reference that compares two double values and returns an
     * int result that is less than, equal to, or greater than zero, like
     * a comparator. There functional interface that takes two doubles and returns
     * an int, so we define one here. Comparing double values introduces
     * special cases such NaN. Consider all NaN values to be equal to each other
     * and greater than any non-NaN value.
     * 
     * 두 개의 double 값을 비교하여 음수, 0, 혹은 양수를 int 타입으로 반환하는 method reference를 적으시오.
     * 두 개의 double 타입 값을 받아 int 타입을 반환하는 functional interface는 표준API에 없으므로
     * 아래에 그런 인터페이스를 정의했습니다.
     * double 값을 다룰 때 NaN을 적절히 처리해야 합니다. 
     * NaN은 NaN과 같고 NaN은 어떤 non-NaN보다 큰 것으로 취급합니다.
     */
    
    interface DoubleToIntBiFunction {
        int applyAsInt(double a, double b);
    }

    @Test 
    public void comparator10() {
        DoubleToIntBiFunction doubleCompare = Double::compare; // TODO

        assertTrue(doubleCompare.applyAsInt(0.0, 1.0) < 0);
        assertTrue(doubleCompare.applyAsInt(1.0, 1.0) == 0);
        assertTrue(doubleCompare.applyAsInt(2.0, 1.0) > 0);
        assertTrue(doubleCompare.applyAsInt(Double.NaN, Double.NaN) == 0);
        assertTrue(doubleCompare.applyAsInt(Double.NaN, 0.0) > 0);
        assertTrue(doubleCompare.applyAsInt(0.0, Double.NaN) < 0);
    }
    // Hint:
    // Double의 compare 메소드가 문제에서 요구한 기능을 모두 갖추고 있습니다.

}	