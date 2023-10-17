package Parser.Element.Exp;

import Lexer.Token;
import Lexer.TokenType;
import Parser.TokenManager;
import java.text.ParseException;

public class Ident {
    private Token token;
    private String identifier;

    public Ident(TokenManager tokenManager) throws ParseException {
        this.token = tokenManager.getCurrentToken();
        if (token.type != TokenType.IDENFR) {
            throw new ParseException("Expected an identifier at position " + token.lineNumber, token.lineNumber);
        }
        this.identifier = token.value;
        tokenManager.advance();

        // Debug information
        System.out.println("[DEBUG] Parsed Identifier with name " + identifier + " at line " + token.lineNumber);
    }

    public String getIdentifier() {
        return identifier;
    }

    // ... other methods and attributes ...
}
