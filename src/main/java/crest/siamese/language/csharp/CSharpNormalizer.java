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

package crest.siamese.language.csharp;

import com.google.common.collect.ImmutableMap;
import crest.siamese.language.Normalizer;
import crest.siamese.language.NormalizerMode;

import java.util.ArrayList;
import java.util.Map;

public class CSharpNormalizer implements Normalizer {

    private Map<String, Boolean> normalizeSettings;

    private static final String NORMALIZED_TOKEN_KEYWORD = "K";
    private static final String NORMALIZED_TOKEN_VALUE = "V";
    private static final String NORMALIZED_TOKEN_STRING = "S";
    private static final String NORMALIZED_TOKEN_OPERATOR = "O";
    private static final String NORMALIZED_TOKEN_NAME = "W"; // same symbol as Java clone search

    /**
     * Maps to a normalised form.
     * Symbols with no corresponding normalised form have the empty string value. Normalization for these
     * symbols will always be false.
     */
    private static final Map<String, String> normalizedSymbols = ImmutableMap.<String, String>builder()
            // add mapping
            .put("BYTE_ORDER_MARK", "")
            .put("SINGLE_LINE_DOC_COMMENT", "")
            .put("EMPTY_DELIMITED_DOC_COMMENT", "")
            .put("DELIMITED_DOC_COMMENT", "")
            .put("SINGLE_LINE_COMMENT", "")
            .put("DELIMITED_COMMENT", "")
            .put("WHITESPACES", "")
            .put("SHARP", "")


            .put("ABSTRACT", NORMALIZED_TOKEN_KEYWORD)
            .put("ADD", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("ALIAS", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("ARGLIST", NORMALIZED_TOKEN_KEYWORD)//Undocumented Keywords //Deprecated rules
            //https://docs.microsoft.com/en-us/visualstudio/code-quality/ca2230?view=vs-2019
            .put("AS", NORMALIZED_TOKEN_KEYWORD)
            .put("ASCENDING", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("ASYNC", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("AWAIT", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("BASE", NORMALIZED_TOKEN_KEYWORD)
            .put("BOOL", NORMALIZED_TOKEN_KEYWORD)
            .put("BREAK", NORMALIZED_TOKEN_KEYWORD)
            .put("BY", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("BYTE", NORMALIZED_TOKEN_KEYWORD)
            .put("CASE", NORMALIZED_TOKEN_KEYWORD)
            .put("CATCH", NORMALIZED_TOKEN_KEYWORD)
            .put("CHAR", NORMALIZED_TOKEN_KEYWORD)
            .put("CHECKED", NORMALIZED_TOKEN_KEYWORD)
            .put("CLASS", NORMALIZED_TOKEN_KEYWORD)
            .put("CONST", NORMALIZED_TOKEN_KEYWORD)
            .put("CONTINUE", NORMALIZED_TOKEN_KEYWORD)
            .put("DECIMAL", NORMALIZED_TOKEN_KEYWORD)
            .put("DEFAULT", NORMALIZED_TOKEN_KEYWORD)
            .put("DELEGATE", NORMALIZED_TOKEN_KEYWORD)
            .put("DESCENDING", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("DO", NORMALIZED_TOKEN_KEYWORD)
            .put("DOUBLE", NORMALIZED_TOKEN_KEYWORD)
            .put("DYNAMIC", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("ELSE", NORMALIZED_TOKEN_KEYWORD)
            .put("ENUM", NORMALIZED_TOKEN_KEYWORD)
            .put("EQUALS", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("EVENT", NORMALIZED_TOKEN_KEYWORD)
            .put("EXPLICIT", NORMALIZED_TOKEN_KEYWORD)
            .put("EXTERN", NORMALIZED_TOKEN_KEYWORD)
            .put("FALSE", NORMALIZED_TOKEN_KEYWORD)
            .put("FINALLY", NORMALIZED_TOKEN_KEYWORD)
            .put("FIXED", NORMALIZED_TOKEN_KEYWORD)
            .put("FLOAT", NORMALIZED_TOKEN_KEYWORD)
            .put("FOR", NORMALIZED_TOKEN_KEYWORD)
            .put("FOREACH", NORMALIZED_TOKEN_KEYWORD)
            .put("FROM", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("GET", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("GOTO", NORMALIZED_TOKEN_KEYWORD)
            .put("GROUP", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("IF", NORMALIZED_TOKEN_KEYWORD)
            .put("IMPLICIT", NORMALIZED_TOKEN_KEYWORD)
            .put("IN", NORMALIZED_TOKEN_KEYWORD)
            .put("INT", NORMALIZED_TOKEN_KEYWORD)
            .put("INTERFACE", NORMALIZED_TOKEN_KEYWORD)
            .put("INTERNAL", NORMALIZED_TOKEN_KEYWORD)
            .put("INTO", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("IS", NORMALIZED_TOKEN_KEYWORD)
            .put("JOIN", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("LET", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("LOCK", NORMALIZED_TOKEN_KEYWORD)
            .put("LONG", NORMALIZED_TOKEN_KEYWORD)
            .put("NAMEOF", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("NAMESPACE", NORMALIZED_TOKEN_KEYWORD)
            .put("NEW", NORMALIZED_TOKEN_KEYWORD)
            .put("NULL_", NORMALIZED_TOKEN_KEYWORD)
            .put("OBJECT", NORMALIZED_TOKEN_KEYWORD)
            .put("ON", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("OPERATOR", NORMALIZED_TOKEN_KEYWORD)
            .put("ORDERBY", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("OUT", NORMALIZED_TOKEN_KEYWORD)
            .put("OVERRIDE", NORMALIZED_TOKEN_KEYWORD)
            .put("PARAMS", NORMALIZED_TOKEN_KEYWORD)
            .put("PARTIAL", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("PRIVATE", NORMALIZED_TOKEN_KEYWORD)
            .put("PROTECTED", NORMALIZED_TOKEN_KEYWORD)
            .put("PUBLIC", NORMALIZED_TOKEN_KEYWORD)
            .put("READONLY", NORMALIZED_TOKEN_KEYWORD)
            .put("REF", NORMALIZED_TOKEN_KEYWORD)
            .put("REMOVE", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("RETURN", NORMALIZED_TOKEN_KEYWORD)
            .put("SBYTE", NORMALIZED_TOKEN_KEYWORD)
            .put("SEALED", NORMALIZED_TOKEN_KEYWORD)
            .put("SELECT", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("SET", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("SHORT", NORMALIZED_TOKEN_KEYWORD)
            .put("SIZEOF", NORMALIZED_TOKEN_KEYWORD)
            .put("STACKALLOC", NORMALIZED_TOKEN_KEYWORD)
            .put("SWITCH", NORMALIZED_TOKEN_KEYWORD)
            .put("STRING", NORMALIZED_TOKEN_KEYWORD)
            .put("STRUCT", NORMALIZED_TOKEN_KEYWORD)
            .put("THIS", NORMALIZED_TOKEN_KEYWORD)
            .put("THROW", NORMALIZED_TOKEN_KEYWORD)
            .put("TRUE", NORMALIZED_TOKEN_KEYWORD)
            .put("TRY", NORMALIZED_TOKEN_KEYWORD)
            .put("TYPEOF", NORMALIZED_TOKEN_KEYWORD)
            .put("UINT", NORMALIZED_TOKEN_KEYWORD)
            .put("ULONG", NORMALIZED_TOKEN_KEYWORD)
            .put("UNCHECKED", NORMALIZED_TOKEN_KEYWORD)
            .put("UNMANAGED", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("UNSAFE", NORMALIZED_TOKEN_KEYWORD)
            .put("USHORT", NORMALIZED_TOKEN_KEYWORD)
            .put("USING", NORMALIZED_TOKEN_KEYWORD)
            .put("VAR", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("VIRTUAL", NORMALIZED_TOKEN_KEYWORD)
            .put("VOID", NORMALIZED_TOKEN_KEYWORD)
            .put("VOLATILE", NORMALIZED_TOKEN_KEYWORD)
            .put("WHEN", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("WHERE", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords
            .put("WHILE", NORMALIZED_TOKEN_KEYWORD)
            .put("YIELD", NORMALIZED_TOKEN_KEYWORD)//Contextual keywords


            .put("IDENTIFIER", NORMALIZED_TOKEN_NAME)

            .put("LITERAL_ACCESS", NORMALIZED_TOKEN_VALUE)
            .put("INTEGER_LITERAL", NORMALIZED_TOKEN_VALUE)
            .put("HEX_INTEGER_LITERAL", NORMALIZED_TOKEN_VALUE)
            .put("BIN_INTEGER_LITERAL", NORMALIZED_TOKEN_VALUE)
            .put("REAL_LITERAL", NORMALIZED_TOKEN_VALUE)
            .put("CHARACTER_LITERAL", NORMALIZED_TOKEN_VALUE)

            .put("REGULAR_STRING", NORMALIZED_TOKEN_STRING)
            .put("VERBATIUM_STRING", NORMALIZED_TOKEN_STRING)
            //https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/tokens/verbatim
            .put("INTERPOLATED_REGULAR_STRING_START", NORMALIZED_TOKEN_STRING)
            .put("INTERPOLATED_VERBATIUM_STRING_START", NORMALIZED_TOKEN_STRING)
            //https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/tokens/interpolated


            .put("OPEN_BRACE", "")
            .put("CLOSE_BRACE", "")
            .put("OPEN_BRACKET", "")
            .put("CLOSE_BRACKET", "")
            .put("OPEN_PARENS", "")
            .put("CLOSE_PARENS", "")
            .put("DOT", "")
            .put("COMMA", "")
            .put("COLON", "")
            .put("SEMICOLON", "")

            // operators
            .put("PLUS", NORMALIZED_TOKEN_OPERATOR)
            .put("MINUS", NORMALIZED_TOKEN_OPERATOR)
            .put("STAR", NORMALIZED_TOKEN_OPERATOR)
            .put("DIV", NORMALIZED_TOKEN_OPERATOR)
            .put("PERCENT", NORMALIZED_TOKEN_OPERATOR)
            .put("AMP", NORMALIZED_TOKEN_OPERATOR)
            .put("BITWISE_OR", NORMALIZED_TOKEN_OPERATOR)
            .put("CARET", NORMALIZED_TOKEN_OPERATOR)
            .put("BANG", NORMALIZED_TOKEN_OPERATOR)
            .put("TILDE", NORMALIZED_TOKEN_OPERATOR)
            .put("ASSIGNMENT", NORMALIZED_TOKEN_OPERATOR)
            .put("LT", NORMALIZED_TOKEN_OPERATOR)
            .put("GT", NORMALIZED_TOKEN_OPERATOR)
            .put("INTERR", NORMALIZED_TOKEN_OPERATOR)
            .put("DOUBLE_COLON", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_COALESCING", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_INC", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_DEC", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_AND", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_OR", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_PTR", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_EQ", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_NE", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_LE", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_GE", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_ADD_ASSIGNMENT", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_SUB_ASSIGNMENT", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_MULT_ASSIGNMENT", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_DIV_ASSIGNMENT", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_MOD_ASSIGNMENT", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_AND_ASSIGNMENT", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_OR_ASSIGNMENT", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_XOR_ASSIGNMENT", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_LEFT_SHIFT", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_LEFT_SHIFT_ASSIGNMENT", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_COALESCING_ASSIGNMENT", NORMALIZED_TOKEN_OPERATOR)
            .put("OP_RANGE", NORMALIZED_TOKEN_OPERATOR)


            .put("DOUBLE_CURLY_INSIDE", "")
            .put("OPEN_BRACE_INSIDE", "")
            .put("REGULAR_CHAR_INSIDE", "")
            .put("VERBATIUM_DOUBLE_QUOTE_INSIDE", "")
            .put("DOUBLE_QUOTE_INSIDE", "")
            .put("REGULAR_STRING_INSIDE", "")
            .put("VERBATIUM_INSIDE_STRING", "")
            .put("CLOSE_BRACE_INSIDE", "")
            .put("FORMAT_STRING", "")
            .put("DIRECTIVE_WHITESPACES", "")
            .put("DIGITS", "")
            .put("DEFINE", "")
            .put("UNDEF", "")
            .put("ELIF", "")
            .put("ENDIF", "")
            .put("LINE", "")
            .put("ERROR", "")
            .put("WARNING", "")
            .put("REGION", "")
            .put("ENDREGION", "")
            .put("PRAGMA", "")
            .put("NULLABLE", "")
            .put("DIRECTIVE_HIDDEN", "")
            .put("CONDITIONAL_SYMBOL", "")
            .put("DIRECTIVE_NEW_LINE", "")
            .put("TEXT", "")
            .put("DOUBLE_CURLY_CLOSE_INSIDE", "")
            .build();

    /**
     * Constructor that refer to parent Object class. Required for calling Class::newInstance.
     */
    public CSharpNormalizer() {
        super();
    }

    /**
     * Configures the normalization settings according to the input NormalizerMode.
     *
     * @param normalizerMode Configuration specifications for which category of symbols are to be normalised
     */
    @Override
    public void configure(NormalizerMode normalizerMode) {
        CSharpNormalizerMode cSharpNormalizerMode = (CSharpNormalizerMode) normalizerMode;
        normalizeSettings = ImmutableMap.<String, Boolean>builder()
                .put(NORMALIZED_TOKEN_KEYWORD, cSharpNormalizerMode.isKeywordToBeNormalised())
                .put(NORMALIZED_TOKEN_VALUE, cSharpNormalizerMode.isValueToBeNormalised())
                .put(NORMALIZED_TOKEN_STRING, cSharpNormalizerMode.isStringToBeNormalised())
                .put(NORMALIZED_TOKEN_OPERATOR, cSharpNormalizerMode.isOperatorToBeNormalised())
                .put(NORMALIZED_TOKEN_NAME, cSharpNormalizerMode.isNameToBeNormalised())
                .put("", false)
                .build();
    }

    /**
     * Normalizes a token depending on the normalization settings.
     *
     * @param token String text of the token
     * @param type  String type of the token
     * @return Normalised token
     */
    @Override
    public String normalizeAToken(String token, String type) {
        String normalizedSymbol = normalizedSymbols.get(type);
        return normalizeSettings.get(normalizedSymbol) ? normalizedSymbol : token;
    }

    @Override
    public ArrayList<String> noNormalizeAToken(String token) {
        return null;
    }
}
