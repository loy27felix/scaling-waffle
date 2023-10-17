package Parser.Element.Exp;

import Lexer.Token;
import Lexer.TokenType;
import Parser.TokenManager;
import java.text.ParseException;

public class MulExp {
    private UnaryExp unaryExp;
    private MulExp mulExp;
    private Token mulOp;

    public MulExp(TokenManager tokenManager) throws ParseException {
        unaryExp = new UnaryExp(tokenManager);

        Token currentToken = tokenManager.getCurrentToken();
        if (currentToken.type == TokenType.MULT || currentToken.type == TokenType.DIV || currentToken.type == TokenType.MOD) {
            mulOp = currentToken;
            tokenManager.advance();
            mulExp = new MulExp(tokenManager);
        }

        // Debug information
        System.out.println("[DEBUG] Parsed MulExp at line " + currentToken.lineNumber);
    }

    // ... other methods and attributes ...
}
