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
		return getRight().getTypesList();
	}

	public String toJava() {
		String procedure = getLeft().toJava();

		for (JavaMethods m : JavaMethods.values()) {
			if (m.toLea().equals(procedure))
				return tab() + m + "(" + getRight().toJava() + ");";
		}

		for (MapProcedures mp : MapProcedures.values()) {
			if (mp.toString().equals(procedure.toString())) {
				StringBuffer sb = new StringBuffer();
				for (AST a : getRight().getRight().getFields()) {
					sb.append(tab() + getRight().getLeft().toJava());
					sb.append("." + mp + "(");
					sb.append(a.getLeft().toJava());
					sb.append(", ");
					sb.append(a.getRight().toJava());
					sb.append(");\n");
				}
				String result = sb.toString();
				return result.substring(0, result.length()-1);
				//Remove trailing carriage return.
			}
		}
		return tab() + procedure + "(" + getRight().toJava() + ");";
	}
}