package org.tree;

import org.gen.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// Abstract Syntax Tree
// decorated with attributes 
// tag: node label (cf EnumTag)


public class AST {

    private int id;   // used in toDot
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
      priority = b;
    }
    
    public AST getLeft() {
      return left;
    }
    
    public void setLeft(AST left) {
      this.left = left;
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

    public ArrayList<AST> getFields() {
      return null;
    }

    public String toJava() {
      return "";
    }

    public String toString() {
      String result = new String();
      result += tag.toString();

      if ((left != null) || (right != null)) {
          result +="(";
        if (left != null)
          result += left.toString();
        if (right != null){
          result+=",";
          if (right.tag==EnumTag.SUCC || right.tag==EnumTag.THENELSE)
            result+="\n\n\t";
          result += right.toString();
        }
        result+=")";
      }
      return result;
    }
    
    /*public void toDot(StringBuffer str) {
      str.append(this.id+" [label=\""+tag.toString());
      switch (tag) {
        case INTEGER:
        case FLOATING:
        case STRING:
        case CHAR:
        case IDENT:      
          str.append(": "+this.str); 
          break;
        }
        if (type!=null) {
          str.append(" (");
          str.append(type.toString());
          str.append(")");
        }
        str.append("\"];\n");
        if (left != null) {
          left.toDot(str);
          str.append(this.id+" -> "+left.id+";\n");
        }
        if (right != null) {
          right.toDot(str);
          str.append(this.id+" -> "+right.id+";\n");
        }
        if (env != null && env.getRoot()!=null) {
          if (tag==EnumTag.IDENT) {
            str.append(env.getNum()+" [shape=\"box\", label=\"s"+env.getNum()+"\"];\n");
            str.append(this.id+" -> "+env.getNum()+" [color=\"blue\"];\n");
        }
      }
    }
    
    public void toDot(String file) {
      try {
        BufferedWriter out = new BufferedWriter(new FileWriter("./"+file+".dot"));
        out.write("digraph AST {");
        StringBuffer str = new StringBuffer();
        toDot(str);
        out.write(str.toString());
        out.write("}");
        out.close();
      } catch (IOException e) {
        System.out.println("ERROR: build dot");
      }       
    }*/
    // >>>
    public static Type getDominantType(Type t1, Type t2) {
      //Type t1 = e1.getType();
      //Type t2 = e2.getType();

      Type t = new Type(t1, t2);

      if( t1.equals(EnumType.STRING) || t2.equals(EnumType.STRING) ) {
        t.setEnumType(EnumType.STRING);
      }
      else if( t1.equals(EnumType.FLOAT) || t2.equals(EnumType.FLOAT) ) {
        t.setEnumType(EnumType.FLOAT);
      }
      else if( t1.equals(EnumType.INT) || t2.equals(EnumType.INT) ) {
        t.setEnumType(EnumType.INT);
      }
      else if( t1.equals(EnumType.CHAR) || t2.equals(EnumType.CHAR) ) {
        t.setEnumType(EnumType.CHAR);
      }
      else if( t1.equals(EnumType.BOOLEAN) || t2.equals(EnumType.BOOLEAN) ) {
        t.setEnumType(EnumType.BOOLEAN);
      }

      if (t.getEnumType() == null)
        return null;
      else
        return t;
    }
    // <<<
}
