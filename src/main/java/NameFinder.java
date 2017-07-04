import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Finds the names in context
 */
public class NameFinder {
    public static void findName() throws IOException {
        String sentence = "Mike Smith has a friend named Bob Dylan";

        InputStream isNameFinder = new FileInputStream("en-ner-person.bin");

        TokenNameFinderModel model = new TokenNameFinderModel(isNameFinder);
        isNameFinder.close();

        NameFinderME nameFinder = new NameFinderME(model);

//        Span nameSpans[] = nameFinder.find(sentence);
//
//        for(Span s: nameSpans) {
//            System.out.println(s);
//            //[start of span..end of span)
//            //start and end spans tell you whoch words in the sentence are names
//            // Prints out the type of entity parsed at the end of sentence
//        }

        //Print the names found in the name finder

        //1. convert sentence into tokens
        InputStream modelInToken = new FileInputStream("en-token.bin");
        TokenizerModel modelToken = new TokenizerModel(modelInToken);
        Tokenizer tokenizer = new TokenizerME(modelToken);
        String tokens[] = tokenizer.tokenize(sentence);

        //2. find names
        Span nameSpans[] = nameFinder.find(tokens);


        //find probabilities for names
        double[] spanProbs = nameFinder.probs(nameSpans);

        //3. print names
        String name;
        double probability;
        for( int i = 0; i<nameSpans.length; i++) {
            probability = spanProbs[i];
            System.out.println("Span: "+nameSpans[i].toString());
            if(nameSpans[i].getStart()==nameSpans[i].getEnd()-1){
                name = tokens[nameSpans[i].getStart()];
                System.out.println("Covered text: "+ name);
                System.out.println("Probability that " + name + " is a person is "+probability);
            }
            else {
                name = tokens[nameSpans[i].getStart()] + " " + tokens[nameSpans[i].getEnd() - 1];
                System.out.println("Covered text: " + name);
                System.out.println("Probability that " + name+ " is a person is " + probability);
            }

        }

    }

    public static void main(String[] args) throws IOException {
            NameFinder.findName();
        }
    }

