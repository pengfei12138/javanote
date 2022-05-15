### Mybatis

------

环境：

- JDK17
- Mysql 5.7
- maven
- idea

回顾：

- JDBC
- Mysql
- java基础
- Maven
- junit



SSM框架：配置文件。最好的方式：看官网文档；

https://mybatis.org/mybatis-3/zh/index.html



### 1、简介

------

#### 1.1、什么是Mybatis

![image-20220321170339269](https://gitee.com/linda12138/picgo/raw/master/image/image-20220321170339269.png)

- MyBatis 是一款优秀的**持久层框架**
- 它支持自定义 SQL、存储过程以及高级映射。
- MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。
- MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。
- MyBatis本是apache的一个[开源项目](https://baike.baidu.com/item/开源项目/3406069)iBatis，2010年这个[项目](https://baike.baidu.com/item/项目/477803)由apache software foundation迁移到了[google code](https://baike.baidu.com/item/google code/2346604)，并且改名为MyBatis。
- 2013年11月迁移到[Github](https://baike.baidu.com/item/Github/10145341)。



如何获得Mybatis?

- maven仓库：https://mvnrepository.com/artifact/org.mybatis/mybatis/3.5.9

  ```xml
  <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
  <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.9</version>
  </dependency>
  ```

  

- github：https://github.com/mybatis/mybatis-3

- 中文文档：https://mybatis.org/mybatis-3/zh/index.html



#### 1.2、持久化

数据持久化

- 持久化就是将程序的数据在持久状态和瞬时状态转化的过程
- 内存：**断电即失**
- 数据库（jdbc）,io文件持久化。
- 生活：冷藏、罐头。

**为什么需要持久化？**

- 有一些对象我们不能让他丢掉。

- 内存太贵了



#### 1.3、持久层

Dao层，Service层，Controller层...

- 完成持久化工作的代码块
- 层界限十分明显。



#### 1.4、为什么需要Mybatis?

- 帮助程序员将数据存入到数据库中。
- 方便
- 传统的jdbc代码太复杂了。简化。框架。自动化。
- 不用Mybatis也可以。更容易上手。**技术没有高低之分**
- 优点
  - 简单易学
  - 灵活
  - sql和代码的分离，提高了可维护性。
  - 提供映射标签，支持对象与数据库的orm字段关系映射。
  - 提供对象关系映射标签，支持对象关系组建维护。
  - 提供xml标签，支持编写动态sql。

**最重要的一点：使用的人多！**

String StringMVC Stringboot



### 2、第一个Mybatis程序

------

思路：搭建环境-->导入Mybatis-->编写代码-->测试！

#### 2.1、搭建环境

搭建数据库

```sql
CREATE DATABASE `mybatis`;

USE `mybatis`;

CREATE TABLE `user`(
`id` INT(20) NOT NULL PRIMARY KEY,
`name` VARCHAR(30) DEFAULT NULL,
`pwd` VARCHAR(30) DEFAULT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `user` (`id`,`name`,`pwd`) VALUES
(1,'鹏飞','123456'),
(2,'张三','123456'),
(3,'李四','1234513')
```

新建项目

1. 新建一个普通的maven项目

2. 删除src目录

3. 导入maven依赖

   ```xml
   <!--导入依赖-->
   <dependencies>
       <!-- mysql驱动-->
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>5.1.47</version>
       </dependency>
       <!--mybatis-->
       <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis</artifactId>
           <version>3.5.9</version>
       </dependency>
       <!--junit-->
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.11</version>
           <scope>test</scope>
       </dependency>
   </dependencies>
   ```



#### 2.2、创建一个模块

- 编写mybatis的核心配置文件

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <!--configuration-->
  <configuration>
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
          <mapper resource="org/mybatis/example/BlogMapper.xml"/>
      </mappers>
  </configuration>
  ```

  

- 编写mybatis工具类

  ```java
  package com.peng.utils;
  
  import org.apache.ibatis.io.Resources;
  import org.apache.ibatis.session.SqlSession;
  import org.apache.ibatis.session.SqlSessionFactory;
  import org.apache.ibatis.session.SqlSessionFactoryBuilder;
  
  import java.io.IOException;
  import java.io.InputStream;
  
  //sqlSessionFactory -- >sqlSession
  public class MybatisUtils {
      private  static SqlSessionFactory sqlSessionFactory;
      static {
          String resource = "mybatis-config.xml";
          try {
              //使用Mybatis第一步，获取sqlSessionFactory对象
              InputStream inputStream = Resources.getResourceAsStream(resource);
               sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  //    既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例。
  //    SqlSession完全包含了面向数据库执行sql命令所需的所有方法
  
      public  static SqlSession getSqlSession(){
         return sqlSessionFactory.openSession();
      }
      
  }
  ```





#### 2.3、编写代码

- 实体类

  ```java
  package com.peng.pojo;
  
  public class User {
      private int id;
      private String name;
      private String pwd;
  
      public User() {
      }
  
      public User(int id, String name, String pwd) {
          this.id = id;
          this.name = name;
          this.pwd = pwd;
      }
  
      public int getId() {
          return id;
      }
  
      public void setId(int id) {
          this.id = id;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public String getPwd() {
          return pwd;
      }
  
      public void setPwd(String pwd) {
          this.pwd = pwd;
      }
  
      @Override
      public String toString() {
          return "User{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  ", pwd='" + pwd + '\'' +
                  '}';
      }
  }
  ```

  

- Dao接口

  ```java
  public interface UserDao {
      List<User> getUserList();
  }
  ```

  

- 接口实现类由原来的UserDaoImpl转变为一个Mapper配置文件

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <!--namespace=绑定一个对应的Dao/Mapper接口-->
  <mapper namespace="com.peng.dao.UserDao">
      <!--select查询语句-->
      <select id="getUserList" resultType="com.peng.pojo.User">
          select * from mybatis.user
      </select>
  </mapper>
  ```



#### 2.4、测试

核心注册文件中注册mappers

- junit测试

  ```java
  package com.peng.dao;
  
  import com.peng.pojo.User;
  import com.peng.utils.MybatisUtils;
  import org.apache.ibatis.session.SqlSession;
  import org.junit.Test;
  
  import java.util.List;
  
  public class UserDaoTest {
      @Test
      public void test(){
          //获得SqlSession对象
          SqlSession sqlSession = MybatisUtils.getSqlSession();
          try{
              //执行sql 方式一 getMapper
              UserDao mapper = sqlSession.getMapper(UserDao.class);
              List<User> userList = mapper.getUserList();
  
              //方式二
  //        List<User> userList = sqlSession.selectList("com.peng.dao.UserDao.getUserList");
              for (User user : userList) {
                  System.out.println(user);
              }
          }catch (Exception e){
              e.printStackTrace();
          }  finally {
              //关闭SqlSession
              sqlSession.close();
          }
      }
  }
  ```



**可能遇到的错误**

1. 配置文件没有注册
2. 绑定接口错误
3. 方法名不对
4. 返回值类型不对
5. Maven导出资源问题



### 3、CRUD

------

#### 1、namespace

namespace中的包名要和Mapper、Dao接口的包名一致



#### 2、select

选择、查询语句；

- id:就是对应的namespace中的方法名
- resultType:sql语句执行的返回值！
- parameterType：参数类型！



1. 编写接口

   ```java
   //根据id查询用户
   User getUserById(int id);
   ```

2. 编写对应的mapper中的sql语句

   ```xml
   <select id="getUserById" parameterType="int" resultType="com.peng.pojo.User">
       select * from mybatis.user where id = #{id}
   </select>
   ```

   

3. 测试

   ```java
   package com.peng.dao;
   
   import com.peng.pojo.User;
   import com.peng.utils.MybatisUtils;
   import org.apache.ibatis.session.SqlSession;
   import org.junit.Test;
   
   import java.util.List;
   
   public class UserDaoTest {
       @Test
       public void test(){
           //获得SqlSession对象
           SqlSession sqlSession = MybatisUtils.getSqlSession();
           try{
               //执行sql 方式一 getMapper
               UserMapper mapper = sqlSession.getMapper(UserMapper.class);
               List<User> userList = mapper.getUserList();
   
               //方式二
   //        List<User> userList = sqlSession.selectList("com.peng.dao.UserDao.getUserList");
               for (User user : userList) {
                   System.out.println(user);
               }
           }catch (Exception e){
               e.printStackTrace();
           }  finally {
               //关闭SqlSession
               sqlSession.close();
           }
       }
       @Test
       public void getUserById(){
           SqlSession sqlSession = MybatisUtils.getSqlSession();
   
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
           User user = mapper.getUserById(1);
           System.out.println(user);
           sqlSession.close();
       }
       //增删改需要提交事务
       @Test
       public void addUser(){
           SqlSession sqlSession = MybatisUtils.getSqlSession();
   
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
           int res = mapper.addUser(new User(5, "哈哈哈", "123523"));
           if (res>0){
               System.out.println("插入成功！");
           }
           //提交事务
           sqlSession.commit();
           sqlSession.close();
       }
   
   
       @Test
       public void updateUser(){
           SqlSession sqlSession = MybatisUtils.getSqlSession();
   
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
           int res = mapper.updateUser(new User(4, "你妹的", "12123"));
           if (res>0){
               System.out.println("更新成功！");
           }
           //提交事务
           sqlSession.commit();
           sqlSession.close();
       }
   
       @Test
       public void deleteUser(){
           SqlSession sqlSession = MybatisUtils.getSqlSession();
   
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
           int res = mapper.deleteUser(4);
           if (res>0){
               System.out.println("删除成功！");
           }
           sqlSession.commit();
           sqlSession.close();
       }
   }
   ```

   

#### 3、Insert

```xml
<insert id="addUser" parameterType="com.peng.pojo.User">
    insert into mybatis.user (id,name,pwd) values(#{id},#{name},#{pwd});
</insert>
```

#### 4、Update

```xml
<update id="updateUser" parameterType="com.peng.pojo.User">
    update mybatis.user
    set name= #{name},pwd=#{pwd}
    where id=#{id};
</update>
```

#### 5、Delete

```xml
<delete id="deleteUser" parameterType="int">
    delete from mybatis.user where id = #{id};
</delete>
```



注意点：

- 增删改需要提交事务



#### 6、万能Map

假设我们的实体类，或者数据库中的表，字段或者参数过多，我们应当考虑Map!

```java
    @Test
    public void addUser1(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        HashMap<String, Object> map = new HashMap<String,Object>();

        map.put("userid",4);
        map.put("username","hello");
        map.put("userpassword","1234567");
        mapper.addUser1(map);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

```

```java
//万能map
int addUser1(Map<String,Object> map);
```

```xml
<!-- 传递map的key-->
<insert id="addUser1" parameterType="map">
    insert into mybatis.user (id,name,pwd) values(#{userid},#{username},#{userpassword});
</insert>
```



Map传递参数，直接在sql中取出key即可！  【parameterType="map"】

对象传递参数，直接在sql中取对象的属性即可！ 【parameterType="Object"】

只有一个基本类型参数的情况下，可以直接在sql中取到！ 【parameterType=""】

多个参数用Map,**或注解！**



#### 8、模糊查询

模糊查询怎么写？

1. java代码执行的时候，传递通配符%%

   ```java
   @Test
   public void getUserLike(){
       SqlSession sqlSession = MybatisUtils.getSqlSession();
   
       UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
       List<User> userLike = mapper.getUserLike("%李%");
       for (User user : userLike) {
           System.out.println(user);
       }
       sqlSession.close();
   }
   ```

   ```xml
   <select id="getUserLike" resultType="com.peng.pojo.User">
       select * from mybatis.user where name like #{value}
   </select>
   ```

   

2. 在sql拼接中使用通配符！

```xml
 select * from mybatis.user where name like "%"#{value}""
```





### 4、配置解析

------

#### 1、核心配置文件

- mybatis-config.xml

- MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息。

  ```xml
  configuration（配置）
  properties（属性）
  settings（设置）
  typeAliases（类型别名）
  typeHandlers（类型处理器）
  objectFactory（对象工厂）
  plugins（插件）
  environments（环境配置）
  environment（环境变量）
  transactionManager（事务管理器）
  dataSource（数据源）
  databaseIdProvider（数据库厂商标识）
  mappers（映射器）
  ```



#### 2、环境配置（environments）

MyBatis 可以配置成适应多种环境

**不过要记住：尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。**

学会使用配置多套运行环境！

Mybatis默认的事务管理器就是JDBC，连接池：POOLED



#### 3、属性（properties）

我们可以通过properties属性来实现引用配置文件

这些属性可以在外部进行配置，并可以进行动态替换。你既可以在典型的 Java 属性文件中配置这些属性，也可以在 properties 元素的子元素中设置。【db.properties】

![image-20220322154002655](https://gitee.com/linda12138/picgo/raw/master/image/image-20220322154002655.png)

编写一个配置文件

```properties
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT
username=root
password=
```

在核心配置文件中引入

```xml
<!-- 引入外部配置文件-->
<properties resource="db.properties">
    <property name="username" value="root"/>
    <property name="password" value=""/>
</properties>
```

- 可以直接引入外部文件
- 可以在其中增加一些属性配置
- 如果两个文件有一个字段，优先使用外部配置文件的！



#### 4、类型别名（typeAliases）

- 类型别名可为 Java 类型设置一个缩写名字。
- 意在降低冗余的全限定类名书写。

```xml
<!--可以给实体类起别名-->
<typeAliases>
    <typeAlias type="com.peng.pojo.User" alias="User"></typeAlias>
</typeAliases>
```

也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean 比如：

扫描实体类的包，它的默认别名就为他的类名 首字母小写（建议）

```xml
<!--可以给实体类起别名-->
<typeAliases>
    <package name="com.peng.pojo"/>
</typeAliases>
```



在实体类比较少的时候，使用第一种方法

如果实体类十分多，建议使用第二种

第一种可以DIY别名，第二种则不行，如果非要改，需要在实体类上面加注解

```java
@Alias("user")
public class User {}
```



#### 5、设置（settings）

这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。

![image-20220322160304277](https://gitee.com/linda12138/picgo/raw/master/image/image-20220322160304277.png)



#### 6、其他配置

- [typeHandlers（类型处理器）](https://mybatis.org/mybatis-3/zh/configuration.html#typeHandlers)
- [objectFactory（对象工厂）](https://mybatis.org/mybatis-3/zh/configuration.html#objectFactory)
- [plugins（插件）](https://mybatis.org/mybatis-3/zh/configuration.html#plugins)
  - mybatis-generator-core
  - mybatis-plus
  - 通用mapper



#### 7、映射器（mappers）

MapperRegistry:注册绑定我们的Mapper文件；

方式一：【推荐使用】

```xml
<mappers>
    <mapper resource="com/peng/dao/UserMapper.xml"/>
</mappers>
```

方式二：使用class文件绑定注册

```xml
<mapper class="com.peng.dao.UserMapper"></mapper>
```



注意点：

- 接口和它的Mapper配置文件必须同名
- 接口和它的Mapper配置文件必须在同一包下



方式三：使用扫描包进行注入绑定

```xml
<package name="com.peng.dao"/>
```

注意点：

- 接口和它的Mapper配置文件必须同名
- 接口和它的Mapper配置文件必须在同一包下



练习时间：

- 将数据库配置文件外部引入
- 实体类别名
- 保证UserMapper接口和UserMapper.xml改为一致！并且放在同一个包下！



#### 8、生命周期和作用域

![image-20220322162930373](https://gitee.com/linda12138/picgo/raw/master/image/image-20220322162930373.png)

生命周期和作用域，是至关重要的，因为错误的使用会导致非常严重的并发问题。

**SqlSessionFactoryBuilder：**

- 一旦创建了 SqlSessionFactory，就不再需要它了。
- 局部变量

**SqlSessionFactory:**

- 说白了就是可以想象为：数据库连接池
- SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，**没有任何理由丢弃它或重新创建另一个实例。**
- 因此 SqlSessionFactory 的最佳作用域是应用作用域。
- 最简单的就是使用**单例模式**或者静态单例模式。

**SqlSession：**

- 连接到连接池的一个请求！
- SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。
- 用完之后需要赶紧关闭，否则资源被占用！

![image-20220322163512145](https://gitee.com/linda12138/picgo/raw/master/image/image-20220322163512145.png)

这里面的每一个Mapper，就代表一个具体的业务！



### 5、解决属性名和字段名不一致的问题

------

#### 1、问题

数据库中的字段

![image-20220322163700622](https://gitee.com/linda12138/picgo/raw/master/image/image-20220322163700622.png)

新建一个项目，拷贝之前的，测试实体类字段不一致的情况

```java
public class User {
    private int id;
    private String name;
    private String password;}
```

测试出现问题：

![image-20220322164608659](https://gitee.com/linda12138/picgo/raw/master/image/image-20220322164608659.png)

```java
//    select * from mybatis.user where id = #{id}
//    类型处理器
//    select id,name,pwd as password from mybatis.user where id = #{id}
```



解决方案：

- 起别名

  ```xml
  <select id="getUserById" parameterType="int" resultType="com.peng.pojo.User">
      select id,name,pwd as password from mybatis.user where id = #{id}
  </select>
  ```

  

#### 2、resultMap

结果集映射

```java
id  name  pwd
id  name  password
```

```xml
<!--结果集映射-->
<resultMap id="UserMap" type="User">
    <!--column数据库中的字段，property实体类中的属性-->
    <result column="id" property="id"/>
    <result column="name" property="name"/>
    <result column="pwd" property="password"/>
</resultMap>

<select id="getUserById" resultMap="UserMap">
    select * from mybatis.user where id = #{id}
</select>
```



- `resultMap` 元素是 MyBatis 中最重要最强大的元素。
- ResultMap 的设计思想是，对简单的语句做到零配置，对于复杂一点的语句，只需要描述语句之间的关系就行了。
- 这就是 `ResultMap` 的优秀之处——你完全可以不用显式地配置它们。
- 如果这个世界总是这么简单就好了



### 6、日志

------

#### 6.1、日志工厂

如果一个数据库操作，出现了异常，我们需要排错。日志就是最好的助手。

曾经：sout、debug

现在：日志工厂！

![image-20220322203611121](https://gitee.com/linda12138/picgo/raw/master/image/image-20220322203611121.png)

- SLF4J 
- LOG4J 【掌握】
- LOG4J2
- JDK_LOGGING
- COMMONS_LOGGING
- STDOUT_LOGGING 【掌握】
- NO_LOGGING



在Mybatis中具体使用哪一个日志实现，在设置中设定！

**STDOUT_LOGGING标准日志输出！**

在mybatis核心配置文件中，配置我们的日志！

```xml
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```



![image-20220322204515112](https://gitee.com/linda12138/picgo/raw/master/image/image-20220322204515112.png)



#### 6.2、log4j

什么是log4j:

- Log4j是[Apache](https://baike.baidu.com/item/Apache/8512995)的一个开源项目，通过使用Log4j，我们可以控制日志信息输送的目的地是[控制台](https://baike.baidu.com/item/控制台/2438626)、文件、[GUI](https://baike.baidu.com/item/GUI)组件，甚至是套接口服务器、[NT](https://baike.baidu.com/item/NT/3443842)的事件记录器、[UNIX](https://baike.baidu.com/item/UNIX) [Syslog](https://baike.baidu.com/item/Syslog)[守护进程](https://baike.baidu.com/item/守护进程/966835)等；
- 我们也可以控制每一条日志的输出格式；
- 通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程。
- 通过一个[配置文件](https://baike.baidu.com/item/配置文件/286550)来灵活地进行配置，而不需要修改应用的代码。



1.先导入log4j的包

```xml
<!-- https://mvnrepository.com/artifact/log4j/log4j -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

2.log4j.properties

```properties
#将等级为DEBUG的日志信息输出到console和file这两个目的地，console和file的定义在下面的代码
log4j.rootLogger=DEBUG,console,file

#控制台输出的相关设置
log4j.appender.console = org.apache.log4j.ConsoleAppender
log4j.appender.console.Target = System.out
log4j.appender.console.Threshold=DEBUG
log4j.appender.console.layout = org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=[%c]-%m%n

#文件输出的相关设置
log4j.appender.file = org.apache.log4j.RollingFileAppender
log4j.appender.file.File=./log/peng.log
log4j.appender.file.MaxFileSize=10mb
log4j.appender.file.Threshold=DEBUG
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n

#日志输出级别
log4j.logger.org.mybatis=DEBUG
log4j.logger.java.sql=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.ResultSet=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
```



3.配置log4j为日志的实现

```xml
<settings>
    <setting name="logImpl" value="LOG4J"/>
</settings>
```

4.Log4j的使用！直接测试运行即可！

![image-20220322210334243](https://gitee.com/linda12138/picgo/raw/master/image/image-20220322210334243.png)

**简单使用**

1. 在要使用Log4j的类中，导入包：import org.apache.log4j.Logger;

2. 日志对象，参数为当前类的class

   ```java
   static Logger logger = Logger.getLogger(UserDaoTest.class);
   ```

   

3. 日志级别

   ```java
   logger.info("info:进入了testlog4j");
   logger.debug("debug:进入了log4j");
   logger.error("error:进入了log4j");
   ```




### 7、分页

------

**思考：为什么要分页？**

- 减少数据的处理量



#### 7.1、使用Limit分页

```sql
语法：select * from user limit startIndex,pageSize;

select * from user limit 2; #【0，n】
```



使用Mybatis实现分页，核心Sql

1. 接口

   ```java
   //分页
   List<User> getUserLimit(Map<String,Integer> map);
   ```

   

2. Mapper.XML

   ```xml
   <!--分页-->
   <select id="getUserLimit" resultMap="UserMap" parameterType="map">
       select * from mybatis.user limit #{startIndex},#{pageSize}
   </select>
   ```

   

3. 测试

```java
@Test
public void getUserLimit(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    HashMap<String, Integer> map = new HashMap<String,Integer>();
    map.put("startIndex",0);
    map.put("pageSize",2);
    List<User> userLimit = mapper.getUserLimit(map);

    for (User user : userLimit) {
        System.out.println(user);
    }
    sqlSession.close();
}
```



#### 7.2、RowBounds分页（了解）

不再使用SQL实现分页

1. 接口

   ```java
   //分页2
   List<User> getUserRowBounds();
   ```

   

2. mapper.xml

   ```xml
   <!--分页2-->
   <select id="getUserRowBounds" resultMap="UserMap">
       select * from mybatis.user
   </select>
   ```

   

3. 测试

   ```java
   @Test
   public void getUserRowBounds(){
       SqlSession sqlSession = MybatisUtils.getSqlSession();
       //RowBounds实现
       RowBounds rowBounds = new RowBounds(1, 2);
   
       //通过Java代码层面实现分页
       List<User> userlist = sqlSession.selectList("com.peng.dao.UserMapper.getUserRowBounds",null,rowBounds);
       for (User user : userlist) {
           System.out.println(user);
       }
       sqlSession.close();
   }
   ```



### 8、使用注解开发

------

#### 8.1、面向接口编程

- 大家之前都学过面向对象编程，也学习过接口，但在真正的开发中，很多时候我们会选择面向接口编程
- 根本原因：**解耦，可拓展，提高复用，分层开发中，上层不用管具体的实现，大家都遵守共同的标准，使得开发卡变得容易，规范性更好**
- 在一个面向对象的系统中，系统的各种功能是由许许多多的不同对象协作完成的。在这种情况下，各个对象内部是如何实现自己的,对系统设计人员来讲就不那么重要了;
- 而各个对象之间的协作关系则成为系统设计的关键。小到不同类之间的通信，大到各模块之间的交互，在系统设计之初都是要着重考虑的，这也是系统设计的主要工作内容。面向接口编程就是指按照这种思想来编程。



**关于接口的理解**

- 接口从更深层次的理解，应是定义（规范，约束）与实现（名实分离的原则）的分离。
- 接口的本身反映了系统设计人员对系统的抽象理解。
- 接口应有两类：
  - 第一类是对一个个体的抽象，它可对应为一个抽象体(abstract class);
  - 第二类是对一个个体某一方面的抽象，即形成一个抽象面（interface）；
  - 个体有可能有多个抽象面。抽象体与抽象面是有区别的。



**三个面向区别**

- 面向对象是指，我们考虑问题时，以对象为单位，考虑它的属性及方法
- 面向过程是指，我们考虑问题时，以一个具体的流程（事务过程）为单位，考虑它的实现
- 接口设计与非接口设计是针对复用技术而言的，与面向对象（过程）不是一个问题.更多的体现就是对系统整体的架构



#### 8.2、使用注解开发

1. 注解在接口上实现

   ```java
   @Select("select * from user")
   List<User> getUsers();
   ```

   

2. 需要在核心配置文件中绑定接口！

   ```xml
   <!--绑定接口-->
   <mappers>
       <mapper class="com.peng.dao.UserMapper"/>
   </mappers>
   ```

   

3. 测试

   ```java
   @Test
   public void test(){
       SqlSession sqlSession = MybatisUtils.getSqlSession();
       //        底层主要应用反射
       UserMapper mapper = sqlSession.getMapper(UserMapper.class);
       List<User> users = mapper.getUsers();
       for (User user : users) {
           System.out.println(user);
       }
       sqlSession.close();
   }
   ```

本质：反射机制实现

底层：动态代理！

![image-20220323162143502](https://gitee.com/linda12138/picgo/raw/master/image/image-20220323162143502.png)



**Mybtais的详细执行流程！**

![temp](https://gitee.com/linda12138/picgo/raw/master/image/temp.png)

#### 8.3、CRUD

我们可以在工具类创建的时候实现自动提交事务！

```java
public  static SqlSession getSqlSession(){
    return sqlSessionFactory.openSession(true);
}
```



编写接口，增加注解

```java
package com.peng.dao;

import com.peng.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

    //方法存在多个参数，所有的参数前面必须加上@Param("id")注解
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") int id);

    @Insert("insert into user(id,name,pwd) values(#{id},#{name},#{password})")
    int addUser(User user);

    @Update("update user set name=#{name},pwd=#{password} where id =#{id}")
    int updateUser(User user);

    @Delete("delete from user where id = #{uid}")
    int deleteUser(@Param("uid") int id);
}
```



测试类

[注意：我们必须要将接口注册绑定到我们的核心配置文件中！]

```java
@Test
public void test(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    //        底层主要应用反射
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);

    mapper.deleteUser(6);

    sqlSession.close();
}
//        List<User> users = mapper.getUsers();
//        for (User user : users) {
//            System.out.println(user);
//        }

//        User userById = mapper.getUserById(1);
//        System.out.println(userById);

//    int i = mapper.addUser(new User(6, "你妹", "12138"));
//        if (i>0){
//            System.out.println("插入成功");
//        }

//        mapper.updateUser(new User(4,"哈哈哈","154848"));
```



**关于@Param()注解**

- 基本类型的参数或者String类型，需要加上
- 引用类型不需要加
- 如果只有一个基本类型的话，可以忽略，但是建议大家都加上！
- 我们在SQL中引用的就是我们这里的@Param("uid")中设定的属性名！



**`#{}`和`${}`的区别**

`$ {}`方式会引发SQL注入的问题、同时也会影响SQL语句的预编译，所以从安全性和性能的角度出发，能使用`#{}`的情况下就不要使用 `${}`。



### 9、Lombok(尽量不用)

------

```java
Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
    
Project Lombok 是一个 java 库，可自动插入您的编辑器和构建工具，为您的 java 增添趣味。
永远不要再编写另一个 getter 或 equals 方法，使用一个注释，您的类就有一个功能齐全的构建器、自动化您的日志记录变量等等。
```

- java library
- plugs
- build tools
- with one annotation your class



使用步骤：

1. 在idea中安装Lombok插件
2. 在项目中导入Lombok的jar包

```xml
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.22</version>
</dependency>
```



```java
@Getter and @Setter
@FieldNameConstants
@ToString
@EqualsAndHashCode
@AllArgsConstructor, @RequiredArgsConstructor and @NoArgsConstructor
@Log, @Log4j, @Log4j2, @Slf4j, @XSlf4j, @CommonsLog, @JBossLog, @Flogger, @CustomLog
@Data
@Builder
@SuperBuilder
@Singular
@Delegate
@Value
@Accessors
@Wither
@With
@SneakyThrows
@val
@var
experimental @var
@UtilityClass
```

说明：

```java
@Data:无参构造，get、set、tostring、hashcode、equals
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter and @Setter
```



### 10、多对一

------

多对一：

![image-20220324200601686](https://gitee.com/linda12138/picgo/raw/master/image/image-20220324200601686.png)

- 多个学生，对应一个老师
- 对于学生这边而言，**关联**（多个学生关联一个老师）【多对一】
- 对于老师而言，**集合**，一个老师有很多学生【一对多】

![image-20220324210000801](https://gitee.com/linda12138/picgo/raw/master/image/image-20220324210000801.png)

SQL:

```sql
CREATE TABLE `teacher` (
    `id` INT(10) NOT NULL,
    `name` VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO teacher(`id`, `name`) VALUES (1, '秦老师'); 

CREATE TABLE `student` (
    `id` INT(10) NOT NULL,
    `name` VARCHAR(30) DEFAULT NULL,
    `tid` INT(10) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fktid` (`tid`),
    CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('1', '小明', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('2', '小红', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('3', '小张', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('4', '小李', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('5', '小王', '1');
```



#### 测试环境搭建

1. 导入Lombok
2. 新建实体类Teacher，Student
3. 建立Mapper接口
4. 建立Mapper.xml文件
5. 在核心文件中绑定注册我们的Mapper接口或文件！【方式很多，随心选】
6. 测试查询是否能够成功！



#### 按照查询嵌套处理

```xml
<!--
思路：
    1.查询所有的学生信息
    2.根据查询出来的学生的tid，寻找对应的老师！ 子查询
-->
<select id="getStudent" resultMap="StudentTeacher">
    select * from student
</select>
<resultMap id="StudentTeacher" type="Student">
    <result property="id" column="id"/>
    <result property="name" column="name"/>
    <!--        复杂的属性我们需要单独处理
            对象：association
            集合：collection
-->
    <association property="teacher" column="tid" javaType="Teacher" select="getTeacher"/>
</resultMap>

<select id="getTeacher" resultType="Teacher">
    select * from teacher where id = #{id}
</select>
```



#### 按照结果嵌套处理

```xml
<!--按照结果嵌套处理-->
<select id="getStudent2" resultMap="StudentTeacher2">
    select s.id sid,s.name sname,t.name tname
    from student s,teacher t
    where s.tid=t.id
</select>
<resultMap id="StudentTeacher2" type="Student">
    <result property="id" column="sid"/>
    <result property="name" column="sname"/>
    <association property="teacher" javaType="Teacher">
        <result property="name" column="tname"/>
    </association>
</resultMap>
```



回顾MySql多对一查询方式：

- 子查询
- 联表查询





### 11、一对多

------

比如：一个老师拥有多个学生！

对于老师而言，就是一对多的关系

#### 环境搭建

1. 环境搭建

**实体类**

```java
@Data
public class Teacher {
    private int id;
    private String name;
    //一个老师拥有多个学生
    private List<Student> students;
}
```

```java
@Data
public class Student {
    private int id;
    private String name;
    private int tid;
}
```



#### 按照结果嵌套处理

```xml
<!--按结果嵌套查询-->
<select id="getTeacher" resultMap="TeacherStudent">
    select s.id sid,s.name sname,t.name tname ,t.id tid
    from student s,teacher t
    where s.tid = t.id and t.id = #{tid}
</select>
<resultMap id="TeacherStudent" type="Teacher">
    <result property="id" column="tid"/>
    <result property="name" column="tname"/>
    <!--        javaType="" 指定属性的类型
集合中的泛型信息我们使用ofType执行-->
    <collection property="students" ofType="Student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <result property="tid" column="tid"/>
    </collection>
</resultMap>
```

#### 按照查询嵌套处理

```xml
<select id="getTeacher2" resultMap="TeacherStudent2">
    select * from mybatis.teacher where id = #{tid}
</select>

<resultMap id="TeacherStudent2" type="Teacher">
    <collection property="students" javaType="ArrayList" ofType="Student" select="getStudentByTeacherId" column="id"/>
</resultMap>

<select id="getStudentByTeacherId" resultType="Student">
    select * from mybatis.student where tid = #{tid}
</select>
```



#### 小结

1. 关联-association 【多对一】
2. 集合-collection 【一对多】
3. javaType & ofType
   1. javaType 用来指定实体类中属性的类型
   2. ofType 用来指定映射到List或者集合中pojo类型，泛型中的约束类型！



注意点：

- 保证SQL的可读性，尽量保证通俗易懂
- 注意一对多和多对一中，属性名和字段的问题！
- 如果问题不好排查错误，可以使用日志，建议使用Log4j



**慢SQL 1s  1000s**  

面试高频

- Mysql引擎
- innoDB底层原理
- 索引
- 索引优化



### 12、动态SQL

------

==**什么是动态SQL:动态SQL就是指根据不同的条件生成不同的SQL语句**==

利用动态SQL这一特性可以彻底摆脱这种痛苦

```java
如果你之前用过 JSTL 或任何基于类 XML 语言的文本处理器，你对动态 SQL 元素可能会感觉似曾相识。在 MyBatis 之前的版本中，需要花时间了解大量的元素。借助功能强大的基于 OGNL 的表达式，MyBatis 3 替换了之前的大部分元素，大大精简了元素种类，现在要学习的元素种类比原来的一半还要少。

if
choose (when, otherwise)
trim (where, set)
foreach
```



#### 搭建环境

```sql
CREATE TABLE `blog`(
`id` VARCHAR(50) NOT NULL COMMENT '博客id',
`title` VARCHAR(100) NOT NULL COMMENT '博客标题',
`author` VARCHAR(30) NOT NULL COMMENT '博客作者',
`create_time` DATETIME NOT NULL COMMENT '创建时间',
`views` INT(30) NOT NULL COMMENT '浏览量'
)ENGINE=INNODB DEFAULT CHARSET=utf8;
```



创建一个基础工程

1. 导包

2. 编写配置文件

   ```java
   @SuppressWarnings("all")
   public class IDutlis {
       public static String getId(){
           return UUID.randomUUID().toString().replaceAll("-","");
       }
   }
   ```

   ```xml
   <!--实现驼峰命名转换-->
   <setting name="mapUnderscoreToCamelCase	" value="true"/>
   ```

   

3. 编写实体类

   ```java
   @Data
   public class Blog {
       private int id;
       private String title;
       private String author;
       private Date createTime;
       private int views;
   }
   ```

   

4. 编写实体类对应的Mapper接口和Mapper.XML文件

   ```java
   int addBlog(Blog blog);
   ```

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <mapper namespace="com.peng.dao.BlogMapper">
       <insert id="addBlog" parameterType="blog">
           insert into mybatis.blog (id,title,author,create_time,views)
           values (#{id},#{title},#{author},#{createTime},#{views})
       </insert>
   </mapper>
   ```

   

5. 测试

   ```java
   import com.peng.dao.BlogMapper;
   import com.peng.pojo.Blog;
   import com.peng.utils.IDutlis;
   import com.peng.utils.MybatisUtils;
   import org.apache.ibatis.session.SqlSession;
   import org.junit.Test;
   
   import java.util.Date;
   
   public class test {
       @Test
       public void addBlogTest() {
           SqlSession sqlSession = MybatisUtils.getSqlSession();
           BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
           Blog blog = new Blog();
           blog.setId(IDutlis.getId());
           blog.setTitle("Mybatis");
           blog.setAuthor("鹏飞");
           blog.setCreateTime(new Date());
           blog.setViews(9999);
   
           mapper.addBlog(blog);
   
           blog.setId(IDutlis.getId());
           blog.setTitle("Java");
           mapper.addBlog(blog);
   
           blog.setId(IDutlis.getId());
           blog.setTitle("Spring");
           mapper.addBlog(blog);
   
           blog.setId(IDutlis.getId());
           blog.setTitle("微服务");
           mapper.addBlog(blog);
   
           sqlSession.close();
       }
   }
   ```

   



#### IF

```xml
<select id="queryBlogIF" parameterType="map" resultType="blog">
    select * from mybatis.blog where 1=1
    <if test="title!=null">
        and title = #{title}
    </if>
    <if test="author!=unll">
        and author = #{author}
    </if>
</select>
```



```java
//查询博客
List<Blog> queryBlogIF(Map map);
```



```java
@Test
public void queryBlog(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
    HashMap map = new HashMap();
    //        map.put("title","java");
    map.put("author","鹏飞");

    List<Blog> blogs = mapper.queryBlogIF(map);

    for (Blog blog : blogs) {
        System.out.println(blog);
    }
    sqlSession.close();
}
```



#### choose (when, otherwise)

```xml
<select id="queryBlogChoose" parameterType="map" resultType="blog">
    select *
    from mybatis.blog
    <where>
        <choose>
            <when test="title!=null">
                title = #{title}
            </when>
            <when test="author!=null">
                author = #{author}
            </when>
            <otherwise>
                and views=#{views}
            </otherwise>
        </choose>
    </where>
</select>
```

#### trim (where, set)

```xml
<select id="queryBlogIF" parameterType="map" resultType="blog">
    select * from mybatis.blog
    <where>
        <if test="title!=null">
            title = #{title}
        </if>
        <if test="author!=unll">
            and author = #{author}
        </if>
    </where>
</select>
```



```xml
<update id="updateBlog" parameterType="map">
    update mybatis.blog
    <set>
        <if test="title!=null">
            title = #{title},
        </if>
        <if test="author!=null">
            author = #{author}
        </if>
    </set>
    where id = #{id}
</update>
```



==**所谓的动态SQL，本质还是SQL语句，只是我们可以在SQL层面执行一些逻辑代码**==



#### foreach

```sql
select * from user where 1=1 and (id=1 or id=2 or id=3)

    <foreach item="id" collection="ids"
        open="ID in (" separator="or" close=")" nullable="true">
          #{id}
    </foreach>
```

```sql
<!--select * from user where 1=1 and (id=1 or id=2 or id=3)
我们现在传递一个万能的map,这个map中可以存在一个集合-->
    <select id="queryBlogForeach" parameterType="map" resultType="blog">
        select * from mybatis.blog
        <where>
            <foreach collection="ids" item="id" open="and (" close=")" separator="or">
            id = #{id}
            </foreach>
        </where>
    </select>
```

测试

```java
@Test
public void queryBlogForeach(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

    HashMap map = new HashMap();

    ArrayList<Integer> ids = new ArrayList<Integer>();
    ids.add(1);
    ids.add(2);
    ids.add(3);

    map.put("ids",ids);

    List<Blog> blogs = mapper.queryBlogForeach(map);

    for (Blog blog : blogs) {
        System.out.println(blog);
    }

    sqlSession.close();
}
```



#### SQL片段

有的时候我们可能会将一些公共的部分抽取出来，实现sql代码的复用

- 使用SQL标签抽取公共的部分
- 在需要使用的地方使用include标签引用即可

```sql
<sql id="if-title-author">
        <if test="title!=null">
            title = #{title}
        </if>
        <if test="author!=null">
            and author = #{author}
        </if>
</sql>

    <select id="queryBlogIF" parameterType="map" resultType="blog">
        select * from mybatis.blog
        <where>
            <include refid="if-title-author"></include>
        </where>
    </select>
```



注意事项：

- 最好基于单表来定义SQL片段！
- 不要存在where标签



==动态SQL就是在拼接SQL语句，我们只要保证SQL的正确性，按照SQL的格式，去排列组合就可以了==

建议：

- 先在Mysql中写出完整的sql，再对应的去修改成为我们的动态SQL实现通用即可！



### 13、缓存(了解)

------

#### 13.1、简介

```
查询  ：   链接数据库  耗资源
	一次查询的结果，给他暂存在一个可以直接取到的地方！--》内存：缓存
	
我们再次查询相同数据的时候，直接走缓存，就不用走数据库了
```



1.什么是缓存[Cache]？

- 存在内存中的临时数据

- 将用户经常查询的数据放在缓存（内存）中，用户去查询数据就不用从磁盘上(关系型数据库数据文件)查询，从缓存中查询，从而提高查询效率，解决了高并发系统的性能问题。

2.为什么使用缓存？

- 减少和数据库的交互次数，减少系统开销，提高系统效率。

3.什么样的数据能使用缓存？

- 经常查询并且不经常改变的数据。



#### 13.2、Mybatis缓存

- MyBatis包含一个非常强大的查询缓存特性，它可以非常方便地定制和配置缓存。缓存可以极大的提升查询效率
- MyBatis系统中默认定义了两级缓存：一级缓存和二级缓存。
  - 默认情况下，只有一级缓存开启。（SqISession级别的缓存，也称为本地缓存）
  - 二级缓存需要手动开启和配置，他是基于namespace级别的缓存。
  - 为了提高扩展性，MyBatis定义了缓存接口Cache。我们可以通过实现Cache接口来自定义二级缓存



#### 13.3、一级缓存

- 一级缓存也叫本地缓存： SqlSession
  - 与数据库同一次会话期间查询到的数据会放在本地缓存中
  - 以后如果需要获取相同的数据，直接从缓存中拿，没必须再去查询数据库；



测试步骤：

1. 开启日志

2. 测试在一个session中查询同一个记录

3. 查看日志输出

   ![image-20220327095054352](https://gitee.com/linda12138/picgo/raw/master/image/image-20220327095054352.png)

缓存失效的情况



1. 增删改操作，可能会改变原来的数据，所以必定会刷新缓存！

2. 查询不同的东西

3. 查询不同的Mapper.xml

4. 手动清理缓存！

   ![image-20220327095926143](https://gitee.com/linda12138/picgo/raw/master/image/image-20220327095926143.png)

小结：一级缓存是默认开启的，只在一次SqlSession中有效，也就是拿到连接到关闭连接这个区段！

一级缓存相当于一个Map!



#### 13.4、二级缓存

- 二级缓存也叫全局缓存，一级缓存作用域太低了，所以诞生了二级缓存

- 基于namespace级别的缓存，一个名称空间，对应一个二级缓存;
- 工作机制
  - 一个会话查询一条数据，这个数据就会被放在当前会话的一级缓存中;
  - 如果当前会话关闭了，这个会话对应的一级缓存就没了；但是我们想要的是，会话关闭了，一级缓存中的数据被保存到二级缓存中;
  - 新的会话查询信息，就可以从二级缓存中获取内容;
  - 不同的mapper查出的数据会放在自己对应的缓存（map）中;



步骤：

1. 开启全局缓存

   ```xml
   <!-- 显示的开启全局缓存-->
   <setting name="cacheEnabled" value="true"/>
   ```

2. 在要使用二级缓存的Mapper中开启

   ```xml
   <!--在当前Mapper.xml中使用二级缓存-->
   <cache/>
   ```

   也可以自定义一些参数

   ```xml
   <!--在当前Mapper.xml中使用二级缓存-->
   <cache eviction="FIFO"
          flushInterval="60000"
          size="512"
          readOnly="true"/>
   ```

3. 测试

   1. 问题：我们需要将实体类序列化！否则就会报错！

      ```
      Caused by: java.io.NotSerializableException: com.peng.pojo.User
      ```

   ```java
   @Data
   @AllArgsConstructor
   public class User implements Serializable {
       private int id;
       private String name;
       private String pwd;
   }
   ```



小结：

- 只要开启了二级缓存，在同一个Mapper下就有效
- 所有的数据都会先放着一级缓存中
- 只有当会话提交，或者关闭的时候，才会提交到二级缓存中



#### 13.5、缓存原理

![缓存](https://gitee.com/linda12138/picgo/raw/master/image/%E7%BC%93%E5%AD%98.png)





#### 13.6、自定义缓存-encache

```java
Ehcache是一种广泛使用的开源Java分布式缓存。主要面向通用缓存
```



要在程序中使用Ehcache,先要导包！

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis.caches/mybatis-ehcache -->
<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-ehcache</artifactId>
    <version>1.2.2</version>
</dependency>
```

在Mapper中指定使用我们的ehcache缓存实现！

```xml
<!--在当前Mapper.xml中使用二级缓存-->
<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
         updateCheck="false">

    <diskStore path="./tmpdir/Tmp_EhCache"/>

    <defaultCache
                  eternal="false"
                  maxElementsInMemory="10000"
                  overflowToDisk="false"
                  diskPersistent="false"
                  timeToIdleSeconds="1800"
                  timeToLiveSeconds="259200"
                  memoryStoreEvictionPolicy="LRU"/>

    <cache
           name="cloud_user"
           eternal="false"
           maxElementsInMemory="5000"
           overflowToDisk="false"
           diskPersistent="false"
           timeToIdleSeconds="1800"
           timeToLiveSeconds="1800"
           memoryStoreEvictionPolicy="LRU"/>
</ehcache>
```



Redis数据库来做缓存！ K-V



### 练习：29道练习题

------

数据库：

```sql
CREATE DATABASE smbms CHARACTER SET utf8 COLLATE utf8_general_ci;

/*
 Navicat Premium Data Transfer

 Source Server         : admin
 Source Server Type    : MySQL
 Source Server Version : 50556
 Source Host           : localhost:3306
 Source Schema         : smbms

 Target Server Type    : MySQL
 Target Server Version : 50556
 File Encoding         : 65001

 Date: 14/10/2018 19:35:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for smbms_address
-- ----------------------------
DROP TABLE IF EXISTS `smbms_address`;
CREATE TABLE `smbms_address`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `contact` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系人姓名',
    `addressDesc` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '收货地址明细',
    `postCode` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '邮编',
    `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系人电话',
    `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `creationDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '修改者',
    `modifyDate` datetime NULL DEFAULT NULL COMMENT '修改时间',
    `userId` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of smbms_address
-- ----------------------------
INSERT INTO `smbms_address` VALUES (1, '王丽', '北京市东城区东交民巷44号', '100010', '13678789999', 1, '2016-04-13 00:00:00', NULL, NULL, 1);
INSERT INTO `smbms_address` VALUES (2, '张红丽', '北京市海淀区丹棱街3号', '100000', '18567672312', 1, '2016-04-13 00:00:00', NULL, NULL, 1);
INSERT INTO `smbms_address` VALUES (3, '任志强', '北京市东城区美术馆后街23号', '100021', '13387906742', 1, '2016-04-13 00:00:00', NULL, NULL, 1);
INSERT INTO `smbms_address` VALUES (4, '曹颖', '北京市朝阳区朝阳门南大街14号', '100053', '13568902323', 1, '2016-04-13 00:00:00', NULL, NULL, 2);
INSERT INTO `smbms_address` VALUES (5, '李慧', '北京市西城区三里河路南三巷3号', '100032', '18032356666', 1, '2016-04-13 00:00:00', NULL, NULL, 3);
INSERT INTO `smbms_address` VALUES (6, '王国强', '北京市顺义区高丽营镇金马工业区18号', '100061', '13787882222', 1, '2016-04-13 00:00:00', NULL, NULL, 3);

-- ----------------------------
-- Table structure for smbms_bill
-- ----------------------------
DROP TABLE IF EXISTS `smbms_bill`;
CREATE TABLE `smbms_bill`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `billCode` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '账单编码',
    `productName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品名称',
    `productDesc` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品描述',
    `productUnit` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品单位',
    `productCount` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品数量',
    `totalPrice` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品总额',
    `isPayment` int(10) NULL DEFAULT NULL COMMENT '是否支付（1：未支付 2：已支付）',
    `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '创建者（userId）',
    `creationDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '更新者（userId）',
    `modifyDate` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `providerId` int(20) NULL DEFAULT NULL COMMENT '供应商ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of smbms_bill
-- ----------------------------
INSERT INTO `smbms_bill` VALUES (1, 'BILL2016_001', '洗发水、护发素', '日用品-洗发、护发', '瓶', 500.00, 25000.00, 2, 1, '2014-12-14 13:02:03', NULL, NULL, 13);
INSERT INTO `smbms_bill` VALUES (2, 'BILL2016_002', '香皂、肥皂、药皂', '日用品-皂类', '块', 1000.00, 10000.00, 2, 1, '2016-03-23 04:20:40', NULL, NULL, 13);
INSERT INTO `smbms_bill` VALUES (3, 'BILL2016_003', '大豆油', '食品-食用油', '斤', 300.00, 5890.00, 2, 1, '2014-12-14 13:02:03', NULL, NULL, 6);
INSERT INTO `smbms_bill` VALUES (4, 'BILL2016_004', '橄榄油', '食品-进口食用油', '斤', 200.00, 9800.00, 2, 1, '2013-10-10 03:12:13', NULL, NULL, 7);
INSERT INTO `smbms_bill` VALUES (5, 'BILL2016_005', '洗洁精', '日用品-厨房清洁', '瓶', 500.00, 7000.00, 2, 1, '2014-12-14 13:02:03', NULL, NULL, 9);
INSERT INTO `smbms_bill` VALUES (6, 'BILL2016_006', '美国大杏仁', '食品-坚果', '袋', 300.00, 5000.00, 2, 1, '2016-04-14 06:08:09', NULL, NULL, 4);
INSERT INTO `smbms_bill` VALUES (7, 'BILL2016_007', '沐浴液、精油', '日用品-沐浴类', '瓶', 500.00, 23000.00, 1, 1, '2016-07-22 10:10:22', NULL, NULL, 14);
INSERT INTO `smbms_bill` VALUES (8, 'BILL2016_008', '不锈钢盘碗', '日用品-厨房用具', '个', 600.00, 6000.00, 2, 1, '2016-04-14 05:12:13', NULL, NULL, 14);
INSERT INTO `smbms_bill` VALUES (9, 'BILL2016_009', '塑料杯', '日用品-杯子', '个', 350.00, 1750.00, 2, 1, '2016-02-04 11:40:20', NULL, NULL, 14);
INSERT INTO `smbms_bill` VALUES (10, 'BILL2016_010', '豆瓣酱', '食品-调料', '瓶', 200.00, 2000.00, 2, 1, '2013-10-29 05:07:03', NULL, NULL, 8);
INSERT INTO `smbms_bill` VALUES (11, 'BILL2016_011', '海之蓝', '饮料-国酒', '瓶', 50.00, 10000.00, 1, 1, '2016-04-14 16:16:00', NULL, NULL, 1);
INSERT INTO `smbms_bill` VALUES (12, 'BILL2016_012', '芝华士', '饮料-洋酒', '瓶', 20.00, 6000.00, 1, 1, '2016-09-09 17:00:00', NULL, NULL, 1);
INSERT INTO `smbms_bill` VALUES (13, 'BILL2016_013', '长城红葡萄酒', '饮料-红酒', '瓶', 60.00, 800.00, 2, 1, '2016-11-14 15:23:00', NULL, NULL, 1);
INSERT INTO `smbms_bill` VALUES (14, 'BILL2016_014', '泰国香米', '食品-大米', '斤', 400.00, 5000.00, 2, 1, '2016-10-09 15:20:00', NULL, NULL, 3);
INSERT INTO `smbms_bill` VALUES (15, 'BILL2016_015', '东北大米', '食品-大米', '斤', 600.00, 4000.00, 2, 1, '2016-11-14 14:00:00', NULL, NULL, 3);
INSERT INTO `smbms_bill` VALUES (16, 'BILL2016_016', '可口可乐', '饮料', '瓶', 2000.00, 6000.00, 2, 1, '2012-03-27 13:03:01', NULL, NULL, 2);
INSERT INTO `smbms_bill` VALUES (17, 'BILL2016_017', '脉动', '饮料', '瓶', 1500.00, 4500.00, 2, 1, '2016-05-10 12:00:00', NULL, NULL, 2);
INSERT INTO `smbms_bill` VALUES (18, 'BILL2016_018', '哇哈哈', '饮料', '瓶', 2000.00, 4000.00, 2, 1, '2015-11-24 15:12:03', NULL, NULL, 2);
INSERT INTO `smbms_bill` VALUES (20, 'BILL2016_020', '洗洁精', '日用品-厨房清洁', '瓶', 500.00, 7000.00, 2, 1, '2018-08-27 13:19:12', NULL, NULL, 9);
INSERT INTO `smbms_bill` VALUES (21, '123', '123', NULL, '个', 123.00, 123.00, 1, 1, '2018-09-26 21:38:08', NULL, NULL, 1);
INSERT INTO `smbms_bill` VALUES (23, '12312', '1231', NULL, '123', 5000.00, 123.00, 2, 1, '2018-09-26 21:46:39', 1, '2018-09-26 22:26:30', 18);

-- ----------------------------
-- Table structure for smbms_provider
-- ----------------------------
DROP TABLE IF EXISTS `smbms_provider`;
CREATE TABLE `smbms_provider`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `proCode` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '供应商编码',
    `proName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '供应商名称',
    `proDesc` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '供应商详细描述',
    `proContact` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '供应商联系人',
    `proPhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
    `proAddress` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '地址',
    `proFax` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '传真',
    `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '创建者（userId）',
    `creationDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `modifyDate` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '更新者（userId）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of smbms_provider
-- ----------------------------
INSERT INTO `smbms_provider` VALUES (1, 'BJ_GYS001', '北京三木堂商贸有限公司', '长期合作伙伴，主营产品:茅台、五粮液、郎酒、酒鬼酒、泸州老窖、赖茅酒、法国红酒等', '张国强', '13566667777', '北京市丰台区育芳园北路', '010-58858787', 1, '2013-03-21 16:52:07', NULL, NULL);
INSERT INTO `smbms_provider` VALUES (4, 'GZ_GYS002', '深圳市喜来客商贸有限公司', '长期合作伙伴，主营产品：坚果炒货.果脯蜜饯.天然花茶.营养豆豆.特色美食.进口食品.海味零食.肉脯肉', '林妮1', '18599897645', '广东省深圳市福龙工业区B2栋3楼西', '0755-67772341', 1, '2013-03-22 16:52:07', '2018-09-24 21:28:07', 1);
INSERT INTO `smbms_provider` VALUES (8, 'ZJ_GYS001', '慈溪市广和绿色食品厂', '长期合作伙伴，主营产品：豆瓣酱、黄豆酱、甜面酱，辣椒，大蒜等农产品', '薛圣丹1', '18099953223', '浙江省宁波市慈溪周巷小安村', '0574-34449090', 1, '2013-11-21 06:02:07', '2018-09-28 19:54:31', 1);
INSERT INTO `smbms_provider` VALUES (9, 'GX_GYS001', '优百商贸有限公司', '长期合作伙伴，主营产品：日化产品', '李立国', '13323566543', '广西南宁市秀厢大道42-1号', '0771-98861134', 1, '2013-03-21 19:52:07', NULL, NULL);
INSERT INTO `smbms_provider` VALUES (18, '12', '234', '123', '234', '15815891967', '213', '123', 1, '2018-09-26 12:55:42', NULL, NULL);

-- ----------------------------
-- Table structure for smbms_role
-- ----------------------------
DROP TABLE IF EXISTS `smbms_role`;
CREATE TABLE `smbms_role`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `roleCode` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色编码',
    `roleName` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
    `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `creationDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '修改者',
    `modifyDate` datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of smbms_role
-- ----------------------------
INSERT INTO `smbms_role` VALUES (1, 'SMBMS_ADMIN', '系统管理员', 1, '2016-04-13 00:00:00', NULL, NULL);
INSERT INTO `smbms_role` VALUES (2, 'SMBMS_MANAGER', '经理', 1, '2016-04-13 00:00:00', NULL, NULL);
INSERT INTO `smbms_role` VALUES (3, 'SMBMS_EMPLOYEE', '普通员工', 1, '2016-04-13 00:00:00', NULL, NULL);
INSERT INTO `smbms_role` VALUES (5, '11', '测试员', 1, '2016-04-13 00:00:00', NULL, NULL);
INSERT INTO `smbms_role` VALUES (7, 'SMBMS_MANA', '董事长', 1, '2018-09-30 20:11:48', 1, '2018-10-08 13:53:02');

-- ----------------------------
-- Table structure for smbms_user
-- ----------------------------
DROP TABLE IF EXISTS `smbms_user`;
CREATE TABLE `smbms_user`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `userCode` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户编码',
    `userName` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名称',
    `userPassword` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户密码',
    `gender` int(10) NULL DEFAULT NULL COMMENT '性别（1:女、 2:男）',
    `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
    `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '手机',
    `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '地址',
    `userRole` int(10) NULL DEFAULT NULL COMMENT '用户角色（取自角色表-角色id）',
    `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '创建者（userId）',
    `creationDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '更新者（userId）',
    `modifyDate` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `idPicPath` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '证件照路径',
    `workPicPath` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '工作证照片路径',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of smbms_user
-- ----------------------------
INSERT INTO `smbms_user` VALUES (1, 'admin', '系统管理员', '1234567', 1, '1983-10-28', '13688889999', '北京市海淀区成府路207号', 1, 1, '2013-03-21 16:52:07', 1, '2018-09-28 13:21:12', NULL, NULL);
INSERT INTO `smbms_user` VALUES (2, 'liming', '李明', '0000000', 2, '1983-12-10', '13688884457', '北京市东城区前门东大街9号', 2, 1, '2013-03-21 00:00:00', NULL, NULL, NULL, NULL);
INSERT INTO `smbms_user` VALUES (5, 'hanlubiao', '韩路彪', '0000000', 2, '1984-06-05', '18567542321', '北京市朝阳区北辰中心12号', 2, 1, '2014-12-31 19:52:09', NULL, NULL, NULL, NULL);
INSERT INTO `smbms_user` VALUES (6, 'zhanghua', '张华', '0000000', 1, '1983-06-15', '13544561111', '北京市海淀区学院路61号', 3, 1, '2013-02-11 10:51:17', NULL, NULL, NULL, NULL);
INSERT INTO `smbms_user` VALUES (7, 'wangyang', '王洋', '0000000', 2, '1982-12-31', '13444561124', '北京市海淀区西二旗辉煌国际16层', 3, 1, '2014-06-11 19:09:07', NULL, NULL, NULL, NULL);
INSERT INTO `smbms_user` VALUES (10, 'sunlei', '孙磊', '0000000', 2, '1981-01-30', '13387676765', '北京市朝阳区管庄新月小区12楼', 2, 1, '2015-05-06 10:52:07', 1, '2018-09-28 13:20:56', NULL, NULL);
INSERT INTO `smbms_user` VALUES (14, 'yangguo', '杨过', '0000000', 2, '1980-01-01', '13388886623', '北京市朝阳区北苑家园茉莉园20号楼', 3, 1, '2015-02-01 03:52:07', NULL, NULL, NULL, NULL);
INSERT INTO `smbms_user` VALUES (39, 'ass', '邓振良', '1231231', 2, '2018-09-10', '15815891967', '北大青鸟', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `smbms_user` VALUES (47, '测试1', '测试1', '123123123', 1, '2018-10-23', '15815891967', '测试1', 5, 1, '2018-09-29 14:01:46', 1, '2018-09-30 17:27:26', '1538201180302_Personal.jpg', '1538201110952_Personal.jpg');
INSERT INTO `smbms_user` VALUES (48, 'admin1', '张晨12', '123123123', 1, '2018-09-28', '15815891967', '测试1', 5, 1, '2018-09-29 16:15:34', NULL, NULL, '1538209618900_Personal.jpg', '1538209900746_Personal.jpg');
INSERT INTO `smbms_user` VALUES (49, 'aa', '1231', '123123123', 1, '2018-09-12', '15815189167', '北大青鸟', 5, 1, '2018-09-29 17:19:54', NULL, NULL, '1538213548680_Personal.jpg', '1538213001444_Personal.jpg');
INSERT INTO `smbms_user` VALUES (50, '测试11111', '12312312', '123123123', 1, '2018-10-09', '15815891967', '测试1', 7, 1, '2018-10-08 14:03:44', NULL, NULL, '1538979518768_Personal.jpg', '1538978729725_Personal.jpg');
INSERT INTO `smbms_user` VALUES (51, 'ADMINs', 'huaa', '1231231', 1, '2018-10-08', '15815891967', '测试1', 3, 1, '2018-10-08 14:21:02', NULL, NULL, '1538979676349_Personal.jpg', '1538979979076_Personal.jpg');

SET FOREIGN_KEY_CHECKS = 1;

```



接口：

BillMapper.jav

```java
public interface BillMapper {
    //根据供应商ID查询订单数量
    public int getBillCountByProviderId(@Param("providerId") Integer providerId);

    //增加订单
    public int add(Bill bill);

    //通过查询条件获取供应商列表-getBillList
    public List<Bill> getBillList(@Param("productName") String productName, @Param("providerId") Integer providerId, @Param("isPayment") Integer isPayment, @Param("from") Integer currentPageNo, @Param("pageSize") Integer pageSize);

    //通过条件查询-订单表记录数
    public int getBillCount(@Param("productName") String productName, @Param("providerId") Integer providerId, @Param("isPayment") Integer isPayment);

    //通过delId删除Bill
    public int deleteBillById(@Param("id") Integer delId);

    //通过billId获取Bill
    public Bill getBillById(@Param("id") Integer id);

    //修改订单信息
    public int modify(Bill bill);

    //根据供应商ID删除订单信息
    public int deleteBillByProviderId(@Param("providerId") Integer providerId);
}
```

```java
public interface RoleMapper {
    //获取角色列表
    public List<Role> getRoleList();
    //增加角色信息
    public int add(Role role);
    //通过ID删除role
    public int deleteRoleById(@Param("id") Integer delId);
    //修改角色信息
    public int modify(Role role);
    //通过Id获取role
    public Role getRoleById(@Param("id") Integer id);
    //根据RoleCode，进行角色编码得唯一性验证
    public int roleCodeIsExist(@Param("roleCode") String roleCode);
}
```

```java
public interface ProviderMapper {

    //通过条件查询供应商列表
    List<Provider> getProviderList(@Param("proCode")String proCode, @Param("proName")String proName,
                                   @Param("from") Integer currentPageNo, @Param("pageSize")Integer pageSize);

    //增加用户信息
    int add(Provider provider);

    //获得供应商list
    List<Provider> getList();

    //通过条件查询供应商表记录数
    int getProCount(@Param("proName") String proName,@Param("proCode")String proCode);

    //通过id删除信息
    int deleteProById(@Param("id") Integer delId);

    //根据provider id获取供应商信息
    Provider getProById(@Param("id") Integer id);

    //修改供应商
    int modify(Provider provider);
}
```

```java
public interface UserMapper {
    //通过userCode获取User
    public User getLoginUser(@Param("userCode") String userCode);

    //增加用户信息
    public int add(User user);

    //通过条件查询userList
    public List<User> getUserList(@Param("userName") String userName,@Param("userRole") Integer userRole,@Param("from") Integer currentPageNo,@Param("pageSize") Integer pageSize);

    //通过条件查询-用户表记录数
    public int getUserCount(@Param("userName") String userName,@Param("userRole") Integer userRole);

    //通过userId删除user
    public int deleteUserById(@Param("id") Integer delId);

    //通过userId获取user
    public User getUserById(@Param("id") Integer id);

    //修改用户信息
    public int modify(User user);

    //修改当前用户密码
    public int updatePwd(@Param("id") Integer id,@Param("userPassword") String pwd);
}
```

```java
package com.wxy.pojo;

import java.util.Date;

public class User {
    private Integer id; // id
    private String userCode; // 用户编码
    private String userName; // 用户名称
    private String userPassword; // 用户密码
    private Integer gender; // 性别
    private Date birthday; // 出生日期
    private String phone; // 电话
    private String address; // 地址
    private Integer userRole; // 用户角色
    private Integer createdBy; // 创建者
    private Date creationDate; // 创建时间
    private Integer modifyBy; // 更新者
    private Date modifyDate; // 更新时间
    private Integer age;// 年龄
    private String userRoleName; // 用户角色名称

    private String idPicPath; // 证件照路径

    private String workPicPath; // 工作证照片路径

    public String getIdPicPath() {
        return idPicPath;
    }

    public void setIdPicPath(String idPicPath) {
        this.idPicPath = idPicPath;
    }

    public String getWorkPicPath() {
        return workPicPath;
    }

    public void setWorkPicPath(String workPicPath) {
        this.workPicPath = workPicPath;
    }

    public User() {
    }

    public User(Integer id, String userCode, String userName, String userPassword, Integer gender, Date birthday,
                String phone, String address, Integer userRole, Integer createdBy, Date creationDate, Integer modifyBy,
                Date modifyDate) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userPassword = userPassword;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.userRole = userRole;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public Integer getAge() {
        /*
         * long time = System.currentTimeMillis()-birthday.getTime(); Integer age =
         * Long.valueOf(time/365/24/60/60/1000).IntegerValue();
         */
        Date date = new Date();
        Integer age = date.getYear() - birthday.getYear();
        return age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}

```

```java
package com.wxy.pojo;

import java.util.Date;

public class Role {
    private Integer id; // id
    private String roleCode; // 角色编码
    private String roleName; // 角色名称
    private Integer createdBy; // 创建者

    private Date creationDate; // 创建时间
    private Integer modifyBy; // 更新者

    private Date modifyDate;// 更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
```

```java
package com.wxy.pojo;

import java.util.Date;

public class Provider {
    private Integer id; // id
    private String proCode; // 供应商编码
    private String proName; // 供应商名称
    private String proDesc; // 供应商描述
    private String proContact; // 供应商联系人
    private String proPhone; // 供应商电话
    private String proAddress; // 供应商地址
    private String proFax; // 供应商传真
    private Integer createdBy; // 创建者
    private Date creationDate; // 创建时间
    private Integer modifyBy; // 更新者
    private Date modifyDate;// 更新时间

    public Integer getId() {
        return id;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProContact() {
        return proContact;
    }

    public void setProContact(String proContact) {
        this.proContact = proContact;
    }

    public String getProPhone() {
        return proPhone;
    }

    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }

    public String getProAddress() {
        return proAddress;
    }

    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
    }

    public String getProFax() {
        return proFax;
    }

    public void setProFax(String proFax) {
        this.proFax = proFax;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
```

```java
package com.wxy.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class Bill {
    private Integer id;   //id
    private String billCode; //账单编码
    private String productName; //商品名称
    private String productDesc; //商品描述
    private String productUnit; //商品单位
    private BigDecimal productCount; //商品数量
    private BigDecimal totalPrice; //总金额
    private Integer isPayment; //是否支付
    private Integer providerId; //供应商ID
    private Integer createdBy; //创建者
    private Date creationDate; //创建时间
    private Integer modifyBy; //更新者
    private Date modifyDate;//更新时间

    private String providerName;//供应商名称


    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public BigDecimal getProductCount() {
        return productCount;
    }

    public void setProductCount(BigDecimal productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(Integer isPayment) {
        this.isPayment = isPayment;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
```

