package Parser.Element.Exp;
import Lexer.Token;
import Lexer.TokenType;
import Parser.TokenManager;
import java.text.ParseException;

public class Number {
    private Token token;
    private int value;

    public Number(TokenManager tokenManager) throws ParseException {
        this.token = tokenManager.getCurrentToken();
        if (token.type != TokenType.INTCON) {
            throw new ParseException("Expected an integer constant at position " + token.lineNumber, token.lineNumber);
        }
        this.value = Integer.parseInt(token.value);
        tokenManager.advance();

        // Debug information
        System.out.println("[DEBUG] Parsed Number with value " + value + " at line " + token.lineNumber);
    }

    public int getValue() {
        return value;
    }

    // ... other methods and attributes ...
}
