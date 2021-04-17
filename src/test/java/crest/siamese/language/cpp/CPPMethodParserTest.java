package crest.siamese.language.cpp;

import crest.siamese.document.Method;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class CPPMethodParserTest {


    final String FILE_NAME = "crest/siamese/language/cpp/CPP_functions.cpp";
    final String METHOD_MOOD = "METHOD-LEVEL";
    final String FILE_MOOD = "FILE-LEVEL";


    @Test
    public void parseMethodsMethodLevelTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(FILE_NAME)).getFile());
        CPPMethodParser cppMethodParser = new CPPMethodParser();
        cppMethodParser.configure(file.getAbsolutePath(), StringUtils.EMPTY, METHOD_MOOD, false);
        ArrayList<Method> methods = cppMethodParser.parseMethods();
        assertEquals(methods.size(), 2);

    }

    @Test
    public void parseMethodsFileLevelTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(FILE_NAME)).getFile());
        CPPMethodParser cppMethodParser = new CPPMethodParser();
        cppMethodParser.configure(file.getAbsolutePath(), StringUtils.EMPTY, FILE_MOOD, false);
        ArrayList<Method> methods = cppMethodParser.parseMethods();
        assertEquals(methods.size(), 1);

    }
}
