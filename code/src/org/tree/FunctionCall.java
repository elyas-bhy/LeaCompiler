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
		Type t;

		if (right.getTag().equals(EnumTag.EXPRLIST)) {

			if (right.getLeft() == null && right.getRight() == null)
				return alt;	//no arguments

			AST tmp = right;
			while (tmp.getLeft().getTag().equals(EnumTag.EXPRLIST)) {
				alt.add(Verificator.findType(tmp.getRight()));
				tmp = tmp.getLeft();
			}

			alt.add(Verificator.findType(tmp.getRight()));
			alt.add(Verificator.findType(tmp.getLeft()));
		}
		else {
			alt.add(Verificator.findType(getRight()));
		}
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