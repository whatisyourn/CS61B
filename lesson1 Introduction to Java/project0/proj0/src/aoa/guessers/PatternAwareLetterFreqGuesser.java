package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;
    List<String> currency;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
        currency = words;
    }
    /**修改当前字典中的值，使用单词匹配的方法
     */
    public Map<Character, Integer> keepOnlyWordsThatMatchPattern(String pattern){
        //首先找到当前patter对应的值和长度
        int len = pattern.length();
        Map<Character, Integer> find = new HashMap<>();//生成一个pattern中单词对应的位置
        List<String> t = new ArrayList<>();
        for(int i=0; i<len; ++i){
            Character tmp = pattern.charAt(i);
            if(tmp == '-'){
                continue;
            }
            //在判断是否是有两个值
            if(find.containsKey(tmp)){
                continue;
            }
            find.put(tmp, i);
        }
        //然后修改currency里面的数据
        for(String s : currency){
            if(s.length() == len){
                //判断find中所有的值是否在相同的位置出现了
                boolean flag = true;//单词是否有效的判断
                for(Character c : find.keySet()){
                    if(flag == false) break;
                    int index = find.get(c);
                    if(s.charAt(index) != c){
                        flag = false;
                    }
                }
                if(flag){
                    t.add(s);
                }
            }
        }
        //修改currency
        currency = t;
        //获取currency的频率字典
        Map<Character, Integer> m = new HashMap<>();
        for(String s : currency){
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

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        char max = '?';//当前次数最多的单词
        int count = 0;//当前最多出现单词的次数
        Map<Character, Integer> m = keepOnlyWordsThatMatchPattern(pattern);//获取频率字典
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
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.words);
        System.out.println(palfg.getGuess("---l", List.of('l', 'a')));
        System.out.println(palfg.currency);
        System.out.println(palfg.keepOnlyWordsThatMatchPattern("---l"));
    }
}