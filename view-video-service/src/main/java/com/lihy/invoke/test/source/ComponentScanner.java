package com.lihy.invoke.test.source;

import com.lihy.invoke.test.source.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 扫描类注解
 * 扫描带注解的类
 * @author lihy
 * @date 2018/09/29
 */
public class ComponentScanner {
    public static HashMap<String, String> getComponentClassName(String packageName) {
        List<String> classes = getClassName(packageName);
        HashMap<String, String> components = new HashMap<>();

        try{
            for (String c1 : classes) {
                //c1 = c1.replace("com.lihy.invoke.test.", "");
                Component component = Class.forName(c1).getAnnotation(Component.class);
                if (null != component) {
                    components.put(component.id(), c1);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return components;
    }

    public static List<String> getClassName(String packageName) {
        String filePath = ClassLoader.getSystemResource("").getPath() + packageName.replace(".", "\\");
        List<String> fileNames = getClassName(filePath, null);
        return fileNames;
    }

    private static List<String> getClassName(String filePath, List<String> className) {
        List<String> myClassName = new ArrayList<>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                myClassName.addAll(getClassName(childFile.getPath(), myClassName));
            } else {
                String childFilePath = childFile.getPath();
                childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
                childFilePath = childFilePath.replace("\\", ".");
                myClassName.add(childFilePath);
            }
        }
        return myClassName;
    }

    public static void main(String[] args) {
        getComponentClassName("com.lihy.invoke.test");
    }
}
