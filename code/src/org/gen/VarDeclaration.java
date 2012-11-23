package org.gen;

public class VarDeclaration extends AST {

  public VarDeclaration(AST left, AST right, Type type) {
    super(left, right, EnumTag.VARDEC, type);
  }

  public String toJava() {
  	if (getType().getEnumType().equals(EnumType.STRUCT))
    	return tab() + getType().getGenericType() + " " + getLeft().toJava();
    return tab() + getType() + " " + getLeft().toJava() + " = new " + getType() + "(null)";
  }
	
}