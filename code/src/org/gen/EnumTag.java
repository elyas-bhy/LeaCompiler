package org.gen;

import org.tree.*;

public enum EnumTag { 

  PLUS ("+"),
  MINUS ("-"),
  MINUS_U ("-"),
  MULT ("*"),
  DIV ("/"),
  MOD ("%"),
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
  EXPRLIST("list"),
  TUPLES("tuples"),
  TUPLE("tuple"),
  TYPETUPLE("typetuple"),
  RETURN("return"),
  ARR_INIT("arr_init"),

  IF ("if"), 
  THENELSE ("thenelse"),

  FOR("for"),
  FOR_RANGE("for_i_in_range"),
  FOR_EACH("for_each"),
  WHILE ("while"), 
  DOWHILE ("do"), 
  BLOCK("block"),

  PARAM("param"),
  PARAMS("params"),
  VAR_STRUCTDEC("struct_var_decl"),
  VAR_STRUCTDECS("struct_var_decls"),
  VARDEC("var_decl"),
  VARDECS("var_decls"),
  DECLOPT("decl_opt"),
  SUBFIELD("subfield"),

  GLOBAL_DECS("global_decs"),
  GLOBAL_DEC("global_dec"),

  HEADER("header"),
  PROTOTYPE("prototype"),
  PROCEDURE("procedure"),
  PROCEDURE_CALL("procedure_call"),
  FUNCTION("function"),
  FUNCTIONS("functions"),
  FUNCTION_CALL("function_call"),
  FUNCTION_TYPE("function_type"),
  FUNCTION_CORE("function_core"),

  PROGRAM("program");
	
	
  private final String tag;

  EnumTag(String s) {
    tag = s;
  }

  public String toString() {
    return tag;
  }
}

