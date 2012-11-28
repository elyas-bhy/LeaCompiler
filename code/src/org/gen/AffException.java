package org.gen;

public class AffException extends Exception{

  private static final long serialVersionUID = 1L;
  private String name;
    
  public AffException(String name) {
   	this.name = name;
  }

  public String getMessage() {
   	return "The variable " + name + " may not have been initialized.";
  }

} 
