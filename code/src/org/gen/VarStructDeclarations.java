package org.gen;;

import org.gen.*;

public class VarStructDeclarations extends AST {
	
	public VarStructDeclarations(AST left, AST right) {
		super(left, right, EnumTag.VAR_STRUCTDECS);		
	}

	public String toJava() {
    return getRight().toJava() + ";\n" + tab() + getLeft().toJava();
	}
	
}