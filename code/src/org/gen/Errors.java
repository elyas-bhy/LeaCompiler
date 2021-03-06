package org.gen;

import java.lang.String;

public enum Errors {
	UNDEF_VARIABLE ("undefined variable: "),
	UNDEF_REF ("undefined reference: "),
	NOT_INIT ("might not have been initialized"),
	ILLEGAL_INSTR ("illegal instruction: "),
	NO_SUCH_FIELD ("no such field: "),
	UNCONSISTANT_ENTRYSET ("unconsistant entry set: mismatch between "),
	INCOMPATIBLE_T ("incompatible types: "),
	INCOMPATIBLE_RET ("incompatible return type: "),
	UNREACHABLE ("unreachable statement: "),
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