package org.lealib;

import java.lang.String;

public enum ArrayLib {
	LEN ("len");

	private final String leaProcedure;

	private ArrayLib (String leaProcedure) {
		this.leaProcedure = leaProcedure;
	}

	public String toString() {
		return this.leaProcedure;
	}
}