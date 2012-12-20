package org.gen;

import org.tree.*;

import java.io.FileReader;
import java_cup.runtime.Symbol;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class Main {

	public static Env firstEnv = new Env();
	public static Env currentEnv = firstEnv;
	public static ArrayList<Env> functionEnvs = new ArrayList<Env>();
	public static Set<AST> structs = new HashSet<AST>();
	public static Prototypes prototypes = new Prototypes();
	public static StringBuffer globals = new StringBuffer();
	public static int envNum = 0;
	
	public static Boolean DEBUG = false;
	public static Scanner mScanner = null;
	public static Parser mParser = null;

	public static void main(String[] args) {
		Symbol result = null;
		AST root = null;
		CodeGenerator cg = new CodeGenerator();
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
			e.printStackTrace();
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
			cg.generateCode("tests/output/" + args[1] + "/src/gen/Main.java");
		}
		else {
			System.err.println("\n\t>> Code generation aborted.");
		}
	}
}
