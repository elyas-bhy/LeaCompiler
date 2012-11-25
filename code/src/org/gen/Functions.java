package org.gen;

public class Functions extends AST {
	
	public Functions(AST left, AST right) {
		super(left, right, EnumTag.FUNCTIONS);		
	}

	public String toJava() {
    return getRight().toJava() + "\n\n" + getLeft().toJava();
	}
	
}