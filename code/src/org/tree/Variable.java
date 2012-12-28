package org.tree;

import org.gen.*;
import java.util.ArrayList;

public class Variable extends AST {
	
	public Variable(EnumTag tag, String val, Type type) {
		super(tag, val, type);		
	}

	public Variable(EnumTag tag, String val) {
		super(tag, val);
	}

    public ArrayList<AST> getFields() {
      ArrayList<AST> fields = new ArrayList<AST>();
      fields.add(this);
      return fields;
    }

	public ArrayList<Type> getTypesList() {
		ArrayList<Type> alt = new ArrayList<Type>();
		alt.add(Verificator.findType(this));
		return alt;
	}

	public String toJava() {
		return getName();
	}
	
}