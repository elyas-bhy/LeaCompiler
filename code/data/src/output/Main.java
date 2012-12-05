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
		System.out.println("h");
	}

	public static void main(String[] s) {
		HashMap<Integer,String> m;
		String str;
		String str2;
		Boolean t;
		Integer a;
		Integer b;
		Integer c;
		Integer d;
		Integer r;
		Integer j;
		Foo f;
		Foo f2;
		Bar bar;

		a = 452;
		b = 1;
		t = true;
		str = "rr";
		str2 = str + "tt";
		f2 = f;
		System.out.println("Finished");
	}
}