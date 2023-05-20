# Lab 02: Debugging

## `IntList` Aside

我们创建了两个方法`print`和`of`在`IntList`中.这个`of`方法是一个很方便的方法创建`IntList`.下面是一个展示其如何工作的.

```Java
IntList lst = new IntList(1, new IntList(2, new IntList(3, null)));
```

上面代码看起来很繁琐.这个`IntList.of`方法将解决这个问题.为了创建元素1,2,3你可以输入以下代码

```Java
IntList lst = IntList.of(1,2,3);
```

`Print`方法返回一个String类型的呈现形式

```Java
IntList lst = IntList.of(1,2,3);
System.out.println(lst.print());

//output: 1->2->3
```

这些方法提供了方便的途径去实例化和展示`IntList`.

## `Bomb`

这个`BombMain`类调用了`Bomb`的`phase`的成员变量.你要找到密码在这些phrases中,使用调试功能.

### `Bomb` Introduction (Phase 0) 

正确答案:39291226

### Visualizer (Phase 1)

正确答案:`IntList p = IntList.of(0,9,3,0,8);`

### Conditional Breakpoints (Phase 2) 

正确答案:在第1337的位置上的String为-81201430

```Java
String number = "";
int i =0 ;
while (i < 100000) {
    if(i == 1337){
        number += "-81201430 ";
    }else {
        number += "1 ";
    }
    ++i;
}
b.phase2(number);
```

## `Adventure` 

错误地点如下:

![stack trace](https://sp23.datastructur.es/materials/lab/lab02/img/adventure_stack_trace.png)

### Debug `BeeCountingStage`

#### 错误1:52行 input未初始化

修正:在17行的初始化函数中进行new

```Java
    public BeeCountingStage(In in) {
        this.in = in;
        this.input = new ArrayList<>();
        this.responses = Map.of("go", new SpeciesListStage(in));
    }
```



#### 错误2:89行`IndexOutOfBoundsException`

修改:把for循环判断的=删除

```Java
for (int i = 0; i < this.input.size(); i++) {
    sum += Integer.parseInt(this.input.get(i));
}
```

### Debug `SpeciesListStage`

错误:99行除0错误

修改:

```Java
public static double arraySimilarity(List<String> listOne, List<String> listTwo) {
    //如果是0直接返回
    if(listOne.size() == 0){
        return 0;
    }
    List<String> copy = new ArrayList<>(listOne);
    int similarObjects = 0;
    for (String o : listTwo) {
        if (copy.contains(o)) {
            similarObjects++;
            copy.remove(o);
        }
    }

    return similarObjects / listOne.size();
}
```

### Debug `PalindromeStage`

### Debug `MachineStage`

#### 错误1:47行,没有对reversedLst赋值

修改:将45行的注释打开

#### 错误2:85行的循环存在无限循环

#### 错误3:86行访问越界

修改:将++改为--,将i改为i-1

```Java
for (int i = s.length(); i > 0; i--) {
    a[s.length() - i] = Character.getNumericValue(s.charAt(i-1));
}
```

