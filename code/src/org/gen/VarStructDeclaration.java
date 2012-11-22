package org.gen;;

import org.gen.*;

public class VarStructDeclaration extends AST {
	
	public VarStructDeclaration(AST left, AST right, Type type) {
		super(left, right, EnumTag.VAR_STRUCTDEC, type);		
	}

	public String toJava() {
    return getType() + " " + getLeft().toJava();
	}
	
}