package org.tree;

import org.gen.*;

public class Instructions extends AST {
	
	public Instructions(AST left, AST right) {
		super(left, right, EnumTag.SUCC);		
	}

	public String toJava() {
		return getLeft().toJava() + "\n" + getRight().toJava();
	}
	
}