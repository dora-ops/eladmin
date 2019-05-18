package me.zhengjie.modules.spider_log.domain;

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
@Table(name="spider_log")
public class SpiderLog implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 数据
     */
    @Column(name = "data")
    private String data;

    /**
     * 任务id
     */
    @Column(name = "task_id")
    private Integer taskId;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    /**
     * 请求地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 请求头
     */
    @Column(name = "header")
    private String header;
}