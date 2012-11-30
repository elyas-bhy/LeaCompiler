package org.gen;

public class ForRange extends AST {
	
	public ForRange(AST left, AST right) {
		super(left, right, EnumTag.FOR_RANGE);		
	}

	public String toJava() {
		String tmp = getLeft().toJava();
		return "int " + tmp + " = " + getRight().getLeft().toJava() + "; " 
		        + tmp + " < " + getRight().getRight().toJava() + "; " + tmp + "++";
	}
	
}