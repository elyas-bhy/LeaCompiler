package org.tp;

import java_cup.runtime.*;
import java.io.*;

%%
%class TpLexer
%line
%column
%cupsym TpSymbol
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

Num = [0-9]+
Type = [fFlL]?
NumType = {Num}{Type}

Floating =   {Num}\.{Num}[eE]-?{NumType}
        | \.{Num}[eE]-?{NumType}
        | {Num}[eE]-?{NumType} 
        | {Num}\.{NumType} 
        | {Num}\.{Type} 
        | \.{NumType} 

%%

/* -------------------------------------------------
	Separateurs Operateurs
   ------------------------------------------------- */

"("	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.LPAR); }
")"	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.RPAR); }
"{"	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.LBRACE); }
"}"	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.RBRACE); }
"["	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.LBRACKET); }
"]"	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.RBRACKET); }
"&&"  	      {/*System.out.print(yytext());*/  return symbol(TpSymbol.AND); }
"||"  	      {/*System.out.print(yytext());*/  return symbol(TpSymbol.OR); }
"<"	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.LT); }
">"	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.GT); }
"<="	        {/*System.out.print(yytext());*/  return symbol(TpSymbol.LE); }
">="	        {/*System.out.print(yytext());*/  return symbol(TpSymbol.GE); }
"!="	        {/*System.out.print(yytext());*/  return symbol(TpSymbol.DIFF); }
"=="	        {/*System.out.print(yytext());*/  return symbol(TpSymbol.EQ); }
"+"	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.PLUS); }
"-"	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.MINUS); }
"*"	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.MULT); }
"/"	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.DIV); }
";"	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.SEMIC); }
":"	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.COLON); }
"="	          {/*System.out.print(yytext());*/  return symbol(TpSymbol.AFF); }
"if"	        {/*System.out.print(yytext());*/  return symbol(TpSymbol.IF); }
"then"	      {/*System.out.print(yytext());*/  return symbol(TpSymbol.THEN); }
"else"	      {/*System.out.print(yytext());*/  return symbol(TpSymbol.ELSE); }
"while"	      {/*System.out.print(yytext());*/  return symbol(TpSymbol.WHILE); }
"do"	        {/*System.out.print(yytext());*/  return symbol(TpSymbol.DO); }
"var"	        {/*System.out.print(yytext());*/  return symbol(TpSymbol.VAR); }
"int"	        {/*System.out.print(yytext());*/  return symbol(TpSymbol.INT); }
"float"       {/*System.out.print(yytext());*/  return symbol(TpSymbol.FLOAT); }
"char"	      {/*System.out.print(yytext());*/  return symbol(TpSymbol.CHAR); }
"string"	    {/*System.out.print(yytext());*/  return symbol(TpSymbol.STRING); }
"array"	      {/*System.out.print(yytext());*/  return symbol(TpSymbol.ARRAY); }
"of"	        {/*System.out.print(yytext());*/  return symbol(TpSymbol.OF); }
"pointer"	    {/*System.out.print(yytext());*/  return symbol(TpSymbol.POINTER); }

/* -------------------------------------------------
	Variables, Entiers
   ------------------------------------------------- */

{Identifier}  {/*System.out.print(yytext());*/  return symbol(TpSymbol.IDENTIFIER, yytext()); }
{Floating}       {/*System.out.print(yytext());*/  return symbol(TpSymbol.FLOATING, yytext()); }
{Integer}     {/*System.out.print(yytext());*/  return symbol(TpSymbol.INTEGER, yytext()); }
{String}      {/*System.out.print(yytext());*/  return symbol(TpSymbol.STRINGEXP, yytext().substring(1, yytext().length()-1)); }

/* -------------------------------------------------
	Commentaires - Caracteres non pris en compte
   ------------------------------------------------- */
{WhiteSpace}  {/*System.out.print(yytext());*/  /* ignore */ }

/* -------------------------------------------------
	Autres signes
   ------------------------------------------------- */
.	            {/* ignore */ }
