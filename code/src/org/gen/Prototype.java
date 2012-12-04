package org.gen;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Prototype {
	private String name;
	private List<Type> args;

	public Prototype(String name) {
		this.name = name;
		args = new ArrayList<Type>();
	}

	public void addArg(Type t) {
		this.args.add(t);
	}

	public List<Type> getArgs() {
		return Collections.unmodifiableList(this.args);
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
	
}