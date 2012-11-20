package org.tp;

public class VarDeclaration extends AST {
	
	public VarDeclaration(AST left, AST right, Type type) {
		super(left, right, EnumTag.VARDEC, type);		
	}

	public String toJava() {
    return getType() + " " + getLeft().toJava() + " = new " + getType() + "()";
	}
	
}