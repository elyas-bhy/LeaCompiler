Creating new environment 	34
Creating new environment 	61
Closing environment
Creating new environment 	69
Closing environment
Creating new environment 	96
Closing environment
Closing environment

function_program(global_decs(global_decs(global_decs(global_decs(global_dec(identifier,Integer),global_dec(identifier,String)),global_dec(identifier,Char)),global_dec(identifier,struct var decls(struct var decl(identifier),struct var decl(identifier)))),global_dec(identifier,struct var decls(struct var decl(identifier),struct var decl(identifier)))),function(header(identifier,params(param(identifier),param(identifier))),block(decl opt(var decl(identifier),decl opt(var decl(identifier),decl opt(var decl(identifier),decl opt))),

	succ(succ(succ(succ(succ(succ(succ(=(identifier,Integer),=(identifier,Integer)),=(identifier,+(identifier,identifier))),if(>=(identifier,identifier),

	thenelse(block(decl opt,=(identifier,+(identifier,Integer))),block(decl opt,=(identifier,Integer))))),=(identifier,+(identifier,identifier))),=(identifier,function call(identifier,identifier))),for(for_i_in_range(identifier,range(Integer,+(identifier,Integer))),block(decl opt,

	succ(=(identifier,*(identifier,+(Integer,identifier))),=(identifier,+(identifier,Integer)))))),return(+(identifier,Integer))))))

-------------------
Generating code:
-------------------

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

	public Integer test(Integer argn, String argv) {
		Integer x = new Integer();
		Integer y = new Integer();
		Integer z = new Integer();

		x = 452;
		y = 1;
		z = x + y;
		if (x >= y) {
			z = z + 1;
		} else {
			z = 2;
		}
		z = x + y;
		z = f(a);
		for (int j = 0; j < z + 2; j++ ) {
			x = y * 2 + z;
			y = y + 10;
		}
		return z + 4;
	}

}
