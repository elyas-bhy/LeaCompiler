package org.gen;

public class FunctionCall extends AST {
	
	public FunctionCall(AST left, AST right) {
		super(left, right, EnumTag.FUNCTION_CALL);		
	}

	public String toJava() {
		for (AST a : Main.structs) {
			if (a.getLeft().getName().equals(this.getLeft().getName()))
				return "new " + getLeft().toJava() + "(" + getRight().toJava() + ")";
		}
		return getLeft().toJava() + "(" + getRight().toJava() + ")";
	}
	
}