package org.tree;

import org.gen.*;

public class Program extends AST {
	
	public Program(AST left, AST right) {
		super(left, right, EnumTag.PROGRAM);		
	}

	public String toJava() {
		StringBuffer sb = new StringBuffer();
		sb.append("public class Main {\n\n");
		sb.append(tab() + "static Console mLeaCompilerConsole = System.console();\n\n");
		if (getLeft() != null)
			sb.append(getLeft().toJava());
		sb.append(Main.globals + "\n");
		sb.append(getRight().toJava());
		sb.append("\n}");
		return sb.toString();
	}
	
}