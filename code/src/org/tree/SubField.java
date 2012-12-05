package org.tree;

import org.gen.*;

public class SubField extends AST {
	
	public SubField(AST left, AST right) {
		super(left, right, EnumTag.SUBFIELD);		
	}

	public String toJava() {
		return getRight().toJava() + "." +  getLeft().toJava();
	}
	
}