import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        //生成一个m的字典
        Map<Character, Integer> m = new HashMap<>();
        //设置初始值
        char s = 'a';
        //生成26个英文字母
        for(int i=0; i<26; i++){
            //将每个字母和对应值加入到字典中
            m.put(s,i+1);
            //往后一个英文字母
            s++;
        }
        return m;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        //生成一个字典
        Map<Integer, Integer> m = new HashMap<>();
        //遍历列表值
        for(Integer i: nums){
            //将值和其的平方加入
            m.put(i, i * i);
        }
        return m;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> m = new HashMap<>();
        for(String s: words){
            //首先判断是否在字典中
            if(m.containsKey(s)){
                m.replace(s, m.get(s)+1);
            }
            //如果不在字典中则加入
            else{
                m.put(s, 1);
            }
        }
        return m;
    }
}
