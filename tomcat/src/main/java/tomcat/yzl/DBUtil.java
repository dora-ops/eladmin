package tomcat.yzl;

import me.zhengjie.domain.vo.ColumnInfo;
import org.apache.commons.beanutils.BeanUtils;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

public class DBUtil {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            //读取配置文件
            InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            //加载配置文件
            properties.load(in);
            //获取配置文件中的数据
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            //加载数据库链接驱动
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取一个数据库链接
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


    /**
     * 增加数据
     */
    public static void exe(String sql) throws SQLException {
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        conn.commit();
        conn.close();
    }

    /**
     * 查找数据
     */
    public static ResultSet query(String sql) throws SQLException {
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //执行查询语句并返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }


    public static List<ColumnInfo> convertList(String tableName, String dataBase) throws SQLException {
        String sql = SqlUtil.getTableCollums(tableName, dataBase);
        System.out.println(sql);
        ResultSet rs = DBUtil.query(sql);
        List<ColumnInfo> list = new ArrayList<>();
        try {
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = md.getColumnName(i);
                    if (columnName.equals("DATA_TYPE")) {
                        rowData.put("columnType", rs.getObject(i));
                    } else {
                        String[] split = columnName.toLowerCase().split("_");
                        String secondStr = split[1];
                        CharSequence firstChar = secondStr.subSequence(0, 1);
                        rowData.put(split[0] + firstChar.toString().toUpperCase() + secondStr.subSequence(1, secondStr.length()), rs.getObject(i));
                    }

                }
                ColumnInfo t = new ColumnInfo();
                BeanUtils.populate(t, rowData);
                list.add(t);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
