package crest.siamese.language.c;

import com.google.common.collect.ImmutableMap;
import crest.siamese.language.Normalizer;
import crest.siamese.language.NormalizerMode;

import java.util.ArrayList;
import java.util.Map;


public class CNormalizer implements Normalizer {

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
            .put("T__0", "")
            .put("T__1", "")
            .put("T__2", "")
            .put("T__3", "")
            .put("T__4", "")
            .put("T__5", "")
            .put("T__6", "")
            .put("T__7", "")
            .put("T__8", "")
            .put("T__9", "")
            .put("T__10", "")
            .put("T__11", "")
            .put("T__12", "")
            .put("T__13", "")
            .put("Auto", "")
            .put("Break", "")
            .put("Case", "")
            .put("Char", "")
            .put("Const", "")
            .put("Continue", "")
            .put("Default", "")
            .put("Do", "")
            .put("Double", "")
            .put("Else", "")
            .put("Enum", "")
            .put("Extern", "")
            .put("Float", "")
            .put("For", "")
            .put("Goto", "")
            .put("If", "")
            .put("Int", "")
            .put("Long", "")
            .put("Register", "")
            .put("Restrict", "")
            .put("Return", "")
            .put("Short", "")
            .put("Signed", "")
            .put("Sizeof", "")
            .put("Static", "")
            .put("Struct", "")
            .put("Typedef", "")
            .put("Union", "")
            .put("Unsigned", "")
            .put("Void", "")
            .put("Volatile", "")
            .put("While", "")
            .put("Alignas", "")
            .put("Alignof", "")
            .put("Atomic", "")
            .put("Bool", "")
            .put("Complex", "")
            .put("Generic", "")
            .put("Imaginary", "")
            .put("Noreturn", "")
            .put("StaticAssert", "")
            .put("ThreadLocal", "")
            .put("LeftParen", "")
            .put("RightParen", "")
            .put("LeftBracket", "")
            .put("RightBracket", "")
            .put("LeftBrace", "")
            .put("RightBrace", "")
            .put("Less", "")
            .put("LessEqual", "")
            .put("Greater", "")
            .put("GreaterEqual", "")
            .put("LeftShift", "")
            .put("RightShift", "")
            .put("Plus", "")
            .put("PlusPlus", "")
            .put("Minus", "")
            .put("MinusMinus", "")
            .put("Star", "")
            .put("Div", "")
            .put("Mod", "")
            .put("And", "")
            .put("Or", "")
            .put("AndAnd", "")
            .put("OrOr", "")
            .put("Caret", "")
            .put("Not", "")
            .put("Tilde", "")
            .put("Question", "")
            .put("Colon", "")
            .put("Semi", "")
            .put("Comma", "")
            .put("Assign", "")
            .put("StarAssign", "")
            .put("DivAssign", "")
            .put("ModAssign", "")
            .put("PlusAssign", "")
            .put("MinusAssign", "")
            .put("LeftShiftAssign", "")
            .put("RightShiftAssign", "")
            .put("AndAssign", "")
            .put("XorAssign", "")
            .put("OrAssign", "")
            .put("Equal", "")
            .put("NotEqual", "")
            .put("Arrow", "")
            .put("Dot", "")
            .put("Ellipsis", "")
            .put("Identifier", "")
            .put("Constant", "")
            .put("DigitSequence", "")
            .put("StringLiteral", "")
            .put("ComplexDefine", "")
            .put("IncludeDirective", "")
            .put("AsmBlock", "")
            .put("LineAfterPreprocessing", "")
            .put("LineDirective", "")
            .put("PragmaDirective", "")
            .put("Whitespace", "")
            .put("Newline", "")
            .put("BlockComment", "")
            .put("LineComment", "")
            .build();

    /**
     * Constructor that refer to parent Object class. Required for calling Class::newInstance.
     */
    public CNormalizer() {
        super();
    }

    /**
     * Configures the normalization settings according to the input NormalizerMode.
     *
     * @param normalizerMode Configuration specifications for which category of symbols are to be normalised
     */
    @Override
    public void configure(NormalizerMode normalizerMode) {
        CNormalizerMode python3NormalizerMode = (CNormalizerMode) normalizerMode;
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
