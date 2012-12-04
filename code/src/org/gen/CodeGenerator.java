package org.gen;

import org.tree.*;

import org.tree.*;

public class CodeGenerator {
	
	private AST mRoot;
	public static int tabLevel;

	public CodeGenerator() {
		tabLevel = 1;
	}

	public void setRoot(AST root) {
		mRoot = root;
	}

	public static void checkDeclared(AST node) {
		if (node != null) {
			if (node.getTag().equals(EnumTag.IDENT)) {
				String var = node.getName();
				if (!Main.currentEnv.isDeclared(var)) {
					ErrorObject err = new ErrorObject(Errors.UNDEF_VARIABLE + var);
					Main.mParser.errors.add(err);
				}
			}
		}
	}

	public StringBuffer prologue() {
		//Includes the java headers (libraries and packages)
		StringBuffer sb = new StringBuffer();
		sb.append("package output;\n\n");
		sb.append("import java.io.*;\n");
		sb.append("import java.lang.*;\n");
		sb.append("import java.util.*;\n\n");
		return sb;
	}

	public String epilogue() {
		//Add the closing bracket of the Java class
		return "\n}";
	}

	public StringBuffer generateCode() {
		StringBuffer sb = new StringBuffer();
		sb.append(prologue());
		sb.append(mRoot.toJava());
		sb.append(epilogue());
		return sb;
	}
}