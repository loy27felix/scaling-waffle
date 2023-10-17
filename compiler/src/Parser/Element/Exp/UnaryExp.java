package Parser.Element.Exp;

import Lexer.Token;
import Lexer.TokenType;
import Parser.TokenManager;
import java.text.ParseException;

public class UnaryExp {
    private PrimaryExp primaryExp;
    private UnaryExp unaryExp;
    private Token unaryOp;

    public UnaryExp(TokenManager tokenManager) throws ParseException {
        Token currentToken = tokenManager.getCurrentToken();

        if (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINU || currentToken.type == TokenType.NOT) {
            unaryOp = currentToken;
            tokenManager.advance();
            unaryExp = new UnaryExp(tokenManager);
        } else if (currentToken.type == TokenType.IDENFR && tokenManager.getNextToken().type == TokenType.LPARENT) {
            // This is a function call
            // TODO: Implement function call parsing
        } else {
            primaryExp = new PrimaryExp(tokenManager);
        }

        // Debug information
        System.out.println("[DEBUG] Parsed UnaryExp at line " + currentToken.lineNumber);
    }

    // ... other methods and attributes ...
}
