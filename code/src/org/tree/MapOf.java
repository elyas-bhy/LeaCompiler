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
  
}