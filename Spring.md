### 1、Spring

------

#### 1.1、简介

- Spring:春天---->给软件行业带来了春天！
- 2002，首次推出了Spring框架的雏形：interface21框架！
- Spring框架即以interface21框架为基础。经过重新设计，并不断丰富其内涵，于2004年3月24日，发布了1.0正式版。
- [Rod Johnson](https://baike.baidu.com/item/Rod Johnson)Spring框架的创始人，同时也是SpringSource的联合创始人。
- spring理念：使现有的技术更加容易使用，本身是一个大杂烩，整合了现有的技术框架！



- SSH:Struct2+Spring+Hibernate
- SSM:SpringMVC+Spring+Mybatis



官网：https://spring.io/

官方下载地址：https://repo.spring.io/ui/native/release/org/springframework/spring/

GitHub地址：https://github.com/spring-projects/spring-framework



```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.3.17</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.3.17</version>
</dependency>
```



#### 1.2、优点

- Spring是一个开源免费的框架（容器）！
- Spring是一个轻量级的、非入侵式的框架！
- 控制反转（IOC），面向切面编程(AOP)
- 支持事务的处理，对框架整合的支持！



> 总结：Spring就是一个轻量级的控制反转（IOC）和面向切面编程（AOP）的框架！



#### 1.3、组成

![img](https://gitee.com/linda12138/picgo/raw/master/image/20170713150400373)



#### 1.4、拓展

在Spring的官网有这个介绍：现代化的java开发！说白就是基于Spring开发！

![image-20220328174620328](https://gitee.com/linda12138/picgo/raw/master/image/image-20220328174620328.png)

- SpringBoot
  - 一个快速开发的脚手架
  - 基于SpringBoot可以快速的开发单个微服务
  - 约定大于配置！
- SpringCloud
  - 基于SpringBoot实现的



因为现在大多数公司都在使用SpringBoot进行快速开发，学习SpringBoot的前提，需要完全掌握Spring及SpringMVC！承上启下！



**弊端：发展了太久之后，违背了原来的理念！配置十分繁琐，人称，”配置地狱“。**





### 2、IOC理论

------

1. UserDao接口

   ```java
   public interface UserDao {
       void getUser();
   }
   ```

   

2. UserDaoImpl实现类

   ```java
   public class UserDaoImpl implements UserDao{
       @Override
       public void getUser() {
           System.out.println("默认获取用户的数据");
       }
   }
   ```

   

3. UserService 业务接口

   ```java
   public interface UserService {
       void getUser();
   }
   ```

   

4. UserServiceImpl业务接口实现类

   ```java
   public class UserServiceImpl implements UserService{
   
       private UserDao userDao;
       //private UserDao userDao = new UserDaoImpl();
   
       //利用set进行动态实现值的注入！
       public void setUserDao(UserDao userDao) {
           this.userDao = userDao;
       }
   
       @Override
       public void getUser() {
           userDao.getUser();
       }
   }
   ```

5. 测试

   ```java
   public static void main(String[] args) {
       //用户实际调用的是业务层，dao层他们不需要接触
       UserServiceImpl userService = new UserServiceImpl();
   
       userService.setUserDao(new UserDaoMysqlImpl());
       userService.getUser();
   }
   ```

   



在我们之前的业务中，用户的需求可能会影响我们原来的代码，我们需要根据用户的需求去修改代码！如果程序的代码量十分大，修改一次的成本会十分昂贵。

![image-20220328192519567](https://gitee.com/linda12138/picgo/raw/master/image/image-20220328192519567.png)

我们使用一个Set接口实现,已经发生了革命性的变化

```java
private UserDao userDao;

//利用set进行动态实现值的注入！
public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
}
```



- 之前，程序是主动创建对象！控制权在程序员手上！
- 使用了Set注入后，程序不再具有主动性，而是变成了被动的接受对象！





这种思想，从本质上解决了问题，我们程序员不用再去管理对象的创建了。系统的耦合性大大降低~，可以更加专注的在业务的实现上！这是IOC的原型！



![image-20220328192611470](https://gitee.com/linda12138/picgo/raw/master/image/image-20220328192611470.png)



#### IOC本质

**控制反转loC(Inversion of G，是一种设计思想，DI(依赖注入)是实现loC的一种方法，**也有人认为DI只是IoC的另一种说法。没有loC的程序中，我们使用面向对象编程，对象的创建与对象问的依赖关系完全硬编码在程序中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方，个认为所谓控制反转就是：获得依赖对象的方式反转了。



采用XML方式配置Bean的时候，Bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。

**控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是loC容器，其实现方法是依赖注入（Dependency Injection,DI）。**



### 3、HelloSpring

------

> 思考问题？

- Hello 对象是谁创建的？

​	hello 对象是由Spring创建的

- Hello 对象的属性是怎么设置的？

  hello 对象的属性是由Spring容器设置的

  

  这个过程就叫控制反转：

  控制：谁来控制对象的创建，传统应用程序的对象是由程序本身控制创建的，使用Spring后，对象是由Spring来创建的 

  反转：程序本身不创建对象，而变成被动的接收对象

  依赖注入：就是利用set方法来进行注入的

  IOC是一种编程思想，由主动的编程变成被动的接收

  可以通过newClassPathXmlApplicationContext去浏览一下底层源码

  **OK，到了现在，我们彻底不用再程序中去改动了，要实现不同的操作，只需要在xml配置文件中进行修改，所谓的loC,一句话搞定：对象由Spring 来创建，管理，装配！**

```java
package com.peng.pojo;

public class Hello {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Hello{" +
            "str='" + str + '\'' +
            '}';
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--使用Spring来创建对象，在Spring这些都叫做bean
 类型  变量名 = new 类型();
 Hello hello = new Hello();
     bean = 对象  new Hello();

     id=变量名
     class = new 的对象
     property 相当于给对象中的属性设置一个值
-->
    <bean id="hello" class="com.peng.pojo.Hello">
        <property name="str" value="Spring"/>
    </bean>
</beans>

```

```java
import com.peng.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest {
    public static void main(String[] args) {
        //获取Spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //我们的对象现在都在Spring中管理了，我们要使用，直接去里面取出来使用就行了
        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello.toString());
    }
}
```



### 4、IOC创建对象的方式

------

1. 使用无参构造创建对象，默认！

2. 假设我们要使用有参构造创建对象

   1. 下标赋值

      ```xml
      <!--第一种:下标赋值-->
      <bean id="user" class="com.peng.pojo.User">
          <constructor-arg index="0" value="鹏飞1"/>
      </bean>
      ```

      

   2. 类型

      ```xml
      <!--第二种方式：通过类型创建 不建议使用-->
      <bean id="user" class="com.peng.pojo.User">
          <constructor-arg type="java.lang.String" value="鹏飞2"/>
      </bean>
      ```

      

   3. 参数名

      ```xml
      <!-- 第三种：直接使用参数名创建-->
      <bean id="user" class="com.peng.pojo.User">
          <constructor-arg name="name" value="鹏飞3"/>
      </bean>
      ```



总结：在配置文件加载的时候，容器种管理的对象就已经初始化了！





### 5、Spring配置

------

#### 5.1、别名

```xml
<!--别名，如果添加了别名，我们也可以使用别名获取到这个对象-->
<alias name="user" alias="newuser"/>
```

#### 5.2、Bean的配置

```xml
<!--
id:bean 的唯一标识符，也就是相当于我们学的对象名
class:bean 对象所对应的全限定名：包名+类型
name:也是别名,而且name 可以同时取多个别名
-->
<bean id="userT" class="com.peng.pojo.UserT" name="user2,u2 u3;u4">
    <property name="name" value="鹏"/>
</bean>
```

#### 5.3、import

这个import，一般用于团队开发使用，它可以将多个配置文件，导入合并为一个

假设，现在项目中有多个人开发，这三个人负责不同的类开发，不同的类需要注册在不同的bean种，我们可以利用import将所有人的beans.xml合并为一个总的！

1. 张三
2. 李四
3. 王五
4. applicationContext.xml

```xml
<import resource="beans.xml"/>
<import resource="beans2.xml"/>
<import resource="beans3.xml"/>
```



使用的时候，直接使用总的配置就可以了



### 6、依赖注入

------

#### 6.1、构造器注入

#### 6.2、Set方式注入【重点】

- 依赖注入：Set注入！
  - 依赖：bean对象的创建依赖于容器
  - 注入:bean对象中的所有属性，由容器来注入！

【环境搭建】

1. 复杂类型

   ```java
   package com.peng.pojo;
   
   public class Address {
       private String address;
   
       public String getAddress() {
           return address;
       }
   
       public void setAddress(String address) {
           this.address = address;
       }
   }
   ```

   

2. 真实测试对象

   ```java
   public class Student {
       private String name;
       private Address address;
       private String[] books;
       private List<String> hobbys;
       private Map<String,String> card;
       private Set<String> games;
       private String wife;
       private Properties info;
   }
   ```

   

3. beans.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
                              https://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <bean id="address" class="com.peng.pojo.Address">
           <property name="address" value="聊城"/>
       </bean>
   
       <bean id="student" class="com.peng.pojo.Student">
           <!--第一种：普通值注入：value-->
           <property name="name" value="鹏飞"/>
           <!--第二种，Bean注入  ref-->
           <property name="address" ref="address"/>
   
           <!--数组注入 ref-->
           <property name="books">
               <array>
                   <value>红楼梦</value>
                   <value>三国演义</value>
                   <value>水浒传</value>
                   <value>西游记</value>
               </array>
           </property>
           <!-- List集合-->
           <property name="hobbys">
               <list>
                   <value>听歌</value>
                   <value>敲代码</value>
                   <value>看电影</value>
               </list>
           </property>
           <!-- Map-->
           <property name="card">
               <map>
                   <entry key="身份证" value="123456456258444"/>
                   <entry key="银行卡" value="464843549831687"/>
               </map>
           </property>
           <property name="games">
               <!--set-->
               <set>
                   <value>哈利波特</value>
                   <value>LOL</value>
                   <value>COC</value>
               </set>
           </property>
           <!--null-->
           <property name="wife">
               <null/>
           </property>
           <!--Properties
       key=value
   -->
           <property name="info">
               <props>
                   <prop key="学号">202110620115</prop>
                   <prop key="username">12138</prop>
               </props>
           </property>
       </bean>
   </beans>
   ```

   

4. 测试类

   ```java
   import com.peng.pojo.Student;
   import org.springframework.context.ApplicationContext;
   import org.springframework.context.support.ClassPathXmlApplicationContext;
   
   public class MyTest {
       public static void main(String[] args) {
           ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
   
           Student student = (Student) context.getBean("student");
           System.out.println(student.toString());
   
           /*
       * Student{
       * name='鹏飞',
       *  address=Address{address='聊城'},
       *  books=[红楼梦, 三国演义, 水浒传, 西游记],
       *  hobbys=[听歌, 敲代码, 看电影],
       *  card={身份证=123456456258444, 银行卡=464843549831687},
       *  games=[哈利波特, LOL, COC],
       *  wife='null',
       *  info={学号=202110620115, username=12138}}
       * */
       }
   }
   ```



#### 6.3、拓展方式注入

我们可以使用p命名空间和c命名空间进行注入

官方解释：

文档：https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-p-namespace

![image-20220329191629771](https://gitee.com/linda12138/picgo/raw/master/image/image-20220329191629771.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--p命名空间注入，可以直接注入属性的值 : property-->
    <bean id="user" class="com.peng.pojo.User" p:name="鹏飞" p:age="22"/>

    <!--c命名空间注入，可以通过构造器注入：construct-args-->
    <bean id="user2" class="com.peng.pojo.User" c:name="哈哈哈" c:age="22"/>

</beans>

```

测试：

```java
@Test
public void ptest(){
    ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
    User user = context.getBean("user2", User.class);
    System.out.println(user);
}
}
```



注意点：p命名和c命名空间不能直接使用，需要导入xml约束

```xml
xmlns:p="http://www.springframework.org/schema/p"
xmlns:c="http://www.springframework.org/schema/c"
```





#### 6.4、bean的作用域

![image-20220329192049845](https://gitee.com/linda12138/picgo/raw/master/image/image-20220329192049845.png)



1. 单例模式（Spring默认机制）

   ```xml
   <bean id="user" class="com.peng.pojo.User" p:name="鹏飞" p:age="22" scope="singleton"/>
   ```

   

2. 原型模式：每次从容器中get的时候，都会产生一个新对象

   ```xml
   <bean id="user" class="com.peng.pojo.User" p:name="鹏飞" p:age="22" scope="prototype"/>
   ```

   

3. 其余的request、session、application、这些只能在web开发中使用到！



### 7、Bean的自动装配

------

- 自动装配是Spring满足bean依赖一种方式！
- Spring会在上下文中自动寻找，并自动给bean装配属性！



在Spring中有三种装配的方式

1. 在xml中显示的配置
2. 在Java中显示配置
3. 隐式的自动装配bran 【重要】



#### 7.1、测试

环境搭建：一个人有两个宠物！



#### 7.2、ByName自动装配

```xml
<bean id="cat" class="com.peng.pojo.Cat"/>
<bean id="dog" class="com.peng.pojo.Dog"/>

<!--
byName:会自动在容器上下文中查找，和自己对象set方法后面的值对应的beanid
-->
<bean id="people" class="com.peng.pojo.People" autowire="byName">
    <property name="name" value="鹏飞"/>
</bean>
```



#### 7.3、ByType自动装配

```xml
<bean class="com.peng.pojo.Cat"/>
<bean class="com.peng.pojo.Dog"/>

<!--
byName:会自动在容器上下文中查找，和自己对象set方法后面的值对应的beanid
byType:会自动在容器上下文中查找，和自己对象属性类型相同的bean
-->
<bean id="people" class="com.peng.pojo.People" autowire="byType">
    <property name="name" value="鹏飞"/>
</bean>
```

小结：

- byname的时候，需要保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set方法的值一致！
- bytype的时候，需要保证所有bean的class唯一，并且这个bean需要和自动注入的属性的类型一致！



#### 7.4、使用注解实现自动装配

JDK1.5支持的注解，Spring2.5就支持注解了

The introduction of annotation-based configuration raised the question of whether this approach is “better” than XML. 

要使用注解须知

1. 导入约束 context约束
2. 配置注解的支持  ==context:annotation-config==

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```



**@Autowired**

直接在属性上使用即可！也可以在set方式上使用！

使用Autowired我们可以不用编写Set方法了，前提是你这个装配的属性在ioc（Spring）容器中存在，且符合名字byname!

科普：

```java
@Nullable 字段标记了这个注解，说明这个字段可以为空
```

```java
public @interface Autowired {
    boolean required() default true;
}
```

测试代码

```java
public class People {
    //如果显示的定义了Autowired的required属性为false，说明这个对象可以为null,否则不允许为空
    @Autowired(required = false)
    private Cat cat;
    @Autowired
    private Dog dog;
    private String name;
}
```



如果@Autowired自动装配的环境比较复杂，自动装配无法通过一个注解【@Autowired】完成的时候、我们可以使用@Qualifier("xxx")去配合@Autowired的使用，指定一个唯一的bean对象注入

```java
public class People {
    @Autowired
    @Qualifier("cat2")
    private Cat cat;
    @Autowired
    @Qualifier("dog222")
    private Dog dog;
    private String name;
}
```



**@Resource注解**

```java
public class People {
    @Resource("cat2")
    private Cat cat;
    @Resource
    private Dog dog;
    private String name;
}
```



小结：

@Resource和@Autowired的区别：

- 都是用来自动装配的，都可以放在属性字段上
- @Autowired 通过byType的方式实现，而且必须要求这个对象存在 【常用】
- @Resource 默认通过byname的方式实现，如果找不到名字，则通过byType实现！如果两个都找不到的情况下，就报错！【常用】
- 执行顺序不同：
  - @Autowired 通过byType的方式实现
  - @Resource 默认通过byname的方式实现





### 8、使用注解开发

------

在Spring4之后，要使用注解开发，必须要保证aop的包导入了

![image-20220330173906907](https://gitee.com/linda12138/picgo/raw/master/image/image-20220330173906907.png)

使用注解需要导入context约束，增加注解的支持

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```



1. bean

2. 属性如何注入

   ```java
   //等价于 <bean id="user" class="com.peng.pojo.User"/>
   //@Component  组件
   @Component
   public class User {
       //相当于<property name="name" value="鹏哥"/>
       @Value("鹏哥")
       public String name;
   
       public void setName(String name) {
           this.name = name;
       }
   }
   ```

   

3. 衍生的注解

   @Component有几个衍生注解，我们在Web开发中，会按照mvc三层架构分层

   dao 【@Repository】

   service 【@Service】

   controller 【@Controller】

   这四个注解功能都是一样的，都是代表将某个类注册到Spring中，装配bean!

4. 自动装配

   @Autowired:自动装配通过类型，名字

   ​	如果Autowired不能唯一自动装配上属性，则需要通过@Qualifier(value="xxx")

   @Nullable  字段标记了这个注解，说明这个字段可以为null

   @Resource:自动装配通过名字，类型

5. 作用域

   ```java
   @Component
   @Scope("singleton")
   public class User {
       //相当于<property name="name" value="鹏哥"/>
       @Value("鹏哥")
       public String name;
       public void setName(String name) {
           this.name = name;
       }
   }
   ```

   

6. 小结

- xml与注解：

  - xml更加万能，适用于任何场合！维护简单方便
  - 注解 不是自己类使用不了，维护相对复杂

  xml与注解最佳实践：

  - xml用来管理bean

  - 注解只负责完成属性的注入

  - 我们在使用的过程中，只需要注意一个问题：必须让注解生效，就需要开启注解的支持

    ```xml
    <context:annotation-config/>
    <!--指定要扫描的包，这个包下面的注解就会生效-->
    <context:component-scan base-package="com.peng"/>
    ```



### 9、使用java的方式配置Spring

------

我们现在要完全不使用Spring的xnl配置了，全权交给java来做！

javaConfig是Spring的一个子项目，在Spring4之后，它成为了一个核心功能！

![image-20220330181750029](https://gitee.com/linda12138/picgo/raw/master/image/image-20220330181750029.png)

实体类：

```java
package com.peng.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//这里这个注解的意思，就是说明这个类被Spring接管了，注册到了容器中
@Component
public class User {
    private String name;

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            '}';
    }

    public String getName() {
        return name;
    }
    @Value("鹏飞") //注入到容器中
    public void setName(String name) {
        this.name = name;
    }
}
```

配置文件：

```java
package com.peng.config;

import com.peng.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //这个也会被Spring容器托管，注册到容器中，因为他本来就是一个@Component
//@Configuration代表这是一个配置类，就和我们之前看的beans.xml
@ComponentScan("com.peng.pojo")
public class PengConfig {
    //注册一个bean，就相当于我们之前写的一个bean标签
    //    这个方法的名字，就相当于bean标签中的id属性
    //    这个方法的返回值，就相当于bean标签中的class属性
    @Bean
    public User user(){
        return new User();//就是返回要注入到bean的对象
    }
}
```

测试类：

```java
import com.peng.config.PengConfig;
import com.peng.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Mytest {
    public static void main(String[] args) {
        //        如果完全使用了配置类方式去做，我们就只能通过AnnotationConfig上下文来获取容器，通过配置类的class对象加载！
        ApplicationContext context = new AnnotationConfigApplicationContext(PengConfig.class);
        User user = context.getBean("user", User.class);
        System.out.println(user.getName());
    }
}
```

这种纯java的配置方式，在SpringBoot中随处可见！



### 10、代理模式

------

为什么要学习代理模式？因为这就是SpringAOP的底层！【SpringAOP和SpringMVC】

代理模式的分类：

- 静态代理
- 动态代理



![image-20220331122416302](https://gitee.com/linda12138/picgo/raw/master/image/image-20220331122416302.png)

#### 10.1、静态代理

角色分析：

- 抽象角色：一把会使用接口或者抽象类来解决
- 真实角色：被代理的角色
- 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作
- 客户：访问代理对象的人！



代码步骤：

1. 接口

   ```java
   //租房
   public interface Rent {
       public void rent();
   }
   ```

   

2. 真实角色

   ```java
   //房东
   public class Host implements Rent{
   
       @Override
       public void rent() {
           System.out.println("房东要出租房子！");
       }
   }
   ```

   

3. 代理角色

   ```java
   //代理
   public class Proxy implements Rent{
       private Host host;
   
       public Proxy() {
       }
   
       public Proxy(Host host) {
           this.host = host;
       }
   
       @Override
       public void rent() {
           seeHouse();
           host.rent();
           hetong();
           fare();
       }
       //看房
       public void seeHouse(){
           System.out.println("中介带你看房");
       }
   
       //签租赁合同
       public void hetong(){
           System.out.println("签租赁合同");
       }
   
       //收中介费
       public void fare(){
           System.out.println("收中介费");
       }
   }
   ```

   

4. 客户端访问代理角色

   ```java
   package com.peng.demo01;
   
   public class Client {
       public static void main(String[] args) {
           //房东要出租房子
           Host host = new Host();
           //代理，中介帮房东租房子，但是呢？代理角色一般会有一些附属操作
           Proxy proxy = new Proxy(host);
   
           //你不用面对房东，直接找中介租房即可！
           proxy.rent();
       }
   }
   ```

   



代理模式的好处：

- 可以使真实角色的操作更加纯粹！不用去关注一些公共的业务
- 公共业务就交给了代理角色！实现了业务的分工！
- 公共业务发送扩展的时候，方便集中管理！

缺点：

- 一个真实角色就会产生一个代理角色；代码量会翻倍-开发效率会降低



#### 10.2、加深理解

代码：

- 接口：

```java
public interface UserService {
    public void add();
    public void delete();
    public void update();
    public void query();
}
```

- 真实对象

  ```java
  //真实对象
  public class UserServiceImpl implements UserService{
      @Override
      public void add() {
          System.out.println("增加了一个用户");
      }
  
      @Override
      public void delete() {
          System.out.println("删除了一个用户");
      }
  
      @Override
      public void update() {
          System.out.println("修改了一个用户");
      }
  
      @Override
      public void query() {
          System.out.println("查询一个用户");
      }
      //改动原有的业务代码，在公司中是大忌！
  }
  ```

- 代理

  ```java
  public class UserServiceProxy implements UserService{
      private  UserServiceImpl userService;
  
      public void setUserService(UserServiceImpl userService) {
          this.userService = userService;
      }
  
      @Override
      public void add() {
          log("add");
          userService.add();
      }
  
      @Override
      public void delete() {
          log("delete");
          userService.delete();
      }
  
      @Override
      public void update() {
          log("update");
          userService.update();
      }
  
      @Override
      public void query() {
          log("query");
          userService.query();
      }
  
      //日志方法
      public  void log(String msg){
          System.out.println("[Debug]使用了"+msg+"方法");
      }
  }
  ```

- 客户端访问代理角色

  ```java
  public class Client {
      public static void main(String[] args) {
          UserServiceImpl userService = new UserServiceImpl();
  
          UserServiceProxy proxy = new UserServiceProxy();
          proxy.setUserService(userService);
          proxy.add();
      }
  }
  ```

  

聊聊AOP

![image-20220331125801521](https://gitee.com/linda12138/picgo/raw/master/image/image-20220331125801521.png)





#### 10.3、动态代理

- 动态代理和静态代理角色一样
- 动态代理的代理类是动态生成的，不是我们直接写好的！
- 动态代理分为两大类：基于接口的动态代理，基于类的动态代理
  - 基于接口--JDK动态代理 【我们在这里使用】
  - 基于类：cglib
  - java字节码实现：javasist



需要了解两个类：Proxy[代理],InvocationHandler【调用处理程序】



动态代理的好处：

- 可以使真实角色的操作更加纯粹！不用去关注一些公共的业务
- 公共业务就交给了代理角色！实现了业务的分工！
- 公共业务发送扩展的时候，方便集中管理！
- 一个动态代理类代理的是一个接口，一般就是对应的一类业务
- 一个代理类可以代理多个类，只要是实现了同一个接口即可！



代码：

1. 接口

   ```java
   //租房
   public interface Rent {
       public void rent();
   }
   ```

   

2. 真实对象

   ```java
   //房东
   public class Host implements Rent {
   
       @Override
       public void rent() {
           System.out.println("房东要出租房子！");
       }
   }
   ```

   

3. 动态代理实现类

   ```java
   //等会我们用这个类，自动生成代理类
   public class ProxyInvocationHandler implements InvocationHandler {
       //被代理的接口
       private Rent rent;
   
       public void setRent(Rent rent) {
           this.rent = rent;
       }
       //    Foo f =（Foo）Proxy.newProxyInstance(Foo.class.getClassLoader(),
       //    new Class<?>[]{Foo.class},
       //    handler);
       //生成带到代理类
       public Object getProxy(){
           return Proxy.newProxyInstance(this.getClass().getClassLoader(),rent.getClass().getInterfaces(),this);
       }
       @Override
       //处理代理实例，并返回结果
       public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
           //动态代理的本质就是使用反射机制实现！
           seeHouse();
           Object result = method.invoke(rent, args);
           fare();
           return result;
       }
       public void seeHouse(){
           System.out.println("中介带着看房子");
       }
       public void fare(){
           System.out.println("收中介费");
       }
   }
   ```

   

4. 客户端访问代理角色

   ```java
   public class Client {
       public static void main(String[] args) {
           //真实角色
           Host host = new Host();
   
           //代理角色:现在没有
           ProxyInvocationHandler pih = new ProxyInvocationHandler();
           //通过调用程序处理角色来处理我们要调用的接口对象！
           pih.setRent(host);
   
           Rent proxy = (Rent) pih.getProxy(); //这里的proxy就是动态生成的，我们并没有写
           proxy.rent();
       }
   }
   ```



万能动态实现类【只需要修改target即可】

```java
//等会我们用这个类，自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {
    //被代理的接口
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    //生成带到代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
    @Override
    //处理代理实例，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质就是使用反射机制实现！
        log(method.getName());
        Object result = method.invoke(target, args);
        return result;
    }
    public void log(String msg){
        System.out.println("执行了"+msg+"方法");
    }
}
```



```java
public class Client {
    public static void main(String[] args) {
        //真实角色
        UserServiceImpl userService = new UserServiceImpl();
        //代理角色
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setTarget(userService);//设置要代理的对象
        //动态生成代理类
        UserService proxy = (UserService) pih.getProxy();
        proxy.add();
    }
}
```



### 11、AOP

------

#### 11.1、什么是AOP

AOP(Aspect Oriented Programming)意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，也是Sping框架中的一个重要内容，是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

![image-20220331165145720](https://gitee.com/linda12138/picgo/raw/master/image/image-20220331165145720.png)



#### 11.2、Aop在Spring中的作用

==提供声明式事务；允许用户自定义切面==

- 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志，安全，缓存，事务等等…

- 切面(ASPECT):横切关注点被模块化的特殊对象。即，它是一个类。
- 通知(Advice):切面必须要完成的工作。即，它是类中的一个方法。
- 目标(Target):被通知对象。
- 代理(Proxy):向目标对象应用通知之后创建的对象。
- 切入点(PointCut):切面通知执行的“地点"的定义。
- 连接点(JointPoint):与切入点匹配的执行点。



![image-20220331165725418](https://gitee.com/linda12138/picgo/raw/master/image/image-20220331165725418.png)

SpringAOP中，通过Advice定义横切逻辑，Spring中支持5种类型的Advice:

![image-20220331165844261](https://gitee.com/linda12138/picgo/raw/master/image/image-20220331165844261.png)

即Aop在不改变原有代码的情况下，去增加新的功能。



#### 11.3、使用Spring实现Aop

【重点】使用AOP织入，需要导入一个依赖包！

```xml
<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.9</version>
</dependency>
```



方式一：使用Spring的API接口【主要SpringApi接口实现】

1. 接口

   ```java
   public interface UserService {
       public void add();
       public void delete();
       public void update();
       public void select();
   }
   ```

   

2. 实现类

   ```java
   public class UserServiceImpl implements UserService{
       @Override
       public void add() {
           System.out.println("增加了一个用户");
       }
   
       @Override
       public void delete() {
           System.out.println("删除了一个用户");
       }
   
       @Override
       public void update() {
           System.out.println("更新了一个用户");
       }
   
       @Override
       public void select() {
           System.out.println("查询了一个用户");
       }
   }
   ```

   

3. 日志类

   ```java
   public class AfterLog implements AfterReturningAdvice {
       //returnVlue 返回值
       @Override
       public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
           System.out.println("执行了"+method.getName()+"方法，返回结果为"+returnValue);
       }
   }
   ```

   ```java
   public class log implements MethodBeforeAdvice {
       //method:要执行的目标对象的方法
       //    args：参数
       //    target:目标对象
       @Override
       public void before(Method method, Object[] args, Object target) throws Throwable {
           System.out.println(target.getClass().getName()+"的"+method.getName()+"被执行了");
       }
   }
   ```

   

4. 测试

   ```java
   public class MyTest {
       public static void main(String[] args) {
           ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
           //动态代理代理的是接口：注意
           UserService userService = context.getBean("userService", UserService.class);
           userService.add();
       }
   }
   ```

5. 配置文件

   ```xml
   <!--注册bean-->
   <bean id="userService" class="com.peng.service.UserServiceImpl"/>
   <bean id="log" class="com.peng.log.log"/>
   <bean id="AfterLog" class="com.peng.log.AfterLog"/>
   <!--方式一：使用原生Spring API接口-->
   
   <!--配置AOP:需要导入aop的约束-->
   <aop:config>
       <!--切入点：execution:表达式execution（要执行的位置）-->
       <aop:pointcut id="pointcut" expression="execution(* com.peng.service.UserServiceImpl.*(..))"/>
       <!-- 执行环绕增加-->
       <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
       <aop:advisor advice-ref="AfterLog" pointcut-ref="pointcut"/>
   </aop:config>
   ```

   



方式二:自定义类来实现【主要是接口】

```java
public class DiyP {

    public void bef(){
        System.out.println("方法执行前");
    }
    public void aft(){
        System.out.println("方法执行后");
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/aop
                           https://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--注册bean-->
    <bean id="userService" class="com.peng.service.UserServiceImpl"/>
    <bean id="log" class="com.peng.log.log"/>
    <bean id="AfterLog" class="com.peng.log.AfterLog"/>
    <!--方式一：使用原生Spring API接口-->

    <!--配置AOP:需要导入aop的约束-->
    <!--   <aop:config>-->
    <!--           &lt;!&ndash;切入点：execution:表达式execution（要执行的位置）&ndash;&gt;-->
    <!--            <aop:pointcut id="pointcut" expression="execution(* com.peng.service.UserServiceImpl.*(..))"/>-->
    <!--           &lt;!&ndash; 执行环绕增加&ndash;&gt;-->
    <!--            <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>-->
    <!--           <aop:advisor advice-ref="AfterLog" pointcut-ref="pointcut"/>-->
    <!--   </aop:config>-->

    <!-- 方式二：自定义类-->
    <bean id="diy" class="com.peng.diy.DiyP"/>

    <aop:config>
        <!--自定义切面：ref要引用的类-->
        <aop:aspect ref="diy">
            <!--切入点-->
            <aop:pointcut id="point" expression="execution(* com.peng.service.UserServiceImpl.*(..))"/>
            <!--通知-->
            <aop:before method="bef" pointcut-ref="point"/>
            <aop:after method="aft" pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>
</beans>

```



方式三：使用注解实现！

```xml
<!--方式三-->
<bean id="annotationPointCut" class="com.peng.diy.AnnotationPointCut"/>
<!-- 开启注解支持  JDK(默认proxy-target-class="false")  cglib proxy-target-class="true"-->
<aop:aspectj-autoproxy/>
```

```java
@Aspect//标注这个类是一个切面
public class AnnotationPointCut {
    @Before("execution(* com.peng.service.UserServiceImpl.*(..))")
    public void beff(){
        System.out.println("方法执行前");
    }
    @After("execution(* com.peng.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("方法执行后");
    }
    //在环绕增强中，我们可以给定一个参数，代表我们要获取处理切入的点
    @Around("execution(* com.peng.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");
        Signature signature = jp.getSignature();//获得签名
        System.out.println("signature"+signature);
        //执行方法
        Object proceed = jp.proceed();
        System.out.println("环绕后");
        System.out.println(proceed);
    }
}
```





### 12、整合Mybatis

------

步骤：

1. 导入相关jar包

   - junit

   - mybatis

   - mysql数据库

   - spring相关的

   - aop织入

   - mybatis-spring 【新】

2. 编写配置文件
3. 测试

#### 12.1、回忆mybatis

1. 编写实体类

   ```java
   @Data
   public class User {
       private int id;
       private String name;
       private String pwd;
   }
   ```

   

2. 编写核心配置文件

   ```xml
   <dependencies>
       <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-webmvc</artifactId>
           <version>5.3.17</version>
       </dependency>
   
       <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-jdbc</artifactId>
           <version>5.3.17</version>
       </dependency>
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.11</version>
       </dependency>
   
       <!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
       <dependency>
           <groupId>org.aspectj</groupId>
           <artifactId>aspectjweaver</artifactId>
           <version>1.9.9</version>
       </dependency>
   </dependencies>
   <build>
       <resources>
           <resource>
               <directory>src/main/resources</directory>
               <includes>
                   <include>**/*.properties</include>
                   <include>**/*.xml</include>
               </includes>
               <filtering>false</filtering>
           </resource>
           <resource>
               <directory>src/main/java</directory>
               <includes>
                   <include>**/*.properties</include>
                   <include>**/*.xml</include>
               </includes>
               <filtering>false</filtering>
           </resource>
       </resources>
   </build>
   ```

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE configuration
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <!--configuration-->
   <configuration>
       <typeAliases>
           <package name="com.peng.pojo"/>
       </typeAliases>
       <environments default="development">
           <environment id="development">
               <transactionManager type="JDBC"/>
               <dataSource type="POOLED">
                   <property name="driver" value="com.mysql.jdbc.Driver"/>
                   <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
                   <property name="username" value="root"/>
                   <property name="password" value=""/>
               </dataSource>
           </environment>
       </environments>
       <mappers>
           <mapper class="com.peng.Mapper.UserMapper"/>
       </mappers>
   
   </configuration>
   ```

   

3. 编写接口

   ```java
   public interface UserMapper {
       public List<User> selectUser();
   }
   ```

   

4. 编写Mapper.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   
   <mapper namespace="com.peng.Mapper.UserMapper">
       <select id="selectUser" resultType="user">
           select * from mybatis.user;
       </select>
   </mapper>
   ```

   

5. 测试

   ```java
   import com.peng.Mapper.UserMapper;
   import com.peng.pojo.User;
   import org.apache.ibatis.io.Resources;
   import org.apache.ibatis.session.SqlSession;
   import org.apache.ibatis.session.SqlSessionFactory;
   import org.apache.ibatis.session.SqlSessionFactoryBuilder;
   import org.junit.Test;
   
   import java.io.IOException;
   import java.io.InputStream;
   import java.util.List;
   
   public class Mytest {
       @Test
       public void test() throws IOException {
           String resources = "mybatis-config.xml";
           InputStream in = Resources.getResourceAsStream(resources);
   
           SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
           SqlSession sqlSession = sessionFactory.openSession(true);
   
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           List<User> userList = mapper.selectUser();
   
           for (User user : userList) {
               System.out.println(user);
           }
           sqlSession.close();
       }
   }
   ```

   

#### 12.2、Mybatis-Spring

1. 编写数据源配置
2. sqlSessionFactory
3. sqlSessionTemplate
4. 需要给接口加实现类 【新】
5. 将自己写的实现类，注入到Spring中
6. 测试使用即可

```java
package com.peng.Mapper;

import com.peng.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class UserMapperImpl implements UserMapper{
    //我们的所有操作，都使用sqlSession来执行，在原来，现在都使用SqlSessionTemplate
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> selectUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
```

**spring-dao.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/aop
                           https://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--DataSource:使用Spring的数据源替换mybatis的配置 c3p0 dbcp druid
    我们这里直接使用Spring提供的JDBC
-->
    <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
        <property name="username" value="root"/>
        <property name="password" value=""/>
    </bean>
    <!-- SqlSessionFcatory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource"/>
        <!--绑定Mybatis配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/peng/Mapper/*.xml"/>
    </bean>

    <!--SqlSessionTemplate:就是我们使用的SqlSession-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <!--只能使用构造器注入sqlSessionFactory，因为它没有set方法-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>
</beans>
```

**applicationContext.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/aop
                           https://www.springframework.org/schema/aop/spring-aop.xsd">
    <import resource="spring-dao.xml"/>
    <!---->
    <bean id="userMapper" class="com.peng.Mapper.UserMapperImpl">
        <property name="sqlSession" ref="sqlSession"/>
    </bean>
</beans>
```



方式二:

```java
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper{

    @Override
    public List<User> selectUser() {
        return getSqlSession().getMapper(UserMapper.class).selectUser();
    }
}
```

```xml
<bean id="userMapper2" class="com.peng.Mapper.UserMapperImpl2">
    <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
</bean>
```



### 13、声明式事务

------

#### 13.1、回顾事务

- 把一组业务当成一个业务来做；要么都成功，要么都失败！
- 事务在项目开发中，十分的重要，涉及到数据一致性问题，不能马虎！
- 确保完整性和一致性；



事务ACID原则：

- 原子性
- 一致性
- 隔离性
  - 多个业务可能操作同一个资源，防止数据损坏
- 持久性
  - 事务一旦提交，无论系统发生什么问题，结果都不会再被影响，被持久化的写到存储器中！



#### 13.2、spring中的事务管理

- 声明式事务：AOP
- 编程式事务：需要在代码中，进行事务的管理



思考：

为什么需要事务？

- 如果不配置事务，可能存在数据提交不一致的情况；
- 如果我们不再spring中去配置声明式事务，我们就需要在代码中手动配置事务
- 事务在项目的开发中十分重要，设计到数据的一致性和完整性问题，不容马虎！



**==实体类：==**

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String pwd;
}
```

**==接口：==**

```java
public interface UserMapper {
    public List<User> selectUser();

    //添加一个用户
    public int addUser(User user);
//    删除一个用户
    public int deleteUser(int id);
}
```

**==实现类：==**

```java
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper{
    @Override
    public List<User> selectUser() {
        User user = new User(7, "王", "1234596");
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        mapper.addUser(user);
        mapper.deleteUser(5);

        return mapper.selectUser();
    }

    @Override
    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return  getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }
}
```

**==Mapper.xml==**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.peng.mapper.UserMapper">
    <select id="selectUser" resultType="user">
        select * from mybatis.user;
    </select>

    <insert id="addUser" parameterType="user">
        insert into mybatis.user (id,name,pwd) values(#{id},#{name},#{pwd});
    </insert>

    <delete id="deleteUser" parameterType="int">
        delete from mybatis.user where id=#{id};
    </delete>
</mapper>
```

**==spring-dao.xml:==**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           https://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/aop
                           https://www.springframework.org/schema/aop/spring-aop.xsd
                           http://www.springframework.org/schema/tx
                           https://www.springframework.org/schema/tx/spring-tx.xsd
                           ">
    <!--DataSource:使用Spring的数据源替换mybatis的配置 c3p0 dbcp druid
    我们这里直接使用Spring提供的JDBC
-->
    <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
        <property name="username" value="root"/>
        <property name="password" value=""/>
    </bean>
    <!-- SqlSessionFcatory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource"/>
        <!--绑定Mybatis配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/peng/mapper/*.xml"/>
    </bean>

    <!--SqlSessionTemplate:就是我们使用的SqlSession-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <!--只能使用构造器注入sqlSessionFactory，因为它没有set方法-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>
    <!-- 配置声明式事务-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="datasource"/>
    </bean>

    <!--结合AOP实现事务的织入-->
    <!--配置事务通知:-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <!--给哪些事务配置事务-->
        <!--配置事务的传播特性：new  propagation-->
        <tx:attributes>
            <tx:method name="add" propagation="REQUIRED"/>
            <tx:method name="delete"  propagation="REQUIRED"/>
            <tx:method name="update"  propagation="REQUIRED"/>
            <tx:method name="query" read-only="true"/>
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>

    <!--配置事务切入-->
    <aop:config>
        <aop:pointcut id="txPointcut" expression="execution(* com.peng.mapper.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointcut"/>
    </aop:config>
</beans>
```

==测试：==

```java
public class Mytest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userSession = context.getBean("userMapper", UserMapper.class);
        for (User user : userSession.selectUser()) {
            System.out.println(user);
        }
    }
}
```

