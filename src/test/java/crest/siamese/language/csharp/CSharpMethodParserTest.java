package crest.siamese.language.csharp;

import crest.siamese.document.Method;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class CSharpMethodParserTest {


    final String FILE_NAME = "crest/siamese/language/csharp/CSharp_functions.cs";
    final String METHOD_MOOD = "METHOD-LEVEL";
    final String FILE_MOOD = "FILE-LEVEL";


    @Test
    public void parseMethodsMethodLevelTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(FILE_NAME)).getFile());
        CSharpMethodParser cSharpMethodParser = new CSharpMethodParser();
        cSharpMethodParser.configure(file.getAbsolutePath(), StringUtils.EMPTY, METHOD_MOOD, false);
        ArrayList<Method> methods = cSharpMethodParser.parseMethods();
        assertEquals(methods.size(), 2);

    }

    @Test
    public void parseMethodsFileLevelTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(FILE_NAME)).getFile());
        CSharpMethodParser cSharpMethodParser = new CSharpMethodParser();
        cSharpMethodParser.configure(file.getAbsolutePath(), StringUtils.EMPTY, FILE_MOOD, false);
        ArrayList<Method> methods = cSharpMethodParser.parseMethods();
        assertEquals(methods.size(), 1);

    }
}
