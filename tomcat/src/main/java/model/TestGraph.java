package model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author 
* @date 2020-12-15
*/
public class TestGraph implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`des`")
    private String des;
}