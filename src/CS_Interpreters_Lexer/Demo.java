//package CS_Interpreters_Lexer;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//
//public class Demo implements Iterable<Demo.Token> {
//
//    //lexer will be iterating through, we give string as input as string and output as tokens
//    //configuration code, can be file too
//    //COMPUTE_CONFIG_STRING
//    private final String input;
//    private final List<Token> tokens;
//    private int current;
//
//    public Demo(String inpout) {
//        this.input = inpout;
//        this.tokens = new ArrayList<Token>();
//        this.current = 0;
//        tokenize();
//    }
//
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
//                    tokens.add(new Token(TokenType.ASSIGNMENT, "="));//its an ASSIGNMENT, so we need it
//                    current++;
//                    break;
//
//                case '+':
//                case '-':
//                case '*':
//                case '/':
//                    tokens.add(new Token(TokenType.OPERATOR, Character.toString(ch)));//its an ASSIGNMENT, so we need it
//                    current++;
//                    break;
//                case '"':
//                    tokens.add(new Token(TokenType.STRING, readString()));//its an ASSIGNMENT, so we need it
//                    current++;
//                    break;
//                case '%':
//                    tokens.add(new Token(TokenType.REFERENCES, readReference()));//its an ASSIGNMENT, so we need it
//                    //current++;
//                    break;
//                default:
//                    if (isDigit(ch)) {
//                        tokens.add(new Token(TokenType.NUMBER, readNumber()));
//                    } else if (isAlpha(ch)) {
//                        //we will have an identifier and lets read an identifier
//                        //based on this identifier we will read and based on type:CONFIG,NUMBER etc, we will know
//                        String identifier = readIdentifier();
//                        tokens.add(new Token(deriveTokenType(identifier), identifier));
//                    } else {
//                        throw new LexerError("Unsupported character: " + ch);
//                    }
//            }
//        }
//    }
//
//    //CONFIG is definition and configs
//    private static TokenType deriveTokenType(String identifier) {
//        return switch (identifier) {
//            case "config" -> TokenType.CONFIG;
//            case "update" -> TokenType.UPDATE;
//            case "compute" -> TokenType.COMPUTE;
//            case "show" -> TokenType.SHOW;
//            case "configs" -> TokenType.CONFIGS;
//            default -> TokenType.IDENTIFIER;
//        };
//    }
//
//    private String readIdentifier() {
//        //read while it is Alphanumeric, continue reading
//        StringBuilder stringBuilder = new StringBuilder();
//        while (current < input.length() && isAlphaNumeric(input.charAt(current))) {
//            stringBuilder.append(input.charAt(current));
//            current++;
//        }
//        return stringBuilder.toString();
//    }
//
//    private String readNumber() {
//        StringBuilder stringBuilder = new StringBuilder();
//        while (current < input.length() && isDigit(input.charAt(current))) {
//            stringBuilder.append(input.charAt(current));
//            current++;
//        }
//        return stringBuilder.toString();
//    }
//
//    private String readReference() {
//        StringBuilder builder = new StringBuilder();
//        current++;
//        while (current < input.length() && isAlphaNumeric(input.charAt(current))) {
//            builder.append(input.charAt(current));
//            current++;
//        }
//        return builder.toString();
//    }
//
//    private boolean isAlphaNumeric(char c) {
//        return isAlpha(c) || isDigit(c);
//    }
//
//    private static boolean isDigit(char c) {
//        return '0' <= c && c <= '9';
//    }
//
//    private static boolean isAlpha(char c) {
//        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == '_';
//    }
//
//    private String readString() {
//        //we know how to read a str that starts with "
//        StringBuilder builder = new StringBuilder();
//        current++;
//        while (current < input.length() && input.charAt(current) != '"') {
//            builder.append(input.charAt(current));
//            current++;
//        }
//        return builder.toString();
//    }
//
//    @Override
//    public Iterator<Token> iterator() {
//        return tokens.iterator();
//    }
//
//    static class Token {
//        final TokenType type;
//        final String value;
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
//    }
//
//    enum TokenType {
//        //TOKEN IS 2 THINGS TYPE AND VALUE
//        //class that has members defined right away, enumeration
//        CONFIG, ASSIGNMENT, OPERATOR, UPDATE, COMPUTE, SHOW, CONFIGS, STRING, NUMBER, IDENTIFIER, REFERENCES, //FOR % SMTH
//    }
//
//
//}
