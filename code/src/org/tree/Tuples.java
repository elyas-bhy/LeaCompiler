package org.tree;

import org.gen.*;

public class Tuples extends AST {

	public Tuples(AST left, AST right) {
		super(left, right, EnumTag.TUPLES);
		//TODO
		//check type uniformity of all tuples
		//then assign type
	}

	public String toJava() {
		return getLeft().toJava() + ", " + getRight().toJava();
	}
  
}