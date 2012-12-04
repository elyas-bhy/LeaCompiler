package org.tree;

import org.gen.*;

/* tree implementing a type*/
public class Type {

	private Type left;
	private Type right;
	private EnumType type;
	private Integer integer; // array size
	private String genericType;

	public Type(Type left, Type right, Integer i, EnumType type) {
		this.left = left;
		this.right = right;
		this.integer = i;
		this.type = type;
		this.genericType = null;
	}

	public Type(Type left, Type right, EnumType type) {
		this(left, right, 0, type);
	}

	public Type(EnumType type) {
		this(null, null, type);
	}

	public Type(EnumType type, String s) {
		this(type);
		this.genericType = s;
	}

	public Type getLeft() {
		return left;
	}

	public void setLeft(Type left) {
		this.left = left;
	}

	public Type getRight() {
		return right;
	}

	public void setRight(Type right) {
		this.right = right;
	}

	public EnumType getEnumType() {
		return this.type;
	}

	public String getGenericType() {
		return genericType;
	}

	public String toString() {
		switch (type) {
			case INT:
			case FLOAT:
			case STRING:
			case CHAR:
			case BOOLEAN:
				return type.toString();
			case ARRAY:
				return left.toString() + "[]";
			case MAP:
				return type.toString() + "<" + left.toString() + "," + right.toString() + ">";
			case STRUCT:
				return genericType;
			//case ERROR: return "ERROR";
		}
		return "";
	}
}