package org.tp;

public enum EnumType {
  CHAR ("Char"), 
	INT ("Integer"), 
	FLOAT ("Float"),
	STRING ("String"), 
	BOOLEAN ("Boolean"), 
	ARRAY ("Array"), 
  LIST ("List"),
	ERROR ("error");
	
  private final String tag;

  EnumType(String s) {
    tag = s;
  }

  public String toString() {
    return tag;
  }
}
