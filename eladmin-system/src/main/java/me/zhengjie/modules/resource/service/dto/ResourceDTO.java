package me.zhengjie.modules.resource.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author y
* @date 2021-05-15
*/
@Data
public class ResourceDTO implements Serializable {

    private Long id;

    /**
     * 路径
     */
    private String filepath;

    /**
     * 生成文件名
     */
    private String filename;

    /**
     * 原文件名称
     */
    private String originalname;
}