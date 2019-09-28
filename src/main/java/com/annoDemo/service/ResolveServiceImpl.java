package com.annoDemo.service;

import com.annoDemo.dao.IResolveDao;
import com.annoDemo.domain.Prob;
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

    /**
     * 调用dao实现部分开始
     * */
    public List<Prob> findAllPro() {
        return data.findAllPro();
    }

    public Prob findPro(Integer id) {
        return data.findPro(id);
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
}
