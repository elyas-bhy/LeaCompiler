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

	public ArrayList<AST> getFields() {
		ArrayList<AST> res = new ArrayList<AST>();
		AST tmp = this;
		while (tmp.getLeft().getTag().equals(EnumTag.VAR_STRUCTDECS)) {
			res.add(tmp.getRight());
			tmp = tmp.getLeft();
		}
		res.add(tmp.getRight());
		res.add(tmp.getLeft());
		Collections.reverse(res);
		return res;
	}
	
}