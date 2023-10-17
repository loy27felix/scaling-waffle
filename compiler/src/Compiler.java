import Lexer.Lexer;
import Lexer.Token;
import java.io.*;
import java.util.*;


public class Compiler {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.tokenize("testfile.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            for (Token token : tokens) {
                writer.write(token.type.name() + " " + token.value + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}













