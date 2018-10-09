package com.lihy.invoke.test.source;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * bean工厂
 * @author lihy
 * @date 2018/09/29
 */
public class BeanFactory {
    //存放bean的map
    private HashMap<String, Object> beanPool;
    //存放注解类的map，存放包括但不限于注解@controller
    private HashMap<String, String> components;

    public BeanFactory(String packageName) {
        beanPool = new HashMap<>();
        scanComponents(packageName);
    }

    private void scanComponents(String packageName) {
        components = ComponentScanner.getComponentClassName(packageName);
    }

    /**
     * 给bean赋值
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public Object getBean(String id) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        //判断bean的map里存的是否有，如果有这个bean就返回
        if (beanPool.containsKey(id)) {
            return beanPool.get(id);
        }
        //判断注入的bean所代表的类是否在扫描类的注解map中，如果在，就把这个类给bean赋值，那么用autowire注解的bean就会有值了
        if (components.containsKey(id)) {
            Object bean = Class.forName(components.get(id)).newInstance();
            bean = assemlyMember(bean);
            beanPool.put(id, bean);
            return getBean(id);
        }
        throw new ClassNotFoundException();
    }

    /**
     * 遍历类里的所有属性
     * 判断是否有autowire注解
     * 获取类中的set方法
     * 最后为类中autowire注解注释的属性赋值
     * @param obj
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    private Object assemlyMember(Object obj) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Class cl = obj.getClass();
        //循环遍历类中的所有属性，不包括父类的属性
        for (Field field : cl.getDeclaredFields()) {
            Autowire autowire = field.getAnnotation(Autowire.class);
            if (null != autowire) {
                //获取类中指定的方法
                Method method = cl.getMethod("set" + captureName(field.getName()), field.getType());
                //执行方法，为类中的注解bean赋值
                method.invoke(obj, getBean(autowire.id()));
            }
        }
        return obj;
    }

    /**
     * 转换首字母大写
     * @param name
     * @return
     */
    public static String captureName(String name) {
        char[] chars = name.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }
}
