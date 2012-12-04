package org.tree;

import org.gen.*;

public class Affect extends AST {


	public Affect(AST left, AST right) {
		super(left, right, EnumTag.AFF);

		CodeGenerator.checkDeclared(left);

		// Check matching type on affectation with function return type >>> a = f(b);
		// Uncomment when dominant types are implemented
		/*if (getRight().getType() == null || !getRight().getType().equals(getLeft().getType()) ) {
			ErrorObject err = new ErrorObject(Errors.TYPE_MISMATCH + getRight().toJava() + " and " + getLeft().toJava());
			Main.mParser.errors.add(err);
		}*/
	}

	public String toJava() {
		StringBuffer sb = new StringBuffer();
		String lvar = getLeft().toJava();
		EnumTag rtag = getRight().getTag();

		if (rtag.equals(EnumTag.FUNCTION_CALL)) {
			if (!Main.currentEnv.isInitialized(lvar))
				Main.currentEnv.set(lvar, getRight().toJava());

			if (getRight().getLeft().toJava().equals(JavaMethods.READ.toLea())) {
				sb.append(tab() + "if (mLeaCompilerConsole != null)\n");
				CodeGenerator.tabLevel++;
				sb.append(tab() + lvar + " = mLeaCompilerConsole.readLine();");
				CodeGenerator.tabLevel--;
				return sb.toString();
			}
		}

		if (rtag.equals(EnumTag.MAPOF) || rtag.equals(EnumTag.TUPLE)) {
			if (!Main.currentEnv.isInitialized(lvar)) {
				Main.currentEnv.set(lvar, getRight().toJava());
				sb.append(tab() + lvar + " = new " + Main.currentEnv.find(lvar) + "();\n");		
			}
			if (rtag.equals(EnumTag.MAPOF)) {
				for (AST node : getRight().getFields())
					sb.append(tab() + lvar + ".put(" + node.toJava() + ");\n");
			}
			else 
				sb.append(tab() + lvar + ".put(" + getRight().toJava() + ");");
			return sb.toString();
		}

		/*if (getLeft().getTag().equals(EnumTag.IDENT)) {
			if (!Main.currentEnv.isInitialized(lvar)) {
				Main.currentEnv.set(lvar, getRight().toJava());
				return tab() + lvar + " = new " + Main.currentEnv.find(lvar) + "(" + getRight().toJava() + ");";
			}
		}*/
		
		return tab() + lvar + " = " + getRight().toJava() + ";";
	}

}