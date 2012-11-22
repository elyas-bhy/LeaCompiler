package output;

import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {

	class Foo {
 		Integer a;
		String b;
	}

	class Bar {
 		Integer c;
		Integer d;
	}

	public Integer MAX = new Integer(500);
	public String hello = new String("world");
	public Character b = new Character('c');

	public Integer main(Integer argn, String argv) {
		Integer x = new Integer(null);
		Integer y = new Integer(null);
		Integer z = new Integer(null);

		x = 452;
		y = 1;
		z = x + y;
		if (x >= y) {
			z = z + 1;
		} else {
			z = 2;
		}
		z = x + y;
		for (int j = 0; j < z + 2; j++ ) {
			x = y * 2 + z;
			y = y + 10;
		}
		return z + 4;
	}

}
