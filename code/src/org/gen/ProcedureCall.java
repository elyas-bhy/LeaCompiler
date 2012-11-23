package org.gen;

public class ProcedureCall extends AST {
	
	public ProcedureCall(AST left, AST right) {
		super(left, right, EnumTag.PROCEDURE_CALL);		
	}

	public String toJava() {
    String procedure = getLeft().toJava();
    for (JavaMethods m : JavaMethods.values()) {
      if (procedure.equals(m.toLea()))
        return tab() + m + "(" + getRight().toJava() + ")";
    }
		return tab() + procedure + "(" + getRight().toJava() + ")";
	}
	
}