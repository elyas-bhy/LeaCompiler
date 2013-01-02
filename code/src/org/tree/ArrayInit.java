package org.tree;

import org.gen.*;

public class ArrayInit extends AST {

	public ArrayInit(Type type, AST left) {
		super(left, null, EnumTag.ARR_INIT, 
			new Type(type, null, left.toJava(), EnumType.ARRAY));
	}

	public String toJava() {
		return "new " + getType();
	}
  
}