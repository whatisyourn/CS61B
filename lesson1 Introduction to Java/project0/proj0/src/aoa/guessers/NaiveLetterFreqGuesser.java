package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
        // TODO: Fill in this method.
        //生成一个字典m
        Map<Character, Integer> m = new HashMap<>();
        //遍历所有单词列表中的单词
        for(String s : words){
            //遍历单个单词的所有字母
            for(int i=0; i<s.length();++i){
                //获取第i个字母
                char letter = s.charAt(i);
                //如果字母在单词表中出现了
                if(m.containsKey(letter)){
                    m.put(letter, m.get(letter)+1);
                }
                //如果单词表中没出现
                else{
                    m.put(letter,1);
                }
            }
        }
        return m;
    }

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        // TODO: Fill in this method.
        char max = '?';//当前次数最多的单词
        int count = 0;//当前最多出现单词的次数
        Map<Character, Integer> m = getFrequencyMap();//获取频率字典
        for(Character a : m.keySet()){
            //如果改字母是已经在列表中的就不去猜
            if(guesses.contains(a)){
                continue;
            }
            int tmp = m.get(a);//获取当前字母的出现次数
            if(count < tmp){
                max = a;
                count = tmp;
            }

        }

        return max;
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');//完成多个单词的列表添加
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
