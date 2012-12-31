package org.tree;

import org.gen.*;
import java.util.ArrayList;

public class FunctionCall extends AST {

	public FunctionCall(AST left, AST right) {
		super(left, right, EnumTag.FUNCTION_CALL);
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
		String function = getLeft().toJava();
		if (Main.structs.keySet().contains(getLeft().getName())) {
			return "new " + function + "(" + getRight().toJava() + ")";
		}

		else if (function.equals(MapProcedures.SIZE.toString())) {
			StringBuffer sb = new StringBuffer();
			sb.append(getRight().toJava());
			sb.append("." + function);
			sb.append("()");
			return sb.toString();
		}
		return function + "(" + getRight().toJava() + ")";
	}
	
}