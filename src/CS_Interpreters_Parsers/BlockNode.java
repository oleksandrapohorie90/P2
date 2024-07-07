package CS_Interpreters_Parsers;
import java.util.List;

public class BlockNode extends ASTNode{

    public List<ASTNode> statements;

    public BlockNode(List<ASTNode>statements) {
        this.statements = statements;
    }

    public void addStatement(ASTNode statement) {
        statements.add(statement);
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "Block: ");
        for (ASTNode statement : statements) {
            statement.print(indent + "  ");
        }
    }
}
