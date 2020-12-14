package tomcat.yzl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Map;

public class SqlUtil {

    public static String getInsertSql(String table, JSONObject jsonObject,Map<String,String> alias){
        StringBuilder builder=new StringBuilder("INSERT INTO `"+table+"`");
        Iterator set = jsonObject.keySet().stream().map(s -> alias.keySet().contains(s) ? s : alias.get(s)).iterator();
        String col = StringUtils.join(set, ",");
        builder.append("(").append(col).append(")");
        builder.append(" VALUES ");
        String value = StringUtils.join(jsonObject.values().iterator(), ",");
        builder.append("(").append(value).append(")");
        return builder.toString();
    }


    public static String getTableCollums(String tableName,String database){
        StringBuilder sql = new StringBuilder("select column_name, is_nullable, data_type, column_comment, column_key from information_schema.columns where ");

        sql.append("table_name = '" + tableName + "' ");

        sql.append("and table_schema = (select " + "'" + database + "'" + ") order by ordinal_position");
        return sql.toString();
    }


}
