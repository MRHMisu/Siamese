package crest.siamese.language.c;

import crest.siamese.document.Method;
import crest.siamese.language.MethodParser;
import crest.siamese.settings.Settings;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


/**
 * This class responsible for extracting all the C function block as Method objects
 */
public class CMethodParser implements MethodParser {


    private String FILE_PATH;
    private String MODE;

    /**
     * Constructor that refer to parent Object class. Required for calling Class::newInstance.
     */
    public CMethodParser() {
        super();
    }


    /**
     * Initialises a CMethodParser
     *
     * @param filePath       Location of input file
     * @param prefixToRemove Legacy parameter
     * @param mode           Method level or Class level parsing
     * @param isPrint        Legacy parameter
     */
    public CMethodParser(String filePath, String prefixToRemove, String mode, boolean isPrint) {
        this.FILE_PATH = filePath;
        this.MODE = mode;
    }


    /**
     * License extraction is not implemented for C
     *
     * @return null
     */
    @Override
    public String getLicense() {
        return null;
    }

    /**
     * Configures constructor attributes when initialised with Class.newInstance() in Siamese.java
     *
     * @param filePath       Location of input file
     * @param prefixToRemove Legacy parameter
     * @param mode           Method level or Class level parsing
     * @param isPrint        Legacy parameter
     */
    @Override
    public void configure(String filePath, String prefixToRemove, String mode, boolean isPrint) {
        this.FILE_PATH = filePath;
        this.MODE = mode;
    }

    /**
     * Uses ANTLR4 generated Parser and Lexer to extract methods from C source code file
     *
     * @return ArrayList of methods extracted from FILE_PATH attribute
     */
    @Override
    public ArrayList<Method> parseMethods() {
        ArrayList<Method> methods = new ArrayList<>();
        File file = new File(this.FILE_PATH);
        if (MODE.equals(Settings.MethodParserType.METHOD)) {
            CParseTreeListener cParseTreeListener = getTraversedCParseTreeListener(file);
            methods.addAll(cParseTreeListener.getCMethods());
        } else {
            ParseTree parseTree = getParsedTree(file);
            CParseTreeListener cParseTreeListener = new CParseTreeListener(file.getAbsolutePath());
            methods.add(cParseTreeListener.getFileBlockMethod(parseTree));
        }
        return methods;
    }


    /**
     * Create a Traversed CParseTreeListener for a given C source file.
     *
     * @param sourceFile C source file
     * @return A fully Traversed CParseTreeListener instance
     */
    protected CParseTreeListener getTraversedCParseTreeListener(File sourceFile) {
        ParseTree parseTree = getParsedTree(sourceFile);
        CParseTreeListener cParseTreeListener = new CParseTreeListener(sourceFile.getPath());
        ParseTreeWalker.DEFAULT.walk(cParseTreeListener, parseTree);
        return cParseTreeListener;
    }

    /**
     * Create a  ParseTree for a given C source file.
     *
     * @param sourceFile C source file
     * @return An instance of a ParseTree fot the give C source file.
     */

    protected ParseTree getParsedTree(File sourceFile) {
        CParser parser = getCParser(sourceFile);
        try {
            return parser.translationUnit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return getCParser(new File("empty.js")).translationUnit();
    }


    /**
     * Create a  CParser for a given C source file.
     *
     * @param sourceFile C source file
     * @return An instance of a CParser fot the give C source file.
     */

    protected CParser getCParser(File sourceFile) {
        CharStream sourceStream = CharStreams.fromString(" ");
        if (sourceFile.isFile()) {
            sourceStream = getSourceAsCharStreams(sourceFile);
        }
        return new Builder.Parser(sourceStream).build();
    }


    /**
     * Read file content as CharStream for a given file
     *
     * @param sourceFile Source file
     * @return File content as CharStream and if IOException occurs then return CharStream of an empty string.
     */
    protected CharStream getSourceAsCharStreams(File sourceFile) {
        CharStream input = CharStreams.fromString(" ");
        try {
            Path sourcePath = Paths.get(sourceFile.getPath());
            input = CharStreams.fromPath(sourcePath);
            return input;
        } catch (IOException e) {
            System.out.println(e.toString());
            return input;
        }
    }
}
