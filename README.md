# GeneralBusiness
通用业务

## gradle使用：

一、Project下的build.gradle文件下添加

allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
}

二、Module下的build.gradle文件下添加

1.0.3以前

dependencies {
          implementation 'com.github.Lvluffy:GeneralBusiness:1.0.3'
}

1.0.4以后

dependencies {
	     implementation 'com.github.Lvluffy.GeneralBusiness:businessconversionlib:1.0.4'
         implementation 'com.github.Lvluffy.GeneralBusiness:countdownlib:1.0.4'
         implementation 'com.github.Lvluffy.GeneralBusiness:masklib:1.0.4'
         implementation 'com.github.Lvluffy.GeneralBusiness:pollerlib:1.0.4'
}

## 核心业务

1，倒计时

2，轮询器

3，业务转换

4，蒙层
