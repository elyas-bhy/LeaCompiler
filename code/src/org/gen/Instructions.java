package org.gen;;

import org.gen.*;

public class Instructions extends AST {
	
	public Instructions(AST left, AST right) {
		super(left, right, EnumTag.SUCC);		
	}

	public String toJava() {
    EnumTag tag = getLeft().getRight().getTag();
    if (tag.equals(EnumTag.IF) || tag.equals(EnumTag.WHILE) || tag.equals(EnumTag.FOR))
       return getLeft().toJava() + "\n" + getRight().toJava();
    return getLeft().toJava() + ";\n" + getRight().toJava();

	}
	
}