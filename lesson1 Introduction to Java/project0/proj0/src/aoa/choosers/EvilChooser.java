package aoa.choosers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

public class EvilChooser implements Chooser {
    private String pattern="";
    private List<String> wordPool;

    public EvilChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in this constructor.
        //如果长度不符合要求抛出异常
        if(wordLength < 1) {
            throw new IllegalArgumentException();
        }
        List<String> word = FileUtils.readWords(dictionaryFile);
        List<String> l =new ArrayList<>();
        //筛选出长度是wordLength的单词
        for(String s : word){
            if(s.length() == wordLength){
                l.add(s);
            }
        }
        wordPool = l;
        //如果没有单词
        if(wordPool.isEmpty()){
            throw new IllegalStateException();
        }
        //然后进行pattern初始化
        for(int i=0; i<wordLength;++i){
            pattern += "-";
        }
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        //初始化一个TreeMap
        Map<String, List<String>> m = new TreeMap<>();
        //对每个单词分别进行划分处理
        for(String s: wordPool){
            //获取每个单词的pattern
            String p = "";
            for(int i=0; i<s.length();++i){
                //首先对比每个字母
                char a = s.charAt(i);
                //如果该字母是猜的字母则揭露
                if(a==letter){
                    p += String.valueOf(letter);
                }
                //否则继续使用前一次的pattern中相对应的值
                else{
                    p += String.valueOf(pattern.charAt(i));
                }
            }
            //将当前的pattern加入到字典中
            //如果pattern存在
            if(m.containsKey(p)){
                List<String> l = m.get(p);
                l.add(s);
                m.put(p, l);
            }
            //如果不在
            else{
                List<String> l = new ArrayList<>();
                l.add(s);
                m.put(p, l);
            }
        }

        //获取数量最大的pattern形式,并且修改这个形式
        int count = 0;
        for(String s : m.keySet()){
            Integer tmp = m.get(s).size();
            if( tmp> count){
                pattern = s;
                count = tmp;
            }
        }
        //修改单词池
        wordPool = m.get(pattern);
        //获取当前pattern该元素的个数
        count =0;
        for(int i=0; i<pattern.length();++i){
            char tmp = pattern.charAt(i);
            if(tmp == letter){
                count++;
            }
        }
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
        return wordPool.get(0);
    }
}
