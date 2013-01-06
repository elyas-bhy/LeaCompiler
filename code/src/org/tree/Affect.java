package org.tree;

import org.gen.*;
import java.util.ArrayList;

public class Affect extends AST {


	public Affect(AST left, AST right) {
		super(left, right, EnumTag.AFF);

		handleConstructorCall(left, right);
		Verificator.checkDeclared(left);
		Verificator.checkDeclared(right);
		Verificator.checkCompatibleTypes(this);
		Main.currentEnv.set(left.toJava(), right.toJava());
	}

	private void handleConstructorCall(AST left, AST right) {
		if (right.getTag().equals(EnumTag.FUNCTION_CALL)) {
			AST structFields = Main.structs.get(right.getLeft().getName());
			if (structFields != null) {
				ArrayList<AST> args = right.getRight().getFields();
				ArrayList<AST> fields = structFields.getFields();

				if (args.size() == fields.size()) {
					for (int i = 0; i < args.size(); i++)
						Main.currentEnv.set(left.getName() + "." + fields.get(i).getName(),
									 		args.get(i).toJava());
				}
			}
		}

	}

	public String toJava() {
		StringBuffer sb = new StringBuffer();
		String lvar = getLeft().toJava();
		EnumTag rtag = getRight().getTag();

		if (rtag.equals(EnumTag.FUNCTION_CALL)) {
			if (getRight().getLeft().toJava().equals(IOLib.READ.toLea())) {
				sb.append(tab() + "if (mLeaCompilerConsole != null)\n");
				CodeGenerator.tabLevel++;
				sb.append(tab() + lvar + " = mLeaCompilerConsole.readLine();\n");
				CodeGenerator.tabLevel--;
				sb.append(tab() + "else\n");
				CodeGenerator.tabLevel++;
				sb.append(tab() + lvar + " = null;");
				CodeGenerator.tabLevel--;
				return sb.toString();
			}
		}

		else if (rtag.equals(EnumTag.TUPLES) || rtag.equals(EnumTag.TUPLE)) {
			if (rtag.equals(EnumTag.TUPLES)) {
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