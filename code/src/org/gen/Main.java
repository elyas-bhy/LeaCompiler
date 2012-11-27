package org.gen;

import java.io.FileReader;
import java_cup.runtime.Symbol;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class Main {

	static Env firstEnv = new Env();
	static Env currentEnv = firstEnv;
	static Boolean DEBUG = false;
	static StringBuffer globals = new StringBuffer();
	static Set<AST> structs = new HashSet<AST>();


	public static void main(String[] args) {
		Scanner myScanner = null;
		Parser myParser = null;
		Symbol result = null;
		AST root = null;
		CodeGenerator cg = new CodeGenerator();
		ArrayList<String> scannerErrors, parserErrors;

		try {
			FileReader  myFile = new FileReader(args[0]);
			myScanner = new Scanner(myFile);
			myParser = new Parser(myScanner);
		} catch (Exception e) {
			System.out.println("Invalid file");
		}

		try {
			result = myParser.parse();
		} catch (Exception e) {
			System.out.println("Parse error: " + e);
		}

		scannerErrors = myScanner.getErrors();
		for (String err : scannerErrors)
			System.err.println("\t" + args[0] + err);

		parserErrors = myParser.errors;
		for (String err : parserErrors)
			System.err.println("\t" + args[0] + err);

		if (scannerErrors.size() == 0 && parserErrors.size() == 0) {
			try {
				root = (AST) result.value;
			} catch (Exception e) {
				System.out.println("Result error: " + e);
			}
			cg.setRoot(root);
			cg.generateCode();
		}
		else {
			System.err.println("\n\t>> Code generation aborted.");
		}
	}
}
