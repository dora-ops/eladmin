package me.zhengjie.utils;

import me.zhengjie.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

public class SqlUtil {

    public static <T> String getInsertSql(String table, T t) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        StringBuilder builder = new StringBuilder("INSERT INTO `" + table + "`");
        Map<String, String> map = BeanUtils.describe(t);
        map.remove("class");
        map.remove("id");
        Iterator set = map.keySet().iterator();
        assert set != null && set.hasNext();

        String col = join(set, "`",true);
        builder.append("(").append(col).append(")");
        builder.append(" VALUES ");
        String value = join(map.values().iterator(), "'",false);
        builder.append("(").append(value).append(")");
        return builder.toString();
    }


    private static String join(Iterator set, String append,boolean toCapitalizeCamelCase) {
        StringBuilder builder = new StringBuilder();
        while (set.hasNext()) {
            Object next = set.next();
            if (next != null) {
                builder = builder.append(append).append(toCapitalizeCamelCase?StringUtils.toUnderScoreCase((String) next):next).append(append);
            } else {
                builder = builder.append(next);
            }
            if (set.hasNext()) {
                builder.append(",");
            }
        }
        return builder.toString();
    }


    public static String getTableCollums(String tableName, String database) {
        StringBuilder sql = new StringBuilder("select column_name, is_nullable, data_type, column_comment, column_key from information_schema.columns where ");

        sql.append("table_name = '" + tableName + "' ");

        sql.append("and table_schema = (select " + "'" + database + "'" + ") order by ordinal_position");
        return sql.toString();
    }


}
