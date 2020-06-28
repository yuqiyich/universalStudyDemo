package com.example.firstdemo.test.testclassloader;

import com.ruqi.travel.model.extra.BaseSelectBean;

import java.lang.reflect.Method;

/**
 *Java中的ClassLoader有三种：Bootstrap ClassLoader 、Extension ClassLoader、App ClassLoader。
 *
 * 1. Bootstrap ClassLoader
 * 由C++写的,由JVM启动.
 *
 * 启动类加载器,负责加载java基础类，对应的文件是%JRE_HOME/lib/ 目录下的rt.jar、resources.jar、charsets.jar和class等
 *
 * 2.Extension ClassLoader
 * Java类,继承自URLClassLoader 扩展类加载器,
 *
 * 对应的文件是 %JRE_HOME/lib/ext 目录下的jar和class等
 *
 * 3.App ClassLoader
 * Java类,继承自URLClassLoader 系统类加载器,
 *
 * 对应的文件是应用程序classpath目录下的所有jar和class等
 *
 */
public class TestClassLoader {

    public static void main(String[] args){

        //打印系统的加载器
        ClassLoader ClassLoader1 = TestClassLoader.class.getClassLoader();
        ClassLoader ClassLoader2 = ClassLoader1.getParent();
        ClassLoader ClassLoader3 = ClassLoader2.getParent();

        System.out.println(ClassLoader1);
        System.out.println(ClassLoader2);
        System.out.println(ClassLoader3);
        //自定义的类加载器
        //这个类class的路径
        String classPath = "./app/BaseSelectBean.class";

        MyClassLoader myClassLoader = new MyClassLoader(classPath);
        //类的全称
        String packageNamePath = "com.ruqi.travel.model.extra.BaseSelectBean";
        System.out.println(System.getProperty("user.dir"));
        //加载Log这个class文件
        Class<?> baseSelectClass = null;
        try {
            baseSelectClass = myClassLoader.loadClass(packageNamePath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        BaseSelectBean beanLocal= new BaseSelectBean();
        System.out.println("BaseSelectBean经过 myClassLoader中的类加载器是:" + baseSelectClass.getClassLoader());
        System.out.println("BaseSelectBean该线程 中的类加载器是:" + BaseSelectBean.class.getClassLoader());

        try {
            Method method = baseSelectClass.getMethod("getName", String[].class);
            Object object = baseSelectClass.newInstance();
            BaseSelectBean fromOtherBean=(BaseSelectBean)object;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }



}
