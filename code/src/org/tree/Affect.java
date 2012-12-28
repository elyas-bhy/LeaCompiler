package org.tree;

import org.gen.*;

public class Affect extends AST {


	public Affect(AST left, AST right) {
		super(left, right, EnumTag.AFF);

		Verificator.checkDeclared(left);
		Verificator.checkDeclared(right);
		Verificator.checkCompatibleTypes(left, right);
		Main.currentEnv.set(left.toJava(), right.toJava());
	}

	public String toJava() {
		StringBuffer sb = new StringBuffer();
		String lvar = getLeft().toJava();
		EnumTag rtag = getRight().getTag();

		if (rtag.equals(EnumTag.FUNCTION_CALL)) {
			if (getRight().getLeft().toJava().equals(JavaMethods.READ.toLea())) {
				sb.append(tab() + "if (mLeaCompilerConsole != null)\n");
				CodeGenerator.tabLevel++;
				sb.append(tab() + lvar + " = mLeaCompilerConsole.readLine();");
				CodeGenerator.tabLevel--;
				return sb.toString();
			}
		}

		else if (rtag.equals(EnumTag.MAPOF) || rtag.equals(EnumTag.TUPLE)) {
			if (rtag.equals(EnumTag.MAPOF)) {
				for (AST node : getRight().getFields())
					sb.append(tab() + lvar + ".put(" + node.toJava() + ");\n");
			}
			else 
				sb.append(tab() + lvar + ".put(" + getRight().toJava() + ");");
			return sb.toString();
		}
		
		return tab() + lvar + " = " + getRight().toJava() + ";";
	}

}