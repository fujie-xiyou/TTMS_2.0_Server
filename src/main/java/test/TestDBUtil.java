package test;

import web.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestDBUtil {
    public static void main(String[] args){
        DBUtil db = new DBUtil();
        PreparedStatement pstmt = db.getPstmt("INSERT INTO `play` (`id`, `name`, `type`, `area`, `rating`, `duration`, `startDate`, `endDate`, `price`, `imgUrl`) VALUES (NULL, 'asd', 'asd', 'asd', 'asd', '123', '2018-06-04', '2018-06-13', '1221', 'https://img.alicdn.com/bao/uploaded/i4/TB1zFl8uCtYBeNjSspaXXaOOFXa_.jpg_300x300.jpg')");
        try {
            pstmt.executeUpdate();
            ResultSet resultSet = pstmt.getGeneratedKeys();
            resultSet.next();
            System.out.println(resultSet.getInt(1));
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
