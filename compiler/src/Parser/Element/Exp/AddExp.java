package Parser.Element.Exp;

import Lexer.Token;
import Lexer.TokenType;
import Parser.TokenManager;
import java.text.ParseException;

public class AddExp {
    private MulExp mulExp;
    private AddExp addExp;
    private Token addOp;

    public AddExp(TokenManager tokenManager) throws ParseException {
        mulExp = new MulExp(tokenManager);

        Token currentToken = tokenManager.getCurrentToken();
        if (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINU) {
            addOp = currentToken;
            tokenManager.advance();
            addExp = new AddExp(tokenManager);
        }

        // Debug information
        System.out.println("[DEBUG] Parsed AddExp at line " + currentToken.lineNumber);
    }

    // ... other methods and attributes ...
}
