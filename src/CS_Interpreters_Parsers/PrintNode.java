package CS_Interpreters_Parsers;

public class PrintNode extends ASTNode{
    public ASTNode value;

    public PrintNode(ASTNode value) {
        this.value = value;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent+ "Print: ");
        value.print(indent + " ");
    }
}
