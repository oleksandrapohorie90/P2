package CS_Interpreters_ASTS;

import CS_Interpreters_Parsers.*;

import java.text.NumberFormat;
import java.util.Set;
import java.util.Stack;

public class SemanticAnalyzer {
    private final Stack<Set<String>> scopes = new Stack<>();

    //parser returns the root node
    //go thru all the children and check if everything is good
    void visit(ASTNode node) throws ParserException {
        //if smth is wrong we should throw an exception to know whats wrong
        //go thru each node

        if(node instanceof BinaryOpNode){

        }else if (node instanceof NumberNode){

        }else if (node instanceof VariableNode){

        }else if (node instanceof BlockNode){

        } else {
            throw new ParserException("Unexpected AST Node: " + node.getClass().getCanonicalName());
        }

    }
}
