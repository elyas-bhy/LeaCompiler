package org.gen;

public class Program extends AST {
	
	public Program(AST left, AST right) {
		super(left, right, EnumTag.PROGRAM);		
	}

	public String toJava() {
		StringBuffer sb = new StringBuffer();
		sb.append("public class Main {\n\n");
        sb.append(tab() + "static Console mLeaCompilerConsole = System.console();\n\n");
		sb.append(getLeft().toJava() + Main.globals + "\n" + getRight().toJava());
		return sb.toString();
	}
	
}