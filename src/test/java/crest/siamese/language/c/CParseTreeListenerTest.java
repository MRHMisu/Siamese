package crest.siamese.language.c;

import crest.siamese.document.Method;
import crest.siamese.document.Parameter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CParseTreeListenerTest {

    final String DUMMY_FILE_PATH = "crest/siamese/language/c/DemoTest.c";
    final String DUMMY_SOURCE_NAME="dummy.c";


    ParseTree parseTree;
    CParseTreeListener cParseTreeListener;


    public void init(String source) {
        CharStream sourceStream = CharStreams.fromString(source, DUMMY_SOURCE_NAME);
        CParser parser = new Builder.Parser(sourceStream).build();
        parseTree = parser.translationUnit();
        cParseTreeListener = new CParseTreeListener(DUMMY_FILE_PATH);
    }


    @Test
    public void traverseParseTreeTest() {

        String source = "int factorial ( int n ) { if ( n == 0 ) { return 1 ; } return n * factorial ( n - 1 ) ; }";
        init(source);
        List<String> terminalNodes = Arrays.asList(source.split("\\s"));

        ParseTreeWalker.DEFAULT.walk(new CBaseListener() {
            @Override
            public void enterFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
                List<String> nodes = cParseTreeListener.traverseParseTree(ctx);
                assertEquals(terminalNodes, nodes);
            }
        }, parseTree);
    }

    @Test
    public void buildMethodTest() {
        String source = "int factorial ( int n ) { if ( n == 0 ) { return 1;  } return n * factorial ( n - 1 ) ; }";
        init(source);
        int startLine = 1;
        int endLine = 6;
        String className = StringUtils.EMPTY;
        String functionName = StringUtils.EMPTY;
        String header = StringUtils.EMPTY;
        List<Parameter> parameters = new ArrayList<>();

        Method method = new Method(this.DUMMY_FILE_PATH, StringUtils.EMPTY, className, functionName, StringUtils.EMPTY,
                source, startLine, endLine, parameters, header);

        ParseTreeWalker.DEFAULT.walk(cParseTreeListener, parseTree);
        assertEquals(1, cParseTreeListener.getCMethods().size());
        assertEquals(method, cParseTreeListener.getCMethods().get(0));

    }


}
