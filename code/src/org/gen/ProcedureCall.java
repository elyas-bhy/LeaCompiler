package org.gen;

public class ProcedureCall extends AST {
	
	public ProcedureCall(AST left, AST right) {
		super(left, right, EnumTag.PROCEDURE_CALL);		
	}

	public String toJava() {
		return tab() + getLeft().toJava() + "(" + getRight().toJava() + ")";
	}
	
}