package org.gen;

import org.tree.*;

import java.util.ArrayList;

public class Verificator {

	public static Type findType(AST node) {
		if (node.getTag().equals(EnumTag.FUNCTION_CALL))
			return Main.prototypes.findPrototype(node);
		Type t = node.getType();
		if (t == null)
			t = Main.currentEnv.find(node.toJava());
		return t;
	}

	public static void checkInitialized(AST node) {
		if (node.getTag().equals(EnumTag.IDENT)) {
			String var = node.getName();
			if (Main.currentEnv.isDeclared(var) && !Main.currentEnv.isInitialized(var)) {
				ErrorObject err = new ErrorObject("variable: " + var + " might not have been initialized");
				Main.mParser.errors.add(err);
			}
		}
		else if (node.getTag().equals(EnumTag.EXPRLIST)) {
			for (AST a : node.getFields())
				checkInitialized(a);
		}
	}
 
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

	public static void checkCompatibleTypes(AST left, AST right) {
		Type ltype = findType(left);	//expected type
		Type rtype = findType(right);

		// resolve/infer types
		if (right instanceof Operation) {
			ArrayList<Type> args = new ArrayList<Type>();
			args.add(findType(right.getLeft()));
			args.add(findType(right.getRight()));
			Prototype p = new Prototype(ltype, right.getTag().toString(), args);

			if (!Main.prototypes.contains(p)) {
				// TODO provide a more explicit error message
				// (mentionning expected type / type found)
				ErrorObject err = new ErrorObject(Errors.UNDEF_REF + p.toString());
				Main.mParser.errors.add(err);
			}
		} 

		else if (rtype == null || ltype == null || !rtype.getEnumType().equals(ltype.getEnumType())) {
			if (right.getTag().equals(EnumTag.FUNCTION_CALL)) {
				Prototype p = new Prototype(ltype, right.getLeft().toJava(), right.getTypesList());
				ErrorObject err = new ErrorObject(Errors.UNDEF_REF + p.toString());
				Main.mParser.errors.add(err);
			}
			else if (!left.getTag().equals(EnumTag.SUBFIELD)
				  && !right.getTag().equals(EnumTag.SUBFIELD)) {
				//Subfields objects are tested in checkSubfield() method
				ErrorObject err = new ErrorObject(Errors.TYPE_MISMATCH 
				+ "[" + ltype + ": " + left.toJava() + "] and "
				+ "[" + rtype + ": " + right.toJava() + "]");
				Main.mParser.errors.add(err);
			}
		}
	}

	public static void checkProcedureCall(AST node) {
		Prototype p = new Prototype(null, node.getLeft().getName(), node.getTypesList());
		if (!Main.prototypes.contains(p)) {
			ErrorObject err = new ErrorObject(Errors.ILLEGAL_INSTR 
				+ p.callToString() + " must be declared as a procedure.");
			Main.mParser.errors.add(err);
		}
	}

	public static void checkSubfield(AST node) {
		Type ltype = findType(node.getLeft());
		String field = node.getRight().getName();
		ArrayList<AST> structFields = Main.structs.get(ltype.toString()).getFields();
		boolean typeFound = false;

		for (AST a : structFields) {
			if (a.getName().equals(field)) {
				node.setType(a.getType());
				typeFound = true;
				break;
			}
		}
		if (!typeFound) {
			ErrorObject err = new ErrorObject(Errors.NO_SUCH_FIELD + ltype.toString() + "." + field);
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

	public static Type inferType(AST e1, AST e2) {
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