package org.tp;

public class GlobalDeclaration extends AST {
	
	public GlobalDeclaration(AST left, AST right) {
		super(left, right, EnumTag.GLOBAL_DEC);		
	}

	public String toJava() {
		if (getRight().getTag().equals(EnumTag.VAR_STRUCTDECS)) {
      // struct declaration
      StringBuffer sb = new StringBuffer();
      sb.append(tab() + "class " + getLeft().toJava() + " {\n ");
      CodeGenerator.tabLevel++;
      sb.append(tab() + getRight().toJava() + ";\n");
      CodeGenerator.tabLevel--; 
      sb.append(tab() + "}\n\n");
      return sb.toString();
    }

    // Global declaration
    Main.globals.append(tab() + "public " + getRight().getType() + " " + getLeft().toJava() +
                        " = new " + getRight().getType() + "(" + getRight().toJava() + ");\n");
    return "";
/*
		if (getRight().getTag().equals(EnumTag.VARDECS)) // struct declaration
          return "class " + getLeft().toJava() + " {\n " + tab() + getRight().toJava() + ";\n}\n\n";
    	// implicit else : global declaration
    	Main.globals.append("\tpublic " + getRight().getType() + " " + getLeft().toJava() +
    	                    " = new " + getRight().getType() + "(" + getRight().toJava() + ");\n");
    	return "";*/
	}
	
}
