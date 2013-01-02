package org.tree;

import org.gen.*;
import java.util.ArrayList;

public class Variable extends AST {
	
	public Variable(EnumTag tag, String val, Type type) {
		super(tag, val, type);
	}

	public Variable(EnumTag tag, String val, AST slot) {
		super(tag, val);
		setLeft(slot);
		Verificator.checkSlots(this);
	}

    public ArrayList<AST> getFields() {
      ArrayList<AST> fields = new ArrayList<AST>();
      fields.add(this);
      return fields;
    }

	public String toJava() {
		return getName()
		+ ((getLeft() != null) ? "[" + getLeft().toJava() + "]" : "");
	}
	
}