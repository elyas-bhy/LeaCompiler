package org.gen;

public class TypeTuple extends AST {

  public TypeTuple(Type left, Type right) {
    super(new AST(null, null, left), new AST(null, null, right), EnumTag.TYPETUPLE);
  }

  public String toJava() {
    return getLeft().toJava() + ", " + getRight().toJava();
  }
	
}