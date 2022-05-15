### 1、什么是JUC

------

**源码+官方文档**

![image-20220506092319261](https://gitee.com/linda12138/picgo/raw/master/images/image-20220506092319261.png)

java.util工具包、包、分类

**业务：普通的线程代码 Thread**

**Runnable** 没有返回值、效率相比于Callable相对较低

![image-20220506092752161](https://gitee.com/linda12138/picgo/raw/master/images/image-20220506092752161.png)

![image-20220506092835749](https://gitee.com/linda12138/picgo/raw/master/images/image-20220506092835749.png)

### 2、线程和进程

------

> 线程、进程，如果不能使用一句话说出来的技术，不扎实！

进程：一个程序，QQ.exe 程序的集合;

一个进程往往可以包含多个线程，至少包含一个！

java默认有几个线程？2个  main、GC

线程：开了一个进程Typora，写字，自动保存（线程负责的）

对于java而言：Thread、Runnable、Callable

**java真的可以开启线程吗？**开不了

```java
public synchronized void start() {
    /**
         * This method is not invoked for the main method thread or "system"
         * group threads created/set up by the VM. Any new functionality added
         * to this method in the future may have to also be added to the VM.
         *
         * A zero status value corresponds to state "NEW".
         */
    if (threadStatus != 0)
        throw new IllegalThreadStateException();

    /* Notify the group that this thread is about to be started
         * so that it can be added to the group's list of threads
         * and the group's unstarted count can be decremented. */
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
            /* do nothing. If start0 threw a Throwable then
                  it will be passed up the call stack */
        }
    }
}
//本地方法，底层的C++,java无法直接操作硬件
private native void start0();
```

> 并发、并行

并发编程：并发、并行

并发（多线程操作同一个资源）

- CPU一核，模拟出来多条线程，天下武功，唯快不破，快速交替

并行（多个人一起行走）

- CPU多核，多个线程同时执行。 线程池

```java
public class Test1 {
    public static void main(String[] args) {
        //获取cpu 的核数
		//CPU 密集型，IO密集型
       System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
```

并发编程的本质：**充分利用CPU的资源**



> 线程有几个状态 

```java
public enum State {
    
 	//新生
    NEW,

 	//运行
    RUNNABLE,
	
    //阻塞
    BLOCKED,

   //等待，死死的等
    WAITING,

   //超时等待
    TIMED_WAITING,

   //终止
    TERMINATED;
}
```



> wait/sleep 区别

**1、来自不同的类**

wait=>Object

sleep=>Thread

**2、关于锁的释放**

wait会释放锁，sleep睡觉了，抱着锁睡觉，不会释放

**3、使用的范围是不同的**

wait必须在同步代码块中

sleep可以在任何地方睡

|            | wait                                                         |                      sleep                      |
| ---------- | ------------------------------------------------------------ | :---------------------------------------------: |
| 同步       | 只能在同步上下文中调用wait方法，否则或抛出lllegalMonitorStateException |         不需要在同步方法或同步块中调用          |
| 作用对象   | wait方法定义在Object类中，作用于对象本身                     | sleep方法定义在java.long.Thread中作用于当前线程 |
| 释放锁资源 | 是                                                           |                       否                        |
| 唤醒条件   | 其他线程调用对象的notify()或者notifyAll()方法                |          超时或者调用interrupt()方法体          |
| 方法属性   | wait是实例方法                                               |                 sleep是静态方法                 |



### 3、Lock锁（重点）

> 传统Synchronized

```java
package com.peng;

/**
 * 基本的买票例子
 * 真正的多线程开发，公司中的开发 降低耦合性
 * 线程就是一个单独的资源类，没有任何附属的操作
 * 属性、方法
 * */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        //并发：多线程操作同一个资源类,把资源类丢入线程
        Ticket ticket = new Ticket();

        //@FunctionalInterface函数式接口  lambda表达式（参数）->{代码}
        new Thread(() ->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"A").start();
        new Thread(() ->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"B").start();
        new Thread(() ->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"C").start();

    }
}
//资源类 OOP
class Ticket{
    //属性，方法
    private int number = 30;

    //买票的方式
    //synchronized 本质 队列，锁
    public synchronized void sale(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票,剩余:"+number);
        }
    }
}
```



> Lock接口

![image-20220506103438777](https://gitee.com/linda12138/picgo/raw/master/images/image-20220506103438777.png)

![image-20220506103705721](https://gitee.com/linda12138/picgo/raw/master/images/image-20220506103705721.png)

![image-20220506104050820](https://gitee.com/linda12138/picgo/raw/master/images/image-20220506104050820.png)

公平锁：十分公平：可以先来后到

**非公平锁：十分不公平：可以插队（默认）**

```java
package com.peng;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo02 {
    public static void main(String[] args) {
        //并发：多线程操作同一个资源类,把资源类丢入线程
        Ticket ticket = new Ticket();

        //@FunctionalInterface函数式接口  lambda表达式（参数）->{代码}
        new Thread(() ->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"A").start();
        new Thread(() ->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"B").start();
        new Thread(() ->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"C").start();

    }
}

//lock
//三部曲 new ReentrantLock();
// lock.lock(); //加锁
//finally {lock.unlock(); //解锁}
class Ticket2{
    //属性，方法
    private int number = 30;

    Lock lock = new ReentrantLock();

    public  void sale(){
        lock.lock(); //加锁
        try{
            //业务代码
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票,剩余:"+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock(); //解锁
        }
    }
}
```



> Synchronized 和 Lock区别

1. Synchronized 内置java关键字，Lock是一个java类
2. Synchronized 无法判断获取锁的状态，Lock可以判断是否获取到了锁
3. Synchronized 会自动释放锁，lock必须要手动释放锁！如果不释放锁，**死锁！**
4. Synchronized 线程1（获得锁、阻塞）、线程2（等待，傻傻的等）；lock就不一定会等待下去
5. Synchronized 可重入锁，不可以中断的，非公平 lock,可重入锁，可以判断锁，非公平（可以自己设置）
6. Synchronized 适合锁少量的代码同步问题，Lock适合锁大量的同步代码



> 锁是什么？如何判断锁的是谁？





### 4、生产者和消费者问题

------

> 生产者和消费者问题Synchronized 版

```java
package com.peng.pc;

/**
 *
 * 线程之间的通信问题：生产者和消费者问题 等待唤醒，通知唤醒
 * 线程交替执行 A  B  操作同一个变量 num = 0
 * A num+1
 * B num-1
 * */
public class A {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }
}

// 判断等待，业务 通知
class Data{ //数字  资源类

    private int number = 0;

    //+1
    public synchronized void increment() throws InterruptedException {
        if (number!=0){ //0
            //等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程，我+1完了
        this.notifyAll();
    }
    //-1
    public synchronized void decrement() throws InterruptedException {
        if (number==0){ //1
            //等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程，我-1完了
        this.notifyAll();
    }
}

```

> 问题存在，A,B,,C,D 四个线程 虚假唤醒

![image-20220506140610140](https://gitee.com/linda12138/picgo/raw/master/images/image-20220506140610140.png)

**if改为while判断**

```java
package com.peng.pc;

/**
 *
 * 线程之间的通信问题：生产者和消费者问题 等待唤醒，通知唤醒
 * 线程交替执行 A  B  操作同一个变量 num = 0
 * A num+1
 * B num-1
 * */
public class A {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

// 判断等待，业务 通知
class Data{ //数字  资源类

    private int number = 0;

    //+1
    public synchronized void increment() throws InterruptedException {
        while (number!=0){ //0
            //等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程，我+1完了
        this.notifyAll();
    }
    //-1
    public synchronized void decrement() throws InterruptedException {
        while (number==0){ //1
            //等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程，我-1完了
        this.notifyAll();
    }
}
```



> JUC版的生产者和消费者问题

通过lock找到Condition

![image-20220506141418596](https://gitee.com/linda12138/picgo/raw/master/images/image-20220506141418596.png)

![image-20220506141245479](https://gitee.com/linda12138/picgo/raw/master/images/image-20220506141245479.png)



代码实现：

```java
package com.peng.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 线程之间的通信问题：生产者和消费者问题 等待唤醒，通知唤醒
 * 线程交替执行 A  B  操作同一个变量 num = 0
 * A num+1
 * B num-1
 * */
public class B {
    public static void main(String[] args) {
        Data2 data = new Data2();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

// 判断等待，业务 通知
class Data2{ //数字  资源类

    private int number = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    //        condition.await(); //等待
    //        condition.signalAll(); //唤醒全部
    //+1
    public  void increment() throws InterruptedException {

        lock.lock();
        try {
            //业务代码
            while (number!=0){ //0
                //等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //通知其他线程，我+1完了
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    //-1
    public  void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number==0){ //1
                //等待
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //通知其他线程，我-1完了
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
```

任何一个新技术，绝对不是仅仅只是覆盖了原来的技术，优势和补充！

> Condition 精准的通知和唤醒线程

![image-20220506142521115](https://gitee.com/linda12138/picgo/raw/master/images/image-20220506142521115.png)

代码测试：

```java
package com.peng.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * A执行完调用B,B执行完调用C,C调用完调用A
 * */
public class C {
    public static void main(String[] args) {
        Data3 data = new Data3();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        },"C").start();
    }
}

// 判断等待，业务 通知
class Data3{ //数字  资源类

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1; //1A 2B 3C

    public void printA(){
        lock.lock();
        try {
            //业务，判断-》执行->通知
            while (number!=1){
                //等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>AAA");
            //唤醒指定的人，B
            number = 2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            //业务，判断-》执行->通知
            while (number!=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>BBBB");
            //唤醒指定的人，C
            number=3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            //业务，判断-》执行->通知
            while (number!=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>CCC");
            number=1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    //生产线：下单->支付->交易->物理
}
```



### 5、8锁现象

------

如何判断锁的是谁！永远的知道什么锁，锁到底锁的是谁？

**深刻理解我们的锁**

```java
package com.peng.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，就是关于锁的8个问题
 * 1、标准情况下，两个线程先打印 发短信还是打电话？1发短信 2打电话  发短信
 *
 * */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        //phone.sendSms(); 是先调用的（锁的问题）
        //锁的存在
        new Thread(()->{
            phone.sendSms();
        },"A").start();
        //捕获
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone.call();
        },"B").start();
    }
}

class Phone{
    //synchronized 锁的对象是方法的调用者
    //两个方法用的是同一个锁，谁先拿到谁执行
    public synchronized void sendSms(){

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call(){
        System.out.println("打电话");
    }
}
```

```java
package com.peng.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 3、添加了一个普通方法，发短信还是hello？ 普通方法
 * 4、两个对象。两个同步方法 发短信还是打电话？ 打电话
 * */
public class Test2 {
    public static void main(String[] args) {
        //两个对象，两个调用者 两把锁
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        //phone.sendSms(); 是先调用的（锁的问题）
        //锁的存在
        new Thread(()->{
            phone1.sendSms();
        },"A").start();
        //捕获
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.call();
        },"B").start();
    }
}

class Phone2{
    //synchronized 锁的对象是方法的调用者
    //两个方法用的是同一个锁，谁先拿到谁执行
    public synchronized void sendSms(){

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call(){
        System.out.println("打电话");
    }

    //这里没有锁，不是同步方法，不受锁的影响
    public void hello(){
        System.out.println("hello");
    }
}
```

```java
package com.peng.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 5、增加两个静态的同步方法,只有一个对象，先打印发短信还是打电话？ 发短信
 * 6、两个对象 增加两个静态的同步方法,只有一个对象，先打印发短信还是打电话？ 发短信
 * */
public class Test3 {
    public static void main(String[] args) {
        //两个对象的Class类的模板只有一个，static,锁的是Class
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

        //phone.sendSms(); 是先调用的（锁的问题）
        //锁的存在
        new Thread(()->{
            phone1.sendSms();
        },"A").start();
        //捕获
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.call();
        },"B").start();
    }
}
 //Phone3 唯一的一个 Class对象

class Phone3{
    //synchronized 锁的对象是方法的调用者
    //两个方法用的是同一个锁，谁先拿到谁执行
    //static 静态方法
    //类一加载就有了
    public static synchronized void sendSms(){

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void call(){
        System.out.println("打电话");
    }
}
```

```java
package com.peng.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 一个静态同步方法，一个普通的同步方法，先打印打电话还是发短信？ 打电话
 * 2.两个对象  一个静态同步方法，一个普通的同步方法，先打印打电话还是发短信？  打电话
 * */
public class Test4 {
    public static void main(String[] args) {
        //两个对象的Class类的模板只有一个，static,锁的是Class
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
        //phone.sendSms(); 是先调用的（锁的问题）
        //锁的存在
        new Thread(()->{
            phone1.sendSms();
        },"A").start();
        //捕获
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.call();
        },"B").start();
    }
}

class Phone4{
    //静态同步方法 锁的是class模板
    public static synchronized void sendSms(){

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    //普通同步方法 锁的调用者
    public  synchronized void call(){
        System.out.println("打电话");
    }
}
```

> 小结

new this具体的一个手机

static Class 唯一的一个模板



### 6、集合类不安全

------

> List不安全

```java
package com.peng.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

//java.util.ConcurrentModificationException  并发修改异常
public class ListTest {
    public static void main(String[] args) {
        //并发下 ArrayList 是不安全的 Synchronized
        /**
         * 解决方案
         * 1、List<String> list = new Vector<>();
         * 2、List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3、List<String> list = new CopyOnWriteArrayList<>();
         * */
        //        List<String> list = new Vector<>();
        //CopyOnWrite 写入时复制  COW 计算机程序设计领域的一种优化策略
        //多个线程调用的时候，list 读取的时候，固定的 ，写入（覆盖）
        //在写入的时候避免覆盖，造成数据问题
        //读写分离  MyCat
        //CopyOnWriteArrayList（用lock锁） 比 Vector（用Synchronized） 好在哪里？
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
```



> Set 不安全

```java
package com.peng.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 同理可证
 * java.util.ConcurrentModificationException
 * */
public class SetTest {
    public static void main(String[] args) {
        /**
         * 解决方案
         * 1. Set<String> set = Collections.synchronizedSet(new HashSet<>());
         * 2.Set<String> set = new CopyOnWriteArraySet<>();
         * */
        //        Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i < 10; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
```

hashSet底层是什么？

```java
public HashSet() {
    map = new HashMap<>();
}
//add set 本质就是 map key是无法重复的
public boolean add(E e) {
    return map.put(e, PRESENT)==null;
}

private static final Object PRESENT = new Object(); //不变的值
```



> Map不安全

map基本操作

![image-20220506204136914](https://gitee.com/linda12138/picgo/raw/master/images/image-20220506204136914.png)

```java
package com.peng.unsafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

//java.util.ConcurrentModificationException
public class MapTest {
    public static void main(String[] args) {
        //map 是这样用的吗？ 不是，工作中不用HashMap
        // 默认等价于什么？ new HashMap<>(16,0.75);
        //Map<Object, Object> map = Collections.synchronizedMap();
        //ConcurrentHashMap原理
        Map<String,String> map = new ConcurrentHashMap<>();

        for (int i = 1; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
        //加载因子、初始化容量
    }
}
```



### 7、Callable(简单)

------

1、可以有返回值

2、可以抛出异常

3、方法不同，run()/call()

> 代码测试

![image-20220507151719707](https://gitee.com/linda12138/picgo/raw/master/images/image-20220507151719707.png)

![image-20220507151653462](https://gitee.com/linda12138/picgo/raw/master/images/image-20220507151653462.png)



![image-20220507151829924](https://gitee.com/linda12138/picgo/raw/master/images/image-20220507151829924.png)

```java
package com.peng.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 探究原理
 * */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //        new Thread(new Runnable()).start();
        //        new Thread(new FutureTask()<V>).start();
        //        new Thread(new FutureTask( Callable )<V>).start();



        MyThread thread = new MyThread();
        FutureTask futureTask = new FutureTask(thread);
        //适配类
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();  //结果会被缓存 提高效率

        String o = (String) futureTask.get(); //获取Callable 的返回值 get方法可能会产生阻塞  把它放到最后一行 或者使用异步通信来处理
        System.out.println(o);
    }
}
class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("call()"); //会打印几个call
        //耗时的操作
        return "123";
    }
}
```

```java
new Thread(new FutureTask(new MyThread())).start();
```

细节：

1. 有缓存
2. 结果可能需要等待，会阻塞！



### 8、常用的辅助类(必会)

------

#### 8.1、CountDownLatch

![image-20220507153144403](https://gitee.com/linda12138/picgo/raw/master/images/image-20220507153144403.png)

**减法计数器**

```java
package com.peng.add;

import java.util.concurrent.CountDownLatch;

//计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //总数是6,必须要执行任务的时候，再使用
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"go out");
                countDownLatch.countDown(); //-1
            },String.valueOf(i)).start();
        }

        countDownLatch.await(); //等待计数器归零，然后再向下执行

        System.out.println("Close Door");
    }
}
```

原理：

`countDownLatch.countDown();` //-1

`countDownLatch.await();`//等待计数器归零，然后再向下执行

每次有线程调用countDown()数量-1，假设计数器变为0 countDownLatch.await();就会被唤醒，继续执行

#### 8.2、CyclicBarrier

![image-20220507154059915](https://gitee.com/linda12138/picgo/raw/master/images/image-20220507154059915.png)

**加法计数器**

```java
package com.peng.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /**
         * 集齐7颗龙珠召唤神龙
         * */
        //召唤龙珠的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙成功！");
        });
        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            //lambda能操作到i吗
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集了"+temp+"颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
```

#### 8.3、Semaphore

Semaphore:信号量

![image-20220507154741949](https://gitee.com/linda12138/picgo/raw/master/images/image-20220507154741949.png)

抢车位！

6车 -- 3个停车位

```java
package com.peng.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        //线程数量：停车位 限流
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                //acquire()得到
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //release()释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}

```

**原理：**

`semaphore.acquire();`获得，假设已经满了，等到被释放为止

`semaphore.release();`释放，会将当前的信号量释放+1，然后唤醒等待的线程

作用：多个共享资源互斥的使用！并发限流，控制最大线程数



### 9、读写锁

------

ReadWriteLock

![image-20220507155821494](https://gitee.com/linda12138/picgo/raw/master/images/image-20220507155821494.png)

```java
package com.peng.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁（写锁） 一次只能被一个线程独有
 * 共享锁（读锁） 多个线程可以同时占有
 * ReadWriteLock
 * 读-读  可以共存
 * 读-写  不能共存
 * 写-写  不能共存
 * */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        MyCacheLock myCacheLock = new MyCacheLock();
        //写入
        for (int i = 1; i <= 5; i++) {
            final int temp =i;
            new Thread(()->{
                myCacheLock.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }

        //读取
        for (int i = 1; i <= 5; i++) {
            final int temp =i;
            new Thread(()->{
                myCacheLock.get(temp+"");
            },String.valueOf(i)).start();
        }

    }
}

//加锁
class MyCacheLock{

    private volatile Map<String,Object> map = new HashMap<>();
    //读写锁：更加细力度的控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //存 写 ，写入的时候，只希望同时只有一个线程写
    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"写入OK");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    //取 读  所有人都可以去读
    public void get(String key){
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取OK");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}

/**
 * 自定义缓存
 * */
class MyCache{

    private volatile Map<String,Object> map = new HashMap<>();

    //存 写
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName()+"写入OK");
    }

    //取 读
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取OK");
    }
}
```



### 10、阻塞队列

------

![image-20220507162030343](https://gitee.com/linda12138/picgo/raw/master/images/image-20220507162030343.png)

**阻塞队列：**

![image-20220507162224567.png](https://gitee.com/linda12138/picgo/raw/master/images/image-20220507162224567.png)



![image-20220507163235811](https://gitee.com/linda12138/picgo/raw/master/images/image-20220507163235811.png)

**BlockingQueue** ：BlockingQueue 不是新的东西

![image-20220507163508258](https://gitee.com/linda12138/picgo/raw/master/images/image-20220507163508258.png)

什么情况下我们会使用阻塞队列：多线程并发处理



**学会使用队列**

添加、移除

**四组API**

| 方式         | 抛出异常 | 不抛出异常，有返回值 | 阻塞等待 | 超时等待  |
| ------------ | -------- | -------------------- | -------- | --------- |
| 添加         | add      | offer                | put      | offer(,,) |
| 移除         | remove   | poll                 | take     | poll(,)   |
| 判断队列首部 | element  | peek                 | -        | -         |

```java
/**
     * 抛出异常
     * */
public static void test1(){
    //队列的大小
    ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
    System.out.println(arrayBlockingQueue.add("a"));
    System.out.println(arrayBlockingQueue.add("b"));
    System.out.println(arrayBlockingQueue.add("c"));
    //java.lang.IllegalStateException: Queue full  抛出异常
    //        System.out.println(arrayBlockingQueue.add("d"));
    System.out.println(arrayBlockingQueue.element()); //查看队首元素是谁
    System.out.println("=======================");
    System.out.println(arrayBlockingQueue.remove());
    System.out.println(arrayBlockingQueue.remove());
    System.out.println(arrayBlockingQueue.remove());
    //java.util.NoSuchElementException 抛出异常
    //        System.out.println(arrayBlockingQueue.remove());
}
```

```java
/**
     * 不抛出异常 有返回值
     * */
public static void test2(){
    //队列的大小
    ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
    System.out.println(arrayBlockingQueue.offer("a"));
    System.out.println(arrayBlockingQueue.offer("b"));
    System.out.println(arrayBlockingQueue.offer("c"));
    System.out.println(arrayBlockingQueue.offer("d")); //false 不抛出异常
    System.out.println(arrayBlockingQueue.peek()); //检查队首元素
    System.out.println("==============");
    System.out.println(arrayBlockingQueue.poll());
    System.out.println(arrayBlockingQueue.poll());
    System.out.println(arrayBlockingQueue.poll());
    System.out.println(arrayBlockingQueue.poll()); //null 不抛出异常
}
```

```java
/**
     * 阻塞 等待 （一直阻塞）
     * */
public static void test3() throws InterruptedException {
    ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
    //一直阻塞
    blockingQueue.put("a");
    blockingQueue.put("b");
    blockingQueue.put("c");
    //        blockingQueue.put("d"); //队列没有位置了，一直等待

    System.out.println(blockingQueue.take());
    System.out.println(blockingQueue.take());
    System.out.println(blockingQueue.take());
    System.out.println(blockingQueue.take()); //没有这个元素 一直阻塞
}
```

```java
/**
     * 等待超时
     * */
public static void test4() throws InterruptedException {
    ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
    blockingQueue.offer("a");
    blockingQueue.offer("c");
    blockingQueue.offer("b");
    blockingQueue.offer("d",2, TimeUnit.SECONDS); //超时退出 等待超过两秒就退出

    System.out.println("=================");
    System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
}
```



> SynchronousQueue 同步队列

没有容量

进去一个元素，必须等待取出来，才能再往里面放一个元素

put、take

```java
package com.peng.bq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

//同步队列
//和其他的BlockingQueue 不一样，SynchronousQueue 不存储元素
//put了一个元素，必须从里面先take取出来，否则不能在put进去值
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<String>();//同步队列

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"="+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"="+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"="+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();
    }
}
```



### 11、线程池(重点)

------

线程池：三大方法、7大参数、4种拒绝策略

> 池化技术

程序的运行，本质：占用系统的资源！优化Cpu资源的使用=>池化技术

线程池、连接池、内存池、对象池...  创建、销毁。十分浪费资源

池化技术：事先准备好一些资源，有人要有，就来我这里拿，用完之后还给我

**线程池的好处：**

1. 降低资源的消耗
2. 提高响应的速度
3. 方便管理

`线程复用、可以控制最大并发数、管理线程`



> 线程池：三大方法

![image-20220508093616927](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508093616927.png)

```java
package com.peng.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//Executors 工具类 三大方法
public class Demo1 {
    public static void main(String[] args) {
        //        ExecutorService threadExecutor = Executors.newSingleThreadExecutor();//单个线程
        //        ExecutorService threadPool = Executors.newCachedThreadPool();//可伸缩的
        ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定的线程池的大小
        try {
            for (int i = 0; i < 10; i++) {
                //使用了线程池之后，要使用线程池来创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
    }
}
```



> 7大参数

源码分析

```java
public static ExecutorService newSingleThreadExecutor() {
    return new FinalizableDelegatedExecutorService
        (new ThreadPoolExecutor(1, 1,
                                0L, TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<Runnable>()));
}

public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                  0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>());
}

public static ExecutorService newCachedThreadPool() {
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE, //21亿 OOM
                                  60L, TimeUnit.SECONDS,
                                  new SynchronousQueue<Runnable>());
}
//本质ThreadPoolExecutor（）

public ThreadPoolExecutor(int corePoolSize, //核心线程池大小
                          int maximumPoolSize, //最大核心线程池大小
                          long keepAliveTime, //超时了没有人调用就会释放
                          TimeUnit unit,//超时单位
                          BlockingQueue<Runnable> workQueue,//阻塞队列
                          ThreadFactory threadFactory,//线程工厂，创建线程的，一般不用动
                          RejectedExecutionHandler handler //拒绝策略) {
    if (corePoolSize < 0 ||
        maximumPoolSize <= 0 ||
        maximumPoolSize < corePoolSize ||
        keepAliveTime < 0)
        throw new IllegalArgumentException();
    if (workQueue == null || threadFactory == null || handler == null)
        throw new NullPointerException();
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.workQueue = workQueue;
    this.keepAliveTime = unit.toNanos(keepAliveTime);
    this.threadFactory = threadFactory;
    this.handler = handler;
}

```

![image-20220508095840397](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508095840397.png)

> 手动创建一个线程池

```java
package com.peng.pool;

import java.util.concurrent.*;

//Executors 工具类 三大方法
//new ThreadPoolExecutor.AbortPolicy() //银行满了，还有人进来，不处理这个人的，抛出异常
// new ThreadPoolExecutor.CallerRunsPolicy(); //哪来的去哪里！
//     new ThreadPoolExecutor.DiscardPolicy() //队列满了，不会抛出异常，丢掉任务，不会抛出异常
//new ThreadPoolExecutor.DiscardOldestPolicy() //队列满了，尝试和最早的竞争 也不会抛出异常
public class Demo1 {
    public static void main(String[] args) {
        //自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                                                            5,
                                                            3,
                                                            TimeUnit.SECONDS,
                                                            new LinkedBlockingQueue<>(3),
                                                            Executors.defaultThreadFactory(),
                                                            new ThreadPoolExecutor.DiscardOldestPolicy()); //队列满了，尝试和最早的竞争 也不会抛出异常
        try {
            //最大承载：DEque+max
            //超过RejectedExecutionException
            for (int i = 1; i <= 9; i++) {
                //使用了线程池之后，要使用线程池来创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
    }
}
```



> 四种拒绝策略

![image-20220508100254936](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508100254936.png)

```java
//new ThreadPoolExecutor.AbortPolicy() //银行满了，还有人进来，不处理这个人的，抛出异常
// new ThreadPoolExecutor.CallerRunsPolicy(); //哪来的去哪里！
//     new ThreadPoolExecutor.DiscardPolicy() //队列满了，不会抛出异常，丢掉任务，不会抛出异常
//new ThreadPoolExecutor.DiscardOldestPolicy() //队列满了，尝试和最早的竞争 也不会抛出异常
```

> 小结和拓展

池的最大的线程如何去设置

了解：IO密集型  Cpu密集型（调优）

```java
package com.peng.pool;

import java.util.concurrent.*;

public class Demo1 {
    public static void main(String[] args) {
        //自定义线程池

        //最大线程到底该如何定义
        //cpu 密集型 几核就定义为几，保证cpu的效率最高
        //io 密集型 >判断你的程序中十分耗io的线程
        //   程序 15个大型任务 io十分占用资源
        //获取cpu的核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                                                            Runtime.getRuntime().availableProcessors(),
                                                            3,
                                                            TimeUnit.SECONDS,
                                                            new LinkedBlockingQueue<>(3),
                                                            Executors.defaultThreadFactory(),
                                                            new ThreadPoolExecutor.DiscardOldestPolicy()); //队列满了，尝试和最早的竞争 也不会抛出异常
        try {
            //最大承载：DEque+max
            //超过RejectedExecutionException
            for (int i = 1; i <= 9; i++) {
                //使用了线程池之后，要使用线程池来创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
    }
}

```



### 12、四大函数式接口(必须掌握)

------

新时代的程序员：lambda表达式、链式编程、函数式接口、Stream流式计算



> 函数式接口：只有一个方法的接口

```java
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
//泛型、枚举、反射
//lambda表达式、链式编程、函数式接口、Stream流式计算
//超级多FunctionalInterface
//简化编程模型，在新版本的框架底层实现
//forEach(消费者类的函数式接口)
```

![image-20220508102841113](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508102841113.png)

**代码测试：**

> Function函数式接口

![image-20220508103534167](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508103534167.png)

```java
package com.peng.function;

import java.util.function.Function;

/**
 * Function 函数型接口,有一个输入参数，有一个输出
 * 只要是函数式接口 就可以用lambda表达式简化
 * */
public class Demo01 {
    public static void main(String[] args) {
        //工具类：输出输入的值
        //        Function function = new Function<String,String>() {
        //            @Override
        //            public String apply(String str) {
        //                return str;
        //            }
        //        };
        Function function = (str)->{
            return str;
        };
        System.out.println(function.apply("ada"));
    }
}
```



> 断定型接口：有一个输入参数，返回值只能是布尔值

![image-20220508104054126](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508104054126.png)

```java
package com.peng.function;

import java.util.function.Predicate;
//断定型接口：有一个输入参数，返回值只能是布尔值
public class Demo02 {
    public static void main(String[] args) {
        //判断字符串是否为空
        //        Predicate<String> predicate = new Predicate<String>() {
        //            @Override
        //            public boolean test(String o) {
        //                return o.isEmpty();
        //            }
        //        };
        Predicate<String> predicate =(o)->{
            return o.isEmpty();
        };
        System.out.println(predicate.test("hhh"));
    }
}
```



> Consumer 消费型接口

![image-20220508104732434](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508104732434.png)

```java
package com.peng.function;

import java.util.function.Consumer;
/**
 * Consumer 消费型接口：只有输入没有返回值
 * */
public class Demo03 {
    public static void main(String[] args) {
        //        Consumer<String> consumer = new Consumer<String>() {
        //
        //            @Override
        //            public void accept(String s) {
        //                System.out.println(s);
        //            }
        //        };
        Consumer<String> consumer =(s)->{
            System.out.println(s);
        };
        consumer.accept("peng");
    }
}
```

> Supplier 供给型接口

![image-20220508105149921](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508105149921.png)

```java
package com.peng.function;

import java.util.function.Supplier;
/**
 * Supplier 供给型接口 没有参数只有返回值
 * */
public class Demo04 {
    public static void main(String[] args) {
        //        Supplier supplier = new Supplier<Integer>() {
        //            @Override
        //            public Integer get() {
        //                System.out.println("get()");
        //                return 1024;
        //            }
        //        };
        Supplier supplier =()->{
            return 1024;
        };
        System.out.println(supplier.get());
    }
}
```



### 13、Stream流式计算

------

> 什么是Stream流式计算

大数据：存储+计算

存储：集合、Mysql本质就是存储东西的

计算都应该交给流来操作！

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String name;
    private int age;
}
```

```java
package com.peng.stream;

import java.util.Arrays;
import java.util.List;

/**
 * 题目要求：一分钟内完成此题，只能用一行代码实现
 * 现在有五个用户！筛选
 * Id必须是偶数
 * 年龄必须大于23岁
 * 用户名转为大写字母
 * 用户名字母倒着排序
 * 只输入一个用户
 * */
public class Test {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(6, "e", 25);
        //集合就是存储
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        //计算交给Stream流
        //链式编程
        list.stream()
            .filter((u)->{return u.getId()%2==0;})
            .filter((u)->{return u.getAge()>23;})
            .map((u)->{return u.getName().toUpperCase();})
            .sorted((u,uu)->{return uu.compareTo(u);})
            .limit(1)
            .forEach(System.out::println);
    }
}
```



### 14、ForkJoin

------

> 什么是ForkJoin

ForkJoin在JDK1.7，并行执行任务！提高效率。大数据量！

大数据：Map Reduce(把大任务派分成小任务)

![image-20220508152839187](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508152839187.png)



> ForkJoin特点：工作窃取

这个里面维护的都是双端队列

![image-20220508153047112](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508153047112.png)



> ForkJoin

![image-20220508154209658](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508154209658.png)

```java
package com.peng.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算的例子
 * 3000   6000(ForkJoin)   9000(Stream并行流)
 * 如何使用ForkJoin
 * 1、ForkJoinPool 通过它来执行
 *  forkjoin.execute(ForkJoinTask<?> task)
 *  计算类要继承ForkJoinTask
 * */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private Long start; //1
    private Long end; //1990900000

    //临界值
    private Long temp=10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    //计算方法
    @Override
    protected Long compute() {
        if ((end-start)<temp){
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum+=i;
            }
            return sum;
        }else {
            //分支合并计算  递归
            long middle = (start + end) / 2; //中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork(); //拆分任务，把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle+1, end);
            task2.fork(); //拆分任务，把任务压入线程队列

            return task1.join()+task2.join();
        }
    }
}
```

测试：

```java
package com.peng.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

// 3000 6000 9000
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //        test1(); //9230
        //        test2(); //8988
        test3(); //1186
    }
    //普通程序员
    public static void test1(){
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 1L; i <10_0000_1000 ; i++) {
            sum+=i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum="+"时间:"+(end-start));
    }

    //会使用ForkJoin
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);//提交任务
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum="+"时间:"+(end-start));
    }

    public static void test3(){
        long start = System.currentTimeMillis();
        //Stream并行流()  (]
        long sum = LongStream.rangeClosed(0L, 10_0000_0000).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum="+"时间:"+(end-start));
    }
}
```



### 15、异步回调

------

> Future设计的初衷：对将来的某个事件的结果进行建模

![image-20220508160832462](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508160832462.png)

```java
package com.peng.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用：CompletableFuture
 * 成功回调
 * 失败回调
 * */
public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //发起一个请求 void
        //没有返回值的runAsync 异步回调
        //        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
        //            try {
        //                TimeUnit.SECONDS.sleep(2);
        //            } catch (InterruptedException e) {
        //                e.printStackTrace();
        //            }
        //            System.out.println(Thread.currentThread().getName()+"runAsync");
        //        });
        //        System.out.println("111");
        //        completableFuture.get(); //获取阻塞执行结果

        //有返回值的supplyAsync 异步回调
        //ajax 成功和失败的回调
        //返回的是错误信息
        CompletableFuture<Integer> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+"supplyAsync=>Integer");
            int i = 10/0;
            return 1024;
        });
        System.out.println(uCompletableFuture.whenComplete((t, u) -> {
            System.out.println("t=>" + t); //正常的返回结果
            System.out.println("u=>" + u); //错误信息 java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233; //可以获取到错误的返回结果
        }).get());

    }
}
```



### 16、JMM

------

> 请你谈谈你对Volatile的理解

Volatile是java虚拟机提供**轻量级的同步机制**

1. 保证可见性
2. `不保证原子性`
3. 禁止指令重排



> 什么是JMM

JMM java内存模型，不存在的东西，概念！约定！

**关于JMM的一些同步的约定：**

1. 线程解锁前，必须把共享变量==立刻==刷回主存
2. 线程加锁前：必须读取主存中的最新值到工作内存中
3. 加锁和解锁是同一把锁



线程 **工作内存**、**主内存**

**8种操作：**

![img](https://gitee.com/linda12138/picgo/raw/master/images/8cedf683cdfacb3cfcd970cd739d5b9d.png)

　**内存交互操作有8种，虚拟机实现必须保证每一个操作都是原子的，不可在分的（对于double和long类型的变量来说，load、store、read和write操作在某些平台上允许例外）**

- - lock   （锁定）：作用于主内存的变量，把一个变量标识为线程独占状态
  - unlock （解锁）：作用于主内存的变量，它把一个处于锁定状态的变量释放出来，释放后的变量才可以被其他线程锁定
  - read  （读取）：作用于主内存变量，它把一个变量的值从主内存传输到线程的工作内存中，以便随后的load动作使用
  - load   （载入）：作用于工作内存的变量，它把read操作从主存中变量放入工作内存中
  - use   （使用）：作用于工作内存中的变量，它把工作内存中的变量传输给执行引擎，每当虚拟机遇到一个需要使用到变量的值，就会使用到这个指令
  - assign （赋值）：作用于工作内存中的变量，它把一个从执行引擎中接受到的值放入工作内存的变量副本中
  - store  （存储）：作用于主内存中的变量，它把一个从工作内存中一个变量的值传送到主内存中，以便后续的write使用
  - write 　（写入）：作用于主内存中的变量，它把store操作从工作内存中得到的变量的值放入主内存的变量中

　　**JMM对这八种指令的使用，制定了如下规则：**

- - 不允许read和load、store和write操作之一单独出现。即使用了read必须load，使用了store必须write
  - 不允许线程丢弃他最近的assign操作，即工作变量的数据改变了之后，必须告知主存
  - 不允许一个线程将没有assign的数据从工作内存同步回主内存
  - 一个新的变量必须在主内存中诞生，不允许工作内存直接使用一个未被初始化的变量。就是怼变量实施use、store操作之前，必须经过assign和load操作
  - 一个变量同一时间只有一个线程能对其进行lock。多次lock后，必须执行相同次数的unlock才能解锁
  - 如果对一个变量进行lock操作，会清空所有工作内存中此变量的值，在执行引擎使用这个变量前，必须重新load或assign操作初始化变量的值
  - 如果一个变量没有被lock，就不能对其进行unlock操作。也不能unlock一个被其他线程锁住的变量
  - 对一个变量进行unlock操作之前，必须把此变量同步回主内存



问题：程序不知道主内存的值已经被修改了



### 17、Volatile

------

> 保证可见性

```java
package com.peng.tvolatile;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
    //不加volatile 程序就会死循环
    //加volatile可以保证可见性
    private volatile static int num = 0;
    public static void main(String[] args) { //mian

        new Thread(()->{ //线程1 对主内存的变化是不知道的
            while (num==0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println(num);
    }
}
```



> 不保证原子性

原子性：不可分割

线程A在执行任务的时候，是不能被打扰的，也不能被分割，要么同时成功，要么同时失败。

```java
package com.peng.tvolatile;

//不保证原子性
public class VDemo {
    //volatile 不保证原子性
    private volatile static int num = 0;

    public  static void add(){
        num++;
    }
    public static void main(String[] args) {

        //理论上num结果为2万
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount()>2){
            //main  GC
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+num);
    }
}
```

**如果不加lock和synchronized,怎么保证原子性**

![image-20220508173509611](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508173509611.png)

使用原子类，解决原子性问题

![image-20220508173634209](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508173634209.png)



```java
package com.peng.tvolatile;

import java.util.concurrent.atomic.AtomicInteger;

//不保证原子性
public class VDemo {
    //volatile 不保证原子性
    //原子类的Integer
    private volatile static AtomicInteger num = new AtomicInteger();

    public  static void add(){
        //        num++;  //不是一个原子性操作
        num.getAndIncrement(); //AtomicInteger +1方法 CAS
    }
    public static void main(String[] args) {

        //理论上num结果为2万
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount()>2){
            //main  GC
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+num);
    }
}
```

这些类的底层都直接和操作系统挂钩！在内存中修改值！Unsafe类是一个很特殊的存在！



> 指令重排

什么是指令重排：**你写的程序，计算机并不是按照你写的那样去执行的**

源代码-->编译器优化的重排-->指令并行也可能会重排-->内存系统也会重排-->执行

`处理器在进行指令重排的时候，考虑：数据之间的依赖性！`

```java
int x = 1; //1
int y = 2; //2
x = x + 5; //3
y = x * x; //4

//我们所期望的：1234  但可能执行的时候会变成 2134  1324  
//可不可能是 4123
```

可能造成影响的结果：a b x y 这四个值默认都是0

| 线程A | 线程B |
| ----- | ----- |
| x=a   | y=b   |
| b=1   | a=2   |

正常的结果：x = 0;y = 0; 但是可能指令重排

| 线程A | 线程B |
| :---- | ----- |
| b=1   | a=2   |
| x=a   | y=b   |

指令重排导致的诡异结果：x=2;y=1;



**volatile可以避免指令重排：**

内存屏障 CPU指令  作用：

1. 保证特定的操作的执行顺序
2. 可以保证某些变量的内存可见性 （利用这写特性就可以保证volatile的可见性）

![image-20220508175731313](https://gitee.com/linda12138/picgo/raw/master/images/image-20220508175731313.png)



Volatile可以保证可见性，不能保证原子性，由于内存屏障，可以保证避免指令重排的现象产生！



### 18、单例模式

------

> 饿汉式

```java
package com.peng.single;
//饿汉式单例
public class HungryDemo {

    //可能会浪费空间
    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];
    private byte[] data4 = new byte[1024*1024];

    private HungryDemo(){

    }
    private final static HungryDemo hungry = new HungryDemo();

    public static HungryDemo getInstance(){
        return hungry;
    }
}

```



> DCL懒汉式

```java
package com.peng.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

//懒汉式单例
public class LazyMan {

    private static boolean peng = false;

    private LazyMan(){

        synchronized (LazyMan.class){
            if (peng==false){
                peng = true;
            }else {
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
        }

        System.out.println(Thread.currentThread().getName()+"ok");
    }

    private volatile static LazyMan lazyMan;

    //双重检测锁模式的  懒汉式单例 DCL懒汉式
    public  static LazyMan getInstance(){
        //加锁
        if (lazyMan == null){
            synchronized (LazyMan.class){
                if (lazyMan == null){
                    lazyMan = new LazyMan(); //不是一个原子性操作
                    /**
                     * 1.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.把这个对象指向这个空间
                     *
                     * 123  132 A
                     *          B 此时lazyman还没有完成构造
                     * */
                }
            }
        }
        return lazyMan; //
    }
    //多线程并发
    //反射
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //        for (int i = 0; i < 10; i++) {
        //            new Thread(()->{
        //                LazyMan.getInstance();
        //            }).start();
        //        }
        //        LazyMan instance = LazyMan.getInstance();
        Field peng = LazyMan.class.getDeclaredField("peng");
        peng.setAccessible(true);
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan instance1 = declaredConstructor.newInstance();
        peng.set(instance1,false);
        LazyMan instance2 = declaredConstructor.newInstance();
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
    }
}
```



> 静态内部类

```java
package com.peng.single;

//静态内部类实现
public class Holder {
    private Holder(){

    }

    public  static  Holder getInstance(){
        return InnerClass.HOLDER;
    }
    public static class InnerClass{
        private static final Holder HOLDER = new Holder();
    }
}

```



> 单例不安全，反射

> 枚举

```java
package com.peng.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//enum  是什么？ 本身也是一个Class类
public enum EnumSingle {
    INSTANCE;
    public EnumSingle getInstance(){
        return INSTANCE;
    }
}

class Test{
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        EnumSingle instance1 = EnumSingle.INSTANCE;
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle instance2 = declaredConstructor.newInstance();

        //NoSuchMethodException
        System.out.println(instance1);
        System.out.println(instance2);

    }
}
```

![image-2022050987](https://gitee.com/linda12138/picgo/raw/master/images/image-20220509095918587.png)

枚举类型的最终反编译源码：

```java
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EnumSingle.java

package com.peng.single;


public final class EnumSingle extends Enum
{

    public static EnumSingle[] values()
    {
        return (EnumSingle[])$VALUES.clone();
    }

    public static EnumSingle valueOf(String name)
    {
        return (EnumSingle)Enum.valueOf(com/peng/single/EnumSingle, name);
    }

    private EnumSingle(String s, int i)
    {
        super(s, i);
    }

    public EnumSingle getInstance()
    {
        return INSTANCE;
    }

    public static final EnumSingle INSTANCE;
    private static final EnumSingle $VALUES[];

    static 
    {
        INSTANCE = new EnumSingle("INSTANCE", 0);
        $VALUES = (new EnumSingle[] {
            INSTANCE
        });
    }
}

```

jad:https://varaneckas.com/jad/



### 19、深入理解CAS

------

> 什么是CAS

```java
package com.peng.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    //CAS  compareAndSet 比较并交换
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2022);
        //期望  更新
        //public final boolean compareAndSet(int expect, int update)
        //如果我期望的值达到了，那么就更新，否则就不更新  CAS 是CPU的并发原语
        System.out.println(atomicInteger.compareAndSet(2022, 2023));
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(2022, 2023));
        System.out.println(atomicInteger.get());
    }
}

```



> Unsafe类

![image-20220509171154263](https://gitee.com/linda12138/picgo/raw/master/images/image-20220509171154263.png)

![image-20220509171432657](https://gitee.com/linda12138/picgo/raw/master/images/image-20220509171432657.png)

![image-20220509171540286](https://gitee.com/linda12138/picgo/raw/master/images/image-20220509171540286.png)

CAS:比较当前工作内存中的值和主内存中的值，如果这个值是期望的，那么则执行操作！如果不是就一直循环

缺点：

1. 循环会耗时
2. 一次性只能保证一个共享变量的原子性
3. ABA问题



> CAS:ABA问题（狸猫换太子）

![image-20220509172139524](https://gitee.com/linda12138/picgo/raw/master/images/image-20220509172139524.png)

```java
package com.peng.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    //CAS  compareAndSet 比较并交换
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2022);
        //对于我们平时写的SQL :乐观锁！
        //期望  更新
        //public final boolean compareAndSet(int expect, int update)
        //如果我期望的值达到了，那么就更新，否则就不更新  CAS 是CPU的并发原语
        //===========捣乱的线程=============
        System.out.println(atomicInteger.compareAndSet(2022, 2023));
        System.out.println(atomicInteger.get());


        System.out.println(atomicInteger.compareAndSet(2023, 2022));
        System.out.println(atomicInteger.get());

        //===========期望的线程=============
        System.out.println(atomicInteger.compareAndSet(2022, 6666));
        System.out.println(atomicInteger.get());

    }
}
```



### 20、原子引用

------

> 解决ABA问题，引入原子引用！对应的思想是乐观锁

带版本的原子操作！

**Integer使用了对象缓存机制，默认范国是-128~127，推荐使用静态工厂方法value0f获取对象实例，而不是new,因为valueof使用缓存，而new一定会创建新的对象分配新的内存空间；**

```java
package com.peng.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

//乐观锁原理
public class CASDemo {
    //CAS  compareAndSet 比较并交换
    public static void main(String[] args) {
        //        AtomicInteger atomicInteger = new AtomicInteger(2022);
        //Integer AtomicStampedReference 注意 如果泛型是包装类注意对象的引用问题
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,1);
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); //获得版本号
            System.out.println("A1=>"+stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Version +1
            System.out.println(atomicStampedReference.compareAndSet(1, 2, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("A2=>"+atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(2, 1, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("A3=>"+atomicStampedReference.getStamp());
        },"A").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); //获得版本号
            System.out.println("B1=>"+stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(1, 6, stamp, stamp + 1));
            System.out.println("B2=>"+atomicStampedReference.getStamp());
        },"B").start();
    }
}

```



### 21、各种锁的理解

------

#### 21.1、公平锁、非公平锁

公平锁：非常公平，不能够插队，必须先来后到

非公平锁：非常不公平，可以插队（默认都是非公平）

```java
public ReentrantLock() {
    sync = new NonfairSync();
}
//
public ReentrantLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
}
```



#### 21.2、可重入锁

可重入锁：递归锁

![image-20220509175340791](https://gitee.com/linda12138/picgo/raw/master/images/image-20220509175340791.png)

> Synchronized

```java
package com.peng.lock;

//Synchronized
public class Demo01 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{
            phone.sms();
        },"A").start();

        new Thread(()->{
            phone.sms();
        },"B").start();
    }
}

class Phone{
    public synchronized  void sms(){
        System.out.println(Thread.currentThread().getName()+"Sms");
        call(); //这里也有锁
    }

    public synchronized  void call(){
        System.out.println(Thread.currentThread().getName()+"call");
    }

}

```



> Lock版

```java
package com.peng.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Synchronized
public class Demo02 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();

        new Thread(()->{
            phone.sms();
        },"A").start();

        new Thread(()->{
            phone.sms();
        },"B").start();
    }
}

class Phone2{
    Lock lock = new ReentrantLock();
    public  void sms(){
        lock.lock(); //细节问题 ：lock.lock();lock.unlock 锁必须配对 否则就会死在里面
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"Sms");
            call(); //这里也有锁
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void call(){
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

}
```



#### 21.3、自旋锁

**spinlock**

![image-20220509195041865](https://gitee.com/linda12138/picgo/raw/master/images/image-20220509195041865.png)



> 自定义锁

```java
package com.peng.lock;

import java.util.concurrent.atomic.AtomicReference;

//自旋锁
public class SpinlockDemo {

    //int 0
    // Thread  null
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    //加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"my lock");

        while (!atomicReference.compareAndSet(null,thread)){


        }    }


    //解锁

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"my unlock");

        atomicReference.compareAndSet(thread,null);
    }
}

```

> 测试

```java
package com.peng.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestSpinLock {
    public static void main(String[] args) throws InterruptedException {
        //        ReentrantLock reentrantLock = new ReentrantLock();
        //        reentrantLock.lock();
        //        reentrantLock.unlock();

        //底层使用的自旋锁
        SpinlockDemo spinlockDemo = new SpinlockDemo();

        new Thread(()->{
            spinlockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinlockDemo.myUnLock();
            }
        },"T1").start();

        TimeUnit.SECONDS.sleep(3);

        new Thread(()->{
            spinlockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinlockDemo.myUnLock();
            }
        },"T2").start();

    }
}

```



#### 21.4、死锁

> 死锁是什么

![image-20220509201240584](https://gitee.com/linda12138/picgo/raw/master/images/image-20220509201240584.png)



**产生死锁的四个必要条件**
1、 互斥条件：
进程要求对所分配的资源（如打印机）进行排他性控制，即在一段时间内某资源仅为一个进程所占有。此时若有其他进程请求该资源，则请求进程只能等待。

2、不可剥夺条件:
进程所获得的资源在未使用完毕之前，不能被其他进程强行夺走，即只能由获得该资源的进程自己来释放（只能是主动释放)。

3、 请求与保持条件：
进程已经保持了至少一个资源，但又提出了新的资源请求，而该资源已被其他进程占有，此时请求进程被阻塞，但对自己已获得的资源保持不放。

4、循环等待条件:
存在一种进程资源的循环等待链，链中每一个进程已获得的资源同时被 链中下一个进程所请求。即存在一个处于等待状态的进程集合{Pl, P2, …, pn}，其中Pi等 待的资源被P(i+1)占有（i=0, 1, …, n-1)，Pn等待的资源被P0占有

以上这四个条件是死锁的必要条件，只要系统发生死锁，这些条件必然成立，而只要上述条件之一不满足，就不会发生死锁。

**处理死锁的方法**
预防死锁：通过设置某些限制条件，去破坏产生死锁的四个必要条件中的一个或几个条件，来防止死锁的发生。
避免死锁：在资源的动态分配过程中，用某种方法去防止系统进入不安全状态，从而避免死锁的发生。
检测死锁：允许系统在运行过程中发生死锁，但可设置检测机构及时检测死锁的发生，并采取适当措施加以清除。
解除死锁：当检测出死锁后，便采取适当措施将进程从死锁状态中解脱出来。

死锁测试，怎么排除死锁：

```java
package com.peng.lock;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {
    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";


        new Thread(new MyThread(lockA,lockB),"T1").start();
        new Thread(new MyThread(lockB,lockA),"T2").start();
    }
}

class MyThread implements Runnable{
    private String lockA;
    private String lockB;

    public MyThread(String lockA,String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }
    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"Lock:"+lockA+"=>get:"+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"Lock:"+lockB+"=>get:"+lockA);
            }
        }
    }
}

```

> 解决问题

1、使用`jps -l`定位进程号

![image-20220509202223285](https://gitee.com/linda12138/picgo/raw/master/images/image-20220509202223285.png)

2、使用`jstack 进程号`查看进行信息



