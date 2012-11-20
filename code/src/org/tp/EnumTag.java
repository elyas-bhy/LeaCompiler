package org.tp;

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

  VAR ("variable"),
	BOOLEAN ("Boolean"),
	INTEGER ("Integer"), 
	FLOATING ("Float"), 
	STRING("String"),
  CHAR("Char"),
	SUCC("succ"),
  RANGE("range"),
  LIST("list"),
  RETURN("return"),
  EXPRLIST("expression_list"),

  IF ("if"), 
  THENELSE ("thenelse"),

  FOR("for"),
  FOR_RANGE("for_i_in_range"),
  WHILE ("while"), 
  DOWHILE ("while"), 
  BLOCK("block"),

  PARAM("param"),
  PARAMS("params"),
  DECVAR("var_decl"),
  DECSVAR("var_decls"),

  GLOBAL_DECS("global_decs"),
  GLOBAL_DEC("global_dec"),

  HEADER("header"),
  PROTOTYPE("prototype"),
  PROCEDURE("procedure"),
  FUNCTION("function"),
  FUNCTIONS("functions"),
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

