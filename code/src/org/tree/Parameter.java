package org.tree;

import org.gen.*;
import java.util.ArrayList;

public class Parameter extends AST {

	public Parameter(AST left, AST right, Type type) {
		super(left, right, EnumTag.PARAM, type);
	}

    public ArrayList<AST> getFields() {
      ArrayList<AST> fields = new ArrayList<AST>();
      fields.add(this);
      return fields;
    }
    
	public ArrayList<Type> getTypesList() {
		ArrayList<Type> alt = new ArrayList<Type>();
		alt.add(getType());
		return alt;
	}

	public String toJava() {
		return getType() + " " + getLeft().toJava();
	}
	
}