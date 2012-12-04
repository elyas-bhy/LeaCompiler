package org.gen;

import org.tree.*;

public class Verificator {

    public static boolean checkDeclared(AST node) {
		if (node != null && node.getTag() != null) {
			if (node.getTag().equals(EnumTag.IDENT)) {
				String var = node.getName();
				if (!Main.currentEnv.isDeclared(var)) {
					ErrorObject err = new ErrorObject(Errors.UNDEF_VARIABLE + var);
					Main.mParser.errors.add(err);
					return false;
				}
			}
		}
		return true;
	}

	public static void checkTypes(AST left, AST right) {
		Type rtype = Main.currentEnv.find(left.toJava());
		Type ltype = Main.currentEnv.find(left.toJava());

		if (rtype == null || !rtype.getEnumType().equals(ltype.getEnumType())) {
			ErrorObject err = new ErrorObject(Errors.TYPE_MISMATCH 
				+ "[" + ltype + ": " + left.toJava() + "] and "
				+ "[" + rtype + ": " + right.toJava() + "]");
			Main.mParser.errors.add(err);
		}
	}

	public static Type getDominantType(AST e1, AST e2) {
		if (!checkDeclared(e1) || !checkDeclared(e2))
			return null;

		Type t1 = e1.getType();
		Type t2 = e2.getType();

		if (t1 == null)
			t1 = Main.currentEnv.find(e1.toJava());
		if (t2 == null)
			t2 = Main.currentEnv.find(e2.toJava());

		Type t = new Type(t1, t2);

		if (t1.getEnumType().equals(EnumType.STRING) || t2.getEnumType().equals(EnumType.STRING)) {
			t.setEnumType(EnumType.STRING);
		}
		else if (t1.getEnumType().equals(EnumType.FLOAT) || t2.getEnumType().equals(EnumType.FLOAT)) {
			t.setEnumType(EnumType.FLOAT);
		}
		else if (t1.getEnumType().equals(EnumType.INT) || t2.getEnumType().equals(EnumType.INT)) {
			t.setEnumType(EnumType.INT);
		}
		else if (t1.getEnumType().equals(EnumType.CHAR) || t2.getEnumType().equals(EnumType.CHAR)) {
			t.setEnumType(EnumType.CHAR);
		}
		else if (t1.getEnumType().equals(EnumType.BOOLEAN) || t2.getEnumType().equals(EnumType.BOOLEAN)) {
			t.setEnumType(EnumType.BOOLEAN);
		}
		else if (t1.getEnumType().equals(EnumType.STRUCT) || t2.getEnumType().equals(EnumType.STRUCT)) {
			t.setEnumType(EnumType.STRUCT);
		}

		if (t.getEnumType() == null)
			return null;
		return t;
    }

}