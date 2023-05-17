# HW0

## Java Language Constructs

### 变量声明

| Python   | Java          |
| -------- | ------------- |
| `i = 0 ` | `int i = 0; ` |

- 就像Python一样，Java变量也有*类型*。在 Java 中，声明 变量，我们必须明确说出它是什么类型。变量的声明 类型永远不会改变。有关“static typing”的更多信息，请参阅第 1 讲。
- 我们还必须在语句末尾加上一个分号。

### Types 

| Python        | Java      | What?                                                        |
| ------------- | --------- | ------------------------------------------------------------ |
| `bool`        | `boolean` | Python uses `True` and `False`; Java uses `true` and `false`. |
| `int`         | `int`     | 虽然 Python 的`int`是无界的，但 Java的`int` 具有（大的）最大值和最小值。 |
| `float`       | `double`  | Decimal values. Java `doubles` are again bounded.            |
| `str`         | `String`  | Java `String`s use 使用双引号(`"`), 可以是任何文本.          |
| no equivalent | `char`    | Java `char` 代表单个字符，并且使用单引号（')                 |

### Comments 

<img src="C:/Users/32494/AppData/Roaming/Typora/typora-user-images/image-20230516212517821.png" alt="image-20230516212517821" style="zoom:67%;" />

Java 也有多行注释，这些注释以`/*` `*/` 开头和结尾。

### `while` Loop 

<img src="C:/Users/32494/AppData/Roaming/Typora/typora-user-images/image-20230516212503618.png" alt="image-20230516212503618" style="zoom: 67%;" />

- 在条件周围的括号`(`和`)`必需的。
- 在 Java 中，经常使用 `++`代替`+= 1` 。
- 我们确实在Java中使用`System.out.println`进行打印。不好意思。
- 我们不缩进，而是使用大括号`{``}`，内部的代码 是 while 循环的一部分。Java不需要缩进，但它是很好风格！

### `for` Loop (counting up) 

<img src="C:/Users/32494/AppData/Roaming/Typora/typora-user-images/image-20230516212555191.png" alt="image-20230516212555191" style="zoom:67%;" />

Java中的for循环的语法为

<img src="C:/Users/32494/AppData/Roaming/Typora/typora-user-images/image-20230516212635671.png" alt="image-20230516212635671" style="zoom:67%;" />

这大致相当于 while 循环：

<img src="C:/Users/32494/AppData/Roaming/Typora/typora-user-images/image-20230516212712800.png" alt="image-20230516212712800" style="zoom:67%;" />

### `for` Loop (counting down) 

<img src="C:/Users/32494/AppData/Roaming/Typora/typora-user-images/image-20230516212745180.png" alt="image-20230516212745180" style="zoom:67%;" />

- 注意不同的“初始化”、“终止”和“增量”块 在 Java  `for`循环中。
- 与 `++`类似，`--`经常使用 `-= 1`代替 。
- 比较表中的`for`循环“直到`i < 0`”。

### Conditionals 

<img src="C:/Users/32494/AppData/Roaming/Typora/typora-user-images/image-20230516212906167.png" alt="image-20230516212906167" style="zoom:67%;" />

布尔运算符如下：

<img src="C:/Users/32494/AppData/Roaming/Typora/typora-user-images/image-20230516212931678.png" alt="image-20230516212931678" style="zoom:67%;" />

- 请注意 `elif`和 `else if`之间的区别。

### Exponentiation （幂）

<img src="C:/Users/32494/AppData/Roaming/Typora/typora-user-images/image-20230516213023238.png" alt="image-20230516213023238" style="zoom:67%;" />

请注意，在Java中`^`是“XOR”运算符，而不是幂运算。 也就是说，`2 ^ 10`是有效的代码，但它将返回`8` ，而不是 `1024`

### Function Declaration and Usage 

<img src="C:/Users/32494/AppData/Roaming/Typora/typora-user-images/image-20230516213544101.png" alt="image-20230516213544101" style="zoom:67%;" />

- 在 Java 中，函数具有特定的返回类型，该返回类型位于 函数名称前。函数还指定其参数的类型。
  - 当函数不返回任何内容时，它的返回类型为 .`void`
- 目前，我们所有的功能都将有`public static`前面。我们将学习 这些在以后意味着什么。
- 调用函数看起来与 Python 中的函数相同。

### 字符串

<img src="C:/Users/32494/AppData/Roaming/Typora/typora-user-images/image-20230516213657051.png" alt="image-20230516213657051" style="zoom:67%;" />

- 在Java中，`String`s不能直接迭代。我们要么迭代一个 索引并`charAt`使用，或者我们将其转换为数组（即将推出）。
- 在Java中，你可以向`String`s添加任何东西，它将是隐式的。 转换为`String` ，无需显式强制转换。

### Java输出

在Java中，您可以简单地使用

```
System.out.println(); 或

System.out.print(); 或

System.out.printf();
```

### println()，print()和printf()之间的区别

- print() - 它在引号内打印字符串。
- println() - 它在引号内打印字符串，类似于print()方法。然后光标移动到下一行的开头。
- printf() - Tt提供字符串格式化（类似于C / C ++编程中的printf）。

## Programs

现在我们已经介绍了各个语言结构，让我们看一些 Java 使用它们的程序。以下是您可能会发现自己的一些简单问题 指的是如果你忘记了如何做某事。

<img src="C:/Users/32494/AppData/Roaming/Typora/typora-user-images/image-20230516213913215.png" alt="image-20230516213913215" style="zoom:67%;" />

- 所有 Java 代码都必须在一个**class**中。稍后我们将了解有关课程的更多信息。
- 当一个Java程序被执行时，它运行**`public static void main(String[] args)`**方法。这与 Python不同，python代码可以在函数之外执行。