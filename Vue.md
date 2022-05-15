### 1、Vue:前端体系、前后端分离

------

#### 1.1、概述

​	Vue(读音ju/,类似于view)是一套用于构建用户界面的渐进式框架，发布于2014年2月。与其它大型框架不同的是，Vue被设计为可以自底向上逐层应用。Vu的核心库只关注视图层，不仅易于上手，还便于与第三方库（如：vue-router:跳转，vue-resource:通信，vuex:管理）或既有项目整合。

​	官网：https://v3.cn.vuejs.org/

#### 1.2、前端知识体系

​	想要成为真正的“互联网JVa全栈工程师”还有很长的一段路要走，其中“我大前端”是绕不开的一门必修课。本阶段课程的主要目的就是带领我Jva后台程序员认识前端、了解前端、掌握前端，为实现成为“互联网Java全栈工程师”再向前迈进一步。

> 前端三要素

- HTML(结构)：超文本标记语言(Hyper Text Markup Language),决定网页的结构和内容
- CSS(表现)：层委样式表(Cascading Style Sheets),设定网页的表现样式
- JavaScript(行为)：是一种弱类型脚本语言，其源代码不需经过编译，而是由浏览器解释运行，用于控制网页的行为

> 结构层（html）

> 表现层（CSS）

​	CSS层叠样式表是一门标记语言，并不是编程语言，因此不可以自定义变量，不可以引用等，换句话说就是不具备任何语法支持，它主要缺陷如下：

- 语法不够强大，比如无法嵌套书写，导致模块化开发中需要书写很多重复的选择器

- 没有变量和合理的样式复用机制，使得逻辑上相关的属性值必须以字面量的形式重复输出，导致难以维护

  ​	这就导致了我们在工作中无端增加了许多工作量。为了解决这个问题，前端开发人员会使用一种称之为**【CSS预处理器】**的工具，提供CSS缺失的样式层复用机制、减少冗余代码，提高样式代码的可维护性。大大提高了前端在样式上的开发效率。

> 什么是CSS预处理器

​	CSS预处理器定义了一种新的语言，其基本思想是，用一种专门的编程语言，为CSS增加了一些编程的特性，将CSS作为目标生成文件，然后开发者就只要使用这种语言进行CSS的编码工作。转化成通俗易懂的话来说就是"**用一种专门的编程语言，进行Wb页面样式设计，再通过编译器转化为正常的CSS文件，以供项目使用**”。

​	**常用的CSS预处理器有哪些**

- SASS：基于Ruby,通过服务端处理，功能强大。解析效率高。需要学习Ruby语言，上手难度高于LESS。

- LESS:基于NodeJS,通过客户端处理，使用简单。功能比SASS简单，解析效率也低于

  SASS,但在实际开发中足够了，所以我们后台人员如果需要的话，建议使用LESS。

> 行为层（javaScript）

​	JavaScript一门弱类型脚本语言，其源代码在发往客户端运行之前不需经过编译，而是将文本格式的字符代码发送给浏览器由浏览器解释运行

​	Native原生JS开发

​	原生JS开发，也就是让我们按照**【ECMAScript】**标准的开发方式，简称是ES,特点是所有浏览器都支持。截止到当前博客发布时间，ES标准已发布如下版本：

- ES3

- ES4(内部，未正式发布)

- ES5(全浏览器支持)

- ES6(常用，当前主流版本：webpack打包成为ES5支持！)

- ES7

- ES8

- ES9(草案阶段)

  区别就是逐步增加新特性。

  **TypeScript微软的标准**

  ​	TypeScript是一种由微软开发的自由和开源的编程语言。它是JavaScript的一个超集，而且本质上向这个语言添加了可选的静态类型和基于类的面向对象编程。由安德斯·海尔斯伯格(C#、Delphi、TypeScript之父；.NET创立者)主导。

  ​	该语言的特点就是除了具备ES的特性之外还纳入了许多不在标准范围内的新特性，所以会导致很多浏览器不能直接支持TypeScript语法，需要编译后（编译成JS)才能被浏览器正确执行。

> javaScript框架

- `jQuery`:大家熟知的JavaScript框架，优点是简化了DOM操作，缺点是DOM操作太频繁，影响前端性能；在前端眼里使用它仅仅是为了兼容IE6、7、8;
- `Angular`:Google收购的前端框架，由一群Java程序员开发，其特点是将后台的MVC模式搬到了前端并增加了模块化开发的理念，与微软合作，采用TypeScript语法开发；对后台程序员友好，对前端程序员不太友好；最大的缺点是版本迭代不合理（如：1代>2代，除了名字，基本就是两个东西；截止发表博客时已推出了Angular6)
- `React`:Facebook出品，一款高性能的JS前端框架；特点是提出了新概念【虚拟DOM】用于减少真实DOM操作，在内存中模拟DOM操作，有效的提升了前端渲染效率；：缺点是使用复杂，因为需要额外学习一门【JSX】语言
- `vue`：一款渐进式JavaScript框架，所谓渐进式就是逐步实现新特性的意思，如实现模块化开发、路由、状态管理等新特性。其特点是综合了Angular(模块化)和React(虚拟DOM)的优点
- `Axios`:前端通信框架；因为Vue的边界很明确，就是为了处理DOM,所以并不具备通信能力，此时就需要额外使用一个通信框架与服务器交互；当然也可以直接选择使用jQuy提供的AJAX通信功能

> UI框架

- Ant-Design:阿里巴巴出品，基于React的UI框架
- ElementUl、iview、ice:饿了么出品，基于Vue的UI框架
- Bootstrap:Twitter推出的一个用于前端开发的开源工具包
- AmazeU:又叫“妹子UI”,一款HTML5跨屏前端框架

> javaScript构建工具

- Babel:JS编译工具，主要用于浏览器不支持的ES新特性，比如用于编译TypeScript

- NebPack:模块打包器，主要作用是打包、压缩、合并及按序加载

  **注：以上知识点已将WebApp开发所需技能全部梳理完毕**



#### 1.3、三端统一

> 混合开发（Hybrid App）

​	主要目的是实现一套代码三端统一(PC、Android:,apk、iOS:ipa)并能够调用到设备底层硬件（如：传感器、GPS、摄像头等），打包方式主要有以下两种：

- 云打包：HBuild->HBuildX,DCloud出品；API Cloud
- 本地打包：Cordova(前身是PhoneGap)



#### 1.4、后端技术

​	前端人员为了方便开发也需要掌握一定的后端技术，但我们Java后台人员知道后台知识体系极其庞大复杂，所以为了方便前端人员开发后台应用，就出现了NodeJS这样的技术。

​	NodeJS的作者已经声称放弃NodeJS(说是架构做的不好再加上笨重的node modules,可能让作者不爽了吧)，开始开发全新架构的Deno

​	既然是后台技术，那肯定也需要框架和项目管理工具，NodeJS框架及项目管理工具如下：

- Express:NodeJS框架
- Koa:Express简化版
- NPM:项目综合管理工具，类似于Maven
- YARN:NPM的替代方案，类似于Maven和Gradle的关系



#### 1.5、主流前端框架

Vue.js

> iView

​	iview是一个强大的基于Vue的UI库，有很多实用的基础组件比elementui的组件更丰富，主要服务于PC界面的中后台产品。使用单文件的Vue组件化开发模式基于npm+webpack+babe|开发，支持ES2015高质量、功能丰富友好的API,自由灵活地使用空间。

- 官网地址:https://iview.github.io/

- Github

- iview-admin

  **备注：属于前端主流框架，选型时可考虑使用，主要特点是移动端支持较多**

> ElementUI

​	Element是饿了么前端开源维护的VueU丨组件库，组件齐全，基本涵盖后台所需的所有组件，文档讲解详细，例子也很丰富。主要用于开发PC端的页面，是一个质量比较高的VuU1组件库。

- 官网地址:https://element-plus.gitee.io/zh-CN/

- Github

- vue-element-admin

  **备注：属于前端主流框架，选型时可考虑使用，主要特点是桌面端支持较多下**



#### 1.6、Vue


> 为什么要使用MVVM

MVVM模式和MVC模式一样，主要目的是分离视图(View)和模型(Model),有几大好处

- 低耦合：视图(View)可以独立于Model变H和修改，一个ViewMode|可以绑定到不同的View上，当View变化的时候Model可以不变，当Mode|变化的时候View也可以不变。
- 可复用：你可以把一些视图逻辑放在一个ViewMode|里面，让很多View重用这段视图逻辑。
- 独立开发：开发人员可以专注于业务逻辑和数据的开发(ViewMode),设计人员可以专注于页面设计。
- 可测试：界面紊来是比较难于测试的，而现在测试可以针对ViewModel来写。



> View

​	Viw是视图层，也就是用户界面。前端主要由**HTML**和**CSS**来构建，为了更方便地展现**ViewModel**或者**Model**层的数据，已经产生了各种各样的前后端模板语言，比如FreeMarker、Thymeleaf等等，各大MVVM框架如Vue,js,AngularJS,EJS等也都有自己用来构建用户界面的内置模板语言。

> Model

​	Model是指数据模型，泛指后端进行的各种业务逻辑处理和数据操控，主要围绕数据库系统展开。这里的难点主要在于需要和前端约定统一的**接口规则**

> ViewModel

​	ViewMode|是由前端开发人员组织生成和维护的视图数据层。在这一层，前端开发者对从后端获取的Mod!数据进行转换处理，做二次封装，以生成符合Vⅵew层使用预期的视图数据模型。

​	需要注意的是ViewMode|所封装出来的数据模型包括视图的状态和行为两部分，而Mode层的数据模型是只包含状态的

- 比如页面的这一块展示什么，那一块展示什么这些都属于视图状态（展示）

- 页面加载进来时发生什么，点击这一块发生什么，这一块滚动时发生什么这些都属于视图行为（交互)

  ​	视图状态和行为都封装在了ViewModel里。这样的封装使得ViewModel可以完整地去描述View层。由于实现了双向绑定，ViewModel的内容会实时展现在View层，这是激动人心的，因为前端开发者再也不必低效又麻烦地通过操纵DOM去更新视图。

​		MVVM框架已经把最脏最累的一块做好了，我们开发者只需要处理和维护ViewModel,	更新数据视图就会自动得到相应更新，真正实现**事件驱动编程**。

​		View层展现的不是Model层的数据，而是ViewModel的数据，由ViewModel负责与	Model层交互，这就**完全解耦了Viw层和Model层，这个解耦是至关重要的，它是前后端分	离方案实施的重要一环。**

> MVVM模式的实现者

- Mode:模型层，在这里表示JavaScript对象

- View:视图层，在这里表示DOM(HTML操作的元紊)

- ViewModel:连接视图和数据的中间件，Vue.js就是MVVM中的ViewModel层的实现者

​	在MVVM架构中，是不允许数据和视图直接通信的，只能通过ViewModel来通信，而ViewModel就是定义了一个Observer观察者

- ViewMode|能够观察到数据的变化，并对视图对应的内容进行更新

- ViewMode|能够监听到视图的变化，并能够通知数据发生改变

  ​	至此，我们就明白了，Vue.js就是一个MVVM的实现者，他的核心就是实现了DOM监听与数据绑定

> 为什么要使用Vue.js

- 轻量级，体积小是一个重要指标。Vue.js压缩后有只有20多kb(Angular压缩后56kb+,React压缩后44kb+)
- 移动优先。更适合移动端，比如移动端的Touch事件
- 易上手，学习曲线平稳，文档齐全
- 吸取了Angular(模块化)和React(虚拟DOM)的长处，并拥有自己独特的功能，如：计算属性
- 开源，社区活跃度高
- ...



### 2、Vue基础语法

------

> 下载地址

- 开发版本

  - 包含完整的警告和调试模式:http://vuejs.org/js/vue.js
  - 删除了警告，30.96kbmin+gzip:http://vuejs.org/js/vue.min.js

- CDN

  - <script src="https://unpkg.com/vue@next"></script>

  - <script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.js"></script>

  - <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>



> 代码编写

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div id="app">
            {{message}}
        </div>
        <!--导入Vue.js-->
        <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
        <script>
            var vm = new Vue({
                el:"#app",
                data:{message:"hello,vue!"}
            });
        </script>
    </body>
</html>
```

> V-bind

​	我们已经成功创建了第一个Vue应用！看起来这限渲染一个字符串模板非常类似，但是Vue在背后做了大量工作。现在数据和DOM已经被建立了关联，所有东西都是响应式的。我们在控制台操作对象属性，界面可以实时更新！

​	我们还可以使用v-bind来绑定元素特性！

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div id="app">
            <span v-bind:title="message">鼠标悬停几秒钟查看此处动态绑定的提示信息</span>
        </div>
        <!--导入Vue.js-->
        <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
        <script>
            var vm = new Vue({
                el:"#app",
                data:{message:"hello,vue!"}
            });
        </script>
    </body>
</html>
```

​	你看到的v-bind等被称为指令。指令带有前缀v-,以表示它们是Vue提供的特殊特性。可能你已经猜到了，它们会在渲染的DOM上应用特殊的响应式行为。在这里，该指令的意思是：“将这个元素节点的title特性和Vue实例的message属性保持一致”。

​	如果你再次打开浏览器的JavaScript控制台，输入app.message='新消息'，就会再一次看到这个绑定了title特性的HTML已经进行了更新。



> v-if,v-else

- v-if
- v-else

**在 `<template>` 元素上使用 `v-if` 条件渲染分组**

因为 `v-if` 是一个指令，所以必须将它添加到一个元素上。但是如果想切换多个元素呢？此时可以把一个 `<template>` 元素当做不可见的包裹元素，并在上面使用 `v-if`。最终的渲染结果将不包含 `<template>` 元素。

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div id="app">
            <h3 v-if="type==='A'">A</h3>
            <h3 v-else-if="type==='B'">B</h3>
            <h3 v-else>C</h3>
        </div>
        <!--导入Vue.js-->
        <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
        <script>
            var vm = new Vue({
                el:"#app",
                data:{type:'A'}
            });
        </script>
    </body>
</html>
```



> v-for

**用 `v-for` 把一个数组映射为一组元素**

我们可以用 `v-for` 指令基于一个数组来渲染一个列表。`v-for` 指令需要使用 `item in items` 形式的特殊语法，其中 items 是源数据数组，而 `item` 则是被迭代的数组元素的**别名**。

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div id="app">
            <li v-for="(item, index) in items">
                {{index}}-{{item.message}}
            </li>
        </div>
        <!--导入Vue.js-->
        <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
        <script>
            var vm = new Vue({
                el:"#app",
                data:{
                    items:[
                        {message:'鹏飞'},
                        {message:'java'},
                        {message:'你妹的'}
                    ]
                }
            });
        </script>
    </body>
</html>
```

> v-on

**监听事件**

我们可以使用 `v-on` 指令 (通常缩写为 `@` 符号) 来监听 DOM 事件，并在触发事件时执行一些 JavaScript。用法为 `v-on:click="methodName"` 或使用快捷方式 `@click="methodName"`

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div id="app">
            <button v-on:click="sayHi">click</button>
        </div>
        <!--导入Vue.js-->
        <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
        <script>
            var vm = new Vue({
                el:"#app",
                data:{
                    message:"鹏飞"
                },
                methods:{
                    //方法必须定义在vue的method里,事件监听
                    sayHi:function (event) {
                        alert(this.message)
                    }
                }
            });
        </script>
    </body>
</html>
```



### 3、Vue:表单双绑、组件

------

> 什么是双向数据绑定

​	Vue.js是一个MVVM框架，即数据双向绑定，即当数据发生变化的时下视图也就发生变化，当视图发生变化的时候，数据也会跟着同步变化。这也算是Vue.js的精髓之处了。

​	值得注意的是，我们所说的数据双向绑定，一定是对于U川控件来说的，非U川控件不会涉及到数据双向绑定。单向数据绑定是使用状态管理工具的前提。如果我们使用vux,那么数据流也是单项的，这时就会和双向数据绑定有冲突。

> 为什么要实现数据的双向绑定

​	在`Vue.js`中，如果使用`vuex`,实际上数据还是单向的，之所以说是数据双向绑定，这是用的UI控件来说，对于我们处理表单，Vu,js的双向数据绑定用起来就特别舒服了。即两者并不互斥，在全局性数据流使用单项，方便跟踪：局部性数据流使用双向，简单易操作。

> 在表单中使用双向数据绑定

​	你可以用`v-model`指令在表单`<input>`、`<textarea>`及`<select>`元素上创建双向数据绑定。它会根据控件类型自动选取正确的方法来更新元素。尽管有些神奇，但v-model本质上不过是语法糖。它负责监听用户的输入事件以更新数据，并对一些极端场景进行一些特殊处理。

​	**注意：v-model会忽略所有表单元素的value、checked、selected特性的初始值而总是将Vue实例的数据作为数据来源。你应该通过JavaScript在组件的data选项中声明初始值！**

> 单行文本

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div id="app">
            输入的文本：<input type="text" v-model="message">
            <p>{{message}}</p>
        </div>
            <!--导入Vue.js-->
            <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
            <script>
                var vm = new Vue({
                    el:"#app",
                    data:{
                        message:"",
                    }
                });
          </script>
       </body>
</html>
```

> 多行文本

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div id="app">
            <!--多行文本-->
            <span>message:</span>
            <p style="white-space: pre-line;">{{message}}</p>
            <textarea v-model="message"></textarea>
        </div>
        <!--导入Vue.js-->
        <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
        <script>
            var vm = new Vue({
                el:"#app",
                data:{
                    message:"",
                }
            });
        </script>
    </body>
</html>
```

> 单选框

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div id="app">
            <!-- 单选框-->
            <div>
                性别：
                <input type="radio" name="sex" value="男" v-model="peng">男
                <input type="radio" name="sex" value="女" v-model="peng">女
                <p><span>选中了：{{peng}}</span></p>
            </div>
        </div>
            <!--导入Vue.js-->
            <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
            <script>
                var vm = new Vue({
                    el:"#app",
                    data:{
                        peng:''
                    }
                });
   </script>
  </body>
</html>
```

> 复选框

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div id="app">
            <!--复选框-->
            <div>
                <input type="checkbox" value="jack" v-model="checkedName">jack
                <input type="checkbox" value="tom" v-model="checkedName">tom
                <input type="checkbox" value="amy" v-model="checkedName">amy<br/>
                <span>Check name:{{checkedName}}</span>
            </div>
          
        </div>
        <!--导入Vue.js-->
        <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
        <script>
            var vm = new Vue({
                el:"#app",
                data:{
                    checkedName:[]
                }
            });
        </script>
    </body>
</html>
```

> 下拉框

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div id="app">
            <!-- 下拉框-->
            <div>
                <p>下拉框</p>
                <select v-model="xiala">
                    <option value="" disabled>--请选择--</option>
                    <option>A</option>
                    <option>B</option>
                    <option>C</option>
                </select>
                <span>value:{{xiala}}</span>
            </div>
        </div>
        <!--导入Vue.js-->
        <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
        <script>
            var vm = new Vue({
                el:"#app",
                data:{
                    xiala:''
                }
            });
        </script>
    </body>
</html>
```

​	**注意**：如果`v-model`表达式的初始值未能匹配任何选项，<select>元素将被渲染为“未选中”状态。在iOS中，这会使用户无法选择第一个选项。因为这样的情况下，iOS不会触发change事件。因此，更推荐像上面这样提供一个值为空的禁用选项。

> 什么是组件

​	组件是可复用的`Vue`实例，说白了就是一组可以重复使用的模板，跟JSTL的自定义标签、Thymeleaf的`th:fragment`等框架有着异曲同工之妙。通常一个应用会以一棵嵌套的组件树的形式来组织：

![components](https://gitee.com/linda12138/picgo/raw/master/image/components.png)

> 第一个组件

​	注意：在实际开发中，我们并不会用以下方式开发组件，而是采用vue-cli创建.vue模板文件的方式开发，以下方法只是为了让大家理解什么是组件。

​	**使用Vue.component()方法注册组件，格式如下：**

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div id="app">
            <!--组件：传递给组件中的值：props-->
            <peng v-for="item in items" v-bind:bang="item"></peng>
        </div>
        <!--导入Vue.js-->
        <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
        <script>
            //定义一个vue组件component
            Vue.component("peng",{
                props:['bang'],
                template:'<li>{{bang}}</li>'
            });
            var vm = new Vue({
                el:"#app",
                data:{
                    items:["java","linux","前端"]
                }
            });
        </script>
    </body>
</html>
```

说明：

- `v-for="item in items”`:遍历`Vue`实例中定义的名为`items`的数组，并创建同等数量的组件
- `v-bind:item="item"`:将遍历的item项绑定到组件中props定义的名为item性上；=号左边的item为props定义的属性名，右边的为`item in items`中遍历的item项的值

### 4、Axios异步通信

------

> 什么是Axios

​	Axios是一个开源的可以用在浏览器端和`Nodejs`的异步通信框架，她的主要作用就是实现Ajax异步通信，其功能特点如下：

- 从浏览器中创建XMLHttpRequests
- 从node.js创建http请求
- 支持Promise API[JS中链式编程]
- 拦截请求和响应
- 转换请求数据和响应数据
- 取消请求
- 自动转换JSON数据
- 客户端支持防御XSRF(跨站请求伪造)
- GitHub:https://github.com/axios/axios
- 中文文档：http://www.axios-js.com/

> 为什么要使用Axios

​	由于`Vue.js`是一个视图层框架并且作者（尤雨溪）严格准守SoC(关注度分离原则)，所以`Vue.js`并不包含AJAX的通信功能，为了解决通信问题，作者单独开发了一个名为`vue-resource`的插件，不过在进入2.0版本以后停止了对该插件的维护并推荐了Axios框架。少用jQuery,因为它操作Dom太频繁！

> 第一个Axios应用程序

​	咱们开发的接口大部分都是采用JSON格式，可以先在项目里模拟一段JSON数据，数据内容如下：创建一个名为data.json的文件并填入上面的内容，放在项目的根目录下

```json
{
    "name":"鹏飞",
    "url": "http://baidu.com",
    "page": "1",
    "isNonProfit":"true",
    "address": {
        "street": "含光门",
        "city":"陕西西安",
        "country": "中国"
    },
    "links": [
        {
            "name": "B站",
            "url": "https://www.bilibili.com/"
        },
        {
            "name": "4399",
            "url": "https://www.4399.com/"
        },
        {
            "name": "百度",
            "url": "https://www.baidu.com/"
        }
    ]
}
```

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <style>
            [v-clock]{
                display: none;
            }
        </style>
    </head>
    <body>

        <div id="vue" v-clock>
            <div>{{info.name}}</div>

            <a v-bind:href="info.url">点击</a>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/vue@2.5.21/dist/vue.js"></script>
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
        <script type="text/javascript">
            var vm = new Vue({
                el:'#vue',
                //data：属性：vm
                data(){
                    return{
                        //请求的返回参数合适，必须和json字符串一样
                        info:{
                            name:null,
                            address:{
                                street:null,
                                city:null,
                                country:null
                            },
                            url:null
                        }
                    }
                },
                mounted(){//钩子函数 链式编程
                    axios.get('../chapter-1/data.json').then(response=>(this.info=response.data));
                }
            })
        </script>
    </body>
</html>
```



说明：

1.在这里使用了v-bind将a:href的属性值与Vue实例中的数据进行绑定

2.使用axios框架的get方法请求AjAX并自动将数据封装进了Vue实例的数据对象中

3.我们在data中的数据结构必须要和`Ajax`响应回来的数据格式匹配！



> Vue的生命周期

官方文档：https://cn.vuejs..org/v2/guide/instance.html#生命周期图示

​	Vue实例有一个完整的生命周期，也就是从开始创建、初始化数据、编译模板、挂载DOM、渲染一→更新→渲染、卸载等一系列过程，我们称这是Vue的生命周期。通俗说就是Vue实例从创建到销毁的过程，就是生命周期。

​	在Vue的整个生命周期中，它提供了一系列的事件，可以让我们在事件触发时注册S方法，可以让我们用自己注册的JS方法控制整个大局，在这些事件响应方法中的this直接指向的是Vue的实例。

![1649496185400](https://gitee.com/linda12138/picgo/raw/master/image/1649496185400.png)



### 5、Vue：计算属性、内容分发、自定义事件

------

> 什么是计算属性

​	计算属性的重点突出在`属性`两个字上（属性是名词），首先它是个`属性`其次这个属性有计算的能力（计算是动词），这里的`计算`就是个函数：简单点说，它就是一个能够将计算结果缓存起来的属性（将行为转化成了静态的属性），仅此而已：可以想象为缓存！

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div id="app">
            <p>currentTime1:{{currentTime1()}}</p>
            <p>currentTime2:{{currentTime2}}</p>
        </div>
        <!--导入Vue.js-->
        <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
        <script>
            var vm = new Vue({
                el:"#app",
                data:{
                    message:"hello"
                },
                methods: {
                    currentTime1:function () {
                        return Date.now();//返回一个时间戳
                    }
                },
                computed:{//计算属性：methods computed 方法名不能重，重名之后他只会调用methods的方法
                    currentTime2:function () {
                        this.message;
                        return Date.now();//返回一个时间戳
                    }
                }

            });
        </script>
    </body>
</html>
```

**注意：methods computed 方法名不能重，重名之后他只会调用methods的方法**

**说明：**

- methods:定义方法，调用方法使用currentTime1(),需要带括号
- computed:定义计算属性，调用属性使用currentTime2,不需要带括号：this.message是为了能够让currentTime2观察到数据变化而变化
- 如何在方法中的值发生了变化，则缓存就会刷新！可以在控制台使用`vm.message="peng”`,改变下数据的值，再次测试观察效果！

**结论：**

​	调用方法时，每次都需要进行计算，既然有计算过程则必定产生系统开销，那如果这个结果是不经常变化的呢？此时就可以考虑将这个结果缓存起来，采用计算属性可以很方便的做到这一点，**计算属性的主要特性就是为了将不经常变化的计算结果进行缓存，以节约我们的系统开销**



> 内容分发

​	在`Vue.js`中我们使用`<slot>`元素作为承载分发内容的出口，作者称其为**插槽**，可以应用在组合组件的场景中；

> 测试

​	比如准备制作一个待办事项组件(todo),该组件由代办标题(todo-title)和待办内容(todo-items)组成，但这三个组件又是相互独立的，该如何操作呢？

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div id="app">
            <todo>
                <todo-title slot="todo-title"v-bind:title="title"></todo-title>
                <todo-items slot="todo-items" v-for="item in todoItems" v-bind:item="item"></todo-items>
            </todo>
        </div>
        <!--导入Vue.js-->
        <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
        <script>
            //slot:插槽
            Vue.component("todo",{
                template: '<div>\
<slot name="todo-title"></slot>\
<ul>\
<slot name="todo-items"></slot>\
            </ul>\
            </div>'
            });
            Vue.component("todo-title",{
                props: ['title'],
                template: '<div>{{title}}</div>'
            });
            Vue.component("todo-items",{
                props:['item'],
                template: '<li>{{item}}</li>'
            });
            var vm = new Vue({
                el:"#app",
                data:{
                    title:"鹏飞",
                    todoItems:['鹏飞','前端','linux']
                }
            });
        </script>
    </body>
</html>
```



> 自定义事件

​	通过以上代码不难发现，数据项在Vue的实例中，但删除操作要在组件中完成，那么组件如何才能删除Vue实例中的数据呢？此时就涉及到参数传递与事件分发了，Vue为我们提供了自定义事件的功能很好的帮助我们解决了这个问题；使用this.$emit('自定义事件名'，参数)，操作过程如下：

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div id="app">
            <todo>
                <todo-title slot="todo-title"v-bind:title="title"></todo-title>
                <todo-items slot="todo-items" v-for="(item,index) in todoItems" v-bind:item="item" v-bind:index="index" v-on:remove="removeItems(index)"></todo-items>
            </todo>
        </div>
        <!--导入Vue.js-->
        <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
        <script>
            //slot:插槽
            Vue.component("todo",{
                template: '<div>\
<slot name="todo-title"></slot>\
<ul>\
<slot name="todo-items"></slot>\
            </ul>\
            </div>'
            });
            Vue.component("todo-title",{
                props: ['title'],
                template: '<div>{{title}}</div>'
            });
            Vue.component("todo-items",{
                props:['item','index'],
                //只能绑定当前组件的方法
                template: '<li>{{index}}--{{item}} <button v-on:click="remove">删除</button></li>',
                methods:{
                    remove:function (index){
                        //自定义事件分发 this.$emit
                        this.$emit('remove',index);
                    }
                }
            });
            var vm = new Vue({
                el:"#app",
                data:{
                    title:"鹏飞",
                    todoItems:['鹏飞','前端','linux']
                },
                methods: {
                    removeItems:function (index) {
                        console.log("删除了"+this.todoItems[index]+"ok");
                        this.todoItems.splice(index,1);//一次删除一个元素
                    }
                }
            });
        </script>
    </body>
</html>
```



### 6、Vue入门小结

------

​	核心：数据驱动，组件化

优点：借鉴了AngulaJS的模块化开发和React的虚拟Dom，模拟Dom就是把Dom操作放到内存中执行；

​	常用的属性：

- v-if

- v-else-if

- v-else

- v-for

- v-on 绑定事件，简写`@`

- v-model数据双向绑定

- v-bind 给组件绑定参数，简写 `:`

  组件化：

- 组合组件slot插槽

- 组件内部绑定事件需要使用到`this.$emit("事件名",参数)`;

- 计算属性的特色，缓存计算数据

​	遵循SoC关注度分离原则，Vue是纯粹的视图框架，并不包含，比如Ajx之类的通信功能，为了解决通信问题，我们需要使用Axios框架做异步通信；



### 7、Vue:第一个vue-cli项目

------

> 什么是vue-cli

​	vue-cli官方提供的一个脚手架，用于快速生成一个vue的项目模板；

​	预先定义好的目录结构及基础代码，就好比咱们在创建Maven项目时可以选择创建一个骨架项目，这个骨架项目就是脚手架，我们的开发更加的快速；

​	**主要的功能：**

- 统一的目录结构
- 本地调试
- 热部署
- 单元测试
- 集成打包上线

> 需要的环境

node.js:https://nodejs.org/zh-cn/

**无脑下一步即可**

**确认nodejs安装成功：**

- cmd下输入`node -v`，查看是否能够正确打印版本号即可
- cmd下输入`npm -v`，查看是否能够正确打印版本号即可



这个npm,就是一个软件包管理工具，就和linux下的apt软件安装差不多！

**安装Node.js淘宝镜像加速器(cnpm)**

这样子的话，下载会快很多~

```bash
# -g 就是全局安装
npm install cnpm -g

# 或使用如下语句解决 npm速度慢的问题
npm install --registry=https://registry.npm.taobao.org
```



**安装vue-cli**

```bash
cnpm install vue-cli -g

#测试是否安装成功
#查看可以基于哪些模板创建vue应用程序，通常我们选中webpack
vue list
```

![image-20220411163822404](https://gitee.com/linda12138/picgo/raw/master/image/image-20220411163822404.png)



> 第一个vue-cli应用程序

1. 创建一个Vu项目，我们随便建立一个空的文件夹在电脑上，我这里在D盘下新建一个目录

2. 创建一个基于webpack模板的vue应用程序

   ```bash
   #这里的myvue是项目名称，可以根据自己的需求起名
   vue init webpack myvue
   ```

   一路选择no即可；

   **说明：**

   - Project name:项目名称，默认回车即可
   - Project description:项目描述，默认回车即可
   - Author:项目作者，默认回车即可
   - Install vue-router:是否安装vue-router,选择n不安装（后期需要再手动添加）
   - Use ESLint to lint your code:是否使用ESLint做代码检查，选择n不安装（后期需要再手动添加)
   - Set up unit tests:单元测试相关，选择n不安装（后期需要再手动添加）
   - Setup e2 e tests with Nightwatch:单元测试相关，选择n不安装（后期需要再手动添加）
   - Should we run npm install for you after the project has been created:创建完成后直接初始化，选择n,我们手动执行；运行结果！

![image-20220411164812785](https://gitee.com/linda12138/picgo/raw/master/image/image-20220411164812785.png)



> 初始化并运行

```
cd myvue
npm install
npm run dev
```

![image-20220411165809663](https://gitee.com/linda12138/picgo/raw/master/image/image-20220411165809663.png)

**运行成功**

![image-20220411165841353](C:\Users\鹏飞12138\AppData\Roaming\Typora\typora-user-images\image-20220411165841353.png)



### 8、什么是Webpack

------

​	本质上，webpack是一个现代JavaScript应用程序的静态模块打包器(module bundler)。当webpack处理应用程序时，它会递归地构建一个依赖关系图(dependency graph),其中包含应用程序需要的每个模块，然后将所有这些模块打包成一个或多个bundle

​	Webpack是当下最热门的前端资源模块化管理和打包工具，它可以将许多松散耦合的模块按照依赖和规则打包成符合生产环境部署的前端资源。还可以将按需加载的模块进行代码分离，等到实际需要时再异步加载。通过loader转换，任何形式的资源都可以当做模块，比如CommonsJS、AMD、ES6、CSS、JSON、CoffeeScript、LESS等；

​	伴随着移动互联网的大潮，当今越来越多的网站已经从网页模式进化到了WebApp模式。它们运行在现代浏览器里，使用HTML5、CSS3、ES6等新的技术来开发丰富的功能，网页已经不仅仅是完成浏览器的基本需求；WebApp通常是一个SPA(单页面应用)，每一个视图通过异步的方式加载，这导致页面初始化和使用过程中会加载越来越多的JS代码，这给前端的开发流程和资源组织带来了巨大挑战。

​	前端开发和其他开发工作的主要区别，首先是前端基于多语言、多层次的编码和组织工作，其次前端产品的交付是基于浏览器的，这些资源是通过增量加载的方式运行到浏览器端，如何在开发环境组织好这些碎片化的代码和资源，并且保证他们在浏览器端快速、优雅的加载和更新，就需要一个模块化系统，这个理想中的模块化系统是前端工程师多年来一直探索的难题。

#### 8.1、模块化的演进

**Script标签**

```html
<script src="module1.js"></script>
<script src="module2.js"></script>
<script src="module3.js"></script>
```

​	这是最原始的JavaScript文件加载方式，如果把每一个文件看做是一个模块，那么他们的接口通常是暴露在全局作用域下，也就是定义在window对象中，不同模块的调用都是一个作用域。

​	这种原始的加载方式暴露了一些显而易见的弊端：

- 全局作用域下容易造成变量冲突
- 文件只能按照<script>的书写顺序进行加载
- 开发人员必须主管解决模块和代码库的依赖关系
- 在大型项目中各种资源难以管理，长期积累的问题导致代码库混乱不堪

**CommonsJS**

​	服务器端的NodeJS遵循CommonsJS规范，该规范核心思想是允许模块通过require方法来同步加载所需依赖的其它模块，然后通过exports或module.exports来导出需要暴露的接口。

```javascript
require("module");
require("../module");
export.doStuff = function(){}
module.exports = somValue;
```

**优点：**

- 服务器端模块便于重用
- NPM中已经有超过45万个可以使用的模块包
- 简单易用

**缺点：**

- 同步的模块加载方式不适合在浏览器环境中，同步意味着阻塞加载，浏览器资源是异步加载的
- 不能非阻塞的并行加载多个模块

**实现：**

- 服务端的NodeJS

- Browserify,浏览器端的CommonsJS实现，可以使用NPM的模块，但是编译打包后的文件体积较大

- modules-webmake,类似Browserify,但不如Browserify灵活

- wreq,Browserify的前身

  

**AMD**

​	Asynchronous Module Definition规范其实主要一个主要接口define(id?,dependencies?,factory):它要在声明模块的时候指定所有的依赖dependencies,并且还要当做形参传到factory中，对于依赖的模块提前执行。

```javascript
define("modeule",["dep1","dep2"],function(d1,d2){
    return someExportedValue;
});
require(["module","../file.js"],function(module,file){});
```

**优点：**

- 适合在浏览器环境中异步加载模块
- 可以并行加载多个模块

**缺点：**

- 提高了开发成本，代码的阅读和书写比较困难，模块定义方式的语义不畅
- 不符合通用的模块化思维方式，是一种妥协的实现

**实现：**

- RequireJS
- curl



**CMD**

​	Commons Module Definition规范和AMD很相似，尽量保持简单，并与CommonsJS和NodeJS的Modules规范保持了很大的兼容性。

```javascript
define(function(require,exports,module){
    var $ = require("jquery");
    var Spinning = require("../spinning");
    exports.doSomething=...;
    module.exports=...;
});
```

**优点：**

- 依赖就近，延迟执行
- 可以很容易在NodeJS中运行

**缺点：**

- 依赖SPM打包，模块的加载逻辑偏重

**实现：**

- Sea.js
- coolie



**ES6模块**

​	EcmaScript6标准增加了JavaScript语言层面的模块体系定义。ES6模块的设计思想，是尽量静态化，使编译时就能确定模块的依赖关系，以及输入和输出的变量。CommonsJS和AMD模块，都只能在运行时确定这些东西。

```javascript
import "jquery";
erport function doStuff(){}
module "localModule" {}
```

**优点：**

- 容易进行静态分析
- 面向未来的EcmaScript标准

**缺点：**

- 原生浏览器端还没有实现该标准
- 全新的命令，新版的NodeJS才支持

**实现：**

- Bable



**大家期望的模块系统**

​	可以兼容多种模块风格，尽量可以利用已有的代码，不仅仅只是JavaScript模块化，还有CSS、图片、字体等资源也需要模块化。

#### 8.2、安装Webpack

​	WebPack是一款模块加载器兼打包工具，它能把各种资源，如JS、JSX、ES6、SASS、LESS、图片等都作为模块来处理和使用。

​	**安装：**

```bash
npm install webpack -g
npm install webpack-cli -g
```

​	测试安装成功：

- `webpack -v`
- `webpack-cli -v`

![image-20220412091051334](https://gitee.com/linda12138/picgo/raw/master/image/image-20220412091051334.png)



> 配置

创建`webpack.config.js`配置文件

- entry:入口文件，指定WebPack用哪个文件作为项目的入口
- output:输出，指定WebPack把处理完成的文件放置到指定路径
- module：模块，用于处理各种类型的文件
- plugins:插件，如：热更新、代码重用等
- resolve:设置路径指向
- watch:监听，用于设置文件改动后直接打包

```javascript
module.exports = {
    entry:"",
    output:{
        path:"",
        filename:""
    },
    module:{
        loaders:[
            {test:/\.js$/,loader:""}
        ]
    },
    plugins:{},
    resolve:{},
    watch:true
}
```

直接运行`webpack`命令打包

#### 8.3、使用webpack

1. 创建项目

2. 创建一个名为modules的目录，用于防止JS模块等资源文件

3. 在modules下创建模块文件，如hello.js，用于编写JS模块相关代码

   ```javascript
   //暴露一个方法
   exports.sayHi = function () {
       document.write("<h1>鹏飞12138</h1>>")
   };
   ```

   

4. 在modules下创建一个名为main,js的入口文件，用于打包时设置entry属性

   ```javascript
   var hello = require("./hello.js")
   hello.sayHi()
   ```

   

5. 在项目目录下创建webpack.config.js配置文件，使用webpack命令打包

   ```javascript
   module.exports = {
       entry:'./modules/main1.js',
       output:{
           filename:"./js/bundle.js"
       }
   };
   ```

6. 在项目目录下创建HTML页面，如index.html,导入WebPack打包后的JS文件

   ```html
   <!DOCTYPE html>
   <html lang="en">
       <head>
           <meta charset="UTF-8">
           <title>Title</title>
       </head>
       <body>
           <!--前端的模块化开发-->
           <script src="dist/js/bundle.js"></script>
       </body>
   </html>
   ```

   

7. 在idea控制台直接执行webpack;

```bash
#参数 --watch 用于监听变化
webpack --watch
```



### 9、Vue:vue-router路由

------

> 说明

​	学习的时候，尽量的打开官方的文档

​	Vue Router是Vue.js官方的路由管理器。它和Vue.js的核心深度集成，让构建单页面应用变得易如反掌。包含的功能有：

- 嵌套的路由/视图表
- 模块化的、基于组件的路由配置
- 路由参数、查询、通配符
- 基于Vue.js过渡系统的视图过渡效果
- 细粒度的导航控制
- 带有自动激活的CSS class的链接
- HTML5历史模式或hash模式，在IE9中自动降级
- 自定义的滚动条行为

> 安装

​	基于第一个`vue-cli`进行测试学习；先查看node modules中是否存在vue-router

​	vue-router是一个插件包，所以我们还是需要用npm/cnpm来进行安装的。打开命令行工具，进入你的项目目录，输入下面命令。

```bash
npm install vue-router --save-dev

# 出现找不到问题 将版本运行
npm i vue-router@3.1.3
```

​	如果在一个模块化工程中使用它，必须要通过Vue.use()明确地安装路由功能：

```javascript
import Vue from "vue";
import VueRouter from "vue-router";

//安装路由
Vue.use(VueRouter);
```

> 测试

1. 先删除没有用的东西

2. `components`目录下存放我们自己编写的组件

3. 定义一个`Content.vue`的组件

   ```vue
   <template>
       <h1>内容页</h1>
   </template>
   
   <script>
       export default {
   name: "Content"
   }
       </script>
   
   <style scoped>
   
       </style>
   ```

   

4. 安装路由，在src目录下，新建一个文件夹：`router`，专门存放路由

   ```javascript
   import Vue from "vue";
   import VueRouter from "vue-router";
   import Content from "../components/Content";
   import Main from "../components/Main";
   
   //安装路由
   Vue.use(VueRouter);
   
   //配置导出路由
   export default new VueRouter({
       routes:[
           {
               //路由路径
               path:'/content',
               name:'content',
               //跳转的组件
               component:Content
           },
           {
               //路由路径
               path:'/main',
               name:'content',
               //跳转的组件
               component:Main
           }
       ]
   });
   ```

   

5. 在`main.js`中配置路由

   ```javascript
   import Vue from 'vue'
   import App from './App'
   import router from './router' //自动扫描里面的路由配置
   
   Vue.config.productionTip = false
   
   /* eslint-disable no-new */
   new Vue({
       el: '#app',
       //配置路由
       router,
       components: { App },
       template: '<App/>'
   })
   ```

   

6. 在`App.vue`中使用路由

   ```vue
   <template>
       <div id="app">
           <router-link to="/main">首页</router-link>
   <router-link to="/content">内容页</router-link>
   <router-view></router-view>
   </div>
   </template>
   
   <script>
   
       export default {
   name: 'App',
   }
       </script>
   
   <style>
           #app {
               font-family: 'Avenir', Helvetica, Arial, sans-serif;
               -webkit-font-smoothing: antialiased;
               -moz-osx-font-smoothing: grayscale;
               text-align: center;
               color: #2c3e50;
               margin-top: 60px;
           }
   </style>
   
   ```

   

7. 运行测试



### 10、Vue:实战快速上手

------

​	我们采用实战教学模式并结合`ElementUI`组件库，将所需知识点应用到实际中，以最快速度带领大家掌握Vue的使用；

> 创建工程

​	注意：命令行都要使用管理员模式运行

1. 创建一个名为hello-vue的工程`vue init webpack hel1o-vue`

2. 安装依赖，我们需要安装vue-router、element-ui、sass-loader和node-sass四个插件

   ```bash
   #进入工程目录
   cd hello-vue
   #安装vue-router
   npm install vue-router --save-dev
   #安装element-ui
   npm i element-ui -S
   #安装依赖
   npm install
   #安装SASS加载器
   cnpm install sass-loader node-sass --save-dev
   #启动测试
   npm run dev
   ```

   

3. npm命令解释：

   - `npm install moduleName`:安装模块到项目目录下
   - `npm install -g moduleName`:-g的意思是将模块安装到全局，具体安装到磁盘哪个位置，要看npm config prefix的位置
   - `npm install -save moduleName`:-Save的意思是将模块安装到项目目录下，并在package文件的dependencies节点写入依赖，-S为该命令的缩写
   - `npm install-save-dev moduleName`:-save-dev的意思是将模块安装到项目目录下，并在package文件的devDependencies节点写入依赖，-D为该命令的缩写

4. 
