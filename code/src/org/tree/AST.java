package org.tree;

import org.gen.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

// Abstract Syntax Tree
// decorated with attributes 
// tag: node label (cf EnumTag)


public class AST {
    private int id;
    private AST left;
    private AST right;
    private EnumTag tag;    // node label
    private String str;     // used for identifiers
    private Env env;        // current environment
    private Type type;
    private boolean priority;
    
    public AST(AST left, AST right, EnumTag tag, Type t) {
    	this.left = left;
    	this.right = right;
    	this.id = Env.num++;
    	this.tag = tag;
    	this.str = "";
    	this.env = Main.currentEnv;
    	this.type = t;
    	this.priority = false;
    }

    public AST(AST left, AST right, EnumTag tag) {
    	this(left, right, tag, null);
    }

    public AST(EnumTag tag) {
    	this(null, null, tag, null);
    }

    public AST(EnumTag tag, String str, Type type) {
    	this(null, null, tag, type);
    	this.str = str;
    }

    public AST(EnumTag tag, String str) {
    	this(null, null, tag, null);
    	this.str = str;
    }

    public void setPriority(boolean b) {
    	if (priority)
    		Verificator.checkDeclared(this);
    	priority = b;
    }

    public AST getLeft() {
    	return left;
    }

    public void setLeft(AST left) {
    	this.left = left;
    }

    public void setType(Type t) {
    	type = t;
    }

    public AST getRight() {
    	return right;
    }

    public void setRight(AST right) {
    	this.right = right;
    }

    public Type getType() {
    	return type;
    }

    public EnumTag getTag() {
    	return tag;
    } 

    public String getName() {
    	return str;
    }

    public Env getEnv() {
    	return env;
    }

    public String tab() {
    	String s = new String();
    	for (int i = 0; i < CodeGenerator.tabLevel; i++)
    		s += "\t";
    	return s;
    }

    public String priorityWrap(String s) {
    	if (this.priority)
    		return "(" + s + ")";
    	return s;
    }

    public ArrayList<AST> getReturnStatements() {
		ArrayList<AST> returns = new ArrayList<AST>();
    	if (tag.equals(EnumTag.RETURN) && left != null)
    		returns.add(this);
    	if (left != null)
    		returns.addAll(left.getReturnStatements());
    	if (right != null)
    		returns.addAll(right.getReturnStatements());
    	return returns;
    }

    public ArrayList<AST> getFields() {
    	return getFields(tag);
    }

    public ArrayList<AST> getFields(EnumTag listTag) {
    	ArrayList<AST> fields = new ArrayList<AST>();
    	if (left == null && right == null)
    		return fields; //no arguments

    	AST tmp = this;
    	while (tmp.getLeft().getTag().equals(listTag)) {
    		fields.add(tmp.getRight());
    		tmp = tmp.getLeft();
    	}
    	fields.add(tmp.getRight());
    	fields.add(tmp.getLeft());
    	Collections.reverse(fields);
    	return fields;
    }

	public ArrayList<Type> getTypesList() {
		ArrayList<Type> alt = new ArrayList<Type>();
		for (AST node : getFields())
			alt.add(Verificator.findType(node));
		return alt;
	}

    public String toJava() {
    	return "";
    }

    public String toString() {
        String result = new String();
        result += tag.toString();

        if ((left != null) || (right != null)) {
            result += "(";
            if (left != null)
                result += left.toString();
            if (right != null) {
                result += ",";
                if (right.tag == EnumTag.SUCC || right.tag == EnumTag.THENELSE)
                    result += "\n\n\t";
                result += right.toString();
            }
            result += ")";
        }
        return result;
    }
}
