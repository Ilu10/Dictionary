package dictionaryfinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
    static TreeSet<String> allDictionaryString = new TreeSet<>();

    public static void readDictionaryWords(String givenWord) {
        int wc = 0;
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader("Dictionary.txt")))) {
            for (;sc.hasNext(); ) {
                String ow=givenWord.toUpperCase();
                String dictionaryWord = sc.next();
                if (dictionaryWord.length() <= ow.length()) {
                    boolean charFound = true;
                    for (char c : dictionaryWord.toCharArray()) {
                        if (!ow.contains(c + "")) {
                            charFound = false;
                            break;
                        }else {
                            ow = ow.replaceFirst(c + "", "");
                        }
                    }
                    if (charFound) {
                        System.out.println(dictionaryWord);
                        allDictionaryString.add(dictionaryWord);
                        wc++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Dictionary is not avaialble");
        }finally {
            System.out.println(allDictionaryString);
            System.out.println(wc);

        }
    }

    private static boolean isEnglishWord(String word) {
        boolean englishWordOnly = true;
        for (char c : word.toCharArray()) {
            if (!Character.isAlphabetic(c)) {
                englishWordOnly = false;
                break;
            }
        }
        return englishWordOnly;
    }

    public static void main(String[] args) {
        String myWord = "Working";
        if (isEnglishWord(myWord)) {
            readDictionaryWords(myWord);
        }
    }
}
