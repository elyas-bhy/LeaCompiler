package output;

import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {

	static Console mLeaCompilerConsole = System.console();

	static class Foo {
 		Integer x;
		Integer y;
		Bar b;

		public Foo() {
			this.x = null;
			this.y = null;
			this.b = null;
		}

		public Foo(Integer x, Integer y, Bar b) {
			this.x = x;
			this.y = y;
			this.b = b;
		}

	}

	static class Bar {
 		String d;

		public Bar() {
			this.d = null;
		}

		public Bar(String d) {
			this.d = d;
		}

	}

	public static Integer MAX = new Integer(500);
	public static String hello = new String("world");
	public static Character b = new Character('c');

	public static Integer add1(Integer i) {
		return i + 1;
	}

	public static void main(String[] s) {
		Boolean t = new Boolean(null);
		Integer a = new Integer(0);
		Integer b = new Integer(0);
		Integer c = new Integer(0);
		String str = new String();
		Foo f;
		Bar bar;
		HashMap<Integer,String> m = new HashMap<Integer,String>();

		a = 452;
		b = 1;
		c = a + b;
		t = true;
		m.put(a, "alpha");
		m.put(b, "beta");

		bar = new Bar();
		f = new Foo();
		f = new Foo(a, b, bar);
		f.x = -4;
		c = add1(a);
		System.out.print("Hello ");
		System.out.println("world");
		if (mLeaCompilerConsole != null)
			str = mLeaCompilerConsole.readLine();
		System.out.println("Value: " + str);
		if (a >= b) {
			c = c + 1;
			if (c < 10) {
			System.out.print("foo");
			}
		} else {
			c = 2;
		}
		for (int j = 0; j < c + 2; j++ ) {
			a = b * 2 + c;
			b = b + 10;
		}
		do {
			c = c - 1;
		} while (c > 0);
		System.out.println("Finished");
	}

}
