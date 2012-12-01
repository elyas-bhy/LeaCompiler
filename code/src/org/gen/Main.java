package org.gen;

import java.io.FileReader;
import java_cup.runtime.Symbol;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class Main {

	static Env firstEnv = new Env();
	static Env currentEnv = firstEnv;
	static ArrayList<Env> functionEnvs = new ArrayList<Env>();
	static Set<AST> structs = new HashSet<AST>();
	static StringBuffer globals = new StringBuffer();
	static int envNum = 0;
	
	static Boolean DEBUG = false;
	static Scanner mScanner = null;
	static Parser mParser = null;

	public static void main(String[] args) {
		Symbol result = null;
		AST root = null;
		CodeGenerator cg = new CodeGenerator();
		StringBuffer sb = new StringBuffer();
		ArrayList<ErrorObject> scannerErrors, parserErrors;

		try {
			FileReader  myFile = new FileReader(args[0]);
			mScanner = new Scanner(myFile);
			mParser = new Parser(mScanner);
		} catch (Exception e) {
			System.err.println("Invalid file");
		}

		try {
			result = mParser.parse();
		} catch (Exception e) {
			System.err.println("Parse error: " + e);
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
				System.err.println("Result error: " + e);
			}
			cg.setRoot(root);
			sb = cg.generateCode();
			System.out.println(sb);

		}
		else {
			System.err.println("\n\t>> Code generation aborted.");
		}
	}
}
