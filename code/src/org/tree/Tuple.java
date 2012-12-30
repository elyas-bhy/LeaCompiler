package org.tree;

import org.gen.*;

public class Tuple extends AST {

	public Tuple(AST left, AST right) {
		super(left, right, EnumTag.TUPLE, 
			new Type(Verificator.findType(left), 
					 Verificator.findType(right), 
					 EnumType.ENTRY));

		Verificator.checkDeclared(left);
		Verificator.checkDeclared(right);
		Verificator.checkInitialized(left);
		Verificator.checkInitialized(right);
	}

	public String toJava() {
		return getLeft().toJava() + ", " + getRight().toJava();
	}
  
}