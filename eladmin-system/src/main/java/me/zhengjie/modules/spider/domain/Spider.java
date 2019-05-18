package me.zhengjie.modules.spider.domain;

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
@Table(name="spider")
public class Spider implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 爬虫code
     */
    @Column(name = "code")
    private String code;

    /**
     * 域名
     */
    @Column(name = "domain")
    private String domain;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;
}