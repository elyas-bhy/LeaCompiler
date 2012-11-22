package org.gen;

public class VarDeclarations extends AST {
	
	public VarDeclarations(AST left, AST right) {
		super(left, right, EnumTag.VARDECS);		
	}

	public String toJava() {
		return getRight().toJava() + ";\n" + tab() + getLeft().toJava();
	}
	
}