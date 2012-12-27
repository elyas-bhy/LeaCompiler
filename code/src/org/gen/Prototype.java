package org.gen;

import org.tree.*;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Prototype {
	private Type returnType;
	private String name;
	private List<Type> args;

	public Prototype(Type returnType, String name, ArrayList<Type> args) {
		this.returnType = returnType;
		this.name = name;
		this.args = args;
	}

	public Prototype(Type returnType, String name) {
		this.returnType = returnType;
		this.name = name;
		args = new ArrayList<Type>();
	}

	public void addArg(Type t) {
		this.args.add(t);
	}

	public Type getReturnType() {
		return returnType;
	}

	public String getName() {
		return name;
	}

	public List<Type> getArgs() {
		return Collections.unmodifiableList(this.args);
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((returnType == null) ? 0 : returnType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((args == null) ? 0 : args.hashCode());
		return result;
	}

	public boolean equals(Object o) {
		if (o instanceof Prototype) {
			Prototype p = (Prototype)o;

			if (!name.equals(p.getName()))
				return false;
			if (returnType == null && p.getReturnType() != null)
				return false;
			if (returnType != null && !returnType.equals(p.getReturnType()))
				return false;

			List<Type> args2 = p.getArgs();
			int size = args.size();
			if (size == args2.size()) {
				for (int i = 0; i < size; i++) {
					if (args.get(i) == null || args2.get(i) == null)
						return false;
					if (!args.get(i).getEnumType().equals(args2.get(i).getEnumType()))
						return false;
				}
				return true;
			}
		}
		return false;
	}

	public String callToString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.name + "(");
		if (args.size() > 0) {
			for(Type t : args)
				sb.append(t + ", ");
			sb = sb.delete(sb.length() -2, sb.length());
		}
		sb.append(")");
		return sb.toString();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.returnType + " ");
		sb.append(this.callToString());
		return sb.toString();
	}
	
}