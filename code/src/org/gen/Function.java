package org.gen;

public class Function extends AST {
	
	public Function(AST left, AST right) {
		super(left, right, EnumTag.FUNCTION);		
	}

	public String toJava() {
    return tab() + "public " + getLeft().toJava() + getRight().toJava() + "\n" + tab() + "}";
	}
	
}