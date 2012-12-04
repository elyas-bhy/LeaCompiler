package org.gen;

import org.tree.*;

import org.tree.*;

public class TypeException extends Exception {

  private static final long serialVersionUID = 1L;
  private Type type1, type2;

  public TypeException(Type type1, Type type2) {
    this.type1 = type1;
    this.type2 = type2;
  }

  public String getMessage() {
    return "Type mismatch : cannot convert from " + this.type1 + " to " + this.type2;
  }
}
