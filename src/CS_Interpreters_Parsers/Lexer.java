package CS_Interpreters_Parsers;

import java.util.Arrays;
import java.util.List;

public class Lexer {

    public static List<Token> getHardcodedTokens() {
        return Arrays.asList(
                new Token(Token.Type.IDENTIFIER, "x"),
                new Token(Token.Type.EQUAL, "="),
                new Token(Token.Type.NUMBER, "3"),
                new Token(Token.Type.PLUS, "+"),
                new Token(Token.Type.NUMBER, "5"),
                new Token(Token.Type.MULTIPLY, "*"),
                new Token(Token.Type.LPAREN, "("),
                new Token(Token.Type.NUMBER, "10"),
                new Token(Token.Type.MINUS, "-"),
                new Token(Token.Type.NUMBER, "4"),
                new Token(Token.Type.RPAREN, ")"),
                new Token(Token.Type.SEMICOLON, ";"),
                new Token(Token.Type.IF, "if"),
                new Token(Token.Type.LPAREN, "("),
                new Token(Token.Type.IDENTIFIER, "x"),
                new Token(Token.Type.GREATER, ">"),
                new Token(Token.Type.NUMBER, "3"),
                new Token(Token.Type.RPAREN, ")"),
                new Token(Token.Type.THEN, "then"),
                new Token(Token.Type.LPAREN, "("),
                new Token(Token.Type.IDENTIFIER, "y"),
                new Token(Token.Type.EQUAL, "="),
                new Token(Token.Type.IDENTIFIER, "x"),
                new Token(Token.Type.PLUS, "+"),
                new Token(Token.Type.NUMBER, "2"),
                new Token(Token.Type.SEMICOLON, ";"),
                new Token(Token.Type.RPAREN, ")"),
                new Token(Token.Type.ELSE, "else"),
                new Token(Token.Type.LPAREN, "("),
                new Token(Token.Type.IDENTIFIER, "y"),
                new Token(Token.Type.EQUAL, "="),
                new Token(Token.Type.IDENTIFIER, "x"),
                new Token(Token.Type.MULTIPLY, "*"),
                new Token(Token.Type.LPAREN, "("),
                new Token(Token.Type.NUMBER, "2"),
                new Token(Token.Type.PLUS, "+"),
                new Token(Token.Type.NUMBER, "3"),
                new Token(Token.Type.RPAREN, ")"),
                new Token(Token.Type.SEMICOLON, ";"),
                new Token(Token.Type.RPAREN, ")"),
                new Token(Token.Type.PRINT, "print"),
                new Token(Token.Type.IDENTIFIER, "y"),
                new Token(Token.Type.SEMICOLON, ";")
        );

    }

}
