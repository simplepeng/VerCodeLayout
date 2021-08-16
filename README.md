# VerCodeLayout

可能是支持属性最多的类似验证码输入控件了。

![](https://raw.githubusercontent.com/simplepeng/VerCodeLayout/master/statics/vercode.gif)

## 引入依赖

```groovy
implementation 'com.simple:VerCodeLayout:1.0.0'
```

## 基本使用

使用`VerCodeEditText`控件

```xml
<com.simple.vclayout.VerCodeEditText
    android:id="@+id/vcEt1"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:vcCount="6"
    app:vcFocusedBackground="@drawable/sp_focused_one"
    app:vcMargin="5dp"
    app:vcNormalBackground="@drawable/sp_normal_one"
    app:vcTextSize="20sp" />
```

### 可用的属性

| 属性名               | 作用                                          |
| -------------------- | --------------------------------------------- |
| vcCount              | EditText的个数                                |
| vcMaxLength          | EditText最大输入长度                          |
| vcNormalBackground   | 未选中的背景                                  |
| vcFocusedBackground  | 选中的背景                                    |
| vcWidth              | EditText的宽                                  |
| vcHeight             | EditText的高                                  |
| vcMinWidth           | EditText的最小宽度                            |
| vcMinHeight          | EditText的最小高度                            |
| vcMargin             | EditText的外边距，当然还有MarginLeft等小属性  |
| vcPadding            | EditText的内边距，当然还有PaddingLeft等小属性 |
| vcTextSize           | EditText的文本大小                            |
| vcTextColor          | EditText的文本颜色                            |
| vcTextCursorDrawable | EditText的游标Drawable                        |
| vcGravity            | EditText的Gravity                             |
| vcInputType          | EditText的输入类型                            |

## 高级使用

使用`VerCodeLayout`控件，VerCodeEditText继承于VerCodeLayout。VerCodeLayout自带了当一个EditText输入完成选中下一个或删除完成选中上一个的功能，但是EditText必须设置`maxLength`。

```xml
<!--车牌-->
<com.simple.vclayout.VerCodeLayout
    android:layout_width="wrap_content"
    android:layout_height="wrap_content">

    <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="5dp"
        android:background="@drawable/sr_et_bg_accent"
        android:gravity="center"
        android:inputType="text"
        android:maxLength="1" />

    <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="5dp"
        android:background="@drawable/sr_et_bg_accent"
        android:gravity="center"
        android:inputType="text"
        android:maxLength="1" />

    <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="5dp"
        android:background="@drawable/sr_et_bg_accent"
        android:gravity="center"
        android:inputType="number"
        android:maxLength="1" />

    <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="5dp"
        android:background="@drawable/sr_et_bg_accent"
        android:gravity="center"
        android:inputType="number"
        android:maxLength="1" />

    <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="5dp"
        android:background="@drawable/sr_et_bg_accent"
        android:gravity="center"
        android:inputType="number"
        android:maxLength="1" />
</com.simple.vclayout.VerCodeLayout>
```

## 可用的方法

| 方法名                | 方法作用           |
| --------------------- | ------------------ |
| setOnCompleteListener | 监听输入完成       |
| clear                 | 清除所有的输入     |
| getEditTexts          | 获取所有的EditText |

## 混淆

```shell
-keep class com.simple.vclayout.** {*;}
```

## 版本更新

* v1.0.1：迁移到`jitpack`

* v1.0.0： 首次上传