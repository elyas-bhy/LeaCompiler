package org.tree;

import org.gen.*;
import java.util.ArrayList;

public class Function extends AST {
	
	public Function(AST left, AST right) {
		super(left, right, EnumTag.FUNCTION);
		Main.prototypes.add(new Prototype(left.getLeft().toJava(), 
										  left.getType(),
										  left.getRight().getTypesList()));
		if (right != null)
			Verificator.checkReturns(this);
	}

	public Function(AST left){ // prototype
		this(left, null);
	}

	public String toJava() {
		if (getRight() == null)
			return "";	//No code generation for prototypes
		return tab() + "public static " + getLeft().toJava() + getRight().toJava() + "\n" + tab() + "}";
	}
	
}