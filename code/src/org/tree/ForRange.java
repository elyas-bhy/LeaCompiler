package org.tree;

import org.gen.*;

public class ForRange extends AST {
	
	public ForRange(AST left, AST right) {
		super(left, right, EnumTag.FOR_RANGE);
	}

	public String toJava() {
		String id = getLeft().toJava();
		String start, end;
		
		if (getRight().getTag().equals(EnumTag.RANGE)) {
			start = getRight().getLeft().toJava();
			end = getRight().getRight().toJava();
		} else {
			start = "0";
			end = getRight().toJava();
		}
		return id + " = " + start + "; " 
		     + id + " < " + end + "; " + id + "++";
	}
	
}