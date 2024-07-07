package CS_Interpreters_Parsers;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Token> tokens; //going over and creating ASD node, you go to BNF form, what I gave to parser it should follow the rules in BNF
    private int currentPos; //num of tokens, if pos = length we are done
    private Token currentToken;

    public Parser(List<Token> tokens) {
        //insures everything works
        this.tokens = tokens;
        currentPos = 0;
        currentToken = tokens.get(currentPos);
    }

    private ASTNode expression() throws ParserException {
        ASTNode node = term();
        while (currentToken != null && (currentToken.type == Token.Type.PLUS || currentToken.type == Token.Type.MINUS || currentToken.type == Token.Type.OPERATOR)) {
            Token token = currentToken;
            consume(token.type);
            node = new BinaryOpNode(node, term(), token);
        }
        return node;
    }

    public ASTNode parse() throws ParserException {
        //will give the top level node to traverse it, we will return the root of the parsed expression if we can parse it
        //return expression(); //term returns a Tree too, any expression is a term and term is a root of the Tree
        List<ASTNode> statements = new ArrayList<>();
        while (currentToken != null) {
            statements.add(statement());
            if (currentToken != null && currentToken.type == Token.Type.SEMICOLON) {
                consume(Token.Type.SEMICOLON);
            }
        }
        return new BlockNode(statements);
    }

    private ASTNode block() throws ParserException {
        consume(Token.Type.LBRACE);
        List<ASTNode> block = new ArrayList<>();
        while (currentToken != null && currentToken.type != Token.Type.RBRACE) {
            block.add(statement());
            if (currentToken.type == Token.Type.SEMICOLON) {
                consume(Token.Type.SEMICOLON);
            }
        }
        consume(Token.Type.RBRACE);
        return new BlockNode(block);
    }

    private ASTNode statement() throws ParserException {
        if (currentToken.type == Token.Type.IDENTIFIER) {
            return assignment();
        }
        if (currentToken.type == Token.Type.IF) {
            return ifStatement();
        }
        if (currentToken.type == Token.Type.PRINT) {
            return printStatement();
        }
        if (currentToken.type == Token.Type.LBRACE) {
            return block();
        }
        if (currentToken.type == Token.Type.VAR) {
            return declaration();
        }
        return expression();
    }

    private ASTNode assignment() throws ParserException {
        Var var = var();
        consume(Token.Type.ASSIGN);
        ASTNode expression = expression();
        return new AssignmentNode(var, expression);
    }

    private ASTNode ifStatement() throws ParserException {
        consume(Token.Type.IF);
        consume(Token.Type.LPAREN);
        ASTNode condition = expression();
        consume(Token.Type.RPAREN);
        consume(Token.Type.THEN);
        consume(Token.Type.LPAREN);
        ASTNode thenBranch = block();
        consume(Token.Type.RPAREN);
        ASTNode elseBranch = null;
        if (currentToken != null && currentToken.type == Token.Type.ELSE) {
            consume(Token.Type.ELSE);
            consume(Token.Type.LPAREN);
            elseBranch = block();
            consume(Token.Type.RPAREN);
        }
        return new IfNode(condition, thenBranch, elseBranch);
    }

    private ASTNode printStatement() throws ParserException {
        consume(Token.Type.PRINT);
        ASTNode expression = expression();
        consume(Token.Type.SEMICOLON);
        return new PrintNode(expression);
    }

    private ASTNode term() throws ParserException {
        //term can be a factor, or factor * or / by smth OR a number
        //WE read a factor and checking if the next term a * or /
        //if next token exists we can only proceed * or / otherwise its an error => Rule 2
        //if not * or / we return a factor
        ASTNode node = factor();
        while (currentToken != null && (currentToken.type == Token.Type.MULTIPLY || currentToken.type == Token.Type.DIVIDE)) {
            Token token = currentToken;
            consume(currentToken.type);//token we are consuming is a type that we want * or / or throw an error
            node = new BinaryOpNode(node, factor(), token);
        }
        return node;
    }

    private void consume(Token.Type type) throws ParserException {
        //we know its a token, verify its multiply and go to the next thing
        //we consume NUMBER .... and get to * and say
        if (currentToken.type == type) {
            currentPos++;
            if (currentPos < tokens.size()) {
                currentToken = tokens.get(currentPos);
            } else {
                currentToken = null; //otherwise we reached the end
            }
        } else {
            throw new ParserException("Unexpected token " + currentToken);
        }
    }

    private ASTNode factor() throws ParserException {
        //factor is just an expression or a number <======
        //it has to be a number token and we just return it
        //we are pointing into a token, it has to be a number token and we need to return a node from it

        //now it could be a parenthesis or a number
        Token token = currentToken;
        if (token.type == Token.Type.NUMBER) {
            consume(Token.Type.NUMBER);
            return new NumberNode(token);
        }
        //we need to consume left parenthesis and the rest should be an expression to give us a node
        if (token.type == Token.Type.LPAREN) {
            consume(Token.Type.LPAREN);
            ASTNode node = expression();
            consume(Token.Type.RPAREN);//if no right parenthesis consume() throws an exception
            return node;
        }
        if (token.type == Token.Type.IDENTIFIER) {
            return var();
        }
        throw new ParserException("Unexpected token found for Factor : " + token);
    }

    private ASTNode declaration() throws ParserException {
        consume(Token.Type.VAR);
        Var var = var();
        consume(Token.Type.ASSIGN);
        ASTNode expression = expression();
        return new Vardecl(var, expression);
    }

    private Var var() throws ParserException {
        Token token = currentToken;
        consume(Token.Type.IDENTIFIER);
        return new Var(token);
    }
}
