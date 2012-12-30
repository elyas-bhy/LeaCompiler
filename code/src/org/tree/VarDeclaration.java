package org.tree;

import org.gen.*;

public class VarDeclaration extends AST {

	public VarDeclaration(AST left, AST right, Type type) {
		super(left, right, EnumTag.VARDEC, type);
		Main.currentEnv.add(left.getName(), type);

		//implicit initialization of hashmap
		if (type.getEnumType().equals(EnumType.MAP))
			Main.currentEnv.set(left.getName(), "");

		AST structFields = Main.structs.get(type.toString());
		if (structFields != null)
			for (AST node : structFields.getFields())
				Main.currentEnv.add(left.getName() + "." + node.getName(),
								    node.getType());
	}

	public String toJava() {
		if (getType().getEnumType().equals(EnumType.MAP)) {
			return tab() + getType() + " " + getLeft().toJava()
						 + " = new " + getType() + "()";
		}
		return tab() + getType() + " " + getLeft().toJava();
	}
}
