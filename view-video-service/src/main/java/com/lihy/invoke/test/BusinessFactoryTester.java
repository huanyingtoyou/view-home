package com.lihy.invoke.test;

import com.lihy.invoke.test.service.BusinessObject;
import com.lihy.invoke.test.source.BeanFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lihy
 * @date 2018/09/29
 */
public class BusinessFactoryTester {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory("com.lihy.invoke.test");
        try {
            BusinessObject businessObject = (BusinessObject) beanFactory.getBean("businessObject");
            businessObject.print();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
