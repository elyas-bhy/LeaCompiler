package org.tree;

import org.gen.*;
import java.util.ArrayList;

public class ExprList extends AST {

	public ExprList(AST left, AST right) {
		super(left, right, EnumTag.EXPRLIST);

		Verificator.checkDeclared(left);
		Verificator.checkDeclared(right);
	}

	public String toJava() {
		if (getLeft() == null && getRight() == null)
			return "";
		return getLeft().toJava() + ", " + getRight().toJava();
	}
	
}