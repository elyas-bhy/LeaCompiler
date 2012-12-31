package org.gen;

import org.tree.*;

public enum EnumType {
	CHAR ("Character"),
	INT ("Integer"),
	FLOAT ("Float"),
	STRING ("String"),
	BOOLEAN ("Boolean"),
	ARRAY ("Array"),
	EXPRLIST ("List"),
	ERROR ("error"),
	MAP("HashMap"),
	ENTRY("MapEntry"),
	STRUCT("generic type");

	private final String tag;

	EnumType(String s) {
		tag = s;
	}

	public String toString() {
		return tag;
	}
}
