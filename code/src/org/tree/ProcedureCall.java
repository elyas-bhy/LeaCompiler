package org.tree;

import org.gen.*;
import java.util.ArrayList;

public class ProcedureCall extends AST {

	public ProcedureCall(AST left, AST right) {
		super(left, right, EnumTag.PROCEDURE_CALL);
		Verificator.checkProcedureCall(this);
		Verificator.checkDeclared(right);
		Verificator.checkInitialized(right);
	}

	public ArrayList<Type> getTypesList() {
		System.out.println("Right: " + getRight() + ": " + getRight().getTypesList());
		return getRight().getTypesList();
	}

	public String toJava() {
		String procedure = getLeft().toJava();
		for (JavaMethods m : JavaMethods.values()) {
			if (procedure.equals(m.toLea()))
				return tab() + m + "(" + getRight().toJava() + ");";
		}
		return tab() + procedure + "(" + getRight().toJava() + ");";
	}
}