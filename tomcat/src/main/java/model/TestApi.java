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
@Table(name="`test_api`")
public class TestApi implements Serializable {

    @Column(name = "`body`")
    private String body;

    @Column(name = "`query_param`")
    private String queryParam;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`success`",nullable = false)
    private String success;

    @Column(name = "`n_id`",nullable = false)
    private Integer nId;
    @Column(name = "`seq`")
    private Integer seq;
}