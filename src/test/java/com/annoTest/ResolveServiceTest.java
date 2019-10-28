package com.annoTest;

import com.annoDemo.domain.Prob;
import com.annoDemo.service.IResolveService;
import config.SpringConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class ResolveServiceTest {

    @Resource(name = "resolveService")
    private IResolveService is;

    @Test
    public void testFindAll(){
        is.findAllPro();

    }

    @Test
    public void testFindOne(){

        Prob prob = is.findPro(2);

    }

    @Ignore
    public void testSaveOne(){
        Date date = new Date();
        Prob testPro = new Prob();
        long longTime = date.getTime();
        Timestamp ts = new Timestamp(longTime);
        testPro.setName("测试数据03");
        testPro.setParticipant_num(110);
        testPro.setAddress("测试华富洋大厦");
        testPro.setStatus(1);
        testPro.setStart_time(ts);
        testPro.setCreate_time(ts);
        is.savePro(testPro);
        System.out.println("save方法运行结束。");
    }

    @Test
    public void testTransfer(){
        is.transfer("腾讯发布会", "小米新品发布会", 1);
    }
}
