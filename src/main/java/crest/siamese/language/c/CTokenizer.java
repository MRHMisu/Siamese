package crest.siamese.language.c;

import crest.siamese.language.Normalizer;
import crest.siamese.language.Tokenizer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

import java.io.File;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Tokenizer for C source code.
 */

public class CTokenizer implements Tokenizer {

    private Normalizer normalizer;


    /**
     * Constructor that refer to parent Object class. Required for calling Class::newInstance.
     */
    public CTokenizer() {
        super();
    }

    @Override
    public void configure(Normalizer normalizer) {
        this.normalizer = normalizer;
    }

    @Override
    public ArrayList<String> tokenize(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> tokenize(Reader reader) throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> tokenizeLine(Reader reader) throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> tokenize(File f) throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> getTokensFromFile(String file) throws Exception {
        return null;
    }


    /**
     * Uses the ANTLR4 CLexer to obtain the list of tokens from the extracted method. Normalises the tokens
     * according to the configuration of the Normalizer (composite object of this class).
     *
     * @param input input
     * @return ArrayList of String
     */

    @Override
    public ArrayList<String> getTokensFromString(String input) throws Exception {
        CLexer lexer = new CLexer(CharStreams.fromString(input));
        List<? extends Token> tokens = lexer.getAllTokens();
        return tokens.stream()
                .map(token -> normalizer.normalizeAToken(
                        token.getText(),
                        CParser.VOCABULARY.getSymbolicName(token.getType())))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
