### 1、SpringBoot概述

------

#### 1.1、回顾什么是Spring

Spring是一个开源框架，2003 年兴起的一个轻量级的Java 开发框架，作者：Rod Johnson  。

**Spring是为了解决企业级应用开发的复杂性而创建的，简化开发。**

**
**

#### 1.2、Spring是如何简化Java开发的

为了降低Java开发的复杂性，Spring采用了以下4种关键策略：

1、基于POJO的轻量级和最小侵入性编程，所有东西都是bean；

2、通过IOC，依赖注入（DI）和面向接口实现松耦合；

3、基于切面（AOP）和惯例进行声明式编程；

4、通过切面和模版减少样式代码，RedisTemplate，xxxTemplate；



#### 1.3、什么是SpringBoot

​	学过javaweb的同学就知道，开发一个web应用，从最初开始接触Servlet结合Tomcat, 跑出一个Hello Wolrld程序，是要经历特别多的步骤；后来就用了框架Struts，再后来是SpringMVC，到了现在的SpringBoot，过一两年又会有其他web框架出现；你们有经历过框架不断的演进，然后自己开发项目所有的技术也在不断的变化、改造吗？建议都可以去经历一遍；

​	言归正传，什么是SpringBoot呢，就是一个javaweb的开发框架，和SpringMVC类似，对比其他javaweb框架的好处，官方说是简化开发，约定大于配置，  you can "just run"，能迅速的开发web应用，几行代码开发一个http接口。

​	所有的技术框架的发展似乎都遵循了一条主线规律：从一个复杂应用场景 衍生 一种规范框架，人们只需要进行各种配置而不需要自己去实现它，这时候强大的配置功能成了优点；发展到一定程度之后，人们根据实际生产应用情况，选取其中实用功能和设计精华，重构出一些轻量级的框架；之后为了提高开发效率，嫌弃原先的各类配置过于麻烦，于是开始提倡“约定大于配置”，进而衍生出一些一站式的解决方案。

​	是的这就是Java企业级应用->J2EE->spring->springboot的过程。

​	随着 Spring 不断的发展，涉及的领域越来越多，项目整合开发需要配合各种各样的文件，慢慢变得不那么易用简单，违背了最初的理念，甚至人称配置地狱。Spring Boot 正是在这样的一个背景下被抽象出来的开发框架，目的为了让大家更容易的使用 Spring 、更容易的集成各种常用的中间件、开源软件；

​	Spring Boot 基于 Spring 开发，Spirng Boot 本身并不提供 Spring 框架的核心特性以及扩展功能，只是用于快速、敏捷地开发新一代基于 Spring 框架的应用程序。也就是说，它并不是用来替代 Spring 的解决方案，而是和 Spring 框架紧密结合用于提升 Spring 开发者体验的工具。Spring Boot 以**约定大于配置的核心思想**，默认帮我们进行了很多设置，多数 Spring Boot 应用只需要很少的 Spring 配置。同时它集成了大量常用的第三方库配置（例如 Redis、MongoDB、Jpa、RabbitMQ、Quartz 等等），Spring Boot 应用中这些第三方库几乎可以零配置的开箱即用。

​	简单来说就是SpringBoot其实不是什么新的框架，它默认配置了很多框架的使用方式，就像maven整合了所有的jar包，spring boot整合了所有的框架 。

Spring Boot 出生名门，从一开始就站在一个比较高的起点，又经过这几年的发展，生态足够完善，Spring Boot 已经当之无愧成为 Java 领域最热门的技术。

**Spring Boot的主要优点：**

- 为所有Spring开发者更快的入门
- **开箱即用**，提供各种默认配置来简化项目配置
- 内嵌式容器简化Web项目
- 没有冗余代码生成和XML配置的要求

真的很爽，我们快速去体验开发个接口的感觉吧！



### 2、微服务

------

#### 2.1、什么是微服务

​	微服务是一种架构风格个它要求我们在开发一个应用的时候，这个应用必须构建成一系列小服务的组合：可以通过htt的方式进行互通。要说微服务架构，先得说说过去我们的单体应用架构。

#### 2.2、单体应用架构

​	所谓单体应用架构(all in one)是指，我们将一个应用的中的所有应用服务都封装在一个应用中。

​	无论是ERP、CRM或是其他什么系统，你都把数据库访问，web访问，等等各个功能放到一个war包内。

- 这样做的好处是，易于开发和测试：也十分方便部署；当需要扩展时，只需要将wr复制多份，然后放到多个服务器上，再做个负载均衡就可以了。
- 单体应用架构的缺点是，哪怕我要修改一个非常小的地方，我都需要停掉整个服务，重新打包、部署这个应用wr包。特别是对于一个大型应用，我们不可能吧所有内容都放在一个应用里面，我们如何维护、如何分工合作都是问题。

#### 2.3、微服务架构

​	all in one的架构方式，我们把所有的功能单元放在一个应用里面。然后我们把整个应用部署到服务器上。如果负载能力不行，我们将整个应用进行水平复制，进行扩展，然后在负载均衡。

​	所谓微服务架构，就是打破之前all in one的架构方式，把每个功能元素独立出来。把独立出来的功能元素的动态组合，需要的功能元素才去拿来组合，需要多一些时可以整合多个功能元素。所以微服务架构是对功能元素进行复制，而没有对整个应用进行复制。

​	这样做的好处是：

1. 节省了调用资源。
2. 每个功能元素的服务都是一个可替换的、可独立升级的软件代码。

![image-20220413092115664](https://gitee.com/linda12138/picgo/raw/master/image/image-20220413092115664.png)



#### 2.4、如何构建微服务

​	一个大型系统的微服务架构，就像一个复杂交织的神经网络，每一个神经元就是一个功能元素，它们各自完成自己的功能，然后通过htt相互请求调用。比如一个电商系统，查缓存、连数据库、浏览页面、结账、支付等服务都是一个个独立的功能服务，都被微化了，它们作为一个个微服务共同构建了一个庞大的系统。如果修改其中的一个功能，只需要更新升级其中一个功能服务单元即可。

​	但是这种庞大的系统架构给部署和运维带来很大的难度。于是，spring为我们带来了构建大型分布式微服务的全套、全程产品：

- 构建一个个功能独立的微服务应用单元，可以使用springboot,可以帮我们快速构建一个应用；
- 大型分布式网络服务的调用，这部分由spring cloud来完成，实现分布式
- 在分布式中间，进行流式数据计算、批处理，我们有spring cloud data flow。
- spring为我们想清楚了整个从开始构建应用到大型分布式应用全流程方案。

![image-20220413092804980](https://gitee.com/linda12138/picgo/raw/master/image/image-20220413092804980.png)



### 3、HelloWorld

------

​	我们将学习如何快速的创建一个Spring Boot应用，并且实现一个简单的Http请求处理。通过这个例子对Spring Boot有一个初步的了解，并体验其结构简单、开发快速的特性。

**我的环境准备：**

- java version "1.8.0_181"
- Maven-3.6.1
- SpringBoot : 最新版

**开发工具：**

- IDEA

#### 3.1、创建基础项目说明

Spring官方提供了非常方便的工具让我们快速构建应用

Spring Initializr：https://start.spring.io/

**项目创建方式一：**使用Spring Initializr 的 Web页面创建项目

1、打开  https://start.spring.io/

2、填写项目信息

3、点击”Generate Project“按钮生成项目；下载此项目

4、解压项目包，并用IDEA以Maven项目导入，一路下一步即可，直到项目导入完毕。

5、如果是第一次使用，可能速度会比较慢，包比较多、需要耐心等待一切就绪。


**项目结构分析：**

通过上面步骤完成了基础项目的创建。就会自动生成以下文件。

1、程序的主启动类

2、一个 application.properties 配置文件

3、一个 测试类

4、一个 pom.xml



#### 3.2、pom.xml分析

打开pom.xml，看看Spring Boot项目的依赖：

```xml
<!-- 父依赖 -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.5.RELEASE</version>
    <relativePath/>
</parent>

<dependencies>
    <!-- web场景启动器 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- springboot单元测试 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <!-- 剔除依赖 -->
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>

<build>
    <plugins>
        <!-- 打包插件 -->
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

- 项目元数据信息：创建时候输入的Project Metadata部分，也就是Maven项目的基本元素，包括：groupld、artifactld、version、name、description等
- parent:继承spring-boot-starter-.parent的依赖管理控制版本与打包等内容
- dependencies:项目具体依赖，这里包含了`spring-boot-starter-web`用于实现HTTP接口（该依赖中包含了Spring MVC),官网对它的描述是：使用Spring MVC构建Web(包括RESTful)应用程序的入门者，使用Tomcat作为默认嵌入式容器。；`spring-boot-starter-test`用于编写单元测试的依赖包。更多功能模块的使用我们将在后面逐步展开。
- build:构建配置部分。默认使用了`spring-boot-maven-plugin`,配合`spring-boot-starterparent`就可以把Spring Boot应用打包成jar来直接运行。



#### 3.3、编写一个http接口

1、在主程序的同级目录下，新建一个controller包，**一定要在同级目录下，否则识别不到**

2、在包中新建一个HelloController类

```java
//本身就是一个spring组件
//自动装配：原理
@RestController
public class HelloController {
    //接口：http://localhost:8080/hello
    @RequestMapping("/hello")
    public String hello(){
        //调用业务，接收前端参数
        return "hello,world";
    }
}
```

3、编写完毕后，从主程序启动项目，浏览器发起请求，看页面返回；控制台输出了 Tomcat 访问的端口号！



#### 3.4、将项目打成jar包，点击 maven的 package

![image-20220413103057678](https://gitee.com/linda12138/picgo/raw/master/image/image-20220413103057678.png)

如果遇到错误

```xml
<!--
    在工作中,很多情况下我们打包是不想执行测试用例的
    可能是测试用例不完事,或是测试用例会影响数据库数据
    跳过测试用例执
    -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <!--跳过项目运行测试用例-->
        <skipTests>true</skipTests>
    </configuration>
</plugin>
```



如果打包成功，则会在target目录下生成一个 jar 包

打成了jar包后，就可以在任何地方运行了！OK

![image-20220413103158492](C:\Users\鹏飞12138\AppData\Roaming\Typora\typora-user-images\image-20220413103158492.png)



==彩蛋==

如何更改启动时显示的字符拼成的字母，SpringBoot呢？也就是 banner 图案；

只需一步：到项目下的 resources 目录下新建一个banner.txt 即可。

图案可以到：https://www.bootschool.net/ascii 这个网站生成，然后拷贝到文件中即可！



### 4、原理初探

------

#### 4.1、pom.xml

> 父依赖

其中它主要是依赖一个父项目，主要是管理项目的资源过滤及插件！

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.6.6</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

点进去，发现还有一个父依赖

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.6.6</version>
    <relativePath>../../spring-boot-dependencies</relativePath>
</parent>
```

这里才是真正管理SpringBoot应用里面所有依赖版本的地方，SpringBoot的版本控制中心；

**以后我们导入依赖默认是不需要写版本；但是如果导入的包没有在依赖中管理着就需要手动配置版本了；**

#### 4.2、启动器 spring-boot-starter

```xml
<dependency>    					      <groupId>org.springframework.boot</groupId>    <artifactId>spring-boot-starter-web</artifactId></dependency>
```

**springboot-boot-starter-xxx**：就是spring-boot的场景启动器

**spring-boot-starter-web**：帮我们导入了web模块正常运行所依赖的组件；

SpringBoot将所有的功能场景都抽取出来，做成一个个的starter （启动器），只需要在项目中引入这些starter即可，所有相关的依赖都会导入进来 ， 我们要用什么功能就导入什么样的场景启动器即可 ；我们未来也可以自己自定义 starter；



#### 4.3、主启动类

```java
package com.peng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@SpringBootApplication 标注这是一个springboot的应用
@SpringBootApplication
public class Springboot01HelloworldApplication {

    public static void main(String[] args) {
        //将springboot启动
        SpringApplication.run(Springboot01HelloworldApplication.class, args);
    }

}
```

但是**一个简单的启动类并不简单！**我们来分析一下这些注解都干了什么

> @SpringBootApplication

作用：标注在某个类上说明这个类是SpringBoot的主配置类 ， SpringBoot就应该运行这个类的main方法来启动SpringBoot应用；

进入这个注解：可以看到上面还有很多其他注解！

```java
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
        type = FilterType.CUSTOM,
        classes = {TypeExcludeFilter.class}
    ), @Filter(
        type = FilterType.CUSTOM,
        classes = {AutoConfigurationExcludeFilter.class}
    )}
)
public @interface SpringBootApplication {
    // ......
}
```



> @ComponentScan

这个注解在Spring中很重要 ,它对应XML配置中的元素。

作用：自动扫描并加载符合条件的组件或者bean ， 将这个bean定义加载到IOC容器中



> @SpringBootConfiguration

作用：SpringBoot的配置类 ，标注在某个类上 ， 表示这是一个SpringBoot的配置类；

我们继续进去这个注解查看

```java
// 点进去得到下面的 @Component
@Configuration
public @interface SpringBootConfiguration {}

@Component
public @interface Configuration {}
```

这里的 @Configuration，说明这是一个配置类 ，配置类就是对应Spring的xml 配置文件；

里面的 @Component 这就说明，启动类本身也是Spring中的一个组件而已，负责启动应用！

我们回到 SpringBootApplication 注解中继续看。

> @EnableAutoConfiguration

**@EnableAutoConfiguration ：开启自动配置功能**

以前我们需要自己配置的东西，而现在SpringBoot可以自动帮我们配置 ；@EnableAutoConfiguration告诉SpringBoot开启自动配置功能，这样自动配置才能生效；

点进注解接续查看：

**@AutoConfigurationPackage ：自动配置包**

```java
@Import({Registrar.class})
public @interface AutoConfigurationPackage {
}
```

**@import** ：Spring底层注解@import ， 给容器中导入一个组件

Registrar.class 作用：将主启动类的所在包及包下面所有子包里面的所有组件扫描到Spring容器 ；

这个分析完了，退到上一步，继续看

**@Import({AutoConfigurationImportSelector.class}) ：给容器导入组件 ；**

AutoConfigurationImportSelector ：自动配置导入选择器，那么它会导入哪些组件的选择器呢？我们点击去这个类看源码：

1、这个类中有一个这样的方法

```java

// 获得候选的配置
protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
    //这里的getSpringFactoriesLoaderFactoryClass（）方法
    //返回的就是我们最开始看的启动自动导入配置文件的注解类；EnableAutoConfiguration
    List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
    Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct.");
    return configurations;
}
```

2、这个方法又调用了  SpringFactoriesLoader 类的静态方法！我们进入SpringFactoriesLoader类loadFactoryNames() 方法

```java
public static List<String> loadFactoryNames(Class<?> factoryClass, @Nullable ClassLoader classLoader) {
    String factoryClassName = factoryClass.getName();
    //这里它又调用了 loadSpringFactories 方法
    return (List)loadSpringFactories(classLoader).getOrDefault(factoryClassName, Collections.emptyList());
}
```

3、我们继续点击查看 loadSpringFactories 方法

```java
private static Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader) {
    //获得classLoader ， 我们返回可以看到这里得到的就是EnableAutoConfiguration标注的类本身
    MultiValueMap<String, String> result = (MultiValueMap)cache.get(classLoader);
    if (result != null) {
        return result;
    } else {
        try {
            //去获取一个资源 "META-INF/spring.factories"
            Enumeration<URL> urls = classLoader != null ? classLoader.getResources("META-INF/spring.factories") : ClassLoader.getSystemResources("META-INF/spring.factories");
            LinkedMultiValueMap result = new LinkedMultiValueMap();

            //将读取到的资源遍历，封装成为一个Properties
            while(urls.hasMoreElements()) {
                URL url = (URL)urls.nextElement();
                UrlResource resource = new UrlResource(url);
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                Iterator var6 = properties.entrySet().iterator();

                while(var6.hasNext()) {
                    Entry<?, ?> entry = (Entry)var6.next();
                    String factoryClassName = ((String)entry.getKey()).trim();
                    String[] var9 = StringUtils.commaDelimitedListToStringArray((String)entry.getValue());
                    int var10 = var9.length;

                    for(int var11 = 0; var11 < var10; ++var11) {
                        String factoryName = var9[var11];
                        result.add(factoryClassName, factoryName.trim());
                    }
                }
            }

            cache.put(classLoader, result);
            return result;
        } catch (IOException var13) {
            throw new IllegalArgumentException("Unable to load factories from location [META-INF/spring.factories]", var13);
        }
    }
}
```

4、发现一个多次出现的文件：spring.factories，全局搜索它

> spring.factories

我们根据源头打开spring.factories ， 看到了很多自动配置的文件；这就是自动配置根源所在！

![image-20220413112029158](https://gitee.com/linda12138/picgo/raw/master/image/image-20220413112029158.png)

**WebMvcAutoConfiguration**

我们在上面的自动配置类随便找一个打开看看，比如 WebMvcAutoConfiguration

![image-20220413112100923](https://gitee.com/linda12138/picgo/raw/master/image/image-20220413112100923.png)

可以看到这些一个个的都是JavaConfig配置类，而且都注入了一些Bean，可以找一些自己认识的类，看着熟悉一下！

所以，自动配置真正实现是从classpath中搜寻所有的META-INF/spring.factories配置文件 ，并将其中对应的 org.springframework.boot.autoconfigure. 包下的配置项，通过反射实例化为对应标注了 @Configuration的JavaConfig形式的IOC容器配置类 ， 然后将这些都汇总成为一个实例并加载到IOC容器中。

**结论：**

1. SpringBoot在启动的时候从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值
2. 将这些值作为自动配置类导入容器 ， 自动配置类就生效 ， 帮我们进行自动配置工作；
3. 整个J2EE的整体解决方案和自动配置都在springboot-autoconfigure的jar包中；
4. 它会给容器中导入非常多的自动配置类 （xxxAutoConfiguration）, 就是给容器中导入这个场景需要的所有组件 ， 并配置好这些组件 ；
5. 有了自动配置类 ， 免去了我们手动编写配置注入功能组件等的工作；

**现在大家应该大概的了解了下，SpringBoot的运行原理，后面我们还会深化一次！**



![zi'dong'pei'zhi](https://gitee.com/linda12138/picgo/raw/master/image/zi'dong'pei'zhi.png)

#### 4.4、SpringApplication

> 不简单的方法

我最初以为就是运行了一个main方法，没想到却开启了一个服务；

```java
@SpringBootApplication
public class SpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
```

**SpringApplication.run分析**

分析该方法主要分两部分，一部分是SpringApplication的实例化，二是run方法的执行；

> SpringApplication

**这个类主要做了以下四件事情：**

1、推断应用的类型是普通的项目还是Web项目

2、查找并加载所有可用初始化器 ， 设置到initializers属性中

3、找出所有的应用程序监听器，设置到listeners属性中

4、推断并设置main方法的定义类，找到运行的主类

查看构造器：

```java

public SpringApplication(ResourceLoader resourceLoader, Class... primarySources) {
    // ......
    this.webApplicationType = WebApplicationType.deduceFromClasspath();
    this.setInitializers(this.getSpringFactoriesInstances();
                         this.setListeners(this.getSpringFactoriesInstances(ApplicationListener.class));
                         this.mainApplicationClass = this.deduceMainApplicationClass();
                         }
```

>run方法流程分析

![640](https://gitee.com/linda12138/picgo/raw/master/image/640.jpg)



### 5、yaml配置

------

> 配置文件

SpringBoot使用一个全局的配置文件 ， 配置文件名称是固定的

- application.properties

- - 语法结构 ：key=value

- application.yml

- - 语法结构 ：key：空格 value

**配置文件的作用 ：**修改SpringBoot自动配置的默认值，因为SpringBoot在底层都给我们自动配置好了；

比如我们可以在配置文件中修改Tomcat 默认启动的端口号！测试一下！

```properties
server.port=8081
```

> yaml概述

YAML是 "YAML Ain't a Markup Language" （YAML不是一种标记语言）的递归缩写。在开发的这种语言时，YAML 的意思其实是："Yet Another Markup Language"（仍是一种标记语言）

**这种语言以数据**作为中心，而不是以标记语言为重点！

以前的配置文件，大多数都是使用xml来配置；比如一个简单的端口配置，我们来对比下yaml和xml

传统xml配置：

```xml
<server>
<port>8081<port>
</server>
```

> yaml基础语法

说明：语法要求严格！

1、空格不能省略

2、以缩进来控制层级关系，只要是左边对齐的一列数据都是同一个层级的。

3、属性和值的大小写都是十分敏感的。



**字面量：普通的值  [ 数字，布尔值，字符串  ]**

字面量直接写在后面就可以 ， 字符串默认不用加上双引号或者单引号；

```yaml
k: v
```

注意：

- “ ” 双引号，不会转义字符串里面的特殊字符 ， 特殊字符会作为本身想表示的意思；

  比如 ：name: "peng\n fei"  输出 ：peng 换行  fei

- '' 单引号，会转义特殊字符 ， 特殊字符最终会变成和普通字符一样输出

  比如 ：name: ‘peng \n fei’  输出 ：peng  \n  fei

  

> 基本写法

  ```yaml
  #修改springboot默认端口号
  server:
    port: 8081
  
  #对象
  student:
    name: pengfei
    age: 3
  
  #行内写法
  student1: {name: peng,age: 3}
  
  #数组
  pets:
    - cat
    - dog
    - pig
  
  pets1: [cat,dog,pig]
  ```

  

### 6、注入配置文件

------

yaml文件更强大的地方在于，他可以给我们的实体类直接注入匹配值！

#### 6.1、yaml注入配置文件

1、在springboot项目中的resources目录下新建一个文件 application.yml

2、编写一个实体类 Dog；

```java
package com.peng.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Dog {
    @Value("旺财")
    private String name;
    @Value("23")
    private Integer age;

    public Dog(){}

    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
```

3、思考，我们原来是如何给bean注入属性值的！@Value，给狗狗类测试一下：

```java
@Value("旺财")
private String name;
@Value("23")
private Integer age;
```

4、在SpringBoot的测试类下注入狗狗输出一下；

```java
@SpringBootTest
class DemoApplicationTests {

    @Autowired //将狗狗自动注入进来
    Dog dog;

    @Test
    public void contextLoads() {
        System.out.println(dog); //打印看下狗狗对象
    }
}
```

结果成功输出

5、我们在编写一个复杂一点的实体类：Person 类

```java
package com.peng.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private Integer age;
    private Boolean happy;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;

    public Person(String name, Integer age, Boolean happy, Date birth, Map<String, Object> maps, List<Object> lists, Dog dog) {
        this.name = name;
        this.age = age;
        this.happy = happy;
        this.birth = birth;
        this.maps = maps;
        this.lists = lists;
        this.dog = dog;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", happy=" + happy +
            ", birth=" + birth +
            ", maps=" + maps +
            ", lists=" + lists +
            ", dog=" + dog +
            '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getHappy() {
        return happy;
    }

    public void setHappy(Boolean happy) {
        this.happy = happy;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
```

6、我们来使用yaml配置的方式进行注入，大家写的时候注意区别和优势，我们编写一个yaml配置！

```yaml
person:
name: pengfei
age: 15
happy: false
birth: 2022/04/13
maps: {k1: v1,k2: v2}
lists:
- code
- music
- girl
dog:
name: 旺财
age: 3
```

7、我们刚才已经把person这个对象的所有值都写好了，我们现在来注入到我们的类中！

```java
/*
@ConfigurationProperties作用：
将配置文件中配置的每一个属性的值，映射到这个组件中；
告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
参数 prefix = “person” : 将配置文件中的person下面的所有属性一一对应
*/
@Component //注册bean
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private Integer age;
    private Boolean happy;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;
}
```

8、IDEA 提示，springboot配置注解处理器没有找到，让我们看文档，我们可以查看文档，找到一个依赖！

```xml
<!-- 导入配置文件处理器，配置文件进行绑定就会有提示，需要重启 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

9、确认以上配置都OK之后，我们去测试类中测试一下：

```java
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    Person person; //将person自动注入进来

    @Test
    public void contextLoads() {
        System.out.println(person); //打印person信息
    }
}
```

结果：所有值全部注入成功！

![image-20220413171054663](https://gitee.com/linda12138/picgo/raw/master/image/image-20220413171054663.png)

**yaml配置注入到实体类完全OK！**

课堂测试：

1、将配置文件的key 值 和 属性的值设置为不一样，则结果输出为null，注入失败

2、在配置一个person2，然后将 @ConfigurationProperties(prefix = "person2") 指向我们的person2；



#### 6.2、加载指定的配置文件

**@PropertySource ：**加载指定的配置文件；

**@configurationProperties**：默认从全局配置文件中获取值；

1、我们去在resources目录下新建一个**person.properties**文件

```properties
name=pengfei
```

2、然后在我们的代码中指定加载person.properties文件

```java
@PropertySource(value = "classpath:person.properties")
@Component //注册bean
public class Person {

    @Value("${name}")
    private String name;

    ......  
}
```

3、再次输出测试一下：指定配置文件绑定成功！



#### 6.3、配置文件占位符

```yaml
person:
    name: ${random.uuid} # 随机uuid
    age: ${random.int}  # 随机int
    happy: false
    birth: 2000/01/01
    maps: {k1: v1,k2: v2}
    lists:
      - code
      - girl
      - music
    dog:
      name: ${person.hello:other}_旺财
      age: 1
```

#### 6.4、回顾properties配置

我们上面采用的yaml方法都是最简单的方式，开发中最常用的；也是springboot所推荐的！那我们来唠唠其他的实现方式，道理都是相同的；写还是那样写；配置文件除了yml还有我们之前常用的properties ， 我们没有讲，我们来唠唠！

【注意】properties配置文件在写中文的时候，会有乱码 ， 我们需要去IDEA中设置编码格式为UTF-8；

settings-->FileEncodings 中配置；

![image-20220413172430657](https://gitee.com/linda12138/picgo/raw/master/image/image-20220413172430657.png)



**测试步骤：**

1、新建一个实体类User

```java
@Component //注册bean
public class User {
    private String name;
    private int age;
    private String sex;
}
```

2、编辑配置文件 user.properties

```properties
user1.name=pengfei
user1.age=18
user1.sex=男
```

3、我们在User类上使用@Value来进行注入！

```java
@Component //注册bean
@PropertySource(value = "classpath:user.properties")
public class User {
    //直接使用@value
    @Value("${user.name}") //从配置文件中取值
    private String name;
    @Value("#{9*2}")  // #{SPEL} Spring表达式
    private int age;
    @Value("男")  // 字面量
    private String sex;
}
```

4、Springboot测试

```java
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    User user;

    @Test
    public void contextLoads() {
        System.out.println(user);
    }

}
```

结果正常输出



#### 6.5、对比小结

@Value这个使用起来并不友好！我们需要为每个属性单独注解赋值，比较麻烦；我们来看个功能对比图

|                      | @ConfigurationProperties | @Value     |
| -------------------- | ------------------------ | ---------- |
| 功能                 | 批量注入配置文件中的属性 | 一个个指定 |
| 松散绑定（松散语法） | 支持                     | 不支持     |
| SpEL                 | 不支持                   | 支持       |
| JSR303数据校验       | 支持                     | 不支持     |
| 复杂类型封装         | 支持                     | 不支持     |

1、@ConfigurationProperties只需要写一次即可 ， @Value则需要每个字段都添加

2、松散绑定：这个什么意思呢? 比如我的yml中写的last-name，这个和lastName是一样的， - 后面跟着的字母默认是大写的。这就是松散绑定。可以测试一下

3、JSR303数据校验 ， 这个就是我们可以在字段是增加一层过滤器验证 ， 可以保证数据的合法性

4、复杂类型封装，yml中可以封装对象 ， 使用value就不支持

**结论：**

配置yml和配置properties都可以获取到值 ， 强烈推荐 yml；

如果我们在某个业务中，只需要获取配置文件中的某个值，可以使用一下 @value；

如果说，我们专门编写了一个JavaBean来和配置文件进行一一映射，就直接@configurationProperties，不要犹豫！



### 7、JSR303数据校验

------

Springboot中可以用@validated来校验数据，如果数据异常则会统一抛出异常，方便异常中心统一处理。我们这里来写个注解让我们的name只能支持Email格式；

需要导入依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

```java
@Component //注册bean
@ConfigurationProperties(prefix = "person")
@Validated  //数据校验
public class Person {

    @Email(message="邮箱格式错误") //name必须是邮箱格式
    private String name;
}
```

运行结果 ：default message [不是一个合法的电子邮件地址];

**使用数据校验，可以保证数据的正确性；**

> 常见参数

```java
@NotNull(message="名字不能为空")
private String userName;
@Max(value=120,message="年龄最大不能查过120")
private int age;
@Email(message="邮箱格式错误")
private String email;

空检查
    @Null       验证对象是否为null
    @NotNull    验证对象是否不为null, 无法查检长度为0的字符串
    @NotBlank   检查约束字符串是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
    @NotEmpty   检查约束元素是否为NULL或者是EMPTY.

    Booelan检查
    @AssertTrue     验证 Boolean 对象是否为 true  
    @AssertFalse    验证 Boolean 对象是否为 false  

    长度检查
    @Size(min=, max=) 验证对象（Array,Collection,Map,String）长度是否在给定的范围之内  
    @Length(min=, max=) string is between min and max included.

    日期检查
    @Past       验证 Date 和 Calendar 对象是否在当前时间之前  
    @Future     验证 Date 和 Calendar 对象是否在当前时间之后  
    @Pattern    验证 String 对象是否符合正则表达式的规则

    .......等等
    除此以外，我们还可以自定义一些数据校验规则
```

### 8、多环境切换

------

profile是Spring对不同环境提供不同配置功能的支持，可以通过激活不同的环境版本，实现快速切换环境；

> 多配置文件

我们在主配置文件编写的时候，文件名可以是 application-{profile}.properties/yml , 用来指定多个环境版本；

**例如：**

application-test.properties 代表测试环境配置

application-dev.properties 代表开发环境配置

但是Springboot并不会直接启动这些配置文件，它**默认使用application.properties主配置文件**；

我们需要通过一个配置来选择需要激活的环境：

> yaml的多文档块

和properties配置文件中一样，但是使用yml去实现不需要创建多个配置文件，更加方便了 !

```yaml
server:
  port: 8081
#选择要激活那个环境块
spring:
  profiles:
    active: prod

---
server:
  port: 8083
spring:
  profiles: dev #配置环境的名称


---

server:
  port: 8084
spring:
  profiles: prod  #配置环境的名称
```

**注意：如果yml和properties同时都配置了端口，并且没有激活其他环境 ， 默认会使用properties配置文件的！**

> 配置文件加载位置

**外部加载配置文件的方式十分多，我们选择最常用的即可，在开发的资源文件中进行配置！**

官方外部配置文件说明参考文档

springboot 启动会扫描以下位置的application.properties或者application.yml文件作为Spring boot的默认配置文件：

```
优先级1：项目路径下的config文件夹配置文件
优先级2：项目路径下配置文件
优先级3：资源路径下的config文件夹配置文件
优先级4：资源路径下配置文件
```

优先级由高到底，高优先级的配置会覆盖低优先级的配置；

**SpringBoot会从这四个位置全部加载主配置文件；互补配置；**

我们在最低级的配置文件中设置一个项目访问路径的配置来测试互补问题；

```properties
#配置项目的访问路径
server.servlet.context-path=/peng
```

指定位置加载配置文件

我们还可以通过spring.config.location来改变默认的配置文件位置

项目打包好以后，我们可以使用命令行参数的形式，启动项目的时候来指定配置文件的新位置；这种情况，一般是后期运维做的多，相同配置，外部指定的配置文件优先级最高

```bash
java -jar spring-boot-config.jar --spring.config.location=F:/application.properties
```

### 9、自动配置原理

------

配置文件到底能写什么？怎么写？

SpringBoot官方文档中有大量的配置，我们无法全部记住

![image-20220414114410172](https://gitee.com/linda12138/picgo/raw/master/images/image-20220414114410172.png)



> 分析自动配置原理

我们以**HttpEncodingAutoConfiguration（Http编码自动配置）**为例解释自动配置原理；

```java
//表示这是一个配置类，和以前编写的配置文件一样，也可以给容器中添加组件；
@Configuration 

//启动指定类的ConfigurationProperties功能；
//进入这个HttpProperties查看，将配置文件中对应的值和HttpProperties绑定起来；
//并把HttpProperties加入到ioc容器中
@EnableConfigurationProperties({HttpProperties.class}) 

//Spring底层@Conditional注解
//根据不同的条件判断，如果满足指定的条件，整个配置类里面的配置就会生效；
//这里的意思就是判断当前应用是否是web应用，如果是，当前配置类生效
@ConditionalOnWebApplication(
    type = Type.SERVLET
)

//判断当前项目有没有这个类CharacterEncodingFilter；SpringMVC中进行乱码解决的过滤器；
@ConditionalOnClass({CharacterEncodingFilter.class})

//判断配置文件中是否存在某个配置：spring.http.encoding.enabled；
//如果不存在，判断也是成立的
//即使我们配置文件中不配置pring.http.encoding.enabled=true，也是默认生效的；
@ConditionalOnProperty(
    prefix = "spring.http.encoding",
    value = {"enabled"},
    matchIfMissing = true
)

public class HttpEncodingAutoConfiguration {
    //他已经和SpringBoot的配置文件映射了
    private final Encoding properties;
    //只有一个有参构造器的情况下，参数的值就会从容器中拿
    public HttpEncodingAutoConfiguration(HttpProperties properties) {
        this.properties = properties.getEncoding();
    }

    //给容器中添加一个组件，这个组件的某些值需要从properties中获取
    @Bean
    @ConditionalOnMissingBean //判断容器没有这个组件？
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding(this.properties.getCharset().name());
        filter.setForceRequestEncoding(this.properties.shouldForce(org.springframework.boot.autoconfigure.http.HttpProperties.Encoding.Type.REQUEST));
        filter.setForceResponseEncoding(this.properties.shouldForce(org.springframework.boot.autoconfigure.http.HttpProperties.Encoding.Type.RESPONSE));
        return filter;
    }
    //。。。。。。。
}
```

**一句话总结 ：根据当前不同的条件判断，决定这个配置类是否生效！**

- 一但这个配置类生效；这个配置类就会给容器中添加各种组件；
- 这些组件的属性是从对应的properties类中获取的，这些类里面的每一个属性又是和配置文件绑定的；
- 所有在配置文件中能配置的属性都是在xxxxProperties类中封装着；
- 配置文件能配置什么就可以参照某个功能对应的这个属性类

```java
//从配置文件中获取指定的值和bean的属性进行绑定
@ConfigurationProperties(prefix = "spring.http") 
public class HttpProperties {
    // .....
}
```

我们去配置文件里面试试前缀，看提示！

![image-20220414115453904](https://gitee.com/linda12138/picgo/raw/master/images/image-20220414115453904.png)

**这就是自动装配的原理！**

> 精髓

1、SpringBoot启动会加载大量的自动配置类

2、我们看我们需要的功能有没有在SpringBoot默认写好的自动配置类当中；

3、我们再来看这个自动配置类中到底配置了哪些组件；（只要我们要用的组件存在在其中，我们就不需要再手动配置了）

4、给容器中自动配置类添加组件的时候，会从properties类中获取某些属性。我们只需要在配置文件中指定这些属性的值即可；

**xxxxAutoConfigurartion：自动配置类；**给容器中添加组件

**xxxxProperties:封装配置文件中相关属性；**



> 了解：@Conditional

了解完自动装配的原理后，我们来关注一个细节问题，**自动配置类必须在一定的条件下才能生效；**

**@Conditional派生注解（Spring注解版原生的@Conditional作用）**

作用：必须是@Conditional指定的条件成立，才给容器中添加组件，配置配里面的所有内容才生效；

| @Conditional扩展注解            | 作用（判断是否满足当前指定条件）                |
| ------------------------------- | ----------------------------------------------- |
| @ConditionalOnBean              | 容器中存在指定Bean;                             |
| @ConditionalMissingBean         | 容器中不存在指定Bean;                           |
| @ConditionalOnExpression        | 满足SpEl表达式指定                              |
| @ConditionalOnClass             | 系统中有指定的类                                |
| @ConditionalOnMissingClass      | 系统中没有指定的类                              |
| @ConditionalOnSingleCandidate   | 容器中只有一个指定的Bean,或者这个Bean是首选Bean |
| @ConditionalOnProperty          | 系统中指定的属性是否有指定的值                  |
| @ConditionalOnResource          | 类路径下是否存在指定资源文件                    |
| @ConditionalOnWebApplication    | 当前是web环境                                   |
| @ConditionalOnNotWebApplication | 当前不是web环境                                 |
| @ConditionalOnjndi              | JNDI存在指定项                                  |
| @ConditionalOnjava              | 系统的Java版本是否符合要求                      |

**那么多的自动配置类，必须在一定的条件下才能生效；也就是说，我们加载了这么多的配置类，但不是所有的都生效了。**

我们怎么知道哪些自动配置类生效？

**我们可以通过启用 debug=true属性；来让控制台打印自动配置报告，这样我们就可以很方便的知道哪些自动配置类生效；**

```yaml
#开启springboot的调试类
debug=true
```

**Positive matches:（自动配置类启用的：正匹配）**

**Negative matches:（没有启动，没有匹配成功的自动配置类：负匹配）**

**Unconditional classes: （没有条件的类）**

【演示：查看输出的日志】

掌握吸收理解原理，即可以不变应万变！



### 10、SpringBoot Web开发

------

#### 10.1、简介

好的，那么接下来呢，我们开始学习SpringBoot与Web开发，从这一章往后，就属于我们实战部分的内容了；

其实SpringBoot的东西用起来非常简单，因为SpringBoot最大的特点就是自动装配。

**使用SpringBoot的步骤：**

1、创建一个SpringBoot应用，选择我们需要的模块，SpringBoot就会默认将我们的需要的模块自动配置好

2、手动在配置文件中配置部分配置项目就可以运行起来了

3、专注编写业务代码，不需要考虑以前那样一大堆的配置了。

要熟悉掌握开发，之前学习的自动配置的原理一定要搞明白！

比如SpringBoot到底帮我们配置了什么？我们能不能修改？我们能修改哪些配置？我们能不能扩展？

- 向容器中自动配置组件 ：xxxAutoconfiguration
- 自动配置类，封装配置文件的内容：xxxProperties

没事就找找类，看看自动装配原理！

我们之后来进行一个单体项目的小项目测试，让大家能够快速上手开发！



#### 10.2、静态资源

> 静态资源映射规则

**首先，我们搭建一个普通的SpringBoot项目，回顾一下HelloWorld程序！**

写请求非常简单，那我们要引入我们前端资源，我们项目中有许多的静态资源，比如css，js等文件，这个SpringBoot怎么处理呢？

如果我们是一个web应用，我们的main下会有一个webapp，我们以前都是将所有的页面导在这里面的，对吧！但是我们现在的pom呢，打包方式是为jar的方式，那么这种方式SpringBoot能不能来给我们写页面呢？当然是可以的，但是SpringBoot对于静态资源放置的位置，是有规定的！

**我们先来聊聊这个静态资源映射规则：**

SpringBoot中，SpringMVC的web配置都在 WebMvcAutoConfiguration 这个配置类里面；

我们可以去看看 WebMvcAutoConfigurationAdapter 中有很多配置方法；

有一个方法：addResourceHandlers 添加资源处理

```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!this.resourceProperties.isAddMappings()) {
        // 已禁用默认资源处理
        logger.debug("Default resource handling disabled");
        return;
    }
    // 缓存控制
    Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
    CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
    // webjars 配置
    if (!registry.hasMappingForPattern("/webjars/**")) {
        customizeResourceHandlerRegistration(registry.addResourceHandler("/webjars/**")
                                             .addResourceLocations("classpath:/META-INF/resources/webjars/")
                                             .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
    }
    // 静态资源配置
    String staticPathPattern = this.mvcProperties.getStaticPathPattern();
    if (!registry.hasMappingForPattern(staticPathPattern)) {
        customizeResourceHandlerRegistration(registry.addResourceHandler(staticPathPattern)
                                             .addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations()))
                                             .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
    }
}
```

读一下源代码：比如所有的 /webjars/** ， 都需要去 classpath:/META-INF/resources/webjars/ 找对应的资源；

> 什么是webjars呢？

Webjars本质就是以jar包的方式引入我们的静态资源 ， 我们以前要导入一个静态资源文件，直接导入即可。

使用SpringBoot需要使用Webjars，我们可以去搜索一下：

网站：https://www.webjars.org 

要使用jQuery，我们只要要引入jQuery对应版本的pom依赖即可！

```xml
  <dependency>
            <groupId>org.webjars.npm</groupId>
            <artifactId>jquery</artifactId>
            <version>3.6.0</version>
        </dependency>
```

导入完毕，查看webjars目录结构，并访问Jquery.js文件！

![image-20220414160137599](https://gitee.com/linda12138/picgo/raw/master/images/image-20220414160137599.png)

访问：只要是静态资源，SpringBoot就会去对应的路径寻找资源，我们这里访问：http://localhost:8080/webjars/jquery/3.6.0/dist/jquery.js

![image-20220414160311642](https://gitee.com/linda12138/picgo/raw/master/images/image-20220414160311642.png)



> 第二种静态资源映射规则

那我们项目中要是使用自己的静态资源该怎么导入呢？我们看下一行代码；

我们去找staticPathPattern发现第二种映射规则 ：/** , 访问当前的项目任意资源，它会去找 resourceProperties 这个类，我们可以点进去看一下分析：

```java
// 进入方法
public String[] getStaticLocations() {
    return this.staticLocations;
}
// 找到对应的值
private String[] staticLocations = CLASSPATH_RESOURCE_LOCATIONS;
// 找到路径
private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { 
    "classpath:/META-INF/resources/",
    "classpath:/resources/", 
    "classpath:/static/", 
    "classpath:/public/" 
};
```

ResourceProperties 可以设置和我们静态资源有关的参数；这里面指向了它会去寻找资源的文件夹，即上面数组的内容。

所以得出结论，以下四个目录存放的静态资源可以被我们识别：

```java
"classpath:/META-INF/resources/"
"classpath:/resources/"
"classpath:/static/"
"classpath:/public/"
```

我们可以在resources根目录下新建对应的文件夹，都可以存放我们的静态文件；

比如我们访问 http://localhost:8080/1.js , 他就会去这些文件夹中寻找对应的静态资源文件；

> 自定义静态资源路径

我们也可以自己通过配置文件来指定一下，哪些文件夹是需要我们放静态资源文件的，在application.properties中配置；

```properties
spring.resources.static-locations=classpath:/coding/,classpath:/peng/
```

一旦自己定义了静态文件夹的路径，原来的自动配置就都会失效了！



#### 10.3、首页处理

静态资源文件夹说完后，我们继续向下看源码！可以看到一个欢迎页的映射，就是我们的首页！

```java
@Bean
public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext,
                                                           FormattingConversionService mvcConversionService,
                                                           ResourceUrlProvider mvcResourceUrlProvider) {
    WelcomePageHandlerMapping welcomePageHandlerMapping = new WelcomePageHandlerMapping(
        new TemplateAvailabilityProviders(applicationContext), applicationContext, getWelcomePage(), // getWelcomePage 获得欢迎页
        this.mvcProperties.getStaticPathPattern());
    welcomePageHandlerMapping.setInterceptors(getInterceptors(mvcConversionService, mvcResourceUrlProvider));
    return welcomePageHandlerMapping;
}
```

点进去继续看

```java
private Optional<Resource> getWelcomePage() {
    String[] locations = getResourceLocations(this.resourceProperties.getStaticLocations());
    // ::是java8 中新引入的运算符
    // Class::function的时候function是属于Class的，应该是静态方法。
    // this::function的funtion是属于这个对象的。
    // 简而言之，就是一种语法糖而已，是一种简写
    return Arrays.stream(locations).map(this::getIndexHtml).filter(this::isReadable).findFirst();
}
// 欢迎页就是一个location下的的 index.html 而已
private Resource getIndexHtml(String location) {
    return this.resourceLoader.getResource(location + "index.html");
}
```

欢迎页，静态资源文件夹下的所有 index.html 页面；被 /** 映射。

比如我访问  http://localhost:8080/ ，就会找静态资源文件夹下的 index.html

新建一个 index.html ，在我们上面的3个目录中任意一个；然后访问测试  http://localhost:8080/  看结果！



### 11、Thymeleaf引擎

------

> 模板引擎

前端交给我们的页面，是html页面。如果是我们以前开发，我们需要把他们转成jsp页面，jsp好处就是当我们查出一些数据转发到JSP页面以后，我们可以用jsp轻松实现数据的显示，及交互等。

jsp支持非常强大的功能，包括能写Java代码，但是呢，我们现在的这种情况，SpringBoot这个项目首先是以jar的方式，不是war，像第二，我们用的还是嵌入式的Tomcat，所以呢，**他现在默认是不支持jsp的**。

那不支持jsp，如果我们直接用纯静态页面的方式，那给我们开发会带来非常大的麻烦，那怎么办呢？

**SpringBoot推荐你可以来使用模板引擎：**

模板引擎，我们其实大家听到很多，其实jsp就是一个模板引擎，还有用的比较多的freemarker，包括SpringBoot给我们推荐的Thymeleaf，模板引擎有非常多，但再多的模板引擎，他们的思想都是一样的，什么样一个思想呢我们来看一下这张图：

![图片](https://gitee.com/linda12138/picgo/raw/master/images/640)

模板引擎的作用就是我们来写一个页面模板，比如有些值呢，是动态的，我们写一些表达式。而这些值，从哪来呢，就是我们在后台封装一些数据。然后把这个模板和这个数据交给我们模板引擎，模板引擎按照我们这个数据帮你把这表达式解析、填充到我们指定的位置，然后把这个数据最终生成一个我们想要的内容给我们写出去，这就是我们这个模板引擎，不管是jsp还是其他模板引擎，都是这个思想。只不过呢，就是说不同模板引擎之间，他们可能这个语法有点不一样。其他的我就不介绍了，我主要来介绍一下SpringBoot给我们推荐的Thymeleaf模板引擎，这模板引擎呢，是一个高级语言的模板引擎，他的这个语法更简单。而且呢，功能更强大。

我们呢，就来看一下这个模板引擎，那既然要看这个模板引擎。首先，我们来看SpringBoot里边怎么用。

> 引入Thymeleaf

怎么引入呢，对于springboot来说，什么事情不都是一个start的事情嘛，我们去在项目中引入一下。给大家三个网址：

Thymeleaf 官网：https://www.thymeleaf.org/

Thymeleaf 在Github 的主页：https://github.com/thymeleaf/thymeleaf

Spring官方文档：找到我们对应的版本

https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#using-boot-starter 

找到对应的pom依赖：可以适当点进源码看下本来的包！

```xml
<!--thymeleaf-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

Maven会自动下载jar包，我们可以去看下下载的东西；

![image-20220414164745141](https://gitee.com/linda12138/picgo/raw/master/images/image-20220414164745141.png)

> Thymeleaf分析

前面呢，我们已经引入了Thymeleaf，那这个要怎么使用呢？

我们首先得按照SpringBoot的自动配置原理看一下我们这个Thymeleaf的自动配置规则，在按照那个规则，我们进行使用。

我们去找一下Thymeleaf的自动配置类：ThymeleafProperties

```java
@ConfigurationProperties(
    prefix = "spring.thymeleaf"
)
public class ThymeleafProperties {
    private static final Charset DEFAULT_ENCODING;
    public static final String DEFAULT_PREFIX = "classpath:/templates/";
    public static final String DEFAULT_SUFFIX = ".html";
    private boolean checkTemplate = true;
    private boolean checkTemplateLocation = true;
    private String prefix = "classpath:/templates/";
    private String suffix = ".html";
    private String mode = "HTML";
    private Charset encoding;
}
```

我们可以在其中看到默认的前缀和后缀！

我们只需要把我们的html页面放在类路径下的templates下，thymeleaf就可以帮我们自动渲染了。

使用thymeleaf什么都不需要配置，只需要将他放在指定的文件夹下即可！

**测试**

1. 编写一个TestController

   ```java
   package com.peng.controller;
   
   import org.springframework.stereotype.Controller;
   import org.springframework.ui.Model;
   import org.springframework.web.bind.annotation.RequestMapping;
   
   //在templates目录下的所有页面，只能通过controller访问
   //需要模板引擎的支持 thymeleaf
   @Controller
   public class IndexController {
       @RequestMapping("/test")
       public String index(Model model){
           model.addAttribute("msg","hello");
           return "test";
       }
   }
   ```

   

2. 编写一个测试页面  test.html 放在 templates 目录下

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
   <head>
       <meta charset="UTF-8">
       <title>Title</title>
   </head>
   <body>
   <!--所有的html元素都可以被thymeleaf替换接管  th:元素名-->
   <div th:text="${msg}"></div>
   </body>
   </html>
   ```

   

3. 启动测试



> Thymeleaf语法学习

要学习语法，还是参考官网文档最为准确，我们找到对应的版本看一下；

Thymeleaf 官网：https://www.thymeleaf.org/ ， 简单看一下官网！我们去下载Thymeleaf的官方文档！

**我们做个最简单的练习 ：我们需要查出一些数据，在页面中展示**

1、修改测试请求，增加数据传输；

```java
package com.peng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//在templates目录下的所有页面，只能通过controller访问
//需要模板引擎的支持 thymeleaf
@Controller
public class IndexController {
    @RequestMapping("/test")
    public String index(Model model){
        model.addAttribute("msg","hello");
        return "test";
    }
}
```

2、我们要使用thymeleaf，需要在html文件中导入命名空间的约束，方便提示。

我们可以去官方文档的#3中看一下命名空间拿来过来：

```html
xmlns:th="http://www.thymeleaf.org"
```

3、我们去编写下前端页面

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <!--所有的html元素都可以被thymeleaf替换接管  th:元素名-->
        <div th:text="${msg}"></div>
    </body>
</html>
```

4、启动测试

![image-20220414165236116](https://gitee.com/linda12138/picgo/raw/master/images/image-20220414165236116.png)

**OK，入门搞定，我们来认真研习一下Thymeleaf的使用语法！**

**1、我们可以使用任意的 th:attr 来替换Html中原生属性的值！**

![image-20220414165638249](https://gitee.com/linda12138/picgo/raw/master/images/image-20220414165638249.png)

**2、我们能写哪些表达式呢？**

```html

Simple expressions:（表达式语法）
Variable Expressions: ${...}：获取变量值；OGNL；
    1）、获取对象的属性、调用方法
    2）、使用内置的基本对象：#18
         #ctx : the context object.
         #vars: the context variables.
         #locale : the context locale.
         #request : (only in Web Contexts) the HttpServletRequest object.
         #response : (only in Web Contexts) the HttpServletResponse object.
         #session : (only in Web Contexts) the HttpSession object.
         #servletContext : (only in Web Contexts) the ServletContext object.

    3）、内置的一些工具对象：
　　　　　　#execInfo : information about the template being processed.
　　　　　　#uris : methods for escaping parts of URLs/URIs
　　　　　　#conversions : methods for executing the configured conversion service (if any).
　　　　　　#dates : methods for java.util.Date objects: formatting, component extraction, etc.
　　　　　　#calendars : analogous to #dates , but for java.util.Calendar objects.
　　　　　　#numbers : methods for formatting numeric objects.
　　　　　　#strings : methods for String objects: contains, startsWith, prepending/appending, etc.
　　　　　　#objects : methods for objects in general.
　　　　　　#bools : methods for boolean evaluation.
　　　　　　#arrays : methods for arrays.
　　　　　　#lists : methods for lists.
　　　　　　#sets : methods for sets.
　　　　　　#maps : methods for maps.
　　　　　　#aggregates : methods for creating aggregates on arrays or collections.
==================================================================================

  Selection Variable Expressions: *{...}：选择表达式：和${}在功能上是一样；
  Message Expressions: #{...}：获取国际化内容
  Link URL Expressions: @{...}：定义URL；
  Fragment Expressions: ~{...}：片段引用表达式

Literals（字面量）
      Text literals: 'one text' , 'Another one!' ,…
      Number literals: 0 , 34 , 3.0 , 12.3 ,…
      Boolean literals: true , false
      Null literal: null
      Literal tokens: one , sometext , main ,…
      
Text operations:（文本操作）
    String concatenation: +
    Literal substitutions: |The name is ${name}|
    
Arithmetic operations:（数学运算）
    Binary operators: + , - , * , / , %
    Minus sign (unary operator): -
    
Boolean operations:（布尔运算）
    Binary operators: and , or
    Boolean negation (unary operator): ! , not
    
Comparisons and equality:（比较运算）
    Comparators: > , < , >= , <= ( gt , lt , ge , le )
    Equality operators: == , != ( eq , ne )
    
Conditional operators:条件运算（三元运算符）
    If-then: (if) ? (then)
    If-then-else: (if) ? (then) : (else)
    Default: (value) ?: (defaultvalue)
    
Special tokens:
    No-Operation: _
```

**练习测试：**

1. controller

   ```java
   package com.peng.controller;
   
   import org.springframework.stereotype.Controller;
   import org.springframework.ui.Model;
   import org.springframework.web.bind.annotation.RequestMapping;
   
   import java.util.Arrays;
   
   //在templates目录下的所有页面，只能通过controller访问
   //需要模板引擎的支持 thymeleaf
   @Controller
   public class IndexController {
       @RequestMapping("/test")
       public String index(Model model){
           model.addAttribute("msg","<h1>hello</h1>");
           model.addAttribute("users", Arrays.asList("鹏飞","聂梅","哈哈哈"));
           return "test";
       }
   }
   ```

   

2. 页面

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
       <head>
           <meta charset="UTF-8">
           <title>Title</title>
       </head>
       <body>
           <!--所有的html元素都可以被thymeleaf替换接管  th:元素名-->
           <div th:text="${msg}"></div>
           <div th:utext="${msg}"></div>
           <hr>
           <div th:each="user:${users}" th:text="${user}"></div>
           <!--<div th:each="user:${users}">[[ ${user} ]]</div>-->
       </body>
   </html>
   ```

   

3. 测试

**我们看完语法，很多样式，我们即使现在学习了，也会忘记，所以我们在学习过程中，需要使用什么，根据官方文档来查询，才是最重要的，要熟练使用官方文档**



### 12、MVC自动装配原理

------

> 官网阅读

在进行项目编写前，我们还需要知道一个东西，就是SpringBoot对我们的SpringMVC还做了哪些配置，包括如何扩展，如何定制。

只有把这些都搞清楚了，我们在之后使用才会更加得心应手。途径一：源码分析，途径二：官方文档！

地址 ：https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-auto-configuration

```java

Spring MVC Auto-configuration
// Spring Boot为Spring MVC提供了自动配置，它可以很好地与大多数应用程序一起工作。
Spring Boot provides auto-configuration for Spring MVC that works well with most applications.
// 自动配置在Spring默认设置的基础上添加了以下功能：
The auto-configuration adds the following features on top of Spring’s defaults:
// 包含视图解析器
Inclusion of ContentNegotiatingViewResolver and BeanNameViewResolver beans.
// 支持静态资源文件夹的路径，以及webjars
Support for serving static resources, including support for WebJars 
// 自动注册了Converter：
// 转换器，这就是我们网页提交数据到后台自动封装成为对象的东西，比如把"1"字符串自动转换为int类型
// Formatter：【格式化器，比如页面给我们了一个2019-8-10，它会给我们自动格式化为Date对象】
Automatic registration of Converter, GenericConverter, and Formatter beans.
// HttpMessageConverters
// SpringMVC用来转换Http请求和响应的的，比如我们要把一个User对象转换为JSON字符串，可以去看官网文档解释；
Support for HttpMessageConverters (covered later in this document).
// 定义错误代码生成规则的
Automatic registration of MessageCodesResolver (covered later in this document).
// 首页定制
Static index.html support.
// 图标定制
Custom Favicon support (covered later in this document).
// 初始化数据绑定器：帮我们把请求数据绑定到JavaBean中！
Automatic use of a ConfigurableWebBindingInitializer bean (covered later in this document).

/*
如果您希望保留Spring Boot MVC功能，并且希望添加其他MVC配置（拦截器、格式化程序、视图控制器和其他功能），则可以添加自己
的@configuration类，类型为webmvcconfiguer，但不添加@EnableWebMvc。如果希望提供
RequestMappingHandlerMapping、RequestMappingHandlerAdapter或ExceptionHandlerExceptionResolver的自定义
实例，则可以声明WebMVCregistrationAdapter实例来提供此类组件。
*/
If you want to keep Spring Boot MVC features and you want to add additional MVC configuration 
(interceptors, formatters, view controllers, and other features), you can add your own 
@Configuration class of type WebMvcConfigurer but without @EnableWebMvc. If you wish to provide 
custom instances of RequestMappingHandlerMapping, RequestMappingHandlerAdapter, or 
ExceptionHandlerExceptionResolver, you can declare a WebMvcRegistrationsAdapter instance to provide such components.

// 如果您想完全控制Spring MVC，可以添加自己的@Configuration，并用@EnableWebMvc进行注释。
If you want to take complete control of Spring MVC, you can add your own @Configuration annotated with @EnableWebMvc.
```

我们来仔细对照，看一下它怎么实现的，它告诉我们SpringBoot已经帮我们自动配置好了SpringMVC，然后自动配置了哪些东西呢？

> ContentNegotiatingViewResolver 内容协商视图解析器

自动配置了ViewResolver，就是我们之前学习的SpringMVC的视图解析器；

即根据方法的返回值取得视图对象（View），然后由视图对象决定如何渲染（转发，重定向）。

我们去看看这里的源码：我们找到 WebMvcAutoConfiguration ， 然后搜索ContentNegotiatingViewResolver。找到如下方法！

```java
@Bean
@ConditionalOnBean(ViewResolver.class)
@ConditionalOnMissingBean(name = "viewResolver", value = ContentNegotiatingViewResolver.class)
public ContentNegotiatingViewResolver viewResolver(BeanFactory beanFactory) {
    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
    resolver.setContentNegotiationManager(beanFactory.getBean(ContentNegotiationManager.class));
    // ContentNegotiatingViewResolver使用所有其他视图解析器来定位视图，因此它应该具有较高的优先级
    resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return resolver;
}
```

我们可以点进这类看看！找到对应的解析视图的代码；

```java
@Nullable // 注解说明：@Nullable 即参数可为null
public View resolveViewName(String viewName, Locale locale) throws Exception {
    RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
    Assert.state(attrs instanceof ServletRequestAttributes, "No current ServletRequestAttributes");
    List<MediaType> requestedMediaTypes = this.getMediaTypes(((ServletRequestAttributes)attrs).getRequest());
    if (requestedMediaTypes != null) {
        // 获取候选的视图对象
        List<View> candidateViews = this.getCandidateViews(viewName, locale, requestedMediaTypes);
        // 选择一个最适合的视图对象，然后把这个对象返回
        View bestView = this.getBestView(candidateViews, requestedMediaTypes, attrs);
        if (bestView != null) {
            return bestView;
        }
    }
    // .....
}
```

我们继续点进去看，他是怎么获得候选的视图的呢？

getCandidateViews中看到他是把所有的视图解析器拿来，进行while循环，挨个解析！

```java
Iterator var5 = this.viewResolvers.iterator();
```

所以得出结论：**ContentNegotiatingViewResolver 这个视图解析器就是用来组合所有的视图解析器的** 

我们再去研究下他的组合逻辑，看到有个属性viewResolvers，看看它是在哪里进行赋值的！

```java
protected void initServletContext(ServletContext servletContext) {
    // 这里它是从beanFactory工具中获取容器中的所有视图解析器
    // ViewRescolver.class 把所有的视图解析器来组合的
    Collection<ViewResolver> matchingBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors(this.obtainApplicationContext(), ViewResolver.class).values();
    ViewResolver viewResolver;
    if (this.viewResolvers == null) {
        this.viewResolvers = new ArrayList(matchingBeans.size());
    }
    // ...............
}
```

既然它是在容器中去找视图解析器，我们是否可以猜想，我们就可以去实现一个视图解析器了呢？

我们可以自己给容器中去添加一个视图解析器；这个类就会帮我们自动的将它组合进来；**我们去实现一下**

1、我们在我们的主程序中去写一个视图解析器来试试；

```java
package com.peng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

//如果，你想diy一些定制化的功能，只要写这个组件，然后将它交给springboot，springboot就会帮我们自动装配
//扩展springmvc dispatchservlet
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //ViewResolver 实现了视图解析器接口的类，我们就可以把他看做视图解析器

    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }

    public static class MyViewResolver implements ViewResolver{
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }
}
```

2、怎么看我们自己写的视图解析器有没有起作用呢？

我们给 DispatcherServlet 中的 doDispatch方法 加个断点进行调试一下，因为所有的请求都会走到这个方法中

![image-20220414173207327](https://gitee.com/linda12138/picgo/raw/master/images/image-20220414173207327.png)

3、我们启动我们的项目，然后随便访问一个页面，看一下Debug信息；

找到this

![image-20220414173258376](https://gitee.com/linda12138/picgo/raw/master/images/image-20220414173258376.png)

找到视图解析器，我们看到我们自己定义的就在这里了；

![image-20220414173335638](https://gitee.com/linda12138/picgo/raw/master/images/image-20220414173335638.png)

所以说，我们如果想要使用自己定制化的东西，我们只需要给容器中添加这个组件就好了！剩下的事情SpringBoot就会帮我们做了！



> 修改SpringBoot的默认配置

这么多的自动配置，原理都是一样的，通过这个WebMVC的自动配置原理分析，我们要学会一种学习方式，通过源码探究，得出结论；这个结论一定是属于自己的，而且一通百通。

SpringBoot的底层，大量用到了这些设计细节思想，所以，没事需要多阅读源码！得出结论；

SpringBoot在自动配置很多组件的时候，先看容器中有没有用户自己配置的（如果用户自己配置@bean），如果有就用用户配置的，如果没有就用自动配置的；

如果有些组件可以存在多个，比如我们的视图解析器，就将用户配置的和自己默认的组合起来！

**扩展使用SpringMVC**  官方文档如下：

If you want to keep Spring Boot MVC features and you want to add additional MVC configuration (interceptors, formatters, view controllers, and other features), you can add your own @Configuration class of type WebMvcConfigurer but without @EnableWebMvc. If you wish to provide custom instances of RequestMappingHandlerMapping, RequestMappingHandlerAdapter, or ExceptionHandlerExceptionResolver, you can declare a WebMvcRegistrationsAdapter instance to provide such components.

我们要做的就是编写一个@Configuration注解类，并且类型要为WebMvcConfigurer，还不能标注@EnableWebMvc注解；我们去自己写一个；我们新建一个包叫config，写一个类MyMvcConfig；

```java
package com.peng.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//如果我们要扩展springmvc 官方建议我们这样去做
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/peng").setViewName("test");
    }
}
```

![image-20220414174436493](https://gitee.com/linda12138/picgo/raw/master/images/image-20220414174436493.png)

**确实也跳转过来了！所以说，我们要扩展SpringMVC，官方就推荐我们这么去使用，既保SpringBoot留所有的自动配置，也能用我们扩展的配置！**

我们可以去分析一下原理：

1、WebMvcAutoConfiguration 是 SpringMVC的自动配置类，里面有一个类WebMvcAutoConfigurationAdapter

2、这个类上有一个注解，在做其他自动配置时会导入：@Import(EnableWebMvcConfiguration.class)

3、我们点进EnableWebMvcConfiguration这个类看一下，它继承了一个父类：DelegatingWebMvcConfiguration

这个父类中有这样一段代码：

```java
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {
    private final WebMvcConfigurerComposite configurers = new WebMvcConfigurerComposite();
    
  // 从容器中获取所有的webmvcConfigurer
    @Autowired(required = false)
    public void setConfigurers(List<WebMvcConfigurer> configurers) {
        if (!CollectionUtils.isEmpty(configurers)) {
            this.configurers.addWebMvcConfigurers(configurers);
        }
    }
}
```

4、我们可以在这个类中去寻找一个我们刚才设置的viewController当做参考，发现它调用了一个

```java
protected void addViewControllers(ViewControllerRegistry registry) {
    this.configurers.addViewControllers(registry);
}
```

5、我们点进去看一下

```java
public void addViewControllers(ViewControllerRegistry registry) {
    Iterator var2 = this.delegates.iterator();

    while(var2.hasNext()) {
        // 将所有的WebMvcConfigurer相关配置来一起调用！包括我们自己配置的和Spring给我们配置的
        WebMvcConfigurer delegate = (WebMvcConfigurer)var2.next();
        delegate.addViewControllers(registry);
    }

}
```

所以得出结论：所有的WebMvcConfiguration都会被作用，不止Spring自己的配置类，我们自己的配置类当然也会被调用；



> 全面接管SpringMVC

官方文档：

```
If you want to take complete control of Spring MVCyou can add your own @Configuration annotated with @EnableWebMvc.
```

全面接管即：SpringBoot对SpringMVC的自动配置不需要了，所有都是我们自己去配置！

只需在我们的配置类中要加一个@EnableWebMvc。

我们看下如果我们全面接管了SpringMVC了，我们之前SpringBoot给我们配置的静态资源映射一定会无效，我们可以去测试一下；

不加注解之前，访问首页：

![image-20220414174825839](https://gitee.com/linda12138/picgo/raw/master/images/image-20220414174825839.png)

给配置类加上注解：@EnableWebMvc

![image-20220414174953536](https://gitee.com/linda12138/picgo/raw/master/images/image-20220414174953536.png)

我们发现所有的SpringMVC自动配置都失效了！回归到了最初的样子；

**当然，我们开发中，不推荐使用全面接管SpringMVC**

思考问题？为什么加了一个注解，自动配置就失效了！我们看下源码：

1、这里发现它是导入了一个类，我们可以继续进去看

```java
@Import({DelegatingWebMvcConfiguration.class})
public @interface EnableWebMvc {
}
```

2、它继承了一个父类 WebMvcConfigurationSupport

```java
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {
  // ......
}
```

3、我们来回顾一下Webmvc自动配置类

```java
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
// 这个注解的意思就是：容器中没有这个组件的时候，这个自动配置类才生效
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureAfter({ DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class,
                     ValidationAutoConfiguration.class })
public class WebMvcAutoConfiguration {

}
```

总结一句话：@EnableWebMvc将WebMvcConfigurationSupport组件导入进来了；

而导入的WebMvcConfigurationSupport只是SpringMVC最基本的功能！

**在SpringBoot中会有非常多的扩展配置，只要看见了这个，我们就应该多留心注意~**





### 13、整合JDBC

------

#### 13.1、SpringData简介

对于数据访问层，无论是 SQL(关系型数据库) 还是 NOSQL(非关系型数据库)，Spring Boot 底层都是采用 Spring Data 的方式进行统一处理。

Spring Boot 底层都是采用 Spring Data 的方式进行统一处理各种数据库，Spring Data 也是 Spring 中与 Spring Boot、Spring Cloud 等齐名的知名项目。

Sping Data 官网：https://spring.io/projects/spring-data

数据库相关的启动器 ：可以参考官方文档：

https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#using-boot-starter

#### 13.2、整合JDBC

> 创建测试项目测试数据源

1、我去新建一个项目测试：springboot-data-jdbc ; 引入相应的模块！基础模块

![image-20220416170014518](https://gitee.com/linda12138/picgo/raw/master/images/image-20220416170014518.png)

2、项目建好之后，发现自动帮我们导入了如下的启动器：

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

3、编写yaml配置文件连接数据库；

```yaml
spring:
  datasource:
    username: root
    password:
    url: jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
```

4、配置完这一些东西后，我们就可以直接去使用了，因为SpringBoot已经默认帮我们进行了自动配置；去测试类测试一下

```java
package com.peng;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot04DataApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        //查看下默认的数据源 class com.zaxxer.hikari.HikariDataSource
        System.out.println(dataSource.getClass());
        //获得数据库链接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        //xxx Template: springBoot已经配好的bean 拿来即用 CRUD

        //关闭
        connection.close();

    }

}
```

结果：我们可以看到他默认给我们配置的数据源为 : class com.zaxxer.hikari.HikariDataSource ， 我们并没有手动配置

我们来全局搜索一下，找到数据源的所有自动配置都在 ：DataSourceAutoConfiguration文件：

```java
@Import(
    {Hikari.class, Tomcat.class, Dbcp2.class, Generic.class, DataSourceJmxConfiguration.class}
)
protected static class PooledDataSourceConfiguration {
    protected PooledDataSourceConfiguration() {
    }
}
```

这里导入的类都在 DataSourceConfiguration 配置类下，可以看出 Spring Boot 2.2.5 默认使用HikariDataSource 数据源，而以前版本，如 Spring Boot 1.5 默认使用 org.apache.tomcat.jdbc.pool.DataSource 作为数据源；

`**HikariDataSource 号称 Java WEB 当前速度最快的数据源，相比于传统的 C3P0 、DBCP、Tomcat jdbc 等连接池更加优秀；**`

**可以使用 spring.datasource.type 指定自定义的数据源类型，值为 要使用的连接池实现的完全限定名。**

关于数据源我们并不做介绍，有了数据库连接，显然就可以 CRUD 操作数据库了。但是我们需要先了解一个对象 JdbcTemplate

> JDBCTemplate

1、有了数据源(com.zaxxer.hikari.HikariDataSource)，然后可以拿到数据库连接(java.sql.Connection)，有了连接，就可以使用原生的 JDBC 语句来操作数据库；

2、即使不使用第三方第数据库操作框架，如 MyBatis等，Spring 本身也对原生的JDBC 做了轻量级的封装，即JdbcTemplate。

3、数据库操作的所有 CRUD 方法都在 JdbcTemplate 中。

4、Spring Boot 不仅提供了默认的数据源，同时默认已经配置好了 JdbcTemplate 放在了容器中，程序员只需自己注入即可使用

5、JdbcTemplate 的自动配置是依赖 org.springframework.boot.autoconfigure.jdbc 包下的 JdbcTemplateConfiguration 类

**JdbcTemplate主要提供以下几类方法：**

- execute方法：可以用于执行任何SQL语句，一般用于执行DDL语句；
- update方法及batchUpdate方法：update方法用于执行新增、修改、删除等语句；batchUpdate方法用于执行批处理相关语句；
- query方法及queryForXXX方法：用于执行查询相关语句；
- call方法：用于执行存储过程、函数相关语句。



> 测试

编写一个Controller，注入 jdbcTemplate，编写测试方法进行访问测试；

```java
package com.peng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据库所有信息
    //没有实体类，数据库中的东西，怎么获取？
    @GetMapping("/userList")
    public List<Map<String,Object>> userList(){
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    @GetMapping("/addUser")
    public String addUser(){
        String sql = "insert into mybatis.user(id,name,pwd) values(5,'小号','123456')";
        jdbcTemplate.update(sql);
        return "update-ok";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id){
        String sql = "update mybatis.user set name=?,pwd=? where id="+id;
        //封装
        Object[] objects = new Object[2];
        objects[0]="小明2";
        objects[1]="zzzzzz";
        jdbcTemplate.update(sql,objects);
        return "update-ok";
    }
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        String sql = "delete from mybatis.user where id=?";
        jdbcTemplate.update(sql,id);
        return "delete-ok";
    }

}
```

测试请求，结果正常；

到此，CURD的基本操作，使用 JDBC 就搞定了。



### 14、集成Druid

------

#### 14.1、Druid简介

Java程序很大一部分要操作数据库，为了提高性能操作数据库的时候，又不得不使用数据库连接池。

Druid 是阿里巴巴开源平台上一个数据库连接池实现，结合了 C3P0、DBCP 等 DB 池的优点，同时加入了日志监控。

Druid 可以很好的监控 DB 池连接和 SQL 的执行情况，天生就是针对监控而生的 DB 连接池。

Druid已经在阿里巴巴部署了超过600个应用，经过一年多生产环境大规模部署的严苛考验。

Spring Boot 2.0 以上默认使用 Hikari 数据源，可以说 Hikari 与 Driud 都是当前 Java Web 上最优秀的数据源，我们来重点介绍 Spring Boot 如何集成 Druid 数据源，如何实现数据库监控。

Github地址：https://github.com/alibaba/druid/

**com.alibaba.druid.pool.DruidDataSource 基本配置参数如下：**

| 配置                          | 省缺值             | 说明                                                         |
| ----------------------------- | ------------------ | ------------------------------------------------------------ |
| name                          |                    | 配置这个属性的意义在于，如果存在多个数据源，监控的时候可以通过名字来区分开来。如果没有配置，将会生成一个名字，格式"DataSource-"+System.identityHashCode(this). |
| url                           |                    | 连接数据库的url,不同数据库不一样。例如：mysq:jdbc:mysql://10.20.153.104:3306/druid2 oraclejdbc:oracle:thin:@10.20.149.85:1521:ocnauto |
| username                      |                    | 链接数据库的用户名                                           |
| password                      |                    | 连接数据库的密码。如果你不希望密码直接写在配置文件中，可以使用ConfigFilter。 |
| driverClassName               | 根据url自动识别    | 这一项可配可不配，如果不配置druid会根据url自动识别dbType,然后选择相应的driverClassName |
| initialSize                   | 0                  | 初始化时建立物理连接的个数。初始化发生在显示调用iit方法或者第一次getConnection时 |
| maxActive                     | 8                  | 最大连接池数量                                               |
| maxIdle                       | 8                  | 已经不再使用，配置了页没效果                                 |
| minIdle                       |                    | 最小连接池数量                                               |
| maxWait                       |                    | 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。 |
| poolpreparedStatement         | false              | 是否缓存preparedStatement,也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。 |
| maxOpenPreparedStatement      | -1                 | 要启用PSCache,必须配置大于0，当大于O时poolPreparedStatements自动触发修改为true。在Druid中，不会存在Dracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100 |
| validationQuery               |                    | 用来检测连接是否有效的s!,要求是一个查询语句。如果validationQuery为null,testOnBorrow、testOnReturn、testWhileldle都不会其作用。 |
| validationQueryTimeout        |                    | 单位：秒，检测连接是否有效的超时时间。底层调用dbcStatementi对象的void setQueryTimeout(int seconds)方法 |
| testOnBorrow                  | true               | 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 |
| testOnReturn                  | false              | 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 |
| testWhileIdle                 | false              | 建议配置为tue,不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis,执行validationQuery检测连接是否有效。 |
| timeBetweenEvictionRunsMillis | 1分钟（1.0.10      | 有两个含义：1)Destroy?线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableldleTimeMillis则关闭物理连接2)testWhileldlel的判断依据，详细看testWhileldle属性的说明 |
| numTestsPerEvictionRun        |                    | 不再使用，一个DruidDataSource只支持一个EvictionRun           |
| minEvictableIdleTimeMillis    | 30分钟（1.0.14）   | 连接保持空闲而不被驱逐的最长时间                             |
| connectionInitSqls            |                    | 物理连接初始化的时候执行的sql                                |
| exceptionSorter               | 根据dbType自动识别 | 当数据库抛出一些不可恢复的异常时，抛弃连接                   |
| filters                       |                    | 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat日志用的filter:log4j防御sql注入的filter:wall |
| proxyFilters                  |                    | 类型是List<com.alibaba.druid.fter.Ftep,如果用时配置了filters和proxyFilters,是组合关系，并非替换关系 |



#### 14.2、配置数据源

1、添加上 Druid 数据源依赖。

```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.9</version>
</dependency>
```

2、切换数据源；之前已经说过 Spring Boot 2.0 以上默认使用 com.zaxxer.hikari.HikariDataSource 数据源，但可以 通过spring.datasource.type 指定数据源。

```yaml
spring:
  datasource:
    username: root
    password:
    url: jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
```

3、数据源切换之后，在测试类中注入 DataSource，然后获取到它，输出一看便知是否成功切换；

4、切换成功！既然切换成功，就可以设置数据源连接初始化大小、最大连接数、等待时间、最小连接数 等设置项；可以查看源码

```yaml
spring:
  datasource:
    username: root
    password:
    url: jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource

    #SpringBoot默认是不注入这些的，需要自己绑定
    #druid数据源专有配置
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true

    #配置监控统计拦截的filters，stat：监控统计、log4j：日志记录、wall：防御sql注入
    #如果允许报错，java.lang.ClassNotFoundException: org.apache.Log4j.Properity
    #则导入log4j 依赖就行
    filters: stat,wall,log4j
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
```

5、导入Log4j 的依赖

```xml
<!-- https://mvnrepository.com/artifact/log4j/log4j -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

6、现在需要程序员自己为 DruidDataSource 绑定全局配置文件中的参数，再添加到容器中，而不再使用 Spring Boot 的自动生成了；我们需要 自己添加 DruidDataSource 组件到容器中，并绑定属性；

```java

package com.peng.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {

    /*
       将自定义的 Druid数据源添加到容器中，不再让 Spring Boot 自动创建
       绑定全局配置文件中的 druid 数据源属性到 com.alibaba.druid.pool.DruidDataSource从而让它们生效
       @ConfigurationProperties(prefix = "spring.datasource")：作用就是将 全局配置文件中
       前缀为 spring.datasource的属性值注入到 com.alibaba.druid.pool.DruidDataSource 的同名参数中
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

}
```

#### 14.3、配置Druid数据源监控

Druid 数据源具有监控的功能，并提供了一个 web 界面方便用户查看，类似安装 路由器 时，人家也提供了一个默认的 web 页面。

所以第一步需要设置 Druid 的后台管理页面，比如 登录账号、密码 等；配置后台管理；

```java
package com.peng.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }
    //后台监控功能: web.xml
    //因为spring Boot内置了servlet容器，没有web.xml,替代方法：ServletRegistrationBean
    @Bean
    public ServletRegistrationBean statviewservlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //后台需要有人登录，账号密码配置
        HashMap<String,String> initParameter = new HashMap<String,String>();
        //增加配置
        initParameter.put("loginUsername","admin"); //登录的key是固定的 loginUsername loginPassword
        initParameter.put("loginPassword","123456");
        //允许谁能访问
        initParameter.put("allow","");
        //禁止谁能访问
        //        initParameter.put("peng","127.0.1.1");
        bean.setInitParameters(initParameter);//设置初始化参数
        return bean;
    }
 
}
```

配置完毕后，我们可以选择访问 ：http://localhost:8080/druid/login.html

![image-20220416175116817](https://gitee.com/linda12138/picgo/raw/master/images/image-20220416175116817.png)

输入设置好的用户名和密码进入

![image-20220416175152552](https://gitee.com/linda12138/picgo/raw/master/images/image-20220416175152552.png)

然后再运行sql,查看sql监控

![image-20220416175247491](https://gitee.com/linda12138/picgo/raw/master/images/image-20220416175247491.png)

**配置 Druid web 监控 filter 过滤器**

```java
//配置 Druid 监控 之  web 监控的 filter
//WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
@Bean
public FilterRegistrationBean webStatFilter() {
    FilterRegistrationBean bean = new FilterRegistrationBean();
    bean.setFilter(new WebStatFilter());

    //exclusions：设置哪些请求进行过滤排除掉，从而不进行统计
    Map<String, String> initParams = new HashMap<>();
    initParams.put("exclusions", "*.js,*.css,/druid/*,/jdbc/*");
    bean.setInitParameters(initParams);

    //"/*" 表示过滤所有请求
    bean.setUrlPatterns(Arrays.asList("/*"));
    return bean;
}
```

平时在工作中，按需求进行配置即可，主要用作监控！



### 15、整合Mybatis

------

官方文档：http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/

Maven仓库地址：https://mvnrepository.com/artifact/org.mybatis.spring.boot/mybatis-spring-boot-starter/2.1.1

![image-20220416201642801](https://gitee.com/linda12138/picgo/raw/master/images/image-20220416201642801.png)

> 整合测试

1、导入 MyBatis 所需要的依赖

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis.spring.boot/mybatis-spring-boot-starter -->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.2.2</version>
</dependency>
```

2、配置数据库连接信息（不变）

```properties
spring.datasource.username=root
spring.datasource.password=
spring.datasource.url=jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver


mybatis.type-aliases-package=com.peng.pojo
mybatis.mapper-locations=classpath:mybatis/mapper/*.xml
```

**3、测试数据库是否连接成功！**

**4、创建实体类，导入 Lombok！**

User.java

```java
package com.peng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String pwd;
}
```

**5、创建mapper目录以及对应的 Mapper 接口**

UserMapper.java

```java
package com.peng.mapper;

import com.peng.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//这个注解表示了这是一个mybatis的mapper类
@Mapper
@Repository
public interface UserMapper {

    List<User> queryUserList();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
```

**6、对应的Mapper映射文件**

UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.peng.mapper.UserMapper">

    <select id="queryUserList" resultType="User">
        select * from mybatis.user
    </select>
    <select id="queryUserById" resultType="User">
        select * from mybatis.user where id=#{id}
    </select>
    <insert id="addUser" parameterType="User">
        insert into mybatis.user (id,name,pwd) values (#{id},#{name},#{pwd})
    </insert>
    <update id="updateUser" parameterType="User">
        update mybatis.user set name=#{name},pwd=#{pwd} where id=#{id}
    </update>
    <delete id="deleteUser" parameterType="int">
        delete from mybatis.user where id=#{id}
    </delete>
</mapper>
```

**7、maven配置资源过滤问题**

```xml
<resources>
    <resource>
        <directory>src/main/java</directory>
        <includes>
            <include>**/*.xml</include>
        </includes>
        <filtering>true</filtering>
    </resource>
</resources>
```

**8、编写部门的Controller 进行测试！**

UserController.java

```java
package com.peng.controller;

import com.peng.mapper.UserMapper;
import com.peng.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUserList")
    public List<User> queryUserList(){
        List<User> userList = userMapper.queryUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }
    @GetMapping("/queryUserById/{id}")
    public User queryUserById(@PathVariable("id") int id){
        return userMapper.queryUserById(id);
    }
    @GetMapping("/queryUserById")
    public String addUser(){
        userMapper.addUser(new User(8, "赵海超", "123456"));
        return "ok";
    }
    @GetMapping("/updateUser")
    public String updateUser(){
        userMapper.updateUser(new User(2,"哈哈哈哈哈","12138"));
        return "ok";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(){
        userMapper.deleteUser(7);
        return "ok";
    }
}
```

**启动项目访问进行测试！**



### 16、SpringSecurity

------

#### 16.1、安全简介

​	在 Web 开发中，安全一直是非常重要的一个方面。安全虽然属于应用的非功能性需求，但是应该在应用开发的初期就考虑进来。如果在应用开发的后期才考虑安全的问题，就可能陷入一个两难的境地：一方面，应用存在严重的安全漏洞，无法满足用户的要求，并可能造成用户的隐私数据被攻击者窃取；另一方面，应用的基本架构已经确定，要修复安全漏洞，可能需要对系统的架构做出比较重大的调整，因而需要更多的开发时间，影响应用的发布进程。因此，从应用开发的第一天就应该把安全相关的因素考虑进来，并在整个应用的开发过程中。

​	市面上存在比较有名的：Shiro，Spring Security ！

​	这里需要阐述一下的是，每一个框架的出现都是为了解决某一问题而产生了，那么Spring Security框架的出现是为了解决什么问题呢？

​	首先我们看下它的官网介绍：Spring Security官网地址

​	Spring Security is a powerful and highly customizable authentication and access-control framework. It is the de-facto standard for securing Spring-based applications.

​	Spring Security is a framework that focuses on providing both authentication and authorization to Java applications. Like all Spring projects, the real power of Spring Security is found in how easily it can be extended to meet custom requirements

​	Spring Security是一个功能强大且高度可定制的身份验证和访问控制框架。它实际上是保护基于spring的应用程序的标准。

​	Spring Security是一个框架，侧重于为Java应用程序提供身份验证和授权。与所有Spring项目一样，	Spring安全性的真正强大之处在于它可以轻松地扩展以满足定制需求

​	从官网的介绍中可以知道这是一个权限框架。想我们之前做项目是没有使用框架是怎么控制权限的？对于权限 一般会细分为功能权限，访问权限，和菜单权限。代码会写的非常的繁琐，冗余。

​	怎么解决之前写权限代码繁琐，冗余的问题，一些主流框架就应运而生而Spring Scecurity就是其中的一种。

​	Spring 是一个非常流行和成功的 Java 应用开发框架。Spring Security 基于 Spring 框架，提供了一套 Web 应用安全性的完整解决方案。一般来说，Web 应用的安全性包括用户认证（Authentication）和用户授权（Authorization）两个部分。用户认证指的是验证某个用户是否为系统中的合法主体，也就是说用户能否访问该系统。用户认证一般要求用户提供用户名和密码。系统通过校验用户名和密码来完成认证过程。用户授权指的是验证某个用户是否有权限执行某个操作。在一个系统中，不同用户所具有的权限是不同的。比如对一个文件来说，有的用户只能进行读取，而有的用户可以进行修改。一般来说，系统会为不同的用户分配不同的角色，而每个角色则对应一系列的权限。

​	对于上面提到的两种应用情景，Spring Security 框架都有很好的支持。在用户认证方面，Spring Security 框架支持主流的认证方式，包括 HTTP 基本认证、HTTP 表单验证、HTTP 摘要认证、OpenID 和 LDAP 等。在用户授权方面，Spring Security 提供了基于角色的访问控制和访问控制列表（Access Control List，ACL），可以对应用中的领域对象进行细粒度的控制。



#### 16.2、实战测试

> 实验环境搭建

1、新建一个初始的springboot项目web模块，thymeleaf模块

2、导入静态资源：https://gitee.com/ENNRIAAA/spring-security-material?_from=gitee_search

3、controller跳转！

```java
package com.peng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "views/login";
    }
    @RequestMapping("/level1/{id}")
    public String level1(@PathVariable("id") int id){
        return "views/level1/"+id;
    }

    @RequestMapping("/level2/{id}")
    public String level2(@PathVariable("id") int id){
        return "views/level2/"+id;
    }

    @RequestMapping("/level3/{id}")
    public String level3(@PathVariable("id") int id){
        return "views/level3/"+id;
    }
}
```

4、测试实验环境是否OK！



#### 16.3、认识SpringSecurity

Spring Security 是针对Spring项目的安全框架，也是Spring Boot底层安全模块默认的技术选型，他可以实现强大的Web安全控制，对于安全控制，我们仅需要引入 spring-boot-starter-security 模块，进行少量的配置，即可实现强大的安全管理！

记住几个类：

- WebSecurityConfigurerAdapter：自定义Security策略
- AuthenticationManagerBuilder：自定义认证策略
- @EnableWebSecurity：开启WebSecurity模式

Spring Security的两个主要目标是 “认证” 和 “授权”（访问控制）。

**“认证”（Authentication）**

身份验证是关于验证您的凭据，如用户名/用户ID和密码，以验证您的身份。

身份验证通常通过用户名和密码完成，有时与身份验证因素结合使用。

 **“授权” （Authorization）**

授权发生在系统成功验证您的身份后，最终会授予您访问资源（如信息，文件，数据库，资金，位置，几乎任何内容）的完全权限。

这个概念是通用的，而不是只在Spring Security 中存在。

#### 16.4、认证和授权

目前，我们的测试环境，是谁都可以访问的，我们使用 Spring Security 增加上认证和授权的功能

1、引入 Spring Security 模块

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

2、编写 Spring Security 配置类

参考官网：https://spring.io/projects/spring-security 

查看我们自己项目中的版本，找到对应的帮助文档：

https://docs.spring.io/spring-security/site/docs/5.3.0.RELEASE/reference/html5  #servlet-applications 8.16.4

![image-20220419104952557](https://gitee.com/linda12138/picgo/raw/master/images/image-20220419104952557.png)

3、编写基础配置类

```java
package com.peng.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;	
@EnableWebSecurity
//aop 横切
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //链式编程
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    }
```

4、定制请求的授权规则

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    //首页所有人可以访问，功能页只有对应有权限的人才能访问
    //请求授权的规则
    http.authorizeRequests().antMatchers("/index").permitAll()
        .antMatchers("/level1/**").hasRole("vip1")
        .antMatchers("/level2/**").hasRole("vip2")
        .antMatchers("/level3/**").hasRole("vip3");
}
```

5、测试一下：发现除了首页都进不去了！因为我们目前没有登录的角色，因为请求需要登录的角色拥有对应的权限才可以！

6、在configure()方法中加入以下配置，开启自动配置的登录功能！

```java
//没有权限默认回到登录页面,需要开启登录页面
//login
http.formLogin().loginPage("/toLogin").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login");
```

7、测试一下：发现，没有权限的时候，会跳转到登录的页面！





8、查看刚才登录页的注释信息；

我们可以定义认证规则，重写configure(AuthenticationManagerBuilder auth)方法

```java
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //这些数据应该从数据库中读
    auth.inMemoryAuthentication()
        .withUser("pengfei").password("123456").roles("vip2","vip3")
        .and()
        .withUser("nimei").password("123456").roles("vip1")
        .and()
        .withUser("root").password("123456").roles("vip1","vip2","vip3");
}
```

9、测试，我们可以使用这些账号登录进行测试！发现会报错！

There is no PasswordEncoder mapped for the id “null”

10、原因，我们要将前端传过来的密码进行某种方式加密，否则就无法登录，修改代码

```java
//认证，在springboot 2.1.x 可以直接使用
//密码编码：PasswordEncoder
//在spring Secutry 5.0+ 新增了很多加密方式
//要想我们的项目还能够正常登陆，需要修改一下configure中的代码。我们要将前端传过来的密码进行某种方式加密
//spring security 官方推荐的是使用bcrypt加密方式。
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //这些数据应该从数据库中读
    auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        .withUser("pengfei").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
        .and()
        .withUser("nimei").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1")
        .and()
        .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3");
}
```

11、测试，发现，登录成功，并且每个角色只能访问自己认证下的规则！搞定

#### 16.5、权限控制和注销

1、开启自动配置的注销的功能

```java
//注销 开启了注销功能
http.logout();
```

2、我们在前端，增加一个注销的按钮，index.html 导航栏中

```html
<a class="item" th:href="@{/logout}">
    <i class="sign-out icon"></i> 注销
</a>
```

3、我们可以去测试一下，登录成功后点击注销，发现注销完毕会跳转到登录页面！

4、但是，我们想让他注销成功后，依旧可以跳转到首页，该怎么处理呢？

```java
//注销 开启了注销功能
http.logout().deleteCookies("remove").invalidateHttpSession(true).logoutSuccessUrl("/index");
```

5、测试，注销完毕后，发现跳转到首页OK

6、我们现在又来一个需求：用户没有登录的时候，导航栏上只显示登录按钮，用户登录之后，导航栏可以显示登录的用户信息及注销按钮！还有就是，比如kuangshen这个用户，它只有 vip2，vip3功能，那么登录则只显示这两个功能，而vip1的功能菜单不显示！这个就是真实的网站情况了！该如何做呢？

我们需要结合thymeleaf中的一些功能

sec：authorize="isAuthenticated()":是否认证登录！来显示不同的页面

Maven依赖：

```xml
<!-- https://mvnrepository.com/artifact/org.thymeleaf.extras/thymeleaf-extras-springsecurity4 -->
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity5</artifactId>
    <version>3.0.4.RELEASE</version>
</dependency>
```

7、修改我们的 前端页面

导入命名空间

```html
xmlns:sec="http://www.thymeleaf.org/extras/spring-security"
```

修改导航栏，增加认证判断

```html
<!--登录注销-->
<div class="right menu">
    <!-- 如果未登录-->
    <!--未登录-->
    <div sec:authorize="!isAuthenticated()">
        <a class="item" th:href="@{/toLogin}">
            <i class="address card icon"></i> 登录
        </a>
    </div>
    <div sec:authorize="isAuthenticated()">
        <a class="item">
            用户名：<span sec:authentication="name"></span>&nbsp;
            角色：<span sec:authentication="principal.authorities"></span>
        </a>
    </div>
    <!--如果登录  登录，注销-->
    <div sec:authorize="isAuthenticated()">
        <a class="item" th:href="@{/logout}">
            <i class="sign-out icon"></i> 注销
        </a>
</div>
```

8、重启测试，我们可以登录试试看，登录成功后确实，显示了我们想要的页面；

9、如果注销404了，就是因为它默认防止csrf跨站请求伪造，因为会产生安全问题，我们可以将请求改为post表单提交，或者在spring security中关闭csrf功能；我们试试：在 配置中增加 `http.csrf().disable();`

```java
/防止网站攻击：get  post
    http.csrf().disable();//关闭csrf功能 登录失败可能的原因
```

10、我们继续将下面的角色功能块认证完成！

```html
<!--菜单的权限显示-->
<div class="ui three column stackable grid">
    <div class="column" sec:authorize="hasRole('vip1')">
        <div class="ui raised segment">
            <div class="ui">
                <div class="content">
                    <h5 class="content">Level 1</h5>
                    <hr>
                    <div><a th:href="@{/level1/1}"><i class="bullhorn icon"></i> Level-1-1</a></div>
                    <div><a th:href="@{/level1/2}"><i class="bullhorn icon"></i> Level-1-2</a></div>
                    <div><a th:href="@{/level1/3}"><i class="bullhorn icon"></i> Level-1-3</a></div>
                </div>
            </div>
        </div>
    </div>

    <div class="column" sec:authorize="hasRole('vip2')">
        <div class="ui raised segment">
            <div class="ui">
                <div class="content">
                    <h5 class="content">Level 2</h5>
                    <hr>
                    <div><a th:href="@{/level2/1}"><i class="bullhorn icon"></i> Level-2-1</a></div>
                    <div><a th:href="@{/level2/2}"><i class="bullhorn icon"></i> Level-2-2</a></div>
                    <div><a th:href="@{/level2/3}"><i class="bullhorn icon"></i> Level-2-3</a></div>
                </div>
            </div>
        </div>
    </div>

    <div class="column" sec:authorize="hasRole('vip3')">
        <div class="ui raised segment">
            <div class="ui">
                <div class="content">
                    <h5 class="content">Level 3</h5>
                    <hr>
                    <div><a th:href="@{/level3/1}"><i class="bullhorn icon"></i> Level-3-1</a></div>
                    <div><a th:href="@{/level3/2}"><i class="bullhorn icon"></i> Level-3-2</a></div>
                    <div><a th:href="@{/level3/3}"><i class="bullhorn icon"></i> Level-3-3</a></div>
                </div>
            </div>
        </div>
    </div>

</div>
</div>
```

11、测试一下！

12、权限控制和注销搞定！

#### 16.6、记住我

现在的情况，我们只要登录之后，关闭浏览器，再登录，就会让我们重新登录，但是很多网站的情况，就是有一个记住密码的功能，这个该如何实现呢？很简单

1、开启记住我功能

```java
//定制请求的授权规则
@Override
protected void configure(HttpSecurity http) throws Exception {
    //。。。。。。。。。。。
    //记住我
    http.rememberMe();
}
```

2、我们再次启动项目测试一下，发现登录页多了一个记住我功能，我们登录之后关闭 浏览器，然后重新打开浏览器访问，发现用户依旧存在！

思考：如何实现的呢？其实非常简单

我们可以查看浏览器的cookie

![image-20220419110439831](https://gitee.com/linda12138/picgo/raw/master/images/image-20220419110439831.png)

3、我们点击注销的时候，可以发现，spring security 帮我们自动删除了这个 cookie

4、结论：登录成功后，将cookie发送给浏览器保存，以后登录带上这个cookie，只要通过检查就可以免登录了。如果点击注销，则会删除这个cookie，具体的原理我们在JavaWeb阶段都讲过了，这里就不在多说了！



#### 16.7、定制登录页

现在这个登录页面都是spring security 默认的，怎么样可以使用我们自己写的Login界面呢？

1、在刚才的登录页配置后面指定 loginpage

```java
//没有权限默认回到登录页面,需要开启登录页面
//login
http.formLogin().loginPage("/toLogin");
```

2、然后前端也需要指向我们自己定义的 login请求

```html
<a class="item" th:href="@{/toLogin}">
    <i class="address card icon"></i> 登录
</a>
```

3、我们登录，需要将这些信息发送到哪里，我们也需要配置，login.html 配置提交请求及方式，方式必须为post:

```html
<form th:action="@{/login}" method="post">
    <div class="field">
        <label>Username</label>
        <div class="ui left icon input">
            <input type="text" placeholder="Username" name="username">
            <i class="user icon"></i>
        </div>
    </div>
    <div class="field">
        <label>Password</label>
        <div class="ui left icon input">
            <input type="password" name="password">
            <i class="lock icon"></i>
        </div>
    </div>
    <div class="field">
        <input type="checkbox" name="remember"> 记住我
    </div>

    <input type="submit" class="ui blue submit button"/>
</form>
```

4、这个请求提交上来，我们还需要验证处理，怎么做呢？我们可以查看formLogin()方法的源码！我们配置接收登录的用户名和密码的参数！

```java
//没有权限默认回到登录页面,需要开启登录页面
//login
http.formLogin().loginPage("/toLogin").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login");
```

5、在登录页增加记住我的多选框

```html
<div class="field">
    <input type="checkbox" name="remember"> 记住我
</div>
```

6、后端验证处理！

```java
//定制记住我的参数！
http.rememberMe().rememberMeParameter("remember");
```

7、测试，OK

#### 16.8、完整配置代码

```java
package com.peng.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
//aop 横切
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //链式编程
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问，功能页只有对应有权限的人才能访问
        //请求授权的规则
        http.authorizeRequests().antMatchers("/index").permitAll()
            .antMatchers("/level1/**").hasRole("vip1")
            .antMatchers("/level2/**").hasRole("vip2")
            .antMatchers("/level3/**").hasRole("vip3");

        //没有权限默认回到登录页面,需要开启登录页面
        //login
        http.formLogin().loginPage("/toLogin").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login");

        //防止网站攻击：get  post
        http.csrf().disable();//关闭csrf功能 登录失败可能的原因

        //注销 开启了注销功能
        http.logout().deleteCookies("remove").invalidateHttpSession(true).logoutSuccessUrl("/index");
        //开启记住我功能 ，自定义接收前端的参数
        http.rememberMe().rememberMeParameter("remember"); //cookie
    }

    //认证，在springboot 2.1.x 可以直接使用
    //密码编码：PasswordEncoder
    //在spring Secutry 5.0+ 新增了很多加密方式
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这些数据应该从数据库中读
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .withUser("pengfei").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
            .and()
            .withUser("nimei").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1")
            .and()
            .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3");
    }
}

```



### 17、Shiro

------

#### 17.1、Shiro简介

> 什么是Shiro?

- Apache Shiro是一个Java的安全（权限）框架。

- Shiro可以非常容易的开发出足够好的应用，其不仅可以用在avaSE环境，也可以用在avaEE环境。

- Shiro可以完成，认证，授权，加密，会话管理，Web集成，缓存等。

- 下载地址：http://shiro.apache.org/

  ![image-20220419164058357](https://gitee.com/linda12138/picgo/raw/master/images/image-20220419164058357.png)

> 有哪些功能？

![image-20220419164201622](https://gitee.com/linda12138/picgo/raw/master/images/image-20220419164201622.png)

- Authentication:身份认证、登录，验证用户是不是拥有相应的身份
- Authorization:授权，即权限验证，验证某个已认证的用户是否拥有某个权限，即判断用户能否进行什么操作，如：验证某个用户是否拥有某个角色，或者细粒度的验证某个用户对某个资源是否具有某个权限！
- Session Manager:会话管理，即用户登录后就是第一次会话，在没有退出之前，它的所有信息都在会话中；会话可以是普通的avaSE环境，也可以是Web环境
- Cryptography:加密，保护数据的安全性，如密码加密存储到数据库中，而不是明文存储；
- Web Support:Web支持，可以非常容易的集成到Web环境：
- Caching:缓存，比如用户登录后，其用户信息，拥有的角色、权限不必每次去查，这样可以提高效率
- Concurrency:Shiro支持多线程应用的并发验证，即，如在一个线程中开启另一个线程，能把权限自动的传播过去·Testing:提供测试支持；
- RuAs:允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；
- Remember Me:记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录了



> Shiro架构（外部）

从外部来看Shiro,即从应用程序角度来观察如何使用shiro完成工作：

![image-20220419164457010](https://gitee.com/linda12138/picgo/raw/master/images/image-20220419164457010.png)

- subject:应用代码直接交互的对象是Subject,也就是说Shiro的对外API核心就是Subject,Subject代表了当前的用户，这个用户不一定是一个具体的人，与当前应用交互的任何东西都是Subject,如网络爬虫，机器人等，与Subject的所有交互都会委托给SecurityManager;Subject其实是一个门面，SecurityManageer才是实际的执行者
- SecurityManager:安全管理器，即所有与安全有关的操作都会与SercurityManager?交互，并且它管理着所有的Subject,可以看出它是Shiro的核心，它负责与Shiro的其他组件进行交互，它相当于SpringMVC的DispatcherServlet的角色
- Realm:Shiro从Realm:获取安全数据（如用户，角色，权限），就是说SecurityManager要验证用户身份，那么它需要从Realm获取相应的用户进行比较，来确定用户的身份是否合法；也需要从Realm得到用户相应的角色、权限，进行验证用户的操作是否能够进行，可以把Realm:看成DataSource;

> Shiro架构(内部)

![image-20220419164759088](https://gitee.com/linda12138/picgo/raw/master/images/image-20220419164759088.png)

- Subject:任何可以与应用交互的'用户'
- Security Manager:相当于SpringMVC中的DispatcherServlet;是Shiro的心脏，所有具体的交互都通过Security Manageri进行控制，它管理者所有的Subject,且负责进行认证，授权，会话，及缓存的管理。
- Authenticator:负责Subjecti认证，是一个扩展点，可以自定义实现；可以使用认证策略(AuthenticationStrategy),即什么情况下算用户认证通过了
- Authorizer:授权器，即访问控制器，用来决定主体是否有权限进行相应的操作；即控制着用户能访问应用中的那些功能
- Realm:可以有一个或者多个的realm,可以认为是安全实体数据源，即用于获取安全实体的，可以用DBC实现，也可以是内存实现等等，由用户提供：所以一般在应用中都需要实现自己的realm。
- SessionManager：管理Session生命周期的组件，而Shiro并不仅仅可以用在Web环境，也可以用在普通的JavaSE环境中
- CacheManager:缓存控制器，来管理如用户，角色，权限等缓存的；因为这些数据基本上很少改变，放到缓存中后可以提高访问的性能：
- Cryptography:密码模块，Shiro提高了一些常见的加密组件用于密码加密，解密等



#### 17.2、HelloWorld

> 快速实践

查看官方文档：https://shiro.apache.org/documentation.html

官方的quickstart:https://github.com/apache/shiro/tree/main/samples/quickstart

1. 创建一个maven父工程，用于学习Shiro,删掉不必要的东西

2. 创建一个普通的maven子工程：shiro-helloworld

3. 根据官方文档，我们来导入Shiro的依赖

   ```xml
   <dependencies>
       <dependency>
           <groupId>org.apache.shiro</groupId>
           <artifactId>shiro-core</artifactId>
           <version>1.9.0</version>
       </dependency>
       <!-- https://mvnrepository.com/artifact/org.slf4j/jcl-over-slf4j -->
       <dependency>
           <groupId>org.slf4j</groupId>
           <artifactId>jcl-over-slf4j</artifactId>
           <version>1.7.21</version>
       </dependency>
       <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12 -->
       <dependency>
           <groupId>org.slf4j</groupId>
           <artifactId>slf4j-log4j12</artifactId>
           <version>1.7.21</version>
       </dependency>
       <!-- https://mvnrepository.com/artifact/log4j/log4j -->
       <dependency>
           <groupId>log4j</groupId>
           <artifactId>log4j</artifactId>
           <version>1.2.17</version>
       </dependency>
   </dependencies>
   ```

   

4. 把quickstart下的resource下的log4j配置拿过来

   ```properties
   log4j.rootLogger=INFO,stdout
   
   log4j.appender.stdout=org.apache.log4j.ConsoleAppender
   log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
   log4j.appender.stdout.layout.ConversionPattern=%d %p [%c] - %m %n
   
   log4j.logger.org.apache=WARN
   
   log4j.logger.org.springframework=WARN
   
   log4j.logger.org.apache.shiro=INFO
   
   1og4j.logger.org.apache.shiro.util.ThreadContext=WARN
   log4j.logger.org.apache.shiro.cache.ehcache.EhCache=WARN
   ```

   

5. 把quickstart下的resource下的shiro.ini配置拿过来

   ```ini
   [users]
   # user 'root' with password 'secret' and the 'admin' role
   root = secret, admin
   # user 'guest' with the password 'guest' and the 'guest' role
   guest = guest, guest
   # user 'presidentskroob' with password '12345' ("That's the same combination on
   # my luggage!!!" ;)), and role 'president'
   presidentskroob = 12345, president
   # user 'darkhelmet' with password 'ludicrousspeed' and roles 'darklord' and 'schwartz'
   darkhelmet = ludicrousspeed, darklord, schwartz
   # user 'lonestarr' with password 'vespa' and roles 'goodguy' and 'schwartz'
   lonestarr = vespa, goodguy, schwartz
   
   # -----------------------------------------------------------------------------
   # Roles with assigned permissions
   #
   # Each line conforms to the format defined in the
   # org.apache.shiro.realm.text.TextConfigurationRealm#setRoleDefinitions JavaDoc
   # -----------------------------------------------------------------------------
   [roles]
   # 'admin' role has all permissions, indicated by the wildcard '*'
   admin = *
   # The 'schwartz' role can do anything (*) with any lightsaber:
   schwartz = lightsaber:*
   # The 'goodguy' role is allowed to 'drive' (action) the winnebago (type) with
   # license plate 'eagle5' (instance specific id)
   goodguy = winnebago:drive:eagle5
   ```

   

6. 最后把Quickstart.java拿过来（由于版本原因，新版略有改动）

   ```java
   import org.apache.shiro.SecurityUtils;
   import org.apache.shiro.authc.*;
   import org.apache.shiro.mgt.DefaultSecurityManager;
   import org.apache.shiro.realm.text.IniRealm;
   import org.apache.shiro.session.Session;
   import org.apache.shiro.subject.Subject;
   
   import org.slf4j.Logger;
   import org.slf4j.LoggerFactory;
   
   
   /**
    * Simple Quickstart application showing how to use Shiro's API.
    *
    * @since 0.9 RC2
    */
   public class Quickstart {
   
       private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);
   
   
       public static void main(String[] args) {
   
           DefaultSecurityManager securityManager = new DefaultSecurityManager();
           IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
           securityManager.setRealm(iniRealm);
   
     
           SecurityUtils.setSecurityManager(securityManager);
   
           // Now that a simple Shiro environment is set up, let's see what you can do:
   
           // get the currently executing user:
           //获取当前的用户对象 Subject
           Subject currentUser = SecurityUtils.getSubject();
   
           // Do some stuff with a Session (no need for a web or EJB container!!!)
           //通过当前用户拿到session
           Session session = currentUser.getSession();
           session.setAttribute("someKey", "aValue");
           String value = (String) session.getAttribute("someKey");
           if (value.equals("aValue")) {
               log.info("Subject=>session [" + value + "]");
           }
   
           // let's login the current user so we can check against roles and permissions:
           //判断当前的用户是否被认证~
           if (!currentUser.isAuthenticated()) {
               //token:令牌 没有获取  随机
               UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
               token.setRememberMe(true); //设置记住我
               try {
                   currentUser.login(token); //执行了登录操作
               } catch (UnknownAccountException uae) {
                   log.info("There is no user with username of " + token.getPrincipal());
               } catch (IncorrectCredentialsException ice) {
                   log.info("Password for account " + token.getPrincipal() + " was incorrect!");
               } catch (LockedAccountException lae) {
                   log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                            "Please contact your administrator to unlock it.");
               }
               // ... catch more exceptions here (maybe custom ones specific to your application?
               catch (AuthenticationException ae) {
                   //unexpected condition?  error?
               }
           }
   
           //say who they are:
           //print their identifying principal (in this case, a username):
           log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");
   
           //test a role:
           if (currentUser.hasRole("schwartz")) {
               log.info("May the Schwartz be with you!");
           } else {
               log.info("Hello, mere mortal.");
           }
           //粗粒度
           //test a typed permission (not instance-level)
           if (currentUser.isPermitted("lightsaber:wield")) {
               log.info("You may use a lightsaber ring.  Use it wisely.");
           } else {
               log.info("Sorry, lightsaber rings are for schwartz masters only.");
           }
           //        细粒度
           //a (very powerful) Instance Level permission:
           if (currentUser.isPermitted("winnebago:drive:eagle5")) {
               log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                        "Here are the keys - have fun!");
           } else {
               log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
           }
   
           //注销
           //all done - log out!
           currentUser.logout();
   
           //结束
           System.exit(0);
       }
   }
   ```

   

7. 运行测试

   ![image-20220419174602505](https://gitee.com/linda12138/picgo/raw/master/images/image-20220419174602505.png)



#### 17.3、整合Shiro环境搭建

1. 新建一个模块springboot-shiro

2. 导入基于Shiro的pom.xml依赖

   ```xml
   <!--
       Subject 用户
       SecurityManager 管理所有用户
       Realm  连接数据
   -->
   <!--shiro  整合spring的包-->
   <!-- https://mvnrepository.com/artifact/org.apache.shiro/shiro-spring-boot-web-starter -->
   <dependency>
       <groupId>org.apache.shiro</groupId>
       <artifactId>shiro-spring-boot-web-starter</artifactId>
       <version>1.9.0</version>
   </dependency>
   //thymeleaf
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-thymeleaf</artifactId>
       <version>2.6.6</version>
   </dependency>
   ```

   

3. 在templates下新建index.html作为首页测试环境

   ```java
   @RequestMapping({"/","/index"})
   public String toIndex(Model model){
       model.addAttribute("msg","hello,shiro");
       return "index";
   }
   ```

   

4. 新建config编写Shiro配置文件

   ```java
   package com.peng.config;
   
   import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
   import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
   import org.springframework.beans.factory.annotation.Qualifier;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   
   import java.util.HashMap;
   import java.util.LinkedHashMap;
   import java.util.Map;
   
   @Configuration
   public class ShiroConfig {
   
       //ShiroFilterFactoryBean
       @Bean
       public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
           ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
           //设置安全管理器
           bean.setSecurityManager(defaultWebSecurityManager);
           //添加shiro内置过滤器
           /*
           * anon:无需认证就可以访问
           * authc:必须认证了才能访问
           * user:必须拥有记住我才能访问
           * perms:拥有对某个资源的权限才能访问
           * role:拥有某个角色权限才能访问
           * */
           return bean;
       }
       //DafaultWebSecurityManager :2
       @Bean(name = "SecurityManager")
       public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
           DefaultWebSecurityManager SecurityManager = new DefaultWebSecurityManager();
           //关联UserRealm
           SecurityManager.setRealm(userRealm);
           return SecurityManager;
       }
   
       //创建 realm 对象 需要自定义:1
       @Bean
       public UserRealm userRealm(){
           return new UserRealm();
       }
   }
   ```

   

5. 在编写Shiro配置文件之前,需要先创建realm对象

   ```java
   package com.peng.config;
   
   import org.apache.shiro.authc.AuthenticationException;
   import org.apache.shiro.authc.AuthenticationInfo;
   import org.apache.shiro.authc.AuthenticationToken;
   import org.apache.shiro.authz.AuthorizationInfo;
   import org.apache.shiro.realm.AuthorizingRealm;
   import org.apache.shiro.subject.PrincipalCollection;
   
   //自定义的UserRealm  extends AuthorizingRealm
   public class UserRealm extends AuthorizingRealm {
       //授权
       @Override
       protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
           System.out.println("执行了==》授权doGetAuthorizationInfo");
           return null;
       }
   
       //认证
       @Override
       protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
           System.out.println("执行了==》认证doGetAuthorizationInfo");
           return null;
       }
   }
   ```



#### 17.4、Shiro实现登录拦截

1. 编写一些要用到的前端页面

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
       <head>
           <meta charset="UTF-8">
           <title>Title</title>
       </head>
       <body>
           <h1>首页</h1>
           <p th:text="${msg}"></p>
           <hr>
   
           <a th:href="@{/user/add}">add</a>  | <a th:href="@{/user/update}">update</a>
       </body>
   </html>
   ```

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org">
       <head>
           <meta charset="UTF-8">
           <title>Title</title>
       </head>
       <body>
           <h1>登录</h1>
           <hr>
           <p th:text="${msg}" style="color: red"></p>
           <form th:action="@{/login}">
               <p>用户名：<input type="text" name="username"></p>
               <p>密码：<input type="password" name="password"></p>
               <p><input type="submit"></p>
           </form>
       </body>
   </html>
   ```

   ```html
   <!DOCTYPE html>
   <html lang="en">
       <head>
           <meta charset="UTF-8">
           <title>Title</title>
       </head>
       <body>
           <h1>add</h1>
       </body>
   </html>
   ```

   ```html
   <!DOCTYPE html>
   <html lang="en">
       <head>
           <meta charset="UTF-8">
           <title>Title</title>
       </head>
       <body>
           <h1>update</h1>
       </body>
   </html>
   ```

2. 编写控制类

   ```java
   package com.peng.controller;
   
   import org.springframework.stereotype.Controller;
   import org.springframework.ui.Model;
   import org.springframework.web.bind.annotation.RequestMapping;
   
   @Controller
   public class MyController {
       @RequestMapping({"/","/index"})
       public String toIndex(Model model){
           model.addAttribute("msg","hello,shiro");
           return "index";
       }
   
       @RequestMapping("/user/add")
       public String add(){
           return "user/add";
       }
   
       @RequestMapping("/user/update")
       public String update(){
           return "user/update";
       }
       @RequestMapping("/tologin")
       public String toLogin(){
           return "login";
       }
   }
   ```

   

3. 实现拦截

   ```java
   //添加shiro内置过滤器
   /*
           * anon:无需认证就可以访问
           * authc:必须认证了才能访问
           * user:必须拥有记住我才能访问
           * perms:拥有对某个资源的权限才能访问
           * role:拥有某个角色权限才能访问
           * */
   //拦截
   Map<String,String> filtermap = new LinkedHashMap<>();
   //        filtermap.put("/user/add","authc");
   //        filtermap.put("/user/update","authc");
   filtermap.put("/user/*","authc");
   bean.setFilterChainDefinitionMap(filtermap);
   ```

   

4. 设置登录的请求

   ```java
   bean.setLoginUrl("/tologin");
   ```

   

#### 17.5、Shiro实现用户认证

1. 编写需要的controller

   ```java
   @RequestMapping("/login")
   public String login(String username,String password,Model model){
       //获取当前的用户
       Subject subject = SecurityUtils.getSubject();
       //封装用户的登录数据
       UsernamePasswordToken token = new UsernamePasswordToken(username, password);
       try {
           subject.login(token);//执行登录的方法，如果没有异常就🆗了
           return "index";
       }catch (UnknownAccountException e){//用户名不存在
           model.addAttribute("msg","用户名异常");
           return "login";
       }
       catch (IncorrectCredentialsException e){//密码不存在
           model.addAttribute("msg","密码错误");
           return "login";
       }
   }
   ```

   

2. 在UserRealm中认证用户名密码

   ```java
   System.out.println("执行了==》认证doGetAuthorizationInfo");
   //用户名，密码  数据库中取
   String name="root";
   String password = "123456";
   
   UsernamePasswordToken userToken =  (UsernamePasswordToken) token;
   if (!userToken.getUsername().equals(name)){
       return null;//抛出异常 UnknownAccountException
   }
   
   //密码认证，shiro做
   return new SimpleAuthenticationInfo("",password,"");
   ```

   

3. 运行测试



#### 17.6、Shiro整合mybatis

1. 导入基于mybatis的pom依赖

   ```xml
   <!--mybatis-->
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>8.0.15</version>
   </dependency>
   <dependency>
       <groupId>log4j</groupId>
       <artifactId>log4j</artifactId>
       <version>1.2.17</version>
   </dependency>
   <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
   <dependency>
       <groupId>com.alibaba</groupId>
       <artifactId>druid</artifactId>
       <version>1.2.9</version>
   </dependency>
   
   <dependency>
       <groupId>org.mybatis.spring.boot</groupId>
       <artifactId>mybatis-spring-boot-starter</artifactId>
       <version>2.0.0</version>
   </dependency>
   <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
   <dependency>
       <groupId>org.projectlombok</groupId>
       <artifactId>lombok</artifactId>
       <version>1.18.24</version>
   </dependency>
   ```

   

2. 编写数据库配置文件（基于druid的配置）

   ```yaml
   
   spring:
     datasource:
       username: root
       password:
       #?serverTimezone=UTC解决时区的报错
       url: jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
       driver-class-name: com.mysql.cj.jdbc.Driver
       type: com.alibaba.druid.pool.DruidDataSource
   
       #Spring Boot 默认是不注入这些属性值的，需要自己绑定
       #druid 数据源专有配置
       initialSize: 5
       minIdle: 5
       maxActive: 20
       maxWait: 60000
       timeBetweenEvictionRunsMillis: 60000
       minEvictableIdleTimeMillis: 300000
       validationQuery: SELECT 1 FROM DUAL
       testWhileIdle: true
       testOnBorrow: false
       testOnReturn: false
       poolPreparedStatements: true
   
       #配置监控统计拦截的filters，stat:监控统计、log4j：日志记录、wall：防御sql注入
       #如果允许时报错  java.lang.ClassNotFoundException: org.apache.log4j.Priority
       #则导入 log4j 依赖即可，Maven 地址：https://mvnrepository.com/artifact/log4j/log4j
       filters: stat,wall,log4j
       maxPoolPreparedStatementPerConnectionSize: 20
       useGlobalDataSourceStat: true
       connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
   ```

   

3. application.properties

   ```properties
   mybatis.type-aliases-package=com.peng.pojo
   mybatis.mapper-locations=classpath:mapper/*.xml
   ```

   

4. 编写pojo实体类

   ```java
   package com.peng.pojo;
   
   import lombok.AllArgsConstructor;
   import lombok.Data;
   import lombok.NoArgsConstructor;
   
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class User {
       private int id;
       private String name;
       private String pwd;
   }
   ```

   

5. 编写mapper接口

   ```java
   package com.peng.mapper;
   
   import com.peng.pojo.User;
   import org.apache.ibatis.annotations.Mapper;
   import org.springframework.stereotype.Repository;
   
   @Repository
   @Mapper
   public interface UserMapper {
       public User queryUserByName(String name);
   }
   ```

   

6. UserMapper.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <mapper namespace="com.peng.mapper.UserMapper">
   
       <select id="queryUserByName" resultType="User" parameterType="String">
           select * from mybatis.user where name=#{name}
       </select>
   
   </mapper>
   ```

   

7. 测试数据库是否连接成功

   ```java
   package com.peng;
   
   import com.peng.service.UserServiceImpl;
   import org.junit.jupiter.api.Test;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.boot.test.context.SpringBootTest;
   
   @SpringBootTest
   class ShiroSpringbootApplicationTests {
       @Autowired
       UserServiceImpl userService;
       @Test
       void contextLoads() {
           System.out.println(userService.queryUserByName("鹏飞"));
       }
   }
   ```

   

8. 在UserRealm中获取用户名密码

   ```java
   package com.peng.config;
   
   import com.peng.pojo.User;
   import com.peng.service.UserService;
   import org.apache.shiro.authc.*;
   import org.apache.shiro.authz.AuthorizationInfo;
   import org.apache.shiro.realm.AuthorizingRealm;
   import org.apache.shiro.subject.PrincipalCollection;
   import org.springframework.beans.factory.annotation.Autowired;
   
   //自定义的UserRealm  extends AuthorizingRealm
   public class UserRealm extends AuthorizingRealm {
   
       @Autowired
       UserService userService;
   
       //授权
       @Override
       protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
           System.out.println("执行了==》授权doGetAuthorizationInfo");
           return null;
       }
   
       //认证
       @Override
       protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
           System.out.println("执行了==》认证doGetAuthorizationInfo");
           //用户名，密码  数据库中取
           //连接真实数据库
           UsernamePasswordToken userToken =  (UsernamePasswordToken) token;
           User user = userService.queryUserByName(userToken.getUsername());
   
           if (user==null){//没有这个人
               return null; //UnknownAccountException
           }
   
           //可以加密  MD5
           //密码认证，shiro做
           return new SimpleAuthenticationInfo("",user.getPwd(),"");
       }
   }
   ```

   

9. 启动测试

   

#### 17.7、Shiro实现授权

1. 授权，正常的情况下，没有被授权，会跳到未授权页面(ShiroConfig.java)

   ```java
   filtermap.put("/user/add","perms[user:add]");
   filtermap.put("/user/update","perms[user:update]");
   ```

   

2. 在Controller设置未授权页面的跳转

   ```java
   @RequestMapping("/noauth")
   @ResponseBody
   public String unauthorized(){
       return "未经授权，无法访问此页面";
   }
   ```

   

3. 配置未授权页面(ShiroConfig.java)

   ```java
   bean.setUnauthorizedUrl("/noauth");
   ```

   

4. 在UserRealm实现授权

   ```java
       //授权
       @Override
       protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
           System.out.println("执行了==》授权doGetAuthorizationInfo");
           SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
          info.addStringPermission("user:add");
           return info;
       }
   ```

   

5. 运行测试，此时用户就拥有了add的权限

6. 通过数据库实现授权

7. 在user表添加perms列

   ![image-20220419213812850](https://gitee.com/linda12138/picgo/raw/master/images/image-20220419213812850.png)

8. 把perms添加到实体类中

   ```java
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class User {
       private int id;
       private String name;
       private String pwd;
       private String perms;
   }
   ```

   

9. 在UserRealm中进行授权

   ```java
   //授权
   @Override
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       System.out.println("执行了==》授权doGetAuthorizationInfo");
       SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
       //        info.addStringPermission("user:add");
       //拿到当前登录的这个对象
       Subject subject = SecurityUtils.getSubject();
       User currentUser = (User) subject.getPrincipal();
   
       //设置当前用户的权限
       info.addStringPermission(currentUser.getPerms());
       return info;
   }
   ```

   

10. 测试，此时`鹏飞`用户只拥有`add`权限，无法访问`update`,`哈哈哈`用户只拥有`update`权限，无法访问`add`

11. 完整UserRealm.java

    ```java
    package com.peng.config;
    
    import com.peng.pojo.User;
    import com.peng.service.UserService;
    import org.apache.shiro.SecurityUtils;
    import org.apache.shiro.authc.*;
    import org.apache.shiro.authz.AuthorizationInfo;
    import org.apache.shiro.authz.SimpleAuthorizationInfo;
    import org.apache.shiro.realm.AuthorizingRealm;
    import org.apache.shiro.subject.PrincipalCollection;
    import org.apache.shiro.subject.Subject;
    import org.springframework.beans.factory.annotation.Autowired;
    
    //自定义的UserRealm  extends AuthorizingRealm
    public class UserRealm extends AuthorizingRealm {
    
        @Autowired
        UserService userService;
    
        //授权
        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            System.out.println("执行了==》授权doGetAuthorizationInfo");
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //        info.addStringPermission("user:add");
            //拿到当前登录的这个对象
            Subject subject = SecurityUtils.getSubject();
            User currentUser = (User) subject.getPrincipal();
    
            //设置当前用户的权限
            info.addStringPermission(currentUser.getPerms());
            return info;
        }
    
        //认证
        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
            System.out.println("执行了==》认证doGetAuthorizationInfo");
            //用户名，密码  数据库中取
            //连接真实数据库
            UsernamePasswordToken userToken =  (UsernamePasswordToken) token;
            User user = userService.queryUserByName(userToken.getUsername());
    
            if (user==null){//没有这个人
                return null; //UnknownAccountException
            }
    
            //可以加密  MD5
            //密码认证，shiro做
            return new SimpleAuthenticationInfo(user,user.getPwd(),"");
        }
    }
    ```

    

12. 完整ShiroConfig.java

    ```java
    package com.peng.config;
    
    import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
    import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
    import org.springframework.beans.factory.annotation.Qualifier;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    
    import java.util.HashMap;
    import java.util.LinkedHashMap;
    import java.util.Map;
    
    @Configuration
    public class ShiroConfig {
    
        //ShiroFilterFactoryBean
        @Bean
        public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
            ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
            //设置安全管理器
            bean.setSecurityManager(defaultWebSecurityManager);
            //添加shiro内置过滤器
            /*
            * anon:无需认证就可以访问
            * authc:必须认证了才能访问
            * user:必须拥有记住我才能访问
            * perms:拥有对某个资源的权限才能访问
            * role:拥有某个角色权限才能访问
            * */
            //拦截
            Map<String,String> filtermap = new LinkedHashMap<>();
            //授权,正常的情况下，没有被授权，会跳到未授权页面
            filtermap.put("/user/add","perms[user:add]");
            filtermap.put("/user/update","perms[user:update]");
            filtermap.put("/user/*","authc");
            bean.setFilterChainDefinitionMap(filtermap);
            //设置登录的请求
            bean.setLoginUrl("/tologin");
            //未授权页面
            bean.setUnauthorizedUrl("/noauth");
    
            return bean;
        }
        //DafaultWebSecurityManager :2
        @Bean(name = "SecurityManager")
        public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
            DefaultWebSecurityManager SecurityManager = new DefaultWebSecurityManager();
            //关联UserRealm
            SecurityManager.setRealm(userRealm);
            return SecurityManager;
        }
    
        //创建 realm 对象 需要自定义:1
        @Bean
        public UserRealm userRealm(){
            return new UserRealm();
        }
    }
    ```



#### 17.8、Shiro整合Thymeleaf

1. 导入pom依赖

   ```xml
   <dependency>
       <groupId>com.github.theborakompanioni</groupId>
       <artifactId>thymeleaf-extras-shiro</artifactId>
       <version>2.1.0</version>
   </dependency>
   ```

   

2. 在ShiroConfig中整合ShiroDialect

   ```java
   //整合ShiroDialect: 用来整合Shiro 和thymeleaf
   @Bean
   public ShiroDialect getshiroDialect(){
       return new ShiroDialect();
   }
   ```

   

3. 首页shiro判断

   ```html
   <!DOCTYPE html>
   <html lang="en" xmlns:th="http://www.thymeleaf.org"
         xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
       <head>
           <meta charset="UTF-8">
           <title>Title</title>
       </head>
       <body>
           <h1>首页</h1>
           <div shiro:notAuthenticated="">
               <a th:href="@{/tologin}">登录</a>
           </div>
           <p th:text="${msg}"></p>
           <hr>
           <div shiro:hasPermission="user:add">
               <a th:href="@{/user/add}">add</a>
           </div>
           <div shiro:hasPermission="user:update">
               <a th:href="@{/user/update}">update</a>
           </div>
       </body>
   </html>
   ```

   

4. 登录记录session

   ```java
   Subject current = SecurityUtils.getSubject();
   Session session = current.getSession();
   session.setAttribute("loginUser",user);
   ```

5. 完整UserRealm

   ```java
   package com.peng.config;
   
   import com.peng.pojo.User;
   import com.peng.service.UserService;
   import org.apache.shiro.SecurityUtils;
   import org.apache.shiro.authc.*;
   import org.apache.shiro.authz.AuthorizationInfo;
   import org.apache.shiro.authz.SimpleAuthorizationInfo;
   import org.apache.shiro.realm.AuthorizingRealm;
   import org.apache.shiro.session.Session;
   import org.apache.shiro.subject.PrincipalCollection;
   import org.apache.shiro.subject.Subject;
   import org.springframework.beans.factory.annotation.Autowired;
   
   //自定义的UserRealm  extends AuthorizingRealm
   public class UserRealm extends AuthorizingRealm {
   
       @Autowired
       UserService userService;
   
       //授权
       @Override
       protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
           System.out.println("执行了==》授权doGetAuthorizationInfo");
           SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
           //        info.addStringPermission("user:add");
           //拿到当前登录的这个对象
           Subject subject = SecurityUtils.getSubject();
           User currentUser = (User) subject.getPrincipal();
   
           //设置当前用户的权限
           info.addStringPermission(currentUser.getPerms());
           return info;
       }
   
       //认证
       @Override
       protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
           System.out.println("执行了==》认证doGetAuthorizationInfo");
           //用户名，密码  数据库中取
           //连接真实数据库
           UsernamePasswordToken userToken =  (UsernamePasswordToken) token;
           User user = userService.queryUserByName(userToken.getUsername());
   
           if (user==null){//没有这个人
               return null; //UnknownAccountException
           }
   
           Subject current = SecurityUtils.getSubject();
           Session session = current.getSession();
           session.setAttribute("loginUser",user);
   
           //可以加密  MD5
           //密码认证，shiro做
           return new SimpleAuthenticationInfo(user,user.getPwd(),"");
       }
   }
   ```

   

6. 完整ShiroConfig

   ```java
   package com.peng.config;
   
   import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
   import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
   import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
   import org.springframework.beans.factory.annotation.Qualifier;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   
   import java.util.HashMap;
   import java.util.LinkedHashMap;
   import java.util.Map;
   
   @Configuration
   public class ShiroConfig {
   
       //ShiroFilterFactoryBean
       @Bean
       public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
           ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
           //设置安全管理器
           bean.setSecurityManager(defaultWebSecurityManager);
           //添加shiro内置过滤器
           /*
           * anon:无需认证就可以访问
           * authc:必须认证了才能访问
           * user:必须拥有记住我才能访问
           * perms:拥有对某个资源的权限才能访问
           * role:拥有某个角色权限才能访问
           * */
           //拦截
           Map<String,String> filtermap = new LinkedHashMap<>();
           //授权,正常的情况下，没有被授权，会跳到未授权页面
           filtermap.put("/user/add","perms[user:add]");
           filtermap.put("/user/update","perms[user:update]");
           filtermap.put("/user/*","authc");
           bean.setFilterChainDefinitionMap(filtermap);
           //设置登录的请求
           bean.setLoginUrl("/tologin");
           //未授权页面
           bean.setUnauthorizedUrl("/noauth");
   
           return bean;
       }
       //DafaultWebSecurityManager :2
       @Bean(name = "SecurityManager")
       public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
           DefaultWebSecurityManager SecurityManager = new DefaultWebSecurityManager();
           //关联UserRealm
           SecurityManager.setRealm(userRealm);
           return SecurityManager;
       }
   
       //创建 realm 对象 需要自定义:1
       @Bean
       public UserRealm userRealm(){
           return new UserRealm();
       }
   
       //整合ShiroDialect: 用来整合Shiro 和thymeleaf
       @Bean
       public ShiroDialect getshiroDialect(){
           return new ShiroDialect();
       }
   }
   ```

   

7. 运行测试



   

### 18、集成Swagger

------

![image-20220421111321306](https://gitee.com/linda12138/picgo/raw/master/images/image-20220421111321306.png)

**学习目标：**

- 了解Swagger的概念及作用
- 掌握在项目中集成Swagger自动生成API文档

#### 18.1、Swagger简介

**前后端分离**

- 前端 -> 前端控制层、视图层
- 后端 -> 后端控制层、服务层、数据访问层
- 前后端通过API进行交互
- 前后端相对独立且松耦合

**产生的问题**

- 前后端集成，前端或者后端无法做到“及时协商，尽早解决”，最终导致问题集中爆发

**解决方案**

- 首先定义schema [ 计划的提纲 ]，并实时跟踪最新的API，降低集成风险

**Swagger**

- 号称世界上最流行的API框架
- Restful Api 文档在线自动生成器 => **API 文档 与API 定义同步更新**
- 直接运行，在线测试API
- 支持多种语言 （如：Java，PHP等）
- 官网：https://swagger.io/

#### 18.2、集成Swagger

**SpringBoot集成Swagger** => **springfox**，两个jar包

- **Springfox-swagger2**
- swagger-springmvc

**使用Swagger**

要求：jdk 1.8 + 否则swagger2无法运行

新版本3.0直接使用springfox-boot-starter即可步骤：

1、新建一个SpringBoot-web项目

2、添加Maven依赖

```xml
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```

新版本3.0

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
```

3、编写HelloController，测试确保运行成功！

4、要使用Swagger，我们需要编写一个配置类-SwaggerConfig来配置 Swagger

```java
@Configuration //配置类
@EnableSwagger2// 开启Swagger2的自动配置
public class SwaggerConfig {  
}
```

新版用@EnableOpenApi

```java
@Configuration
@EnableOpenApi //开启Swagger
public class SwaggerConfig {
}
```

5、访问测试 (2.9.2)：http://localhost:8080/swagger-ui.html ，可以看到swagger的界面；

(3.0):http://localhost:8080/swagger-ui/index.html

![image-20220421111815742](https://gitee.com/linda12138/picgo/raw/master/images/image-20220421111815742.png)



#### 18.3、配置Swagger

1、Swagger实例Bean是Docket，所以通过配置Docket实例来配置Swaggger。

```java
@Bean //配置docket以配置Swagger具体参数
public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2);
}
```

2、可以通过apiInfo()属性配置文档信息

```java
//配置Swagger信息 = apiinfo
private ApiInfo info(){
    //作者信息
    Contact contact = new Contact("鹏飞", "https://www.cnblogs.com/peng12138/", "2910919712@qq.com");
    return new ApiInfo("鹏飞的SwaggerAPI",
                       "你妹的",
                       "1.0",
                       "https://www.cnblogs.com/peng12138/",
                       contact,
                       "Apache 2.0",
                       "http://www.apache.org/licenses/LICENSE-2.0",
                       new ArrayList());
}
```

3、Docket 实例关联上 apiInfo()

```java
@Bean
public Docket docket(){
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(info());
}
```

4、重启项目，访问测试 http://localhost:8080/swagger-ui/index.html 看下效果；



#### 18.4、配置扫描接口

1、构建Docket时通过select()方法配置怎么扫描接口。

```java
@Bean
public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
        .apis(RequestHandlerSelectors.basePackage("com.kuang.swagger.controller"))
        .build();
}
```

2、重启项目测试，由于我们配置根据包的路径扫描接口，所以我们只能看到一个类

3、除了通过包路径配置扫描接口外，还可以通过配置其他方式扫描接口，这里注释一下所有的配置方式：

```java
//RequestHandlerSelectors ,配置要扫描接口的方式
//basePackage 指定要扫描的包
//any():扫描全部
//none()：不扫描
//withClassAnnotation:扫描类上的注解,参数是注解的反射对象
//withMethodAnnotation:扫描方法上的注解
```

4、除此之外，我们还可以配置接口扫描过滤：

```java
@Bean
public Docket docket(){

    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(info())
        .select()
        //RequestHandlerSelectors ,配置要扫描接口的方式
        //basePackage 指定要扫描的包
        //any():扫描全部
        //none()：不扫描
        //withClassAnnotation:扫描类上的注解,参数是注解的反射对象
        //withMethodAnnotation:扫描方法上的注解
        .apis(RequestHandlerSelectors.basePackage("com.peng.controller"))
        //paths 过滤
        //                .paths(PathSelectors.ant("/peng/**"))
        .build();//build
}
```

5、这里的可选值还有

```java
any() // 任何请求都扫描
none() // 任何请求都不扫描
regex(final String pathRegex) // 通过正则表达式控制
ant(final String antPattern) // 通过ant()控制
```



#### 18.5、配置Swagger开关

1、通过enable()方法配置是否启用swagger，如果是false，swagger将不能在浏览器中访问了

```java
@Bean
public Docket docket(){
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(info())
        .enable(false)
        .select()
        //RequestHandlerSelectors ,配置要扫描接口的方式
        //basePackage 指定要扫描的包
        //any():扫描全部
        //none()：不扫描
        //withClassAnnotation:扫描类上的注解,参数是注解的反射对象
        //withMethodAnnotation:扫描方法上的注解
        .apis(RequestHandlerSelectors.basePackage("com.peng.controller"))
        //paths 过滤
        //                .paths(PathSelectors.ant("/peng/**"))
        .build();//build
}
```

2、如何动态配置当项目处于test、dev环境时显示swagger，处于prod时不显示？

```java
@Bean
public Docket docket(Environment environment){

    //设置要显示的swagger环境
    Profiles profiles =Profiles.of("dev","test");

    //获取项目的环境
    //environment.acceptsProfiles(profiles); 判断是否处在自己设定的环境当中
    boolean flag = environment.acceptsProfiles(profiles);

    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(info())
        .enable(flag)
        .select()
        //RequestHandlerSelectors ,配置要扫描接口的方式
        //basePackage 指定要扫描的包
        //any():扫描全部
        //none()：不扫描
        //withClassAnnotation:扫描类上的注解,参数是注解的反射对象
        //withMethodAnnotation:扫描方法上的注解
        .apis(RequestHandlerSelectors.basePackage("com.peng.controller"))
        //paths 过滤
        //                .paths(PathSelectors.ant("/peng/**"))
        .build();//build
}
```

3、可以在项目中增加一个dev的配置文件查看效果！

![image-20220421171258965](https://gitee.com/linda12138/picgo/raw/master/images/image-20220421171258965.png)



#### 18.6、配置API的分组

1、如果没有配置分组，默认是default。通过groupName()方法即可配置分组：

```java
@Bean
public Docket docket1(){
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("哈哈哈");
}
```

2、重启项目查看分组

3、如何配置多个分组？配置多个分组只需要配置多个docket即可：

```java
@Bean
public Docket docket1(){
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("哈哈哈");
}
@Bean
public Docket docket2(){
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("你妹的");
}
@Bean
public Docket docket3(){
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("真不戳");
}
```

4、重启项目查看即可

![image-20220421171912625](https://gitee.com/linda12138/picgo/raw/master/images/image-20220421171912625.png)



#### 18.7、实体类配置

1、新建一个实体类

```java
package com.peng.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//@Api("注释")
@ApiModel("用户实体类")
public class User {
    @ApiModelProperty("用户名")
    public String username;
    @ApiModelProperty("密码")
    public String password;
}
```

2、只要这个实体在**请求接口**的返回值上（即使是泛型），都能映射到实体项中：

```java
//ApiOperation接口 不能放在类上
@ApiOperation("post测试类")
@PostMapping("/postt")
public User postt(@ApiParam("用户") User user){
    return user;
}
```

3、重启查看测试

![image-20220421173947071](https://gitee.com/linda12138/picgo/raw/master/images/image-20220421173947071.png)

注：并不是因为@ApiModel这个注解让实体显示在这里了，而是只要出现在接口方法的返回值上的实体都会显示在这里，而@ApiModel和@ApiModelProperty这两个注解只是为实体添加注释的。

@ApiModel为类添加注释

@ApiModelProperty为类属性添加注释



#### 18.8、常用注解

Swagger的所有注解定义在io.swagger.annotations包下

下面列一些经常用到的，未列举出来的可以另行查阅说明：

| Swagger注解                                            | 简单说明                                             |
| ------------------------------------------------------ | ---------------------------------------------------- |
| @Api(tags = "xxx模块说明")                             | 作用在模块类上                                       |
| @ApiOperation("xxx接口说明")                           | 作用在接口方法上                                     |
| @ApiModel("xxxPOJO说明")                               | 作用在模型类上：如VO、BO                             |
| @ApiModelProperty(value = "xxx属性说明",hidden = true) | 作用在类方法和属性上，hidden设置为true可以隐藏该属性 |
| @ApiParam("xxx参数说明")                               | 作用在参数、方法和字段上，类似@ApiModelProperty      |

我们也可以给请求的接口配置一些注释

```java
//ApiOperation接口 不能放在类上
@ApiOperation("hello控制类")
@GetMapping("/hello1")
public String hello1(@ApiParam("用户名") String username){
    return "hello"+username;
}
```

这样的话，可以给一些比较难理解的属性或者接口，增加一些配置信息，让人更容易阅读！

相较于传统的Postman或Curl方式测试接口，使用swagger简直就是傻瓜式操作，不需要额外说明文档(写得好本身就是文档)而且更不容易出错，只需要录入数据然后点击Execute，如果再配合自动化框架，可以说基本就不需要人为操作了。

Swagger是个优秀的工具，现在国内已经有很多的中小型互联网公司都在使用它，相较于传统的要先出Word接口文档再测试的方式，显然这样也更符合现在的快速迭代开发行情。当然了，提醒下大家在正式环境要记得关闭Swagger，一来出于安全考虑二来也可以节省运行时内存。

### 19、异步任务

------

1、创建一个service包

2、创建一个类AsyncService

异步处理还是非常常用的，比如我们在网站上发送邮件，后台会去发送邮件，此时前台会造成响应不动，直到邮件发送完毕，响应才会成功，所以我们一般会采用多线程的方式去处理这些任务。

编写方法，假装正在处理数据，使用线程设置一些延时，模拟同步等待的情况；

```java
@Service
public class AsyncService {
    
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据正在处理");
    }
}
```

3、编写controller包

4、编写AsyncController类

我们去写一个Controller测试一下

```java
@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @RequestMapping("/hello")
    public String hello(){
        asyncService.hello();//停止三秒
        return "ok";
    }
}
```

5、访问http://localhost:8080/hello进行测试，3秒后出现ok，这是同步等待的情况。

问题：我们如果想让用户直接得到消息，就在后台使用多线程的方式进行处理即可，但是每次都需要自己手动去编写多线程的实现的话，太麻烦了，我们只需要用一个简单的办法，在我们的方法上加一个简单的注解即可，如下：

6、给hello方法添加@Async注解；

```java
@Service
public class AsyncService {

    //告诉spring这是一个异步的方法
    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据正在处理");
    }
}

```

SpringBoot就会自己开一个线程池，进行调用！但是要让这个注解生效，我们还需要在主程序上添加一个注解@EnableAsync ，开启异步注解功能；

```java
//开启异步注解功能
@EnableAsync
@SpringBootApplication
public class Springboot09TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot09TestApplication.class, args);
    }

}
```

7、重启测试，网页瞬间响应，后台代码依旧执行！



### 20、邮件任务

------

邮件发送，在我们的日常开发中，也非常的多，Springboot也帮我们做了支持

- 邮件发送需要引入spring-boot-start-mail
- SpringBoot 自动配置MailSenderAutoConfiguration
- 定义MailProperties内容，配置在application.yml中
- 自动装配JavaMailSender
- 测试邮件发送

**测试：**

1、引入pom依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```

看它引入的依赖，可以看到 jakarta.mail

```xml
<dependency>
    <groupId>com.sun.mail</groupId>
    <artifactId>jakarta.mail</artifactId>
    <version>1.6.4</version>
    <scope>compile</scope>
</dependency>
```

2、查看自动配置类：MailSenderAutoConfiguration

![image-20220422162050404](https://gitee.com/linda12138/picgo/raw/master/images/image-20220422162050404.png)

这个类中存在bean，JavaMailSenderImpl

![image-20220422162132749](https://gitee.com/linda12138/picgo/raw/master/images/image-20220422162132749.png)

然后我们去看下配置文件

```java
@ConfigurationProperties(
    prefix = "spring.mail"
)
public class MailProperties {
    private static final Charset DEFAULT_CHARSET;
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String protocol = "smtp";
    private Charset defaultEncoding;
    private Map<String, String> properties;
    private String jndiName;
}
```

3、配置文件：

```properties
spring.mail.username=2910919712@qq.com
spring.mail.password=qq授权码
spring.mail.host=smtp.qq.com

# 开启加密验证
spring.mail.properties.mail.smtp.ssl.enable=true
```

获取授权码：在QQ邮箱中的设置->账户->开启pop3和smtp服务

![image-20220422162350253](https://gitee.com/linda12138/picgo/raw/master/images/image-20220422162350253.png)

4、Spring单元测试

```java
package com.peng;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot09TestApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {
        //一个简单的邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("鹏飞你好");
        mailMessage.setText("你妹的啊");

        mailMessage.setTo("2910919712@qq.com");
        mailMessage.setFrom("2910919712@qq.com");
        mailSender.send(mailMessage);
    }

    @Test
    void contextLoads1() throws MessagingException {
        //一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        //正文
        helper.setSubject("你好啊");
        helper.setText("<p style='color:red'>哈哈哈哈哈哈</p>",true);

        //附件
        helper.addAttachment("1.jpg",new File("D:\\壁纸\\锁屏\\1.jpg"));
        helper.addAttachment("2.jpg",new File("D:\\壁纸\\锁屏\\2.jpg"));
        helper.setTo("2910919712@qq.com");
        helper.setFrom("2910919712@qq.com");

        mailSender.send(mimeMessage);
    }

    //封装成方法
    public void sendMail(Boolean html,String subject,String text,String attachmentFilename,File file,String to,String from) throws MessagingException {
        //一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,html);

        //正文
        helper.setSubject(subject);
        helper.setText(text,true);

        //附件
        helper.addAttachment(attachmentFilename,file);

        helper.setTo(to);
        helper.setFrom(from);

        mailSender.send(mimeMessage);
    }
}
```

查看邮箱，邮件接收成功！

我们只需要使用Thymeleaf进行前后端结合即可开发自己网站邮件收发功能了！



### 21、定时任务

------

项目开发中经常需要执行一些定时任务，比如需要在每天凌晨的时候，分析一次前一天的日志信息，Spring为我们提供了异步执行任务调度的方式，提供了两个接口。

- TaskExecutor接口
- TaskScheduler接口

两个注解：

- @EnableScheduling
- @Scheduled

**cron表达式：**

| 字段 | 允许值                | 允许的特殊字符  |
| ---- | --------------------- | --------------- |
| 秒   | 0-59                  | , - * /         |
| 分   | 0-59                  | , - * /         |
| 小时 | 0-23                  | , - * /         |
| 日期 | 1-31                  | , - * ? / L W C |
| 月份 | 1-12                  | , - * /         |
| 星期 | 0-7或SUN-SAT 0,7是SUN | , - * ? / L # C |



| 特殊字符 | 代表含义                   |
| -------- | -------------------------- |
| ,        | 枚举                       |
| -        | 区间                       |
| *        | 任意                       |
| /        | 步长                       |
| ?        | 日/星期冲突匹配            |
| L        | 最后                       |
| W        | 工作日                     |
| C        | 和calendar联系后计算过的值 |
| #        | 星期，4#2，第2个星期三     |

**测试步骤：**

1、创建一个ScheduledService

我们里面存在一个hello方法，他需要定时执行，怎么处理呢？

```java
@Service
public class SchedulingService {

    //在一个特定的时间执行这个方法 Timer
    //cron表达式
    //秒 分 时 日 月 周
    @Scheduled(cron = "0 40 16 * * ?")
    public void hello(){
        System.out.println("hello被执行了");
    }
}
```

2、这里写完定时任务之后，我们需要在主程序上增加@EnableScheduling 开启定时任务功能

```java
@EnableAsync //开启异步注解功能
@EnableScheduling //开启基于注解的定时任务
@SpringBootApplication
public class SpringbootTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTaskApplication.class, args);
    }

}
```

3、我们来详细了解下cron表达式；

http://www.bejson.com/othertools/cron/

4、常用的表达式

```
（1）0/2 * * * * ?   表示每2秒 执行任务
（1）0 0/2 * * * ?   表示每2分钟 执行任务
（1）0 0 2 1 * ?   表示在每月的1日的凌晨2点调整任务
（2）0 15 10 ? * MON-FRI   表示周一到周五每天上午10:15执行作业
（3）0 15 10 ? 6L 2002-2006   表示2002-2006年的每个月的最后一个星期五上午10:15执行作
（4）0 0 10,14,16 * * ?   每天上午10点，下午2点，4点
（5）0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时
（6）0 0 12 ? * WED   表示每个星期三中午12点
（7）0 0 12 * * ?   每天中午12点触发
（8）0 15 10 ? * *   每天上午10:15触发
（9）0 15 10 * * ?     每天上午10:15触发
（10）0 15 10 * * ?   每天上午10:15触发
（11）0 15 10 * * ? 2005   2005年的每天上午10:15触发
（12）0 * 14 * * ?     在每天下午2点到下午2:59期间的每1分钟触发
（13）0 0/5 14 * * ?   在每天下午2点到下午2:55期间的每5分钟触发
（14）0 0/5 14,18 * * ?     在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
（15）0 0-5 14 * * ?   在每天下午2点到下午2:05期间的每1分钟触发
（16）0 10,44 14 ? 3 WED   每年三月的星期三的下午2:10和2:44触发
（17）0 15 10 ? * MON-FRI   周一至周五的上午10:15触发
（18）0 15 10 15 * ?   每月15日上午10:15触发
（19）0 15 10 L * ?   每月最后一日的上午10:15触发
（20）0 15 10 ? * 6L   每月的最后一个星期五上午10:15触发
（21）0 15 10 ? * 6L 2002-2005   2002年至2005年的每月的最后一个星期五上午10:15触发
（22）0 15 10 ? * 6#3   每月的第三个星期五上午10:15触发
```



### 22、Dubbo和Zookeeper集成

------

#### 22.1、什么是分布式理论

在《分布式系统原理与范型》一书中有如下定义：“分布式系统是若干独立计算机的集合，这些计算机对于用户来说就像单个相关系统”；

分布式系统是由一组通过网络进行通信、为了完成共同的任务而协调工作的计算机节点组成的系统。分布式系统的出现是为了用廉价的、普通的机器完成单个计算机无法完成的计算、存储任务。其目的是**利用更多的机器，处理更多的数据**。

分布式系统（distributed system）是建立在网络之上的软件系统。

首先需要明确的是，只有当单个节点的处理能力无法满足日益增长的计算、存储任务的时候，且硬件的提升（加内存、加磁盘、使用更好的CPU）高昂到得不偿失的时候，应用程序也不能进一步优化的时候，我们才需要考虑分布式系统。因为，分布式系统要解决的问题本身就是和单机系统一样的，而由于分布式系统多节点、通过网络通信的拓扑结构，会引入很多单机系统没有的问题，为了解决这些问题又会引入更多的机制、协议，带来更多的问题。。。

> Dubbo文档

随着互联网的发展，网站应用的规模不断扩大，常规的垂直应用架构已无法应对，分布式服务架构以及流动计算架构势在必行，急需**一个治理系统**确保架构有条不紊的演进。

在Dubbo的官网文档有这样一张图

![640 (4)](https://gitee.com/linda12138/picgo/raw/master/images/640%20(4).png)

> 单一应用架构

当网站流量很小时，只需一个应用，将所有功能都部署在一起，以减少部署节点和成本。此时，用于简化增删改查工作量的数据访问框架(ORM)是关键。

![640 (3)](https://gitee.com/linda12138/picgo/raw/master/images/640%20(3).png)

适用于小型网站，小型管理系统，将所有功能都部署到一个功能里，简单易用。

**缺点：**

1、性能扩展比较难

2、协同开发问题

3、不利于升级维护

> 垂直应用架构

当访问量逐渐增大，单一应用增加机器带来的加速度越来越小，将应用拆成互不相干的几个应用，以提升效率。此时，用于加速前端页面开发的Web框架(MVC)是关键。

![640 (2)](https://gitee.com/linda12138/picgo/raw/master/images/640%20(2).png)

通过切分业务来实现各个模块独立部署，降低了维护和部署的难度，团队各司其职更易管理，性能扩展也更方便，更有针对性。

缺点：公用模块无法重复利用，开发性的浪费

> 分布式服务架构

当垂直应用越来越多，应用之间交互不可避免，将核心业务抽取出来，作为独立的服务，逐渐形成稳定的服务中心，使前端应用能更快速的响应多变的市场需求。此时，用于提高业务复用及整合的**分布式服务框架(RPC)**是关键。

![640 (1)](https://gitee.com/linda12138/picgo/raw/master/images/640%20(1).png)

> 流动计算架构

当服务越来越多，容量的评估，小服务资源的浪费等问题逐渐显现，此时需增加一个调度中心基于访问压力实时管理集群容量，提高集群利用率。此时，用于**提高机器利用率的资源调度和治理中心**(SOA)[ Service Oriented Architecture]是关键。

![640](https://gitee.com/linda12138/picgo/raw/master/images/640.png)



#### 22.2、什么是RPC

RPC【Remote Procedure Call】是指远程过程调用，是一种进程间通信方式，他是一种技术的思想，而不是规范。它允许程序调用另一个地址空间（通常是共享网络的另一台机器上）的过程或函数，而不用程序员显式编码这个远程调用的细节。即程序员无论是调用本地的还是远程的函数，本质上编写的调用代码基本相同。

也就是说两台服务器A，B，一个应用部署在A服务器上，想要调用B服务器上应用提供的函数/方法，由于不在一个内存空间，不能直接调用，需要通过网络来表达调用的语义和传达调用的数据。为什么要用RPC呢？就是无法在一个进程内，甚至一个计算机内通过本地调用的方式完成的需求，比如不同的系统间的通讯，甚至不同的组织间的通讯，由于计算能力需要横向扩展，需要在多台机器组成的集群上部署应用。RPC就是要像调用本地的函数一样去调远程函数；

推荐阅读文章：https://www.jianshu.com/p/2accc2840a1b

**RPC基本原理**

![6](https://gitee.com/linda12138/picgo/raw/master/images/6.png)

**步骤解析：**

![666](https://gitee.com/linda12138/picgo/raw/master/images/666.png)

RPC两个核心模块：通讯，序列化。

#### 22.3、测试环境搭建

> Dubbo

Apache Dubbo |ˈdʌbəʊ| 是一款高性能、轻量级的开源Java RPC框架，它提供了三大核心能力：面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现。

dubbo官网 http://dubbo.apache.org/zh-cn/index.html

1.了解Dubbo的特性

2.查看官方文档

**dubbo基本概念**

![60](https://gitee.com/linda12138/picgo/raw/master/images/60.png)

**服务提供者**（Provider）：暴露服务的服务提供方，服务提供者在启动时，向注册中心注册自己提供的服务。

**服务消费者**（Consumer）：调用远程服务的服务消费方，服务消费者在启动时，向注册中心订阅自己所需的服务，服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。

**注册中心**（Registry）：注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者

**监控中心**（Monitor）：服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心

**调用关系说明**

- 服务容器负责启动，加载，运行服务提供者。

- 服务提供者在启动时，向注册中心注册自己提供的服务。

- 服务消费者在启动时，向注册中心订阅自己所需的服务。

- 注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。

- 服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。

- 服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。

> Dubbo环境搭建

点进dubbo官方文档，推荐我们使用Zookeeper 注册中心

什么是zookeeper呢？可以查看官方文档

> Window下载zookeeper

1、下载zookeeper ：地址:https://zookeeper.apache.org/releases.html#download，最新版！解压zookeeper

2、运行/bin/zkServer.cmd ，初次运行会报错，没有zoo.cfg配置文件；

可能遇到问题：闪退 !

解决方案：编辑zkServer.cmd文件末尾添加pause 。这样运行出错就不会退出，会提示错误信息，方便找到原因。

![image-20220424143557038](https://gitee.com/linda12138/picgo/raw/master/images/image-20220424143557038.png)

3、修改zoo.cfg配置文件

将conf文件夹下面的zoo_sample.cfg复制一份改名为zoo.cfg即可。

注意几个重要位置：

dataDir=./  临时数据存储的目录（可写相对路径）

clientPort=2181  zookeeper的端口号

修改完成后再次启动zookeeper

![image-20220424143655817](https://gitee.com/linda12138/picgo/raw/master/images/image-20220424143655817.png)

4、使用zkCli.cmd测试

ls /：列出zookeeper根下保存的所有节点

![image-20220424143818569](https://gitee.com/linda12138/picgo/raw/master/images/image-20220424143818569.png)

create –e /pengfei 123：创建一个pengfei节点，值为123

![image-20220424143910068](https://gitee.com/linda12138/picgo/raw/master/images/image-20220424143910068.png)

get /pengfei：获取/pengfei节点的值

![image-20220424144001768](https://gitee.com/linda12138/picgo/raw/master/images/image-20220424144001768.png)

我们再来查看一下节点(我的有过dubbo所以有三个)

![image-20220424144024325](https://gitee.com/linda12138/picgo/raw/master/images/image-20220424144024325.png)



> window下安装dubbo-admin

dubbo本身并不是一个服务软件。它其实就是一个jar包，能够帮你的java程序连接到zookeeper，并利用zookeeper消费、提供服务。

但是为了让用户更好的管理监控众多的dubbo服务，官方提供了一个可视化的监控程序dubbo-admin，不过这个监控即使不装也不影响使用。

我们这里来安装一下：

**1、下载dubbo-admin**

地址 ：https://github.com/apache/dubbo-admin/tree/master-0.2.0

**2、解压进入目录**

修改 dubbo-admin\src\main\resources \application.properties 指定zookeeper地址

```properties
server.port=7001
spring.velocity.cache=false
spring.velocity.charset=UTF-8
spring.velocity.layout-url=/templates/default.vm
spring.messages.fallback-to-system-locale=false
spring.messages.basename=i18n/message
spring.root.password=root
spring.guest.password=guest

dubbo.registry.address=zookeeper://127.0.0.1:2181
```

**3、在项目目录下**打包dubbo-admin

```bash
mvn clean package -Dmaven.test.skip=true
```

**第一次打包的过程有点慢，需要耐心等待！直到成功！**

![image-20220424144218246](C:\Users\鹏飞12138\AppData\Roaming\Typora\typora-user-images\image-20220424144218246.png)

4、执行 dubbo-admin\target 下的dubbo-admin-0.0.1-SNAPSHOT.jar

```bash
java -jar dubbo-admin-0.0.1-SNAPSHOT.jar
```

【注意：zookeeper的服务一定要打开！】

执行完毕，我们去访问一下 http://localhost:7001/ ， 这时候我们需要输入登录账户和密码，我们都是默认的root-root；

登录成功后，查看界面

![image-20220424144418476](https://gitee.com/linda12138/picgo/raw/master/images/image-20220424144418476.png)

安装完成！



#### 22.4、SpringBoot + Dubbo + zookeeper

> 框架搭建

**1. 启动zookeeper ！**

**2. IDEA创建一个空项目；**

**3.创建一个模块，实现服务提供者：provider-server ， 选择web依赖即可**

**4.项目创建完毕，我们写一个服务，比如卖票的服务；**

编写接口

```java
package com.peng.service;

public interface TicketService {

    public String getTicket();
}
```

编写实现类

```java
package com.peng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProviderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderServerApplication.class, args);
    }
}
```

**5.创建一个模块，实现服务消费者：consumer-server ， 选择web依赖即可**

**6.项目创建完毕，我们写一个服务，比如用户的服务；**

编写service

```java
package com.peng.service;

public class UserService {
    //想拿到provider-server票,要去注册中心拿
  
}
```

**需求：现在我们的用户想使用买票的服务，这要怎么弄呢 ？**

> 服务提供者

**1、将服务提供者注册到注册中心，我们需要整合Dubbo和zookeeper，所以需要导包**

**我们从dubbo官网进入github，看下方的帮助文档，找到dubbo-springboot，找到依赖包**

```xml
<!-- https://mvnrepository.com/artifact/org.apache.dubbo/dubbo-spring-boot-starter -->
<dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>3.0.7</version>
</dependency>
```

**zookeeper的包我们去maven仓库下载，zkclient；**

```xml
<dependency>
    <groupId>com.github.sgroschupf</groupId>
    <artifactId>zkclient</artifactId>
    <version>0.1</version>
</dependency>
```

**【新版的坑】zookeeper及其依赖包，解决日志冲突，还需要剔除日志依赖；**

```xml
<!-- 引入zookeeper -->
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-framework</artifactId>
    <version>5.2.1</version>
</dependency>
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-recipes</artifactId>
    <version>5.2.1</version>
</dependency>
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-x-discovery</artifactId>
    <version>5.1.0</version>
</dependency>
<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
    <version>3.8.0</version>
    <!--排除这个slf4j-log4j12-->
    <exclusions>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

**2、在springboot配置文件中配置dubbo相关属性！**

```properties
# 当前应用名字
dubbo.application.name=provider-server
# 注册中心地址
dubbo.registry.address=zookeeper://127.0.0.1:2181
#扫描指定包下服务
dubbo.scan.base-packages=com.peng.service

server.port=8002
```

**3、在service的实现类中配置服务注解，发布服务！注意导包问题**

```java
package com.peng.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

//服务注册与发现 zookeeper

@DubboService //可以被扫描到，在项目一启动就自动注册到注册中心
//@Component //这里使用了Dubbo后尽量不要用Service注解
public class TicketServiceImpl implements TicketService{
    @Override
    public String getTicket() {
        return "鹏飞12138";
    }
}
```

**逻辑理解 ：应用启动起来，dubbo就会扫描指定的包下带有@component注解的服务，将它发布在指定的注册中心中！**



> 服务消费者

**1、导入依赖，和之前的依赖一样；**

```xml
<dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>3.0.7</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.github.sgroschupf/zkclient -->
<dependency>
    <groupId>com.github.sgroschupf</groupId>
    <artifactId>zkclient</artifactId>
    <version>0.1</version>
</dependency>

<!-- 引入zookeeper -->
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-framework</artifactId>
    <version>5.2.1</version>
</dependency>
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-recipes</artifactId>
    <version>5.2.1</version>
</dependency>
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-x-discovery</artifactId>
    <version>5.1.0</version>
</dependency>
<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
    <version>3.8.0</version>
    <!--排除这个slf4j-log4j12-->
    <exclusions>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

2、**配置参数**

```properties
#消费者去哪里拿服务 需要暴露自己的名字
dubbo.application.name=consumer-server

#注册中心的地址
dubbo.registry.address=zookeeper://127.0.0.1:2181
```

**3. 本来正常步骤是需要将服务提供者的接口打包，然后用pom文件导入，我们这里使用简单的方式，直接将服务的接口拿过来，路径必须保证正确，即和服务提供者相同；**

![image-20220424154914848](https://gitee.com/linda12138/picgo/raw/master/images/image-20220424154914848.png)

**4. 完善消费者的服务类**

```java
package com.peng.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //想拿到provider-server票,要去注册中心拿
    @DubboReference //引用 pom坐标，可以定义路径相同的接口名
    TicketService ticketService;

    public void butTicket(){
        String ticket = ticketService.getTicket();
        System.out.println("在注册中心拿到=》"+ticket);
    }
}
```

**5. 测试类编写；**

```java
package com.peng;

import com.peng.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConsumerServerApplicationTests {

    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        userService.butTicket();
    }

}
```



> 启动测试

**1. 开启zookeeper**

**2. 打开dubbo-admin实现监控【可以不用做】**

**3. 开启服务者**

**4. 消费者消费测试，结果：**

![image-20220424155152431](https://gitee.com/linda12138/picgo/raw/master/images/image-20220424155152431.png)

**监控中心 ：**

![image-20220424155223389](https://gitee.com/linda12138/picgo/raw/master/images/image-20220424155223389.png)

**ok , 这就是SpingBoot + dubbo + zookeeper实现分布式开发的应用，其实就是一个服务拆分的思想；**
