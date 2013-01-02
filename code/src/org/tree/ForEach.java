package org.tree;

import org.gen.*;

public class ForEach extends AST {
	
	public ForEach(AST left, AST right, Type type) {
		super(left, right, EnumTag.FOR_EACH, type);
	}

	public String toJava() {
		String tmp = getLeft().toJava();
		return getType() + " " + tmp + " : " + getRight().toJava();
	}
	
}