package tomcat.yzl;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    static{
        try {
            //读取配置文件
            InputStream in = DBUtil.class.getResourceAsStream("db.properties");
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
    public static void exe(String sql) throws SQLException{
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
    public static ResultSet query(String sql) throws SQLException{
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //执行查询语句并返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

}
