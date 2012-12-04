package org.gen;

import org.tree.*;

import org.tree.*;

import java.lang.String;

public enum JavaMethods {
	WRITE ("write", "System.out.print"),
	WRITELN ("writeln", "System.out.println"),
	READ ("read", "readLine");

	private final String leaMethod;
	private final String javaMethod;

	private JavaMethods (String leaMethod, String javaMethod) {
		this.leaMethod = leaMethod;
		this.javaMethod = javaMethod;
	}

	public String toLea() {
		return this.leaMethod;
	}

	public String toString() {
		return this.javaMethod;
	}
}