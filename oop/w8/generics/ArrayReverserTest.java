package w8.generics;

import java.util.Arrays;

public class ArrayReverserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Number[] a = new Number[5];
		a[0] = 1;
		a[1] = 2;
		a[2] = 1;
		a[3] =-1;
		a[4] = 3;
		
		System.out.println(Arrays.toString(a));
		
		ArrayReverser.reverse(a);
		System.out.println(Arrays.toString(a));
		System.out.println();
		
		
		Number[] a1 = new Number[5];
		a1[0] = 1.0;
		a1[1] = 2.0;
		a1[2] = 1.0;
		a1[3] =-1.0;
		a1[4] = 3.0;
		
		System.out.println(Arrays.toString(a1));
		
		ArrayReverser.reverse(a1);
		System.out.println(Arrays.toString(a1));
		System.out.println();
		

	}

}
