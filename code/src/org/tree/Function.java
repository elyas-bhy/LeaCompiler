package org.tree;

import org.gen.*;
import java.util.ArrayList;

public class Function extends AST {
	
	public Function(AST left, AST right) {
		super(left, right, EnumTag.FUNCTION);
		if (right != null) {
			Env tmp = Main.currentEnv;
			Main.currentEnv = Main.lastEnv;
			Verificator.checkReturns(this);
			Main.currentEnv = tmp;
		}
	}

	public Function(AST left) { // prototype
		this(left, null);
	}

	public Type getType() {
		return getLeft().getType();
	}

	public String toJava() {
		if (getRight() == null)
			return "";	//No code generation for prototypes
		return tab() + "public static " + getLeft().toJava() + getRight().toJava() + "\n" + tab() + "}";
	}
	
}