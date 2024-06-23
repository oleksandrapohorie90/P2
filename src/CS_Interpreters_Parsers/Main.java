package CS_Interpreters_Parsers;

import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserException {
        /**
         * primary version:
         * BinaryOp{+}
         *     NumberNode{3}
         *     BinaryOp{*}
         *       NumberNode{5}
         *       BinaryOp{-}
         *         NumberNode{10}
         *         NumberNode{4}
         */

        List<Token> tokens = Lexer.getHardcodedTokens();
        Parser parser = new Parser(tokens);
        ASTNode root = parser.parse(); //later there will be Interpreter to pass on

        root.print("  ");
    }
}
