package org.tree;

import org.gen.*;

public class Tuple extends AST {

	public Tuple(AST left, AST right) {
		super(left, right, EnumTag.TUPLE);
	}

	public String toJava() {
		return getLeft().toJava() + ", " + getRight().toJava();
	}
  
}