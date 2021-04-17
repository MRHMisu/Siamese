package crest.siamese.language.c;

import crest.siamese.document.Method;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class CMethodParserTest {


    final String FILE_NAME = "crest/siamese/language/c/C_functions.c";
    final String METHOD_MOOD = "METHOD-LEVEL";
    final String FILE_MOOD = "FILE-LEVEL";


    @Test
    public void parseMethodsMethodLevelTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(FILE_NAME)).getFile());
        CMethodParser cMethodParser = new CMethodParser();
        cMethodParser.configure(file.getAbsolutePath(), StringUtils.EMPTY, METHOD_MOOD, false);
        ArrayList<Method> methods = cMethodParser.parseMethods();
        assertEquals(methods.size(), 2);

    }

    @Test
    public void parseMethodsFileLevelTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(FILE_NAME)).getFile());
        CMethodParser cMethodParser = new CMethodParser();
        cMethodParser.configure(file.getAbsolutePath(), StringUtils.EMPTY, FILE_MOOD, false);
        ArrayList<Method> methods = cMethodParser.parseMethods();
        assertEquals(methods.size(), 1);

    }
}
