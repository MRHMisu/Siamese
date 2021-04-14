package crest.siamese.language.c;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;


/**
 * A builder class that helps to build CParser with Custom Error Listener
 * based on the recommended Builder Pattern
 */
public final class Builder {

    private static final ErrorListener ERROR_LISTENER = new ErrorListener();

    /**
     * This class will not be instantiated.
     */
    private Builder() {
    }

    /**
     * A static class  generates a Parser Builder
     */
    public static final class Parser {

        private CParser parser;

        /**
         * Constructor to Create Parser with source code as CharStream
         *
         * @param input Provided source code as CharStream
         */

        public Parser(CharStream input) {
            CLexer lexer = new CLexer(input);
            lexer.removeErrorListeners();
            lexer.addErrorListener(ERROR_LISTENER);
            this.parser = new CParser(new CommonTokenStream(lexer));
            this.parser.removeErrorListeners();
            this.parser.addErrorListener(ERROR_LISTENER);
        }

        /**
         * Constructor to Create Parser with CLexer
         *
         * @param lexer CLexer;
         */
        public Parser(CLexer lexer) {
            this.parser = new CParser(new CommonTokenStream(lexer));
            this.parser.removeErrorListeners();
            this.parser.addErrorListener(ERROR_LISTENER);
        }

        /**
         * Build CParser Parser
         *
         * @return CParser Parser
         */
        public CParser build() {
            return this.parser;
        }
    }

}
