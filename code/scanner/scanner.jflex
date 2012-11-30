package org.gen;

import java_cup.runtime.*;
import java.io.*;
import java.util.ArrayList;

%%
%class Scanner

%{
	public static ArrayList<ErrorObject> errors = new ArrayList<ErrorObject>();

	public int yyline() {
		return yyline;
	}

	public int yycolumn() {
		return yycolumn;
	}

	public static ArrayList<ErrorObject> getErrors() {
		return Scanner.errors;
	}
%}

%line
%column
%cupsym MySymbol
%cup
%{
	private Symbol symbol (int type) {
		return new Symbol (type, yyline, yycolumn);
	}

	private Symbol symbol (int type, Object value) {
		return new Symbol (type, yyline, yycolumn, value);
	}
%}



Identifier	   = [a-zA-Z][a-zA-Z0-9_]*
LineTerminator = \r|\n|\r\n
WhiteSpace	   = {LineTerminator} | [ \t\f]
Integer		     = [0-9]+
String		     = \"[^\"]*\"
Char           = \'.?\'

Num = [0-9]+
Type = [fFlL]?
NumType = {Num}{Type}

Floating =   {Num}\.{Num}[eE]-?{NumType}
        | \.{Num}[eE]-?{NumType}
        | {Num}[eE]-?{NumType} 
        | {Num}\.{NumType} 
        | {Num}\.{Type} 
        | \.{NumType} 

%xstates MONO_COMMENT, MULTI_COMMENT

%%

<MONO_COMMENT> {
  {LineTerminator}      {yybegin(YYINITIAL);}
  .                     {}
}

<MULTI_COMMENT> {
  \*\/                  {yybegin(YYINITIAL);}
  . | {LineTerminator}  {}
}

<YYINITIAL> {

/* -------------------------------------------------
	Separators & operators
   ------------------------------------------------- */

\/\/			{yybegin(MONO_COMMENT); }
\/\*			{yybegin(MULTI_COMMENT); }

\"				{ return symbol(MySymbol.DQUOTE, yytext()); }
"("				{ return symbol(MySymbol.LPAR, yytext()); }
")"				{ return symbol(MySymbol.RPAR, yytext()); }
"{"				{ return symbol(MySymbol.LBRACE, yytext()); }
"}"				{ return symbol(MySymbol.RBRACE, yytext()); }
"["				{ return symbol(MySymbol.LBRACKET, yytext()); }
"]"				{ return symbol(MySymbol.RBRACKET, yytext()); }
".."			{ return symbol(MySymbol.DDOT, yytext()); }
"&&"			{ return symbol(MySymbol.AND, yytext()); }
"||"			{ return symbol(MySymbol.OR, yytext()); }
"<"				{ return symbol(MySymbol.LT, yytext()); }
">"				{ return symbol(MySymbol.GT, yytext()); }
"<="			{ return symbol(MySymbol.LE, yytext()); }
">="			{ return symbol(MySymbol.GE, yytext()); }
"!="			{ return symbol(MySymbol.DIFF, yytext()); }
":="			{ return symbol(MySymbol.AFF, yytext()); }
"+"				{ return symbol(MySymbol.PLUS, yytext()); }
"-"				{ return symbol(MySymbol.MINUS, yytext()); }
"*"				{ return symbol(MySymbol.MULT, yytext()); }
"/"				{ return symbol(MySymbol.DIV, yytext()); }
","				{ return symbol(MySymbol.COMMA, yytext()); }
"."				{ return symbol(MySymbol.DOT, yytext()); }
";"				{ return symbol(MySymbol.SEMIC, yytext()); }
":"				{ return symbol(MySymbol.COLON, yytext()); }
"="				{ return symbol(MySymbol.EQ, yytext()); }
"if"			{ return symbol(MySymbol.IF, yytext()); }
"then"			{ return symbol(MySymbol.THEN, yytext()); }
"else"			{ return symbol(MySymbol.ELSE, yytext()); }
"while"			{ return symbol(MySymbol.WHILE, yytext()); }
"for"			{ return symbol(MySymbol.FOR, yytext()); }
"in"			{ return symbol(MySymbol.IN, yytext()); }
"do"			{ return symbol(MySymbol.DO, yytext()); }
"bool"			{ return symbol(MySymbol.BOOLEAN, yytext()); }
"int"			{ return symbol(MySymbol.INT, yytext()); }
"float"			{ return symbol(MySymbol.FLOAT, yytext()); }
"char"			{ return symbol(MySymbol.CHAR, yytext()); }
"string"		{ return symbol(MySymbol.STRING, yytext()); }
"array"			{ return symbol(MySymbol.ARRAY, yytext()); }
"of"			{ return symbol(MySymbol.OF, yytext()); }
"set"			{ return symbol(MySymbol.SET, yytext()); }
"map"			{ return symbol(MySymbol.MAP, yytext()); }
"list"			{ return symbol(MySymbol.EXPRLIST, yytext()); }
"struct"		{ return symbol(MySymbol.STRUCT, yytext()); }
"void"			{ return symbol(MySymbol.VOID, yytext()); }
"repeat"		{ return symbol(MySymbol.REPEAT, yytext()); }
"return"		{ return symbol(MySymbol.RETURN, yytext()); }
"function"		{ return symbol(MySymbol.FUNCTION, yytext()); }
"procedure"		{ return symbol(MySymbol.PROCEDURE, yytext()); }
"true"			{ return symbol(MySymbol.BOOL, yytext()); }
"false"			{ return symbol(MySymbol.BOOL, yytext()); }

/* -------------------------------------------------
	Variables
   ------------------------------------------------- */

{Identifier}	{ return symbol(MySymbol.IDENTIFIER, yytext()); }
{Floating}		{ return symbol(MySymbol.FLOATING, yytext()); }
{Integer}		{ return symbol(MySymbol.INTEGER, yytext()); }
{String}		{ return symbol(MySymbol.STRINGEXP, yytext()); }
{Char}			{ return symbol(MySymbol.CHAREXP, yytext()); }

/* -------------------------------------------------
	Other
   ------------------------------------------------- */
{WhiteSpace}	{ /* ignore */ }

.				{ ErrorObject err = new ErrorObject("illegal character: " + yytext(), 
												  yyline()+1, 
												  yycolumn());
				  Scanner.errors.add(err); }

}
