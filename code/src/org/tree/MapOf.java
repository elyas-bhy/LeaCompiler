package org.tree;

import org.gen.*;

import java.util.ArrayList;
import java.util.Collections;

public class MapOf extends AST {

	public MapOf(AST left, AST right) {
		super(left, right, EnumTag.MAPOF);
	}

	public String toJava() {
		return getRight().toJava() + ", " + getLeft().toJava();
	}

	public ArrayList<AST> getFields() {
		ArrayList<AST> res = new ArrayList<AST>();
		AST tmp = this;
		while (tmp.getRight().getTag().equals(EnumTag.MAPOF)) {
			res.add(tmp.getLeft());
			tmp = tmp.getRight();
		}
		res.add(tmp.getLeft());
		res.add(tmp.getRight());
		Collections.reverse(res);
		return res;
	}
  
}