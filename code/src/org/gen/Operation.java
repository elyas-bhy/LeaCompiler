package org.gen;

public class Operation extends AST {

	public Operation(AST left, AST right, EnumTag tag, Type type) {
		super(left, right, tag, type);
		String var;

		if (left != null && left.getTag().equals(EnumTag.IDENT)) {
			var = getLeft().getName();
			if (!Main.currentEnv.isDeclared(var)) {
				ErrorObject err = new ErrorObject(Errors.UNDEF_VARIABLE + var, 
												Main.mScanner.yyline(), 
												Main.mScanner.yycolumn());
				Main.mParser.errors.add(err);
			}
		}

		if (right != null && right.getTag().equals(EnumTag.IDENT)) {
			var = right.getName();
			if (!Main.currentEnv.isDeclared(var)) {
				ErrorObject err = new ErrorObject(Errors.UNDEF_VARIABLE + var, 
												Main.mScanner.yyline(), 
												Main.mScanner.yycolumn());
				Main.mParser.errors.add(err);
			}
		}
	}
	
	public Operation(AST left, AST right, EnumTag tag) {
		this(left, right, tag, null);		
	}

	public String toJava() {
		if (getTag().equals(EnumTag.MINUS_U))
			return priorityWrap(getTag() + getRight().toJava());
		return priorityWrap(getLeft().toJava() + " " + getTag() + " " + getRight().toJava());
	}

}