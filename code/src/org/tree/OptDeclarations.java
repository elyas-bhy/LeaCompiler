package org.tree;

import org.gen.*;

public class OptDeclarations extends AST {
	
	public OptDeclarations(AST left, AST right) {
		super(left, right, EnumTag.DECLOPT);
	}

	public String toJava() {
		if (getLeft() == null)
			return "";
		return getLeft().toJava() + getRight().toJava() + ";\n";
	}
	
}