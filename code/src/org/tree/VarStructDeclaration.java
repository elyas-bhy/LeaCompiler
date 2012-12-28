package org.tree;

import org.gen.*;
import java.util.ArrayList;

public class VarStructDeclaration extends AST {
	
	public VarStructDeclaration(AST left, AST right, Type type) {
		super(left, right, EnumTag.VAR_STRUCTDEC, type);		
	}

    public ArrayList<AST> getFields() {
      ArrayList<AST> fields = new ArrayList<AST>();
      fields.add(this);
      return fields;
    }

	public String toJava() { 
		return getType() + " " + getLeft().toJava();
	}
	
}