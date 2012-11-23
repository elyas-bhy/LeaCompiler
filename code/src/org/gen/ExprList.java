package org.gen;

public class ExprList extends AST {

  public ExprList(AST left, AST right) {
    super(left, right, EnumTag.EXPRLIST);
  }

  public String toJava() {
    if (getLeft() == null && getRight() == null)
      return "";
    return getLeft().toJava() + ", " + getRight().toJava();
  }
	
}