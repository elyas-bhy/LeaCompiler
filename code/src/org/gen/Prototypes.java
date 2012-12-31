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
		ArrayList<Type> args = new ArrayList<Type>();
		Type integer = new Type(EnumType.INT);
		Type str = new Type(EnumType.STRING);
		Type map = new Type(EnumType.MAP);
		Type entrySet = new Type(EnumType.ENTRY);

		addPrimitive(EnumTag.PLUS.toString(), integer, integer, integer);
		addPrimitive(EnumTag.PLUS.toString(), str, integer, str);
		addPrimitive(EnumTag.PLUS.toString(), str, str, integer);
		addPrimitive(EnumTag.PLUS.toString(), str, str, str);

		addPrimitive(EnumTag.MINUS.toString(), integer, integer, integer);
		addPrimitive(EnumTag.MULT.toString(), integer, integer, integer);
		addPrimitive(EnumTag.DIV.toString(), integer, integer, integer);
		//TODO complete

		// Include Lea default I/O methods
		addPrimitive(JavaMethods.WRITE.toLea(), null, str);
		addPrimitive(JavaMethods.WRITELN.toLea(), null, str);
		addPrimitive(JavaMethods.WRITELN.toLea(), null, integer);
		add(new Prototype(str, JavaMethods.READ.toLea(), args));

		//Hashmap accessors & modifiers
		addPrimitive(MapProcedures.PUT.toString(), null, map, entrySet);
		addPrimitive(MapProcedures.CLEAR.toString(), null, map);
		addPrimitive(MapProcedures.SIZE.toString(), integer, map);
	}


	private void addPrimitive(String identifier, Type returnType, Type...arg) {
		ArrayList<Type> args = new ArrayList<Type>();
		if (arg != null) {
			for (Type t : arg)
				args.add(t);
		}
		Prototype p = new Prototype(returnType, identifier, args);
		add(p);
	}

	public void add(Prototype p) {
		prototypes.add(p);
	}

	public boolean contains(Object o) {
		return prototypes.contains(o);
	}

	public Type findPrototype(AST node) {
		Prototype tmp = new Prototype(null, node.getLeft().toJava(), node.getTypesList());
		for (Prototype p : prototypes) {
			//if (p.equals(tmp))	//Modify when allowing overrided methods
			if (p.getName().equals(tmp.getName()) && p.getArgs().equals(tmp.getArgs()))
				return p.getReturnType();
		}
		// No matching signature
		return null;
	}

	public void dump() {
		System.out.println("Prototypes dump: ");
		for (Prototype p : prototypes)
			System.out.println("\t" + p);
		System.out.println();
	}
}