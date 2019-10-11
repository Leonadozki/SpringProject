package com.annoDemo.service;

import com.annoDemo.dao.IResolveDao;
import com.annoDemo.domain.Prob;
import com.annoDemo.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

// 把这个类对象存入SpringIoc容器，service区分是逻辑层对象
@Service("resolveService")
public class ResolveServiceImpl implements IResolveService{

    // 注入基本类型和String类型数据
    @Value("最短路径问题")
    public String prob;
    @Value("狄克斯特拉算法")
    public String algorithm;
    @Value("true")
    public Boolean result;


    // 集合类型注入只能通过xml实现
    private String[] testStr;
    private List<String> testList;
    private Set<String> testSet;
    private Map<String, String> testMap;
    private Properties testProps;

    // 第二种注入bean类型数据方法,可直接指定bean的id
    @Resource(name = "resolveDao")
    private IResolveDao solved;

    public void resolveMathPro() {
        System.out.println(prob + " Done with ："+ algorithm +" result is "+ result);
        System.out.println(data);
    }

    public void resolveMassPro(){
        System.out.println(Arrays.toString(testStr));
        System.out.println(testList);
        System.out.println(testSet);
        System.out.println(testMap);
        System.out.println(testProps);
    }


    // 第一种注入bean类型数据方法
    @Resource(name = "resolveDao")
    private IResolveDao data;

    @Resource(name = "transactionManager")
    private TransactionManager tsManager;

    /**
     * --------------------------调用dao实现部分开始------------------------------------
     * */
    public List<Prob> findAllPro() {

        List<Prob> probs = data.findAllPro();

        for (Prob prob : probs) {
            System.out.println(prob);
        }

        return probs;

    }

    public Prob findPro(Integer id) {

        Prob prob = data.findPro(id);

        System.out.println("findPro方法结束。");

        return prob;


    }

    public void savePro(Prob prob) {

        data.savePro(prob);

    }

    public void updatePro(Prob prob) {

        data.updatePro(prob);

    }

    public void deletePro(Integer id) {

        data.deletePro(id);

    }

    public void transfer(String sourceName, String targetName, Integer participants) {

            // 2.1.根据名称查找源发布会数据
            Prob source = data.findByName(sourceName);
            // 2.2.根据名称查找目标发布会数据
            Prob target = data.findByName(targetName);
            // 2.3.修改源、目标发布会与会人数
            source.setParticipant_num(source.getParticipant_num()+participants);
            target.setParticipant_num((target.getParticipant_num()-participants));
            // 2.4.更新数据
            data.updatePro(source);
//            int i = 1/0;
            data.updatePro(target);

            System.out.println("transfer方法结束。");

    }
}
