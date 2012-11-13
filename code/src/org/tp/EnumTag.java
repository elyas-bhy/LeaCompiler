package org.tp;
public enum EnumTag { 
	
	IF ("if"), 
	THENELSE ("thenelse"), 
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
	SUCC("succ");
	
	
    private final String tag;

    EnumTag(String s){
            tag = s;
    }

    public String toString(){
            return tag;
    }


}

