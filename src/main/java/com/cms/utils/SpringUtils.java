package com.cms.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext ac = null;
    private static SpringUtils springConfigTool = null;

    public synchronized static SpringUtils init() {
        if (springConfigTool == null) {
            springConfigTool = new SpringUtils();
        }
        return springConfigTool;
    }

    public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
        ac = applicationContext;
    }

    public synchronized static Object getBean(String beanName) {
        if(ac != null) {
            return ac.getBean(beanName);
        } else {
            return null;
        }
    }
}
