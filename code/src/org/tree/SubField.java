package org.tree;

import org.gen.*;

public class SubField extends AST {
	
	public SubField(AST left, AST right) {
		super(left, right, EnumTag.SUBFIELD);
		Verificator.checkInitialized(left);
		Verificator.checkSubfield(this);
	}

	public String getName() {
		return toJava();
	}

	public String toJava() {
		return getLeft().toJava() + "." +  getRight().toJava();
	}
	
}