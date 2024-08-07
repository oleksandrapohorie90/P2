//package CS_Interpreters_Lexer;
//
//import java.util.List;
//import java.util.Iterator;
//import java.util.ArrayList;
//
//public class Lexer implements Iterable<Lexer.Token> {
//    public final String input;
//    public final List<Lexer.Token> tokens;
//    public int current;
//
//    public Lexer(String input) {
//        this.input = input;
//        this.tokens = new ArrayList<>();
//        this.current = 0;
//        tokenize();
//    }
//    public List<Token> getTokens(){
//        return tokens;
//    }
//    public void tokenize() {
//        //anything that is in our BNF form
//        //word config, update,compute
//        //to go over each element and see what are we reading
//        while (current < input.length()) {
//            char ch = input.charAt(current);
//            switch (ch) {
//                case ' ':
//                case '\t':
//                case '\n':
//                case '\r':
//                    current++;
//                    break;
//                case '=':
//                    tokens.add(new Token(TokenType.ASSIGNMENT, "="));
//                    current++;
//                    break;
//                case '+':
//                case '-':
//                case '*':
//                case '/':
//                case '>':
//                case '<':
//                    tokens.add(new Token(TokenType.OPERATOR, Character.toString(ch)));
//                    current++;
//                    break;
//                case '(':
//                case ')':
//                case '{':
//                case '}':
//                case ';':
//                    tokens.add(new Token(TokenType.PUNCTUATION, Character.toString(ch)));
//                    current++;
//                    break;
//                case '"':
//                    tokens.add(new Token(TokenType.STRING, readString()));
//                    current++;
//                    break;
//                default:
//                    if (Character.isDigit(ch)) {
//                        tokens.add(new Token(TokenType.NUMBER, readNumber()));
//                    } else if (ch == 'p' && input.startsWith("print", current)) {
//                        tokens.add(new Token(TokenType.PRINT, "print"));
//                        current += 5;
//                    } else if (Character.isLetter(ch)) {
//                        tokens.add(new Token(TokenType.IDENTIFIER, readIdentifier()));
//                    }  else {
//                        throw new LexerError("Unsupported character: " + ch);
//                    }
//            }
//        }
//    }
//
//    private String readNumber() {
//        StringBuilder stringBuilder = new StringBuilder();
//        while (current < input.length() && Character.isDigit(input.charAt(current))) {
//            stringBuilder.append(input.charAt(current));
//            current++;
//        }
//        return stringBuilder.toString();
//    }
//
//    private String readIdentifier() {
//        //read while it is Alphanumeric, continue reading
//        StringBuilder stringBuilder = new StringBuilder();
//        while (current < input.length() && (Character.isLetterOrDigit(input.charAt(current)) || input.charAt(current) == '_')) {
//            stringBuilder.append(input.charAt(current));
//            current++;
//        }
//        return stringBuilder.toString();
//    }
//
//    private String readString() {
//        //we know how to read a str that starts with "
//        StringBuilder stringBuilder = new StringBuilder();
//        current++;
//        while (current < input.length() && input.charAt(current) != '"') {
//            stringBuilder.append(input.charAt(current));
//            current++;
//        }
//        return stringBuilder.toString();
//    }
//
//    //interface override rules
//    @Override
//    public Iterator<Token> iterator() {
//        return tokens.iterator();
//    }
//
//    //For output
//    public static class Token {
//        public final TokenType type;
//        public final String value;
//
//        public Token(TokenType type, String value) {
//            this.type = type;
//            this.value = value;
//        }
//
//        @Override
//        public String toString() {
//            return "Token{" +
//                    "type=" + type +
//                    ", value='" + value + '\'' +
//                    '}';
//        }
//
//    }
//
//    enum TokenType {
//        //TOKEN IS 2 THINGS TYPE AND VALUE
//        //class that has members defined right away, enumeration
//        IDENTIFIER, ASSIGNMENT, NUMBER, OPERATOR, PUNCTUATION, STRING, PRINT
//    }
//}
