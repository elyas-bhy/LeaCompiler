package org.gen;

public class VarDeclaration extends AST {

	public VarDeclaration(AST left, AST right, Type type) {
		super(left, right, EnumTag.VARDEC, type);
		Main.currentEnv.add(left.getName(),type);
	}

	public String toJava() {
		if( getRight() != null ) {
			return tab() + "HashMap<" + getRight().toJava() + "> " + getLeft().toJava()
						 + " = new HashMap<" + getRight().toJava() + ">()";
		}
		// TODO check if we need to use primitive types instead of Java objects
		return tab() + getType() + " " + getLeft().toJava();
		/*switch(getType().getEnumType()) {
			case STRUCT:
				return tab() + getType().getGenericType() + " " + getLeft().toJava();
			case INT:
				return tab() + getType() + " " + getLeft().toJava() + " = new " + getType() + "(0);";
			case STRING:
				return tab() + getType() + " " + getLeft().toJava() + " = new " + getType() + "()";
			default:
				return tab() + getType() + " " + getLeft().toJava() + " = new " + getType() + "(null)";
		}*/
	}
}