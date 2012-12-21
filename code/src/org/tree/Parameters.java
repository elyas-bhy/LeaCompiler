package org.tree;

import org.gen.*;
import java.util.ArrayList;
import java.util.Collections;

public class Parameters extends AST {
	
	public Parameters(AST left, AST right) {
		super(left, right, EnumTag.PARAMS);		
	}

	public ArrayList<Type> getTypesList() {
		ArrayList<Type> al = new ArrayList<Type>();
		AST tmp = this;

		if (getRight() == null && getLeft() == null)
			return al;

		while (tmp.getRight().getTag().equals(EnumTag.PARAMS)) {
			al.add(tmp.getLeft().getType());
			tmp = tmp.getRight();
		}

		al.add(tmp.getLeft().getType());
		al.add(tmp.getRight().getType());
		Collections.reverse(al);
		return al;
	}

	public String toJava() {
		if (getLeft() == null && getRight() == null)
			return "";
		return getRight().toJava() + ", " + getLeft().toJava();
	}
	
}