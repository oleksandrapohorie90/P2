package CS_Interpreters_Parsers;

public class Vardecl extends ASTNode {
    public final Var var;
    public ASTNode expression;


    public Vardecl(Var varNode, ASTNode expression) {
        this.var = varNode;
        this.expression = expression;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "Vardecl{" + var.name + '}');
        expression.print(indent + indent);
    }
}
