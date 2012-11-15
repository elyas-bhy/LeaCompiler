package org.tp;
public enum EnumTag { 
	
	WHILE ("while"), 
	AFF ("="), 
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
  EXPRLIST("expression_list"),
  RANGE("range"),
  LIST("list"),
  BLOCK("block"),

  IF ("if"), 
  THENELSE ("thenelse"), 

  FOR("for"),
  FOR_RANGE("for i in range"),

  PARAMS("multiple params"),
  DECSVAR("var decls"),
  DECVAR("var decl"),

  GLOBAL_DECS("global decs"),
  GLOBAL_DEC("global dec"),

  FUNCTIONS("functions"),
  FUNCTION("function"),
  FUNCTION_TYPE("function type"),
  FUNCTION_PARAMS("function params"),

  PROGRAM("function program");
	
	
    private final String tag;

    EnumTag(String s){
            tag = s;
    }

    public String toString(){
            return tag;
    }


}

