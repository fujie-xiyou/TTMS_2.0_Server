package web.util;

import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBUtil {
    private final String dbConnFile = "database/jdbc.properties";
    private Connection conn = null;
    private String dbDriver;    //定义驱动
    private String dbURL;        //定义URL
    private String userName;    //定义用户名
    private String password;    //定义密码

    private static ResourceBundle rb = ResourceBundle.getBundle("database/jdbc");
    //从配置文件取数据库链接参数
    private void loadConnProperties() {
        Properties props = new Properties();

//        try {
//            props.load(new FileInputStream(dbConnFile));//根据配置文件路径Conf加载配置文件
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        this.dbDriver = rb.getString("driver");//从配置文件中取得相应的参数并设置类变量
        this.dbURL = rb.getString("url");
        this.userName = rb.getString("username");
        this.password = rb.getString("password");

    }

    public boolean openConnection() {
        try {
            loadConnProperties();
            Class.forName(dbDriver);
//            this.conn = DriverManager.getConnection(dbURL, userName, password);
            this.conn = Druid.getConn();
            return true;
        } catch (ClassNotFoundException classnotfoundexception) {
            classnotfoundexception.printStackTrace();
            System.err.println("db: " + classnotfoundexception.getMessage());
        }
//        catch (SQLException sqlexception) {
//            System.err.println("db.getconn(): " + sqlexception.getMessage());
//        }
        return false;
    }
    public PreparedStatement getPstmt(String sql){
        openConnection();
        try {
            return  (PreparedStatement) conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {

        }
    }


    protected void finalize() throws Exception {
        try {
            if (null != conn)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //占位符形式的查询方法
    public ResultSet query(PreparedStatement pstmt) throws Exception{
        ResultSet rtn = null;
        try {
            rtn = pstmt.executeQuery();
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return rtn;
    }
    //占位符方式插入(返回标示列的值)
    public int insert(PreparedStatement pstmt) throws Exception{
        int rtn = 0;
        try{
            pstmt.executeUpdate();
            ResultSet res = pstmt.getGeneratedKeys();
            if(res != null && res.first()) {
                rtn = res.getInt(1);
            }
            pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rtn;
    }
    //占位符方式插入,删除.修改(返回1表示成功)
    public int command(PreparedStatement pstmt){
        int rtn = 0;
        try{
            rtn = pstmt.executeUpdate();
            pstmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return  rtn;
    }

    // 查询并得到结果集
    public ResultSet execQuery(String sql) throws Exception {
        ResultSet rstSet = null;
        try {
            if (null == conn)
                throw new Exception("Database not connected!");
            Statement stmt = conn.createStatement();
            rstSet = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rstSet;
    }

    // 插入一条新纪录，并获取标识列的值
    public ResultSet getInsertObjectIDs(String insertSql) throws Exception {
        ResultSet rst = null;
        try {
            if (null == conn)
                throw new Exception("Database not connected!");

            Statement stmt = conn.createStatement();

            stmt.executeUpdate(insertSql, Statement.RETURN_GENERATED_KEYS);
            rst = stmt.getGeneratedKeys();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rst;
    }

    // 插入、更新、删除
    public int execCommand(String sql) throws Exception {
        int flag = 0;
        try {
            if (null == conn)
                throw new Exception("Database not connected!");

            Statement stmt = conn.createStatement();
            flag = stmt.executeUpdate(sql);

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // 释放资源
    public void close(ResultSet rst) throws Exception {
        try {
            Statement stmt = rst.getStatement();
            rst.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();       }
    }


    // 释放资源
    public void close(Statement stmt) throws Exception {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 释放资源
    public void close() throws SQLException, Exception {
        if (null != conn) {
            conn.close();
            conn = null;
        }
    }
}
