package org.tp;

public class CodeGenerator {
	
	private AST mRoot;
	private int tabLevel;

	public CodeGenerator(AST root) {
		mRoot = root;
		tabLevel = 1;
	}

	public void prologue() {
		//Includes the java headers (libraries and packages)
		StringBuffer sb = new StringBuffer();
		sb.append("import java.io.*;\n");
		sb.append("import java.lang.*;\n");
		sb.append("import java.util.*;\n\n");
		sb.append("public class Main {\n\n");
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
		switch(node.getTag()) {
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
				return translate(node.getLeft()) + ";\n" + translate(node.getRight());
			case AFF:
				return tab() + translate(node.getLeft()) + " = " + translate(node.getRight());
			case RETURN:
				return tab() + node.getTag() + " " + translate(node.getLeft()) + ";";
				
			case PLUS:
				return translate(node.getLeft()) + " + " + translate(node.getRight());

			case VAR:
			case INTEGER:
			case FLOATING:
			case STRING:
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