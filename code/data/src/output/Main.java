package output;

import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {

	static Console mLeaCompilerConsole = System.console();

	class Foo {
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

	class Bar {
 		String d;

		public Bar() {
			this.d = null;
		}

		public Bar(String d) {
			this.d = d;
		}

	}

	public Integer MAX = new Integer(500);
	public String hello = new String("world");
	public Character b = new Character('c');

	public Integer test() {
		Boolean t = new Boolean(null);
		Integer a = new Integer(null);
		Integer b = new Integer(null);
		Integer c = new Integer(null);
		String s = new String(null);
		Foo f;
		Bar bar;

		a = 452;
		b = 1;
		c = a + b;
		t = true;
		f = new Foo();
		f = new Foo(a, b, bar);
		f.a = 4;
		System.out.print("Hello ");
		System.out.println("world");
		if (mLeaCompilerConsole != null)
			s = mLeaCompilerConsole.readLine();
		System.out.println("Value: " + s);
		if (a >= b) {
			c = c + 1;
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
		return c + 4;
	}

}
