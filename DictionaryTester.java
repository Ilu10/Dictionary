import org.mockito.junit.MockitoJUnitRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DictionaryTester {


        private Dictionary dictionary;
        private DictionaryService dictService;
        List<String> dictionaryList;


        public void setUp() {
            dictionary = new Dictionary();
            dictService = mock(DictionaryService.class);
            dictionary.setDictionaryService(dictService);
            // I'm adding a mocked dictionary here
            when(dictService.getDictionary()).thenReturn(createDictionaryArray());
            dictionaryList = dictService.getDictionary();
        }

        /**
         * Create String list based on the Dictionary file (EnglishWords in this case) to mock the dictionary service
         * @return
         */
        static List<String> createDictionaryArray() {
            List<String> listDictionary = new ArrayList<String>();
            BufferedReader reader;

            try {
                ClassLoader loader = DictionaryTester.class.getClassLoader();
                File file = new File(loader.getResource("Dictionary").getFile());
                reader = new BufferedReader(new FileReader(file));
                //reader = new BufferedReader(new FileReader("EnglishWords"));
                String line = reader.readLine();
                while (line != null) {
                    listDictionary.add(line);
                    line = reader.readLine(); // read next line
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return listDictionary;
        }

        /**
         * Validate if given word exists in the dictionary (EnglishWords in this case) to mock the isEnglishWord function
         * @param word
         * @return
         */
        public boolean isThisEnglish(String word) {
            for (String w : dictionaryList) {
                if (w.equals(word.toLowerCase())) {
                    System.out.println(word + " is a valid english word");
                    return true;
                }
            }
            return false;
        }
    @Test
    public void validateWorkingWord() {
        when(dictService.isEnglishWord("WORKING")).thenReturn(isThisEnglish("WORKING"));
        Assert.assertTrue(dictionary.isEnglishWord("WORKING"));
        // Print all possible words
        dictionary.findPossibleWords("WORKING");
    }

}



