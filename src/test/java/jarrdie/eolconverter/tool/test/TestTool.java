package jarrdie.eolconverter.tool.test;

public class TestTool {

    public static void testDefaultConstructor(Class testClass) throws Exception {
        String testClassName = testClass.getCanonicalName();
        String className = testClassName.replace("Test", "");
        Class classObject = Class.forName(className);
        classObject.newInstance();
        assert true;
    }

}
