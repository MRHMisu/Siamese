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
 * This class responsible for extracting all the CSharp function block as Method objects
 */
public class CSharpMethodParser implements MethodParser {


    private String FILE_PATH;
    private String MODE;

    /**
     * Constructor that refer to parent Object class. Required for calling Class::newInstance.
     */
    public CSharpMethodParser() {
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
    public CSharpMethodParser(String filePath, String prefixToRemove, String mode, boolean isPrint) {
        this.FILE_PATH = filePath;
        this.MODE = mode;
    }


    /**
     * License extraction is not implemented for CSharp
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
     * Uses ANTLR4 generated Parser and Lexer to extract methods from CSharp source code file
     *
     * @return ArrayList of methods extracted from FILE_PATH attribute
     */
    @Override
    public ArrayList<Method> parseMethods() {
        ArrayList<Method> methods = new ArrayList<>();
        File file = new File(this.FILE_PATH);
        if (MODE.equals(Settings.MethodParserType.METHOD)) {
            CSharpParseTreeListener cSharpParseTreeListener = getTraversedCSharpParseTreeListener(file);
            methods.addAll(cSharpParseTreeListener.getCSharpMethods());
        } else {
            ParseTree parseTree = getParsedTree(file);
            CSharpParseTreeListener cParseTreeListener = new CSharpParseTreeListener(file.getAbsolutePath());
            methods.add(cParseTreeListener.getFileBlockMethod(parseTree));
        }
        return methods;
    }


    /**
     * Create a Traversed CSharpParseTreeListener for a given CSharp source file.
     *
     * @param sourceFile CSharp source file
     * @return A fully Traversed CSharpParseTreeListener instance
     */
    protected CSharpParseTreeListener getTraversedCSharpParseTreeListener(File sourceFile) {
        ParseTree parseTree = getParsedTree(sourceFile);
        CSharpParseTreeListener cParseTreeListener = new CSharpParseTreeListener(sourceFile.getPath());
        ParseTreeWalker.DEFAULT.walk(cParseTreeListener, parseTree);
        return cParseTreeListener;
    }

    /**
     * Create a  ParseTree for a given CSharpsource file.
     *
     * @param sourceFile CSharp source file
     * @return An instance of a ParseTree fot the given CSharp source file.
     */

    protected ParseTree getParsedTree(File sourceFile) {
        CSharpParser parser = getCSharpParser(sourceFile);
        try {
            return parser.compilation_unit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return getCSharpParser(new File("empty.cs")).compilation_unit();
    }


    /**
     * Create a  CParser for a given CSharp source file.
     *
     * @param sourceFile CSharp source file
     * @return An instance of a CSharpParser fot the given CSharp source file.
     */

    protected CSharpParser getCSharpParser(File sourceFile) {
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
