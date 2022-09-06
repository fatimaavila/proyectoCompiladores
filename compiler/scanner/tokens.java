/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

/**
 *
 * @author loren
 */
public enum tokens {
    RESERVADAS,
    ASIGNAR,
    RESASIG,
    SUMASIG,
    SUMA,
    RESTA,
    MULTIPLICACION,
    DIVISION,
    MOD,
    MENORQ,
    MAYORQ,
    MENORIGUALQ,
    MAYORIGUALQ,
    IGUAL,
    DIFERENTE,
    AND,
    OR,
    
    CORCHETEABIERTO,
    CORCHETECERRADO,
    LLAVEABIERTA,
    LLAVECERRADA,
    PARENABIERTO,
    PARENCERRADO,
    LLAVES,
    NOT,
    PUNTOYCOMA,
    COMA,
    PUNTO,
    
    CLASS,
    PROGRAM,
    FIELD_DECL,
    TYPE,
    ID,
    INT_LITERAL,
    METHOD_DECL,
    VOID,
    BLOCK,
    VAR_DECL,
    INT,
    BOOLEAN,
    STATEMENT,
    LOCATION,
    ASSIGN_OP,
    EXPR,
    METHOD_CALL,
    IF,
    ELSE,
    FOR,
    RETURN,
    BREAK,
    CONTINUE,
    METHOD_NAME,
    CALLOUT,
    CALLOUT_ARG,
    LITERAL,
    BIN_OP,
    ARITH_OP,
    REL_OP,
    EQ_OP,
    COND_OP,
    CHAR_LITERAL,
    BOOL_LITERAL,
    ALPHA,
    ALPHA_NUM,
    DIGIT,
    HEX_DIGIT,
    DECIMAL_LITERAL,
    HEX_LITERAL,
    TRUE,
    FALSE,
    CHAR,
    STRING_LITERAL,
    
    ERROR
}
