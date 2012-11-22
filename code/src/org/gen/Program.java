package org.gen;;

import org.gen.*;

public class Program extends AST {
	
	public Program(AST left, AST right) {
		super(left, right, EnumTag.PROGRAM);		
	}

	public String toJava() {
		return "public class Main {\n\n" + getLeft().toJava() + Main.globals + "\n" + getRight().toJava();
	}
	
}