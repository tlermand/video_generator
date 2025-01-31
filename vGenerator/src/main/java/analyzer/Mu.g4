grammar Mu;

parse
 : block EOF
 ;

block
 : stat*
 ;

stat
 : definition
 | declaration
 | assignment
 | method
 | status
 | if_stat
 | while_stat
 | log
 | commemnt
 | OTHER {System.err.println("unknown char: " + $OTHER.text);}
 ;

definition
 : TYPE (STATE | LIST) ASSIGN (OBRACE|OSQBRA) expr+ (COMMA expr)* (CBRACE|CSQBRA)
 ;

declaration
 : ID COLON TYPE (STATE | LIST);

exprs
 : expr (COMMA expr)*
 ;

assignment
 : ID ASSIGN ((OSQBRA* expr (COMMA expr)* CSQBRA*) | method )
 ;

method
 : (ID PERIOD)* ID OPAR expr* CPAR (COLON list*)*
 ;

list
 : INT PERIOD expr
 ;

status
 : ID exprs+ ID
 ;

commemnt
 : COMMENT
 ;

if_stat
 : IF condition_block (ELSE IF condition_block)* (ELSE stat_block)?
 ;

condition_block
 : expr stat_block
 ;

stat_block
 : OBRACE block CBRACE
 | stat
 ;

while_stat
 : WHILE expr stat_block
 ;

log
 : LOG expr SCOL
 ;

expr
 : <assoc=right>expr POW expr           #powExpr
 | P MINUS GT expr                      #pointerExpr
 | MINUS expr                           #unaryMinusExpr
 | NOT expr                             #notExpr
 | expr op=(MULT | DIV | MOD) expr      #multiplicationExpr
 | expr op=(PLUS | MINUS) expr          #additiveExpr
 | expr op=(LTEQ | GTEQ | LT | GT) expr #relationalExpr
 | expr op=(EQ | NEQ) expr              #equalityExpr
 | expr AND expr                        #andExpr
 | expr OR expr                         #orExpr
 | atom                                 #atomExpr
 ;

atom
 : OBRACE exprs* CBRACE #braExpr
 | OSQBRA exprs* CSQBRA #sqbraExpr
 | (INT | FLOAT)        #numberAtom
 | (TRUE | FALSE)       #booleanAtom
 | ID                   #idAtom
 | STRING               #stringAtom
 | STATE                #stateAtom
 | NIL                  #nilAtom
 ;

TYPE : [Tt][Yy][Pp][Ee];
STATE : 'STATE';
LIST : 'LIST';
COLON : ':';
COMMA : ',';
PERIOD : '.';

OR : '||';
AND : '&&';
EQ : '==';
NEQ : '!=';
GT : '>';
LT : '<';
GTEQ : '>=';
LTEQ : '<=';
PLUS : '+';
MINUS : '-';
MULT : '*';
DIV : '/';
MOD : '%';
POW : '^';
NOT : '!';
P : 'p';

SCOL : ';';
ASSIGN : '=';
OPAR : '(';
CPAR : ')';
OBRACE : '{';
CBRACE : '}';
OSQBRA : '[';
CSQBRA : ']';

TRUE : 'true';
FALSE : 'false';
NIL : 'nil';
IF : 'if';
ELSE : 'else';
WHILE : 'while';
LOG : 'log';

ID
 : [a-zA-Z_] [a-zA-Z_0-9]*
 ;

INT
 : [0-9]+
 ;

FLOAT
 : [0-9]+ '.' [0-9]+ 
 | '.' [0-9]+
 ;

STRING
 : '"' (~["\r\n] | '""')* '"'
 ;

COMMENT
 : '//' ~[\r\n]*
 ;

SPACE
 : [ \t\r\n] -> skip
 ;

OTHER
 : . 
 ;
