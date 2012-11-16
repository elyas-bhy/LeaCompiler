package org.tp;

public class CodeGenerator {
	
	private AST mRoot;

	public CodeGenerator(AST root) {
		mRoot = root;
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


	public String translate(AST node) {
		switch(node.getTag()) {
			case FUNCTION:
				return "public " + node.getType() + " ";
			case PROCEDURE:
				return "public void ";
			case FUNCTION_CORE:
				return "(" + translate(node.getLeft()) + ") {\n" + translate(node.getRight()) + "\n}";
			case PARAM:
				return node.getType() + " " + translate(node.getLeft());
			case PARAMS:
				return translate(node.getRight()) + ", " + translate(node.getLeft());
			case BLOCK:
				return "<block>";
			case VAR:
				return node.getName();
			default:
				return "";
		}

	}

	public void generateCode() {
		StringBuffer sb = new StringBuffer();
		prologue();
		sb.append(translate(mRoot));
		sb.append(translate(mRoot.getLeft()));
		sb.append(translate(mRoot.getRight()));
		System.out.println(sb);
		epilogue();
    }
}