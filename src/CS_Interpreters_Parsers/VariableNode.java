package CS_Interpreters_Parsers;

public class VariableNode extends ASTNode {
    public Token variableToken;
    public String name;

    public VariableNode(Token variableToken) {
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
