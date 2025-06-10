package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *院领导：(HospitalLeaders)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "HospitalLeaders")
public class HospitalLeaders implements Serializable {

    //HospitalLeaders编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_leaders_id")
    private Integer hospital_leaders_id;
    // 院领导编号
    @Basic
    private String institute_leader_no;
    // 院领导姓名
    @Basic
    private String name_of_institute_leader;
    // 职务
    @Basic
    private String post;
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
