package CS_Interpreters_Parsers;

import java.util.ArrayList;
import java.util.List;

public class BlockNode extends ASTNode{

    private List<ASTNode> statements;

    public BlockNode() {
        this.statements = new ArrayList<>();
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
