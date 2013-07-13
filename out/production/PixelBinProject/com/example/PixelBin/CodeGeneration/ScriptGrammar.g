grammar ScriptGrammar;

// to generate Java source: java -cp /path/to/antlr.jar org.antlr.Tool ScriptGrammar.g

options {
    output=AST;
    ASTLabelType=CommonTree; // type of $stat.tree ref etc...
    backtrack = false;
}

tokens {
    CALL;
    NEG;
    BLOCK;
}

@header  {
package com.google.imageplayground.parser;
}
@lexer::header  {
package com.google.imageplayground.parser;
}

prog:   (block)+ ;

block: '{' NEWLINE block* '}' -> ^(BLOCK block*)
    |  (stat NEWLINE) -> stat
    |  NEWLINE ->
    ;

// like block, but without newline. Used by bodies of if/for/while statements
// because the top-level stat should consume the newline.
statblock: ('{') => block
    |      stat
    ;

stat:   expr
    |   assign
    |   retexp
    |   ifexp
    |   whileexp
    |   forexp
    ;
    
assign: ID '=' expr -> ^('=' ID expr)
    ;
    
retexp: 'return' expr -> ^('return' expr)
    ;
    
expr:   multExpr (('+'^|'-'^|'&'^|'|'^|'>>'^|'<<'^|'<<<'^) multExpr)*
    ; 

multExpr
    :   unaryExp (('*'^|'/'^|'%'^) unaryExp)*
    ; 
    
unaryExp
    :   '-' atom -> ^(NEG atom)
    |   '~' atom -> ^('~' atom)
    |   atom
    ;
    
atom:   INT 
    |   ID
    |   '(' expr ')' -> expr
    |   funcall
    ;

funcall: ID '(' expr? (',' expr)* ')' -> ^(CALL ID expr*)
    ;

ifexp:  (ifelseexp) => ifelseexp
    |    'if' '('? boolexp ')'? statblock -> ^('if' boolexp statblock)
    ;
    
ifelseexp: 'if' '('? boolexp ')'? statblock NEWLINE* 'else' statblock -> ^('if' boolexp statblock*)
    ;
    
whileexp:  'while' '('? boolexp ')'? statblock -> ^('while' boolexp statblock)
    ;
    
boolexp:  boolterm ('=='^|'!='^|'>'^|'>='^|'<'^|'<='^) boolterm
    ;

boolterm: (ID|INT)
    ;
    
forexp: 'for' '('? ID (',' forterm)* ')'? statblock -> ^('for' ID forterm* statblock)
    ;
    
forterm: (ID|INT)
    ;
    
ID  :   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9')* ;
INT :   '0'..'9'+ ;
NEWLINE:'\r'? '\n' ;
WS  :   (' '|'\t')+ {skip();} ;