package CS_Interpreters_Parsers;

public class BinaryOpNode extends ASTNode{
        //left node
        ASTNode left; // can be another binary operation
        //right node
        ASTNode right; // can be another binary operation
        Token operationToken;

        public BinaryOpNode(ASTNode left, ASTNode right, Token operationToken) {
            this.left = left;
            this.right = right;
            this.operationToken = operationToken;
        }

    public void print(String indent){
        System.out.println(indent + "BinaryOp{" + operationToken.value + "}");
        if (left != null) {
            left.print(indent + "  ");
        } else {
            System.out.println(indent + "  null");
        }
        if (right != null) {
            right.print(indent + "  ");
        } else {
            System.out.println(indent + "  null");
        }
    }
}

