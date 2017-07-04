import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.InvalidFormatException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Detects sentences in a paragraph and returns an array of Strings
 */

public class SetenceDetect {
    public void SentenceDetect() throws InvalidFormatException,
            IOException {

        String paragraph = "Hi. How are you? This is Mike.";

        // always start with a model, a model enSent learned from training data
        InputStream enSent = new FileInputStream("en-sent.bin");

        SentenceModel model = new SentenceModel(enSent);
        SentenceDetectorME sdetector = new SentenceDetectorME(model);

        String sentences[] = sdetector.sentDetect(paragraph);

        System.out.println(sentences[0]);
        System.out.println(sentences[1]);

        enSent.close(); //Close component
    }

    public static void main(String[] args) throws IOException {
        SetenceDetect sd = new SetenceDetect();
        sd.SentenceDetect();
    }
}
