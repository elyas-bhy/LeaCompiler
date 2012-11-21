package org.tp;

public class OptDeclarations extends AST {
	
	public OptDeclarations(AST left, AST right) {
		super(left, right, EnumTag.DECLOPT);		
	}

	public String toJava() {
    if (getRight() == null)
      return "";
    return getRight().toJava() + getLeft().toJava() + ";\n";
	}
	
}