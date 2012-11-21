package org.tp;

public class FunctionCall extends AST {
	
	public FunctionCall(AST left, AST right) {
		super(left, right, EnumTag.FUNCTION_CALL);		
	}

	public String toJava() {
		return getLeft().toJava() + "(" + getRight().toJava() + ")";
	}
	
}