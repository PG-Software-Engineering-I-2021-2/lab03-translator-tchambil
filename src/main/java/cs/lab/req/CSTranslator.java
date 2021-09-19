package cs.lab.req;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.util.Scanner;

public class CSTranslator {
    private static final Logger logger = Logger.getLogger(CSTranslator.class.getName());
    private static final HashMap<String, String> ht = new HashMap<>(100);
    private static final Translate translate = TranslateOptions.getDefaultInstance().getService();
    public  String translateWeb(String toBeTranslated) throws IOException { logger.info("Traductor CS!");
        if (toBeTranslated.length() > 500 ) {return "Mucho texto";}

        String urlStr = "https://script.google.com/macros/s/AKfycbw0gAKvEsYNIFUNoBi-GeOIXHsCcDiFkGtpt9ItLgmNOwurm8Jj/exec" +
                "?q=" + URLEncoder.encode(toBeTranslated, "UTF-8") +
                "&target=" + "en" +
                "&source=" + "es" ;

        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();

    }
    public  String translate(String text) {
        if (ht.containsKey(text)) return ht.get(text);
        Translation translation = translate.translate(
                text,
                Translate.TranslateOption.sourceLanguage("es"),
                Translate.TranslateOption.targetLanguage("en"),
                Translate.TranslateOption.model("base"));
        ht.put(text, translation.getTranslatedText());
        return translation.getTranslatedText();
    }

    public  void translate(Scanner scanner) throws IOException {
        logger.info("Traductor CS!");
        logger.info("Ingrese texto a traducir: ");
        String output = translateWeb(scanner.nextLine());
        logger.info("Translated text");
        logger.info(output);
    }
}
