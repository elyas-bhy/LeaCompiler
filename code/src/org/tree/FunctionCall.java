package org.tree;

import org.gen.*;
import java.util.ArrayList;

public class FunctionCall extends AST {

	public FunctionCall(AST left, AST right) {
		super(left, right, EnumTag.FUNCTION_CALL);
		Verificator.checkDeclared(right);
		Verificator.checkInitialized(right);
	}

	public ArrayList<Type> getTypesList() {
		ArrayList<Type> alt = new ArrayList<Type>();
		AST right = getRight();

		if (right.getTag().equals(EnumTag.EXPRLIST))
			return right.getTypesList();

		alt.add(Verificator.findType(right));
		return alt;
	}

	public String toJava() {
		for (AST a : Main.structs) {
			if (a.getLeft().getName().equals(this.getLeft().getName()))
				return "new " + getLeft().toJava() + "(" + getRight().toJava() + ")";
		}
		return getLeft().toJava() + "(" + getRight().toJava() + ")";
	}
	
}