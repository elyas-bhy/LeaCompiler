package org.tp;

public class Affect extends AST {
	
	public Affect(AST left, AST right) {
		super(left, right, EnumTag.AFF);		
	}

	public String toJava() {
    return tab() + getLeft().toJava() + " = " + getRight().toJava();
	}

}