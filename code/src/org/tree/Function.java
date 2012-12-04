package org.tree;

import org.gen.*;

public class Function extends AST {
	
	public Function(AST left, AST right) {
		super(left, right, EnumTag.FUNCTION);
	}

	public Function(AST left){ // prototype
		this(left, null);
	}

	public String toJava() {
		return tab() + "public static " + getLeft().toJava() + getRight().toJava() + "\n" + tab() + "}";
	}
	
}