package me.zhengjie.modules.spider_info.domain;

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
@Table(name="spider_info")
public class SpiderInfo implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 域名
     */
    @Column(name = "domain")
    private String domain;

    /**
     * 网站名称
     */
    @Column(name = "site_name")
    private String siteName;

    /**
     * 起始链接
     */
    @Column(name = "start_url")
    private String startUrl;

    /**
     * 动态字段列表
     */
    @Column(name = "dynamic_field")
    private String dynamicField;

    /**
     * 正文正则表达式
     */
    @Column(name = "title_reg")
    private String titleReg;

    /**
     * 正文Xpath
     */
    @Column(name = "title_xpath")
    private String titleXpath;

    /**
     * 线程数
     */
    @Column(name = "thread")
    private Integer thread;

    /**
     * 失败的网页重试次数
     */
    @Column(name = "retry")
    private Integer retry;

    /**
     * 抓取每个网页睡眠时间
     */
    @Column(name = "sleep")
    private Integer sleep;

    /**
     * 网站权重
     */
    @Column(name = "priority")
    private Integer priority;

    /**
     * url正则
     */
    @Column(name = "url_reg")
    private String urlReg;

    /**
     * 编码
     */
    @Column(name = "charset")
    private String charset;

    /**
     * 回调url
     */
    @Column(name = "callback_url")
    private String callbackUrl;

    /**
     * User Agent
     */
    @Column(name = "user_agent")
    private String userAgent;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;
}