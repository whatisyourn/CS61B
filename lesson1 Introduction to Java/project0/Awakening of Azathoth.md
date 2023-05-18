# Awakening of Azathoth

在游戏“Awakening of Azathoth”中，玩家的尝试次数有限 通过猜测字母来显示一个单词，一次一个。每次玩家猜到一个 正确的字母，该字母在单词中出现的所有内容都会被揭示出来。但是当玩家猜错了一个字母时， 他们失去了一个猜测机会，没有透露任何字母。如果在猜测次数用完前将所有单词都揭示出来，Azathoth继续沉睡，但如果他们用完了机会，阿扎索斯被唤醒，游戏输了。

让我们从玩游戏开始。基于浏览器 游戏的实现可以[在此链接中找到](https://sp23.datastructur.es/materials/proj/proj0/demo/index.html)。

尝试玩几次，直到您完全理解游戏方式 工程。如果你想要音效，你可以调出一个音量计。

在本项目中，您将首先创建三个不同的“猜测器”，它们是可以玩游戏的AI 玩家。

然后，您将构建两个不同的“选择器”，它们是选择 词待猜测。虽然这似乎是一个微不足道的问题，但事实证明 有一个非常聪明的转折（由[Keith Schwarz](https://www.keithschwarz.com/)提供），使这是一个惊人的数据 结构问题。

在本项目结束时，您将：

- 能够使用您的实现玩Awakening of Azathoth（AoA）的游戏。
- 具有使用 Java 内置`List`和`Map`数据结构的经验。

## Guessers

### `NaiveLetterFreqGuesser`

#### 函数说明

- `getFrequencyMap`：将单词列表words中每个单词出现的频率用字典进行映射
- `getGuess`:猜取下一个除了guesses中最有可能的字母（找出现次数最多的字母）
- `NaiveLetterFreqGuesser`:对象初始化将words读取文本文件内容

```Java
public NaiveLetterFreqGuesser(String dictionaryFile) {
    //初始化函数,读取文件获取单词
    words = FileUtils.readWords(dictionaryFile);
}

/** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
public Map<Character, Integer> getFrequencyMap() {
    // TODO: Fill in this method.
    return null;
}

/** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
public char getGuess(List<Character> guesses) {
    // TODO: Fill in this method.
    return '?';
}

public static void main(String[] args) {
    //New了一个对象，并且读取文本中的单词
    NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
    //输出所有可能的单词
    System.out.println("list of words: " + nlfg.words);
    //输出频率字典
    System.out.println("frequency map: " + nlfg.getFrequencyMap());
	
    List<Character> guesses = List.of('e', 'l');//完成多个单词的列表添加
    //猜下一个单词
    System.out.println("guess: " + nlfg.getGuess(guesses));
}
```

#### 函数完成

```Java
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
            //如果字母在单词表中出现了获取原来的值然后加入
            if(m.containsKey(letter)){
                m.put(letter, m.get(letter)+1);
            }
            //如果单词表中没出现加入字典
            else{
                m.put(letter,1);
            }
        }
    }
    return m;
}

public char getGuess(List<Character> guesses) {
    // TODO: Fill in this method.
    char max='a';//当前次数最多的单词
    int count = 0;//当前最多出现单词的次数
    Map<Character, Integer> m = getFrequencyMap();//获取频率字典
    for(Character a : m.keySet()){
        //如果改字母是已经在列表中的就不去猜
        if(guesses.contains(a)){
            continue;
        }
        int tmp = m.get(a);//获取当前字母的出现次数
        //如果当前字母出现次数很最多则进行记录
        if(count < tmp) {
            max = a;
            count = tmp;
        }
    }

    return max;
}
```

### `PatternAwareLetterFreqGuesser`

`getGuess(String pattern, List<Character> guesses)`方法`pattern`就像`-e--`这种形式

新增了一个函数`public Map<Character, Integer> keepOnlyWordsThatMatchPattern(String pattern)`只保留那些匹配模式的单词。可能会导致测试案例无法通过。

```Java
public Map<Character, Integer> keepOnlyWordsThatMatchPattern(String pattern){
    //首先找到当前patter对应的值和长度
    int len = pattern.length();
    Map<Character, Integer> find = new HashMap<>();//生成一个pattern中单词对应的位置
    //初始化一个筛选单词的列表
    List<String> t = new ArrayList<>();
    //获取pattern中的对应的字母和其下标
    for(int i=0; i<len; ++i){
        //获取当前第i个位置的值
        Character tmp = pattern.charAt(i);
        //如果值为'-'说明这个不是字母
        if(tmp == '-'){
            continue;
        }
        //否则是字母的话,再判断是否已经在列表中了,有就跳过不管
        if(find.containsKey(tmp)){
            continue;
        }
        //如果不在就加入
        find.put(tmp, i);
    }
    //然后修改currency里面的数据
    for(String s : currency){
        if(s.length() == len){
            //判断find中所有的值是否在相同的位置出现了
            boolean flag = true;//单词是否有效的判断
            for(Character c : find.keySet()){
                int index = find.get(c);
                //如果单词在第i个位置上不是单词c的话就跳过这个单词,说明错误
                if(s.charAt(index) != c){
                    flag = false;
                    break;
                }
            }
            //flag==true说明符合条件加入到t中
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
        //如果当前字母出现次数更多就进行修改
        if(count < tmp){
            max = a;
            count = tmp;
        }

    }
    return max;
}
```

### `PAGALetterFreqGuesser`

这个猜测者在第二个猜测者之上利用了已经排除的单词的信息

```Java

@Override
/** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
public char getGuess(String pattern, List<Character> guesses) {
    // TODO: Fill in this method.
    //获取pattern长度
    int len = pattern.length();
    //获取pattern中的映射关系
    Map<Character, List<Integer> > f = new HashMap<>();
    //遍历pattern获取字母和下标的映射
    for(int i=0; i<len; ++i){
        char tmp = pattern.charAt(i);
        //如果是"-"就跳过
        if(tmp == '-') continue;
        //再判断字典中是否存在
        if(f.containsKey(tmp)){
            //如果存在
            List<Integer> l= f.get(tmp);
            l.add(i);
            f.put(tmp, l);
        }else{
            //如果不存在
            List<Integer> l = new ArrayList<>();
            l.add(i);
            f.put(tmp, l);
        }
    }

    //遍历所有单词
    //获取字母和其出现次数的字典
    Map<Character, Integer> m = new HashMap<>();
    for(String s: words){
        //首先判断是否要记录其字母频率
        Boolean flag = true;
        //首先长度匹配
        if(s.length() != len) continue;
        //然后是pattern匹配
        for(Character c : f.keySet()){
            //判断pattern是否匹配
            for(Integer i : f.get(c)){
                //如果不匹配就退出本次循环
                if(s.charAt(i) != c){
                    flag = false;
                    break;
                }
            }
            //如果不匹配就退出本次循环
            if(flag == false) break;
        }
        //如果不匹配就跳过本次循环
        if(flag == false) continue;
        //guesses的判断，要求其不在pattern中出现
        for(int i=0; i<s.length(); ++i){
            char tmp = (s.charAt(i));
            //如果单词中存在guesses但不在pattern的值就不加入
            if(guesses.contains(tmp) && !f.containsKey(tmp)){
                flag = false;
                break;
            }
        }
        //如果不匹配就跳过本次循环
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

```

## Choosers

### `RandomChooser`

- `public RandomChooser(int wordLength, String dictionaryFile)`

参数说明：**wordLength**：希望单词的长度

​					**dictionaryFile**：保存可供选择的单词的文件

要求：如果**wordLength**<1，抛出异常`IllegalArgumentException`

​		**dictionaryFile**中不存在长度为**wordLength**单词,抛出异常`IllegalStateException`

函数说明:应该从文件中随机选择单词长度符合要求的单词作为秘密词.必须使用`StdRandom.uniform()`进行生成随机数

- 函数`public int makeGuess(char letter)`

参数说明:letter:要猜测的字母

函数说明:返回传入字母在秘密词中的数量,并且还要更新pattern

- `public String getPattern()`

函数说明:返回当前游戏要显示的pattern

- `public String getWord()`

函数说明:该方法返回当前的有RandomChooser选择的秘密词

```Java
public RandomChooser(int wordLength, String dictionaryFile) {
    // TODO: Fill in/
    //如果长度不符合要求抛出异常
    if(wordLength < 1) {
        throw new IllegalArgumentException();
    }
    //获取单词
    List<String> word = FileUtils.readWords(dictionaryFile);
    //用于存取符合长度的单词
    List<String> words = new ArrayList<>();
    //筛选出长度是wordLength的单词
    for(String s : word){
        if(s.length() == wordLength){
            words.add(s);
        }
    }
    //如果没有单词抛出异常
    if(words.isEmpty()){
        throw new IllegalStateException();
    }
    //随机生成一个下表
    int numWords = words.size();
    int randomlyChosenWordNumber = StdRandom.uniform(numWords);
    chosenWord = words.get(randomlyChosenWordNumber);
    //初始化Pattern 两种方法
    /**pattern = "-";
    pattern.repute(wordLength);*/
    
    for(int i=0; i<chosenWord.length();++i){
        pattern += "-";
    }
}

@Override
public int makeGuess(char letter) {
    // TODO: Fill in this method.
    
    Integer count = 0;//初始化计数器,记录letter出现的次数
    String s="";//初始化之后的pattern
    for(int i=0; i<chosenWord.length();++i){
        //首先对比每个字母
        char a = chosenWord.charAt(i);
        //如果两个字母相同,则显示当前字母
        if(a==letter){
            count++;
            s += String.valueOf(letter);
        }
        //否则使用之前的字母
        else{
            s += String.valueOf(pattern.charAt(i));
        }
    }
    //替换掉当前的pattern
    pattern = s;
    return count;
}

@Override
public String getPattern() {
    // TODO: Fill in this method.
    //返回当前的pattern
    return pattern;
}

@Override
public String getWord() {
    // TODO: Fill in this method.
    //返回当前的神秘值
    return chosenWord;
}
```

### `EvilChooser` 

说明:邪恶的选择者不会像随机选择者那么不智能,并不会在一开始就将秘密词选定,而是在猜测过程中选择与pattern词符合最多的集合的那个pattern,然后才判断你猜测的正不正确

- `public EvilChooser(int wordLength, String dictionaryFile)`

构造函数采用与`RandomChooser` 's 相同的参数，并且 应该在相同的情况下引发相同的异常。但是，由于这是*邪恶的*，因此您不应该立即选择单个单词！

- `public int makeGuess(char letter)`

此方法首先找到所有在所猜的字母中有相似的划分,并且选择数量最多的划分.如果两个划分相同,你应该选择按照字母表顺序排在前面的pattern.然后下一个回合的单词集合来自与本次元素最多的划分.

**注意：**解析关系的最简单方法是使用`TreeMap` 而不是`HashMap` ，因为 `TreeMap`按排序顺序存储其键。

选择新模式后，返回 新模式中的猜测字母。

- `public String getPattern()`

就像在 `RandomChooser`的`getPattern()`中一样，此方法应返回要为游戏显示的当前模式 使用已经做出的猜测。尚未被猜到的字母 应显示为短划线 （`-`）。不应有前导或尾随 空间。

- `public String getWord()`

此方法返回当前`wordPool` .如果只有一个词存在`wordPool`作为输出。

解决函数

```Java
public EvilChooser(int wordLength, String dictionaryFile) {
    // TODO: Fill in this constructor.
    //如果长度不符合要求抛出异常
    if(wordLength < 1) {
        throw new IllegalArgumentException();
    }
    //获取单词
    List<String> word = FileUtils.readWords(dictionaryFile);
    List<String> l =new ArrayList<>();//用于存取符合长度的单词
    //筛选出长度是wordLength的单词
    for(String s : word){
        if(s.length() == wordLength){
            l.add(s);
        }
    }
    //替换掉当前的单词池
    wordPool = l;
    //如果没有单词抛出异常
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
    int count = 0;//计数器
    //找到元素最多的pattern
    for(String s : m.keySet()){
        Integer tmp = m.get(s).size();
        if( tmp> count){
            pattern = s;
            count = tmp;
        }
    }
    //修改单词池
    wordPool = m.get(pattern);
    //获取当前pattern中letter的个数
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
```

