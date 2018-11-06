## 前言
黄油刀(BufferKnife)使用注解声明控件，不再反复 findViewById，使得代码简洁高效。
下面我们手撸一套ioc框架实现黄油刀的效果。

[Github 地址](https://github.com/maokai1229/ViewJet)

### ViewJet 2.0 (View飞行器)特性

![1206567.png](https://upload-images.jianshu.io/upload_images/5639648-77d5d0831c1d28b9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/140)

* BindView 注解声明控件对象
* 自动绑定监听器 
* Gradle 集成

### 实现原理
* 注解+反射

1. 注入属性
2. 注入方法

### 使用教程
1. 添加 Gradle 依赖

```
repositories {
    maven { url 'https://dl.bintray.com/ethanmao/ViewJet' 
    }
}

dependencies {
     implementation 'com.ethanmao.open:viewjet:2.0.0'
}

```

2. 在 Activity 中使用

```
 ViewJet.bind(this);
``` 

3. 使用 BindView 注解
* 声明变量
```
   @BindView(R.id.tutu_login)
    private Button toLogin;
```
* 响应点击事件

```
   @OnClick(R.id.tutu_login)
    public void onClick(View view){
       // Todo Something
    }
```






