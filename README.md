[![](https://jitpack.io/v/liqinew/widgetutils.svg)](https://jitpack.io/#liqinew/widgetutils)
[![](https://img.shields.io/badge/%E4%BD%9C%E8%80%85-%E6%9D%8E%E5%A5%87-orange.svg)](https://github.com/LiqiNew)
# WidgetUtils
支持异步弹出软键盘，实时弹出软键盘，关闭软键盘，软键盘弹出不遮挡指定区域
### 效果预览
<image src="./image/demo.gif" width="400px" height="700px"/>

# 如何使用

#### Gradle远程依赖 ####
**1：在项目根目录build.gradley**	<br>

```gradle
allprojects {
　　repositories {
  　　//依赖仓库
　　　maven { url 'https://jitpack.io' }
　　}
}
```

**2：依赖WidgetUtils**<br>

```gradle
compile 'com.github.liqinew:widgetutils:V.1.0.1'
```
### 使用方式 ###
**AndroidMainFest.xml中对使用Activity属性设置android:windowSoftInputMode="adjustResize"。可以多个设置。但是必须要有一个设置为adjustResize**
#### 建议XML视图布局样式

* 滑动效果XML布局建议样式
```xml
 <!--mainLayout对应的控件-->
<ScrollView>

     <!--此处布局随意定义-->
     <layout>

         <!--此处布局随意定义-->
         <...>
         </...>

        <!--bottomLayout对应的控件-->
        <底部展示到那个布局>
        </底部展示到那个布局>

    </layout>

</ScrollView>
```
* 5.0以上缩小效果，5.0以下滑动效果XML布局建议样式
```xml
 <!--mainLayout对应的控件-->
<ScrollView>

    <!--此处布局随意定义-->
    <layout>

        <!--logoLayout对应的控件.建议尺寸大概100dp*100dp左右-->
        <ImageView
        android:layout_width="100dp"
        android:layout_height="100dp">
        </ImageView>

        <!--此处布局随意定义-->
        <...>
        </...>

        <!-- bottomLayout对应的控件-->
         <底部展示到那个布局>
        </底部展示到那个布局>

    </layout>

</ScrollView>
```
#### 代码调用

##### 软键盘弹出不遮挡指定区域
```java
/**
* 获取软键盘弹出不遮挡指定区域的控件工具对象：滑动效果对象。然后调用start()开启
*
*/
WidgetKeepOutNoUtils.openScrollViewKeepOutNoUtils().start(mainLayout, bottomLayout);

/**
* 获取软键盘弹出不遮挡指定区域的控件工具对象：5.0以上缩小效果，5.0以下滑动效果对象。然后调用start()开启
*
* @param activity 依赖界面
*/
WidgetKeepOutNoUtils.openReduceSlidingKeepOutNoUtils(Activity activity)
//设置Y轴缩放值。系统版本>=21
.setY(120)
//设置X轴缩放值。系统版本>=21
.setX(120)
//开启效果
.start(mainLayout, logoLayout, bottomLayout);
```
##### 软键盘弹出和关闭(静态调用)
```java
/**
* 异步延迟展开指定的editText对应的键盘，并且获取输入焦点（dialog里面展开键盘需要异步展开）
*
* @param editText 指定的editText
* @param time     延迟多久展示键盘
*/
ViewOperatetUtils.asynUnfoldEditTextIn(final EditText editText, long time) ;

/**
* 展开指定的editText对应的软键盘，并且获取输入焦点
*
* @param editText 指定的editText
*/
ViewOperatetUtils.unfoldEditTextIn(EditText editText);

/**
* 关闭指定的EditText对应的输入软键盘
*
* @param editText 指定的editText
*/
ViewOperatetUtils.closeEditKeyboard(EditText editText);

/**
* 异步延迟关闭指定EditText对应的输入软键盘
*
* @param editText 指定的editText
* @param time     延迟多久关闭键盘
*/
ViewOperatetUtils.asynCloseEditKeyboard(final EditText editText, long time);

/**
* 关闭当前界面弹出的软键盘。不需要指定editText
*
* @param activity
*/
ViewOperatetUtils.allCloseEditKeyboard(Activity activity);

/**
* 异步延迟关闭当前界面弹出的软键盘。不需要指定editText
*
* @param activity
* @param time     延迟多久展示键盘
*/
ViewOperatetUtils.allAsynCloseEditKeyboard(final Activity activity, long time);
```
