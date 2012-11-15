package org.tp;

public enum EnumTag { 

	AFF (":="), 
	AND ("&&"), 
	OR ("||"), 
	LT ("<"), 
	GT (">"), 
	LE ("<="), 
	GE (">="), 
	EQ ("=="), 
	DIFF ("!=") , 
	PLUS ("+"), 
	MINUS ("-"), 
	MINUS_U ("-"), 
	MULT ("*"), 
	DIV ("/"), 

  VAR ("variable"),
	BOOLEAN ("boolean"),
	INTEGER ("integer"), 
	FLOATING ("floating"), 
	STRING("string"),
  CHAR("char"),
	SUCC("succ"),
  RANGE("range"),
  LIST("list"),
  BLOCK("block"),
  RETURN("return"),
  EXPRLIST("expression_list"),

  IF ("if"), 
  THENELSE ("thenelse"), 

  FOR("for"),
  FOR_RANGE("for_i_in_range"),
  WHILE ("while"), 

  PARAM("param"),
  PARAMS("params"),
  DECSVAR("var_decls"),
  DECVAR("var_decl"),

  GLOBAL_DECS("global_decs"),
  GLOBAL_DEC("global_dec"),

  PROCEDURE("procedure"),
  FUNCTION("function"),
  FUNCTIONS("functions"),
  FUNCTION_TYPE("function_type"),
  FUNCTION_CORE("function_core"),

  PROGRAM("function program");
	
	
  private final String tag;

  EnumTag(String s) {
    tag = s;
  }

  public String toString() {
    return tag;
  }
}

