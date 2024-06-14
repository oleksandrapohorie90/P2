import java.util.List;


public class Lexer {

    //lexer will be iterating through, we give string as input as string and output as tokens
    //configuration code, can be file too

    private final String inpout;
    private final List<Token> tokens;
    private int current;

    public Lexer(String inpout, List<Token> tokens) {
        this.inpout = inpout;
        this.tokens = tokens;
        this.current = 0;
    }

    private void tokenize() {
        //anything that is in our BNF form
        //word config, update,compute
        //to go over each element and see what are we reading
        while (current < inpout.length()) {
            char ch = inpout.charAt(current);
            switch (ch) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    current++;
                    break;
                case '=':
                    tokens.add(new Token(TokenType.ASSIGNMENT, "="));//its an ASSIGNMENT, so we need it
                    current++;
                    break;

                case '+':
                case '-':
                case '*':
                case '/':
                    tokens.add(new Token(TokenType.OPERATOR, Character.toString(ch)));//its an ASSIGNMENT, so we need it
                    current++;
                    break;
                case '"':
                    tokens.add(new Token(TokenType.STRING, readString()));//its an ASSIGNMENT, so we need it
                    current++;
                    break;
                case '%':
                    tokens.add(new Token(TokenType.REFERENCES, readReference()));//its an ASSIGNMENT, so we need it
                    current++;
                    break;
            }
        }
    }

    private String readReference() {
        StringBuilder builder = new StringBuilder();
        current++;
        while(current< inpout.length() && isAlphaNumeric()){
            builder.append(inpout.charAt(current));
            current++;
        }
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private static boolean isDigit(char c) {
        return isDigit(c);
    }

    private static boolean isAlpha(char c) {
        return ('a' <= c && c <='z') ||('A' <= c && c <='Z');
    }

    private String readString()
{
    //we know how to read a str that starts with "
    StringBuilder builder = new StringBuilder();
    current++;
    while(current< inpout.length() && inpout.charAt(current)!='"'){
        builder.append(inpout.charAt(current));
        current++;
    }
    return  builder.toString();
}
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
        CONFIG, ASSIGNMENT, OPERATOR, UPDATE, COMPUTE, SHOW, CONFIGS, STRING, NUMBER, IDENTIFIER, REFERENCES, //FOR % SMTH
    }


}
