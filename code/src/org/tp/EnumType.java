package org.tp;

public enum EnumType {
  CHAR ("char"), 
	INT ("int"), 
	FLOAT ("float"),
	STRING ("string"), 
	BOOLEAN ("bool"), 
	ARRAY ("array"), 
  LIST ("list"),
	POINTER ("pointer"), 
	ERROR ("error");
	
  private final String tag;

  EnumType(String s) {
    tag = s;
  }

  public String toString() {
    return tag;
  }
}
