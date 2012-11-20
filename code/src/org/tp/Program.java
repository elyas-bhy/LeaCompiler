package org.tp;

public class Program extends AST {
	
	public Program(AST left, AST right) {
		super(left, right, EnumTag.PROGRAM);		
	}

	public String toJava() {
		return getLeft().toJava() + "public class Main {\n\n" + Main.globals + "\n" + getRight().toJava();
	}
	
}