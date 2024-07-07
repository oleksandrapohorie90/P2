package CS_Interpreters_Parsers;

public class Var extends ASTNode {
    public Token variableToken;
    public String name;

    public Var(Token variableToken) {
        this.variableToken = variableToken;
        this.name = variableToken.value;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "VariableNode{" +
                name +
                '}');
    }
}
