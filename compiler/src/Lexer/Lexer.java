package Lexer;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lexer {
    private boolean inMultilineComment = false;
    private static final Map<String, TokenType> RESERVED_WORDS;
    private static final Pattern PATTERN = Pattern.compile("[a-zA-Z_]+\\w*|\\d+|\".*?\"|//.*?$|<=|>=|==|!=|&&|\\|\\||\\S", Pattern.MULTILINE);

    static {
        RESERVED_WORDS = new HashMap<>();
        RESERVED_WORDS.put("const", TokenType.CONSTTK);
        RESERVED_WORDS.put("int", TokenType.INTTK);
        RESERVED_WORDS.put("break", TokenType.BREAKTK);
        RESERVED_WORDS.put("continue", TokenType.CONTINUETK);
        RESERVED_WORDS.put("if", TokenType.IFTK);
        RESERVED_WORDS.put("else", TokenType.ELSETK);
        RESERVED_WORDS.put("for", TokenType.FORTK);
        RESERVED_WORDS.put("main", TokenType.MAINTK);
        RESERVED_WORDS.put("void", TokenType.VOIDTK);
        RESERVED_WORDS.put("return", TokenType.RETURNTK);
        RESERVED_WORDS.put("printf", TokenType.PRINTFTK);
        RESERVED_WORDS.put("getint", TokenType.GETINTTK);
        RESERVED_WORDS.put("!", TokenType.NOT);
        RESERVED_WORDS.put("&&", TokenType.AND);
        RESERVED_WORDS.put("||", TokenType.OR);
        RESERVED_WORDS.put("*", TokenType.MULT);
        RESERVED_WORDS.put("/", TokenType.DIV);
        RESERVED_WORDS.put("%", TokenType.MOD);
        RESERVED_WORDS.put("+", TokenType.PLUS);
        RESERVED_WORDS.put("-", TokenType.MINU);
        RESERVED_WORDS.put("=", TokenType.ASSIGN);
        RESERVED_WORDS.put(";", TokenType.SEMICN);
        RESERVED_WORDS.put(",", TokenType.COMMA);
        RESERVED_WORDS.put("<", TokenType.LSS);
        RESERVED_WORDS.put("<=", TokenType.LEQ);
        RESERVED_WORDS.put(">", TokenType.GRE);
        RESERVED_WORDS.put(">=", TokenType.GEQ);
        RESERVED_WORDS.put("==", TokenType.EQL);
        RESERVED_WORDS.put("!=", TokenType.NEQ);
        RESERVED_WORDS.put("(", TokenType.LPARENT);
        RESERVED_WORDS.put(")", TokenType.RPARENT);
        RESERVED_WORDS.put("[", TokenType.LBRACK);
        RESERVED_WORDS.put("]", TokenType.RBRACK);
        RESERVED_WORDS.put("{", TokenType.LBRACE);
        RESERVED_WORDS.put("}", TokenType.RBRACE);
    }
    private boolean insideStringConstant(String line, String pattern) {
        int quoteCountBeforePattern = line.substring(0, line.indexOf(pattern)).split("\"", -1).length - 1;
        return quoteCountBeforePattern % 2 == 1;
    }



    public List<Token> tokenize(String fileName) {
        List<Token> tokens = new ArrayList<>();
        try {
            StringBuilder fileContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("Processing line " + lineNumber + ": " + line);
                fileContent.append(line).append('\n');
                if (inMultilineComment) {
                    if (line.contains("*/") && !insideStringConstant(line, "*/")) {
                        inMultilineComment = false;
                        line = line.substring(line.indexOf("*/") + 2);
                    } else {
                        lineNumber++;
                        continue;
                    }
                }
                if (line.contains("/*") && !insideStringConstant(line, "/*")) {
                    if (line.contains("*/") && !insideStringConstant(line, "*/")) {
                        String beforeComment = line.substring(0, line.indexOf("/*"));
                        String afterComment = line.substring(line.indexOf("*/") + 2);
                        line = beforeComment + afterComment;
                    } else {
                        inMultilineComment = true;
                        line = line.substring(0, line.indexOf("/*"));
                    }
                }

                Matcher matcher = PATTERN.matcher(line);
                while (matcher.find()) {
                    String value = matcher.group();
                    if (!value.startsWith("//")) {
                        TokenType type = identifyToken(value);
                        tokens.add(new Token(type, value, lineNumber));
                        System.out.println("Added token: " + type + " " + value);
                    }
                }
                lineNumber++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokens;
    }

    private TokenType identifyToken(String value) {
        if (RESERVED_WORDS.containsKey(value)) {
            return RESERVED_WORDS.get(value);
        } else if (value.matches("\\d+")) {
            return TokenType.INTCON;
        } else if (value.startsWith("\"")) {
            return TokenType.STRCON;
        } else if (value.matches("[a-zA-Z_]+\\w*")) {
            return TokenType.IDENFR;
        } else {
            return TokenType.ERROR;
        }
    }

    public void output(List<Token> tokens, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (Token token : tokens) {
                if (token.type != TokenType.ERROR) {
                    writer.write(token.type.name() + " " + token.value + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}