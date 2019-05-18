package me.zhengjie.modules.spider_info.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-18
*/
@Data
public class SpiderInfoDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 域名
     */
    private String domain;

    /**
     * 网站名称
     */
    private String siteName;

    /**
     * 起始链接
     */
    private String startUrl;

    /**
     * 动态字段列表
     */
    private String dynamicField;

    /**
     * 正文正则表达式
     */
    private String titleReg;

    /**
     * 正文Xpath
     */
    private String titleXpath;

    /**
     * 线程数
     */
    private Integer thread;

    /**
     * 失败的网页重试次数
     */
    private Integer retry;

    /**
     * 抓取每个网页睡眠时间
     */
    private Integer sleep;

    /**
     * 网站权重
     */
    private Integer priority;

    /**
     * url正则
     */
    private String urlReg;

    /**
     * 编码
     */
    private String charset;

    /**
     * 回调url
     */
    private String callbackUrl;

    /**
     * User Agent
     */
    private String userAgent;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}