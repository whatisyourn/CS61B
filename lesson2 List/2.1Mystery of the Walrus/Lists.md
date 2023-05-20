# Lists

### 海象之谜

```Java
public class PollQuestions {
   public static void main(String[] args) {
      Walrus a = new Walrus(1000, 8.3);
      Walrus b;
      b = a;
      b.weight = 5;
      System.out.println(a);
      System.out.println(b);      

      int x = 5;
      int y;
      y = x;
      x = 2;
      System.out.println("x is: " + x);
      System.out.println("y is: " + y);      
   }
   
   public static class Walrus {
      public int weight;
      public double tuskSize;
      
      public Walrus(int w, double ts) {
         weight = w;
         tuskSize = ts;
      }

      public String toString() {
         return String.format("weight: %d, tusk size: %.2f", weight, tuskSize);
      }
   }
}
```



下面代码会输出什么结果呢.

```Java
Walrus a = new Walrus(1000, 8.3);
Walrus b;
b = a;
b.weight = 5;
System.out.println(a);
System.out.println(b);
```

答案:

```Java
weight: 5, tusk size: 8.30
weight: 5, tusk size: 8.30
```

现在尝试预测当我们运行下面的代码时会发生什么。更改为 x 会影响 y 吗？

```Java
int x = 5;
int y;
y = x;
x = 2;
System.out.println("x is: " + x);
System.out.println("y is: " + y);
```

答案:

```Java
x is 2
y is 5
```

### Bits

一段Java代码如何知道如何解释01001000？答案是通过类型！

在 Java 中，有 8 种原始类型：字节、短、整、长、浮点、双精度、布尔值和字符。每个都有不同的属性，我们将在整个课程中讨论，除了短型和浮点型，你可能永远不会使用它们。

### 声明变量（Simplified）

您可以将计算机视为包含大量用于存储信息的内存位，每个内存位都有一个唯一的地址。现代计算机可以使用数十亿个这样的位。

当你声明某种类型的变量时，Java 会找到一个连续的块，其中包含足够多的位来容纳该类型的东西。例如，如果你声明一个 int，你会得到一个 32 位的bits。如果你声明一个字节，你会得到一个 8 位的bits。Java 中的每种数据类型都包含不同数量的位。

除了留出内存之外，Java 解释器还在内部表中创建一个条目，该条目将每个变量名映射到框中第一个比特位的位置。

Java 语言没有提供让你知道盒子位置的方法，例如，你无法以某种方式发现 x 在位置 352。换句话说，确切的内存地址低于我们在 Java 中可访问的抽象级别。这与 C 等语言不同，在 C 语言中，您可以向语言询问一段数据的确切地址。即Java没有提供指针可以访问地址

声明变量时，Java 不会将任何内容写入保留框。换句话说，没有默认值。因此，Java 编译器会阻止您使用变量，直到使用`=`运算符填充了该框的位。出于这个原因，我避免在上图中的框中显示任何位。

### 平等的黄金法则 （GRoE）

事实证明，我们的 Mystery 有一个简单的解决方案：当你编写`y = x`时，你告诉 Java 解释器将位从 x 复制到 y 中。这个平等的黄金法则（GRoE）是理解我们的海象之谜时所有真理的根源。

### 引用类型

上面，我们说有 8 种原始类型：字节、短、整、长、浮点、双精度、布尔值、字符。其他所有内容（包括数组）都不是基元类型，而是 .`reference type`

#### 对象实例化

当我们使用`new`（例如 Dog、Walrus、Planet）*实例化*一个对象时，Java 首先为类的每个实例变量分配一个框，并用默认值填充它们。然后，构造函数通常（但并非总是）用其他值填充每个框。

例如，如果我们的海象类是：

```Java
public static class Walrus {
    public int weight;
    public double tuskSize;

    public Walrus(int w, double ts) {
          weight = w;
          tuskSize = ts;
    }
}
```

我们使用`new `创建一个海象，然后我们最终得到一个分别由两个 32 位和 64 位的盒子组成的`Walrus(1000, 8.3);`海象：

#### 引用变量声明

当我们*声明*任何引用类型（Walrus、Dog、Planet、array等）的变量时，Java 会分配一个 64 位的空间，无论对象类型如何。

乍一看，这似乎导致了海象悖论。上一节中的海象需要存储超过 64 位。此外，无论对象类型如何，我们只有 64 位来存储它，这似乎很奇怪。

但是，通过以下信息可以轻松解决此问题：64 位框包含的不是有关海象的数据，而是内存中海象的地址。

我们还可以将特殊值`null`分配给一个参考变量，对应于所有零。

#### 框和指针表示法

和以前一样，很难解释引用变量中的一堆位，因此我们将为引用变量创建一个简化的框表示法，如下所示：

- 如果一个地址全为零，我们将用 null 表示它。
- 非零地址将由指向对象实例化的**箭头**表示。

这有时也称为“框和指针”表示法。

![someWalrus_simplified_bit_notation.png](%E6%9C%AA%E5%91%BD%E5%90%8D.assets/someWalrus_simplified_bit_notation.png)![someWalrus_simplified_bit_notation_null.png](%E6%9C%AA%E5%91%BD%E5%90%8D.assets/someWalrus_simplified_bit_notation_null.png)

#### 解开海象之谜

```Java
Walrus a = new Walrus(1000, 8.3);
Walrus b;
b = a;
```

执行第一行后，我们有：

![mystery_of_the_walrus_resolved_step1.png](%E6%9C%AA%E5%91%BD%E5%90%8D.assets/mystery_of_the_walrus_resolved_step1.png)

执行第二行后，我们有：

![mystery_of_the_walrus_resolved_step2.png](%E6%9C%AA%E5%91%BD%E5%90%8D.assets/mystery_of_the_walrus_resolved_step2.png)

请注意，上面，b 是未定义的，而不是空的。

根据GRoE，最后一行只是将`a`盒子中的位复制到盒子`b`中。或者就我们的视觉隐喻而言，这意味着 b 将完全复制 a 中的箭头，现在显示一个指向同一对象的箭头。

![mystery_of_the_walrus_resolved_step3.png](%E6%9C%AA%E5%91%BD%E5%90%8D.assets/mystery_of_the_walrus_resolved_step3.png)

### 参数传递

将参数传递给函数时，也只是在复制位。换句话说，GRoE 也适用于参数传递。复制位通常称为“按值传递”。在Java中，我们**总是**按值传递。

```Java
public static double average(double a, double b) {
    return (a + b) / 2;
}
```

假设我们调用此函数，如下所示：

```java
public static void main(String[] args) {
    double x = 5.5;
    double y = 10.5;
    double avg = average(x, y);
}
```

执行此函数的前两行后，main 方法将有两个标记框并包含如下所示的值：`x``y`

![main_x_y.png](%E6%9C%AA%E5%91%BD%E5%90%8D.assets/main_x_y.png)

调用函数时，该`average`函数具有**自己的**作用域，其中包含两个标记为`a`和`b`的新框，并且只是*简单地复制*了位。这种位的复制就是我们所说的“按值传递”时所指的。

![average_a_b.png](%E6%9C%AA%E5%91%BD%E5%90%8D.assets/average_a_b.png)

如果`average`函数要改变`a`，那么 main中的`x`将保持不变，因为 GRoE 告诉我们，我们只是在用新位标记的框中填充`a`。

### 数组的实例化

如上所述，存储数组的变量是引用变量，就像任何其他变量一样。例如，请考虑以下声明：

```java
int[] x;
Planet[] planets;
```

这两个声明都创建 64 位的内存盒。 `x`只能保存`int`数组的地址，`planets`也只能保存`Planet`数组的地址。

实例化数组与实例化对象非常相似。例如，如果我们创建一个大小为 5 的整数数组，如下所示：

```
x = new int[]{0, 1, 2, 95, 4};
```

然后，关键字`new`创建 5 个框，每个框 32 位，并返回要分配给 x 的整个对象的地址。

如果丢失与地址对应的位，则对象可能会丢失。例如，如果特定海象地址的唯一副本存储在 x中，`x = null`则会导致您永久丢失该海象。这不一定是一件坏事，因为您通常会决定完成一个对象，因此简单地丢弃引用是安全的。我们将在本章后面构建列表时看到这一点。

#### 破碎蒲团法则

### IntLists（英语：IntLists）

现在我们已经真正理解了海象之谜，我们准备构建自己的列表类。

事实证明，一个非常基本的列表实现起来很简单，如下所示：

```java
public class IntList {
    public int first;
    public IntList rest;        

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }
}
```

这样的列表使用起来很丑陋。例如，如果我们想列出数字 5、10 和 15，我们可以执行以下操作之一：

```java
IntList L = new IntList(5, null);
L.rest = new IntList(10, null);
L.rest.rest = new IntList(15, null);
```

或者，我们可以向后构建我们的列表，产生稍微好一点但更难理解的代码：

```java
IntList L = new IntList(15, null);
L = new IntList(10, L);
L = new IntList(5, L);
```

虽然原则上可以使用 IntList 来存储任何整数列表，但生成的代码会相当丑陋且容易出错。我们将采用通常的面向对象编程策略，向类中添加帮助程序方法来执行基本任务。

#### 大小和迭代大小

我们想向类`IntList`添加一个方法`size`，以便在调用 `L.size()`时返回 `L`中的项数。

```Java
public class IntList {
    public int first;
    public IntList rest;        

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }
    //递归版
    public int size(){
        if(rest == null){
            return 1;
        }
        return this.rest.size() + 1;
    }
    //迭代版本
    /** Return the size of the list using no recursion! */
    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }
}
```

#### 获取

虽然该`size`方法允许我们获取列表的大小，但我们没有简单的方法来获取列表的第 i 个元素。

练习：编写一个返回列表第 i 项的方法`get(int i)`。例如，如果是 5 -> 10 -> 15，`L.get(0)`则应返回 5，`L.get(1)`应返回 10，`L.get(2)`并应返回 15。你的代码如何表现都无关紧要`i`无效 ，太大或太小。

```Java
public class IntList {
    public int first;
    public IntList rest;        

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }
	public int get(int i){
        IntList p = this;
        //找到第i个元素
        for(int j=0;j<i; ++j) p = p.rest;
        //返回第i个yuan
        return p.first;
    }
}
```

