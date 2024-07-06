package CS_Interpreters_Parsers;

import CS_Interpreters_ASTS.SemanticAnalyzer;
import CS_Interpreters_Lexer.Lexer;

import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserException {
        /**
         Input code:
         x=5
         if(x>3){
         y=x+2;
         }else{
         y=x*(2+3);
         }
         print y;
         TokenType: identifier w/ value x, assignment w/ value =, number w/ value 5
         x - identifier

         Output list of tokens:
         ["x","=","5",";","if","(","x",">","3",")","{","y","=","x","+","2",";","}",
         "else","{","y","=","x","*","(","2","+","3",")",";","}","print","y",";"]
         */
        String input = """
                x = 5;
                if (x > 3){
                    y = x + 2;
                } else {
                    y = x * (2 + 3);
                    }
                    print y;""";
//CS_Lexer.Lexer produces tokens with different types, then these tokens are eaten by parser later and then parser will say smth is valid or invalid
        Lexer lexer = new Lexer(input);
        for (Lexer.Token token : lexer) {
            System.out.println(token);
        }

        List<Lexer.Token> tokens = lexer.getTokens(); //method to get tokens
        Parser parser = new Parser(tokens);
        ASTNode root = parser.parse();
        root.print("    ");

        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
        semanticAnalyzer.visit(root);

        //add interpreter

    }
}