package org.tree;

import org.gen.*;

import java.util.ArrayList;
import java.util.Collections;

public class VarStructDeclarations extends AST {

	public VarStructDeclarations(AST left, AST right) {
		super(left, right, EnumTag.VAR_STRUCTDECS);
	}

	public String toJava() {
		return getLeft().toJava() + ";\n" + tab() + getRight().toJava();
	}
	
}