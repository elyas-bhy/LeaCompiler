package org.gen;

public enum EnumType {
  CHAR ("Character"), 
	INT ("Integer"), 
	FLOAT ("Float"),
	STRING ("String"), 
	BOOLEAN ("Boolean"), 
	ARRAY ("Array"), 
  LIST ("List"),
	ERROR ("error"),
  STRUCT("generic type");
	
  private final String tag;

  EnumType(String s) {
    tag = s;
  }

  public String toString() {
    return tag;
  }
}
