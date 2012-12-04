package org.tree;

import org.gen.*;

public class ForRange extends AST {
	
	public ForRange(AST left, AST right) {
		super(left, right, EnumTag.FOR_RANGE);		
	}

	public String toJava() {
		String tmp = getLeft().toJava();
		return tmp + " = " + getRight().getLeft().toJava() + "; " 
		     + tmp + " < " + getRight().getRight().toJava() + "; " + tmp + "++";
	}
	
}