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
		HashMap<Integer,String> m;
		String str;
		Boolean t;
		Integer a;
		Integer b;
		Integer c;
		Integer d;
		Integer r;
		Integer j;
		Foo f;
		Bar bar;

		a = 452;
		b = 1;
		t = true;
		c = "rr" + t;
		bar = new Bar();
		f = new Foo();
		f = new Foo(a, b, bar);
		f.x = -4;
		c = add1(a);
		t = add1(a);
		System.out.println("Finished");
	}
}