package scanner;
import static scanner.tokens.*;
%%
%class lexer
%type tokens

alpha=[a-zA-Z]
digit=[0-9]
decimal_literal={digit}({digit})*
char=({alpha})+
alpha_num={alpha}|{digit}
id={alpha}({alpha_num})*
hex_digit={digit}|[a-fA-F]
hex_literal=([0]+[x]+{hex_digit}({hex_digit})*)
int_literal={decimal_literal}|{hex_literal}
char_literal=([']+{char}+['])
string_literal=(["]+{char}*+["])
true=[t]+[r]+[u]+[e]
false=[f]+[a]+[l]+[s]+[e]
bool_literal={true}|{false}
literal=({int_literal}|{char_literal}|{bool_literal})

espacio=[ ,\t,\r,\n]+

%{
    public String lexeme;
%}
%%
int |
if |
else |
while {lexeme=yytext(); return RESERVADAS;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"=" {return ASIGNAR;}
"-=" {return RESASIG;}
"+=" {return SUMASIG;}
"+" {return SUMA;}
"-" {return RESTA;}
"*" {return MULTIPLICACION;}
"/" {return DIVISION;}
"%" {return MOD;}
"<" {return MENORQ;}
">" {return MAYORQ;}
"<=" {return MENORIGUALQ;}
">=" {return MAYORIGUALQ;}
"==" {return IGUAL;}
"!=" {return DIFERENTE;}
"&&" {return AND;}
"||" {return OR;}

"{" {return CORCHETEABIERTO;}
"}" {return CORCHETECERRADO;}
"[" {return LLAVEABIERTA;}
"]" {return LLAVECERRADA;}
"(" {return PARENABIERTO;}
")" {return PARENCERRADO;}
"[]" {return LLAVES;}
"!" {return NOT;}
";" {return PUNTOYCOMA;}
"," {return COMA;}
"." {return PUNTO;}

"class" {return CLASS;}
"program" {return PROGRAM;}
"field_decl" {return FIELD_DECL;}
"type" {return TYPE;}
"id" {return ID;}
"int_literal" {return INT_LITERAL;}
"method_decl" {return METHOD_DECL;}
"void" {return VOID;}
"block" {return BLOCK;}
"var_decl" {return VAR_DECL;}
"int" {return INT;}
"boolean" {return BOOLEAN;}
"statement" {return STATEMENT;}
"location" {return LOCATION;}
"assign_op" {return ASSIGN_OP;}
"expr" {return EXPR;}
"method_call" {return METHOD_CALL;}
"if" {return IF;}
"else" {return ELSE;}
"for" {return FOR;}
"return" {return RETURN;}
"break" {return BREAK;}
"continue" {return CONTINUE;}
"method_name" {return METHOD_NAME;}
"callout" {return CALLOUT;}
"callout_arg" {return CALLOUT_ARG;}
"literal" {return LITERAL;}
"bin_op" {return BIN_OP;}
"arith_op" {return ARITH_OP;}
"rel_op" {return REL_OP;}
"eq_op" {return EQ_OP;}
"cond_op" {return COND_OP;}
"char_literal" {return CHAR_LITERAL;}
"bool_literal" {return BOOL_LITERAL;}
"alpha" {return ALPHA;}
"alpha_num" {return ALPHA_NUM;}
"digit" {return DIGIT;}
"hex_digit" {return HEX_DIGIT;}
"decimal_literal" {return DECIMAL_LITERAL;}
"hex_literal" {return HEX_LITERAL;}
"true" {return TRUE;}
"false" {return FALSE;}
"char" {return CHAR;}
"string_literal" {return STRING_LITERAL;}


{alpha} {lexeme=yytext(); return ALPHA;}
{digit} {lexeme=yytext(); return DIGIT;}
{decimal_literal} {lexeme=yytext(); return DECIMAL_LITERAL;}
{char} {lexeme=yytext(); return CHAR;}
{alpha_num} {lexeme=yytext(); return ALPHA_NUM;}
{id} {lexeme=yytext(); return ID;}
{hex_digit} {lexeme=yytext(); return HEX_DIGIT;}
{hex_literal} {lexeme=yytext(); return HEX_LITERAL;}
{int_literal} {lexeme=yytext(); return INT_LITERAL;}
{char_literal} {lexeme=yytext(); return CHAR_LITERAL;}
{string_literal} {lexeme=yytext(); return STRING_LITERAL;}
{true} {lexeme=yytext(); return TRUE;}
{false} {lexeme=yytext(); return FALSE;}
{bool_literal} {lexeme=yytext(); return BOOL_LITERAL;}
{literal} {lexeme=yytext(); return LITERAL;}
 . {return ERROR;}
