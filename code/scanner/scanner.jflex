package org.tp;

import java_cup.runtime.*;
import java.io.*;

%%
%class Scanner
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

%%

/* -------------------------------------------------
	Separateurs Operateurs
   ------------------------------------------------- */

"("	          {/*System.out.print(yytext());*/  return symbol(MySymbol.LPAR); }
")"	          {/*System.out.print(yytext());*/  return symbol(MySymbol.RPAR); }
"{"	          {/*System.out.print(yytext());*/  return symbol(MySymbol.LBRACE); }
"}"	          {/*System.out.print(yytext());*/  return symbol(MySymbol.RBRACE); }
"["	          {/*System.out.print(yytext());*/  return symbol(MySymbol.LBRACKET); }
"]"	          {/*System.out.print(yytext());*/  return symbol(MySymbol.RBRACKET); }
"&&"  	      {/*System.out.print(yytext());*/  return symbol(MySymbol.AND); }
"||"  	      {/*System.out.print(yytext());*/  return symbol(MySymbol.OR); }
"<"	          {/*System.out.print(yytext());*/  return symbol(MySymbol.LT); }
">"	          {/*System.out.print(yytext());*/  return symbol(MySymbol.GT); }
"<="	        {/*System.out.print(yytext());*/  return symbol(MySymbol.LE); }
">="	        {/*System.out.print(yytext());*/  return symbol(MySymbol.GE); }
"!="	        {/*System.out.print(yytext());*/  return symbol(MySymbol.DIFF); }
":="	        {/*System.out.print(yytext());*/  return symbol(MySymbol.AFF); }
"+"	          {/*System.out.print(yytext());*/  return symbol(MySymbol.PLUS); }
"-"	          {/*System.out.print(yytext());*/  return symbol(MySymbol.MINUS); }
"*"	          {/*System.out.print(yytext());*/  return symbol(MySymbol.MULT); }
"/"	          {/*System.out.print(yytext());*/  return symbol(MySymbol.DIV); }
","           {/*System.out.print(yytext());*/  return symbol(MySymbol.COMMA); }
";"	          {/*System.out.print(yytext());*/  return symbol(MySymbol.SEMIC); }
":"	          {/*System.out.print(yytext());*/  return symbol(MySymbol.COLON); }
"="	          {/*System.out.print(yytext());*/  return symbol(MySymbol.EQ); }
"if"	        {/*System.out.print(yytext());*/  return symbol(MySymbol.IF); }
"then"	      {/*System.out.print(yytext());*/  return symbol(MySymbol.THEN); }
"else"	      {/*System.out.print(yytext());*/  return symbol(MySymbol.ELSE); }
"while"	      {/*System.out.print(yytext());*/  return symbol(MySymbol.WHILE); }
"do"	        {/*System.out.print(yytext());*/  return symbol(MySymbol.DO); }
"bool"        {/*System.out.print(yytext());*/  return symbol(MySymbol.BOOLEAN); }
"int"	        {/*System.out.print(yytext());*/  return symbol(MySymbol.INT); }
"float"       {/*System.out.print(yytext());*/  return symbol(MySymbol.FLOAT); }
"char"	      {/*System.out.print(yytext());*/  return symbol(MySymbol.CHAR); }
"string"	    {/*System.out.print(yytext());*/  return symbol(MySymbol.STRING); }
"array"	      {/*System.out.print(yytext());*/  return symbol(MySymbol.ARRAY); }
"of"	        {/*System.out.print(yytext());*/  return symbol(MySymbol.OF); }
"pointer"	    {/*System.out.print(yytext());*/  return symbol(MySymbol.POINTER); }
"True"     {/*System.out.print(yytext());*/  return symbol(MySymbol.TRUE); }
"False"     {/*System.out.print(yytext());*/  return symbol(MySymbol.FALSE); }

/* -------------------------------------------------
	Variables, Entiers
   ------------------------------------------------- */

{Identifier}  {/*System.out.print(yytext());*/  return symbol(MySymbol.IDENTIFIER, yytext()); }
{Floating}    {/*System.out.print(yytext());*/  return symbol(MySymbol.FLOATING, yytext()); }
{Integer}     {/*System.out.print(yytext());*/  return symbol(MySymbol.INTEGER, yytext()); }
{String}      {/*System.out.print(yytext());*/  return symbol(MySymbol.STRINGEXP, yytext().substring(1, yytext().length()-1)); }
{Char}        {/*System.out.print(yytext());*/  return symbol(MySymbol.CHAREXP, yytext().substring(1, yytext().length()-1)); }

/* -------------------------------------------------
	Commentaires - Caracteres non pris en compte
   ------------------------------------------------- */
{WhiteSpace}  {/*System.out.print(yytext());*/  /* ignore */ }

/* -------------------------------------------------
	Autres signes
   ------------------------------------------------- */
.	            {/* ignore */ }
