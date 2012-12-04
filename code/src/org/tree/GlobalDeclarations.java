package org.tree;

import org.gen.*;

public class GlobalDeclarations extends AST {
	
	public GlobalDeclarations(AST left, AST right) {
		super(left, right, EnumTag.GLOBAL_DECS);		
	}

	public String toJava() {
		return getLeft().toJava() + getRight().toJava();
	}
	
}