package org.gen;

public class SymbolTableEntry {

  public String id;
  public String value;
  public Type type;

  public SymbolTableEntry(String i, String v, Type t) {
    id = i;
    value = v;
    type = t;
  }

  public SymbolTableEntry() {
    id = null;
    value = null;
    type = null;
  }
}