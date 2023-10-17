package Parser;
import Lexer.Token;
import java.util.List;

public class TokenManager {
    private List<Token> tokens;
    private int currentIndex;

    public TokenManager(List<Token> tokens) {
        this.tokens = tokens;
        this.currentIndex = 0;
    }

    public Token getCurrentToken() {
        return tokens.get(currentIndex);
    }

    public Token getNextToken() {
        if (currentIndex + 1 < tokens.size()) {
            return tokens.get(currentIndex + 1);
        }
        return null; // or throw an exception
    }

    public void advance() {
        currentIndex++;

        // Debug information
        Token currentToken = getCurrentToken();
        System.out.println("[DEBUG] Advanced to token " + currentToken.value + " of type " + currentToken.type + " at line " + currentToken.lineNumber);
    }

    public void backtrack() {
        currentIndex--;

        // Debug information
        Token currentToken = getCurrentToken();
        System.out.println("[DEBUG] Backtracked to token " + currentToken.value + " of type " + currentToken.type + " at line " + currentToken.lineNumber);
    }

    // ... other methods as needed ...
}
