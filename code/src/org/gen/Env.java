package org.gen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/* stack of scopes implementing the environnement */


public class Env {
	protected static int num = 0;

	/* double linked list */
	private Env prev;
	private Env next;
	private HashMap<String, SymbolTableEntry> symbolTable; /* scope on top of the stack */
	private int scopenum;


	/*public Env(Env prev, Env next, ArbreSym root) {
		this.prev = prev;
		this.next = next;
		this.root = root;
		this.scopenum = num++;
	}*/

	public Env(Env prev, Env next) {
		this.prev = prev;
		this.next = next;
		this.scopenum = num++;
		this.symbolTable = new HashMap<String, SymbolTableEntry>();
		//this(prev, next, null);
	}

	public Env(Env prev) {
		this(prev, null);
	}

	public Env() {
		this(null, null);
	}

	public int getNum() {
		return scopenum;
	}

	public Env getPrev() {
		return prev;
	}

	public void putPrev(Env prev) {
		this.prev = prev;
	}

	public Env getNext() {
		return next;
	}

	public void putNext(Env next) {
		this.next = next;
	}

	public HashMap<String, SymbolTableEntry> getSymbols() {
		return symbolTable;
	}

	/*public void toDot(String file) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("./" + file + ".dot"));
			StringBuffer str = new StringBuffer();
			str.append("digraph Env {");
			//str.append("subgraph {node [shape=\"box\"]; rank=same;");
			for (Env e = this; e != null; e = e.next) {
				str.append(e.scopenum + " [shape=\"box\", label=\"s" + e.scopenum + "\"];" + "\n");
				//if (e.next != null)
					//str.append(e.id+" -> " + e.next.id + ";\n");
			}
			//str.append("};");
			for (Env e = this; e != null; e = e.next) {
				if (e.root != null) {
					e.root.toDot(str);
					str.append(e.scopenum + " -> " + e.root.getNum() + ";\n");
				}
			}
			str.append("}");
			out.write(str.toString());
			out.close();
		} catch (IOException e) {
			System.out.println("ERROR: build dot");
		}
	}*/

	public void add(String s, Type t) {
		symbolTable.put(s, new SymbolTableEntry(s, t));
	}

	public void add(String s, String v, Type t) {
		symbolTable.put(s, new SymbolTableEntry(s, v, t));
	}

	public boolean isDeclared(String s) {
		return symbolTable.containsKey(s);
	}

	public boolean isInitialized(String s) {
		if(this.isDeclared(s))
			return (symbolTable.get(s).value != null);
		return false;
	}

	public Type find(String s) {
		if(symbolTable.containsKey(s))
			return symbolTable.get(s).type;
		return null;
	}
}