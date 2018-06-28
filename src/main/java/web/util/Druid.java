package web.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.*;
import java.util.Properties;

public class Druid {
    private static DruidPooledConnection conn = null;
    private static DruidDataSource ds = null;

    public static DruidPooledConnection getConn() {
        try {
            if (ds == null) {
                //创建数据源
                ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(loadPropertyFile("database/druid.properties"));
            }
            return ds.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public static Properties loadPropertyFile(String fullFile) {
        String webRootPath = null;
        if (null == fullFile || fullFile.equals(""))
            throw new IllegalArgumentException(
                    "Properties file path can not be null : " + fullFile);
        webRootPath = Druid.class.getClassLoader().getResource("")
                .getPath();
//        webRootPath = new File(webRootPath).getParent();
        InputStream inputStream = null;
        Properties p = null;
        try {
            System.out.println(webRootPath
                    + fullFile);
            inputStream = new FileInputStream(new File(webRootPath
                    + fullFile));
            p = new Properties();
            p.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Properties file not found: "
                    + fullFile);
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    "Properties file can not be loading: " + fullFile);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;
    }
}
