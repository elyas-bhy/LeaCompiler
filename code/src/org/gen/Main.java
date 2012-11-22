package org.gen;



import java.io.FileReader;
import java_cup.runtime.Symbol;
import java.util.Set;
import java.util.HashSet;

public class Main {

  static Env firstEnv = null;
  static Env currentEnv = null;
  static Boolean DEBUG = false;
  static StringBuffer globals = new StringBuffer();
  static Set<AST> structs = new HashSet<AST>();


  public static void main(String[] args) {
    try {
      FileReader  myFile = new FileReader(args[0]);
	    Scanner myLex = new Scanner(myFile);
	    Parser myP = new Parser(myLex);
	    Symbol result = null;
      CodeGenerator cg = new CodeGenerator();

      try {
        //result = myP.debug_parse();
        result = myP.parse();
        try {
          AST root = (AST) result.value;
          cg.setRoot(root);
          /*System.out.println("");
          System.out.println(root.toString());
          System.out.println("\n-------------------");
          System.out.println("Generating code:");
          System.out.println("-------------------\n");*/
          System.out.println("package output;\n");
          cg.generateCode();
          //root.toDot("detruire");
        }	catch (Exception e) {
		    System.out.println("Result error: " + e);
        }
	    } catch (Exception e) {
        System.out.println("Parse error: " + e);
	    }
    } catch (Exception e) {
	    System.out.println("Invalid file");
    }
  }
}
