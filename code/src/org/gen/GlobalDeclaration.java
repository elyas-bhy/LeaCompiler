package org.gen;;

import org.gen.*;

public class GlobalDeclaration extends AST {
	
	public GlobalDeclaration(AST left, AST right) {
		super(left, right, EnumTag.GLOBAL_DEC);		
	}

	public String toJava() {
		if (getRight().getTag().equals(EnumTag.VAR_STRUCTDECS)) {
      // struct declaration
      Main.structs.add(this);
      StringBuffer sb = new StringBuffer();
      sb.append(tab() + "class " + getLeft().getName() + " {\n ");
      CodeGenerator.tabLevel++;
      sb.append(tab() + getRight().toJava() + ";\n\n");

      // Constructors:
      // emtpy constructor
      sb.append(tab() + "public " + getLeft().getName() + "() {\n");
      CodeGenerator.tabLevel++;
      sb.append(tab() + "//init fields to null\n");
      CodeGenerator.tabLevel--;
      sb.append(tab() + "}\n\n");

      // constructor with all fields
      sb.append(tab() + "public " + getLeft().getName() + "() {\n");
      CodeGenerator.tabLevel++;
      sb.append(tab() + "//init fields to parameter values\n");
      CodeGenerator.tabLevel--;
      sb.append(tab() + "}\n\n");

      



      CodeGenerator.tabLevel--; 
      sb.append(tab() + "}\n\n");
      return sb.toString();
    }

    // Global declaration
    Main.globals.append(tab() + "public " + getRight().getType() + " " + getLeft().toJava() +
                        " = new " + getRight().getType() + "(" + getRight().toJava() + ");\n");
    return "";
	}
	
}
