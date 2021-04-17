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

import com.google.common.collect.ImmutableMap;
import crest.siamese.language.Normalizer;
import crest.siamese.language.NormalizerMode;

import java.util.ArrayList;
import java.util.Map;

public class CPPNormalizer implements Normalizer {
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
            .put("IntegerLiteral", NORMALIZED_TOKEN_VALUE)
            .put("CharacterLiteral", NORMALIZED_TOKEN_VALUE)
            .put("FloatingLiteral", NORMALIZED_TOKEN_VALUE)
            .put("StringLiteral", NORMALIZED_TOKEN_STRING)// verify it
            .put("BooleanLiteral", NORMALIZED_TOKEN_VALUE)
            .put("PointerLiteral", NORMALIZED_TOKEN_VALUE)
            .put("UserDefinedLiteral", NORMALIZED_TOKEN_VALUE)

            .put("MultiLineMacro", "")
            .put("Directive", "")
            .put("Alignas", "")
            .put("Alignof", "")
            .put("Asm", "")

            .put("Auto", NORMALIZED_TOKEN_KEYWORD)
            .put("Bool", NORMALIZED_TOKEN_KEYWORD)
            .put("Break", NORMALIZED_TOKEN_KEYWORD)
            .put("Case", NORMALIZED_TOKEN_KEYWORD)
            .put("Catch", NORMALIZED_TOKEN_KEYWORD)
            .put("Char", NORMALIZED_TOKEN_KEYWORD)
            .put("Char16", NORMALIZED_TOKEN_KEYWORD)
            .put("Char32", NORMALIZED_TOKEN_KEYWORD)
            .put("Class", NORMALIZED_TOKEN_KEYWORD)
            .put("Const", NORMALIZED_TOKEN_KEYWORD)
            .put("Constexpr", NORMALIZED_TOKEN_KEYWORD)
            .put("Const_cast", NORMALIZED_TOKEN_KEYWORD)
            .put("Continue", NORMALIZED_TOKEN_KEYWORD)
            .put(" Decltype", NORMALIZED_TOKEN_KEYWORD)
            .put("Default", NORMALIZED_TOKEN_KEYWORD)
            .put("Delete", NORMALIZED_TOKEN_KEYWORD)
            .put("Do", NORMALIZED_TOKEN_KEYWORD)
            .put("Double", NORMALIZED_TOKEN_KEYWORD)
            .put("Dynamic_cast", NORMALIZED_TOKEN_KEYWORD)
            .put("Else", NORMALIZED_TOKEN_KEYWORD)
            .put("Enum", NORMALIZED_TOKEN_KEYWORD)
            .put("Explicit", NORMALIZED_TOKEN_KEYWORD)
            .put("Export", NORMALIZED_TOKEN_KEYWORD)
            .put("Extern", NORMALIZED_TOKEN_KEYWORD)
            .put("False_", NORMALIZED_TOKEN_KEYWORD)
            .put("Final", NORMALIZED_TOKEN_KEYWORD)
            .put("Float", NORMALIZED_TOKEN_KEYWORD)
            .put("For", NORMALIZED_TOKEN_KEYWORD)
            .put("Friend", NORMALIZED_TOKEN_KEYWORD)
            .put("Goto", NORMALIZED_TOKEN_KEYWORD)
            .put("If", NORMALIZED_TOKEN_KEYWORD)
            .put("Inline", NORMALIZED_TOKEN_KEYWORD)
            .put("Int", NORMALIZED_TOKEN_KEYWORD)
            .put("Long", NORMALIZED_TOKEN_KEYWORD)
            .put("Mutable", NORMALIZED_TOKEN_KEYWORD)
            .put("Namespace", NORMALIZED_TOKEN_KEYWORD)
            .put("New", NORMALIZED_TOKEN_KEYWORD)
            .put("Noexcept", NORMALIZED_TOKEN_KEYWORD)
            .put("Nullptr", NORMALIZED_TOKEN_KEYWORD)
            .put("Operator", NORMALIZED_TOKEN_KEYWORD)
            .put("Override", NORMALIZED_TOKEN_KEYWORD)
            .put("Private", NORMALIZED_TOKEN_KEYWORD)
            .put("Protected", NORMALIZED_TOKEN_KEYWORD)
            .put("Public", NORMALIZED_TOKEN_KEYWORD)
            .put("Register", NORMALIZED_TOKEN_KEYWORD)
            .put("Reinterpret_cast", NORMALIZED_TOKEN_KEYWORD)
            .put("Return", NORMALIZED_TOKEN_KEYWORD)
            .put("Short", NORMALIZED_TOKEN_KEYWORD)
            .put("Signed", NORMALIZED_TOKEN_KEYWORD)
            .put("Sizeof", NORMALIZED_TOKEN_KEYWORD)
            .put("Static", NORMALIZED_TOKEN_KEYWORD)
            .put("Static_assert", NORMALIZED_TOKEN_KEYWORD)
            .put("Static_cast", NORMALIZED_TOKEN_KEYWORD)
            .put("Struct", NORMALIZED_TOKEN_KEYWORD)
            .put("Switch", NORMALIZED_TOKEN_KEYWORD)
            .put("Template", NORMALIZED_TOKEN_KEYWORD)
            .put("This", NORMALIZED_TOKEN_KEYWORD)
            .put("Thread_local", NORMALIZED_TOKEN_KEYWORD)
            .put("Throw", NORMALIZED_TOKEN_KEYWORD)
            .put("True_", NORMALIZED_TOKEN_KEYWORD)
            .put("Try", NORMALIZED_TOKEN_KEYWORD)
            .put("Typedef", NORMALIZED_TOKEN_KEYWORD)
            .put("Typeid_", NORMALIZED_TOKEN_KEYWORD)
            .put("Typename_", NORMALIZED_TOKEN_KEYWORD)
            .put("Union", NORMALIZED_TOKEN_KEYWORD)
            .put(" Unsigned", NORMALIZED_TOKEN_KEYWORD)
            .put("Using", NORMALIZED_TOKEN_KEYWORD)
            .put("Virtual", NORMALIZED_TOKEN_KEYWORD)
            .put("Void", NORMALIZED_TOKEN_KEYWORD)
            .put(" Volatile", NORMALIZED_TOKEN_KEYWORD)
            .put("Wchar", NORMALIZED_TOKEN_KEYWORD)
            .put("While", NORMALIZED_TOKEN_KEYWORD)

            .put("LeftParen", "")
            .put("RightParen", "")
            .put("LeftBracket", "")
            .put("RightBracket", "")
            .put("LeftBrace", "")
            .put("RightBrace", "")

            .put("Plus", NORMALIZED_TOKEN_OPERATOR)
            .put("Minus", NORMALIZED_TOKEN_OPERATOR)
            .put("Star", NORMALIZED_TOKEN_OPERATOR)
            .put("Div", NORMALIZED_TOKEN_OPERATOR)
            .put("Mod", NORMALIZED_TOKEN_OPERATOR)
            .put("Caret", NORMALIZED_TOKEN_OPERATOR)
            .put("And", NORMALIZED_TOKEN_OPERATOR)
            .put("Or", NORMALIZED_TOKEN_OPERATOR)
            .put("Tilde=", NORMALIZED_TOKEN_OPERATOR)
            .put("Not", NORMALIZED_TOKEN_OPERATOR)
            .put("Assign", NORMALIZED_TOKEN_OPERATOR)
            .put("Less", NORMALIZED_TOKEN_OPERATOR)
            .put("Greater", NORMALIZED_TOKEN_OPERATOR)
            .put("PlusAssign", NORMALIZED_TOKEN_OPERATOR)
            .put("MinusAssign", NORMALIZED_TOKEN_OPERATOR)
            .put("StarAssign", NORMALIZED_TOKEN_OPERATOR)
            .put("DivAssign", NORMALIZED_TOKEN_OPERATOR)
            .put("ModAssign", NORMALIZED_TOKEN_OPERATOR)
            .put("XorAssign", NORMALIZED_TOKEN_OPERATOR)
            .put("AndAssign", NORMALIZED_TOKEN_OPERATOR)
            .put("OrAssign", NORMALIZED_TOKEN_OPERATOR)
            .put("LeftShiftAssign", NORMALIZED_TOKEN_OPERATOR)
            .put("RightShiftAssign", NORMALIZED_TOKEN_OPERATOR)
            .put("Equal", NORMALIZED_TOKEN_OPERATOR)
            .put("NotEqual", NORMALIZED_TOKEN_OPERATOR)
            .put("LessEqual", NORMALIZED_TOKEN_OPERATOR)
            .put("GreaterEqual", NORMALIZED_TOKEN_OPERATOR)
            .put("AndAnd", NORMALIZED_TOKEN_OPERATOR)
            .put("OrOr", NORMALIZED_TOKEN_OPERATOR)
            .put("PlusPlus", NORMALIZED_TOKEN_OPERATOR)
            .put("MinusMinus", NORMALIZED_TOKEN_OPERATOR)
            .put("Comma", NORMALIZED_TOKEN_OPERATOR)
            .put("ArrowStar", NORMALIZED_TOKEN_OPERATOR)
            .put("Arrow", NORMALIZED_TOKEN_OPERATOR)
            .put("Question", NORMALIZED_TOKEN_OPERATOR)
            .put("Colon", NORMALIZED_TOKEN_OPERATOR)
            .put("Doublecolon", NORMALIZED_TOKEN_OPERATOR)
            .put("Semi", "")// verify it
            .put("Dot", NORMALIZED_TOKEN_OPERATOR)
            .put("DotStar", NORMALIZED_TOKEN_OPERATOR)
            .put("Ellipsis", NORMALIZED_TOKEN_OPERATOR)

            .put("Identifier", NORMALIZED_TOKEN_NAME)
            .put("DecimalLiteral", NORMALIZED_TOKEN_VALUE)
            .put("OctalLiteral", NORMALIZED_TOKEN_VALUE)
            .put("HexadecimalLiteral", NORMALIZED_TOKEN_VALUE)
            .put("BinaryLiteral", NORMALIZED_TOKEN_VALUE)

            .put("Integersuffix", "")

            .put("UserDefinedIntegerLiteral", NORMALIZED_TOKEN_VALUE)
            .put("UserDefinedFloatingLiteral", NORMALIZED_TOKEN_VALUE)
            .put("UserDefinedStringLiteral", NORMALIZED_TOKEN_VALUE)
            .put("UserDefinedCharacterLiteral", NORMALIZED_TOKEN_VALUE)

            .put("Whitespace", "")
            .put("Newline", "")
            .put("BlockComment", "")
            .put("LineComment", "")
            .build();

    /**
     * Constructor that refer to parent Object class. Required for calling Class::newInstance.
     */
    public CPPNormalizer() {
        super();
    }

    /**
     * Configures the normalization settings according to the input NormalizerMode.
     *
     * @param normalizerMode Configuration specifications for which category of symbols are to be normalised
     */
    @Override
    public void configure(NormalizerMode normalizerMode) {
        CPPNormalizerMode cppNormalizerMode = (CPPNormalizerMode) normalizerMode;
        normalizeSettings = ImmutableMap.<String, Boolean>builder()
                .put(NORMALIZED_TOKEN_KEYWORD, cppNormalizerMode.isKeywordToBeNormalised())
                .put(NORMALIZED_TOKEN_VALUE, cppNormalizerMode.isValueToBeNormalised())
                .put(NORMALIZED_TOKEN_STRING, cppNormalizerMode.isStringToBeNormalised())
                .put(NORMALIZED_TOKEN_OPERATOR, cppNormalizerMode.isOperatorToBeNormalised())
                .put(NORMALIZED_TOKEN_NAME, cppNormalizerMode.isNameToBeNormalised())
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
