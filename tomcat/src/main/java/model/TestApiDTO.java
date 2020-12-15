package model;

import lombok.Data;
import java.io.Serializable;

/**
* @author 
* @date 2020-12-15
*/
@Data
public class TestApiDTO implements Serializable {

    private String uri;

    private String method;

    private String body;

    private String queryParam;

    private Integer id;
}