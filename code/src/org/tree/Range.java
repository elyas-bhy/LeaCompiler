package org.tree;

import org.gen.*;

public class Range extends AST {

	public Range(AST left, AST right) {
		super(left, right, EnumTag.RANGE);
	}
  
}