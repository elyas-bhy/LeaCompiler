package org.gen;

import org.tree.*;
import java.util.HashSet;
import java.util.ArrayList;

public class Prototypes {

	private HashSet<Prototype> prototypes;
	private ArrayList<Type> mArgs = new ArrayList<Type>();

	private Type integer = new Type(EnumType.INT);
	private Type floating = new Type(EnumType.FLOAT);
	private Type str = new Type(EnumType.STRING);
	private Type chr = new Type(EnumType.CHAR);
	private Type bool = new Type(EnumType.BOOLEAN);
	private Type map = new Type(EnumType.MAP);
	private Type entry = new Type(EnumType.ENTRY);
	private Type entries = new Type(EnumType.ENTRIES);
	private Type array = new Type(EnumType.ARRAY);

	public Prototypes() {
		prototypes = new HashSet<Prototype>();
		initialize();
	}

	private void initialize() {

		// Arithmetic operations
		addOperations(EnumTag.PLUS.toString());
		addOperations(EnumTag.MINUS.toString());
		addOperations(EnumTag.MULT.toString());
		addOperations(EnumTag.DIV.toString());
		addOperations(EnumTag.MOD.toString());

		// String concatenation
		addOp(EnumTag.PLUS.toString(), str, str, str);
		addOp(EnumTag.PLUS.toString(), str, str, integer);
		addOp(EnumTag.PLUS.toString(), str, str, floating);
		addOp(EnumTag.PLUS.toString(), str, str, chr);

		// Lea default I/O methods
		addWriter(IOLib.WRITE.toLea());
		addWriter(IOLib.WRITELN.toLea());
		add(new Prototype(str, IOLib.READ.toLea(), mArgs));

		//Hashmap accessors & modifiers
		addPrimitive(MapLib.PUT.toString(), null, map, entry);
		addPrimitive(MapLib.PUT.toString(), null, map, entries);
		addPrimitive(MapLib.CLEAR.toString(), null, map);
		addPrimitive(MapLib.SIZE.toString(), integer, map);

		//Array functions
		addPrimitive(ArrayLib.LEN.toString(), integer, array);
	}

	private void addOperations(String operator) {
		addOp(operator, floating, floating, floating);
		addOp(operator, floating, floating, integer);
		addOp(operator, floating, floating, chr);

		addOp(operator, integer, integer, integer);
		addOp(operator, integer, integer, chr);
		addOp(operator, integer, chr, chr);
	}

	private void addOp(String identifier, Type returnType, Type arg1, Type arg2) {
		addPrimitive(identifier, returnType, arg1, arg2);
		if (!arg1.equals(arg2))
			addPrimitive(identifier, returnType, arg2, arg1);
	}

	private void addWriter(String identifier) {
		addPrimitive(identifier, null, str);
		addPrimitive(identifier, null, integer);
		addPrimitive(identifier, null, floating);
		addPrimitive(identifier, null, chr);
		addPrimitive(identifier, null, bool);
		add(new Prototype(null, identifier, mArgs));
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
			if (p.getName().equals(tmp.getName()) && p.getArgs().equals(tmp.getArgs()))
				return p.getReturnType();
		}
		// No matching signature found
		return null;
	}

	public void dump() {
		System.out.println("Prototypes dump: ");
		for (Prototype p : prototypes)
			System.out.println("\t" + p);
		System.out.println();
	}
}