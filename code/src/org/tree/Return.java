package org.tree;

import org.gen.*;

public class Return extends AST {
	
	public Return(AST left, AST right) {
		super(left, right, EnumTag.RETURN);		
	}

	public String toJava() {
		return tab() + getTag() + " " + getLeft().toJava() + ";";
	}

}