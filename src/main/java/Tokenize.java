import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Tokens are usually words which are separated by space
 */
public class Tokenize {
    public void Tokenize() throws InvalidFormatException, IOException {

        InputStream is = new FileInputStream("en-token.bin");

        TokenizerModel model = new TokenizerModel(is);

        Tokenizer tokenizer = new TokenizerME(model);

        String tokens[] = tokenizer.tokenize("Hi. How are you? This is Mike.");

        for (String a : tokens)
            System.out.println(a);

        is.close();
    }

    public static void main(String[] args) throws IOException {
        Tokenize tokenize = new Tokenize();
        tokenize.Tokenize();
    }
}
