package org.gen;

public class Variable extends AST {
	
	public Variable(EnumTag tag, String val, Type type) {
		super(tag, val, type);		
	}

	public Variable(EnumTag tag, String val) {
		super(tag, val);
	}

	public String toJava() {
		return getName();
	}
	
}