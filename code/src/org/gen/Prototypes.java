package org.gen;

import org.tree.*;
import java.util.HashSet;

public class Prototypes {

	private HashSet<Prototype> prototypes;

	// TODO add Lea I/O methods
	public Prototypes() {
		prototypes = new HashSet<Prototype>();
	}

	public void add(Prototype p) {
		prototypes.add(p);
	}

	public Type findType(AST node) {
		Prototype tmp = new Prototype(node.getLeft().toJava(), null, node.getTypesList());
		for (Prototype p : prototypes) {
			if (p.equals(tmp))
				return p.getReturnType();
		}
		// No matching signature
		return null;
	}

	public void dump() {
		System.out.println("Dump: prototypes: ");
		for (Prototype p : prototypes)
			System.out.println(p);
	}
}