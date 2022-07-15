package com.course.springbootcustomtrimming.configuration;

import com.course.springbootcustomtrimming.annotation.Trimmed;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TrimmedAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Trimmed.class)) {
            MethodInterceptor interceptor = (Object object, Method method, Object[] args, MethodProxy methodProxy) -> {
                for (int i = 0; i < args.length; i++) {
                    if (args[i].getClass().isAssignableFrom(String.class)) {
                        args[i] = ((String) args[i]).trim().replaceAll(" ", "");
                    }
                }
                Object returned = methodProxy.invokeSuper(object, args);
                if (returned != null && returned.getClass().isAssignableFrom(String.class)) {
                    returned = ((String) returned).trim().replaceAll(" ", "");
                }
                return returned;
            };
            var enhancer = new Enhancer();
            enhancer.setSuperclass(bean.getClass());
            enhancer.setCallback(interceptor);

            return BeanPostProcessor.super.postProcessAfterInitialization(enhancer.create(), beanName);
        } else {
            return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
        }
    }
}
