package org.tree;

import org.gen.*;

public class UndefVariableException extends Exception {

	private static final long serialVersionUID = 1L;
	private String name;

	public UndefVariableException(String name) {
		this.name = name;
	}

	public String getMessage() {
		return "The variable " + name + " is undefined.";
	}

} 
