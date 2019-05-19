package me.zhengjie.modules.stu_job.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-19
*/
@Entity
@Data
@Table(name="stu_job")
public class StuJob implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 资源id
     */
    @Column(name = "res_id")
    private Integer resId;

    /**
     * 资源url
     */
    @Column(name = "res_url")
    private String resUrl;

    /**
     * 实验名称
     */
    @Column(name = "exp_name")
    private String expName;

    /**
     * 实验编号
     */
    @Column(name = "exp_number")
    private String expNumber;

    /**
     * 授课教师
     */
    @Column(name = "tea_name")
    private String teaName;

    /**
     * 教工号
     */
    @Column(name = "tea_number")
    private String teaNumber;

    /**
     * 学生名称
     */
    @Column(name = "stu_name")
    private String stuName;

    /**
     * 学号
     */
    @Column(name = "stu_number")
    private String stuNumber;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;
}