package org.tp;

import java.io.FileReader;
import java_cup.runtime.Symbol;

public class Main {

  static Env firstEnv = null;
  static Env currentEnv = null;
  static Boolean DEBUG = true;


  public static void main(String[] args) {
    try {
      FileReader  myFile = new FileReader(args[0]);
	    Scanner myLex = new Scanner(myFile);
	    Parser myP = new Parser(myLex);
	    Symbol result = null;

      try {
        //result = myP.debug_parse();
        result = myP.parse();
        try {
          AST root = (AST) result.value;
          CodeGenerator cg = new CodeGenerator(root);
          System.out.println("");
          System.out.println(root.toString());
          System.out.println("\n-------------------");
          System.out.println("Generating code:");
          System.out.println("-------------------\n");
          cg.generateCode();
          //root.toDot("detruire");
        }	catch (Exception e) {
		    System.out.println("Result error: " + e);
        }
	    } catch (Exception e) {
        System.out.println("Parse error");
	    }
    } catch (Exception e) {
	    System.out.println("Invalid file");
    }
  }
}
