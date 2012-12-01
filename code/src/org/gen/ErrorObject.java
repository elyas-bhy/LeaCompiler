package org.gen;

public class ErrorObject {

	private String error;
	private int line;
	private int column;

	public ErrorObject(String error, int line, int column) {
		this.error = error;
		this.line = line;
		this.column = column;
	}

	public ErrorObject(String error) {
		this(error, Main.mScanner.yyline(), Main.mScanner.yycolumn());
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public String toString() {
		return ":" + line + ":" + column + ": error: " + error;
	}

}