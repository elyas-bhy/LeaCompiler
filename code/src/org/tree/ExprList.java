package org.tree;

import org.gen.*;
import java.util.ArrayList;

public class ExprList extends AST {

	public ExprList(AST left, AST right) {
		super(left, right, EnumTag.EXPRLIST);

		Verificator.checkDeclared(left);
		Verificator.checkDeclared(right);
	}

	public ArrayList<Type> getTypesList() {
		ArrayList<Type> alt = new ArrayList<Type>();
		for (AST node : getFields())
			alt.add(Verificator.findType(node));
		return alt;
	}

	public String toJava() {
		if (getLeft() == null && getRight() == null)
			return "";
		return getLeft().toJava() + ", " + getRight().toJava();
	}
	
}