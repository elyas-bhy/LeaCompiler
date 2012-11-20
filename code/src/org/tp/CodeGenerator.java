package org.tp;

public class CodeGenerator {
	
	private AST mRoot;
	private int tabLevel;
  private StringBuffer global_var;

	public CodeGenerator(AST root) {
		mRoot = root;
		tabLevel = 1;
    global_var = new StringBuffer();
	}

	public void prologue() {
		//Includes the java headers (libraries and packages)
		StringBuffer sb = new StringBuffer();
		sb.append("import java.io.*;\n");
		sb.append("import java.lang.*;\n");
		sb.append("import java.util.*;\n\n");
		System.out.println(sb);
	}

	public void epilogue() {
		//Add the closing bracket of the Java class
		System.out.println("\n}");
	}

	public String tab() {
		String s = new String();
		for (int i = 0; i < tabLevel; i++)
			s += "\t";
		return s;
	}


	public String translate(AST node) {
		StringBuffer sb;
    String tmp;
    EnumTag tag;
    if (node == null)
      return "";

		switch(node.getTag()) {
      case PROGRAM:
        return translate(node.getLeft()) + "public class Main {\n\n" + global_var + "\n" + translate(node.getRight());

      case GLOBAL_DECS:
        return translate(node.getLeft()) + translate(node.getRight());

      case GLOBAL_DEC:
        if (node.getRight().getTag().equals(EnumTag.DECSVAR)) // struct declaration
          return "class " + translate(node.getLeft()) + " {\n " + tab() + translate(node.getRight()) + ";\n}\n\n";
        // implicit else : global declaration
        global_var.append("\tpublic " + node.getRight().getType() + " " + translate(node.getLeft()) +
                          " = new " + node.getRight().getType() + "(" + translate(node.getRight()) + ");\n");
        return "";
        
			case FUNCTION:
				return tab() + "public " + node.getType() + " " + translate(node.getLeft()) + translate(node.getRight());
			
      case PROCEDURE:
				return tab() + "public void " + translate(node.getRight());
			
      case FUNCTION_CORE:
				sb = new StringBuffer();
				sb.append("(" + translate(node.getLeft()) + ") {\n" + translate(node.getRight()) + "\n");
				tabLevel--;
				sb.append(tab() + "}");
				return sb.toString();
			
      case PARAM:
				return node.getType() + " " + translate(node.getLeft());
			case PARAMS:
				return translate(node.getRight()) + ", " + translate(node.getLeft());
			
      case BLOCK:
				tabLevel++;
				return tab() + translate(node.getLeft()) + ";\n\n" + translate(node.getRight());
			
      case DECVAR:
				return node.getType() + " " + translate(node.getLeft()) + " = new " + node.getType() + "()";
			case DECSVAR:
				return translate(node.getRight()) + ";\n" + tab() + translate(node.getLeft());
			
      case SUCC:
        tag = node.getLeft().getRight().getTag();
        if (tag.equals(EnumTag.IF) || tag.equals(EnumTag.WHILE) || tag.equals(EnumTag.FOR))
          return translate(node.getLeft()) + "\n" + translate(node.getRight());
        return translate(node.getLeft()) + ";\n" + translate(node.getRight());
			
      case AFF:
				return tab() + translate(node.getLeft()) + " = " + translate(node.getRight());
			
      case RETURN:
				return tab() + node.getTag() + " " + translate(node.getLeft()) + ";";
			
			case IF:
				return tab() + node.getTag() + " (" + translate(node.getLeft()) + ") " + translate(node.getRight());
			case THENELSE:
        sb = new StringBuffer();
        tabLevel++;
        sb.append("{\n" + translate(node.getLeft()) + ";\n");
        tabLevel--;
        sb.append(tab() + "}");
        if (node.getRight() != null) {
          //ELSE section
          tabLevel++;
          sb.append(" else {\n");
          sb.append(translate(node.getRight()) + ";\n");
          tabLevel--;
          sb.append(tab() + "}");
        }
        return sb.toString();

      case WHILE:
      case FOR:
        sb = new StringBuffer();
        sb.append(tab() + node.getTag() + " (" + translate(node.getLeft()) + " ) {\n");
        tabLevel++;
        sb.append(translate(node.getRight()) + ";\n");
        tabLevel--;
        sb.append(tab() + "}");
        return sb.toString();


      case FOR_RANGE:
        tmp = translate(node.getLeft());
        return "int " + tmp + " = " + translate(node.getRight().getLeft()) + "; " +
               tmp + " < " + translate(node.getRight().getRight()) + "; " + tmp + "++";

			case PLUS:
			case MINUS:
			case MULT:
			case DIV:
			case EQ:
			case DIFF:
			case AND:
			case OR:
			case LT:
			case GT:
			case LE:
			case GE:
				return translate(node.getLeft()) + " " + node.getTag() + " " + translate(node.getRight());

			case VAR:
			case INTEGER:
			case FLOATING:
			case STRING:
      case CHAR:
				return node.getName();

			default:
				return "";
		}

	}

	public void generateCode() {
		prologue();
		System.out.println(translate(mRoot));
		epilogue();
    }
}