package org.gen;

public class Affect extends AST {
	
	public Affect(AST left, AST right) {
		super(left, right, EnumTag.AFF);
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
    else if (getLeft().getTag() == EnumTag.IDENT)
      if (Main.currentEnv.isDeclared(getLeft().getName())){
        Main.currentEnv.set(getLeft().getName(), getRight().toJava());
        return tab() + getLeft().toJava() + " = new " + Main.currentEnv.find(getLeft().getName()) + "(" + getRight().toJava() + ");";
      }
      /*else
        throw new UndefVariableException(left.getName());  // A décommenter lorsque nous ajouterons les exceptions
      */

		return tab() + getLeft().toJava() + " = " + getRight().toJava() + ";";
	}

}