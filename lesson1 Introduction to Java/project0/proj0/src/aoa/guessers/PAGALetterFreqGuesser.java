package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        //获取pattern长度
        int len = pattern.length();
        //获取pattern中的映射关系
        Map<Character, List<Integer> > f = new HashMap<>();
        for(int i=0; i<len; ++i){
            char tmp = pattern.charAt(i);
            if(tmp == '-') continue;
            //再判断字典中是否存在
            if(f.containsKey(tmp)){
                List<Integer> l= f.get(tmp);
                l.add(i);
                f.put(tmp, l);
            }else{
                List<Integer> l = new ArrayList<>();
                l.add(i);
                f.put(tmp, l);
            }
        }

        //遍历所有单词
        Map<Character, Integer> m = new HashMap<>();
        for(String s: words){
            //首先判断是否要记录其字母频率
            Boolean flag = true;
            //首先长度匹配
            if(s.length() != len) continue;
            //然后是pattern匹配
            for(Character c : f.keySet()){
                //判断pattern
                for(Integer i : f.get(c)){
                    if(s.charAt(i) != c){
                        flag = false;
                        break;
                    }
                }
                if(flag == false) break;
            }
            if(flag == false) continue;
            //+guesses的判断，要求其不在pattern中出现
            for(int i=0; i<s.length(); ++i){
                char tmp = (s.charAt(i));
                //如果单词中存在guesses但不在pattern的值就不加入
                if(guesses.contains(tmp) && !f.containsKey(tmp)){
                    flag = false;
                    break;
                }
            }
            if(flag == false) continue;
            //System.out.println(s);
            //然后进行频率统计
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

        //找到频率最大的单词输出
        char max = '?';//当前次数最多的单词
        int count = 0;//当前最多出现单词的次数
        for(Character a : m.keySet()){
            int tmp = m.get(a);//获取当前字母的出现次数
            //如果改字母是已经在列表中的就不去猜
            if(guesses.contains(a)){
                continue;
            }
            if(count < tmp){
                max = a;
                count = tmp;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.words);
        System.out.println(pagalfg.getGuess("-e--", List.of('e')));

    }
}
