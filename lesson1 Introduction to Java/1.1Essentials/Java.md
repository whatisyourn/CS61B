# Java基础

1.Java代码的执行通常需要两步，第一步是Java的编译器（javac），第二步是Java解释器

![compilationflow](https://joshhug.gitbooks.io/hug61b/content/assets/compilation_figure.svg)

```linux
$ javac HelloWorld.java
$ java HelloWorld
Hello World! 
```

我们在编译阶段使用了.java而在解释阶段没有包括.class,这是我们通常的做法

# 静态类型（Static Typing）

静态类型是Java的一个很重要的特点。Java的变量包括了值的类型并且后面该变量的类型都不能改变

静态类型有很多有点包括：

- 编译器确保所有类型都兼容，使程序员更容易调试其代码。
- 由于代码保证没有类型错误，因此已编译程序的用户永远不会遇到类型错误。例如，Android应用程序是用Java编写的，通常仅作为.class文件分发，即以编译格式分发。因此，此类应用程序永远不会因类型错误而崩溃，因为它们已被编译器检查过。
- 每个变量、参数和函数都有一个声明的类型，使程序员更容易理解和推理代码。

# Java的函数定义

由于所有 Java 代码都是类的一部分，因此我们必须定义函数，以便它们属于某个类。属于类的函数通常称为“方法”。我们将在整个课程中交替使用这些术语。与上述代码等效的 Java 程序如下所示：

```java
public class LargerDemo {
    public static int larger(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }

    public static void main(String[] args) {
        System.out.println(larger(8, 10));
    }
}
```

这里的新语法是，我们使用关键字 public static 声明我们的方法，这是 Python的def 关键字的一个非常粗略的模拟。

# 代码样式，注释，Javadoc

## Code Style

好的代码风格应该有下面的特点

- 一致的样式（间距、可变命名、大括号样式等）
- 大小（行不太宽，源文件不太长）
- 描述性命名（变量、函数、类），例如名称为“year”或“getUserName”而不是“x”或“f”的变量或函数。
- 避免重复代码：除了一些更改外，您几乎不应该有两个几乎相同的重要代码块。
- 适当时的评论。Java 中的行注释使用“//”分隔符。多行（也称为多行注释）注释使用“/\*”和“*/”

### Whitespace(空格)

1. 每个文件必须以换行符结尾。

2. 文件不得包含水平制表符。仅将空白用于缩进。

3. 任何行都不得包含尾随空格。

4. 1.不要放空格：

         1. 在通用类型名称中的“<”和“>”周围（“列表”，而不是“列表”或“列表<整数>”）。
         2. 在前缀运算符“！”、“–”、“++”、“一元”-“或一元”+“之后。
         3. 在标记 “;” 或后缀运算符 “–” 和 “++” 之前。
         4. 在“（”或之前“）”之后。
         5. 在“.” 之后
         6. 当你lonely时。

5. 1. 1.一定要放空格：
         1. 在 “;”、“，” 或类型转换之后（例如，“(String) x”，而不是“(String)x”）。
         2. 围绕二元运算符（例如，“*”、“+”）和比较运算符。
         3. 围绕赋值运算符（例如，“=”、“+=”）。
         4. 在三元条件运算符中的“？”和“：”周围（“x>0 ？x ： -x“）。
            5. 围绕关键字“assert”、“catch”、“do”、“else”、“finally”、“for”、“if”、“return”、“synchronized”、“try”和“while”。

6. 通常，在运算符之前换行（插入换行符），如

   ```
    ... + 20 * X 
        + Y;
   ```

7. 不要在方法调用中用空格将方法名称与“（”分开。但是，您可以使用换行符分隔它们，后跟长行上的空白（用于缩进）。

### Indentation(缩 进)

1. 基本缩进步骤是4个空格。

2. 通过每个块级别的基本缩进步骤缩进代码（块通常用“{”和“}”括起来），如

   ```
    if (x > 0) {
        r = -x;
    } else {
        r = x;
    }
   ```

3. 缩进“case”标记了超出其封闭“开关”的缩进，如

   ```
    switch (op) {
        case '+':
            addOpnds(x, y);
            break;
        default:
            ERROR();
    }
   ```

4. 按基本缩进步骤缩进连续行。

### Braces(括号)

1. 在所有“if”、“while”、“do”和“for”语句的语句周围使用 { } 大括号。

2. 将“}”大括号与后面的“else”、“final”或“catch”放在同一行上，如

   ```
    if (x > 0) {
        y = -x;
    } else {
        y = x;
    }
   ```

3. 将打开块的“{”放在行尾。通常，它位于包含它的“if”、“for”、“while”、“switch”、“do”、方法标头或类标头的末尾。如果行长强制它到下一行，请不要缩进它，而是将其单独放在行上。

### Comments

一个特别的注意事项是，您的所有方法和几乎所有类都应该使用所谓的 Javadoc 格式在注释中描述。在 Javadoc 注释中，块注释以额外的星号开头，例如 /**，并且注释通常（但并非总是）包含描述性标记。

方法应该有 javadoc 注释来解释行为、参数（使用 @param 标记或其他）和返回类型。

返回非 void 值的方法必须在其 Javadoc 注释中使用“@return”标记或在包含单词“return”、“returning”或“returns”的运行文本中的短语中描述它们。

每个 Javadoc 注释必须以正确格式的句子开头，以大写字母开头，以句点结尾。

### Names

静态最终常量的名称必须全部大写（例如，RED、DEFAULT_NAME）。

参数、局部变量和方法的名称必须以小写字母开头，或者由单个大写字母组成。

类型（类）的名称（包括类型参数）必须以大写字母开头。

包的名称必须以小写字母开头。

实例变量和非最终类（静态）变量的名称必须以小写字母或“_”开头。

### Imports

不要使用“import PACKAGE.”，除非包是java.lang.Math，java.lang.Double或org.junit.Assert。“*import static CLASS.*”是可以的。

不要两次导入同一类或静态成员。

不要导入不使用的类或成员。

### Assorted Java Style Conventions(各种 Java 风格约定)

1. 在元素类型名称之后使用“[]”写入数组类型，而不是在声明符之后。写“String[] names”，而不是“String names[]”。

2. 按以下顺序为方法、类或字段编写任何修饰符：
   1. public, protected, or private.
   2. abstract or static.
   3. final, transient, or volatile.
   4. synchronized.
   5. native.
   6. strictfp.

3. 不要显式修改修改多余的方法、字段或类：
   1. 不要将接口或注释中的方法标记为“public”或“abstract”。
   2. 不要将接口或注释中的字段标记为“static”、“public”或“final”。
   3. 不要将最终类中的方法标记为“final”。
   4. 不要将嵌套接口标记为“static”。

4. 不要对控制语句使用空块（“{ }”，里面只有空格或注释）。有一个例外：catch 块可能仅由具有以下形式的注释组成

   ```
     /* Ignore EXCEPTIONNAME. */
   ```

5. 通过为代码赋予符号名称来避免代码中的“magic numbers”，例如

   ```
    public static final MAX_SIZE = 100;
   ```

   数字 -10 到 10、0.5、-0.5、0.25、-0.25 除外。

6. 不要尝试捕获异常异常、运行时错误或错误。

7. 写“b”而不是“b == true”和“！b”而不是“b == false”。

8. 更换

   ```
    if (condition) {
        return true;
    } else {
        return false;
    }
   ```

   with just

   ```
    return condition;
   ```

9. 只有类的静态最终字段可以是公开的。其他字段必须是私有的或受保护的。

10. 只有静态方法和字段的类不得具有公共（或默认）构造函数。
11. 只有私有构造函数的类必须声明为“final”。

### Avoiding Error-Prone Constructs(避免容易出错的构造)

1. 如果一个类覆盖了“等于”，它也必须覆盖“hashCode”。
2. 局部变量和参数不得隐藏字段名称。例如，处理仅控制字段的 getter/setter 方法的首选方法是在字段名称前面加上“_”，如

```
 public double getWidth() {
     return _width;
 }

 public void setWidth(double width) {
     _width = width;
 }
```

​	3.不要使用嵌套赋值，例如“if （（x = next（）） ！= null） ...”。虽然这在 C 中很有用，但在 Java 中		几乎从来都不需要。

​	4.在每个“switch”语句中包含一个“default”大小写。

​	5.用“break”语句或形式的注释结束“switch”语句的每个分支

```
 /* fall through */
```

​	6.不要将字符串文本与“==”进行比较。

```
 if (x.equals("something"))
```

and not

```
 if (x == "something")
```

在某些情况下，您确实想使用“==”，但您不太可能在此类中遇到它们。

### Limits

1.任何文件都不能超过 2000 行。

2.任何行都不能超过 100 个字符。

3.任何方法都不能超过 80 行。

4.任何方法都不能有超过 8 个参数。

5.每个文件必须只包含一个外部类（嵌套类是可以的）。