package org.tree;

import org.gen.*;

public class Tuples extends AST {

	public Tuples(AST left, AST right) {
		super(left, right, EnumTag.TUPLES);
		Verificator.checkIdenticalEntryTypes(this);
	}

	public String toJava() {
		return getLeft().toJava() + ", " + getRight().toJava();
	}
  
}