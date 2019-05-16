package me.zhengjie.modules.major.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-16
*/
@Entity
@Data
@Table(name="major")
public class Major implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 编号
     */
    @Column(name = "number")
    private String number;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    /**
     * 学院
     */
    @Column(name = "yard")
    private String yard;
}