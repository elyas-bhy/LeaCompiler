package org.gen;;

import org.gen.*;

public class Header extends AST {
	
  public Header(AST left, AST right, Type type) {
    super(left, right, EnumTag.HEADER, type);   
  }

	public Header(AST left, AST right) {
		super(left, right, EnumTag.HEADER);		
	}

	public String toJava() {
    return getType() + " " + getLeft().toJava() + "(" + getRight().toJava() + ") {\n";
	}
	
}