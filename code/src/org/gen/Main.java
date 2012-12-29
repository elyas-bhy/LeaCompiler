package org.gen;

import org.tree.*;

import java.io.FileReader;
import java_cup.runtime.Symbol;
import java.util.HashMap;
import java.util.ArrayList;

public class Main {

	public static Env firstEnv = new Env();
	public static Env currentEnv = firstEnv;
	public static ArrayList<Env> functionEnvs = new ArrayList<Env>();
	public static HashMap<String,AST> structs = new HashMap<String,AST>();
	public static Prototypes prototypes = new Prototypes();
	public static StringBuffer globals = new StringBuffer();
	public static int envNum = 0;
	
	public static Boolean DEBUG = true;
	public static Scanner mScanner = null;
	public static Parser mParser = null;

	public static void main(String[] args) {
		Symbol result = null;
		AST root = null;
		CodeGenerator cg = new CodeGenerator();
		ArrayList<ErrorObject> scannerErrors, parserErrors;
		int nbExceptions = 0;

		try {
			FileReader  myFile = new FileReader(args[0]);
			mScanner = new Scanner(myFile);
			mParser = new Parser(mScanner);
		} catch (Exception e) {
			nbExceptions++;
			System.err.println("Invalid file");
		}

		try {
			result = mParser.parse();
		} catch (Exception e) {
			nbExceptions++;
			if (DEBUG) {
				System.err.println("Parse error: " + e);
				e.printStackTrace();
			} else
				System.err.println("A parsing error has occured.");
		}

		scannerErrors = mScanner.getErrors();
		for (ErrorObject err : scannerErrors)
			System.err.println("\t" + args[0] + err);

		parserErrors = mParser.errors;
		for (ErrorObject err : parserErrors)
			System.err.println("\t" + args[0] + err);

		if (scannerErrors.size() == 0 && parserErrors.size() == 0) {
			try {
				root = (AST) result.value;
			} catch (Exception e) {
				nbExceptions++;
				if (DEBUG) {
					System.err.println("Result error: " + e);
				} else
					System.err.println("An internal error has occured.");
			}
			cg.setRoot(root);

			if (nbExceptions == 0)
				cg.generateCode("tests/output/" + args[1] + "/src/gen/Main.java");
		}

		if (scannerErrors.size() != 0
		 || parserErrors.size() != 0
		 || nbExceptions != 0) {
			System.err.println("\n\t>> Code generation aborted; see the compiler error output for details.");
		}
	}
}
