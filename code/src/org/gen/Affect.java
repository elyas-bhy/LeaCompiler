package org.gen;

public class Affect extends AST {

	public Affect(AST left, AST right) throws Exception {
		super(left, right, EnumTag.AFF);	

		if (left.getTag() == EnumTag.IDENT) {
			String var = getLeft().getName();
			if (!Main.currentEnv.isDeclared(var)) {
				ErrorObject err = new ErrorObject(Errors.UNDEF_VARIABLE + var, 
												Main.mScanner.yyline(), 
												Main.mScanner.yycolumn());
				Main.mParser.errors.add(err);
			}
		}
	}

	public String toJava() {
		if (getRight().getTag().equals(EnumTag.FUNCTION_CALL)) {
			if (getRight().getLeft().toJava().equals(JavaMethods.READ.toLea())) {
				StringBuffer sb = new StringBuffer();
				sb.append(tab() + "if (mLeaCompilerConsole != null)\n");
				CodeGenerator.tabLevel++;
				sb.append(tab() + getLeft().toJava() + " = mLeaCompilerConsole.readLine();");
				CodeGenerator.tabLevel--;
				return sb.toString();
			}
		}
		else if (getRight().getTag().equals(EnumTag.MAPOF)) {
			StringBuffer sb = new StringBuffer();
			for (AST node : getRight().getFields())
				sb.append(tab() + getLeft().toJava() + ".put(" + node.toJava() + ");\n");
			return sb.toString();
		}
		else if (getLeft().getTag() == EnumTag.IDENT) {
			String var = getLeft().getName();
			if (!Main.currentEnv.isInitialized(var)) {
				Main.currentEnv.set(var, getRight().toJava());
				return tab() + var + " = new " + Main.currentEnv.find(var) + "(" + getRight().toJava() + ");";
			}
		}
		return tab() + getLeft().toJava() + " = " + getRight().toJava() + ";";
	}

}