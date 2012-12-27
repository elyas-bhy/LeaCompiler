package org.tree;

import org.gen.*;

import java.util.ArrayList;
import java.util.Collections;

public class MapOf extends AST {

	public MapOf(AST left, AST right) {
		super(left, right, EnumTag.MAPOF, new Type(EnumType.MAP));
		//TODO change map type to real type
	}

	public String toJava() {
		return getLeft().toJava() + ", " + getRight().toJava();
	}

	public ArrayList<AST> getFields() {
		ArrayList<AST> res = new ArrayList<AST>();
		AST tmp = this;
		while (tmp.getLeft().getTag().equals(EnumTag.MAPOF)) {
			res.add(tmp.getRight());
			tmp = tmp.getLeft();
		}
		res.add(tmp.getRight());
		res.add(tmp.getLeft());
		Collections.reverse(res);
		return res;
	}
  
}