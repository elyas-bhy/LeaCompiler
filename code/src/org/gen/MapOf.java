package org.gen;


public class MapOf extends AST {
  
  public MapOf(AST left, AST right) {
    super(left, right, EnumTag.MAPOF);
  }

  public String toJava() {
    return getLeft().toJava() + ", " + getRight().toJava();
  }
  
}