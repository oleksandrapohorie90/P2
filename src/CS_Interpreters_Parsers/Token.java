package CS_Interpreters_Parsers;

public class Token {

    public enum Type{
        NUMBER, PLUS, MINUS, MULTIPLY, DIVIDE, LPAREN, RPAREN, IDENTIFIER, EQUAL, SEMICOLON, IF, THEN, ELSE, PRINT, GREATER
    }

    public final Type type;

    public final String value;

    public Token (Type type, String value){
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
