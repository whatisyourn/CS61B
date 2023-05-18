package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern = "";

    public RandomChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in/
        //如果长度不符合要求抛出异常
        if(wordLength < 1) {
            throw new IllegalArgumentException();
        }
        List<String> word = FileUtils.readWords(dictionaryFile);
        List<String> words = new ArrayList<>();
        //筛选出长度是wordLength的单词
        for(String s : word){
            if(s.length() == wordLength){
                words.add(s);
            }
        }
        //如果没有单词
        if(words.isEmpty()){
            throw new IllegalStateException();
        }
        int numWords = words.size();
        int randomlyChosenWordNumber = StdRandom.uniform(numWords);
        chosenWord = words.get(randomlyChosenWordNumber);
        for(int i=0; i<chosenWord.length();++i){
            pattern += "-";
        }
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        Integer count = 0;
        String s="";
        for(int i=0; i<chosenWord.length();++i){
            //首先对比每个字母
            char a = chosenWord.charAt(i);
            if(a==letter){
                count++;
                s += String.valueOf(letter);
            }
            else{
                s += String.valueOf(pattern.charAt(i));
            }
        }
        pattern = s;
        return count;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return chosenWord;
    }

    public static void main(String[] args)
    {
        RandomChooser rc = new RandomChooser(4, "data/example-ea.txt");

        // Check occurrences and pattern after guessing e
        System.out.println(rc.getPattern());
        System.out.println(rc.getWord());
        int occurencesOfE = rc.makeGuess('e');
        System.out.println(rc.getPattern());
    }
}
