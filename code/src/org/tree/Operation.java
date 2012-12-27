package org.tree;

import org.gen.*;

public class Operation extends AST {

	public Operation(AST left, AST right, EnumTag tag, Type type) {
		super(left, right, tag, type);
		if (left != null)	// U_MINUS case
			Verificator.checkInitialized(left);
		Verificator.checkInitialized(right);
	}
	
	public Operation(AST left, AST right, EnumTag tag) {
		this(left, right, tag, null);		
	}

	public String toJava() {
		if (getTag().equals(EnumTag.MINUS_U))
			return priorityWrap(getTag() + getRight().toJava());
		return priorityWrap(getLeft().toJava() + " " + getTag() + " " + getRight().toJava());
	}

}