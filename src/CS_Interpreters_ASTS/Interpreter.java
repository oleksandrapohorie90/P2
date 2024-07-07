package CS_Interpreters_ASTS;

import CS_Interpreters_Parsers.*;

import java.util.HashMap;
import java.util.Map;

public class Interpreter {
    private final Map<String, Integer> valueTable = new HashMap<>();

    /*
    var x = {
    var y = 5
    y - 5
    }
     */
    //x is 0
    //our program will return the execution of the last statement
    public int visit(ASTNode node) throws ParserException {

        if (node instanceof BinaryOpNode binaryOpNode) {
            int left = visit(binaryOpNode.left);
            int right = visit(binaryOpNode.right);

            //depending on the token type we will do an operation we need
            switch (binaryOpNode.operationToken.type) {
                case MINUS -> {
                    return left - right;
                }
                case PLUS, OPERATOR -> {
                    return left + right;
                }
                case DIVIDE -> {
                    return left / right;
                }
                case MULTIPLY -> {
                    return left * right;
                }
                default -> throw new ParserException("Unexpected token: " + binaryOpNode.operationToken);
            }

        } else if (node instanceof NumberNode numberNode) {
            return numberNode.value;

        } else if (node instanceof Vardecl varDecl) {
            int rightExpressionResult = visit(varDecl.expression);
            valueTable.put(varDecl.var.name, rightExpressionResult);
            return rightExpressionResult;

        } else if (node instanceof Var var) {
            String varName = var.name;
            if (!valueTable.containsKey(varName)) {
                throw new ParserException("Variable not found: " + varName);
            }
            return valueTable.get(varName);

        } else if (node instanceof AssignmentNode assignNode) {
            int rightExpressionResult = visit(assignNode.right);
            valueTable.put(assignNode.left.name, rightExpressionResult);
            return rightExpressionResult;

        } else if (node instanceof BlockNode block) {
            int result = 0;
            for (ASTNode statement : block.statements) {
                result = visit(statement);
            }
            return result;

        } else if (node instanceof IfNode ifNode) {
            int conditionResult = visit(ifNode.condition);
            if (conditionResult != 0) {
                return visit(ifNode.ifBlock);
            } else if (ifNode.elseBlock != null) {
                return visit(ifNode.elseBlock);
            }
            return 0;

        } else if (node instanceof PrintNode printNode) {
            int value = visit(printNode.value);
            System.out.println(value);
            return value;

        } else {
            throw new ParserException("Unexpected AST Node: " + node.getClass().getCanonicalName());
        }

    }
}
