package org.gen;

public class Operation extends AST {
	
	public Operation(AST left, AST right, EnumTag tag) {
		super(left, right, tag);		
	}

	public Operation(AST left, AST right, EnumTag tag, Type type) {
		super(left, right, tag, type);
	}

	public String toJava() {
		return getLeft().toJava() + " " + getTag() + " " + getRight().toJava();
	}

}