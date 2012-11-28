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

	public void add(String id, Type t) {
		symbolTable.put(id, new SymbolTableEntry(id, t));
	}

	public void add(String id, String value, Type t) {
		symbolTable.put(id, new SymbolTableEntry(id, value, t));
	}

	public void set(String id, String value){
		if (symbolTable.containsKey(id))
			symbolTable.get(id).value = value;
	}

	public boolean isDeclared(String id) {
		if (symbolTable.containsKey(id))
			return true;
		else if (this.prev != null)
			return this.prev.isDeclared(id);
		return false;
	}

	public boolean isInitialized(String id) {
		if(symbolTable.containsKey(id))
			return (symbolTable.get(id).value != null);
		else if (this.prev != null)
			return this.prev.isInitialized(id);
		return false;
	}

	public Type find(String id) {
		if(symbolTable.containsKey(id))
			return symbolTable.get(id).type;
		return null;
	}
}