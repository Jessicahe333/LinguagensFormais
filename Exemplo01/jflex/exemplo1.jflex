/**
 * Analisador léxico para expressões simples
 */
package exemplo1;
import java.util.Hashtable;

%%

%class Lexer
%unicode
%debug
%standalone
%line
%column
%type Token

%eofval{ 
return new Token(Tag.EOF);
%eofval}

%eof{
System.out.println("Análise léxica terminada com sucesso!");
%eof}

%{ 
// Declarar uma tabele de palavras reservadas
private Hashtable<String, Token> reservedWords
    = new Hashtable();
%}

%init{
    //Inicializar o hashtable com as palavras reservadas
    reservedWords.put("if", new Token(Tag.IF));
    reservedWords.put("else", new Token(Tag.ELSE));
    reservedWords.put("then", new Token(Tag.THEN));
%init}
delim	= [\ \t\n]
ws		= {delim}+
letter	= [A-Za-z]
digit	= [0-9]
id		= {letter}({letter}|{digit})*
number	= {digit}+(\.{digit}+)?(E[+-]?{digit}+)?

%%
/* Regras e ações */
{ws}		{ /* ignorar */ }
{id}		{ 
                        // Se o lexema for palavra reservadas
                        Token t = reservedWords.get(yytext());
                        if(t != null){
                            // Retornar o token da palavra reservada
                            System.out.println("RW: " + t);
                            return t;
                        }
                        else{
                            // Retornar apenas um identificador
                            t =  new Word(Tag.ID, yytext());
                            System.out.println("ID: " + t);
                            return t;
                        }
}



{number}	{ return new Num(Double.parseDouble(yytext())); }
"<"			{ return new Token(Tag.RELOP);}
"<="		{ return new Token(Tag.RELOP);}
"="			{ return new Token(Tag.RELOP);}
"<>"		{ return new Token(Tag.RELOP);}
">"			{ return new Token(Tag.RELOP);}
">="		{ return new Token(Tag.RELOP);}
/* Qualquer outro - gerar erro */
.		{ throw new Error("Illegal <" + yytext() + "(" + (int)(yytext().charAt(0)) + ")" + ">"); }
