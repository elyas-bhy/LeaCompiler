package org.gen;

public class UndefMethodException extends Exception{

  private static final long serialVersionUID = 1L;
  private String name;
    
  public UndefMethodException(String name) {
   	this.name = name;
  }

  public String getMessage() {
   	return "The method " + name + "() is undefined.";
  }

} 