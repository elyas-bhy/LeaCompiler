package org.gen;

import org.tree.*;
import java.util.ArrayList;

public class Verificator {

	public static Type findType(AST node) {
		EnumTag tag = node.getTag();
		if (tag.equals(EnumTag.FUNCTION_CALL))
			return Main.prototypes.findPrototype(node);

		Type t = node.getType();
		if (t == null) {
			String var;
			//Workaround to get array slot type
			if (tag.equals(EnumTag.IDENT) && node.getLeft() != null)
				var = node.getName();
			else
				var = node.toJava();
			t = Main.currentEnv.find(var);
		}
		return t;
	}

	public static void checkInitialized(AST node) {
		if (node != null) {
			if (node.getTag().equals(EnumTag.IDENT)
		 	|| node.getTag().equals(EnumTag.SUBFIELD)) {

				String var = node.getName();
				if (Main.currentEnv.isDeclared(var)
				&& !Main.currentEnv.isInitialized(var)) {
					ErrorObject err = new ErrorObject("variable: " + var 
						+ " might not have been initialized");
					Main.mParser.errors.add(err);
				}
			}
			else if (node.getTag().equals(EnumTag.EXPRLIST)) {
				for (AST a : node.getFields())
					checkInitialized(a);
			}
		}
	}
 
    public static boolean checkDeclared(AST node) {
		if (node != null) {
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

	public static void checkSlots(AST node) {
		AST slot = node.getLeft();
		if (slot != null) {
			checkDeclared(slot);
			checkInitialized(slot);

			Type varType = Verificator.findType(node);
			Type slotType = Verificator.findType(slot);

			if (varType != null ) {
				node.setType(varType.getLeft());
				if (slotType == null || !slotType.getEnumType().equals(EnumType.INT)) {

    				ErrorObject err = new ErrorObject(Errors.INCOMPATIBLE_T.toString() 
    					+ node.toJava().replace("\t", "")
    					+ "\n\tfound: " + slotType
    					+ "\n\trequired: " + new Type(EnumType.INT));
    				Main.mParser.errors.add(err);
				}
			}
		}
	}

	public static void checkCompatibleTypes(AST left, AST right) {
		Type ltype = findType(left);	//expected type
		Type rtype = findType(right);

		// resolve/infer types
		if (right instanceof Operation 
		&& !right.getTag().equals(EnumTag.MINUS_U)) {
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

		else if (rtype == null || ltype == null
			 || !rtype.getEnumType().equals(ltype.getEnumType())) {
			if (right.getTag().equals(EnumTag.FUNCTION_CALL)) {
				Prototype p = new Prototype(ltype,
											right.getLeft().toJava(),
											right.getTypesList());
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

	public static void checkProcedureCall(AST node) {
		Prototype p = new Prototype(null, node.getLeft().getName(), node.getTypesList());
		if (Main.prototypes.contains(p)) {

			if (node.getLeft().getName().equals(MapLib.PUT.toString())) {
				AST map = node.getFields().get(0);
				Type mapTypes = findType(map);
				checkIdenticalEntryTypes(node.getFields().get(1), mapTypes);
			}
		}
		else {
			ErrorObject err = new ErrorObject(Errors.ILLEGAL_INSTR 
				+ p.callToString() + " must be declared as a procedure.");
			Main.mParser.errors.add(err);
		}
	}

	public static void checkForEach(AST node) {
		AST var = node.getRight();
		Type iterator = node.getType();
		if (var.getTag().equals(EnumTag.IDENT)) {
			Type t = findType(var).getLeft();
			if (!iterator.equals(t)) {
				
				ErrorObject err = new ErrorObject(Errors.INCOMPATIBLE_T.toString() 
    				+ node.getName().replace("\t", "")
    				+ "\n\tfound: " + iterator
    				+ "\n\trequired: " + t);
    			Main.mParser.errors.add(err);
			}
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
			ErrorObject err = new ErrorObject(Errors.NO_SUCH_FIELD
								+ ltype.toString() + "." + field);
			Main.mParser.errors.add(err);
		}
	}

	public static void checkIdenticalEntryTypes(AST node, Type mapTypes) {
		ArrayList<Type> entries = node.getTypesList();
		Type expectedType = new Type(mapTypes.getLeft(), mapTypes.getRight(), EnumType.ENTRY);
		for (Type t : entries) {
			if (!t.getLeft().equals(expectedType.getLeft())
			 || !t.getRight().equals(expectedType.getRight())) {
				ErrorObject err = new ErrorObject(Errors.UNCONSISTANT_ENTRYSET
									+ mapTypes.toString() + " and " + t);
				Main.mParser.errors.add(err);
			}
		}
	}

    public static void checkReturns(AST node) {
    	ArrayList<AST> returns = node.getReturnStatements();
    	ErrorObject err;

    	if (node.getLeft().getType() == null
    		  && returns.size() > 0) {
    		err = new ErrorObject(Errors.VOID_RETURN.toString());
    		Main.mParser.errors.add(err);
    	}
    	else if (node.getLeft().getType() != null) {
    		if (returns.size() == 0) {
    			err = new ErrorObject(Errors.MISSING_RETURN.toString());
    			Main.mParser.errors.add(err);
    		}
    		else {
    			for (AST a : returns) {
    				Type t = findType(a.getLeft());
    				if (!node.getType().equals(t)) {
    					err = new ErrorObject(Errors.INCOMPATIBLE_RET.toString() 
    						+ a.toJava().replace("\t", "")
    						+ "\n\tfound: " + t
    						+ "\n\trequired: " + node.getType());
    					Main.mParser.errors.add(err);

    				}
    			}
    		}
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
			return new Type(t1.getEnumType());
		return null;
    }

}