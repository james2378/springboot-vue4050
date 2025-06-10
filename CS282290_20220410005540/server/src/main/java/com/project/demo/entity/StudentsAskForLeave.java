package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学生请假：(StudentsAskForLeave)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "StudentsAskForLeave")
public class StudentsAskForLeave implements Serializable {

    //StudentsAskForLeave编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "students_ask_for_leave_id")
    private Integer students_ask_for_leave_id;
    // 学号
    @Basic
    private Integer student_number;
    // 学生姓名
    @Basic
    private String student_name;
    // 班级名称
    @Basic
    private String class_name;
    // 辅导员工号
    @Basic
    private Integer tutor_no;
    // 请假类型
    @Basic
    private String leave_type;
    // 请假开始时间
    @Basic
    private Timestamp leave_start_time;
    // 请假结束时间
    @Basic
    private Timestamp leave_end_time;
    // 辅导员审批
    @Basic
    private String counselor_approval;
    // 院领导审批
    @Basic
    private String approved_by_leaders_of_the_institute;
    // 请假原因
    @Basic
    private String reason_for_leave;
    // 辅导员回复
    @Basic
    private String counselor_reply;
    // 院领导回复
    @Basic
    private String reply_from_leaders_of_the_institute;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
