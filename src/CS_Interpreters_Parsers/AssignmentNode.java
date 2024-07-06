package CS_Interpreters_Parsers;

public class AssignmentNode extends ASTNode{
    public String variable;
    public ASTNode value;

    public AssignmentNode(String variable, ASTNode value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent+ "Assignment: " +
                variable + " = ");
        value.print(indent + " ");
    }
}
