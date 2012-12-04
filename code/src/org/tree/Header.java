package org.tree;

import org.gen.*;

public class Header extends AST {

	public Header(AST left, AST right, Type type) {
		super(left, right, EnumTag.HEADER, type);
		Parser.params = right;
	}

	public Header(AST left, AST right) {
		this(left, right, null);
	}

	public String toJava() {
		String returnType = "void";
		if (getType() != null)
			returnType = getType().toString();
		return returnType + " " + getLeft().toJava() + "(" + getRight().toJava() + ") {\n";
	}
	
}