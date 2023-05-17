# Static vs. Non-Static Methods

Java 中的所有代码都必须是类的一部分（或类似于类的内容，我们将在后面学习）。大多数代码都是在方法内部编写的。让我们考虑一个例子：

```py
public class Dog {
    public static void makeNoise() {
        System.out.println("Bark!");
    }
}
```

如果我们尝试运行`Dog`类，我们只会收到一条错误消息：

```
$ java Dog
Error: Main method not found in class Dog, please define the main method as:
       public static void main(String[] args)
```

我们定义的`Dog`类不执行任何操作。我们简单地定义了`Dog`**可以**做的事情，即制造噪音。要实际运行该类，我们需要向该`Dog`类添加一个 main 方法，正如我们在第 1.1 章中看到的那样。或者我们可以创建一个单独的 [`DogLauncher`](https://www.youtube.com/watch?v=Q-LE-jJQLTM) 类来运行该`Dog`类中的方法。例如，考虑以下程序：

```java
public class DogLauncher {
    public static void main(String[] args) {
        Dog.makeNoise();
    }
}
```

```
$ java DogLauncher
Bark!
```

使用另一个类的类有时称为该类的“客户端”，例如`DogLauncher`是 的`Dog`客户端。这两种技术都不是更好的：在某些情况下，添加 main 方法可能更好，而创建类似`DogLauncher`的客户端类在其他情况下可能更好。随着我们在整个课程中获得额外的练习，每种方法的相对优势将变得清晰。

#### 实例变量和对象实例化

并非所有的狗都是一样的。有些狗喜欢不停地叫叫，而另一些则发出铿锵有力的吼叫，给所有听到它们光荣呼唤的人带来欢乐。通常，我们编写程序来模仿我们所居住的宇宙的特征，而Java的语法很容易允许这种模仿。

允许我们表示 Dogdom 谱系的一种方法是为每种类型的 Dog 创建单独的类。

```java
public class TinyDog {
    public static void makeNoise() {
        System.out.println("yip yip yip yip");
    }
}

public class MalamuteDog {
    public static void makeNoise() {
        System.out.println("arooooooooooooooo!");
    }
}
```

正如您过去应该看到的那样，类可以实例化，实例可以保存数据。这导致了一种更自然的方法，我们在其中创建`Dog`类的实例并使方法的行为取决于特定`Dog` .为了使这一点更具体，请考虑以下类：

```java
public class Dog {
    public int weightInPounds;

    public void makeNoise() {
        if (weightInPounds < 10) {
            System.out.println("yipyipyip!");
        } else if (weightInPounds < 30) {
            System.out.println("bark. bark.");
        } else {
            System.out.println("woof!");
        }
    }    
}
```

作为使用此类狗的示例，请考虑：

```java
public class DogLauncher {
    public static void main(String[] args) {
        Dog d;
        d = new Dog();
        d.weightInPounds = 20;
        d.makeNoise();
    }
}
```

运行时，该程序将创建一个重量为 20 的`Dog`，很快就会发出漂亮的“吠叫”。

一些关键观察和术语：

- Java 中的 `Object` 是任何类的实例。
- 该类`Dog`有自己的变量，也称为*实例*变量或*非静态变量*。这些必须在类中声明，不像Python或Matlab等语言，可以在运行时添加新变量。
- 我们在`Dog`类中创建的方法没有`static`关键字。我们将此类方法称为*实例*方法或*非静态方法*。
- 要调用该`makeNoise`方法，我们必须首先*实例化* `Dog`使用`new`关键字，然后制作特定的`Dog`。换句话说，我们调用`d.makeNoise()`而不是`Dog.makeNoise()` .
- 一旦一个对象被实例化，就可以将其*分配给*适当类型的*声明*变量，例如`d = new Dog();`
- 类的变量和方法也称为类*的成员*。
- 使用*点表示法*访问类的成员。

#### Java 中的构造函数

正如你之前希望看到的，我们通常使用构造函数在面向对象语言中*构造*对象：

```java
public class DogLauncher {
    public static void main(String[] args) {
        Dog d = new Dog(20);
        d.makeNoise();
    }
}
```

在这里，实例化是使用参数的，节省了我们手动键入可能许多实例变量赋值的时间和混乱。要启用这样的语法，我们只需要向 Dog 类添加一个“构造函数”，如下所示：

```java
public class Dog {
    public int weightInPounds;

    public Dog(int w) {
        weightInPounds = w;
    }

    public void makeNoise() {
        if (weightInPounds < 10) {
            System.out.println("yipyipyip!");
        } else if (weightInPounds < 30) {
            System.out.println("bark. bark.");
        } else {
            System.out.println("woof!");
        }    
    }
}
```

每当我们尝试使用 `new`关键字和单个整数参数创建`Dog` 时，都会调用带有参数`public Dog(int w)`的构造函数。对于那些来自Python的人来说，构造函数`__init__`与该方法非常相似。

#### 数组实例化，对象数组

正如我们在 HW0 中看到的，数组也在 Java 中使用 new 关键字实例化。例如：

```java
public class ArrayDemo {
    public static void main(String[] args) {
        /* Create an array of five integers. */
        int[] someArray = new int[5];
        someArray[0] = 3;
        someArray[1] = 4;
    }
}
```

类似地，我们可以在 Java 中创建实例化对象的数组，例如

```java
public class DogArrayDemo {
    public static void main(String[] args) {
        /* Create an array of two dogs. */
        Dog[] dogs = new Dog[2];
        dogs[0] = new Dog(8);
        dogs[1] = new Dog(20);

        /* Yipping will result, since dogs[0] has weight 8. */
        dogs[0].makeNoise();
    }
}
```

请注意，new 以两种不同的方式使用：一次用于创建可以容纳两个`Dog`对象的数组，两次用于创建每个实际`Dog` .

### 类方法与实例方法

Java允许我们定义两种类型的方法：

- 类方法，又名static methods.。
- 实例方法，也称为non-static methods。

实例方法是只能由类的特定实例执行的操作。静态方法是由类本身执行的操作。两者都在不同的情况下有用。作为静态方法的示例，该`Math`类提供了一个`sqrt`方法。因为它是静态的，我们可以这样调用它：

```java
x = Math.sqrt(100);
```

如果`sqrt`是一个实例方法，我们将采用下面的笨拙语法。幸运的是，`sqrt`它是一种静态方法，因此我们不必在实际程序中执行此操作。

```java
Math m = new Math();
x = m.sqrt(100);
```

有时，拥有一个同时包含实例和静态方法的类是有意义的。例如，假设想要比较两只狗的能力。一种方法是添加一个静态方法来比较 Dogs。

```java
public static Dog maxDog(Dog d1, Dog d2) {
    if (d1.weightInPounds > d2.weightInPounds) {
        return d1;
    }
    return d2;
}
```

例如，可以通过以下方式调用此方法：

```java
Dog d = new Dog(15);
Dog d2 = new Dog(100);
Dog.maxDog(d, d2);
```

请注意，我们使用类名进行了调用，因为此方法是静态方法。

我们也可以实现为非静态方法，例如`maxDog`

```java
public Dog maxDog(Dog d2) {
    if (this.weightInPounds > d2.weightInPounds) {
        return this;
    }
    return d2;
}
```

上面，我们使用`this`关键字来引用当前对象。例如，可以使用以下命令调用此方法：

```java
Dog d = new Dog(15);
Dog d2 = new Dog(100);
d.maxDog(d2);
```

在这里，我们使用特定的实例变量调用该方法。

#### 静态变量

类具有静态变量有时很有用。这些是类本身固有的属性，而不是实例固有的属性。例如，我们可以记录狗的学名（或双名词）是“Canis familiaris”：

```java
public class Dog {
    public int weightInPounds;
    public static String binomen = "Canis familiaris";
    ...
}
```

静态变量应该使用类的名称而不是特定的实例来访问，例如，你应该使用 `Dog.binomen`， 而不是 `d.binomen`。

虽然Java在技术上允许你使用实例名称访问静态变量，但它的风格很糟糕，令人困惑，在我看来是Java设计者的错误。

### public static void main(String[] args)

根据我们目前所学到的知识，是时候揭开我们一直用于 main 方法的声明的神秘面纱了。将其分解为碎片，我们有：

- `public`：到目前为止，我们所有的方法都以这个关键字开头。
- `static`：它是一个静态方法，不与任何特定实例关联。
- `void`：它没有返回类型。
- `main`：这是方法的名称。
- `String[] args`：这是传递给 main 方法的参数。

#### 命令行参数

由于 main 是由 Java 解释器本身而不是另一个 Java 类调用的，因此解释器的工作是提供这些参数。它们通常引用命令行参数。例如，考虑以下`ArgsDemo`程序：

```java
public class ArgsDemo {
    public static void main(String[] args) {
        System.out.println(args[0]);
    }
}
```

该程序打印出第 0 个命令行参数，例如

```
$ java ArgsDemo these are command line arguments
these
```

在上面的例子中，`args`将是一个字符串数组，其中值是{“these”，“are”，“command”，“line”，“arguments”}。

### 使用库

作为程序员，最重要的技能之一是知道如何查找和使用现有库。在光荣的现代时代，通常可以通过向网络寻求帮助来节省自己的大量工作和调试。

在本课程中，欢迎您执行此操作，但需要注意以下事项：

- 请勿使用我们不提供的库。
- 引用您的来源。
- 不要为特定的家庭作业或项目问题寻找解决方案。

例如，可以搜索“转换字符串整数Java”。但是，搜索“nbody Project Berkeley”是不行的。