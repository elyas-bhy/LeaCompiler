package org.tree;

import org.gen.*;

public class Return extends AST {
	
	public Return(AST left, AST right) {
		super(left, right, EnumTag.RETURN);		
	}

	public String toJava() {
		String left = getLeft() != null ? getLeft().toJava() : "";
		return tab() + getTag() + " " + left + ";";
	}

}