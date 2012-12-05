package org.tree;

import org.gen.*;

public class Loop extends AST {
	
	public Loop(AST left, AST right, EnumTag tag) {
		super(left, right, tag);
	}

	public String toJava() {
		StringBuffer sb = new StringBuffer();
		sb.append(tab() + getTag() + " (" + getLeft().toJava() + " ) {\n");
		sb.append(getRight().toJava() + "\n");
		sb.append(tab() + "}");
		return sb.toString();
	}
	
}