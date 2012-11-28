package org.gen;

import java.util.Set;
import java.util.ArrayList;

public class GlobalDeclaration extends AST {
	
	public GlobalDeclaration(AST left, AST right) {
		super(left, right, EnumTag.GLOBAL_DEC);
    Main.currentEnv.add(left.getName(), right.getType());
	}

	public String toJava() {
    AST left = getLeft();
    AST right = getRight();
    StringBuffer sb = new StringBuffer();

    if (right.getTag().equals(EnumTag.VAR_STRUCTDECS) || right.getTag().equals(EnumTag.VAR_STRUCTDEC)) {
      // subclass declaration
      Main.structs.add(this);
      sb.append(tab() + "static class " + left.getName() + " {\n ");
      CodeGenerator.tabLevel++;
      sb.append(tab() + right.toJava() + ";\n\n");

      // include both constructors here
      declareConstructors(sb, left, right);

      CodeGenerator.tabLevel--; 
      sb.append(tab() + "}\n\n");
      return sb.toString();
    }

    // Global declaration
    Main.globals.append(tab() + "public static " + right.getType() + " " + left.toJava() +
                        " = new " + right.getType() + "(" + right.toJava() + ");\n");
    return "";
	}

  private void declareConstructors(StringBuffer sb, AST left, AST right) {
    // Retrieve declared fields of the subclass
    ArrayList<AST> fields;
    if (right.getTag().equals(EnumTag.VAR_STRUCTDEC)) {
      fields = new ArrayList<AST>();
      fields.add(right);
    } else {
      fields = right.getFields();
    }

    // Emtpy constructor
    sb.append(tab() + "public " + left.getName() + "() {\n");
    CodeGenerator.tabLevel++;
    for (AST node : fields)
      sb.append(tab() + "this." + node.getLeft().getName() + " = null;\n");
    CodeGenerator.tabLevel--;
    sb.append(tab() + "}\n\n");

    // Constructor with all fields
    sb.append(tab() + "public " + left.getName() + "(");
    for (AST node : fields)
      sb.append(node.getType() + " " + node.getLeft().getName() + ", ");
    sb = sb.delete(sb.length() - 2, sb.length());           //extract the trailing ", "
    sb.append(") {\n");
    CodeGenerator.tabLevel++;
    for (AST node : fields)
      sb.append(tab() + "this." + node.getLeft().getName() + " = " + node.getLeft().getName() + ";\n");
    CodeGenerator.tabLevel--;
    sb.append(tab() + "}\n\n");
  }
	
}
