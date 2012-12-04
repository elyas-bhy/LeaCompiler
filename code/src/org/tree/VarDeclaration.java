package org.tree;

import org.gen.*;

public class VarDeclaration extends AST {

	public VarDeclaration(AST left, AST right, Type type) {
		super(left, right, EnumTag.VARDEC, type);
		Main.currentEnv.add(left.getName(), type);
		if (Main.DEBUG) System.out.println("Adding declaration in env " + Main.currentEnv.getNum() + ": " + left.getName() + ", " + type);
	}

	public String toJava() {
		return tab() + getType() + " " + getLeft().toJava();
	}
}