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
		return getRight().getTypesList();
	}

	public String toJava() {
		if (Main.structs.keySet().contains(getLeft().getName())) {
			return "new " + getLeft().toJava() + "(" + getRight().toJava() + ")";
		}
		return getLeft().toJava() + "(" + getRight().toJava() + ")";
	}
	
}