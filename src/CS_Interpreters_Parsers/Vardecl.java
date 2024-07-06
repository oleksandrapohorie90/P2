package CS_Interpreters_Parsers;

public class Vardecl extends ASTNode {
    public final VariableNode varNode;
    ASTNode expression;


    public Vardecl(VariableNode varNode, ASTNode expression) {
        this.varNode = varNode;
        this.expression = expression;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "Vardecl{" + varNode.name + '}');
        expression.print(indent + indent);
    }
}
