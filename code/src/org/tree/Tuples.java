package org.tree;

import org.gen.*;

public class Tuples extends AST {

	public Tuples(AST left, AST right) {
		super(left, right, EnumTag.TUPLES, new Type(EnumType.MAP));
		//TODO change map type to real type
	}

	public String toJava() {
		return getLeft().toJava() + ", " + getRight().toJava();
	}
  
}