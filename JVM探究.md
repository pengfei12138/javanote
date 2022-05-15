## JVM探究

- 请你谈谈你对JVM的理解？
- java8虚拟机和之前的变化更新？
- 什么是OOM,什么是栈溢出StackOverFlowError?怎么分析？
- JVM的常用调优参数有哪些？
- 内存快照如何抓取，怎么分析Dump文件？知道吗？
- 谈谈JVM中，类加载器你的认识？



### 1、JVM的位置

![image-20220505151711980](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505151711980.png)

### 2、JVM的体系结构

![image-20220505151758742](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505151758742.png)

### 3、类加载器

作用：加载Class文件~

类是模板，对象是具体的

![image-20220505153100804](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505153100804.png)

1. 虚拟机自带的加载器
2. 启动类（根）加载器
3. 扩展类加载器
4. 应用程序加载器

```java
package com.jvm;

public class Car {
    public int age;

    public static void main(String[] args) {
        //类是模板，对象是具体的

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        System.out.println(car1.hashCode());
        System.out.println(car2.hashCode());
        System.out.println(car3.hashCode());

        Class<? extends Car> aClass1 = car1.getClass();

        ClassLoader classLoader = aClass1.getClassLoader(); //AppClassLoader 应用程序加载器
        System.out.println(classLoader);
        System.out.println(classLoader.getParent()); //ExtClassLoader 扩展类加载器 \jre\lib\ext
        System.out.println(classLoader.getParent().getParent()); // null 不存在 或 java程序获取不到
    }
}
```

![img](https://gitee.com/linda12138/picgo/raw/master/images/2020121722082798.png)

### 4、双亲委派机制

```java
package java.lang;

public class String {
    //双亲委派机制：安全
    /**
     * 1.App-->EXC-->BOOT(最终执行)
     * 2.Boot
     * 3.EXC
     * 4.APP
     * */
    @Override
    public String toString(){
        return "hello";
    }

    public static void main(String[] args) {
        String s = new String();
        System.out.println(s.getClass().getClassLoader());
        s.toString();

        new Thread().start();
    }
    /***
     *1.类加载器收到类加载的请求 application
     * 2.将这个请求向上委托给父类加载器去完成，一直向上委托，知道启动类加载器
     * 3.启动加载器检查是否能够加载当前这个类，能加载就结束，使用当前的加载器，否则，抛出异常，通知自家在其进行加载
     * 重复步骤3
     * Class not Found ~
     *
     * null :java调用不到~ C、C++
     * java = C++:去掉繁琐的东西，指针，内存管理 C++--
     */
}
```

```java
package com.jvm;

public class Student {

    @Override
    public String toString() {
        return "hello";
    }

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.getClass().getClassLoader()); //AppClassLoader
        System.out.println(student.toString());
    }
}
```

![img](https://gitee.com/linda12138/picgo/raw/master/images/20201217213314510.png)



### 5、沙箱安全机制

​	Java安全模型的核心就是ava沙箱(sandbox),什么是沙箱？沙箱是一个限制程序运行的环境。沙箱机制就是将」va代码限定在虚拟机(UVM)特定的运行范围中，并且严格限制代码对本地系统资源访问，通过这样的措施来保证对代码的有效隔离，防止对本地系统造成破坏。沙箱**主要限制系统资源访问**，那系统资源包括什么？CPU、内存、文件系统、网络。不同级别的沙箱对这些资源访问的限制也可以不一样。

​	所有的java程序运行都可以指定沙箱，可以定制安全策略。在va中将执行程序分成本地代码和远程代码两种，本地代码默认视为可信任的，而远程代码则被看作是不受信的。对于授信的本地代码，可以访问一切本地资源。而对于非授信的远程代码在早期的va实现中，安全依赖于沙箱(Sandbox)机制。如下图所示JDK1.0安全模型

![image-20220505160645083](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505160645083.png)

​	但如此严格的安全机制也给程序的功能扩展带来障碍，比如当用户希望远程代码访问本地系统的文件时候，就无法实现。因此在后续的java1.1版本中，针对安全机制做了改进，增加了安全策略，允许用户指定代码对本地资源的访问权限。如下图所示JDK1.1安全模型

![image-20220505160911919](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505160911919.png)

​	在Java1.2版本中，再次改进了安全机制，增加了**代码签名**。不论本地代码或是远程代码，都会按照用户的安全策略设定，由类加载器加载到虚拟机中权限不同的运行空间，来实现差异化的代码执行权限控制。如下图所示JDK1.2安全模型

![image-20220505161009993](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505161009993.png)

​	当前最新的安全机制实现，则引入了域(Domain)的概念。虚拟机会把所有代码加载到不同的系统域和应用域，系统域部分专门负责与关键资源进行交互，而各个应用域部分则通过系统域的部分代理来对各种需要的资源进行访问。虚拟机中不同的受保护域(Protected Domain),对应不一样的权限(Permission)。存在于不同域中的类文件就具有了当前域的全部权限，如下图所示最新的安全模型(jdk1.6)

![121300](https://gitee.com/linda12138/picgo/raw/master/images/121300.png)

组成沙箱的基本组件：

- **字节码校验器**(bytecode verifier):确保ava类文件遵循java语言规范。这样可以帮助java程序实现内存保护。但并不是所有的类文件都会经过字节码校验，比如核心类。
- **类装载器**(class loader)：其中类装载器在3个方面对ava沙箱起作用。
  - 它防止恶意代码去干涉善意的代码； //双亲委派机制
  - 它守护了被信任的类库边界；
  - 它将代码归入保护域，确定了代码可以进行哪些操作。
  虚拟机为不同的类加载器载入的类提供不同的命名空间，命名空间由一系列唯一的名称组成，每一个被装载的类将有一个名字，这个命名空间是由java虚拟机为每一个类装载器维护的，它们互相之间甚至不可见。
  - 类装载器采用的机制是双亲委派模式。

1.从最内层M自带类加载器开始加载，外层恶意同名类得不到加载从而无法使用；

2.由于严格通过包来区分了访问域，外层恶意的类通过内置代码也无法获得权限访问到内层类，破坏代码就自然无法生效。

- **存取控制器**(access controller):存取控制器可以控制核心APl对操作系统的存取权限，而这个控制的策略设定，可以由用户指定。
- **安全管理器**(security manager)：是核心APl和操作系统之间的主要接口。实现权限控制，比存取控制器优先级高。
- **安全软件包**(security package):java.security下的类和扩展包下的类，允许用户为自己的应用增加新的安全特性，包括：
  - 安全提供者
  - 消息摘要
  - 数字签名 Keytools
  - 加密
  - 鉴别

### 6、Native

编写一个多线程类启动

```java
package com.jvm;

public class Demo {
    public static void main(String[] args) {
        new Thread(() ->{

        },"my thread name").start();
    }
    //native : 凡是带了native关键字的，说明java的作用范围达不到了，他会去调用c语言的库
    //会进入本地方法栈
    //调用本地方法本地接口 JNI java native interface
    //JNI作用：扩展java的使用，融合不同的编程语言为Java所用
    //java诞生的时候C C++ 横行，想要立足，必须要有调用C C++的程序
    //它在内存区域专门开辟了一块标记区域 ：Native Method Stack,等级Native方法
    //在最终执行的时候，加载本地方法库中的方法通过JNI

    //java程序驱动打印机，管理系统 Robot 掌握即可
    private native void s();

    //调用其他接口： Socket ,WebService~ http~

}

```

点进去看start方法的源码

```java
public synchronized void start() {
    if (threadStatus != 0)
        throw new IllegalThreadStateException();

    group.add(this);

    boolean started = false;
    try {
        start0();
        started = true;
    } finally {
        try {
            if (!started) {
                group.threadStartFailed(this);
            }
        } catch (Throwable ignore) {
        }
    }
}

private native void start0();
```

凡是带了native关键字的，说明java的作用范围达不到，去调用底层C语言的库！

**JNI:Java Native Interface (Java本地方法接口)**

凡是带了native:关键字的方法就会进入本地方法栈，其他的就是java栈！



**Native Interface** 本地接口

​	本地接口的作用是融合不同的编程语言为va所用，它的初衷是融合C/C++程序，Java在诞生的时候是C/C++横行的时候，想要立足，必须有调用C、C++的程序，于是就在内存中专门开辟了一块区域处理标记为native的代码，它的具体做法是在Native Method Stack中登记native方法，在(Execution Engine)执行引擎执行的时候加载Native Libraies。

​	目前该方法使用的越来越少了，除非是与硬件有关的应用，比如通过vā程序驱动打印机或者)ava系统管理生产设备，在企业级应用中已经比较少见。因为现在的异构领域间通信很发达，比如可以使用S0ckt通信，也可以使用Web Service等等，不多做介绍！



**Native Method Stack**

​	它的具体做法是Native Method Stack中登记native方法，在(Execution Engine)执行引擎执行的时候加载Native Libraies。【本地库】

### 7、PC寄存器

程序计数器：Program Counter Register

​	每个线程都有一个程序计数器，是线程私有的，就是一个指针，指向方法区中的方法字节码（用来存储指向像一条指令的地址，也即将要执行的指令代码)，在执行引擎读取下一条指令，是一个非常小的内存空间，几乎可以忽略不计

### 8、方法区

Method Area 方法区

​	方法区是被所有线程共享，所有字段和方法字节码，以及一些特殊方法，如构造函数，接口代码也在此定义，简单说，所有定义的方法的信息都保存在该区域，**此区域属于共享区间**：

​	==静态变量、常量、类信息（构造方法、接口定义）、运行时的常量池存在方法区中，但是实例变量存在堆内存中，和方法区无关==

 static final Class模板 常量池

### 9、栈

1.栈：数据结构

  程序 = 数据结构 + 算法

  栈：先进后出、后进先出：桶

  队列：先进先出（FIFO:first input first output）



  为什么main()先执行，最后结束~

  栈：栈内存，主管程序的运行，生命周期和线程同步；

  线程结束，栈内存也就释放了，对于栈来说，==不存在垃圾回收问题==

  一旦线程结束，栈就Over!

  栈：8大基本类型 + 对象引用 + 实例的方法

  

栈运行原理：栈帧

栈满了：StackOverflowError



栈+堆+方法区：交互关系

![image-20220505171701723](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505171701723.png)

**蓝色堆  粉色方法区 深蓝常量池 绿色栈**

**画出一个对象实例化的过程在内存中：**

### 10、三种jvm

- Sun公司 HotSpot Java HotSpot(TM) 64-Bit Server VM
- BEA `JRockit`
- IBM `J9 VM`



### 11、堆

Heap,一个JVM只有一个堆内存，堆内存的大小是可以调节的。

类加载器读取了类文件后，一般会把什么东西放到堆中？ 类，方法，常量，变量~，保存我们所有引用类型的真实对象

堆内存中还要细分为三个区域：

- 新生区（伊甸园区）Young/New
- 养老区 old
- 永久区 Perm

![image-20220505175028686](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505175028686.png)

GC垃圾回收，主要是在伊甸园区和养老区~

假设内存满了，OOM,堆内存不够！java.lang.OutOfMemoryError:java heap space

**在JDK8以后**，永久存储区有另外一个名字==元空间==

#### 11.1、新生区

- 类：诞生和成长的地方，甚至死亡；
- 伊甸园，所有的对象都是在伊甸园区new出来的
- 幸存者区（0，1）

​	老年区



![image-20220505195517658](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505195517658.png)

真理：经过研究，99%的对象都是临时对象！

#### 11.2、永久区

​	这个区域常驻内存。用来存放JDK自身携带的Class对象。Interface元数据，存储的是java运行时的一些环境或类信息~，这个区域不存在垃圾回收！关闭虚拟机就会释放这个区域的内存

​	一个启动类，加载了大量的第三方jar包。tomcat部署了太多的应用，大量动态生成的反射类。不断的被加载。知道内存满，就会出现OOM;



- jdk1.6之前：永久代，常量池是在方法区中
- jdk1.7:永久代，但是慢慢的退化了，`去永久代`，常量池在堆中
- jdk1.8之后：无永久代，常量池在元空间

![image-20220505200740393](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505200740393.png)

元空间：逻辑上存在：物理上不存在

```java
package com.jvm;

public class Demo02 {
    public static void main(String[] args) {
        //返回虚拟机试图使用的最大内存
        long maxMemory = Runtime.getRuntime().maxMemory(); //字节 1024*1024
        //返回jvm的总内存
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("max="+maxMemory+"字节\t"+(maxMemory/(double)1024/1024)+"MB");
        System.out.println("total="+totalMemory+"字节\t"+(totalMemory/(double)1024/1024)+"MB");

        //默认情况下：分配的总内存是电脑内存的1/4，初始化的内存1/64
        //OOM:尝试扩大堆内存空间看结果
        //        分析内存，看一下那个地方出现了什么问题

        //-Xms1024m -Xmx1024m -XX:+PrintGCDetails

        //305664K+699392K
    }
}
```

```java
package com.jvm;

import java.util.Random;

public class Demo03 {
    public static void main(String[] args) {
        String str = "akjbdjandakjkd";
        while (true){
            str+=str +new Random().nextInt(888888888)+new Random().nextInt(9999999);
        }
        //-Xms8m -Xmx8m -XX:+PrintGCDetails
    }
}
```

在一个项目中，突然出现了OOM故障，那么该如何排除~研究为什么出错

- 能够看到代码第几行出错：内存快照分析工具，MAT,jprofiler
- Dubug，一行行分析代码

**MAT,jprofiler作用：**

- 分析Dump内存文件，快速定位内存泄漏；
- 获得堆中的数据
- 获得大的对象~

```java
package com.jvm;

import java.util.ArrayList;

//-Xms:设置初始化内存分配大小 1/64
//-Xmx:设置最大分配内存，默认 1/4
//-XX:+PrintGCDetails //打印GC垃圾回收
//-XX:+HeapDumpOnOutOfMemoryError //oom Dump
//Dump
//-Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
public class Demo04 {
    byte[] array = new byte[1*1024*1024]; //1m

    public static void main(String[] args) {
        ArrayList<Demo04> list = new ArrayList<>();
        int count = 0;
        try {
            while (true){
                list.add(new Demo04());
                count=count+1;
            }
        }catch (Error e){
            System.out.println("count:"+count);
            e.printStackTrace();
        }
        //Throwable
        //Exception
        //Error
    }
}
```

### 12、GC:垃圾回收

![image-20220505211343074](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505211343074.png)

JVM在进行GC时，并不是对这三个区域统一回收。大部分时候，回收都是新生代

- 新生代
- 幸存区(from,to)
- 老年区

GC两种类型：轻GC(普通的GC),重GC(全局GC)



题目：

- JVM的内存模型和分区~详细到每个区放什么？

  `JVM 分为堆区和栈区，还有方法区，初始化的对象放在堆里面，引用放在栈里面，
  class 类信息常量池（static 常量和 static 变量）等放在方法区
  new:
  方法区：主要是存储类信息，常量池（static 常量和 static 变量），编译后的代码（字
  节码）等数据
   堆：初始化的对象，成员变量 （那种非 static 的变量），所有的对象实例和数组都要
  在堆上分配
   栈：栈的结构是栈帧组成的，调用一个方法就压入一帧，帧上面存储局部变量表，操
  作数栈，方法出口等信息，局部变量表存放的是 8 大基础类型加上一个应用类型，所
  以还是一个指向地址的指针
   本地方法栈：主要为 Native 方法服务
   程序计数器：记录当前线程执行的行号`

- 堆里面的分区有哪些？Eden，from,to,老年区，说说他们的特点

  `堆中的分区主要分为`

  ​	`新生区;它又分为幸存1区和幸存2区,伊甸园区`
  ​	`老年区`
  `首先新生区:大部分的对象都是在伊甸园区创建并回收的,回收后幸存的对象会被放到幸存区(to)区,幸存区1,2他俩是互相交换的,这其中牵涉到了复制算法,
  复制算法主要被应用在新生区中的幸存区,具体流程就是在幸存区中将活的对象进行复制到另一个幸存区中,留下来的对象就会被清理掉,不断地交换.这样做的缺点就是占用空间大,用到的空间只有开辟的1/2.`
  `复制算法主要是根据GCroot判断一个对象是否是垃圾.`

  `当幸存区中经过十五次GC还未被清除掉的对象会进入老年区.`

  `老年代一般存放的是比较大的对象和存活时间很久的对象因此老年代用标记整理算法,老年代容量满后会启用重GC`

- GC的算法有哪些？标记清除法，标记压缩，复制算法，引用计数器，怎么用？

- 轻GC和重GC分别在什么时候发生？

  **Minor GC**

  `此时如果新生的对象无法在 Eden 区创建（Eden 区无法容纳) 就会触发一次Young GC 此时会将 S0 区与Eden 区的对象一起进行可达性分析，找出活跃的对象，将它复制到 S1 区并且将S0区域和 Eden 区的对象给清空，这样那些不可达的对象进行清除，并且将S0 区 和 S1区交换。`

  `但是这里会产生一个问题，Q:为啥会有两个 Survivor 区？`

  `A: 因为假设设想一下只有一个 Survibor 区 那么就无法实现对于 S0 区的垃圾收集，以及分代年龄的提升。`

  **Major GC**

  `发生在老年代的GC ，基本上发生了一次Major GC 就会发生一次 Minor GC。并且Major GC 的速度往往会比 Minor GC 慢 10 倍。`

  **什么时候发生Major GC**

  `既然我们已经知道了 Minor GC 是在 Eden 区快满的情况下才会触发`

  `Q:那么 Major GC 呢?`
  `A:`

  `对于一个大对象，我们会首先在Eden 尝试创建，如果创建不了，就会触发Minor GC
  随后继续尝试在Eden区存放，发现仍然放不下
  尝试直接进入老年代，老年代也放不下
  触发 Major GC 清理老年代的空间
  放的下 成功
  放不下 OOM`

  

**引用计数法：**

![image-20220505212255430](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505212255430.png)



**复制算法：**

![image-20220505214116364](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505214116364.png)

![image-20220505214612199](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505214612199.png)

- 好处：没有内存的碎片
- 坏处：浪费了内存空间：多了一半空间永远是空的，假设对象100%存活（极端情况）

复制算法最佳使用场景：对象存活度较低的时候；新生区~



**标记清除算法**

![image-20220505215126197](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505215126197.png)

- 优点：不需要额外的空间！
- 缺点：两次扫描严重浪费时间，会产生内存碎片。



**标记压缩**

再优化：

![image-20220505215517982](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505215517982.png)

**总结**

内存效率：复制算法>标记清除算法>标记压缩算法（时间复杂度）

内存整齐度：复制算法=标记压缩算法>标记清除算法

内存利用率：标记压缩算法=标记清除算法>复制算法



GC:分代收集算法



年轻代：

- 存活率低
- 复制算法

老年代：

- 存活率高：区域大
- 标记清除算法（内存碎片不是太多）+标记压缩算法混合实现



### 13、JMM

1. 什么是JMM?

   JMM:（Java Memory Model的缩写）

2. 它干嘛的？：官方，其他人的博客，对应的视频！

   作用：缓存一致性协议，用于定义数据读写的规则（遵守，找到这个规则）

   JMM定义了线程工作内存和主内存之间的抽象关系：线程之间的共享变量存储在主内存(Main Memory)中，每个线程都有一个私有的本地内存(Local Memory)

   ![image-20220505221639572](https://gitee.com/linda12138/picgo/raw/master/images/image-20220505221639572.png)

   volilate:解决共享对象可见性这个问题

3. 它该如何学习？

   JMM:抽象的概念,理论

   JMM对这八种指令的使用，制定了如下规则：

   - 不允许read和load、store和write操作之一单独出现。即使用了read必须load,使用了store必须write

   - 不允许线程丢弃他最近的assigr操作，即工作变量的数据改变了之后，必须告知主存

   - 不允许一个线程将没有assign的数据从工作内存同步回主内存

   - 一个新的变量必须在主内存中诞生，不允许工作内存直接使用一个未被初始化的变量。就是怼变量实施use、store操作之前，必须经过assign和load操作

   - 一个变量同一时间只有一个线程能对其进行lock。多次ock后，必须执行相同次数的unlock.才能解锁·如果对一个变量进行引0ck操作，会清空所有工作内存中此变量的值，在执行引擎使用这个变量前，必须重新load或assign操作初始化变量的值

   - 如果一个变量没有被Iock,就不能对其进行unlock操作。也不能unlock-一个被其他线程锁住的变量

   - 对一个变量进行unlock操作之前，必须把此变量同步回主内存

     JMM对这八种操作规则和对volatile的一些特殊规则就能确定哪里操作是线程安全，哪些操作是线程不安全的了。但是这些规则实在复杂，很难在实践中直接分析。所以一般我们也不会通过上述规则进行分析。更多的时候，使用java的happen-before规侧则来进行分析。

