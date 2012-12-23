package org.tree;

import org.gen.*;
import java.util.ArrayList;
import java.util.Collections;

public class ExprList extends AST {

	public ExprList(AST left, AST right) {
		super(left, right, EnumTag.EXPRLIST);

		Verificator.checkDeclared(left);
		Verificator.checkDeclared(right);
	}

	public ArrayList<Type> getTypesList() {
		ArrayList<Type> alt = new ArrayList<Type>();
		if (getLeft() == null && getRight() == null)
			return alt;	//no arguments

		AST tmp = this;
		while (tmp.getLeft().getTag().equals(EnumTag.EXPRLIST)) {
			alt.add(Verificator.findType(tmp.getRight()));
			tmp = tmp.getLeft();
		}

		alt.add(Verificator.findType(tmp.getRight()));
		alt.add(Verificator.findType(tmp.getLeft()));
		Collections.reverse(alt);
		return alt;
	}

	public String toJava() {
		if (getLeft() == null && getRight() == null)
			return "";
		return getLeft().toJava() + ", " + getRight().toJava();
	}
	
}