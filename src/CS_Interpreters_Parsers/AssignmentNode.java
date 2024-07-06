package CS_Interpreters_Parsers;

public class AssignmentNode extends ASTNode{
    public String left;
    public ASTNode right;

    public AssignmentNode(String left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print(String indent) {

    }
}
