package CS_Interpreters_Parsers;

public class AssignmentNode extends ASTNode{
    public Var left;
    public ASTNode right;

    public AssignmentNode(Var left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print(String indent) {

    }
}
