### 1、什么是MVC

------

- MVC是模型(Model dao,service)、视图(View)、控制器(Controller)的简写，是一种软件设计规范
- 是将业务逻辑、数据、显示分离的方法来组织代码。
- MVC主要作用是降低了视图与业务逻辑间的双向偶合。
- MVC不是一种设计模式，MVC是一种架构模式。当然不同的MVC存在差异。



Model(**模型**)：数据模型，提供要展示的数据，因此包含数据和行为，可以认为是领域模型或JavaBean组件（包含数据和行为），不过现在一般都分离开来：Value Object(数据Dao)和服另层（行为Service)。也就是模型提供了模型数据查询和模型数据的状态更新等功能，包括数据和业务。

View(**视图**)：负责进行模型的展示，一般就是我们见到的用户界面，客户想看到的东西。

Controller(**控制器**)：接收用户请求，委托给模型进行处理（状态改变），处理完毕后把返回的模型数据返回给视图，由视图负责展示。也就是说控制器做了个调度员的工作。

**最典型的MVC就是JSP+servlet+javabean的模式。**

![image-20220402101029831](https://gitee.com/linda12138/picgo/raw/master/image/image-20220402101029831.png)

> Model1时代

- 在web早期的开发中，通常采用的都是Model1
- Model1中，主要分为两层，**视图层和模型层**

![image-20220402101634226](https://gitee.com/linda12138/picgo/raw/master/image/image-20220402101634226.png)

Model1优点：架构简单，比较适合小型项目开发；

Model1缺点：JSP职责不单一，职责过重，不便于维护；



> Model2时代

Model2把一个项目分成三部分，包括**视图、控制、模型。**

![image-20220402102356937](https://gitee.com/linda12138/picgo/raw/master/image/image-20220402102356937.png)



1.用户发请求

2.Servlet接收请求数据，并调用对应的业务逻辑方法

3.业务处理完毕，返回更新后的数据给servlet

4.servlet转向到JSP,由JSP来渲染页面

5.响应给前端更新后的页面



**职责分析：**



**Controller:控制器**

1. 取得表单数据
2. 调用业务逻辑
3. 转向指定的页面



**Model:模型**

1. 业务逻辑
2. 保存数据的状态



**View:视图**

1. 显示页面

​	Model2这样不仅提高的代码的复用率与项目的扩展性，且大大降低了项目的维护成本。Model1模式的实现比较简单，适用于快速开发小规模项目，Model1中JSP页面身兼View和Controlleri两种角色，将控制逻辑和表现逻辑混杂在一起，从而导致代码的重用性非常低，增加了应用的扩展性和维护的难度。Model2消除了Model1的缺点。



**面试【可能】**

假设：你的项目的架构，是设计好的，还是**演进**的？

- Alibaba  PHP
- 随着用户量大，Java
- 王坚 去 IOE **Mysql**
- MySQL: mysql-->AliSQL、AliRedis
- All in one-->微服务



### 2、回顾Servlet

------

1.  新建一个Maven工程当作父工程！pom依赖！

   ```xml
   <dependencies>
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>3.8.2</version>
       </dependency>
       <dependency>
           <groupId>javax.servlet</groupId>
           <artifactId>servlet-api</artifactId>
           <version>2.5</version>
       </dependency>
       <dependency>
           <groupId>javax.servlet.jsp.jstl</groupId>
           <artifactId>jstl-api</artifactId>
           <version>1.2</version>
       </dependency>
       <dependency>
           <groupId>javax.servlet</groupId>
           <artifactId>jstl</artifactId>
           <version>1.2</version>
       </dependency>
       <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-webmvc</artifactId>
           <version>5.3.18</version>
       </dependency>
   </dependencies>
   ```

   

2. 建立一个Moudle:springmvc-01-servlet,添加Web app的支持！

3. 导入servlet和jsp的jar依赖

   ```xml
   <dependency>
       <groupId>javax.servlet</groupId>
       <artifactId>servlet-api</artifactId>
       <version>2.5</version>
   </dependency>
   <dependency>
       <groupId>javax.servlet.jsp</groupId>
       <artifactId>jsp-api</artifactId>
       <version>2.1</version>
   </dependency>
   ```

   

4. 编写一个Servlet，用来处理用户请求

   ```java
   package com.peng.servlet;
   
   import javax.servlet.ServletException;
   import javax.servlet.http.HttpServlet;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   import java.io.IOException;
   
   public class HelloServlet extends HttpServlet {
       @Override
       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           //获取前端参数
           String method = req.getParameter("method");
           if (method.equals("add")){
               req.getSession().setAttribute("msg","执行了add方法");
           }
           if (method.equals("delete")){
               req.getSession().setAttribute("msg","执行了delete方法");
           }
           //        调用业务层
           //        视图转发或者重定向
           req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req,resp);
       }
   
       @Override
       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           doGet(req, resp);
       }
   }
   ```

   

5. 编写Hello.jsp,在WEB-INF目录下新建一个jsp的文件夹，新建hello.jsp

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
       <head>
           <title>Title</title>
       </head>
       <body>
           ${msg}
       </body>
   </html>
   ```

   

6. 在web.xml中注册Servlet

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
       <servlet>
           <servlet-name>hello</servlet-name>
           <servlet-class>com.peng.servlet.HelloServlet</servlet-class>
       </servlet>
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello</url-pattern>
       </servlet-mapping>
   
       <!--    <session-config>-->
       <!--        <session-timeout>15</session-timeout>-->
       <!--    </session-config>-->
       <!--    <welcome-file-list>-->
       <!--        <welcome-file>index.jsp</welcome-file>-->
       <!--    </welcome-file-list>-->
   </web-app>
   ```

   

7. 配置Tomcat，并启动测试

   http://localhost:8080/hello?method=add

   http://localhost:8080/hello?method=delete

**MVC框架要做哪些事情**

1. 将url映射到Java类或Java类的方法
2. 封装用户提交的数据
3. 处理请求--调用相关的业务处理--封装响应数据
4. 将响应的数据进行渲染.jsp/html等表示层数据

**说明**：

​	常见的服务器端MVC框架有：Struts、Spring MVC、ASP.NET MVC、Zend Framework、JSF;常见前端MVC框架：vue、angularjs、react、backbone;由MVC演化出了另外一些模式如：MVP、MVVM等等......



### 3、什么是SpringMVC

------

> 概述

Spring MVC是Spring Framework的一部分，是基于java实现MVC的轻量级Web框架。

查看官方文档：https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#spring-web

​	

**我们为什么要学习SpringMVC呢？**

SpringMVC特点：

1. 轻量级，简单易学
2. 高效，基于请求响应的MVC框架
3. 与Spring兼容性好，无缝结合
   - Spring:大杂烩，我们可以将SpringMVC中所有要用到的bean,注册到Spring中！
4. 约定优于配置
5. 功能强大：RESTful、数据验证、格式化、本地化、主题等
6. 简洁灵活

​	

​	Spring的web框架围绕**DispatcherServlet**【调度Servlet】设计。

​	DispatcherServlet的作用是将请求分发到不同的处理器。从Spring2.5开始，使用Java5或者以上版本的用户可以采用基于注解形式进行开发，十分简洁：

​	正因为SpringMVC好，简单，便捷，易学，天生和Spring无缝集成（使用SpringloC和Aop),使用约定优于配置.能够进行简单的juniti测试，支持Restfull风格.异常处理，本地化，国际化，数据验证，类型转换，拦截器等等....所以我们要学习

​	**最重要的一点还是用的人多，使用的公司多**



> 中心控制器



​	Springl的web框架围绕DispatcherServlet设计。DispatcherServlet的作用是将请求分发到不同的处理器。从Spring2.5开始，使用Java5或者以上版本的用户可以采用基于注解的controller声明方式。

​	Spring MVC框架像许多其他MVC框架一样，**以请求为驱动，围绕一个中心Servlet:分派请求及提共其他功能，DispatcherServlet是一个实际的Servlet(它继承自HttpServlet基类)。**

![image-20220402155139178](https://gitee.com/linda12138/picgo/raw/master/image/image-20220402155139178.png)



SpringMVC的原理如下图所示：

​	当发起请求时被前置的控制器拦截到请求，根据请求参数生成代理请求，找到请求对应的实际控制器，控制器处理请求，创建数据模型，访问数据库，将模型响应给中心控制器，控制器使用模型与视图渲染视图结果，将结果返回给中心控制器，再将结果返回给请求者。

![image-20220402160825796](https://gitee.com/linda12138/picgo/raw/master/image/image-20220402160825796.png)

> SpringMVC执行原理

![image-20220402165848557](https://gitee.com/linda12138/picgo/raw/master/image/image-20220402165848557.png)

​	图为SpringMVC的一个较完整的流程图，实线表示SpringMVC框架提供的技术，不需要开发者实现，虚线表示需要开发者实现。

**简要分析执行流程**

1. DispatcherServlet表示前置控制器，是整个SpringMVC的控制中心。用户发出请求，DispatcherServlet接收请求并拦截请求。
   - 我们假设请求的url为：http://localhost:8080//SpringMVC/hello
   - **如上url拆分成三部分：**
   - http://localhost:8080服务器域名
   - SpringMVC部署在服务器上的web站点
   - hello表示控制器
   - 通过分析，如上url表示为：请求位于服务器localhost:8080上的SpringMVC站点的hello控制器。
2. HandlerMapping为处理器映射。DispatcherServlet调用HandlerMapping,HandlerMapping根据请求url查找Handler。
3. HandlerExecution表示具体的Hand|er,其主要作用是根据url查找控制器，如上url被查找控制器为：hello。
4. HandlerExecution将解析后的信息传递给DispatcherServlet,如解析控制器映射等。
5. HandlerAdapter表示处理器适配器，其按照特定的规则去执行Handler。
6. Handler让具体的Controller执行。
7. Controller将具体的执行信息返回给HandlerAdapter,如ModelAndView。
8. HandlerAdapter将视图逻辑名或模型传递给DispatcherServlet。
9. DispatcherServlet调用视图解析器(ViewResolver)来解析HandlerAdapter传递的逻辑视图名。
10. 视图解析器将解析的逻辑视图名传给DispatcherServlet。
11. DispatcherServlet根据视图解析器解析的视图结果，调用具体的视图。
12. 最终视图呈现给用户。

### 4、HelloSpringMVC

------

> 配置版

1. 新建一个Moudle,添加web的支持

2. 确定导入了SpringMVC的依赖

3. 配置web.xml,注册DispatcherServlet

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
       <!--注册DispatcherServlet：这个是SpringMVC的核心：请求分发器，前端控制器-->
       <servlet>
           <servlet-name>springmvc</servlet-name>
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
           <!--关联一个springmvc的配置文件：【servlet-name】-servlet.xml-->
           <init-param>
               <param-name>contextConfigLocation</param-name>
               <param-value>classpath:springmvc-servlet.xml</param-value>
           </init-param>
           <!--启动级别1-->
           <load-on-startup>1</load-on-startup>
       </servlet>
       <!-- /匹配所有的请求；（不包括.jsp）-->
       <!-- /*匹配所有的请求；（包括.jsp）-->
       <servlet-mapping>
           <servlet-name>springmvc</servlet-name>
           <url-pattern>/</url-pattern>
       </servlet-mapping>
   </web-app>
   ```

   

4. 编写SpringMVC的配置文件！名称：springmvc-servlet.xml：【servletname】-servlet.xml 说明，这里的名称要求是按照官方来的

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd">
   
   </beans>
   ```

   

5. 添加 处理映射器

   ```xml
   <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
   ```

   

6. 添加 处理器适配器

   ```xml
     <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
   ```

   

7. 添加 视图解析器

   ```xml
   <!--视图解析器：DispatcherServlet给他的ModelAndView
    获取了ModelAndView的数据
       解析ModelAndView的视图名字
       拼接视图名字，找到对应的视图 /WEB-INF/jsp/hello.jsp
       将数据渲染到视图上
   -->
   <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
       <!--前缀-->
       <property name="prefix" value="/WEB-INF/jsp/"/>
       <property name="suffix" value=".jsp"/>
   </bean>
   ```

   

8. 编写我们要操作业务Controller,要么实现Controller接口，要么增加注解；需要返回一个ModelAndView,装数据，封视图；

   ```java
   package com.peng.controller;
   
   import org.springframework.web.servlet.ModelAndView;
   import org.springframework.web.servlet.mvc.Controller;
   
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   
   public class HelloController implements Controller {
       @Override
       public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
           //ModelAndView 模型和视图
           ModelAndView mv = new ModelAndView();
           //业务代码
           String result = "helloSpringMVC";
           //封装对象，放在ModelAndView中，model
           mv.addObject("msg",result);
           //封装要跳转的视图，放在ModelAndView中
           mv.setViewName("hello");//:/WEB-INF/jsp/hello.jsp
           return mv;
       }
   }
   ```

   

9. 将自己的类交给SpringIOC容器，注册bean

   ```xml
   <!--Handler-->
   <bean id="/hello" class="com.peng.controller.HelloController"/>
   ```

   

10. 要跳转的jsp页面，显示ModelAndView存放的数据，以及我们的正常页面

    ```jsp
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
        <head>
            <title>Title</title>
        </head>
        <body>
            ${msg}
        </body>
    </html>
    ```

    

11. 配置tomcat,启动测试



**可能遇到的问题：访问出现404，排查步骤：**

1. 查看控制台输出，看一下是不是缺少了什么jar包

2. 如果jar包存在，显示无法输出，就在IDEA的项目发布中，添加lib依赖

3. 重启Tomcat即可解决！

   ![image-20220402165048834](https://gitee.com/linda12138/picgo/raw/master/image/image-20220402165048834.png)

​	小结：看这个估计大部分同学都能理解其中的原理了，但是我们实际开发才不会这么写，不然就疯了，还学这个玩意干嘛！我们来看个注解版实现，这才是SpringMVC的精髓，到底有多么简单，看这个图就知道了。





> **注解版**

**第一步：新建一个Moudle，添加web支持！建立包结构com.peng.controller**

**第二步：由于Maven可能存在资源过滤的问题，我们将配置完善**

```xml
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

**第三步：在pom.xml文件引入相关的依赖：**

主要有Spring框架核心库、SpringMVC、servlet、JSTL等。我们在父依赖中已经引入了！

**第四步：配置web.xml**

注意点：

- 注意web.xml版本问题，要最新版！
- 注册DispatcherServlet
- 关联SpringMVC的配置文件
- 启动级别为1
- 映射路径为 / 【不要用 /*,会404】

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--注册DispatcherServlet：这个是SpringMVC的核心：请求分发器，前端控制器-->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--关联一个springmvc的配置文件：【servlet-name】-servlet.xml-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>
        <!--启动级别1-->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <!-- /匹配所有的请求；（不包括.jsp）-->
    <!-- /*匹配所有的请求；（包括.jsp）-->
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```



第五步：添加SpringMVC配置文件

- 让IOC的注解生效
- 静态资源过滤：HTML、JS、CSS、图片、视频....
- MVC的注解驱动
- 配置视图解析器



​	在resource目录下添加springmvc-servlet.xml配置文件，配置的形式与Spring容器配置基本类似，为了支持基于注解的OC,设置了自动扫描包的功能，具体配置信息如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context  https://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--自动扫描包，让指定包下的注解生效，由IOC容器统一管理-->
    <context:component-scan base-package="com.peng.controller"/>
    <!-- 让SpringMVC不处理静态资源-->
    <mvc:default-servlet-handler/>
    <!--
支持mvc注解驱动
    在spring中一般采用@RequestMapping注解来完成映射关系
    要想使@RequestMapping注解生效
    必须向上下文注册DefaultAnnotationHandlerMapping
    和一个AnnotationMethodHandlerAdapter实例
    这两个实例分别在类级别和方法级别处理
    而annotation-driven配置帮助我们自动完成上述两个实例的注入    
-->
    <mvc:annotation-driven/>

    <!-- 视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
        <!-- 前缀-->
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <!-- 后缀-->
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```

​	在视图解析器中我们把所有的视图都存放在/WEB-NF/目录下，这样可以保证视图安全，因为这个目录下的文件，客户端不能直接访问。

**第六步：创建Controller**

编写一个Java控制类：com.peng.contriller.HelloController,注意编码规范

```java
package com.peng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/hello")
public class HelloController {
    //localhost:8080/hello/h1
    @RequestMapping("/h1")
    public String hello(Model model){
        //    封装数据
        model.addAttribute("msg","hello,SpringMVCAnnotation!");

        return "hello"; //会被视图解释器处理；
    }
}
```

- @Controller是为了让Spring lOC容器初始化时自动扫描到；
- @RequestMapping是为了映射请求路径，这里因为类与方法上都有映射所以访问时应该/HelloController/hello;
- 方法中声明Mode|类型的参数是为了把Action中的数据带到视图中
- 方法返回的结果是视图的名称hello,加上配置文件中的前后缀变成WEB-INF/jsp./hello,jsp。



**第七步：创建视图层**

在WEB-lNF/jsp目录中创建hello.jsp,视图可以直接取出并展示从Controller带回的信息；可以通过EL表示取出Model中存放的值，或者对象；

```xml
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
        <head>
            <title>Title</title>
        </head>
        <body>
            ${msg}
        </body>
    </html>
```



**第八步：配置Tomcat运行**



> 小结

1. 新建一个web项目
2. 导入相关jar包
3. 编写web.xml,注册DispatcherServlet
4. 编写springmvc配置文件
5. 接下来就是去创建对应的控制类，controller
6. 最后完善前端视图和Controller之间的对应
7. 测试运行调试



​	使用SpringMVC必须配置三大件：

​	**处理器映射器、处理器适配器、视图解析器**

​	通常，我们只需要**手动配置视图解析器**，而**处理器映射器**和**处理器适配器**只需要开启**注解驱动**即可，而省去了大段的xml配置



### 5、控制器Controller

------

- 控制器复杂提供访问应用程序的行为，通常通过接口定义或注解定义两种方法实现。

- 控制器负责解析用户的请求并将其转换为一个模型。

- 在Spring MVC中一个控制器类可以包含多个方法

- 在Spring MVC中，对于Controller的配置方式有很多种

  

  我们来看看有哪些方式可以实现：

> 实现Controller接口

​	Controller是一个接口，在org.springframework..web.servlet.mvc包下，接口中只有一个方法：

```java
//实现该接口的类获得控制器功能
public interface Controller {
    @Nullable
    //处理请求且返回一个模型与视图对象
    ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
```

**测试**

1. 新建一个Moudle

2. 编写一个Controller类，ContrillerTest1

   ```java
   //只要实现了Controller接口的类，说明这就是一个控制器了
   public class ContrillerTest1 implements Controller {
       @Override
       public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
           ModelAndView mv = new ModelAndView();
           mv.addObject("msg","controllertest1");
           mv.setViewName("test");
           return mv;
       }
   }
   ```

   

3. 编写完毕后，去Spring配置文件中注册请求的bean;name对应请求路径，class对应处理请求的类

   ```xml
   <bean name="/t1" class="com.peng.controller.ContrillerTest1"/>
   ```

   

4. 视图层

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
       <head>
           <title>Title</title>
       </head>
       <body>
           ${msg}
       </body>
   </html>
   ```

   

5. 配置tomcat运行测试

说明：

- 实现接口Controller定义控制器是较老的办法
- 缺点是：一个控制器只有一个方法，如果要多个方法则需要定义多个Controller;定义的方式比较麻烦

> 使用注解@Controller

- @Controlleri注解类型用于声明Spring类的实例是一个控制器（在讲IOC时还提到了另外3个注解)；

- Spring可以使用扫描机制来找到应用程序中所有基于注解的控制器类，为了保证Spring能找到你的控制器，需要在配置文件中声明组件扫描。

  ```xml
  <!--&lt;!&ndash;自动扫描包，让指定包下的注解生效，由IOC容器统一管理&ndash;&gt;-->
  <context:component-scan base-package="com.peng.controller"/>
  ```

- 增加一个ControllerTest2类，使用注解实现

  ```java
  @Controller
  //代表这个类会被Spring接管
  //被这个注解的类中的所有方法，如果返回值是Spring,并且有具体页面可以跳转，那么就会被视图解析器解析
  public class ControllerTest2 {
    //映射访问路径  
      @RequestMapping("/t2")
      public String test(Model model){
          //SpringMVC 会自动实例化一个Model对象用于向视图中传值
          model.addAttribute("msg","ControllerTest2");
          //返回视图位置
          return "test";
      }
  }
  ```

- 运行测试



​	**可以发现，我们的两个请求都可以指向一个视图，但是页面结果的结果是不一样的，从这里可以看出视图是被复用的，而控制器与视图之间是弱偶合关系。**



### 6、RequestMapping

------

> @RequestMapping

- @RequestMapping注解用于映射url到控制器类或一个特定的处理程序方法。可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。

- 为了测试结论更加准确，我们可以加上一个项目名测试myweb

- 只注解在方法上面

  ```java
  @Controller
  public class ControllerTest3 {
      @RequestMapping("/t1")
     public String test1(){
         return "test";
     }
  }
  ```

  访问路径：http://localhost:8080/c3/t1

- 同时注解类与方法

  ```java
  @Controller
  @RequestMapping("/c3")
  public class ControllerTest3 {
      @RequestMapping("/t1")
     public String test1(){
         return "test";
     }
  }
  ```



> RestFul风格

**概念**

​	Restful就是一个资源定位及资源操作的风格。不是标准也不是协议，只是一种风格。基于这个风格设计的软件可以更简洁，更有层次，更易于实现缓存等机制。



**功能**

- 资源：互联网所有的市物都可以被抽象为资源

- 资源操作：使用POST、DELETE、PUT、GET,使用不同方法对资源进行操作。

- 分别对应添加、删除、修改、查询。

  传统方式操作资源：通过不同的参数来实现不同的效果！方法单一，post和get

- http://127.0.0.1/item/queryltem.action?id=1 查询，GET

- http://127.0.0.1item/saveltem.action 新增 POST

- http://127.0.0.1/item/updateltem.action 更新，PoST

- http://127.0.0.1/item/deleteltem.action?id=1 删除，GET或POsT

  ​	**使用RESTfu操作资源**：可以通过不同的请求方式来实现不同的效果！如下：请求地址一样，但是功能可以不同！

- http://127.0.0.1/item/1 查询，GET

- http://127.0.0.1/item 新建，POST

- http://127.0.0.1/item 更新，PUT

- http://127.0.0.1/item/1 删除，DELETE

**学习测试**

1. 新建一个类RestFulController

   ```java
   @Controller
   public class RestFulController {
       public String test1(){
           return "test";
       }
   }
   ```

   

2. 在SpringMVC中可以使用@PathVariable注解，让方法参数的值对应绑定到一个URL模板变量上

   ```java
   @Controller
   public class RestFulController {
       //原来的：http://localhost:8080/add?a=1&b=2
       //RestFul:http://localhost:8080/add/a/b
       @RequestMapping("/add/{a}/{b}")
       public String test1(@PathVariable int a, @PathVariable int b, Model model){
           int res = a+b;
           model.addAttribute("msg","结果为"+res);
           return "test";
       }
   }
   ```

   

3. 测试查看



`思考：使用路径变量的好处？`

- 使路径变得更加简洁
- 获得参数更加方便，框架会自动进行类型转换。
- 通过路径变量的类型可以约束访问参数，如果类型不一样，则访问不到对应的请求方法，如这里访问是的路径是/commit/1/a,则路径与方法不匹配，而不会是参数转换失败。



**使用method属性指定请求类型**

​	用于约束请求的类型，可以收窄请求范围。指定请求谓词的类型如GET,POST,HEAD,OPTIONS,PUT,PATCH,DELETE,TRACE

```java
@Controller
public class RestFulController {
    //安全
   	//简洁
    //原来的：http://localhost:8080/add?a=1&b=2
    //RestFul:http://localhost:8080/add/a/b
    //    @RequestMapping(value = "/add/{a}/{b}",method = RequestMethod.GET)
    @GetMapping("/add/{a}/{b}")
    public String test1(@PathVariable int a, @PathVariable int b, Model model){
        int res = a+b;
        model.addAttribute("msg","结果为"+res);
        return "test";
    }
}
```

```java
@RequestMapping(value = "/add/{a}/{b}",method = RequestMethod.GET)
@RequestMapping(value = "/add/{a}/{b}",method = RequestMethod.POST)
@RequestMapping(value = "/add/{a}/{b}",method = RequestMethod.PUT)
@RequestMapping(value = "/add/{a}/{b}",method = RequestMethod.DELETE)
```



**小结：**

​	Spring MVC的@RequestMapping注解能够处理HTTP请求的方法，比如GET,PUT,POST,DELETE以及PATCH

​	**所有的地址栏请求默认都会是HTTP GET类型的**

方法级别的注解变体有如下几个：组合注解

```java
@GetMapping("/add/{a}/{b}")
@PostMapping("/add/{a}/{b}")
@PutMapping("/add/{a}/{b}")
@DeleteMapping("/add/{a}/{b}")
@PatchMapping
```

​	@GetMapping是一个组合注解

​	它所扮演的是@RequestMapping(method = RequestMethod.GET)的一个快捷方式。

​	平时使用的会比较多！



### 7、SpringMVC结果跳转方式

------

> ModelAndView

设置ModelAndView对象，根据view的名称，和视图解析器跳到指定的页面

页面：{视图解析器前缀}+viewName+{视图解析器后缀}

```xml
<!-- 视图解析器-->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
    <!-- 前缀-->
    <property name="prefix" value="/WEB-INF/jsp/"/>
    <!-- 后缀-->
    <property name="suffix" value=".jsp"/>
</bean>
```

对应的controller类

```java
public class ControllerTest1 implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","controllertest1");
        mv.setViewName("test");
        return mv;
    }
}
```

> ServletAPI

​	通过设置ServletAPI，不需要视图解析器

1.通过HttpServletResponsei进行输出

2.通过HttpServletResponse实现重定向

3.通过HttpServletResponse实现转发

```java
@Controller
public class ModelTest1 {
    @RequestMapping("/m1/t1")
    public String test(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        //resp.sendRedirect(); 重定向
        //req.getRequestDispatcher().forward(req,resp); 转发
        return "test";
    }
}
```



> SpringMVC

**通过SpringMVC来实现转发和重定向-无需视图解释器；**

测试前，需要将视图解析器注释掉

```java
@Controller
public class ModelTest1 {
    @RequestMapping("/m1/t1")
    public String test(Model model){
        model.addAttribute("msg","hello");
        //转发
        //        return "forward:/WEB-INF/jsp/test.jsp";
        //重定向
        return "redirect:/index.jsp";
    }
}
```



**通过SpringMVC来实现转发和重定向-有视图解释器；**

重定向，不需要视图解析器，本质就是重新请求一个新地方嘛，所以注意路径问题，可以重定向到另外一个请求实现

```java
@Controller
public class ModelTest1 {
    @RequestMapping("/m1/t1")
    public String test(Model model){
        model.addAttribute("msg","hello");
        //转发
        //        return "test";
        //重定向
        return "redirect:/index.jsp";
    }
}
```



### 8、SpringMVC：数据处理

------

1. 提交的域名和处理方法的参数名一致

   提交数据：http://localhost:8080/hello?name=peng

   处理方法：

   ```java
   @Controller
   @RequestMapping("/user")
   public class UserController {
       @GetMapping("/t1")
       //localhost:8080/user/t1?name=xxx
       public String test(String name, Model model){
           //接收前端参数
           //将返回的结果传递给前端
           model.addAttribute("msg",name);
           System.out.println(name);
           //跳转视图
           return "test";
       }
   }
   ```

   后台输出：peng

2. 提交的域名称和处理方法的参数名不一致

   提交数据：http://localhost:8080/hello?username=peng

   处理方法：

   ```java
   @Controller
   @RequestMapping("/user")
   public class UserController {
       @GetMapping("/t1")
       //localhost:8080/user/t1?name=xxx
       public String test(@RequestParam("username") String name, Model model){
           //接收前端参数
           //将返回的结果传递给前端
           model.addAttribute("msg",name);
           System.out.println(name);
           //跳转视图
           return "test";
       }
   }
   ```

   后台输出：peng

3. 提交的是一个对象

   要求提交的表单域和对象的属性名一致，参数使用对象即可

   1.实体类

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

   2.提交数据：http://localhost:8080/user/t2?id=1&name=peng&age=23

   3.处理方法

   ```java
   //前端接收的是一个对象：id,name,age
   /*
       * 1.接收前端用户传递的参数，判断参数的名字，假设名字直接在方法上，可以直接使用
       * 2.假设传递的是一个对象User,匹配User对象中的字段名；如果名字一致则🆗，否则匹配不到
       * */
   @GetMapping("/t2")
   public String test2(User user,Model model){
       model.addAttribute("msg",user);
       System.out.println(user);
       return "test";
   }
   }
   ```

   4.返回结果：User(id=1, name=peng, age=23)

   **说明：如果使用对象的话，前端传递的参数名和对象名必须一致，否则就是null**



> 提交到前端的三种方式

```java
Model 只有寥寥几个方法只适合用于储存数据，愾化了新手对于Mode1对象的操作和理解
ModelMap 继承了LinkedMap,除了实现了自身的一些方法，同样的继承LinkedMap的方法和特性
ModelAndview 可以在储存数据的同时，可以进行设置返回的逻辑视图，进行控制展示层的跳转。
```

​	**请使用80%的时间打好扎实的基础，剩下18%的时间研究框架，2%的时间去学点英文，框架的官方文档永远是最好的教程。**



> 乱码问题

测试步骤：

1.我们可以在首页编写一个提交的表单

```jsp
<form action="" method="post">
    <input type="text" name="name">
    <input type="submit">
</form>
```

2.后台编写控制类

```java
@Controller
public class EncodingController {
    @PostMapping("/e/t1")
    public String test(String name, Model model){
        model.addAttribute("msg",name);
        return "test";
    }
}
```

3.测试出现乱码



​	不得不说，乱码问题是在我们开发中十分常见的问题，也是让我们程序猿比较头大的问题！以前乱码问题通过过滤器解决，而Spring MVC给我们提供了一个过滤器，可以在web.Xml中配置，修改了xm文件需要重启服务器！

```xml
<!-- 配置SpringMVC的乱码过滤-->
<filter>
    <filter-name>encoding</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>utf-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>encoding</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

有些极端情况下，这个过滤器对get的支持不好

处理方法：

1.修改tomcat配置文件：设置编码！

```xml
<Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" 
           URLEncoding="UTF-8"/>
```



### 9、SpringMVC:JSON讲解

------

> 什么是JSON？

- JSON(JavaScript Object Notation,JS对象标记)是一种轻量级的数据交换格式，目前使用特别广泛。

- 采用完全独立于编程语言的**文本格式**来存储和表示数据。
- 简洁和清晰的层次结构使得JSON成为理想的数据交换语言。
- 易于人阅读和编写，同时也易于机器解析和生成，并有效地提升网络传输效率。



​	在JavaScript语言中，一切都是对象。因此，任何)avaScript支持的类型都可以通过JSON来表示，例如字符串、数字、对象、数组等。看看他的要求和语法格式：

- 对象表示为键值对，数据由逗号分隔
- 花括号保存对象
- 方括号保存数组



​	**JSON键值对**是用来保存JavaScript对象的一种方式，和JavaScript对象的写法也大同小异，键/值对组合中的键名写在前面并用双引号""包裹，使用冒号：分隔，然后紧接着值：

```json
{"name":"peng"}
{"age":"23"}
{"sex":"男"}
```

​	很多人搞不清楚JSON和JavaScript对象的关系，甚至连谁是谁都不清楚。其实，可以这么理解：

- JSON是JavaScript对象的字符串表示法，它使用文本表示一个JS对象的信息，本质是一个字符串。

  ```javascript
  var obj={a:'hello',b:'world'}; 这是一个对象，注意键名也是可以使用引号包裹的
  var json='{"a":"hello","b":"world"}';//这是一个JSON字符串，本质是一个字符串
  ```

  **JSON和JavaScript对象互转**

- 要实现JSON字符串转换为JavaScript对象，使用JSON.parse()方法

  ```javascript
  var obj=JSON.parse('{"a":"hello","b":"world"}');
  //结果是 {a:'hello',b:'world'}
  ```

- 要实现从JavaScript对象转换为JSON字符串，使用JSON.stringify()方法：

  ```javascript
  var json = JSON.stringify({a:'hello',b:'world'});
  //结果是 '{"a":"hello","b":"world"}'
  ```

**代码测试**

1. 新建一个module，添加web支持

2. 代码测试

   ```html
   <!DOCTYPE html>
   <html lang="en">
       <head>
           <meta charset="UTF-8">
           <title>Title</title>
           <script type="text/javascript">
               //编写一个JavaScript对象
               var user = {
                   name:"鹏飞",
                   age:3,
                   sex:"男"
               };
               //要实现从JavaScript对象转换为JSON字符串，使用JSON.stringify()方法
               var json = JSON.stringify(user);
   
               console.log(json);
               //要实现JSON字符串转换为JavaScript对象，使用JSON.parse()方法
               var json1 = JSON.parse(json);
               console.log(json1);
           </script>
       </head>
       <body>
   
       </body>
   </html>
   ```



> Controller返回JSON数据(重点)

- Jackson应该是目前比较好的json解析工具了

- 当然工具不止这一个，比如还有阿里巴巴的fastjson等等。

- 我们这里使用Jackson,使用它需要导入它的jar包；

  ```xml
  <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
  <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.13.2.2</version>
  </dependency>
  ```

- 配置SpringMVC需要的配置

  web.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
           version="4.0">
      <!-- 配置DispatchServlet-->
      <servlet>
          <servlet-name>springmvc</servlet-name>
          <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
          <init-param>
              <param-name>contextConfigLocation</param-name>
              <param-value>classpath:springmvc-servlet.xml</param-value>
          </init-param>
          <load-on-startup>1</load-on-startup>
      </servlet>
      <servlet-mapping>
          <servlet-name>springmvc</servlet-name>
          <url-pattern>/</url-pattern>
      </servlet-mapping>
      <!-- 配置SpringMVC的乱码过滤-->
      <filter>
          <filter-name>encoding</filter-name>
          <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
          <init-param>
              <param-name>encoding</param-name>
              <param-value>utf-8</param-value>
          </init-param>
      </filter>
      <filter-mapping>
          <filter-name>encoding</filter-name>
          <url-pattern>/*</url-pattern>
      </filter-mapping>
  </web-app>
  ```

  springmvc-servlet.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xmlns:mvc="http://www.springframework.org/schema/mvc"
         xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
                             http://www.springframework.org/schema/context  https://www.springframework.org/schema/context/spring-context.xsd
                             http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">
  
      <context:component-scan base-package="com.peng.controller"/>
      <mvc:default-servlet-handler/>
      <mvc:annotation-driven/>
  
      <!-- 视图解析器-->
      <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
          <!-- 前缀-->
          <property name="prefix" value="/WEB-INF/jsp/"/>
          <!-- 后缀-->
          <property name="suffix" value=".jsp"/>
      </bean>
  </beans>
  
  ```

- 随便编写一个User实体类，然后我们去编写测试Controller

  ```java
  package com.peng.pojo;
  
  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;
  
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class User {
      private String name;
      private int age;
      private String sex;
  }
  ```

- 这里我们需要两个新东西，一个是@ResponseBody,一个是ObjectMapper对象，我们看下具体的用法

  ```java
  package com.peng.controller;
  
  import com.fasterxml.jackson.core.JsonProcessingException;
  import com.fasterxml.jackson.databind.ObjectMapper;
  import com.peng.pojo.User;
  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.ResponseBody;
  
  @Controller
  public class UserController {
      @RequestMapping("/j1")
      @ResponseBody //它就不会走视图解析器，会直接返回一个字符串
      public String json() throws JsonProcessingException {
  
          //jackson  ObjectMapper
          ObjectMapper mapper = new ObjectMapper();
          //创建一个对象
          User user = new User("鹏飞",23,"男");
          String str = mapper.writeValueAsString(user);
  
          return str;
      }
  }
  ```

- 配置tomcat，启动测试

  http://localhost:8080/j1

- 发现出现乱码问题，我们需要设置以下他的编码格式为utf-8,以及它返回的类型；

- 通过@RequestMaping的produces属性来实现，修改下代码

  ```java
  @RequestMapping(value = "/j1",produces = "application/json;charset=utf-8")
  ```

  解决乱码问题



> 代码优化

**乱码统一解决**

​	上一种方法比较麻烦，如果项目中有许多请求则每一个都要添加，可以通过Spring配置统一指定，这样就不用每次都去处理了！

​	我们可以在springmvc的配置文件上添加一段消息StringHttpMessageConverter转换配置！

```xml
<!--json乱码问题配置-->
<mvc:annotation-driven>
    <mvc:message-converters register-defaults="true">
        <bean class="org.springframework.http.converter.StringHttpMessageConverter">
            <constructor-arg value="UTF-8"/>
        </bean>
        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
            <property name="objectMapper">
                <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                    <property name="failOnEmptyBeans" value="false"/>
                </bean>
            </property>
        </bean>
    </mvc:message-converters>
</mvc:annotation-driven>
```

**返回json字符串统一解决**

​	在类上直接使用@RestController,这样子，里面所有的方法都只会返回json字符串了，不用再每一个都添加@ResponseBody!我们在前后端分离开发中，一般都使用@RestController,十分便捷！

```java
//@Controller
@RestController
public class UserController {
    @RequestMapping("/j1")
    //@ResponseBody //它就不会走视图解析器，会直接返回一个字符串
    public String json() throws JsonProcessingException {

        //jackson  ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //创建一个对象
        User user = new User("鹏飞",23,"男");
        String str = mapper.writeValueAsString(user);

        return str;
}
```



> 测试集合输出

```java
@RequestMapping("/j2")
public String json1() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    List<User> list = new ArrayList<User>();
    User user1 = new User("鹏飞1",23,"男");
    User user2 = new User("鹏飞2",23,"男");
    User user3 = new User("鹏飞3",23,"男");
    User user4 = new User("鹏飞4",23,"男");
    list.add(user1);
    list.add(user2);
    list.add(user3);
    list.add(user4);
    String str = mapper.writeValueAsString(list);
    return str;//new ObjectMapper().writeValueAsString(list);
}
```

![image-20220405161433178](https://gitee.com/linda12138/picgo/raw/master/image/image-20220405161433178.png)

> 输出时间对象

```java
@RequestMapping("/j3")
public String json2() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Date date = new Date();
    //时间戳
    String str = mapper.writeValueAsString(date);
    return str;//new ObjectMapper().writeValueAsString(list);
}
```

- 默认日期格式会变成一个数字，是1970年1月1日到当前日期的毫秒数！
- Jackson默认是会把时间转成timestamps形式

**解决方案：取消timetamps形式，自定义时间格式**

```java
@RequestMapping("/j3")
public String json2() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();

    //使用ObjectMapper 来格式化输出
    //不使用时间戳
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
    //自定义日期的格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    mapper.setDateFormat(sdf);
    Date date = new Date();
    //时间戳
    String str = mapper.writeValueAsString(date);
    return str;//new ObjectMapper().writeValueAsString(list);
}
```



> 抽取为工具类

**如果要经常使用的话，这样是比较麻烦的，我们可以将这些代码封装到一个工具类中；我们去编写下**

```java
package com.peng.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class JsonUtils {

    public static String getJson(Object object,String datformat){
        ObjectMapper mapper = new ObjectMapper();

        //不使用时间戳
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //自定义日期的格式
        SimpleDateFormat sdf = new SimpleDateFormat(datformat);
        mapper.setDateFormat(sdf);

        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getJson(Object object){
        return getJson(object,"yyyy-MM-dd HH:mm:ss");
    }
}
```

**测试**

```java
package com.peng.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.peng.pojo.User;
import com.peng.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Controller
@RestController
public class UserController {
    @RequestMapping("/j1")
    //@ResponseBody //它就不会走视图解析器，会直接返回一个字符串
    public String json() throws JsonProcessingException {

        //jackson  ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //创建一个对象
        User user = new User("鹏飞",23,"男");
        String str = mapper.writeValueAsString(user);

        return str;
    }
    @RequestMapping("/j2")
    public String json1() throws JsonProcessingException {
        List<User> list = new ArrayList<User>();
        User user1 = new User("鹏飞1",23,"男");
        User user2 = new User("鹏飞2",23,"男");
        User user3 = new User("鹏飞3",23,"男");
        User user4 = new User("鹏飞4",23,"男");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        return JsonUtils.getJson(list);//new ObjectMapper().writeValueAsString(list);
    }
    @RequestMapping("/j3")
    public String json2() throws JsonProcessingException {
        Date date = new Date();
        return JsonUtils.getJson(date);
    }
}
```



> FastJson



​	fastjson.jar是阿里开发的一款专门用于Java开发的包，可以方便的实现json对象与JavaBean对象的转换，实现JavaBean对象与json字符串的转换，实现json对象与json字符串的转换。实现json的转换方法很多，最后的实现结果都是一样的。

​	fastjson的pom依赖！

```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.80</version>
</dependency>
```

​	fastjson三个主要的类

- 【JSONObject代表jso众对象】
  - JSONObject实现了Map接口，猜想JSONObject底层操作是由Map实现的。
  - JSONObject对应json对象，通过各种形式的get0方法可以获取json对象中的数据，也可利用诸如size(),isEmpty()等方法获取"键：值"对的个数和判断是否为空。其本质是通过实现Map接口并调用接口中的方法完成的。
- 【JSONArray代表json对象数组】
  - 内部是有Lst接口中的方法来完成操作的。
- 【JSON代表JSONObject和)SONArray的转化】
  - JSON类源码分祈与使用
  - 仔细观察这些方法，主要是实现son对象，json对象数组，javabean对象，json字符串之间的相互转化。

```java
@RequestMapping("/j4")
public String json3() throws JsonProcessingException {
    List<User> list = new ArrayList<User>();
    User user1 = new User("鹏飞1",23,"男");
    User user2 = new User("鹏飞2",23,"男");
    User user3 = new User("鹏飞3",23,"男");
    User user4 = new User("鹏飞4",23,"男");
    list.add(user1);
    list.add(user2);
    list.add(user3);
    list.add(user4);

    System.out.println("**********java对象  转  JSON字符串**********");
    String str1 = JSON.toJSONString(list);
    System.out.println("JSON.toJSONString(list)==>"+str1);
    String str2 = JSON.toJSONString(user1);
    System.out.println("JSON.toJSONString(list)==>"+str2);

    System.out.println("\n**********JSON字符串 转  java对象**********");
    User jp_user1 = JSON.parseObject(str2, User.class);
    System.out.println("JSON.parseObject(str2, User.class)==>"+jp_user1);

    System.out.println("\n**********Java对象  转 JSON对象**********");
    JSONObject jsonObject1 = (JSONObject) JSON.toJSON(user2);
    System.out.println(" (JSONObject) JSON.toJSON(user2)==>"+jsonObject1.getString("name"));

    System.out.println("\n**********JSON对象  转 Java对象**********");
    User to_java_user = JSON.toJavaObject(jsonObject1, User.class);
    System.out.println("JSON.toJavaObject(jsonObject1, User.class)==>"+to_java_user);

    return "Hello";
}
```





### 10、整合SSM

------

> 环境要求

环境：

- IDEA
- MySQL
- Tomcat 9
- Maven 3.6

​	要求

- 需要熟练掌握MySQL数据库，Spring,javaweb及Mybatis知识，简单的前端知识

> 数据库环境

​	创建一个存放书籍数据的数据库表

```sql
CREATE DATABASE ssmbuild;

USE ssmbuild;

CREATE TABLE `books`(
    `bookID` INT NOT NULL AUTO_INCREMENT COMMENT '书id',
    `bookName` VARCHAR(100) NOT NULL COMMENT '书名',
    `bookCounts` INT NOT NULL COMMENT '数量',
    `detail` VARCHAR(200) NOT NULL COMMENT '描述',
    KEY `bookID`(`bookID`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO `books`(`bookID`,`bookName`,`bookCounts`,`detail`)VALUES
(1,'Java',1,'从入门到放弃'),
(2,'MySQL',10,'从删库到跑路'),
(3,'Linux',5,'从进门到进牢')
```



> 基本环境搭建

1. 新建一个Maven项目！ssmbuild,添加web支持

2. 导入相关pom依赖

   ```xml
   <!-- 依赖 junit 数据库驱动，连接池，servlet,jsp,mybatis,mybatis-spring,spring-->
   <dependencies>
       <!--junit-->
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.11</version>
       </dependency>
       <!--数据库驱动-->
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>5.1.47</version>
       </dependency>
       <!--数据库连接池  c3p0  dbcp-->
       <!-- https://mvnrepository.com/artifact/com.mchange/c3p0 -->
       <dependency>
           <groupId>com.mchange</groupId>
           <artifactId>c3p0</artifactId>
           <version>0.9.5.5</version>
       </dependency>
       <!--servlet   jsp -->
       <dependency>
           <groupId>javax.servlet</groupId>
           <artifactId>servlet-api</artifactId>
           <version>2.5</version>
       </dependency>
       <dependency>
           <groupId>javax.servlet.jsp</groupId>
           <artifactId>jsp-api</artifactId>
           <version>2.1</version>
       </dependency>
       <dependency>
           <groupId>javax.servlet</groupId>
           <artifactId>jstl</artifactId>
           <version>1.2</version>
       </dependency>
       <!--mybatis-->
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis</artifactId>
           <version>3.5.9</version>
       </dependency>
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis-spring</artifactId>
           <version>2.0.7</version>
       </dependency>
       <!-- Spring-->
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-webmvc</artifactId>
           <version>5.3.18</version>
       </dependency>
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-jdbc</artifactId>
           <version>5.3.17</version>
       </dependency>
       <dependency>
           <groupId>org.projectlombok</groupId>
           <artifactId>lombok</artifactId>
           <version>1.18.22</version>
       </dependency>
       <dependency>
           <groupId>org.aspectj</groupId>
           <artifactId>aspectjweaver</artifactId>
           <version>1.9.9</version>
       </dependency>
   </dependencies>
   ```

   

3. Maven资源过滤设置

   ```xml
   <!-- 静态资源导出问题-->
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

   

4. 建立基本结构和配置框架

   - com.peng.pojo

   - com.peng.dao

   - com.peng.service

   - com.peng.controller

   - mybatis-config.xml

     ```xml
     <?xml version="1.0" encoding="UTF-8" ?>
     <!DOCTYPE configuration
             PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
             "http://mybatis.org/dtd/mybatis-3-config.dtd">
     <configuration>
         <!--配置数据源 交给Spring去做-->
     
         <typeAliases>
             <package name="com.peng.pojo"/>
         </typeAliases>
     
         <mappers>
             <mapper class="com.peng.dao.BookMapper"/>
         </mappers>
     </configuration>
     ```
     
   - applicationcontext.xml
   
     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd">
     
         <import resource="classpath:spring-dao.xml"/>
         <import resource="classpath:spring-service.xml"/>
         <import resource="classpath:spring-mvc.xml"/>
     </beans>
     ```
   
   - database.properties
   
     ```properties
     jdbc.driver=com.mysql.jdbc.Driver
     # 时区配置 Mysql8.0+ &serverTimezone=Asia/Shanghai
     jdbc.url=jdbc:mysql://localhost:3306/ssmbuild?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
     jdbc.username=root
     jdbc.password=
     ```
   
   - spring-dao.xml
   
     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
                                http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
     
         <!--关联数据库配置文件-->
         <context:property-placeholder location="classpath:database.properties"/>
         <!--连接池
         dbcp:半自动化操作 不能自动连接
         c3p0:自动化操作（自动化的加载配置文件，并且可以自动设置到对象中）
         druid:
         hikari:
     -->
         <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
             <property name="driverClass" value="${jdbc.driver}"/>
             <property name="jdbcUrl" value="${jdbc.url}"/>
             <property name="user" value="${jdbc.username}"/>
             <property name="password" value="${jdbc.password}"/>
             <!-- c3p0连接池的私有属性-->
             <property name="maxPoolSize" value="30"/>
             <property name="minPoolSize" value="10"/>
             <!--关闭连接后不自动commit-->
             <property name="autoCommitOnClose" value="false"/>
             <!-- 获取连接超时时间-->
             <property name="checkoutTimeout" value="10000"/>
             <!--当获取连接失败重试次数-->
             <property name="acquireRetryAttempts" value="2"/>
         </bean>
     
         <!--SQLSessionFactory-->
     
         <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
             <property name="dataSource" ref="dataSource"/>
             <!--绑定Mybatis的配置文件-->
             <property name="configLocation" value="classpath:mybatis-config.xml"/>
         </bean>
     
         <!--配置dao接口扫描包，动态的实现了dao接口可以注入到Spring容器中-->
         <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
             <!--注入sqlSessionFactory-->
             <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
             <!--扫描要扫描的dao包-->
             <property name="basePackage" value="com.peng.dao"/>
         </bean>
     </beans>
     ```
   
   - spring-service.xml
   
     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
                                http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
     
         <!--扫描service下的包-->
         <context:component-scan base-package="com.peng.service"/>
         <!--将我们的所有业务类，注入到Spring,可以通过配置或者注解实现-->
         <bean id="BookServiceImpl" class="com.peng.service.BookServiceImpl">
             <property name="bookMapper" ref="bookMapper"/>
         </bean>
         <!--声明式事务配置-->
         <bean id="TransactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
             <!--注入数据源-->
             <property name="dataSource" ref="dataSource"/>
         </bean>
         <!-- aop横切事务-->
     </beans>
     ```
   
   - spring-mvc.xml
   
     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
            xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
                                http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd
                                http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
         <!--注解驱动-->
         <mvc:annotation-driven/>
         <!--静态资源过滤-->
         <mvc:default-servlet-handler/>
         <!--扫描包：controller-->
         <context:component-scan base-package="com.peng.controller"/>
         <!--视图解析器-->
         <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
             <property name="prefix" value="/WEB-INF/jsp/"/>
             <property name="suffix" value=".jsp"/>
         </bean>
     </beans>
     ```
   
   - web.xml
   
     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
              version="4.0">
         <!--DispatchServlet-->
         <servlet>
             <servlet-name>springmvc</servlet-name>
             <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
             <init-param>
                 <param-name>contextConfigLocation</param-name>
                 <param-value>classpath:spring-mvc.xml</param-value>
             </init-param>
         </servlet>
         <servlet-mapping>
             <servlet-name>springmvc</servlet-name>
             <url-pattern>/</url-pattern>
         </servlet-mapping>
         <!--乱码过滤-->
         <filter>
             <filter-name>encodingFilter</filter-name>
             <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
             <init-param>
                 <param-name>encoding</param-name>
                 <param-value>utf-8</param-value>
             </init-param>
         </filter>
         <filter-mapping>
             <filter-name>encodingFilter</filter-name>
             <url-pattern>/*</url-pattern>
         </filter-mapping>
         <!--Session过期时间-->
         <session-config>
             <session-timeout>15</session-timeout>
         </session-config>
     </web-app>
     ```
     
   

**实体类Books**

```java
package com.peng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {
    private int bookID;
    private String bookName;
    private int bookCounts;
    private String detail;
}
```

**BookMapper接口**

```java
package com.peng.dao;

import com.peng.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BookMapper {
    //    增加一本书
    int addBook(Books books);
    //    删除一本书
    int deleteBookById(@Param("bookId") int id);
    //    更新一本书
    int updateBook(Books books);
    //    查询一本书
    Books queryBookById(@Param("bookId") int id);
    //    查询全部的书
    List<Books> queryAllBook();

    Books queryBookByName(@Param("bookName") String bookName);
}
```

**BookMapper.xml**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.peng.dao.BookMapper">
    <!--添加一本书-->
    <insert id="addBook" parameterType="Books">
        insert into ssmbuild.books(bookName,bookCounts,detail)
        values(#{bookName},#{bookCounts},#{detail});
    </insert>
    <!-- 删除一本书-->
    <delete id="deleteBookById" parameterType="int">
        delete from ssmbuild.books
        where bookID=#{bookId}
    </delete>
    <!--更新一本书-->
    <update id="updateBook" parameterType="Books">
        update ssmbuild.books set bookName=#{bookName},bookCounts=#{bookCounts},detail=#{detail}
        where bookID=#{bookID}

    </update>
    <!-- 查询一本书-->
    <select id="queryBookById" resultType="Books">
        select * from ssmbuild.books where bookID=#{bookId}
    </select>
    <!--查询全部-->
    <select id="queryAllBook" resultType="Books">
        select * from ssmbuild.books
    </select>
    <select id="queryBookByName" resultType="Books">
        select * from ssmbuild.books where bookName=#{bookName}
    </select>
</mapper>
```

**业务层BookService**

```java
package com.peng.service;

import com.peng.pojo.Books;
import org.apache.ibatis.annotations.Param;


import java.util.List;


//BookService:底下需要去实现,调用dao层
public interface BookService {
    //增加一个Book
    int addBook(Books book);
    //根据id删除一个Book
    int deleteBookById(int id);
    //更新Book
    int updateBook(Books books);
    //根据id查询,返回一个Book
    Books queryBookById(int id);
    //查询全部Book,返回list集合
    List<Books> queryAllBook();

    Books queryBookByName(String bookName);
}
```

**BooksServiceImpl**

```java
package com.peng.service;

import com.peng.dao.BookMapper;
import com.peng.pojo.Books;

import java.util.List;

public class BookServiceImpl implements BookService{
    //service调dao层 组合dao
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public int addBook(Books books) {
        return bookMapper.addBook(books);
    }

    @Override
    public int deleteBookById(int id) {
        return bookMapper.deleteBookById(id);
    }

    @Override
    public int updateBook(Books books) {
        System.out.println("BookServiceImpl--update==>"+books);
        return bookMapper.updateBook(books);
    }

    @Override
    public Books queryBookById(int id) {
        return bookMapper.queryBookById(id);
    }

    @Override
    public List<Books> queryAllBook() {
        return bookMapper.queryAllBook();
    }

    @Override
    public Books queryBookByName(String bookName) {
        return bookMapper.queryBookByName(bookName);
    }
}
```



**BookController**

```java
package com.peng.controller;

import com.peng.pojo.Books;
import com.peng.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    //controller 调service 层
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    //查询全部的书籍，并且返回到一个书籍展示页面
    @RequestMapping("/allBook")
    public String list(Model model){
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list",list);
        return "allBook";
    }
    //跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddPaper(){
        return "addBook";
    }
    //    添加书籍的请求
    @RequestMapping("addBook")
    public String addBook(Books books){
        System.out.println("addBook==>"+books);
        bookService.addBook(books);
        return "redirect:/book/allBook";//重定向到我们的@RequestMapping("/allBook")
    }
    //跳转到修改页面
    @RequestMapping("toUpdate")
    public String toUpdatePaper(int id,Model model){
        Books books = bookService.queryBookById(id);
        model.addAttribute("QBook",books);
        return "updateBook";
    }
    //修改书籍
    @RequestMapping("/updateBook")
    public String updateBook(Books books){
        System.out.println("updateBook==>"+books);
        bookService.updateBook(books);
        return "redirect:/book/allBook";
    }
    //删除书籍
    @RequestMapping("deleteBook")
    public String deleteBook(int id){
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    //查询书籍
    @RequestMapping("queryBook")
    public String queryBook(String queryBookName,Model model){
        Books books = bookService.queryBookByName(queryBookName);
        List<Books> list = new ArrayList<Books>();
        list.add(books);
        if (books==null){
            list = bookService.queryAllBook();
            model.addAttribute("error","未查到");
        }
        model.addAttribute("list",list);
        return "allBook";
    }
}
```

**index.jsp**

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>首页</title>
        <style>
            a{
                text-decoration: none;
                color: black;
                font-size: 18px;
            }
            h3{
                width: 180px;
                height: 38px;
                margin: 100px auto;
                text-align: center;
                line-height: 38px;
                background: deepskyblue;
                border-radius: 5px;
            }
        </style>
    </head>
    <body>
        <h3>
            <a href="${pageContext.request.contextPath}/book/allBook">进入书籍页面</a>
        </h3>
    </body>
</html>
```



**allBook.jsp**

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>书籍展示</title>
        <%--BootStrap美化页面--%>
        <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="page-header">
                        <h1>
                            <small>书籍列表---显示所有书籍</small>
                        </h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 column">
                        <%--toAddBook--%>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增书籍</a>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/allBook">显示所有书籍</a>
                    </div>
                    <div class="col-md-8 column">
                        <%--查询书籍--%>
                        <form class="form-inline" action="${pageContext.request.contextPath}/book/queryBook" method="post" style="float: right">
                            <span style="color: red;font-weight: bold;">${error}</span>
                            <input type="text" name="queryBookName" class="form-control" placeholder="请输入要查询的书籍名称">
                            <input type="submit" value="查询" class="btn btn-primary">
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <th>书籍编号</th>
                            <th>书籍名称</th>
                            <th>书籍数量</th>
                            <th>书籍详情</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <%--书籍从数据库中查询，从list中遍历出来 c forEach--%>
                    <tbody>
                        <c:forEach var="book" items="${list}">
                            <tr>
                                <td>${book.bookID}</td>
                                <td>${book.bookName}</td>
                                <td>${book.bookCounts}</td>
                                <td>${book.detail}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/book/toUpdate?id=${book.bookID}">修改</a>
                                    &nbsp; | &nbsp;
                                    <a href="/book/deleteBook?id=${book.bookID}">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
```

**addBook.jsp**

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>新增书籍</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>新增书籍</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/book/addBook" method="post">
        书籍名称：<input type="text" name="bookName"><br><br><br>
        书籍数量：<input type="text" name="bookCounts"><br><br><br>
        书籍详情：<input type="text" name="detail"><br><br><br>
        <input type="submit" value="添加">
    </form>

</div>
</body>
</html>
```

**updateBook.jsp**

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>修改书籍</title>
        <%--BootStrap美化页面--%>
        <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="page-header">
                        <h1>
                            <small>修改书籍</small>
                        </h1>
                    </div>
                </div>
            </div>
            <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
                <%--出现的问题，我们提交了修改的SQL请求，但是修改失败，初次考虑，是事务的问题 配置完成 依旧失败--%>
                <%--sql出现问题--%>
                <%--前端传递隐藏域--%>
                <input type="hidden" name="bookID" value="${QBook.bookID}">
                <div class="form-group">
                    <label>书籍名称</label>
                    <input type="text" name="bookName" class="form-control" value="${QBook.bookName}" required>
                </div>
                <div class="form-group">
                    <label>书籍数量</label>
                    <input type="text" name="bookCounts" class="form-control" value="${QBook.bookCounts}" required>
                </div>
                <div class="form-group">
                    <label>书籍描述</label>
                    <input type="text" name="detail" class="form-control" value="${QBook.detail}" required>
                </div>
                <div class="form-group">
                    <input type="submit" class="form-control" value="修改">
                </div>
            </form>
        </div>

    </body>
</html>
```



### 11、SpringMVC:Ajax技术

------

> 简介

- AJAX=Asynchronous JavaScript and XML(异步的JavaScript和XML)。
- AJAX是一种在无需重新加载整个网页的情况下，能够更新部分网页的技术。
- Ajax不是一种新的编程语言，而是一种用于创建更好更快以及交互性更强的Wb应用程序的技术
- 在2005年，Google通过其Google Suggest使AJAX变得流行起来。Google Suggesti能够自动帮你完成搜索单词。
- Google Suggest使用AJAX创造出动态性极强的web界面：当您在谷歌的搜索框输入关键字时，JavaScript会把这些字符发送到服务器，然后服务器会返回一个搜索建议的列表。
- 就和国内百度的搜索框一样：
  ![image-20220407095859449](https://gitee.com/linda12138/picgo/raw/master/image/image-20220407095859449.png)
- 传统的网页（即不用jax技术的网页），想要更新内容或者提交一个表单，都需要重新加载整个网页。
- 使用可x技术的网页，通过在后台服务器进行少量的数据交换，就可以实现异步局部更新
- 使用Ajx,用户可以创建接近本地桌面应用的直接、高可用、更丰富、更动态的Web用户界面。



> 伪造Ajax

我们可以使用前端的一个标签来伪造一个ajax的样子。iframe标签

1.新建一个module:sspringmvc-06-ajax,导入web支持！

2.编写一个ajaX-frame.html使用iframe测试，感受下效果

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>iframe测试体验页面无刷新</title>
        <script>
            function go(){
                //所有的值变量，提前获取
                var url = document.getElementById("url").value;
                document.getElementById("iframe1").src=url;
            }
        </script>
    </head>
    <body>
        <div>
            <p>请输入地址：</p>
            <p><input type="text" id="url" value="test1.html">
                <input type="button" value="提交" onclick="go()"></p>

        </div>
        <div>
            <iframe id="iframe1" style="width:100%;height: 500px" ></iframe>
        </div>
    </body>
</html>
```

3.测试

**利用AJAX可以做：**

- 注册时，输入用户名自动检测用户是否已经存在。
- 登陆时，提示用户名密码错误
- 删除数据行时，将行ID发送到后台，后台在数据库中删除，数据库删除成功后，在页面DOM中将数据行也删除。
- ...等等

> jQuery.ajax

- 纯S原生实现Ajax我们不去讲解这里，直接使用jquery提供的，方便学习和使用，避免重复造轮子，有兴趣的同学可以去了解下JS原生XMLHttpRequest
- Ajax的核心是XMLHttpRequest对象(XHR)。XHR为向服务器发送请求和解析服务器响应提供了接口。能够以异步方式从服务器获取新数据。
- jQuery提供多个与AJAX有关的方法。
- 通过jQuery AJAX方法，您能够使用HTTP Get和HTTP Post从远程服务器上请求文本HTML、XML或jSON-同时您能够把这些外部数据直接载入网页的被选元素中。
- jQuery不是生产者，而是大自然搬运工
- jQuery Ajax本质就是XMLHttpRequest,对他进行了封装，方便调用！

```javascript
jQuery.ajax(...)
      部分参数：
            url：请求地址
            type：请求方式，GET、POST（1.9.0之后用method）
        headers：请求头
            data：要发送的数据
    contentType：即将发送信息至服务器的内容编码类型(默认: "application/x-www-form-urlencoded; charset=UTF-8")
          async：是否异步
        timeout：设置请求超时时间（毫秒）
      beforeSend：发送请求前执行的函数(全局)
        complete：完成之后执行的回调函数(全局)
        success：成功之后执行的回调函数(全局)
          error：失败之后执行的回调函数(全局)
        accepts：通过请求头发送给服务器，告诉服务器当前客户端可接受的数据类型
        dataType：将服务器端返回的数据转换成指定类型
          "xml": 将服务器端返回的内容转换成xml格式
          "text": 将服务器端返回的内容转换成普通文本格式
          "html": 将服务器端返回的内容转换成普通文本格式，在插入DOM中时，如果包含JavaScript标签，则会尝试去执行。
        "script": 尝试将返回值当作JavaScript去执行，然后再将服务器端返回的内容转换成普通文本格式
          "json": 将服务器端返回的内容转换成相应的JavaScript对象
        "jsonp": JSONP 格式使用 JSONP 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数
```

**我们来个简单的测试，使用最原始的HttpServletResponse处理 , 最简单 , 最通用**![2649321879410](https://gitee.com/linda12138/picgo/raw/master/image/2649321879410.png)

1、配置web.xml 和 springmvc的配置文件，复制上面案例的即可 【记得静态资源过滤和注解驱动配置上】

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context  https://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="com.peng.controller"/>
    <mvc:default-servlet-handler/>
    <mvc:annotation-driven/>

    <!-- 视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
        <!-- 前缀-->
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <!-- 后缀-->
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```

2、编写一个AjaxController

```java
package com.peng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AjaxController {
    @RequestMapping("/t1")
    public String test(){
        return "hello";
    }
    @RequestMapping("/a1")
    public void a1(String name, HttpServletResponse response) throws IOException {
        System.out.println("a1:param=>"+name);
        if ("peng".equals(name)){
            response.getWriter().print("true");
        }else {
            response.getWriter().print("false");
        }
    }
}
```

3、导入jquery ， 可以使用在线的CDN ， 也可以下载导入

```html
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/jquery-3.1.1.min.js"></script>
```

4、编写index,jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>$Title$</title>
        <script src="${pageContext.request.contextPath}/static/js/jquery-3.6.0.js"></script>
        <%--js 是一个很随便的语言--%>
        <script>
            function a(){
                $.post({
                    url:"${pageContext.request.contextPath}/a1",
                    data:{"name":$("#username").val()},
                    success:function (data,status){
                        console.log("data="+data);
                        console.log("status="+status);
                    }
                })
            }
        </script>
    </head>
    <body>
        <%--失去焦点的时候，发起一个请求(携带信息)到后台--%>
        用户名：<input type="text" id="username" onblur="a()">
    </body>
</html>
```

5、启动tomcat测试！打开浏览器的控制台，当我们鼠标离开输入框的时候，可以看到发出了一个ajax的请求！是后台返回给我们的结果！测试成功！



**SpringMVC实现**

实现类User

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private int age;
    private String sex;
}
```



获取集合对象，展示到前端页面

```java
@RequestMapping("/a2")
public List<User> a2(){
    List<User> list = new ArrayList<User>();
    //添加数据
    list.add(new User("鹏飞",12,"男"));
    list.add(new User("二狗",18,"男"));
    list.add(new User("三山",25,"男"));

    return list;
}
```

前端页面

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <script src="${pageContext.request.contextPath}/static/js/jquery-3.6.0.js"></script>
        <script>
            $(function () {
                $("#btn").click(function () {
                    /*
                *$.post(url,param[可以省略],success)
                * */
                    $.post("${pageContext.request.contextPath}/a2",function (data){
                        // console.log(data);
                        var html="";
                        for (let i = 0; i < data.length; i++) {
                            html+="<tr>"+
                                "<td>"+data[i].name+"</td>"+
                                "<td>"+data[i].age+"</td>"+
                                "<td>"+data[i].sex+"</td>"+
                                "</tr>"
                        }
                        $("#context").html(html)
                    })
                })
            });
        </script>
    </head>
    <body>
        <input type="button" value="加载数据" id="btn">
        <table>
            <tr>
                <td>姓名</td>
                <td>年龄</td>
                <td>性别</td>
            </tr>
            <tbody id="context">
                <%--数据在后台--%>

            </tbody>
        </table>
    </body>
</html>
```

**运行测试**



> 注册提示效果

**Controller**

```java
@RequestMapping("/a3")
public String a3(String name,String password){
    String msg="";
    if (name!=null){
        //这些数据应该在数据库中查
        if ("admin".equals(name)){
            msg="ok";
        }else {
            msg="用户名有误";
        }
    }
    if (password!=null){
        //这些数据应该在数据库中查
        if ("123456".equals(password)){
            msg="ok";
        }else {
            msg="密码有误";
        }
    }
    return msg;
}
```

**前端页面login.jsp**

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>登录</title>
        <script src="${pageContext.request.contextPath}/static/js/jquery-3.6.0.js"></script>
        <script>
            function a1() {
                $.post({
                    url:"${pageContext.request.contextPath}/a3",
                    data:{"name":$("#name").val()},
                    success:function (data) {
                        if (data.toString()=='ok'){
                            $("#userInfo").css("color","green");
                        }else {
                            $("#userInfo").css("color","red");
                        }
                        $("#userInfo").html(data);
                    }
                })
            }
            function a2() {
                $.post({
                    url:"${pageContext.request.contextPath}/a3",
                    data:{"password":$("#password").val()},
                    success:function (data) {
                        if (data.toString()=='ok'){
                            $("#pwdInfo").css("color","green");
                        }else {
                            $("#pwdInfo").css("color","red");
                        }
                        $("#pwdInfo").html(data);
                    }
                })
            }
        </script>
    </head>
    <body>
        <p>
            用户名：<input type="text" id="name" onblur="a1()">
            <span id="userInfo"></span>
        </p>
        <p>
            密码：<input type="password" id="password" onblur="a2()">
            <span id="pwdInfo"></span>
        </p>
    </body>
</html>
```

**【记得处理json乱码问题】**

```xml
<mvc:annotation-driven>
    <mvc:message-converters register-defaults="true">
        <bean class="org.springframework.http.converter.StringHttpMessageConverter">
            <constructor-arg value="UTF-8"/>
        </bean>
        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
            <property name="objectMapper">
                <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                    <property name="failOnEmptyBeans" value="false"/>
                </bean>
            </property>
        </bean>
    </mvc:message-converters>
</mvc:annotation-driven>
```

**测试**



### 12、SpringMVC:拦截器

------

> 概述

​	SpringMVC的处理器拦截器类似于Servlet开发中的过滤器Filter,用于对处理器进行预处理和后处理。开发者可以自己定义一些拦截器来实现特定的功能。

​	**过滤器与拦截器的区别**：拦截器是AOP思想的具体应用。

​	**过滤器**

- servlet规范中的一部分，任何Java web工程都可以使用
- 在url-pattern中配置了/*之后，可以对所有要访问的资源进行拦截

​	**过滤器**

- 栏截器是SpringMVC:框架自己的，只有使用了SpringMVC框架的工程才能使用
- 拦截器只会拦截访问的控制器方法，如果访问的是jsp/html/css/image/js是不会进行拦截的

> 自定义拦截器

​	那如何实现拦截器呢？

​	想要自定义栏截器，必须实现Handlerlnterceptor接口。

1.新建一个Moudule,springmvc-O7-Interceptor,添加web支持

2.配置web.xml和springmvc-servlet.xml文件

3.编写一个拦截器

```java
package com.peng.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    //return true;执行下一个拦截器，放行
    //    return false;执行下一个拦截器，禁止放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("=============处理前=============");
        return true;
    }
    //拦截日志
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("=========处理后===========");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("=============清理============");
    }
}
```

**Controller**

```java
@RestController
public class TestController {
    @GetMapping("/t1")
    public String test(){
        System.out.println("TestController==>执行了");
        return "ok";
    }
}
```

**拦截器配置**

```xml
<!--拦截器配置-->
<mvc:interceptors>
    <mvc:interceptor>
        <!--包括这个请求下的所有请求 /**-->
        <mvc:mapping path="/**"/>
        <bean class="com.peng.config.MyInterceptor"/>
    </mvc:interceptor>
</mvc:interceptors>
```



> 验证用户是否登录 (认证用户)

**实现思路**

1、有一个登陆页面，需要写一个controller访问页面。

2、登陆页面有一提交表单的动作。需要在controller中处理。判断用户名密码是否正确。如果正确，向session中写入用户信息。*返回登陆成功。*

3、拦截用户请求，判断用户是否登陆。如果用户已经登陆。放行， 如果用户未登陆，跳转到登陆页面

**测试：**

1. 编写一个登录界面login.jsp

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
       <head>
           <title>login</title>
       </head>
       <body>
           <%--在WEB-INF下的所有页面或者资源，只能通过controller或者servlet进行访问--%>
           <h1>登录页面</h1>
   
           <form action="${pageContext.request.contextPath}/user/login" method="post">
               用户名：<input type="text" name="username">
               密码：<input type="password" name="password">
               <input type="submit" value="提交">
           </form>
       </body>
   </html>
   ```

   

2. Controller

   ```java
   package com.peng.controller;
   
   import org.springframework.stereotype.Controller;
   import org.springframework.ui.Model;
   import org.springframework.web.bind.annotation.RequestMapping;
   
   import javax.servlet.http.HttpSession;
   
   @Controller
   @RequestMapping("/user")
   public class LoginController {
   
       @RequestMapping("/login")
       public String login(HttpSession session, String username, String password, Model model){
           //把用户的信息存在session中
           System.out.println("login==>"+username);
           session.setAttribute("userLoginInfo",username);
           model.addAttribute("username",username);
           return "main";
       }
       @RequestMapping("/main")
       public String test(){
           return "main";
       }
       @RequestMapping("/gologin")
       public String test1(){
           return "login";
       }
       @RequestMapping("/goout")
       public String goOut(HttpSession session){
           session.removeAttribute("userLoginInfo");
           return "login";
       }
   }
   ```

   

3. 主页main.jsp

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
       <head>
           <title>Title</title>
       </head>
       <body>
           <h1>首页</h1>
           <span>${username}</span>
           <p>
               <a href="${pageContext.request.contextPath}/user/goout">注销</a>
           </p>
       </body>
   </html>
   ```

   

4. 在 index 页面上测试跳转！启动Tomcat 测试，未登录也可以进入主页！

   ```jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
       <head>
           <title>$Title$</title>
       </head>
       <body>
           <h1><a href="${pageContext.request.contextPath}/user/gologin">登录页面</a></h1>
           <h1><a href="${pageContext.request.contextPath}/user/main">主页</a></h1>
       </body>
   </html>
   ```

   

5. 编写用户拦截器

   ```java
   package com.peng.config;
   
   import org.springframework.web.servlet.HandlerInterceptor;
   
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   import javax.servlet.http.HttpSession;
   
   public class LoginInterceptor implements HandlerInterceptor {
       @Override
       public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
           HttpSession session = request.getSession();
           //放行的判断：判断什么情况下登录了
           //登录页面也放行
           if (request.getRequestURI().contains("gologin")){
               return true;
           }
           //说明我在提交登录
           if (request.getRequestURI().contains("login")){
               return true;
           }
           //        第一次登录也是没有session的
           if(session.getAttribute("userLoginInfo")!=null){
               return true;
           }
           //判断什么情况下没有登录
           request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
           return false;
       }
   }
   ```

   

6. 在Springmvc的配置文件中注册拦截器

   ```xml
   <mvc:interceptor>
       <!--包括这个请求下的所有请求 /**-->
       <mvc:mapping path="/user/**"/>
       <bean class="com.peng.config.LoginInterceptor"/>
   </mvc:interceptor>
   ```

   

7. 测试



> 准备工作

文件上传是项目开发中最常见的功能之一 ,springMVC 可以很好的支持文件上传，但是SpringMVC上下文中默认没有装配MultipartResolver，因此默认情况下其不能处理文件上传工作。如果想使用Spring的文件上传功能，则需要在上下文中配置MultipartResolver。

前端表单要求：为了能上传文件，必须将表单的method设置为POST，并将enctype设置为multipart/form-data。只有在这样的情况下，浏览器才会把用户选择的文件以二进制数据发送给服务器；

**对表单中的 enctype 属性做个详细的说明：**

- application/x-www=form-urlencoded：默认方式，只处理表单域中的 value 属性值，采用这种编码方式的表单会将表单域中的值处理成 URL 编码方式。
- multipart/form-data：这种编码方式会以二进制流的方式来处理表单数据，这种编码方式会把文件域指定文件的内容也封装到请求参数中，不会对字符编码。
- text/plain：除了把空格转换为 "+" 号外，其他字符都不做编码处理，这种方式适用直接通过表单发送邮件。

```xml
<form action="" enctype="multipart/form-data" method="post">
   <input type="file" name="file"/>
   <input type="submit">
</form>
```

一旦设置了enctype为multipart/form-data，浏览器即会采用二进制流的方式来处理表单数据，而对于文件上传的处理则涉及在服务器端解析原始的HTTP响应。在2003年，Apache Software Foundation发布了开源的Commons FileUpload组件，其很快成为Servlet/JSP程序员上传文件的最佳选择。

- Servlet3.0规范已经提供方法来处理文件上传，但这种上传需要在Servlet中完成。
- 而Spring MVC则提供了更简单的封装。
- Spring MVC为文件上传提供了直接的支持，这种支持是用即插即用的MultipartResolver实现的。
- Spring MVC使用Apache Commons FileUpload技术实现了一个MultipartResolver实现类：
- CommonsMultipartResolver。因此，SpringMVC的文件上传还需要依赖Apache Commons FileUpload的组件。

> 文件上传

1、导入文件上传的jar包，commons-fileupload ， Maven会自动帮我们导入他的依赖包 commons-io包；

```xml
<!--文件上传-->
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.3.3</version>
</dependency>
<!--servlet-api导入高版本的-->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
</dependency>
```

2、配置bean：multipartResolver

【**注意！！！这个bena的id必须为：multipartResolver ， 否则上传文件会报400的错误！在这里栽过坑,教训！**】

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context  https://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="com.peng.controller"/>
    <mvc:default-servlet-handler/>
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg value="UTF-8"/>
            </bean>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                        <property name="failOnEmptyBeans" value="false"/>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>

    <!-- 视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
        <!-- 前缀-->
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <!-- 后缀-->
        <property name="suffix" value=".jsp"/>
    </bean>
    <!--文件上传配置-->
    <bean id="multipartResolver"  class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!-- 请求的编码格式，必须和jSP的pageEncoding属性一致，以便正确读取表单的内容，默认为ISO-8859-1 -->
        <property name="defaultEncoding" value="utf-8"/>
        <!-- 上传文件大小上限，单位为字节（10485760=10M） -->
        <property name="maxUploadSize" value="10485760"/>
        <property name="maxInMemorySize" value="40960"/>
    </bean>
</beans>
```

CommonsMultipartFile 的 常用方法：

- **String getOriginalFilename()：获取上传文件的原名**
- **InputStream getInputStream()：获取文件流**
- **void transferTo(File dest)：将上传文件保存到一个目录文件中**

 我们去实际测试一下

3、编写前端页面

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>$Title$</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data" method="post">
            <input type="file" name="file"/>
            <input type="submit">
        </form>
    </body>
</html>
```

4、**Controller**

```java
package com.peng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
public class FileController {
    //@RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
    //批量上传CommonsMultipartFile则为数组即可
    @RequestMapping("/upload")
    public String fileUpload(@RequestParam("file") CommonsMultipartFile file , HttpServletRequest request) throws IOException {

        //获取文件名 : file.getOriginalFilename();
        String uploadFileName = file.getOriginalFilename();

        //如果文件名为空，直接回到首页！
        if ("".equals(uploadFileName)){
            return "redirect:/index.jsp";
        }
        System.out.println("上传文件名 : "+uploadFileName);

        //上传路径保存设置
        String path = request.getServletContext().getRealPath("/upload");
        //如果路径不存在，创建一个
        File realPath = new File(path);
        if (!realPath.exists()){
            realPath.mkdir();
        }
        System.out.println("上传文件保存地址："+realPath);

        InputStream is = file.getInputStream(); //文件输入流
        OutputStream os = new FileOutputStream(new File(realPath,uploadFileName)); //文件输出流

        //读取写出
        int len=0;
        byte[] buffer = new byte[1024];
        while ((len=is.read(buffer))!=-1){
            os.write(buffer,0,len);
            os.flush();
        }
        os.close();
        is.close();
        return "redirect:/index.jsp";
    }
}
```

**采用file.Transto 来保存上传的文件**

```java
/*
     * 采用file.Transto 来保存上传的文件
     */
@RequestMapping("/upload2")
public String  fileUpload2(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {

    //上传路径保存设置
    String path = request.getServletContext().getRealPath("/upload");
    File realPath = new File(path);
    if (!realPath.exists()){
        realPath.mkdir();
    }
    //上传文件地址
    System.out.println("上传文件保存地址："+realPath);

    //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
    file.transferTo(new File(realPath +"/"+ file.getOriginalFilename()));

    return "redirect:/index.jsp";
}
```

> 文件下载

**文件下载步骤：**

1、设置 response 响应头

2、读取文件 -- InputStream

3、写出文件 -- OutputStream

4、执行操作

5、关闭流 （先开后关）

**代码实现：**

```java
@RequestMapping(value="/download")
public String downloads(HttpServletResponse response ,HttpServletRequest request) throws Exception{
    //要下载的图片地址
    String  path = request.getServletContext().getRealPath("/upload");
    String  fileName = "基础语法.jpg";

    //1、设置response 响应头
    response.reset(); //设置页面不缓存,清空buffer
    response.setCharacterEncoding("UTF-8"); //字符编码
    response.setContentType("multipart/form-data"); //二进制传输数据
    //设置响应头
    response.setHeader("Content-Disposition",
                       "attachment;fileName="+URLEncoder.encode(fileName, "UTF-8"));

    File file = new File(path,fileName);
    //2、 读取文件--输入流
    InputStream input=new FileInputStream(file);
    //3、 写出文件--输出流
    OutputStream out = response.getOutputStream();

    byte[] buff =new byte[1024];
    int index=0;
    //4、执行 写出操作
    while((index= input.read(buff))!= -1){
        out.write(buff, 0, index);
        out.flush();
    }
    out.close();
    input.close();
    return null;
}
```

前端

```html
<a href="/download">点击下载</a>
```





![ssm回顾](https://gitee.com/linda12138/picgo/raw/master/image/ssm%E5%9B%9E%E9%A1%BE.png)
