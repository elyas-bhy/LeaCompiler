package org.gen;



public enum EnumTag { 

  PLUS ("+"),
  MINUS ("-"),
  MINUS_U ("-"),
  MULT ("*"),
  DIV ("/"),
  AFF ("="),
  EQ ("=="),
  DIFF ("!=") ,
  AND ("&&"),
  OR ("||"),
  LT ("<"),
  GT (">"),
  LE ("<="),
  GE (">="),

  IDENT ("identifier"),
	BOOLEAN ("Boolean"),
	INTEGER ("Integer"), 
	FLOATING ("Float"), 
	STRING("String"),
  CHAR("Char"),
	SUCC("succ"),
  RANGE("range"),
  LIST("list"),
  RETURN("return"),

  IF ("if"), 
  THENELSE ("thenelse"),

  FOR("for"),
  FOR_RANGE("for_i_in_range"),
  WHILE ("while"), 
  DOWHILE ("while"), 
  BLOCK("block"),

  PARAM("param"),
  PARAMS("params"),
  VAR_STRUCTDEC("struct var decl"),
  VAR_STRUCTDECS("struct var decls"),
  VARDEC("var decl"),
  VARDECS("var decls"),
  DECLOPT("decl opt"),

  GLOBAL_DECS("global_decs"),
  GLOBAL_DEC("global_dec"),

  HEADER("header"),
  PROTOTYPE("prototype"),
  PROCEDURE("procedure"),
  FUNCTION("function"),
  FUNCTIONS("functions"),
  FUNCTION_CALL("function call"),
  FUNCTION_TYPE("function_type"),
  FUNCTION_CORE("function_core"),

  PROGRAM("function_program");
	
	
  private final String tag;

  EnumTag(String s) {
    tag = s;
  }

  public String toString() {
    return tag;
  }
}

