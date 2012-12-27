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

		if (getLeft() == null && getRight() == null)
			return al;

		while (tmp.getLeft().getTag().equals(EnumTag.PARAMS)) {
			al.add(tmp.getRight().getType());
			tmp = tmp.getLeft();
		}

		al.add(tmp.getRight().getType());
		al.add(tmp.getLeft().getType());
		Collections.reverse(al);
		return al;
	}

	public String toJava() {
		if (getLeft() == null && getRight() == null)
			return "";
		return getLeft().toJava() + ", " + getRight().toJava();
	}
	
}