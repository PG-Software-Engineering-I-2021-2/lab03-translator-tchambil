package cs.lab.req;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            CSTranslator csTranslator = new CSTranslator();
            csTranslator.translateWeb(input.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
