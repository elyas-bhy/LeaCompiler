package org.tree;

import org.gen.*;

public class Parameters extends AST {
	
	public Parameters(AST left, AST right) {
		super(left, right, EnumTag.PARAMS);		
	}

	public String toJava() {
		if (getLeft() == null && getRight() == null)
			return "";
		return getLeft().toJava() + ", " + getRight().toJava();
	}
	
}