package org.tree;

import org.gen.*;

public class Tuples extends AST {

	public Tuples(AST left, AST right) {
		super(left, right, EnumTag.TUPLES, new Type(EnumType.ENTRIES));
	}

	public String toJava() {
		return getLeft().toJava() + ", " + getRight().toJava();
	}
  
}