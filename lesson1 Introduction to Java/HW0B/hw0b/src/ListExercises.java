import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int sum = 0;//总和值
        //进行值的遍历
        for(int i : L){
            //将列表中的值加和
            sum += i;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        //初始化一个列表
        List<Integer> l = new ArrayList<>();
        //进行列表遍历
        for(Integer i : L){
            //进行偶数的判断
            if(i % 2 == 0){
                //将偶数值加入到列表中
                l.add(i);
            }
        }
        return l;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        //生成一个l的列表
        List<Integer> l = new ArrayList<>();
        //进行循环遍历值
        for(Integer i : L1){
            //判断L1中的值是否在L2中出现
            if(L2.contains(i)){
                //将两个列表公有元素加入列表中
                l.add(i);
            }
        }
        return l;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count = 0;//声明一个计数器
        //首先获取列表中的每一个字符串
        for(String s : words){
            //然后遍历该字符串中每一个字符
            for(int i=0; i<s.length();++i){
                //判断是否为c
                if(c == s.charAt(i)){
                    //如果字符为c则计数器+1
                    count++;
                }
            }
        }
        return count;
    }
}
