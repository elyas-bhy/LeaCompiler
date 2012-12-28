package org.tree;

import org.gen.*;

public class VarDeclaration extends AST {

	public VarDeclaration(AST left, AST right, Type type) {
		super(left, right, EnumTag.VARDEC, type);
		Main.currentEnv.add(left.getName(), type);
	}

	public String toJava() {
		if (getType().getEnumType().equals(EnumType.MAP)) {
			return tab() + getType() + " " + getLeft().toJava()
						 + " = new " + getType() + "()";
		}
		return tab() + getType() + " " + getLeft().toJava();
	}
}
