package org.gen;

import org.tree.*;
import java.util.HashSet;
import java.util.ArrayList;

public class Prototypes {

	private HashSet<Prototype> prototypes;

	public Prototypes() {
		prototypes = new HashSet<Prototype>();
		initialize();
	}

	private void initialize() {
		Type integer = new Type(EnumType.INT);
		Type str = new Type(EnumType.STRING);

		addPrimitive(EnumTag.PLUS.toString(), integer, integer, integer);
		addPrimitive(EnumTag.PLUS.toString(), str, integer, str);
		addPrimitive(EnumTag.PLUS.toString(), str, str, integer);
	}

	private void addPrimitive(String identifier, Type returnType, Type arg1, Type arg2) {
		ArrayList<Type> args = new ArrayList<Type>();
		args.add(arg1);
		args.add(arg2);
		Prototype p = new Prototype(returnType, identifier, args);
		add(p);
	}

	public void add(Prototype p) {
		prototypes.add(p);
	}

	public boolean contains(Object o) {
		return prototypes.contains(o);
	}

	public Type findType(AST node) {
		Prototype tmp = new Prototype(null, node.getLeft().toJava(), node.getTypesList());
		for (Prototype p : prototypes) {
			if (p.equals(tmp))
				return p.getReturnType();
		}
		// No matching signature
		return null;
	}

	public void dump() {
		System.out.println("Prototypes dump: ");
		for (Prototype p : prototypes)
			System.out.println(p + " " + p.hashCode());
		System.out.println();
	}
}