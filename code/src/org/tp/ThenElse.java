package org.tp;

public class ThenElse extends AST {
	
	public ThenElse(AST left, AST right) {
		super(left, right, EnumTag.THENELSE);		
	}

	public String toJava() {
    StringBuffer sb = new StringBuffer();
    sb.append("{\n" + getLeft().toJava() + ";\n");
    sb.append(tab() + "}");
    if (getRight() != null) {
      //ELSE section
      sb.append(" else {\n");
      sb.append(getRight().toJava() + ";\n");
      sb.append(tab() + "}");
    }
    return sb.toString();
	}
	
}