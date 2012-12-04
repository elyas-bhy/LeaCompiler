package org.tree;

import org.gen.*;

public class Function extends AST {
	
	public Function(AST left, AST right) {
		super(left, right, EnumTag.FUNCTION);
	}

	public Function(AST left){ // prototype
		this(left, null);
		//Prototypes.add();  TODO
	}

	public String toJava() {
		if (getRight() == null)
			return "";	//No code generation for prototypes
		return tab() + "public static " + getLeft().toJava() + getRight().toJava() + "\n" + tab() + "}";
	}
	
}