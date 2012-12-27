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
		ArrayList<Type> alt = new ArrayList<Type>();
		AST right = getRight();

		if (right.getTag().equals(EnumTag.EXPRLIST))
			return right.getTypesList();

		alt.add(Verificator.findType(right));
		return alt;
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