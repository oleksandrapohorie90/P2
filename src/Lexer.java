import LexerError.LexerError;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class Lexer implements Iterable<Lexer.Token> {
    private final String input;
    private final List<Demo.Token> tokens;
    private int current;

    public Lexer(String input, List<Lexer.Token> tokens) {
        this.input = input;
        this.tokens = new ArrayList<>();
        this.current = 0;
        tokenize();
    }

    @Override
    public Iterator<Token> iterator() {
        return null;
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
                    tokens.add(new Demo.Token(Demo.TokenType.ASSIGNMENT, "="));//its an ASSIGNMENT, so we need it
                    current++;
                    break;

                case '+':
                case '-':
                case '*':
                case '/':
                    tokens.add(new Demo.Token(Demo.TokenType.OPERATOR, Character.toString(ch)));//its an ASSIGNMENT, so we need it
                    current++;
                    break;
                case '"':
                    tokens.add(new Demo.Token(Demo.TokenType.STRING, readString()));//its an ASSIGNMENT, so we need it
                    current++;
                    break;
                case '%':
                    tokens.add(new Demo.Token(Demo.TokenType.REFERENCES, readReference()));//its an ASSIGNMENT, so we need it
                    //current++;
                    break;
                default:
                    if (isDigit(ch)) {
                        tokens.add(new Demo.Token(Demo.TokenType.NUMBER, readNumber()));
                    } else if (isAlpha(ch)) {
                        //we will have an identifier and lets read an identifier
                        //based on this identifier we will read and based on type:CONFIG,NUMBER etc, we will know
                        String identifier = readIdentifier();
                        tokens.add(new Demo.Token(deriveTokenType(identifier), identifier));
                    } else {
                        throw new LexerError("Unsupported character: " + ch);
                    }
            }
        }
    }

    //For output
    static class Token {
        final TokenType type;
        final String value;

        public Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }
        @Override
        public String toString() {
            return "Token{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    enum TokenType {
        //TOKEN IS 2 THINGS TYPE AND VALUE
        //class that has members defined right away, enumeration
        IDENTIFIER, ASSIGNMENT, NUMBER, OPERATOR, PUNCTUATION, STRING, PRINT
    }
}
