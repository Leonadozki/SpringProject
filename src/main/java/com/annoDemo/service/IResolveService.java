package com.annoDemo.service;


import com.annoDemo.domain.Prob;

import java.util.List;

public interface IResolveService {

    // 查找所有的问题数据
    List<Prob> findAllPro();

    // 查找单个问题
    Prob findPro(Integer id);

    // 保存问题
    void savePro(Prob prob);

    // 更新问题
    void updatePro(Prob prob);

    // 删除问题
    void deletePro(Integer id);

    //
    void transfer(String sourceName, String targetName, Integer participants);

}
