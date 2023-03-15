package com.songzhen.howcool.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CostTimeProxy implements MethodInterceptor {
    // 需要被代理的对象
    private Object target;

    public CostTimeProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long starTime = System.nanoTime();
        // invoke 需要使用传入的被代理对象target，invokeSuper需要使用intercept方法入参 o
        Object result = method.invoke(target, objects);
        long endTime = System.nanoTime();
        System.out.println(method + "，耗时(纳秒)：" + (endTime - starTime));
        return result;
    }

    /**
     * 创建任意类的代理对象
     *
     * @param target 需要被代理的对象
     * @param <T> T
     * @return T的代理对象
     */
    public static <T> T createProxy(T target) {
        // 1.创建Enhancer对象,Enhancer继承AbstractClassGenerator
        Enhancer enhancer = new Enhancer();
        // 2.通过setSuperclass来设置父类型，即需要给哪个类创建代理类
        enhancer.setSuperclass(target.getClass());
        /*3.设置回调，需实现org.springframework.cglib.proxy.Callback接口，
        此处我们使用的是org.springframework.cglib.proxy.MethodInterceptor，也是一个接口，继承了Callback接口，
        当调用代理对象的任何方法的时候，都会被MethodInterceptor接口的invoke方法处理*/
        enhancer.setCallback(new CostTimeProxy(target));
        return (T) enhancer.create();
    }
}