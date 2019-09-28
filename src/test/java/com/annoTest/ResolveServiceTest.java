package com.annoTest;

import com.annoDemo.domain.Prob;
import com.annoDemo.service.IResolveService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ResolveServiceTest {

    @Test
    public void testFindAll(){
        ApplicationContext ac = new AnnotationConfigApplicationContext("config");
        IResolveService is1 = (IResolveService) ac.getBean("resolveService");
        List<Prob> probs = is1.findAllPro();
        for (Prob prob: probs) {
            System.out.println(prob);
        }
    }

    @Test
    public void testFindOne(){
        ApplicationContext ac = new AnnotationConfigApplicationContext("config");
        IResolveService is1 = (IResolveService) ac.getBean("resolveService");
        Prob prob = is1.findPro(2);
        System.out.println(prob);
    }
}
