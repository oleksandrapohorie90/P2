package CS_Interpreters_Parsers;

import CS_Interpreters_Lexer.LexerError;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static CS_Interpreters_Parsers.Token.Type.*;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetterOrDigit;

public class Lexer implements Iterable<Token> {

    //public static List<Token> getHardcodedTokens() {
//        return Arrays.asList(
//                new Token(Token.Type.IDENTIFIER, "x"),
//                new Token(Token.Type.EQUAL, "="),
//                new Token(Token.Type.NUMBER, "3"),
//                new Token(Token.Type.PLUS, "+"),
//                new Token(Token.Type.NUMBER, "5"),
//                new Token(Token.Type.MULTIPLY, "*"),
//                new Token(Token.Type.LPAREN, "("),
//                new Token(Token.Type.NUMBER, "10"),
//                new Token(Token.Type.MINUS, "-"),
//                new Token(Token.Type.NUMBER, "4"),
//                new Token(Token.Type.RPAREN, ")"),
//                new Token(Token.Type.SEMICOLON, ";"),
//                new Token(Token.Type.IF, "if"),
//                new Token(Token.Type.LPAREN, "("),
//                new Token(Token.Type.IDENTIFIER, "x"),
//                new Token(Token.Type.GREATER, ">"),
//                new Token(Token.Type.NUMBER, "3"),
//                new Token(Token.Type.RPAREN, ")"),
//                new Token(Token.Type.THEN, "then"),
//                new Token(Token.Type.LPAREN, "("),
//                new Token(Token.Type.IDENTIFIER, "y"),
//                new Token(Token.Type.EQUAL, "="),
//                new Token(Token.Type.IDENTIFIER, "x"),
//                new Token(Token.Type.PLUS, "+"),
//                new Token(Token.Type.NUMBER, "2"),
//                new Token(Token.Type.SEMICOLON, ";"),
//                new Token(Token.Type.RPAREN, ")"),
//                new Token(Token.Type.ELSE, "else"),
//                new Token(Token.Type.LPAREN, "("),
//                new Token(Token.Type.IDENTIFIER, "y"),
//                new Token(Token.Type.EQUAL, "="),
//                new Token(Token.Type.IDENTIFIER, "x"),
//                new Token(Token.Type.MULTIPLY, "*"),
//                new Token(Token.Type.LPAREN, "("),
//                new Token(Token.Type.NUMBER, "2"),
//                new Token(Token.Type.PLUS, "+"),
//                new Token(Token.Type.NUMBER, "3"),
//                new Token(Token.Type.RPAREN, ")"),
//                new Token(Token.Type.SEMICOLON, ";"),
//                new Token(Token.Type.RPAREN, ")"),
//                new Token(Token.Type.PRINT, "print"),
//                new Token(Token.Type.IDENTIFIER, "y"),
//                new Token(Token.Type.SEMICOLON, ";")
//        );
    public final String input;
    public final List<Token> tokens;
    public int current;

    public Lexer(String input) {
        this.input = input;
        this.tokens = new ArrayList<>();
        this.current = 0;
        tokenize();
    }

    public void tokenize() {
        //anything that is in our BNF form
        //word config, update,compute
        //to go over each element and see what are we reading
        while (current < input.length()) {
            char ch = input.charAt(current);
            switch (ch) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    current++;
                    break;
                case '=':
                    if (current + 1 < input.length() && input.charAt(current + 1) == '=') {
                        tokens.add(new Token(ROPERATOR, "=="));
                        current += 2;
                        break;
                    } else {
                        tokens.add(new Token(ASSIGN, "="));
                        current++;
                    }
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    tokens.add(new Token(OPERATOR, Character.toString(ch)));
                    current++;
                    break;
                case '(':
                    tokens.add(new Token(LPAREN, "("));
                    current++;
                    break;
                case ')':
                    tokens.add(new Token(RPAREN, ")"));
                    current++;
                    break;
                case '{':
                    tokens.add(new Token(LBRACE, "{"));
                    current++;
                    break;
                case '}':
                    tokens.add(new Token(RBRACE, "}"));
                    current++;
                    break;
                case ';':
                    tokens.add(new Token(SEMICOLON, ";"));
                    current++;
                    break;
                case '"':
                    tokens.add(new Token(STRING, readString()));
                    current++;
                    break;
                case '>':
                case '<':
                case '!':
                    if (current + 1 < input.length() && input.charAt(current + 1) == '=') {
                        tokens.add(new Token(ROPERATOR, ch + "="));
                        current += 2;
                    } else {
                        tokens.add(new Token(ROPERATOR, Character.toString(ch)));
                        current++;
                    }
                    break;
                default:
                    if (isDigit(ch)) {
                        tokens.add(new Token(NUMBER, readNumber()));
                    } else if (isAlpha(ch)) {
                        String identifier = readIdentifier();
                        tokens.add(new Token(deriveTokenType(identifier),identifier));
                    } else {
                        throw new LexerError("Unsupported character: " + ch);
                    }
            }
        }
    }

    private String readNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        while (current < input.length() && isDigit(input.charAt(current))) {
            stringBuilder.append(input.charAt(current));
            current++;
        }
        return stringBuilder.toString();
    }

    private String readIdentifier() {
        //read while it is Alphanumeric, continue reading
        StringBuilder stringBuilder = new StringBuilder();
        while (current < input.length() && isLetterOrDigit(input.charAt(current))) {
            stringBuilder.append(input.charAt(current));
            current++;
        }
        return stringBuilder.toString();
    }

    private String readString() {
        //we know how to read a str that starts with "
        StringBuilder stringBuilder = new StringBuilder();
        current++;
        while (current < input.length() && input.charAt(current) != '"') {
            stringBuilder.append(input.charAt(current));
            current++;
        }
        return stringBuilder.toString();
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private boolean isAlpha(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == '_';
    }

    private boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private Token.Type deriveTokenType(String identifier) {
        return switch (identifier) {
            case "config" -> CONFIG;
            case "update" -> UPDATE;
            case "compute" -> COMPUTE;
            case "show" -> SHOW;
            case "configs" -> CONFIGS;
            case "var" -> VAR;
            case "if" -> IF;
            case "else" -> ELSE;
            case "print" -> PRINT;
            default -> IDENTIFIER;
        };
    }

    @Override
    public Iterator<Token> iterator() {
        return tokens.iterator();
    }

    public List<Token> getTokens() {
        return tokens;
    }
}


