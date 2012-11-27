package org.gen;

import java.io.FileReader;
import java_cup.runtime.Symbol;
import java.util.Set;
import java.util.HashSet;

public class Main {

	static Env firstEnv = new Env();
	static Env currentEnv = firstEnv;
	static Boolean DEBUG = false;
	static StringBuffer globals = new StringBuffer();
	static Set<AST> structs = new HashSet<AST>();


	public static void main(String[] args) {
		try {
			FileReader  myFile = new FileReader(args[0]);
			Scanner myLex = new Scanner(myFile);
			Parser myP = new Parser(myLex);
			CodeGenerator cg = new CodeGenerator();
			Symbol result = null;
		} catch (Exception e) {
			System.out.println("Invalid file");
		}

		try {
			result = myP.parse();
		} catch (Exception e) {
			System.out.println("Parse error: " + e);
		}

		ArrayList<String> scannerErrors = myLex.getErrors();
		try {
			AST root = (AST) result.value;
		} catch (Exception e) {
			System.out.println("Result error: " + e);
		}
		cg.setRoot(root);
		System.out.println("package output;\n");
		cg.generateCode();
	}
}
