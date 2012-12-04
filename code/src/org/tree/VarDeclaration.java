package org.tree;

import org.gen.*;

public class VarDeclaration extends AST {

	public VarDeclaration(AST left, AST right, Type type) {
		super(left, right, EnumTag.VARDEC, type);
		Main.currentEnv.add(left.getName(),type);
	}

	public String toJava() {
		if( getRight() != null ) {
			return tab() + "HashMap<" + getRight().toJava() + "> " + getLeft().toJava()
						 + " = new HashMap<" + getRight().toJava() + ">()";
		}
		return tab() + getType() + " " + getLeft().toJava();
	}
}
