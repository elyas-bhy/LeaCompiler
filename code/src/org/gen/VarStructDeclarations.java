package org.gen;;

import org.gen.*;

import java.util.Set;
import java.util.HashSet;

public class VarStructDeclarations extends AST {
	
	public VarStructDeclarations(AST left, AST right) {
		super(left, right, EnumTag.VAR_STRUCTDECS);		
	}

	public String toJava() {
    return getRight().toJava() + ";\n" + tab() + getLeft().toJava();
	}

  //TODO implement getFields() in subclasses
  /*public Set<AST> getFields() {
    if (this.getRight() != null)
      return getRight().toArray().add(getLeft());
    return getLeft();
  }*/
	
}