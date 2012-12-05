package org.gen;

import org.tree.*;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;

public class CodeGenerator {
	
	private AST mRoot;
	public static int tabLevel;

	public CodeGenerator() {
		tabLevel = 1;
	}

	public void setRoot(AST root) {
		mRoot = root;
	}

	public StringBuffer prologue() {
		//Includes the java headers (libraries and packages)
		StringBuffer sb = new StringBuffer();
		sb.append("package output;\n\n");
		sb.append("import java.io.*;\n");
		sb.append("import java.lang.*;\n");
		sb.append("import java.util.*;\n\n");
		return sb;
	}

	public String epilogue() {
		//Add the closing bracket of the Java class
		return "\n}";
	}

	public void generateCode(String filename) {
		StringBuffer sb = new StringBuffer();
		sb.append(prologue());
		sb.append(mRoot.toJava());
		sb.append(epilogue());
		
		try {
			File f = new File(filename);
			if(!f.exists()) {
				System.out.println("Creating file");
				f.createNewFile();
			}
			FileWriter fw = new FileWriter(f);
			BufferedWriter bfw = new BufferedWriter(fw);
			bfw.write(sb.toString());
			bfw.flush();
		} catch (Exception e) {
			System.out.println("Failed file creation: " + e);
		}
	}
}