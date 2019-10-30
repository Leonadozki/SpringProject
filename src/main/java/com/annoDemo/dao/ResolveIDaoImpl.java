package com.annoDemo.dao;

import com.annoDemo.domain.Prob;
import com.annoDemo.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

// 把这个类对象存入SpringIoc容器，用repository区分是持久层对象
@Repository("resolveDao")
public class ResolveIDaoImpl implements IResolveDao {

    @Resource
    private QueryRunner runner;

    @Resource
    private ConnectionUtils connectionUtils;


    public List<Prob> findAllPro() {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from sign_event",new BeanListHandler<Prob>(Prob.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Prob findPro(Integer id) {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from sign_event where id = ?", new BeanHandler<Prob>(Prob.class), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void savePro(Prob prob) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"insert into sign_event(name, participant_num, status, address, start_time, create_time) " +
                    "values(?,?,?,?,?,?)", prob.getName(),prob.getParticipant_num(),prob.getStatus(),
                    prob.getAddress(),prob.getStart_time(),prob.getCreate_time());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePro(Prob prob) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"update sign_event set name=?, participant_num=?, status=?, address=? where id=?",
                    prob.getName(),prob.getParticipant_num(),prob.getStatus(),prob.getAddress(),prob.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePro(Integer id) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"delete from sign_event where id=?", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Prob findByName(String name) {
        try {
            List<Prob> events = runner.query(connectionUtils.getThreadConnection(),"select * from sign_event where name = ?", new BeanListHandler<Prob>(Prob.class), name);
            if(events == null || events.size() == 0){
                return  null;
            }if(events.size() > 1){
                throw new RuntimeException("Response data is not unique.");
            }
            return events.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 结果打印
    public void resolveProb() {

        System.out.println("问题解决成功！");
    }
}
