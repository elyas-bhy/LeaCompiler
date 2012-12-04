package org.tree;

import org.gen.*;

public class SymbolTableEntry {

	public String id;
	public String value;
	public Type type;

	public SymbolTableEntry(String i, String v, Type t) {
		id = i;
		value = v;
		type = t;
	}

	public SymbolTableEntry(String i, Type t) {
		this(i, null, t);
	}
}