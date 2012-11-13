package org.tp;

import java.io.FileReader;
import java_cup.runtime.Symbol;

public class Main {
	
    static Env firstEnv=null;
    static Env currentEnv=null;
    static Boolean DEBUG = true;

    public static void main(String[] args) {
	try {
	    FileReader  myFile = new FileReader(args[0]);
	    Scanner myLex = new Scanner(myFile);
	    Parser myP = new Parser(myLex);
	    Symbol result=null;
	    try {
		result=myP.debug_parse();
		try {
		    AST root=(AST)result.value;
		    System.out.println(root.toString());
		    //root.toDot("detruire");
		}	
		catch (Exception e) {
		    System.out.println("result error");
		}
	    }
	    catch (Exception e) {
		System.out.println("parse error...");
	    }
	}
	catch (Exception e){
	    System.out.println("invalid file");	    
	}
    }
}
