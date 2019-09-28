package com.annoDemo.domain;

import com.sun.org.apache.bcel.internal.generic.INEG;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 问题的实体类
 * */

@Service("problem")
public class Prob implements Serializable {

    private Integer id;

    private String name;

    private Integer participant_num;

    private Integer status;

    private String address;

    private Date start_time;

    private Date create_time;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getParticipant_num() {
        return participant_num;
    }

    public Integer getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public Date getStart_time() {
        return start_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParticipant_num(Integer participant_num) {
        this.participant_num = participant_num;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Prob{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", participant_num=" + participant_num +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", start_time=" + start_time +
                ", create_time=" + create_time +
                '}';
    }
}
