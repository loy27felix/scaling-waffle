package Parser.Element.Exp;

import Lexer.Token;
import Lexer.TokenType;
import Parser.TokenManager;
import java.text.ParseException;

public class PrimaryExp {
    private Exp expression;
    private Number number;
    private LVal lval;

    public PrimaryExp(TokenManager tokenManager) throws ParseException {
        Token currentToken = tokenManager.getCurrentToken();

        if (currentToken.type == TokenType.LPARENT) {
            tokenManager.advance();
            expression = new Exp(tokenManager);

            if (tokenManager.getCurrentToken().type != TokenType.RPARENT) {
                throw new ParseException("Expected closing parenthesis at position " + currentToken.lineNumber, currentToken.lineNumber);
            }
            tokenManager.advance();
        } else if (currentToken.type == TokenType.INTCON) {
            number = new Number(tokenManager);
        } else if (currentToken.type == TokenType.IDENFR) {
            lval = new LVal(tokenManager);
        } else {
            throw new ParseException("Unexpected token in PrimaryExp at position " + currentToken.lineNumber, currentToken.lineNumber);
        }

        // Debug information
        System.out.println("[DEBUG] Parsed PrimaryExp at line " + currentToken.lineNumber);
    }

    // ... other methods and attributes ...
}

