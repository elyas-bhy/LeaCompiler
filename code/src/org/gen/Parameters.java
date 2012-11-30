package org.gen;

public class Parameters extends AST {
	
	public Parameters(AST left, AST right) {
		super(left, right, EnumTag.PARAMS);		
	}

	public String toJava() {
		if (getLeft() == null && getRight() == null)
			return "";
		return getRight().toJava() + ", " + getLeft().toJava();
	}
	
}