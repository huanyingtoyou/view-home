package com.lihy.invoke.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author lihy
 * @date 2018/09/29
 */
public class BeanFactory {
    private HashMap<String, Object> beanPool;
    private HashMap<String, String> components;

    public BeanFactory(String packageName) {
        beanPool = new HashMap<>();
        scanComponents(packageName);
    }

    private void scanComponents(String packageName) {
        components = ComponentScanner.getComponentClassName(packageName);
    }

    public Object getBean(String id) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (beanPool.containsKey(id)) {
            return beanPool.get(id);
        }

        if (components.containsKey(id)) {
            Object bean = Class.forName(components.get(id)).newInstance();
            bean = assemlyMember(bean);
            beanPool.put(id, bean);
            return getBean(id);
        }
        throw new ClassNotFoundException();
    }

    private Object assemlyMember(Object obj) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Class cl = obj.getClass();
        for (Field field : cl.getDeclaredFields()) {
            Autowire autowire = field.getAnnotation(Autowire.class);

            if (null != autowire) {
                Method method = cl.getMethod("set" + captureName(field.getName()), field.getType());
                method.invoke(obj, getBean(autowire.id()));
            }
        }
        return obj;
    }

    public static String captureName(String name) {
        char[] chars = name.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }
}
