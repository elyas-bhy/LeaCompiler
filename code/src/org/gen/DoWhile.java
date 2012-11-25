package org.gen;

public class DoWhile extends AST {
	
	public DoWhile(AST left, AST right) {
		super(left, right, EnumTag.DOWHILE);		
	}

	public String toJava() {
		StringBuffer sb = new StringBuffer();
		sb.append(tab() + getTag() + " {\n");
		sb.append(getRight().toJava() + "\n");
		sb.append(tab() + "} while (" + getLeft().toJava() + ");");
		return sb.toString();
	}
	
}