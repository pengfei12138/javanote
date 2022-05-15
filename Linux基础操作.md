### Linux基础操作

------

> 开机登录

开机会启动许多程序。它们在Windows叫做"服务"（service），在Linux就叫做"守护进程"（daemon）。

开机成功后，它会显示一个文本登录界面，这个界面就是我们经常看到的登录界面，在这个登录界面中会提示用户输入用户名，而用户输入的用户将作为参数传给login程序来验证用户的身份，密码是不显示的，输完回车即可！

一般来说，用户的登录方式有三种：

- 命令行登录
- ssh登录
- 图形界面登录

最高权限账户为 root，可以操作一切！

> 关机

在linux领域内大多用在服务器上，很少遇到关机的操作。毕竟服务器上跑一个服务是永无止境的，除非特殊情况下，不得已才会关机。

关机指令为：shutdown ；

```bash
sync # 将数据由内存同步到硬盘中。

shutdown # 关机指令，你可以man shutdown 来看一下帮助文档。例如你可以运行如下命令关机：

shutdown –h 10 # 这个命令告诉大家，计算机将在10分钟后关机

shutdown –h now # 立马关机

shutdown –h 20:25 # 系统会在今天20:25关机

shutdown –h +10 # 十分钟后关机

shutdown –r now # 系统立马重启

shutdown –r +10 # 系统十分钟后重启

reboot # 就是重启，等同于 shutdown –r now

halt # 关闭系统，等同于shutdown –h now 和 poweroff
```

最后总结一下，不管是重启系统还是关闭系统，首先要运行 **sync** 命令，把内存中的数据写到磁盘中。

> 系统目录结构

登录系统后，在当前命令窗口下输入命令：

```bash
ls /
```

你会看到如下图所示：

![image-20220512104307861](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512104307861.png)

树状目录结构：（Linux的一切资源都挂载在这个 / 根节点下）

![11111](https://gitee.com/linda12138/picgo/raw/master/images/11111.jpg)

**以下是对这些目录的解释：**

- **/bin**：bin是Binary的缩写, 这个目录存放着最经常使用的命令。
- **/boot：** 这里存放的是启动Linux时使用的一些核心文件，包括一些连接文件以及镜像文件。
- **/dev ：** dev是Device(设备)的缩写, 存放的是Linux的外部设备，在Linux中访问设备的方式和访问文件的方式是相同的。
- **/etc：** 这个目录用来存放所有的系统管理所需要的配置文件和子目录。
- **/home**：用户的主目录，在Linux中，每个用户都有一个自己的目录，一般该目录名是以用户的账号命名的。
- **/lib**：这个目录里存放着系统最基本的动态连接共享库，其作用类似于Windows里的DLL文件。
- **/lost+found**：这个目录一般情况下是空的，当系统非法关机后，这里就存放了一些文件。
- **/media**：linux系统会自动识别一些设备，例如U盘、光驱等等，当识别后，linux会把识别的设备挂载到这个目录下。
- **/mnt**：系统提供该目录是为了让用户临时挂载别的文件系统的，我们可以将光驱挂载在/mnt/上，然后进入该目录就可以查看光驱里的内容了。
- **/opt**：这是给主机额外安装软件所摆放的目录。比如你安装一个ORACLE数据库则就可以放到这个目录下。默认是空的。
- **/proc**：这个目录是一个虚拟的目录，它是系统内存的映射，我们可以通过直接访问这个目录来获取系统信息。
- **/root**：该目录为系统管理员，也称作超级权限者的用户主目录。
- **/sbin**：s就是Super User的意思，这里存放的是系统管理员使用的系统管理程序。
- **/srv**：该目录存放一些服务启动之后需要提取的数据。
- **/sys**：这是linux2.6内核的一个很大的变化。该目录下安装了2.6内核中新出现的一个文件系统 sysfs 。
- **/tmp**：这个目录是用来存放一些临时文件的。
- **/usr**：这是一个非常重要的目录，用户的很多应用程序和文件都放在这个目录下，类似于windows下的program files目录。
- **/usr/bin：** 系统用户使用的应用程序。
- **/usr/sbin：** 超级用户使用的比较高级的管理程序和系统守护程序。
- **/usr/src：** 内核源代码默认的放置目录。
- **/var**：这个目录中存放着在不断扩充着的东西，我们习惯将那些经常被修改的目录放在这个目录下。包括各种日志文件。
- **/run**：是一个临时文件系统，存储系统启动以来的信息。当系统重启时，这个目录下的文件应该被删掉或清除。
- /www:存放服务器网站相关的资源，环境，网站项目



### 常用的基本命令

------

#### 目录管理

> 绝对路径、相对路径

我们知道Linux的目录结构为树状结构，最顶级的目录为根目录 /。

其他目录通过挂载可以将它们添加到树中，通过解除挂载可以移除它们。

在开始本教程前我们需要先知道什么是绝对路径与相对路径。

**绝对路径：**

路径的写法，由根目录 / 写起，例如：/usr/share/doc 这个目录。

**相对路径：**

路径的写法，不是由 / 写起，例如由 /usr/share/doc 要到 /usr/share/man 底下时，可以写成：cd ../man 这就是相对路径的写法啦！

> 处理目录的常用命令

cd:切换目录命令

./：当前目录

cd ..：返回上一级目录

ls: 列出目录

pwd：显示目前的目录

mkdir：创建一个新的目录

rmdir：删除一个空的目录

cp: 复制文件或目录

rm: 移除文件或目录

mv: 移动文件与目录，或修改文件与目录的名称

![image-20220512105607025](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512105607025.png)



> ls（列出目录！）

-a参数：all,查看全部文件，包括隐藏文件

-l参数：列出所有的文件，包含文件的属性和权限，没有隐藏文件！

![image-20220512110100400](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512110100400.png)

![image-20220512110118231](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512110118231.png)



> cd命令 切换目录

cd 目录名（绝对路径都是以/开头，行对路径，对于当前目录该如何寻找../../）

> pwd 显示当前所在的目录

```bash
[root@iz0jl2iuwjqvwcbzf0ekvlz ~]# pwd
/root
[root@iz0jl2iuwjqvwcbzf0ekvlz ~]# cd /home/peng
[root@iz0jl2iuwjqvwcbzf0ekvlz peng]# pwd
/home/peng
[root@iz0jl2iuwjqvwcbzf0ekvlz peng]# 
```



> mkdir 创建一个目录

```bash
[root@iz0jl2iuwjqvwcbzf0ekvlz /]# cd home
[root@iz0jl2iuwjqvwcbzf0ekvlz home]# mkdir test1
[root@iz0jl2iuwjqvwcbzf0ekvlz home]# ls
peng  pengfei  redis  test  test1  www
[root@iz0jl2iuwjqvwcbzf0ekvlz home]# cd test1
[root@iz0jl2iuwjqvwcbzf0ekvlz test1]# mkdir -p test2/test3/test4  # 创建多级目录
[root@iz0jl2iuwjqvwcbzf0ekvlz test1]# cd /test2
-bash: cd: /test2: No such file or directory
[root@iz0jl2iuwjqvwcbzf0ekvlz test1]# cd test2
[root@iz0jl2iuwjqvwcbzf0ekvlz test2]# ls
test3
[root@iz0jl2iuwjqvwcbzf0ekvlz test2]# cd test3
[root@iz0jl2iuwjqvwcbzf0ekvlz test3]# ls
test4
[root@iz0jl2iuwjqvwcbzf0ekvlz test3]# 
```



> rmdir 删除目录

rmdir仅能删除空的目录，如果下面存在文件，需要先删除文件，递归删除多个目录 -p参数即可

rmdir -p [层级目录]

```bash
[root@iz0jl2iuwjqvwcbzf0ekvlz home]# rmdir test1
rmdir: failed to remove ‘test1’: Directory not empty
[root@iz0jl2iuwjqvwcbzf0ekvlz home]# rmdir peng
[root@iz0jl2iuwjqvwcbzf0ekvlz home]# rmdir -p test1/test2/test3/test4
[root@iz0jl2iuwjqvwcbzf0ekvlz home]# ls
pengfei  redis  test  www
[root@iz0jl2iuwjqvwcbzf0ekvlz home]# rmdir test
[root@iz0jl2iuwjqvwcbzf0ekvlz home]# ls
pengfei  redis  www
[root@iz0jl2iuwjqvwcbzf0ekvlz home]# 
```



> cp(复制文件或者目录)

cp 文件 移动到的文件名！

```bash
[root@www ~]# cp [-adfilprsu] 来源档(source) 目标档(destination)
[root@www ~]# cp [options] source1 source2 source3 .... directory
```

- **-a：**相当於 -pdr 的意思，至於 pdr 请参考下列说明；(常用)
- **-p：**连同文件的属性一起复制过去，而非使用默认属性(备份常用)；
- **-d：**若来源档为连结档的属性(link file)，则复制连结档属性而非文件本身；
- **-r：**递归持续复制，用於目录的复制行为；(常用)
- **-f：**为强制(force)的意思，若目标文件已经存在且无法开启，则移除后再尝试一次；
- **-i：**若目标档(destination)已经存在时，在覆盖时会先询问动作的进行(常用)
- **-l：**进行硬式连结(hard link)的连结档创建，而非复制文件本身。
- **-s：**复制成为符号连结档 (symbolic link)，亦即『捷径』文件；
- **-u：**若 destination 比 source 旧才升级 destination ！



> rm(移除文件或者目录)

语法：

```
rm [-fir] 文件或目录
```

选项与参数：

- -f ：就是 force 的意思，忽略不存在的文件，不会出现警告信息；
- -i ：互动模式，在删除前会询问使用者是否动作
- -r ：递归删除啊！最常用在目录的删除了！这是非常危险的选项！！！

```bash
rm -rf /  系统中的所有文件就被删除了，删库跑路
```

![image-20220512114000934](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512114000934.png)



> mv 移动文件或者目录  重命名文件

语法：

```bash
[root@www ~]# mv [-fiu] source destination
[root@www ~]# mv [options] source1 source2 source3 .... directory
```

选项与参数：

- -f ：force 强制的意思，如果目标文件已经存在，不会询问而直接覆盖；
- -i ：若目标文件 (destination) 已经存在时，就会询问是否覆盖！
- -u ：若目标文件已经存在，且 source 比较新，才会升级 (update)

 ```bash
 pengfei  redis  www
 [root@pengfei home]# mv pengfei peng #重命名文件夹
 [root@pengfei home]# ls
 peng  redis  www
 [root@pengfei home]# 
 ```



#### 基本属性

> 看懂文件属性

Linux系统是一种典型的多用户系统，不同的用户处于不同的地位，拥有不同的权限。为了保护系统的安全性，Linux系统对不同的用户访问同一文件（包括目录文件）的权限做了不同的规定。

在Linux中我们可以使用`ll`或者`ls –l`命令来显示一个文件的属性以及文件所属的用户和组，如：

![image-20220512114824791](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512114824791.png)

实例中，boot文件的第一个属性用"d"表示。"d"在Linux中代表该文件是一个目录文件。

在Linux中第一个字符代表这个文件是目录、文件或链接文件等等：

- 当为[ **d** ]则是目录
- 当为[ **-** ]则是文件；
- 若是[ **l** ]则表示为链接文档 ( link file )；
- 若是[ **b** ]则表示为装置文件里面的可供储存的接口设备 ( 可随机存取装置 )；
- 若是[ **c** ]则表示为装置文件里面的串行端口设备，例如键盘、鼠标 ( 一次性读取装置 )。

接下来的字符中，以三个为一组，且均为『rwx』 的三个参数的组合。

其中，[ r ]代表可读(read)、[ w ]代表可写(write)、[ x ]代表可执行(execute)。

要注意的是，这三个权限的位置不会改变，如果没有权限，就会出现减号[ - ]而已。

每个文件的属性由左边第一部分的10个字符来确定（如下图）：

![dasdadw40](https://gitee.com/linda12138/picgo/raw/master/images/dasdadw40.jpg)

从左至右用0-9这些数字来表示。

第0位确定文件类型，第1-3位确定属主（该文件的所有者）拥有该文件的权限。第4-6位确定属组（所有者的同组用户）拥有该文件的权限，第7-9位确定其他用户拥有该文件的权限。

其中：

第1、4、7位表示读权限，如果用"r"字符表示，则有读权限，如果用"-"字符表示，则没有读权限；

第2、5、8位表示写权限，如果用"w"字符表示，则有写权限，如果用"-"字符表示没有写权限；

第3、6、9位表示可执行权限，如果用"x"字符表示，则有执行权限，如果用"-"字符表示，则没有执行权限。

对于文件来说，它都有一个特定的所有者，也就是对该文件具有所有权的用户。

同时，在Linux系统中，用户是按组分类的，一个用户属于一个或多个组。

文件所有者以外的用户又可以分为文件所有者的同组用户和其他用户。

因此，Linux系统按文件所有者、文件所有者同组用户和其他用户来规定了不同的文件访问权限。

在以上实例中，boot 文件是一个目录文件，属主和属组都为 root。



> 修改文件属性

**1、chgrp：更改文件属组**

```
chgrp [-R] 属组名 文件名
```

-R：递归更改文件属组，就是在更改某个目录文件的属组时，如果加上-R的参数，那么该目录下的所有文件的属组都会更改。

**2、chown：更改文件属主，也可以同时更改文件属组**

```
chown [–R] 属主名 文件名
chown [-R] 属主名：属组名 文件名
```

==**3、chmod：更改文件9个属性**==

```
chmod [-R] xyz 文件或目录
```

Linux文件属性有两种设置方法，一种是数字（常用），一种是符号。

Linux文件的基本权限就有九个，分别是owner/group/others三种身份各有自己的read/write/execute权限。

先复习一下刚刚上面提到的数据：文件的权限字符为：『-rwxrwxrwx』， 这九个权限是三个三个一组的！其中，我们可以使用数字来代表各个权限，各权限的分数对照表如下：

```
r:4     w:2         x:1

可读可写不可执行  rw-  6
可读可写可执行    rwx  7

chmod 777   文件赋予所有用户可读可写可执行
```

每种身份(owner/group/others)各自的三个权限(r/w/x)分数是需要累加的，例如当权限为：[-rwxrwx---] 分数则是：

- owner = rwx = 4+2+1 = 7
- group = rwx = 4+2+1 = 7
- others= --- = 0+0+0 = 0

```bash
chmod 770 filename
```

![image-20220512160010961](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512160010961.png)

可以自己下去多进行测试！



#### 文件内容查看

> 概述

Linux系统中使用以下命令来查看文件的内容：

- `cat由第一行开始显示文件内容，用来读文章，或者读配置文件，都是用这个命令`

  语法：

  ```bash
  cat [-AbEnTv]
  ```

  选项与参数：

  - -A ：相当於 -vET 的整合选项，可列出一些特殊字符而不是空白而已；
  - -b ：列出行号，仅针对非空白行做行号显示，空白行不标行号！
  - -E ：将结尾的断行字节 $ 显示出来；
  - -n ：列印出行号，连同空白行也会有行号，与 -b 的选项不同；
  - -T ：将 [tab] 按键以 ^I 显示出来；
  - -v ：列出一些看不出来的特殊字符

- tac 从最后一行开始显示，可以看出 tac 是 cat 的倒着写！

  ![image-20220512160814472](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512160814472.png)

- `nl  显示的时候，顺道输出行号！ 看代码的时候，希望显示行号`

  语法：

  ```bash
  nl [-bnw] 文件
  ```

  选项与参数：

  - -b ：指定行号指定的方式，主要有两种：-b a ：表示不论是否为空行，也同样列出行号(类似 cat -n)；-b t ：如果有空行，空的那一行不要列出行号(默认值)；
  - -n ：列出行号表示的方法，主要有三种：-n ln ：行号在荧幕的最左方显示；-n rn ：行号在自己栏位的最右方显示，且不加 0 ；-n rz ：行号在自己栏位的最右方显示，且加 0 ；
  - -w ：行号栏位的占用的位数。

  ![image-20220512160912661](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512160912661.png)

- `more 一页一页的显示文件内容（空格代表翻页，enter代表向下看一行，：f行号配置）`

  在 more 这个程序的运行过程中，你有几个按键可以按的：

  - 空白键 (space)：代表向下翻一页；
  - Enter   ：代表向下翻『一行』；
  - /字串   ：代表在这个显示的内容当中，向下搜寻『字串』这个关键字；
  - :f    ：立刻显示出档名以及目前显示的行数；
  - q    ：代表立刻离开 more ，不再显示该文件内容。
  - b 或 [ctrl]-b ：代表往回翻页，不过这动作只对文件有用，对管线无用。

  ![image-20220512161125866](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512161125866.png)

- `less 与 more类似，但是比 more 更好的是，他可以往前翻页！（空格代表翻页，enter代表一行一行，上下键可以翻动页面） 退出用 q命令   查询字符串 /要查找的字符,向下查询使用？要查询的字符串，n继续查询下一个，N向上寻找`

  less运行时可以输入的命令有：

  - 空白键  ：向下翻动一页；
  - [pagedown]：向下翻动一页；
  - [pageup] ：向上翻动一页；
  - /字串  ：向下搜寻『字串』的功能；
  - ?字串  ：向上搜寻『字串』的功能；
  - n   ：重复前一个搜寻 (与 / 或 ? 有关！)
  - N   ：反向的重复前一个搜寻 (与 / 或 ? 有关！)
  - q   ：离开 less 这个程序；

  ![image-20220512161858470](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512161858470.png)

- head 只看头几行 通过-n参数来控制显示几行

  语法：

  ```bash
  head [-n number] 文件
  ```

  选项与参数：**-n** 后面接数字，代表显示几行的意思！

  默认的情况中，显示前面 10 行！若要显示前 20 行，就得要这样：

  ![image-20220512161420889](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512161420889.png)

- tail 只看尾巴几行

  语法：

  ```bash
  tail [-n number] 文件
  ```

  选项与参数：

  - -n ：后面接数字，代表显示几行的意思

  默认的情况中，显示最后 10 行！若要显示最后 20 行，就得要这样：

![image-20220512161508124](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512161508124.png)

你可以使用 *man [命令]*来查看各个命令的使用文档，如 ：man cp。

网络配置目录：`cd /etc/sysconfig/network-scripts`

![image-20220512160508959](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512160508959.png)

ifconfig 查看网络配置



> Linux链接概念

Linux 链接分两种，一种被称为硬链接（Hard Link），另一种被称为符号链接（Symbolic Link）。

情况下，**ln** 命令产生硬链接。

**硬连接**

硬连接指通过索引节点来进行连接。在 Linux 的文件系统中，保存在磁盘分区中的文件不管是什么类型都给它分配一个编号，称为索引节点号(Inode Index)。在 Linux 中，多个文件名指向同一索引节点是存在的。比如：A 是 B 的硬链接（A 和 B 都是文件名），则 A 的目录项中的 inode 节点号与 B 的目录项中的 inode 节点号相同，即一个 inode 节点对应两个不同的文件名，两个文件名指向同一个文件，A 和 B 对文件系统来说是完全平等的。删除其中任何一个都不会影响另外一个的访问。

硬连接的作用是允许一个文件拥有多个有效路径名，这样用户就可以建立硬连接到重要文件，以防止“误删”的功能。其原因如上所述，因为对应该目录的索引节点有一个以上的连接。只删除一个连接并不影响索引节点本身和其它的连接，只有当最后一个连接被删除后，文件的数据块及目录的连接才会被释放。也就是说，文件真正删除的条件是与之相关的所有硬连接文件均被删除。

**软连接**

另外一种连接称之为符号连接（Symbolic Link），也叫软连接。软链接文件有类似于 Windows 的快捷方式。它实际上是一个特殊的文件。在符号连接中，文件实际上是一个文本文件，其中包含的有另一文件的位置信息。比如：A 是 B 的软链接（A 和 B 都是文件名），A 的目录项中的 inode 节点号与 B 的目录项中的 inode 节点号不相同，A 和 B 指向的是两个不同的 inode，继而指向两块不同的数据块。但是 A 的数据块中存放的只是 B 的路径名（可以根据这个找到 B 的目录项）。A 和 B 之间是“主从”关系，如果 B 被删除了，A 仍然存在（因为两个是不同的文件），但指向的是一个无效的链接。



`touch命令创建文件`

`echo输入字符串，也可以输入到文件`

```bash
[root@pengfei home]# touch f1  #创建一个f1文件
[root@pengfei home]# ls
f1  peng  redis  www
[root@pengfei home]# ln f1 f2  #创建一个硬链接 f2
[root@pengfei home]# ls
f1  f2  peng  redis  www
[root@pengfei home]# ln -s f1 f3  #创建一个软链接（符号链接）f3
[root@pengfei home]# ls
f1  f2  f3  peng  redis  www
[root@pengfei home]# ll
total 12
-rw-r--r-- 2 root  root     0 May 12 16:30 f1
-rw-r--r-- 2 root  root     0 May 12 16:30 f2
lrwxrwxrwx 1 root  root     2 May 12 16:30 f3 -> f1
drwxr-xr-x 2 root  root  4096 May 12 10:33 peng
drwx------ 2 redis redis 4096 May 11 12:33 redis
drwxrw---x 3 www   www   4096 May 11 12:32 www
[root@pengfei home]# echo "i love pengfei">>f1  #给f1文件中写入字符串
[root@pengfei home]# ls
f1  f2  f3  peng  redis  www
[root@pengfei home]# ll
total 20
-rw-r--r-- 2 root  root    15 May 12 16:31 f1
-rw-r--r-- 2 root  root    15 May 12 16:31 f2
lrwxrwxrwx 1 root  root     2 May 12 16:30 f3 -> f1
drwxr-xr-x 2 root  root  4096 May 12 10:33 peng
drwx------ 2 redis redis 4096 May 11 12:33 redis
drwxrw---x 3 www   www   4096 May 11 12:32 www
[root@pengfei home]# cat f1
i love pengfei
[root@pengfei home]# cat f2
i love pengfei
[root@pengfei home]# cat f3
i love pengfei
[root@pengfei home]# 
```

删除f1之后，查看f2和f3的区别

```bash
[root@pengfei home]# rm -rf f1
[root@pengfei home]# ls
f2  f3  peng  redis  www
[root@pengfei home]# cat f2  #f2硬链接还在
i love pengfei
[root@pengfei home]# cat f3  # f3（软连接  符号链接）失效
cat: f3: No such file or directory
```



#### Vim编辑器

> 什么是Vim编辑器

Vim是从 vi 发展出来的一个文本编辑器。代码补完、编译及错误跳转等方便编程的功能特别丰富，在程序员中被广泛使用。

==（查看内容，编辑内容，保存内容！）==

简单的来说， vi 是老式的字处理器，不过功能已经很齐全了，但是还是有可以进步的地方。

vim 则可以说是程序开发者的一项很好用的工具。

所有的 Unix Like 系统都会内建 vi 文书编辑器，其他的文书编辑器则不一定会存在。

连 vim 的官方网站 (http://www.vim.org) 自己也说 vim 是一个程序开发工具而不是文字处理软件。

vim:键盘图：

![adwdad0](https://gitee.com/linda12138/picgo/raw/master/images/adwdad0.jpg)

> 三种使用模式

基本上 vi/vim 共分为三种模式，分别是**命令模式（Command mode）**，**输入模式（Insert mode）**和**底线命令模式（Last line mode）**。这三种模式的作用分别是：

**命令模式：**

用户刚刚启动 vi/vim，便进入了命令模式。

![image-20220512165117706](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512165117706.png)

此状态下敲击键盘动作会被Vim识别为命令，而非输入字符。比如我们此时按下i，并不会输入一个字符，i被当作了一个命令。

以下是常用的几个命令：

- **i** 切换到输入模式，以输入字符。
- **x** 删除当前光标所在处的字符。
- **:** 切换到底线命令模式，以在最底一行输入命令，如果是编辑模式，需要esc编辑模式。

若想要编辑文本：启动Vim，进入了命令模式，按下i，切换到输入模式。

命令模式只有一些最基本的命令，因此仍要依靠底线命令模式输入更多命令。

**输入模式：**

在命令模式下按下i就进入了输入模式。

![image-20220512165142887](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512165142887.png)

在输入模式中，可以使用以下按键：

- **字符按键以及Shift组合**，输入字符
- **ENTER**，回车键，换行
- **BACK SPACE**，退格键，删除光标前一个字符
- **DEL**，删除键，删除光标后一个字符
- **方向键**，在文本中移动光标
- **HOME**/**END**，移动光标到行首/行尾
- **Page Up**/**Page Down**，上/下翻页
- **Insert**，切换光标为输入/替换模式，光标将变成竖线/下划线
- **ESC**，退出输入模式，切换到命令模式

**底线命令模式**

在命令模式下按下:（英文冒号）就进入了底线命令模式。

![image-20220512165214464](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512165214464.png)

底线命令模式可以输入单个或多个字符的命令，可用的命令非常多。

在底线命令模式中，基本的命令有（已经省略了冒号）：

- q 退出程序
- w 保存文件

![image-20220512165240391](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512165240391.png)

按ESC键可随时退出底线命令模式。

![image-20220512165305225](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512165305225.png)



新建或者编辑文件，按i进入编辑模式，编写内容，编写完成后退出编辑模式，esc,退出之后进入底线命令模式：wq保存退出！



> Vim 按键说明

除了上面简易范例的 i, Esc, :wq 之外，其实 vim 还有非常多的按键可以使用。

**第一部分：一般模式可用的光标移动、复制粘贴、搜索替换等**

| 移动光标的方法     |                                                              |
| :----------------- | ------------------------------------------------------------ |
| h 或 向左箭头键(←) | 光标向左移动一个字符                                         |
| j 或 向下箭头键(↓) | 光标向下移动一个字符                                         |
| k 或 向上箭头键(↑) | 光标向上移动一个字符                                         |
| l 或 向右箭头键(→) | 光标向右移动一个字符                                         |
| [Ctrl] + [f]       | 屏幕『向下』移动一页，相当于 [Page Down]按键 (常用)          |
| [Ctrl] + [b]       | 屏幕『向上』移动一页，相当于 [Page Up] 按键 (常用)           |
| [Ctrl] + [d]       | 屏幕『向下』移动半页                                         |
| [Ctrl] + [u]       | 屏幕『向上』移动半页                                         |
| +                  | 光标移动到非空格符的下一行                                   |
| -                  | 光标移动到非空格符的上一行                                   |
| n< space>          | 那个 n 表示『数字』，例如 20 。按下数字后再按空格键，光标会向右移动这一行的 n 个字符。 |
| 0 或功能键[Home]   | 这是数字『 0 』：移动到这一行的最前面字符处 (常用)           |
| $ 或功能键[End]    | 移动到这一行的最后面字符处(常用)                             |
| H                  | 光标移动到这个屏幕的最上方那一行的第一个字符                 |
| M                  | 光标移动到这个屏幕的中央那一行的第一个字符                   |
| L                  | 光标移动到这个屏幕的最下方那一行的第一个字符                 |
| G                  | 移动到这个档案的最后一行(常用)                               |
| nG                 | n 为数字。移动到这个档案的第 n 行。例如 20G 则会移动到这个档案的第 20 行(可配合 :set nu) |
| gg                 | 移动到这个档案的第一行，相当于 1G 啊！(常用)                 |
| n< Enter>          | n 为数字。光标向下移动 n 行(常用)                            |

| 搜索替换 |                                                              |
| :------- | ------------------------------------------------------------ |
| /word    | 向光标之下寻找一个名称为 word 的字符串。例如要在档案内搜寻 vbird 这个字符串，就输入 /vbird 即可！(常用) |
| ?word    | 向光标之上寻找一个字符串名称为 word 的字符串。               |
| n        | 这个 n 是英文按键。代表重复前一个搜寻的动作。举例来说， 如果刚刚我们执行 /vbird 去向下搜寻 vbird 这个字符串，则按下 n 后，会向下继续搜寻下一个名称为 vbird 的字符串。如果是执行 ?vbird 的话，那么按下 n 则会向上继续搜寻名称为 vbird 的字符串！ |
| N        | 这个 N 是英文按键。与 n 刚好相反，为『反向』进行前一个搜寻动作。例如 /vbird 后，按下 N 则表示『向上』搜寻 vbird 。 |

| 删除、复制与粘贴 |                                                              |
| :--------------- | ------------------------------------------------------------ |
| x, X             | 在一行字当中，x 为向后删除一个字符 (相当于 [del] 按键)， X 为向前删除一个字符(相当于 [backspace] 亦即是退格键) (常用) |
| nx               | n 为数字，连续向后删除 n 个字符。举例来说，我要连续删除 10 个字符， 『10x』。 |
| dd               | 删除游标所在的那一整行(常用)                                 |
| ndd              | n 为数字。删除光标所在的向下 n 行，例如 20dd 则是删除 20 行 (常用) |
| d1G              | 删除光标所在到第一行的所有数据                               |
| dG               | 删除光标所在到最后一行的所有数据                             |
| d$               | 删除游标所在处，到该行的最后一个字符                         |
| d0               | 那个是数字的 0 ，删除游标所在处，到该行的最前面一个字符      |
| yy               | 复制游标所在的那一行(常用)                                   |
| nyy              | n 为数字。复制光标所在的向下 n 行，例如 20yy 则是复制 20 行(常用) |
| y1G              | 复制游标所在行到第一行的所有数据                             |
| yG               | 复制游标所在行到最后一行的所有数据                           |
| y0               | 复制光标所在的那个字符到该行行首的所有数据                   |
| y$               | 复制光标所在的那个字符到该行行尾的所有数据                   |
| p, P             | p 为将已复制的数据在光标下一行贴上，P 则为贴在游标上一行！举例来说，我目前光标在第 20 行，且已经复制了 10 行数据。则按下 p 后， 那 10 行数据会贴在原本的 20 行之后，亦即由 21 行开始贴。但如果是按下 P 呢？那么原本的第 20 行会被推到变成 30 行。(常用) |
| J                | 将光标所在行与下一行的数据结合成同一行                       |
| c                | 重复删除多个数据，例如向下删除 10 行，[ 10cj ]               |
| u                | 复原前一个动作。(常用)                                       |
| [Ctrl]+r         | 重做上一个动作。(常用)                                       |

**第二部分：一般模式切换到编辑模式的可用的按钮说明**

| 进入输入或取代的编辑模式 |                                                              |
| :----------------------- | ------------------------------------------------------------ |
| i, I                     | 进入输入模式(Insert mode)：i 为『从目前光标所在处输入』， I 为『在目前所在行的第一个非空格符处开始输入』。(常用) |
| a, A                     | 进入输入模式(Insert mode)：a 为『从目前光标所在的下一个字符处开始输入』， A 为『从光标所在行的最后一个字符处开始输入』。(常用) |
| o, O                     | 进入输入模式(Insert mode)：这是英文字母 o 的大小写。o 为『在目前光标所在的下一行处输入新的一行』；O 为在目前光标所在处的上一行输入新的一行！(常用) |
| r, R                     | 进入取代模式(Replace mode)：r 只会取代光标所在的那一个字符一次；R会一直取代光标所在的文字，直到按下 ESC 为止；(常用) |
| [Esc]                    | 退出编辑模式，回到一般模式中(常用)                           |

**第三部分：一般模式切换到指令行模式的可用的按钮说明**

| 指令行的储存、离开等指令                                     |                                                              |
| :----------------------------------------------------------- | ------------------------------------------------------------ |
| :w                                                           | 将编辑的数据写入硬盘档案中(常用)                             |
| :w!                                                          | 若文件属性为『只读』时，强制写入该档案。不过，到底能不能写入， 还是跟你对该档案的档案权限有关啊！ |
| :q                                                           | 离开 vi (常用)                                               |
| :q!                                                          | 若曾修改过档案，又不想储存，使用 ! 为强制离开不储存档案。    |
| 注意一下啊，那个惊叹号 (!) 在 vi 当中，常常具有『强制』的意思～ |                                                              |
| :wq                                                          | 储存后离开，若为 :wq! 则为强制储存后离开 (常用)              |
| ZZ                                                           | 这是大写的 Z 喔！若档案没有更动，则不储存离开，若档案已经被更动过，则储存后离开！ |
| :w [filename]                                                | 将编辑的数据储存成另一个档案（类似另存新档）                 |
| :r [filename]                                                | 在编辑的数据中，读入另一个档案的数据。亦即将 『filename』 这个档案内容加到游标所在行后面 |
| :n1,n2 w [filename]                                          | 将 n1 到 n2 的内容储存成 filename 这个档案。                 |
| :! command                                                   | 暂时离开 vi 到指令行模式下执行 command 的显示结果！例如 『:! ls /home』即可在 vi 当中看 /home 底下以 ls 输出的档案信息！ |
| :set nu                                                      | 显示行号，设定之后，会在每一行的前缀显示该行的行号           |
| :set nonu                                                    | 与 set nu 相反，为取消行号！                                 |



#### 账号管理

> 简介

Linux系统是一个多用户多任务的分时操作系统，任何一个要使用系统资源的用户，都必须首先向系统管理员申请一个账号，然后以这个账号的身份进入系统。

用户的账号一方面可以帮助系统管理员对使用系统的用户进行跟踪，并控制他们对系统资源的访问；另一方面也可以帮助用户组织文件，并为用户提供安全性保护。

每个用户账号都拥有一个唯一的用户名和各自的口令。

用户在登录时键入正确的用户名和口令后，就能够进入系统和自己的主目录。

实现用户账号的管理，要完成的工作主要有如下几个方面：

- 用户账号的添加、删除与修改。
- 用户口令的管理。
- 用户组的管理。

> 用户账号的管理

用户账号的管理工作主要涉及到用户账号的添加、修改和删除。

添加用户账号就是在系统中创建一个新账号，然后为新账号分配用户号、用户组、主目录和登录Shell等资源。

属主，属组

> 添加账号 useradd

```bash
useradd -选项 用户名
```

参数说明：

- 选项 :

- - -c comment 指定一段注释性描述。
  
  - -d 目录 指定用户主目录，如果此目录不存在，则同时使用-m选项，可以创建主目录。
  
  - -g 用户组 指定用户所属的用户组。
  
  - -G 用户组，用户组 指定用户所属的附加组。

  - -m　使用者目录如不存在则自动建立。
  
    ```bash
    [root@pengfei home]# useradd -m pengfei12138
    [root@pengfei home]# ls
    peng  pengfei12138  redis  www
    
    理解下本质：Linux中一切皆文件，这里添加用户说白了就是往某一个文件中写入用户的信息了！ /etc/passwd
    ```
  
    
  
  - -s Shell文件 指定用户的登录Shell。
  
  - -u 用户号 指定用户的用户号，如果同时有-o选项，则可以重复使用其他用户的标识号。
  
- 用户名 :

  - 指定新账号的登录名。




> 删除用户

如果一个用户的账号不再使用，可以从系统中删除。

删除用户账号就是要将/etc/passwd等系统文件中的该用户记录删除，必要时还删除用户的主目录。

删除一个已有的用户账号使用userdel命令，其格式如下：

```
userdel 选项 用户名
```

常用的选项是 **-r**，它的作用是把用户的主目录一起删除。

```bash
[root@pengfei home]# userdel -r pengfei12138
[root@pengfei home]# ls
peng  redis  www
```

此命令删除用户pengfei12138在系统文件中（主要是/etc/passwd, /etc/shadow, /etc/group等）的记录，同时删除用户的主目录。

> 修改用户 usermod

修改用户账号就是根据实际情况更改用户的有关属性，如用户号、主目录、用户组、登录Shell等。

修改已有用户的信息使用usermod命令，其格式如下：

```bash
usermod -选项 用户名
```

常用的选项包括-c, -d, -m, -g, -G, -s, -u以及-o等，这些选项的意义与useradd命令中的选项一样，可以为用户指定新的资源值。

例如：

```bash
[root@pengfei home]# usermod -d /home/233 pengfei12138
```

修改之后查看配置文件即可！



> 切换用户

root用户

![image-20220512173106803](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512173106803.png)

1.切换用户的命令为：su username 【username是你的用户名哦】

2.从普通用户切换到root用户，还可以使用命令：sudo su

3.在终端输入exit或logout或使用快捷方式ctrl+d，可以退回到原来用户，其实ctrl+d也是执行的exit命令

![image-20220512173431104](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512173431104.png)

4.在切换用户时，如果想在切换用户之后使用新用户的工作环境，可以在su和username之间加-，例如：【su - root】

$表示普通用户

\#表示超级用户，也就是root用户



> 用户密码设置问题

我们一般通过root创建用户的时候！要配置密码！

Linux上输入密码是不会显示的，正常输入即可！

用户管理的一项重要内容是用户口令的管理。用户账号刚创建时没有口令，但是被系统锁定，无法使用，必须为其指定口令后才可以使用，即使是指定空口令。

指定和修改用户口令的Shell命令是passwd。超级用户可以为自己和其他用户指定口令，普通用户只能用它修改自己的口令。

命令的格式为：

```bash
passwd 选项 用户名
```

可使用的选项：

- -l 锁定口令，即禁用账号。
- -u 口令解锁。
- -d 使账号无口令。
- -f 强迫用户下次登录时修改口令。

如果默认用户名，则修改当前用户的口令。

例如，假设当前用户是pengfei12138，则下面的命令修改该用户自己的口令：

```bash
$ passwd
Old password:******
New password:*******
Re-enter new password:*******
```

如果是超级用户，可以用下列形式指定任何用户的口令：

```bash
# passwd pengfei12138
New password:*******
Re-enter new password:*******
```

普通用户修改自己的口令时，passwd命令会先询问原口令，验证后再要求用户输入两遍新口令，如果两次输入的口令一致，则将这个口令指定给用户；而超级用户为用户指定口令时，就不需要知道原口令。

为了系统安全起见，用户应该选择比较复杂的口令，例如最好使用8位长的口令，口令中包含有大写、小写字母和数字，并且应该与姓名、生日等不相同。

为用户指定空口令时，执行下列形式的命令：

```bash
# passwd -d pengfei12138
```

此命令将用户 pengfei12138的口令删除，这样用户 pengfei12138下一次登录时，系统就不再允许该用户登录了。

passwd 命令还可以用 -l(lock) 选项锁定某一用户，使其不能登录，例如：

```bash
# passwd -l pengfei12138
```



#### 用户组管理

每个用户都有一个用户组，系统可以对一个用户组中的所有用户进行集中管理。不同Linux 系统对用户组的规定有所不同，如Linux下的用户属于与它同名的用户组，这个用户组在创建用户时同时创建。

用户组的管理涉及用户组的添加、删除和修改。`组的增加、删除和修改实际上就是对/etc/group文件的更新。`

> 创建一个用户组 groupadd命令

```
groupadd 选项 用户组
```

可以使用的选项有：

- -g GID 指定新用户组的组标识号（GID）。
- -o 一般与-g选项同时使用，表示新用户组的GID可以与系统已有用户组的GID相同。

![image-20220512190701784](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512190701784.png)

创建完用户组后可以得到组的id,这个id是可以指定的~ `-g520`如果不指定就是自增1

> 如果要删除一个已有的用户组，使用groupdel命令

```
groupdel 用户组
```

例如：

```
# groupdel group1
```

此命令从系统中删除组group1。

![image-20220512191004012](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512191004012.png)

> 修改用户组的属性使用groupmod命令

```
groupmod 选项 用户组
```

常用的选项有：

- -g GID 为用户组指定新的组标识号。
- -o 与-g选项同时使用，用户组的新GID可以与系统已有用户组的GID相同。
- -n新用户组 将用户组的名字改为新名字

```bash
# 此命令将组group2的组标识号修改为102。
groupmod -g 102 group2

# 将组group2的标识号改为10000，组名修改为group3。
groupmod –g 10000 -n group3 group2
```

![image-20220512191207608](https://gitee.com/linda12138/picgo/raw/master/images/image-20220512191207608.png)



> 切换组

如果一个用户同时属于多个用户组，那么用户可以在用户组之间切换，以便具有其他用户组的权限。

用户可以在登录后，使用命令newgrp切换到其他用户组，这个命令的参数就是目的用户组。例如：

```bash
$ newgrp root
```

这条命令将当前用户切换到root用户组，前提条件是root用户组确实是该用户的主组或附加组。



> /etc/passwd

完成用户管理的工作有许多种方法，但是每一种方法实际上都是对有关的系统文件进行修改。

与用户和用户组相关的信息都存放在一些系统文件中，这些文件包括/etc/passwd, /etc/shadow, /etc/group等。

下面分别介绍这些文件的内容。

**/etc/passwd文件是用户管理工作涉及的最重要的一个文件。**

Linux系统中的每个用户都在/etc/passwd文件中有一个对应的记录行，它记录了这个用户的一些基本属性。

这个文件对所有用户都是可读的。它的内容类似下面的例子：

```bash
[root@pengfei ~]# cat /etc/group
root:x:0:
bin:x:1:
daemon:x:2:
sys:x:3:
adm:x:4:
tty:x:5:
disk:x:6:
lp:x:7:
mem:x:8:
kmem:x:9:
wheel:x:10:
cdrom:x:11:
mail:x:12:postfix
man:x:15:
dialout:x:18:
floppy:x:19:
games:x:20:
tape:x:30:
video:x:39:
ftp:x:50:
lock:x:54:
audio:x:63:
nobody:x:99:
users:x:100:
utmp:x:22:
utempter:x:35:
ssh_keys:x:999:
input:x:998:
systemd-journal:x:190:
systemd-network:x:192:
dbus:x:81:
polkitd:x:997:
postdrop:x:90:
postfix:x:89:
chrony:x:996:
sshd:x:74:
ntp:x:38:
tcpdump:x:72:
nscd:x:28:
www:x:1000:
mysql:x:1001:
cgred:x:995:
redis:x:1002:
pengfei:x:666:
[root@pengfei ~]# cat /etc/passwd
root:x:0:0:root:/root:/bin/bash
bin:x:1:1:bin:/bin:/sbin/nologin
daemon:x:2:2:daemon:/sbin:/sbin/nologin
adm:x:3:4:adm:/var/adm:/sbin/nologin
lp:x:4:7:lp:/var/spool/lpd:/sbin/nologin
sync:x:5:0:sync:/sbin:/bin/sync
shutdown:x:6:0:shutdown:/sbin:/sbin/shutdown
halt:x:7:0:halt:/sbin:/sbin/halt
mail:x:8:12:mail:/var/spool/mail:/sbin/nologin
operator:x:11:0:operator:/root:/sbin/nologin
games:x:12:100:games:/usr/games:/sbin/nologin
ftp:x:14:50:FTP User:/var/ftp:/sbin/nologin
nobody:x:99:99:Nobody:/:/sbin/nologin
systemd-network:x:192:192:systemd Network Management:/:/sbin/nologin
dbus:x:81:81:System message bus:/:/sbin/nologin
polkitd:x:999:997:User for polkitd:/:/sbin/nologin
postfix:x:89:89::/var/spool/postfix:/sbin/nologin
chrony:x:998:996::/var/lib/chrony:/sbin/nologin
sshd:x:74:74:Privilege-separated SSH:/var/empty/sshd:/sbin/nologin
ntp:x:38:38::/etc/ntp:/sbin/nologin
tcpdump:x:72:72::/:/sbin/nologin
nscd:x:28:28:NSCD Daemon:/:/sbin/nologin
www:x:1000:1000::/home/www:/sbin/nologin
mysql:x:1001:1001::/home/mysql:/sbin/nologin
redis:x:1002:1002::/home/redis:/sbin/nologin
pengfei12138:x:1003:666::/home/pengfei12138:/bin/bash
```



从上面的例子我们可以看到，/etc/passwd中一行记录对应着一个用户，每行记录又被冒号(:)分隔为7个字段，其格式和具体含义如下：

```bash
用户名:口令:用户标识号:组标识号:注释性描述:主目录:登录Shell
```

1）"用户名"是代表用户账号的字符串。

通常长度不超过8个字符，并且由大小写字母和/或数字组成。登录名中不能有冒号(:)，因为冒号在这里是分隔符。

为了兼容起见，登录名中最好不要包含点字符(.)，并且不使用连字符(-)和加号(+)打头。

2）“口令”一些系统中，存放着加密后的用户口令字。

虽然这个字段存放的只是用户口令的加密串，不是明文，但是由于/etc/passwd文件对所有用户都可读，所以这仍是一个安全隐患。因此，现在许多Linux 系统（如SVR4）都使用了shadow技术，把真正的加密后的用户口令字存放到/etc/shadow文件中，而在/etc/passwd文件的口令字段中只存放一个特殊的字符，例如“x”或者“*”。

3）“用户标识号”是一个整数，系统内部用它来标识用户。

一般情况下它与用户名是一一对应的。如果几个用户名对应的用户标识号是一样的，系统内部将把它们视为同一个用户，但是它们可以有不同的口令、不同的主目录以及不同的登录Shell等。

通常用户标识号的取值范围是0～65 535。0是超级用户root的标识号，1～99由系统保留，作为管理账号，普通用户的标识号从100开始。在Linux系统中，这个界限是500。

4）“组标识号”字段记录的是用户所属的用户组。

它对应着/etc/group文件中的一条记录。

5)“注释性描述”字段记录着用户的一些个人情况。

例如用户的真实姓名、电话、地址等，这个字段并没有什么实际的用途。在不同的Linux 系统中，这个字段的格式并没有统一。在许多Linux系统中，这个字段存放的是一段任意的注释性描述文字，用作finger命令的输出。

6)“主目录”，也就是用户的起始工作目录。

它是用户在登录到系统之后所处的目录。在大多数系统中，各用户的主目录都被组织在同一个特定的目录下，而用户主目录的名称就是该用户的登录名。各用户对自己的主目录有读、写、执行（搜索）权限，其他用户对此目录的访问权限则根据具体情况设置。

7)用户登录后，要启动一个进程，负责将用户的操作传给内核，这个进程是用户登录到系统后运行的命令解释器或某个特定的程序，即Shell。

Shell是用户与Linux系统之间的接口。Linux的Shell有许多种，每种都有不同的特点。常用的有sh(Bourne Shell), csh(C Shell), ksh(Korn Shell), tcsh(TENEX/TOPS-20 type C Shell), bash(Bourne Again Shell)等。

系统管理员可以根据系统情况和用户习惯为用户指定某个Shell。如果不指定Shell，那么系统使用sh为默认的登录Shell，即这个字段的值为/bin/sh。

用户的登录Shell也可以指定为某个特定的程序（此程序不是一个命令解释器）。

利用这一特点，我们可以限制用户只能运行指定的应用程序，在该应用程序运行结束后，用户就自动退出了系统。有些Linux 系统要求只有那些在系统中登记了的程序才能出现在这个字段中。

8)系统中有一类用户称为伪用户（pseudo users）。

这些用户在/etc/passwd文件中也占有一条记录，但是不能登录，因为它们的登录Shell为空。它们的存在主要是方便系统管理，满足相应的系统进程对文件属主的要求。

常见的伪用户如下所示：

```
伪 用 户 含 义
bin 拥有可执行的用户命令文件
sys 拥有系统文件
adm 拥有帐户文件
uucp UUCP使用
lp lp或lpd子系统使用
nobody NFS使用
```

> /etc/shadow

```bash
[root@pengfei ~]# cat /etc/shadow
root:$6$SOboT/UTtROg5R$r5/vASg89bitt3.X2hFjGMH2m.6yH73td/jMTK1Eu/8bOtXwwsTz0fIls9qp189EmocGgfwi4z8JkG8qKTdPG/:19123:0:99999:7:::
bin:*:17110:0:99999:7:::
daemon:*:17110:0:99999:7:::
adm:*:17110:0:99999:7:::
lp:*:17110:0:99999:7:::
sync:*:17110:0:99999:7:::
shutdown:*:17110:0:99999:7:::
halt:*:17110:0:99999:7:::
mail:*:17110:0:99999:7:::
operator:*:17110:0:99999:7:::
games:*:17110:0:99999:7:::
ftp:*:17110:0:99999:7:::
nobody:*:17110:0:99999:7:::
systemd-network:!!:17454::::::
dbus:!!:17454::::::
polkitd:!!:17454::::::
postfix:!!:17454::::::
chrony:!!:17454::::::
sshd:!!:17454::::::
ntp:!!:17454::::::
tcpdump:!!:17454::::::
nscd:!!:17454::::::
www:!!:19123:0:99999:7:::
mysql:!!:19123:0:99999:7:::
redis:!!:19123:0:99999:7:::
pengfei12138:$6$4v9kN.A6$7XmgkcJD84Qyctxni8xcd5pc./ptsFs3.j2YUXRBaOFacP0VFlc2BL7vQaS3hlHrpmAtE46d1hkBmIS9qZcVI.:19124:0:99999:7:::
```



**1、除了上面列出的伪用户外，还有许多标准的伪用户，例如：audit, cron, mail, usenet等，它们也都各自为相关的进程和文件所需要。**

由于/etc/passwd文件是所有用户都可读的，如果用户的密码太简单或规律比较明显的话，一台普通的计算机就能够很容易地将它破解，因此对安全性要求较高的Linux系统都把加密后的口令字分离出来，单独存放在一个文件中，这个文件是/etc/shadow文件。有超级用户才拥有该文件读权限，这就保证了用户密码的安全性。

**2、/etc/shadow中的记录行与/etc/passwd中的一一对应，它由pwconv命令根据/etc/passwd中的数据自动产生**

它的文件格式与/etc/passwd类似，由若干个字段组成，字段之间用":"隔开。这些字段是：

```bash
登录名:加密口令:最后一次修改时间:最小时间间隔:最大时间间隔:警告时间:不活动时间:失效时间:标志
```

1. "登录名"是与/etc/passwd文件中的登录名相一致的用户账号
2. "口令"字段存放的是加密后的用户口令字，长度为13个字符。如果为空，则对应用户没有口令，登录时不需要口令；如果含有不属于集合 { ./0-9A-Za-z }中的字符，则对应的用户不能登录。
3. "最后一次修改时间"表示的是从某个时刻起，到用户最后一次修改口令时的天数。时间起点对不同的系统可能不一样。例如在SCO Linux 中，这个时间起点是1970年1月1日。
4. "最小时间间隔"指的是两次修改口令之间所需的最小天数。
5. "最大时间间隔"指的是口令保持有效的最大天数。
6. "警告时间"字段表示的是从系统开始警告用户到用户密码正式失效之间的天数。
7. "不活动时间"表示的是用户没有登录活动但账号仍能保持有效的最大天数。
8. "失效时间"字段给出的是一个绝对的天数，如果使用了这个字段，那么就给出相应账号的生存期。期满后，该账号就不再是一个合法的账号，也就不能再用来登录了。

> /etc/group

```bash
[root@pengfei ~]# cat /etc/group
root:x:0:
bin:x:1:
daemon:x:2:
sys:x:3:
adm:x:4:
tty:x:5:
disk:x:6:
lp:x:7:
mem:x:8:
kmem:x:9:
wheel:x:10:
cdrom:x:11:
mail:x:12:postfix
man:x:15:
dialout:x:18:
floppy:x:19:
games:x:20:
tape:x:30:
video:x:39:
ftp:x:50:
lock:x:54:
audio:x:63:
nobody:x:99:
users:x:100:
utmp:x:22:
utempter:x:35:
ssh_keys:x:999:
input:x:998:
systemd-journal:x:190:
systemd-network:x:192:
dbus:x:81:
polkitd:x:997:
postdrop:x:90:
postfix:x:89:
chrony:x:996:
sshd:x:74:
ntp:x:38:
tcpdump:x:72:
nscd:x:28:
www:x:1000:
mysql:x:1001:
cgred:x:995:
redis:x:1002:
pengfei:x:666:
```

用户组的所有信息都存放在/etc/group文件中。

将用户分组是Linux 系统中对用户进行管理及控制访问权限的一种手段。

每个用户都属于某个用户组；一个组中可以有多个用户，一个用户也可以属于不同的组。

当一个用户同时是多个组中的成员时，在/etc/passwd文件中记录的是用户所属的主组，也就是登录时所属的默认组，而其他组称为附加组。

用户要访问属于附加组的文件时，必须首先使用newgrp命令使自己成为所要访问的组中的成员。

用户组的所有信息都存放在/etc/group文件中。此文件的格式也类似于/etc/passwd文件，由冒号(:)隔开若干个字段，这些字段有：

```bash
组名:口令:组标识号:组内用户列表
```

1. "组名"是用户组的名称，由字母或数字构成。与/etc/passwd中的登录名一样，组名不应重复。
2. "口令"字段存放的是用户组加密后的口令字。一般Linux 系统的用户组都没有口令，即这个字段一般为空，或者是*。
3. "组标识号"与用户标识号类似，也是一个整数，被系统内部用来标识组。
4. "组内用户列表"是属于这个组的所有用户的列表/b]，不同用户之间用逗号(,)分隔。这个用户组可能是用户的主组，也可能是附加组。



#### 磁盘管理

> 概述

Linux磁盘管理好坏直接关系到整个系统的性能问题。

Linux磁盘管理常用命令为 df、du。

- df ：列出文件系统的整体磁盘使用量
- du：检查磁盘空间使用量



> df

df命令参数功能：检查文件系统的磁盘空间占用情况。可以利用该命令来获取硬盘被占用了多少空间，目前还剩下多少空间等信息。

语法：

```bash
df [-ahikHTm] [目录或文件名]
```

![image-20220513090435692](https://gitee.com/linda12138/picgo/raw/master/images/image-20220513090435692.png)

选项与参数：

- -a ：列出所有的文件系统，包括系统特有的 /proc 等文件系统；
- -k ：以 KBytes 的容量显示各文件系统；
- -m ：以 MBytes 的容量显示各文件系统；
- -h ：以人们较易阅读的 GBytes, MBytes, KBytes 等格式自行显示；
- -H ：以 M=1000K 取代 M=1024K 的进位方式；
- -T ：显示文件系统类型, 连同该 partition 的 filesystem 名称 (例如 ext3) 也列出；
- -i ：不用硬盘容量，而以 inode 的数量来显示



> du

Linux du命令也是查看使用空间的，但是与df命令不同的是Linux du命令是对文件和目录磁盘使用的空间的查看，还是和df命令有一些区别的，这里介绍Linux du命令。

语法：

```bash
du [-ahskm] 文件或目录名称
```

选项与参数：

- -a ：列出所有的文件与目录容量，因为默认仅统计目录底下的文件量而已。
- -h ：以人们较易读的容量格式 (G/M) 显示；
- -s ：列出总量而已，而不列出每个各别的目录占用容量；
- -S ：不包括子目录下的总计，与 -s 有点差别。
- -k ：以 KBytes 列出容量显示；
- -m ：以 MBytes 列出容量显示；

![image-20220513090716736](https://gitee.com/linda12138/picgo/raw/master/images/image-20220513090716736.png)

![image-20220513090838437](https://gitee.com/linda12138/picgo/raw/master/images/image-20220513090838437.png)



> 磁盘挂载与卸除

根文件系统之外的其他文件要想能够被访问，都必须通过“关联”至根文件系统上的某个目录来实现，此关联操作即为“挂载”，此目录即为“挂载点”,解除此关联关系的过程称之为“卸载”

Linux 的磁盘挂载使用mount命令，卸载使用umount命令。

磁盘挂载语法：

```bash
mount [-t 文件系统] [-L Label名] [-o 额外选项] [-n] 装置文件名 挂载点
```

测试：

```bash
# 将 /dev/hdc6 挂载到 /mnt/hdc6 上面！
[root@www ~]# mkdir /mnt/hdc6
[root@www ~]# mount /dev/hdc6 /mnt/hdc6
[root@www ~]# df
Filesystem           1K-blocks     Used Available Use% Mounted on
/dev/hdc6              1976312     42072   1833836   3% /mnt/hdc6
```

磁盘卸载命令 umount 语法：

```bash
umount [-fn] 装置文件名或挂载点
```

选项与参数：

- -f ：强制卸除！可用在类似网络文件系统 (NFS) 无法读取到的情况下；
- -n ：不升级 /etc/mtab 情况下卸除。

卸载/dev/hdc6

```bash
[root@www ~]# umount /dev/hdc6
```



#### 进程管理

对于我们开发人员来说，其实Linux更多偏向于使用即可！

> 什么是进程

1. 在Linux中，每一个程序都是有自己的一个进程，每一个进程都有一个id号
2. 每一个进程，都会有一个父进程！
3. 进程可以有两种存在方式：前台，后台运行！
4. 一般的话服务都是后台运行的，基本的程序都是前台运行的！

> 命令

**ps** 查看当前系统中正在执行的各种进程的信息！

ps -xx:

- -a 显示当前终端运行的所有的进程信息(当前的进程一个)
- -u 以用户的信息显示进程
- -x 显示后台运行进程的参数!

```bash
#ps -aux 查看所有的进程
ps -aux|grep mysql

# | 在Linux中这个叫做管道符  A|B 
# grep 查找文件中符合条件的字符串
```

对于我们来说，这里目前只需要记住一个命令即可 ps -xx|grep [进程名] 



**ps -ef:可以查看到父进程的信息**

```bash
ps -ef|grep mysql # 看父进程我们一般可以通过目录树结构来查看！

#进程树
pstree -pu
	-p 显示父id
	-u 显示当前用户组
```



结束进程：杀掉进程

`kill -9 进程的id`  表示强制结束该进程

我们平时写的java代码死循环了，可以结束进程



### 环境安装

------

#### JDK安装

1. 下载JDK rpm
2. 安装java环境

1、rpm下载地址http://www.oracle.com/technetwork/java/javase/downloads/index.html

2、如果有安装openjdk 则卸载

```bash
[root@pengfei peng]# java -version
java version "1.8.0_121"
Java(TM) SE Runtime Environment (build 1.8.0_121-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.121-b13, mixed mode)
# 检查
[root@pengfei peng]# rpm -qa|grep jdk
jdk1.8.0_121-1.8.0_121-fcs.x86_64
# 卸载 -e --nodeps 强制删除
[root@pengfei peng]# rpm -e --nodeps jdk1.8.0_121-1.8.0_121-fcs.x86_64
[root@pengfei peng]# java -version
-bash: /usr/bin/java: No such file or directory  # OK
```

```bash
# 安装jdk
[root@pengfei peng]# rpm -ivh jdk-8u333-linux-x64.rpm 
```

![image-20220513100651218](https://gitee.com/linda12138/picgo/raw/master/images/image-20220513100651218.png)

```bash
# 配置环境变量
vim /etc/profile

# 在最上（下）面添加
JAVA_HOME=usr/java/jdk1.8.0_333-amd64
CLASSPATH=%JAVA_HOME%/lib:%JAVA_HOME%/jre/lib
PATH=$PATH:$JAVA_HOME/bin:$JAVA_HOME/jre/bin
export PATH CLASSPATH JAVA_HOME
```

![image-20220513101527359](https://gitee.com/linda12138/picgo/raw/master/images/image-20220513101527359.png)

```bash
# 让新增的环境变量生效！
source /etc/profile
```

测试：

```bash
[root@pengfei ~]# java -version
java version "1.8.0_333"
Java(TM) SE Runtime Environment (build 1.8.0_333-b02)
Java HotSpot(TM) 64-Bit Server VM (build 25.333-b02, mixed mode)
```



#### Tomcat安装

ssm war就需要放到tomcat中运行

1. 下载tomcat9

2. 解压这个文件

   ```bash
   tar -zxvf apache-tomcat-9.0.62.tar.gz
   ```

   ![image-20220513103550946](https://gitee.com/linda12138/picgo/raw/master/images/image-20220513103550946.png)

3. 启动tomcat

```bash
# 执行：startup.sh -->启动tomcat
# 执行：shutdown.sh -->关闭tomcat
./startup.sh
./shutdown.sh
```



![image-20220513103733565](https://gitee.com/linda12138/picgo/raw/master/images/image-20220513103733565.png)

如果防火墙8080端口开了并且阿里云安全组也开放了这个时候就可以直接访问远程了！

```bash
# 查看firewall服务状态
systemctl status firewalld

# 开启、重启、关闭、firewalld.service服务
# 开启
service firewalld start
# 重启
service firewalld restart
# 关闭
service firewalld stop

# 查看防火墙规则
firewall-cmd --list-all    # 查看全部信息
firewall-cmd --list-ports  # 只看端口信息

# 开启端口
开端口命令：firewall-cmd --zone=public --add-port=80/tcp --permanent
重启防火墙：systemctl restart firewalld.service

命令含义：
--zone #作用域
--add-port=80/tcp  #添加端口，格式为：端口/通讯协议
--permanent   #永久生效，没有此参数重启后失效
```

上传完毕的项目直接购买自己的域名，备案解析即可！



#### Docker安装

1. 官网安装参考手册：https://docs.docker.com/install/linux/docker-ce/centos/
2. 我们现在是在Linux下执行，一定要联网，yum在线安装



> 安装

1. 检测centOS版本

   ```bash
   [root@pengfei etc]# cat redhat-release 
   CentOS Linux release 7.4.1708 (Core) 
   ```

   

2. 安装准备环境

   ```bash
   yum -y install 包名   #yum install 安装命令  -y所有的提示都为y
   
   yum -y install gcc
   yum -y install gcc-c++
   ```

3. 清除以前的版本

   ```bash
   yum -y remove docker docker-common docker-selinux docker-engine
   # 官网版本
   yum remove docker \
             docker-client \
             docker-client-latest \
             docker-common \
             docker-latest \
             docker-latest-logrotate \
             docker-logrotate \
             docker-engine
   ```

   

4. 安装需要的软件包

   ```bash
   yum install -y yum-utils device-mapper-persistent-data lvm2
   ```

5. 设置stable镜像仓库

   ```bash
   # 正确推荐使用国内的
   yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
   ```

6. 更新yum软件包索引

   ```bash
   yum makecache fast
   ```

7. 安装Docker CE

   ```bash
   yum -y install docker-ce docker-ce-cli containerd.io
   ```

8. 启动docker

   ```bash
   systemctl start docker
   ```

9. 测试

   ```bash
   docker version
   
   docker run hello-world
   
   docker images
   ```



