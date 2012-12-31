package org.gen;

import org.tree.*;
import java.lang.String;

public enum MapProcedures {
	PUT ("put"),
	CLEAR ("clear"),
	SIZE ("size");

	private final String leaProcedure;

	private MapProcedures (String leaProcedure) {
		this.leaProcedure = leaProcedure;
	}

	public String toString() {
		return this.leaProcedure;
	}
}