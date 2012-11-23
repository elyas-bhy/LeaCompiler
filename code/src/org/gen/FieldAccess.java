package org.gen;

public class FieldAccess extends AST {
	
	public FieldAccess(AST left, AST right) {
		super(left, right, EnumTag.FIELD_ACCESS);		
	}

	public String toJava() {
		return getRight().toJava() + "." +  getLeft().toJava();
	}
	
}