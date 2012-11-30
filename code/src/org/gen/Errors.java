package org.gen;

import java.lang.String;

public enum Errors {
	UNDEF_VARIABLE ("undefined variable: "),
	UNDEF_METHOD ("undefined method: "),
	TYPE_MISMATCH ("type mismatch: ");

	private final String error;

	private Errors (String error) {
		this.error = error;
	}

	public String toString() {
		return this.error;
	}
}