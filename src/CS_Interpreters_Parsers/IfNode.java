package CS_Interpreters_Parsers;

public class IfNode extends ASTNode{
    public ASTNode value;
    public ASTNode thenBranch;
    public ASTNode elseBranch;

    public IfNode(ASTNode value, ASTNode thenBranch, ASTNode elseBranch) {
        this.value = value;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "If Statement: ");
        System.out.println(indent + "  Condition: ");
        value.print(indent + "    ");
        System.out.println(indent + "  Then Block");
        thenBranch.print(indent + "    ");
        if (elseBranch != null) {
            System.out.println(indent + "  Else Block");
            elseBranch.print(indent + "    ");
        }
    }
}
