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
            .put("IntegerLiteral", "")
            .put("CharacterLiteral", "")
            .put("FloatingLiteral", "")
            .put("StringLiteral", "")
            .put("BooleanLiteral", "")
            .put("PointerLiteral", "")
            .put("UserDefinedLiteral", "")
            .put("MultiLineMacro", "")
            .put("Directive", "")
            .put("Alignas", "")
            .put("Alignof", "")
            .put("Asm", "")
            .put("Auto", "")
            .put("Bool", "")
            .put("Break", "")
            .put("Case", "")
            .put("Catch", "")
            .put("Char", "")
            .put("Char16", "")
            .put("Char32", "")
            .put("Class", "")
            .put("Const", "")
            .put("Constexpr", "")
            .put("Const_cast", "")
            .put("Continue", "")
            .put(" Decltype", "")
            .put("Default", "")
            .put("Delete", "")
            .put("Do", "")
            .put("Double", "")
            .put("Dynamic_cast", "")
            .put("Else", "")
            .put("Enum", "")
            .put("Explicit", "")
            .put("Export", "")
            .put("Extern", "")
            .put("False_", "")
            .put("Final", "")
            .put("Float", "")
            .put("For", "")
            .put("Friend", "")
            .put("Goto", "")
            .put("If", "")
            .put("Inline", "")
            .put("Int", "")
            .put("Long", "")
            .put("Mutable", "")
            .put("Namespace", "")
            .put("New", "")
            .put("Noexcept", "")
            .put("Nullptr", "")
            .put("Operator", "")
            .put("Override", "")
            .put("Private", "")
            .put("Protected", "")
            .put("Public", "")
            .put("Register", "")
            .put("Reinterpret_cast", "")
            .put("Return", "")
            .put("Short", "")
            .put("Signed", "")
            .put("Sizeof", "")
            .put("Static", "")
            .put("Static_assert", "")
            .put("Static_cast", "")
            .put("Struct", "")
            .put("Switch", "")
            .put("Template", "")
            .put("This", "")
            .put("Thread_local", "")
            .put("Throw", "")
            .put("True_", "")
            .put("Try", "")
            .put("Typedef", "")
            .put("Typeid_", "")
            .put("Typename_", "")
            .put("Union", "")
            .put(" Unsigned", "")
            .put("Using", "")
            .put("Virtual", "")
            .put("Void", "")
            .put(" Volatile", "")
            .put("Wchar", "")
            .put("While", "")
            .put("LeftParen", "")
            .put("RightParen", "")
            .put("LeftBracket", "")
            .put("RightBracket", "")
            .put("LeftBrace", "")
            .put("RightBrace", "")
            .put("Plus", "")
            .put("Minus", "")
            .put("Star", "")
            .put("Div", "")
            .put("Mod", "")
            .put("Caret", "")
            .put("And", "")
            .put("Or", "")
            .put("Tilde=", "")
            .put("Not", "")
            .put("Assign", "")
            .put("Less", "")
            .put("Greater", "")
            .put("PlusAssign", "")
            .put("MinusAssign", "")
            .put("StarAssign", "")
            .put("DivAssign", "")
            .put("ModAssign", "")
            .put("XorAssign", "")
            .put("AndAssign", "")
            .put("OrAssign", "")
            .put("LeftShiftAssign", "")
            .put("RightShiftAssign", "")
            .put("Equal", "")
            .put("NotEqual", "")
            .put("LessEqual", "")
            .put("GreaterEqual", "")
            .put("AndAnd", "")
            .put("OrOr", "")
            .put("PlusPlus", "")
            .put("MinusMinus", "")
            .put("Comma", "")
            .put("ArrowStar", "")
            .put("Arrow", "")
            .put("Question", "")
            .put("Colon", "")
            .put("Doublecolon", "")
            .put("Semi", "")
            .put("Dot", "")
            .put("DotStar", "")
            .put("Ellipsis", "")
            .put("Identifier", "")
            .put("DecimalLiteral", "")
            .put("OctalLiteral", "")
            .put("HexadecimalLiteral", "")
            .put("BinaryLiteral", "")
            .put("Integersuffix", "")
            .put("UserDefinedIntegerLiteral", "")
            .put("UserDefinedFloatingLiteral", "")
            .put("UserDefinedStringLiteral", "")
            .put("UserDefinedCharacterLiteral", "")
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
        CPPNormalizerMode python3NormalizerMode = (CPPNormalizerMode) normalizerMode;
        normalizeSettings = ImmutableMap.<String, Boolean>builder()
                .put(NORMALIZED_TOKEN_KEYWORD, python3NormalizerMode.isKeywordToBeNormalised())
                .put(NORMALIZED_TOKEN_VALUE, python3NormalizerMode.isValueToBeNormalised())
                .put(NORMALIZED_TOKEN_STRING, python3NormalizerMode.isStringToBeNormalised())
                .put(NORMALIZED_TOKEN_OPERATOR, python3NormalizerMode.isOperatorToBeNormalised())
                .put(NORMALIZED_TOKEN_NAME, python3NormalizerMode.isNameToBeNormalised())
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
