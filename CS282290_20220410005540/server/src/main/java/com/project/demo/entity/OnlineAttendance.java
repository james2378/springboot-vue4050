package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *在线考勤：(OnlineAttendance)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "OnlineAttendance")
public class OnlineAttendance implements Serializable {

    //OnlineAttendance编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "online_attendance_id")
    private Integer online_attendance_id;
    // 教师工号
    @Basic
    private Integer teacher_job_number;
    // 教师姓名
    @Basic
    private String teacher_name;
    // 考勤名称
    @Basic
    private String attendance_name;
    // 签到开始时间
    @Basic
    private Timestamp check_in_start_time;
    // 签到结束时间
    @Basic
    private Timestamp check_in_end_time;
    // 备注
    @Basic
    private String remarks;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
