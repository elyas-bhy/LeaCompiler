package org.gen;

import org.tree.*;

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

	public Type(Type left, Type right) {
		this(left, right, 0, null);
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

	public void setEnumType(EnumType type) {
		this.type = type;
	}

	public EnumType getEnumType() {
		return this.type;
	}

	public String getGenericType() {
		return genericType;
	}

	public int hashCode() {
		/*final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((integer == null) ? 0 : integer.hashCode());
		result = prime * result + ((genericType == null) ? 0 : genericType.hashCode());
		return result;*/
		return 0;
	}

	public boolean equals(Object o) {
		if (o instanceof Type) {
			Type t = (Type)o;

			if (left != null && !left.equals(t.getLeft()))
				return false;
			if (right != null && !right.equals(t.getRight()))
				return false;
			if (genericType != null && !genericType.equals(t.getGenericType()))
				return false;
			if (type != null && !type.equals(t.getEnumType()))
				return false;
			return true;
		}
		return false;
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
			case ENTRY:
				return "<" + left.toString() + "," + right.toString() + ">";
			case STRUCT:
				return genericType;
		}
		return "";
	}
}
