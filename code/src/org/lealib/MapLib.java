package org.lealib;

import java.lang.String;

public enum MapLib {
	PUT ("put"),
	CLEAR ("clear"),
	SIZE ("size");

	private final String leaProcedure;

	private MapLib (String leaProcedure) {
		this.leaProcedure = leaProcedure;
	}

	public String toString() {
		return this.leaProcedure;
	}
}