package org.tp;

public class ThenElse extends AST {
	
	public ThenElse(AST left, AST right) {
		super(left, right, EnumTag.THENELSE);		
	}

	public String toJava() {
    StringBuffer sb = new StringBuffer();
    CodeGenerator.tabLevel++;
    sb.append("{\n" + getLeft().toJava() + ";\n");
    CodeGenerator.tabLevel--;
    sb.append(tab() + "}");
    if (getRight() != null) {
      //ELSE section
      CodeGenerator.tabLevel++;
      sb.append(" else {\n");
      sb.append(getRight().toJava() + ";\n");
      CodeGenerator.tabLevel--;
      sb.append(tab() + "}");
    }
    return sb.toString();
	}
	
}