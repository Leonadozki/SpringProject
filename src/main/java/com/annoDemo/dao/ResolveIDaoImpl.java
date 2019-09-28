package com.annoDemo.dao;

import com.annoDemo.domain.Prob;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

// 把这个类对象存入SpringIoc容器，用repository区分是持久层对象
@Repository("resolveDao")
public class ResolveIDaoImpl implements IResolveDao {

    @Autowired
    private QueryRunner runner;


    public List<Prob> findAllPro() {
        try {
            return runner.query("select * from sign_event",new BeanListHandler<Prob>(Prob.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Prob findPro(Integer id) {
        try {
            return runner.query("select * from sign_event where id = ?", new BeanHandler<Prob>(Prob.class), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void savePro(Prob prob) {
        try {
            runner.update("insert into sign_event(name, participant_num, status, address, start_time, create_time) " +
                    "values(?,?,?,?,?,?)", prob.getName(),prob.getParticipant_num(),prob.getStatus(),
                    prob.getAddress(),prob.getStart_time(),prob.getCreate_time());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePro(Prob prob) {
        try {
            runner.update("update sign_event set name=?, status=?, address=?", prob.getName(),
                    prob.getStatus(),prob.getAddress());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePro(Integer id) {
        try {
            runner.update("delete from sign_event where id=?", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 结果打印
    public void resolveProb() {

        System.out.println("问题解决成功！");
    }
}
