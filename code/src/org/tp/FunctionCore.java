package org.tp;

public class FunctionCore extends AST {
	
	public FunctionCore(AST left, AST right) {
		super(left, right, EnumTag.FUNCTION_CORE);		
	}

	public String toJava() {
    StringBuffer sb = new StringBuffer();
    sb.append("(" + getLeft().toJava() + ") {\n" + getRight().toJava() + "\n");
    CodeGenerator.tabLevel--;
    sb.append(tab() + "}");
    return sb.toString();
	}
	
}