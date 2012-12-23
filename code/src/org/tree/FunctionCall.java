package org.tree;

import org.gen.*;
import java.util.ArrayList;
import java.util.Collections;

public class FunctionCall extends AST {
	
	public FunctionCall(AST left, AST right) {
		super(left, right, EnumTag.FUNCTION_CALL);	
		Verificator.checkDeclared(right);	
	}

	public ArrayList<Type> getTypesList() {
		ArrayList<Type> alt = new ArrayList<Type>();
		AST right = getRight();

		if (right.getTag().equals(EnumTag.EXPRLIST))
			return right.getTypesList();
		else
			alt.add(Verificator.findType(right));

		Collections.reverse(alt);
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