package me.zhengjie.modules.spider_task.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-18
*/
@Entity
@Data
@Table(name="spider_task")
public class SpiderTask implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 状态
     */
    @Column(name = "state")
    private String state;

    /**
     * 策略id
     */
    @Column(name = "info_id")
    private Integer infoId;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;
}