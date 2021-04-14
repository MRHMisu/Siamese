package crest.siamese.language.c;

import crest.siamese.document.Method;
import crest.siamese.document.Parameter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CParseTreeListener extends CBaseListener {
    private List<Method> cMethods;
    private String filePath;
    private final String START = "START";
    private final String END = "END";

    /**
     * Constructor to build CParseTreeListener
     *
     * @param filePath C source file path
     */
    public CParseTreeListener(String filePath) {
        this.filePath = filePath;
        this.cMethods = new ArrayList<>();

    }

    @Override
    public void enterFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        buildMethod(ctx);
    }

    @Override
    public void enterFunctionSpecifier(CParser.FunctionSpecifierContext ctx) {
        buildMethod(ctx);
    }


    protected List<Method> getCMethods() {
        return this.cMethods;
    }
    /**
     * This method builds a Method object using the complete source code of the file.
     *
     * @param tree ANTLR4 generated Parse Tree
     * @return A Method Object containing the whole source code of the file block.
     */
    protected Method getFileBlockMethod(ParseTree tree) {
        String src = getSourceCode(tree);
        Map<String, Integer> range = getRange(tree);
        int startLine = range.get(START);
        int endLine = range.get(END);
        List<Parameter> parameters = new ArrayList<>();

        return new Method(this.filePath, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY,
                StringUtils.EMPTY, src, startLine, endLine, parameters, StringUtils.EMPTY);
    }


    /**
     * This method builds a Complete Method from the ANTLR4 generated Parse Tree.
     * It collects function name, parameters, headers source, start and end line.
     * Moreover, it helps to detect duplicate extracted methods from the Parse Tree.
     *
     * @param tree ANTLR4 generated Parse Tree
     */
    protected void buildMethod(ParseTree tree) {
        Map<String, Integer> range = getRange(tree);
        int startLine = range.get(START);
        int endLine = range.get(END);

        String src = getSourceCode(tree);
        List<Parameter> parameters = new ArrayList<>();

        Method method = new Method(this.filePath, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY,
                StringUtils.EMPTY, src, startLine, endLine, parameters, StringUtils.EMPTY);
        cMethods.add(method);

    }

    /**
     * This method is responsible for retrieving the source code from the ANTLR4 generated Parse Tree.
     * It travers ANTLR4 generated Parse Tree to get the list of terminal nodes and then concatenating
     * the terminal nodes values as string to produce the tokenize source code.
     *
     * @param tree ANTLR4 generated Parse Tree
     * @return Extract function block source code.
     */
    protected String getSourceCode(ParseTree tree) {
        StringBuilder builder = new StringBuilder();
        List<String> terminalNodes = traverseParseTree(tree);
        for (String tm : terminalNodes) {
            builder.append(tm).append(" ");
        }
        return builder.toString().trim();
    }

    /**
     * Travers ANTLR4 generated Parse Tree using Depth-First-Search and collect all the terminal nodes.
     *
     * @param tree tree ANTLR4 generated Parse Tree
     * @return A list of String derived from function block terminal nodes.
     */
    protected List<String> traverseParseTree(ParseTree tree) {
        List<String> terminalNodes = new ArrayList<>();
        List<ParseTree> firstStack = new ArrayList<>();
        firstStack.add(tree);
        List<List<ParseTree>> childListStack = new ArrayList<>();
        childListStack.add(firstStack);
        while (!childListStack.isEmpty()) {
            List<ParseTree> childStack = childListStack.get(childListStack.size() - 1);
            if (childStack.isEmpty()) {
                childListStack.remove(childListStack.size() - 1);
            } else {
                tree = childStack.remove(0);
                if (tree instanceof TerminalNodeImpl) {
                    terminalNodes.add((tree.getText()));
                }
                if (tree.getChildCount() > 0) {
                    List<ParseTree> children = new ArrayList<>();
                    for (int i = 0; i < tree.getChildCount(); i++) {
                        children.add(tree.getChild(i));
                    }
                    childListStack.add(children);
                }
            }
        }
        return terminalNodes;
    }

    /**
     * Identify the method block range from the Parse tree.
     *
     * @param tree ANTLR4 generated Parse Tree
     * @return A Map containing start line and end line of the method block
     */
    protected Map<String, Integer> getRange(ParseTree tree) {
        Map<String, Integer> range = new HashMap<>();
        range.put(START, 1);
        range.put(END, 1);
        if (tree instanceof ParserRuleContext) {
            ParserRuleContext ctx = (ParserRuleContext) tree;
            range.put(START, ctx.getStart().getLine());
            range.put(END, ctx.getStop().getLine());
        }
        return range;
    }

}
