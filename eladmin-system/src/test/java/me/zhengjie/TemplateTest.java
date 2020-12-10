package me.zhengjie;

import cn.hutool.core.util.HexUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import me.zhengjie.domain.GenConfig;
import me.zhengjie.domain.vo.ColumnInfo;
import me.zhengjie.utils.ColUtil;
import me.zhengjie.utils.GenUtil;
import me.zhengjie.utils.StringUtils;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;

/**
 * 既可以记载src\main\resources和src\test\resources
 */
public class TemplateTest {

    private static final String TIMESTAMP = "Timestamp";

    private static final String BIGDECIMAL = "BigDecimal";

    private static final String PK = "PRI";

    static String tableName = "users";
    //数据库
//    static String database = "test";
    static String database = "illustrator";
//    前端
    static String[] genModuleList = new String[]{"index", "header", "eForm", "edit", "api"};
//    后端
static String[] genAdminModuleList = new String[]{"Controller", "Dto", "Entity", "Mapper", "QueryService","Repository","Service","ServiceImpl"};
    //    是否模糊查询和精确查询
    static Map queryMap = new HashMap();

    static {
//         查询 1:模糊 2：精确
        queryMap.put("hash", 2);
    }

    //    文件后缀名
    String suffix = ".vue";
    //生成路径
//    String genPath="E:\\前后端分离\\common-system\\code\\eladmin\\eladmin-system\\src\\test\\java\\me\\zhengjie\\";
   static String genFrontPath = "E:\\study\\eladmin\\eladmin-system\\src\\test\\java\\me\\zhengjie\\";
    static String genApiPath = "E:\\study\\eladmin\\eladmin-system\\src\\test\\java\\me\\zhengjie\\";
    static String genPath = "E:\\study\\eladmin\\eladmin-system\\src\\main\\java\\me\\zhengjie\\modules\\"+tableName+"\\";
    @Test
    public void contextLoads() throws IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlJPA");
        EntityManager em = factory.createEntityManager();
//        读取表信息
        StringBuilder sql = new StringBuilder("select column_name, is_nullable, data_type, column_comment, column_key from information_schema.columns where ");

        sql.append("table_name = '" + tableName + "' ");

        sql.append("and table_schema = (select " + "'" + database + "'" + ") order by ordinal_position");
        Query query = em.createNativeQuery(sql.toString());
        List<Object[]> result = query.getResultList();
        List<ColumnInfo> columnInfos = new ArrayList<>();
        for (Object[] obj : result) {
            columnInfos.add(new ColumnInfo(obj[0], obj[1], obj[2], obj[3], obj[4], null, "true"));
        }
        System.out.println(columnInfos);
//        生成变量Map
        Map<String, Object> map = new HashMap();
//    基本配置信息
        map.put("package", "me.zhengjie.modules." + tableName);
        map.put("moduleName", "eladmin-system");
        map.put("author", "jie");
        map.put("date", LocalDate.now().toString());
        map.put("tableName", tableName);
        String className = StringUtils.toCapitalizeCamelCase(tableName);
        map.put("className", className);
        map.put("changeClassName", StringUtils.toCamelCase(tableName));
        map.put("hasTimestamp", false);
        map.put("hasBigDecimal", false);
        map.put("hasQuery", false);
//表信息转换成columns,queryColumns变量
        List<Map<String, Object>> columns = new ArrayList<>();
        List<Map<String, Object>> queryColumns = new ArrayList<>();
        for (ColumnInfo column : columnInfos) {
            Map<String, Object> listMap = new HashMap();
            listMap.put("columnComment", column.getColumnComment());
            listMap.put("columnKey", column.getColumnKey());

            String colType = ColUtil.cloToJava(column.getColumnType().toString());
            if (PK.equals(column.getColumnKey())) {
                map.put("pkColumnType", colType);
            }
            if (TIMESTAMP.equals(colType)) {
                map.put("hasTimestamp", true);
            }
            if (BIGDECIMAL.equals(colType)) {
                map.put("hasBigDecimal", true);
            }
            listMap.put("columnType", colType);
            listMap.put("columnName", column.getColumnName());
            listMap.put("isNullable", column.getIsNullable());
            listMap.put("columnShow", column.getColumnShow());
            listMap.put("changeColumnName", StringUtils.toCamelCase(column.getColumnName().toString()));
            listMap.put("capitalColumnName", StringUtils.toCapitalizeCamelCase(column.getColumnName().toString()));

            if (queryMap.containsKey(column.getColumnName())) {
                listMap.put("columnQuery",
                        queryMap.get(column.getColumnName()));
                map.put("hasQuery", true);
                queryColumns.add(listMap);
            }
            columns.add(listMap);
        }
        map.put("columns", columns);
        map.put("queryColumns", queryColumns);

        //path为根目录，subpath改目录下的模板文件
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        //生成前端模块名
        for (String genModule : genModuleList) {
            String subpath = "generator/front/" + genModule + ".ftl";
            Template template = engine.getTemplate(subpath);
            File file = new File(getFrontFilePath(genModule,map.get("changeClassName").toString()));

            GenUtil.genFile(file, template, map);
        }
        for (String genModule : genAdminModuleList) {
            String subpath = "generator/admin/" + genModule + ".ftl";
            Template template = engine.getTemplate(subpath);
            File file = new File(getAdminFilePath(genModule,className));

            GenUtil.genFile(file, template, map);
        }

    }



    public static String getAdminFilePath(String templateName, String className) {
        String packagePath =genPath;
        if ("Entity".equals(templateName)) {
            return packagePath + "domain" + File.separator + className + ".java";
        }

        if ("Controller".equals(templateName)) {
            return packagePath + "rest" + File.separator + className + "Controller.java";
        }

        if ("Service".equals(templateName)) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if ("ServiceImpl".equals(templateName)) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if ("Dto".equals(templateName)) {
            return packagePath + "service" + File.separator + "dto" + File.separator + className + "DTO.java";
        }

        if ("Mapper".equals(templateName)) {
            return packagePath + "service" + File.separator + "mapper" + File.separator + className + "Mapper.java";
        }

        if ("QueryService".equals(templateName)) {
            return packagePath + "service" + File.separator + "query" + File.separator + className + "QueryService.java";
        }

        if ("Repository".equals(templateName)) {
            return packagePath + "repository" + File.separator + className + "Repository.java";
        }

        return null;
    }

    public static String getFrontFilePath(String templateName, String apiName) {
        String path =genFrontPath;
        if ("api".equals(templateName)) {
            return genApiPath+ File.separator + apiName + ".js";
        }

        if ("index".equals(templateName)) {
            return File.separator + "index.vue";
        }

        if ("header".equals(templateName)) {
            return  File.separator + "module" + File.separator + "header.vue";
        }

        if ("edit".equals(templateName)) {
            return  File.separator + "module" + File.separator + "edit.vue";
        }

        if ("eForm".equals(templateName)) {
            return  File.separator + "module" + File.separator + "form.vue";
        }
        return null;
    }


    @Test
    public void test(){
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park();
        System.out.println("123");
    }

    @Test
    public  void encode() {
        String name = "I am 君山";
        toHex(name.getBytes());
        try {
            byte[] iso8859 = name.getBytes("ISO-8859-1");
            toHex(iso8859);
            byte[] gb2312 = name.getBytes("GB2312");
            toHex(gb2312);
            byte[] gbk = name.getBytes("GBK");
            toHex(gbk);
            byte[] utf16 = name.getBytes("UTF-16");
            toHex(utf16);
            byte[] utf8 = name.getBytes("UTF-8");
            toHex(utf8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void toHex(byte[] bytes){
        System.out.println("bytes = [" + HexUtil.encodeHexStr(bytes) + "]");
    }

}
