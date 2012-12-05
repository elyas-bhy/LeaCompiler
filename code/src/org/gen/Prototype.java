package org.gen;

import org.tree.*;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Prototype {
	private String returnType;
	private String name;
	private List<Type> args;

	public Prototype(String name, String returnType) {
		this.name = name;
		this.returnType = returnType;
		args = new ArrayList<Type>();
	}

	public Prototype(String name, String returnType, ArrayList<Type> args) {
		this.name = name;
		this.args = args;
		this.returnType = returnType;
	}

	public void addArg(Type t) {
		this.args.add(t);
	}

	public List<Type> getArgs() {
		return Collections.unmodifiableList(this.args);
	}

	public String getReturnType() {
		return returnType;
	}

	public boolean equals(Object o) {
		if (o instanceof Prototype){
			Prototype p = (Prototype)o;
			List<Type> args2 = p.getArgs();
			int size = args.size();
			if (size == args2.size()){
				for (int i = 0; i < size; i++)
					if (args.get(i) != args2.get(i))
						return false;
				return true;
			}
		}
		return false;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.returnType + " " + this.name + "(");
		for(Type t : args)
			sb.append(t + " ");
		sb.append(");\n");
		return sb.toString();
	}
	
}