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

    public ArrayList<AST> getFields() {
    	ArrayList<AST> fields = new ArrayList<AST>();
    	if (getRight().getTag().equals(EnumTag.EXPRLIST)) {
    		if (getRight().getLeft() == null && getRight().getRight() == null)
    			return fields;
    		fields.addAll(getRight().getFields());
    		return fields;
    	}
    	fields.add(getRight());
    	return fields;
    }

	public String toJava() {
		String procedure = getLeft().toJava();

		for (JavaMethods m : JavaMethods.values()) {
			if (m.toLea().equals(procedure))
				return tab() + m + "(" + getRight().toJava() + ");";
		}

		if (procedure.equals(MapProcedures.PUT.toString())) {
			StringBuffer sb = new StringBuffer();
			for (AST a : getRight().getRight().getFields()) {
				sb.append(tab() + getRight().getLeft().toJava());
				sb.append("." + procedure + "(");
				sb.append(a.getLeft().toJava());
				sb.append(", ");
				sb.append(a.getRight().toJava());
				sb.append(");\n");
			}
			String result = sb.toString();
			return result.substring(0, result.length()-1);
			//Remove trailing carriage return.
		}

		else if (procedure.equals(MapProcedures.CLEAR.toString())) {
			StringBuffer sb = new StringBuffer();
			sb.append(tab() + getRight().toJava());
			sb.append("." + procedure);
			sb.append("();");
			return sb.toString();
		}
		return tab() + procedure + "(" + getRight().toJava() + ");";
	}
}