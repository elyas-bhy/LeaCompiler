package org.tp;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
// import org.tp.temp.*;


// binary search tree implementing a scope

public class ScopeTree {
    
    private ScopeTree left;
    private ScopeTree right;
    private int num;
    private String tag;	/* node label */
    private Type type;
    // private Temp tmp;  /* used in Intermediate Code */
    private boolean flag;
    
    public ScopeTree(ScopeTree left, ScopeTree right, String s, Type t) {
	this.left=left;
	this.right=right;
	this.num=Env.num;
	this.tag=s;
	this.type=t;
	 // this.tmp=null;
	this.flag=false; // used in toDot
	//System.out.print(toString()+"\n"); 
    }
    
    public ScopeTree(String s, Type t) {
	this(null, null, s ,t);
	//System.out.print(toString()+"\n"); 
    }
    
    public ScopeTree getLeft() {
	return left;
    }
    
    public void setLeft(ScopeTree left) {
	this.left = left;
    }
    
    public ScopeTree getRight() {
	return right;
    }
    
    public int getNum() {
	return num;
    }
    
    public Type getType() {
	return type;
    }
    
    public String getTag() {
	return tag;
    }
    
    public void setRight(ScopeTree right) {
	this.right = right;
    }
    
    public Type find(String s) {
	if (s.compareTo(tag) < 0)
	    return left.find(s);
	else if (s.compareTo(tag) > 0)
	    return right.find(s);
	else if (s.compareTo(tag) == 0)
	    return type; 
	else
	    return null;
    }
    
    public ScopeTree add(String s, Type t, ScopeTree a) {
	if (a==null)
	    return new ScopeTree(s, t);
	else if (s.compareTo(a.tag) < 0)
	    return new ScopeTree(add(s, t, a.left), a.right, a.tag, a.type);
	else if (s.compareTo(a.tag) > 0)
	    return new ScopeTree(a.left, add(s, t, a.right), a.tag, a.type);
	else
	    return new ScopeTree(a.left, a.right, s, t);
    }

    public String toString() {
	String result = new String();
	result += tag;
	if ((left != null) || (right != null)){
	    result +="(";
	    if (left != null)
		result += left.toString();
	    result+=",";
	    if (right != null)
		result += right.toString();
	    result+=")";
	}
	return result;
    }
    
    public void toDot(StringBuffer str) {
	if (!flag){
	    flag=true;
	    str.append(this.num+" [label=\""+tag+"\"];"+"\n");
	    if (left != null){
		left.toDot(str);
		str.append(this.num+" -> "+left.num+";\n");
	    }
	    if (right != null){
		right.toDot(str);
		str.append(this.num+" -> "+right.num+";\n");
	    }
	}
    }
    
    public void toDot(String file) {
	try {
	    BufferedWriter out = new BufferedWriter(new FileWriter("./"+file+".dot"));
	    out.write("digraph ArbreSym {");
	    StringBuffer str = new StringBuffer();
	    //flagOff();
	    toDot(str);
	    out.write(str.toString());
	    out.write("}");
	    out.close();
	} catch (IOException e) {
	    System.out.println("ERROR: build dot");
	}		
    }

    /***
    public void flagOff() {
	flag=false;
	if (left != null)
	    left.flagOff();
	if (right != null)
	    right.flagOff();
    }
    ***/
    
}
