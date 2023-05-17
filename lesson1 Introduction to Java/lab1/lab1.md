# 1.安装软件

- ## window下

### 安装Java

1.下载地址https://adoptium.net/temurin/releases. 

2.**Important**

在接下来的选项中

![JDK Download 1](https://sp23.datastructur.es/materials/lab/lab01/img/windows/openJDK_install_1.png)

你应该点击这个两个红色的X然后改变成改变成本地硬件驱动如下图展示![JDK Download 2](https://sp23.datastructur.es/materials/lab/lab01/img/windows/openJDK_install_2.png)

3.点击下一步以继续

### 安装Git

1.官网http://git-scm.com/download/ 

2.在下面的图片中

![Git Installation 1](https://sp23.datastructur.es/materials/lab/lab01/img/windows/git_install_components.png)

在Windows Explorer integration中，点击Windows Explorer integration将两个全选。

3.在选择git的editor时，你可以选择你喜欢的或者你可以使用VIM

![Git Installation 3](https://sp23.datastructur.es/materials/lab/lab01/img/windows/git_default_editor.png)

4.下面中选择第二个选项![Git Installation 4](https://sp23.datastructur.es/materials/lab/lab01/img/windows/git_path_install.png)



- ## Linux下

在linux下只需要简单的命令即可（apt/yum/etc)安装install the Java JDK and git.

例如在Ubuntu系统下:

```
sudo apt install openjdk-17-jdk git
git config --global credential.helper store
```

- ## Mac下

# 2.Git的使用

## Setting Up Git

1.首先输入的的姓名和邮箱地址

```
git config --global user.name "<your name>"
git config --global user.email "<your email>"
```

2.设置git默认分支名

```
git config --global init.defaultBranch main
```

3.设置分支合并的策略

```
git config --global pull.rebase false
```

# 3.InteliJ IDEA

在开始前需要准备

1.课程代码框架 https://github.com/Berkeley-CS61B/skeleton-sp23.git

2.安装本课程需要的Java包 git clone https://github.com/Berkeley-CS61B/library-sp23

**每次之后 从`框架`中提取以获取新的实验室或项目文件，您需要运行 再次通过以下步骤。**

1. 启动 IntelliJ。**如果您没有打开任何项目**，请单击“Open” 按钮。如果您当前打开了一个项目，请导航到**“File –> Open”。**

2. 查找并选择当前作业的目录。例如，对于 实验 1，您将选择存储库中的目录。`lab01``sp23-***`

3. 导航到**“File -> Project Structure”**菜单，并确保 在**“项目**”选项卡中。将项目 SDK 设置为已安装的 Java 版本。 如果下拉列表中没有 17 或更高版本，请确保您已下载 并完全安装了Java。

   ![选择-JDK](https://sp23.datastructur.es/materials/guides/assignment-workflow/img/select-jdk.png)

4. 仍在“项目”选项卡中，将“**项目**语言级别”设置为 “17 - Sealed types, always-strict floating-point semantics”。

   此时，“项目”选项卡应如下所示：

   ![项目](https://sp23.datastructur.es/materials/guides/assignment-workflow/img/project_structure_settings.png)

   - SDK 设置为 Java 17 或更高版本
   - 语言级别至少为 17，最多为 SDK。
   - 编译器输出已填充，并设置为赋值 目录，后跟`out`

5. 仍在“项目结构”中，转到**“Libraries***”选项卡。单击 “**+ -> Java**”按钮，然后导航到 ，选择 文件夹，然后单击“确定”。`library-sp23`

6. 单击“确定”以应用您的设置并离开项目结构。

此时，如果配置正确：

- 每个 Java 文件的名称旁边都应该有一个蓝色圆圈。
- 打开任何文件时，不应突出显示任何代码 红。
