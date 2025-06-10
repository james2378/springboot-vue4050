package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *考勤统计：(AttendanceStatistics)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "AttendanceStatistics")
public class AttendanceStatistics implements Serializable {

    //AttendanceStatistics编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_statistics_id")
    private Integer attendance_statistics_id;
    // 班级名称
    @Basic
    private String class_name;
    // 考勤名称
    @Basic
    private String attendance_name;
    // 应到人数
    @Basic
    private Integer number_of_persons_due;
    // 出勤人数
    @Basic
    private Integer attendance;
    // 请假人数
    @Basic
    private Integer number_of_people_asking_for_leave;
    // 缺勤人数
    @Basic
    private Integer number_of_absentees;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
