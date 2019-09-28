package com.annoDemo.ui;

import com.annoDemo.dao.IResolveDao;
import com.annoDemo.domain.Prob;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.annoDemo.service.IResolveService;


public class UiImpl {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext("config");
        IResolveDao data =  (IResolveDao) ac.getBean("resolveDao");
        IResolveService service1 = (IResolveService) ac.getBean("resolveService");
        Prob prob = (Prob) ac.getBean("problem");
        data.deletePro(1);
        service1.savePro(prob);
    }
}
