package org.gen;

import org.tree.*;

import java.util.ArrayList;

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
		if (node.getTag().equals(EnumTag.FUNCTION_CALL))
			return Main.prototypes.findType(node);
		Type t = node.getType();
		if (t == null)
			t = Main.currentEnv.find(node.toJava());
		return t;
	}

	public static void checkCompatibleTypes(AST left, AST right) {
		Type ltype = findType(left);
		Type rtype = findType(right);

		if (rtype == null || ltype == null || !rtype.getEnumType().equals(ltype.getEnumType())) {
			if (right.getTag().equals(EnumTag.FUNCTION_CALL)) {
				Prototype p = new Prototype(ltype, right.getLeft().toJava(), right.getTypesList());
				ErrorObject err = new ErrorObject(Errors.UNDEF_REF + p.toString());
				Main.mParser.errors.add(err);
			}
			else {
				ErrorObject err = new ErrorObject(Errors.TYPE_MISMATCH 
				+ "[" + ltype + ": " + left.toJava() + "] and "
				+ "[" + rtype + ": " + right.toJava() + "]");
				Main.mParser.errors.add(err);
			}
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
		if (t1 == null  || t2 == null)
			return null;

		if (t1.getEnumType().equals(t2.getEnumType()))
			return new Type(t1, t2, t1.getEnumType());
		return null;
    }

}