package org.gen;;

import org.gen.*;

public class VarDeclaration extends AST {

  public VarDeclaration(AST left, AST right, Type type) {
    super(left, right, EnumTag.VARDEC, type);
  }

  public String toJava() {
    return tab() + getType() + " " + getLeft().toJava() + " = new " + getType() + "(null)";
  }
	
}