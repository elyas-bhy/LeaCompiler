package output;

import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {

	class Foo {
 		Integer a;
		String b;
		String c;

		public Foo() {
			this.a = null;
			this.b = null;
			this.c = null;
		}

		public Foo(Integer a, String b, String c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

	}

	class Bar {
 		Integer d;

		public Bar() {
			this.d = null;
		}

		public Bar(Integer d) {
			this.d = d;
		}

	}

	public Integer MAX = new Integer(500);
	public String hello = new String("world");
	public Character b = new Character('c');

	public Integer main(Integer argn, String argv) {
		Integer x = new Integer(null);
		Integer y = new Integer(null);
		Integer z = new Integer(null);
		 f = new (null);

		x = 452;
		y = 1;
		z = x + y;
		if (x >= y) {
			z = z + 1;
		} else {
			z = 2;
		}
		z = x + y;
		f = new Foo();
		for (int j = 0; j < z + 2; j++ ) {
			x = y * 2 + z;
			y = y + 10;
		}
		return z + 4;
	}

}
