package org.gen;;

import org.gen.*;

public class If extends AST {
	
	public If(AST left, AST right) {
		super(left, right, EnumTag.IF);	
	}

	public String toJava() {
    return tab() + getTag() + " (" + getLeft().toJava() + ") " + getRight().toJava();
	}
	
}