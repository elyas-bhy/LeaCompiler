package org.tree;

import org.gen.*;
import java.util.ArrayList;

public class Parameters extends AST {
	
	public Parameters(AST left, AST right) {
		super(left, right, EnumTag.PARAMS);		
	}

	public ArrayList<Type> getTypesList() {
		ArrayList<Type> al = new ArrayList<Type>();
		AST tmp = this;
		while(tmp != null) {
			if(tmp.getRight().getTag().equals(EnumTag.PARAMS)){
				AST lefty = tmp.getLeft();
				if( lefty != null && lefty.getTag().equals(EnumTag.PARAM))
					if(lefty.getType() != null)
						al.add(lefty.getType());
			} else 
				if(tmp.getRight().getTag().equals(EnumTag.PARAM))
					if(tmp.getRight().getType() != null)
						al.add(tmp.getRight().getType());

			tmp = tmp.getRight();
		}
		return al;
	}

	public String toJava() {
		if (getLeft() == null && getRight() == null)
			return "";
		return getRight().toJava() + ", " + getLeft().toJava();
	}
	
}