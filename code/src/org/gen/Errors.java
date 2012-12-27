package org.gen;

import java.lang.String;

public enum Errors {
	UNDEF_VARIABLE ("undefined variable: "),
	UNDEF_REF ("undefined reference: "),
	TYPE_MISMATCH ("type mismatch: between "),
	EXPECTED_TYPE ("expected type: "),
	ILLEGAL_INSTR ("illegal instruction: "),
	VOID_RETURN ("cannot use return statement within a procedure"),
	MISSING_RETURN ("missing return statement");

	private final String error;

	private Errors (String error) {
		this.error = error;
	}

	public String toString() {
		return this.error;
	}
}