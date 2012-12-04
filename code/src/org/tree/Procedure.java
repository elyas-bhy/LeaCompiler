package org.tree;

import org.gen.*;

public class Procedure extends AST {
	
	public Procedure(AST left, AST right) {
		super(left, right, EnumTag.PROCEDURE);		
	}

	public String toJava() {
		return tab() + "public void " + getRight().toJava();
	}
	
}