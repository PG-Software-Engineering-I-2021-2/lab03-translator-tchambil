package cs.lab.req;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.String;
import java.util.Hashtable;
import java.util.logging.Logger;
public class CSTranslatorTest {
    static final Logger logger = Logger.getLogger(CSTranslatorTest.class.getName());
    static Hashtable<String, String> translationTemporaryStorage = new Hashtable<String, String>();
    @Test
    public void testCase0() throws Exception {
        generic(0);
    }

    @Test(invocationCount = 80, threadPoolSize = 80)
    public void testCase1() throws Exception {
        generic(0);
    }

    @Test
    public void testCase3() throws Exception {
        long begginTime = System.currentTimeMillis();
        long maxExecutionTime = 400;
        generic(0);
        long finalTime = System.currentTimeMillis();
        long executionTime = finalTime - begginTime;
        logger.info(String.valueOf(executionTime));
        Assert.assertTrue(executionTime < maxExecutionTime);
    }
    private void generic(int i) throws Exception {
        String input = readInput(i);
        String output = readOutput(i);
        String response = "";
        if(translationTemporaryStorage.containsKey(input)){
            response = translationTemporaryStorage.get(input);
        } else {
            CSTranslator csTranslator = new CSTranslator();
            response = csTranslator.translateWeb(input);
            translationTemporaryStorage.put(input,response);
        }
        Assert.assertEquals(response.toLowerCase(), output.toLowerCase());
    }
    private String readInput(int testNumber){
        List<String> lines = readFile(testNumber, "input");
        return lines.get(0);
    }

    private String readOutput(int testNumber){
        List<String> lines = readFile(testNumber, "output");
        return lines.get(0);
    }

    public List<String> readFile(int testNumber, String type){
        String fileName = "test_case<testNumber>_<type>";
        fileName = fileName.replace("<testNumber>", Integer.toString(testNumber));
        fileName = fileName.replace("<type>", type);
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        Scanner scan = new Scanner(is);
        List<String> lines = new ArrayList<String>();
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            lines.add(line);
        }
        return lines;
    }

}
