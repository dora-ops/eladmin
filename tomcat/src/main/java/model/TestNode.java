package model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @author
 * @date 2020-12-17
 */
@Entity
@Data
@Table(name="`test_node`")
public class TestNode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`next`",nullable = false)
    private Integer next;

    @Column(name = "`g_id`",nullable = false)
    private Integer gId;

    @Column(name = "`uri`",nullable = false)
    private String uri;

    @Column(name = "`method`",nullable = false)
    private String method;


    @Column(name = "`script`")
    private String script;
}