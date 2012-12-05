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

	public static Type findType(AST node) {
		Type t = node.getType();
		if (t == null)
			t = Main.currentEnv.find(node.toJava());
		return t;
	}

	public static void checkTypes(AST left, AST right) {
		Type ltype = findType(left);
		Type rtype = findType(right);

		if (rtype == null || !rtype.getEnumType().equals(ltype.getEnumType())) {
			ErrorObject err = new ErrorObject(Errors.TYPE_MISMATCH 
				+ "[" + ltype + ": " + left.toJava() + "] and "
				+ "[" + rtype + ": " + right.toJava() + "]");
			Main.mParser.errors.add(err);
		}
	}

    public static void checkReturns(AST node) {
    	ErrorObject err;
    	if (node.getLeft().getType() == null && node.returnCount() > 0) {
    		err = new ErrorObject(Errors.VOID_RETURN.toString());
    		Main.mParser.errors.add(err);
    	}
    	else if (node.getLeft().getType() != null && node.returnCount() == 0) {
    		err = new ErrorObject(Errors.MISSING_RETURN.toString());
    		Main.mParser.errors.add(err);
    	}
    }

	public static Type getDominantType(AST e1, AST e2) {
		if (!checkDeclared(e1) || !checkDeclared(e2))
			return null;

		Type t1 = findType(e1);
		Type t2 = findType(e2);

		Type t = new Type(t1, t2);

		if (t1.getEnumType().equals(t2.getEnumType())) {
			t.setEnumType(t1.getEnumType());
		}
		/*if (t1.getEnumType().equals(EnumType.STRING) || t2.getEnumType().equals(EnumType.STRING)) {
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
		}*/

		if (t.getEnumType() == null)
			return null;
		return t;
    }

}