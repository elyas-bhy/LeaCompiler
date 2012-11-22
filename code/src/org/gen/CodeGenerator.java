package org.gen;

public class CodeGenerator {
	
	private AST mRoot;
	public static int tabLevel;

	public CodeGenerator() {
		tabLevel = 1;
	}

  public void setRoot(AST root) {
    mRoot = root;
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

	public void generateCode() {
		prologue();
		//System.out.println(translate(mRoot));
    System.out.println(mRoot.toJava());
		epilogue();
  }
}