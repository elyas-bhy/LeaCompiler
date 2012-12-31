package org.tree;

import org.gen.*;
import java.util.ArrayList;

public class Tuple extends AST {

	public Tuple(AST left, AST right) {
		super(left, right, EnumTag.TUPLE, 
			new Type(Verificator.findType(left), 
					 Verificator.findType(right), 
					 EnumType.ENTRY));

		Verificator.checkDeclared(left);
		Verificator.checkDeclared(right);
		Verificator.checkInitialized(left);
		Verificator.checkInitialized(right);
	}
	
    public ArrayList<AST> getFields() {
      ArrayList<AST> fields = new ArrayList<AST>();
      fields.add(this);
      return fields;
    }

	public String toJava() {
		return getLeft().toJava() + ", " + getRight().toJava();
	}
  
}