/*
   Copyright 2021 Md Rakib Hossain

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package crest.siamese.language.cpp;
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

        private CPP14Parser parser;

        /**
         * Constructor to Create Parser with source code as CharStream
         *
         * @param input Provided source code as CharStream
         */

        public Parser(CharStream input) {
            CPP14Lexer lexer = new CPP14Lexer(input);
            lexer.removeErrorListeners();
            lexer.addErrorListener(ERROR_LISTENER);
            this.parser = new CPP14Parser(new CommonTokenStream(lexer));
            this.parser.removeErrorListeners();
            this.parser.addErrorListener(ERROR_LISTENER);
        }

        /**
         * Constructor to Create Parser with CPP14Lexer
         *
         * @param lexer CPP14Lexer;
         */
        public Parser(CPP14Lexer lexer) {
            this.parser = new CPP14Parser(new CommonTokenStream(lexer));
            this.parser.removeErrorListeners();
            this.parser.addErrorListener(ERROR_LISTENER);
        }

        /**
         * Build CPP14Parser Parser
         *
         * @return CPP14Parser Parser
         */
        public CPP14Parser build() {
            return this.parser;
        }
    }

}
