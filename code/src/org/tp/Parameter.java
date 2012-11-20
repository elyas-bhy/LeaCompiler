package org.tp;

public class Parameter extends AST {
	
	public Parameter(AST left, AST right, Type type) {
		super(left, right, EnumTag.PARAM, type);		
	}

	public String toJava() {
    return getType() + " " + getLeft().toJava();
	}
	
}